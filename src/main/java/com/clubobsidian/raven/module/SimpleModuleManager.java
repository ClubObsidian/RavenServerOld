package com.clubobsidian.raven.module;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import com.clubobsidian.raven.Raven;
import com.clubobsidian.raven.classloader.BetterURLClassLoader;
import com.clubobsidian.raven.config.Configuration;
import com.clubobsidian.raven.config.ConfigurationType;

import com.google.inject.Guice;
import com.google.inject.Injector;

import lombok.Cleanup;
import lombok.SneakyThrows;

public class SimpleModuleManager implements ModuleManager {

	private Injector injector;
	private BetterURLClassLoader loader;
	private List<Module> modules;
	
	@Override
	@SneakyThrows(IOException.class)
	public void preLoadModules(Path toLoad) 
	{
		ModuleWrapperStack stack = new ModuleWrapperStack();
		
		@Cleanup
		DirectoryStream<Path> stream = Files.newDirectoryStream(toLoad);
		
		Iterator<Path> it = stream.iterator();
		while(it.hasNext())
		{
			Path path = it.next();
			if(path.getFileName().endsWith(".jar"))
			{
				@Cleanup
				JarFile jar = new JarFile(path.toFile());
				ZipEntry moduleEntry = jar.getEntry("module.yml");
				if(moduleEntry == null)
				{
					Raven.getLogger().error("Module.yml not found for module " + path.getFileName());
					continue;
				}
				@Cleanup
				InputStream moduleStream = jar.getInputStream(moduleEntry);
				Configuration moduleConfig = Configuration.load(moduleStream, ConfigurationType.YAML);
				String main = moduleConfig.getString("main");
				boolean hasMain = (main != null) && (jar.getEntry(main.replace(".", "/") + ".class") != null);
				if(!hasMain)
				{
					Raven.getLogger().error("Main class not found for module " + path.getFileName());
					continue;
				}
				String name = moduleConfig.getString("name");
				boolean hasName = (name != null);
				if(!hasName)
				{
					Raven.getLogger().error("Name not found for module " + path.getFileName());
					continue;
				}
				List<String> depend = moduleConfig.getStringList("depend");
				if(depend == null)
					depend = new ArrayList<>();
				List<String> softDepend = moduleConfig.getStringList("softdepend");
				if(softDepend == null)
					softDepend = new ArrayList<>();
				List<String> loadBefore = moduleConfig.getStringList("loadbefore");
				if(loadBefore == null)
					loadBefore = new ArrayList<>();
				
				List<String> authors = moduleConfig.getStringList("authors");
				if(authors == null)
					authors = new ArrayList<>();
				
				String version = moduleConfig.getString("version");
				if(version == null)
					version = "";
				
				ModuleWrapper wrapper = new ModuleWrapper()
				.setMainClass(main)
				.setName(name)
				.setDepend(depend)
				.setSoftDepend(softDepend)
				.setLoadBefore(loadBefore)
				.setLocation(path)
				.setAuthors(authors)
				.setVersion(version);
				stack.add(wrapper);
			}
		}
		this.doPreLoad(stack);
	}
	
	@SneakyThrows(Exception.class)
	private void doPreLoad(ModuleWrapperStack stack)
	{
		List<URL> urls = new ArrayList<URL>();
		for(ModuleWrapper wrapper : stack)
		{
			for(String soft : wrapper.getSoftDepend())
			{
				int index = stack.getIndexOfModule(soft);
				if(index != -1)
				{
					wrapper.getDepend().add(soft);
				}
			}
			wrapper.getSoftDepend().clear();
			for(String loadBefore : wrapper.getLoadBefore())
			{
				int index = stack.getIndexOfModule(loadBefore);
				if(index != -1)
				{
					stack.get(index).getDepend().add(wrapper.getName());
				}
			}
			wrapper.getLoadBefore().clear();
			urls.add(wrapper.getLocation().toUri().toURL());
		}
		this.loader = new BetterURLClassLoader(urls.toArray(new URL[urls.size()]), Raven.class.getClassLoader());
		this.modules = new ArrayList<Module>();
		
		for(ModuleWrapper wrapper : stack)
		{
			Class<? extends Module> mainClass = (Class<? extends Module>) this.loader.loadClass(wrapper.getMainClass());
			List<Class<? extends Module>> dependencies = new ArrayList<>();
			for(String depend : wrapper.getDepend())
			{
				int index = stack.getIndexOfModule(depend);
				if(index != -1)
				{
					ModuleWrapper modWrapper = stack.get(index);
					Class<? extends Module> cl;
					try 
					{
						cl = (Class<? extends Module>) loader.loadClass(modWrapper.getMainClass());
					} 
					catch (ClassNotFoundException e) 
					{
						Raven.getLogger().error(e.getMessage());
						continue;
					}
					dependencies.add(cl);
				}
				else
				{
					Raven.getLogger().error("Dependency missing for module " + wrapper.getName() + " cannot load");
				}
			}
			Module module = (Module) mainClass.getDeclaredConstructors()[0].newInstance(wrapper.getName(), dependencies);
			if(module != null)
			{
				this.modules.add(module);
			}	
		}
		this.injector = Guice.createInjector(this.modules);
		for(Module module : this.modules)
		{
			this.injector.injectMembers(module);
		}
	}

	@Override
	public void loadModules() 
	{
		for(Module module : this.modules)
		{
			module.onLoad();
		}
		
	}

	@Override
	public void enableModules() 
	{
		for(Module module : this.modules)
		{
			module.onEnable();
		}
	}

	@Override
	public void disableModules() 
	{
		for(Module module : this.modules)
		{
			module.onDisable();
		}
	}
	
	public BetterURLClassLoader getLoader()
	{
		return this.loader;
	}
}
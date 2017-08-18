package com.clubobsidian.raven.server;

import java.nio.file.Path;
import java.util.Optional;

import com.clubobsidian.raven.Raven;
import com.clubobsidian.raven.command.CommandManager;
import com.clubobsidian.raven.command.SimpleCommandManager;
import com.clubobsidian.raven.event.EventManager;
import com.clubobsidian.raven.event.SimpleEventManager;
import com.clubobsidian.raven.module.ModuleManager;
import com.clubobsidian.raven.module.SimpleModuleManager;
import com.clubobsidian.raven.user.SimpleUser;
import com.clubobsidian.raven.user.User;

import lombok.Getter;
import lombok.Setter;

public class SimpleServer implements Server {

	@Getter @Setter
	private Optional<ModuleManager> moduleManager = Optional.of(new SimpleModuleManager());
	@Getter @Setter
	private Optional<CommandManager> commandManager = Optional.of(new SimpleCommandManager());
	@Getter @Setter
	private Optional<EventManager> eventManager = Optional.of(new SimpleEventManager());
	@Getter @Setter
	private Optional<User> user = Optional.of(new SimpleUser());
	
	@Override
	public void start(Path moduleFolder)
	{
		
		if(this.getModuleManager().isPresent())
		{
			ModuleManager manager = this.getModuleManager().get();
			manager.preLoadModules(moduleFolder);
			manager.loadModules();
			manager.enableModules();
			manager.disableModules();
			
			this.input(new ConsoleRunnable());
		}
		else
		{
			Raven.getLogger().info("No module manager found for Raven stopping");
			System.exit(0);
		}
	}

	@Override
	public void input(Runnable input) 
	{
		Thread thread = new Thread(input);
		thread.start();
	}
}
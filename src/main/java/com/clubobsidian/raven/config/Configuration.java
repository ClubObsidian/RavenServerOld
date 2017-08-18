package com.clubobsidian.raven.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.json.JSONConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

public class Configuration extends ConfigurationSection {

	@SneakyThrows(Exception.class)
	public static Configuration load(@NonNull File file)
	{
		Configuration config = new Configuration();
		ConfigurationNode node = null;
		String name = file.getName().toLowerCase();
		
		if(name.endsWith(".yml"))
		{
			ConfigurationLoader<ConfigurationNode> loader = YAMLConfigurationLoader.builder().setFile(file).build();
			node = loader.load();
		}
		else if(name.endsWith(".conf"))
		{
			ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setFile(file).build();
			node = loader.load();
		}
		else if(name.endsWith(".json"))
		{
			ConfigurationLoader<ConfigurationNode> loader = JSONConfigurationLoader.builder().setFile(file).build();
			node = loader.load();
		}
		else
		{
			throw new UnknownFileTypeException(file);
		}
		
		config.node = node;
		return config;
	}
	
	public static Configuration load(@NonNull Path path)
	{
		return load(path.toFile());
	}
	
	@SneakyThrows(IOException.class)
	public static Configuration load(@NonNull InputStream stream, @NonNull ConfigurationType type)
	{
		Configuration config = new Configuration();
		ConfigurationNode node = null;
		@Cleanup
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		Callable<BufferedReader> callable = new Callable<BufferedReader>() 
		{
			
			@Override
			public BufferedReader call()
			{
				return reader;
			}
		};
		
		if(type == ConfigurationType.YAML)
		{
			ConfigurationLoader<ConfigurationNode> loader = YAMLConfigurationLoader
					.builder()
					.setSource(callable)
					.build();
			node = loader.load();
		}
		else if(type == ConfigurationType.HOCON)
		{
			ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader
					.builder()
					.setSource(callable)
					.build();
			node = loader.load();
		}
		else if(type == ConfigurationType.JSON)
		{
			ConfigurationLoader<ConfigurationNode> loader = JSONConfigurationLoader
					.builder()
					.setSource(callable)
					.build();
			node = loader.load();
		}
		
		config.node = node;
		return config;
	}
}
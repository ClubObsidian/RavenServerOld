package com.clubobsidian.raven.config;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.json.JSONConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

public class ConfigurationFile extends ConfigurationSection {

	public static ConfigurationFile load(@Nonnull File file) throws IOException, UnknownFileTypeException
	{
		ConfigurationFile config = new ConfigurationFile();
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
		else if(name.endsWith(".gson"))
		{
			ConfigurationLoader<ConfigurationNode> loader = GsonConfigurationLoader.builder().setFile(file).build();
			node = loader.load();
		}
		else
		{
			throw new UnknownFileTypeException(file);
		}
		config.node = node;
		return config;
	}
}
package com.clubobsidian.raven.server;

import java.nio.file.Path;
import java.util.Optional;

import com.clubobsidian.raven.command.CommandManager;
import com.clubobsidian.raven.event.EventManager;
import com.clubobsidian.raven.module.ModuleManager;
import com.clubobsidian.raven.user.User;

public interface Server {

	public Optional<ModuleManager> getModuleManager();
	public Optional<CommandManager> getCommandManager();
	public Optional<EventManager> getEventManager();
	
	public void setModuleManager(Optional<ModuleManager> moduleManager);
	public void setCommandManager(Optional<CommandManager> commandManager);
	public void setEventManager(Optional<EventManager> eventManager);
	
	public Optional<User> getUser();
	public void setUser(Optional<User> user);
	
	public void input(Runnable input);
	
	public void start(Path moduleFolder);
	
	
}
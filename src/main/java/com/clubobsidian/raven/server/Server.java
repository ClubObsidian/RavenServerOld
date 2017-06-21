package com.clubobsidian.raven.server;

import java.util.Optional;

import com.clubobsidian.raven.command.CommandManager;
import com.clubobsidian.raven.event.EventManager;
import com.clubobsidian.raven.module.ModuleManager;

public interface Server {

	public Optional<ModuleManager> getModuleManager();
	public Optional<CommandManager> getCommandManager();
	public Optional<EventManager> getEventManager();
	
	public void setModuleManager(Optional<ModuleManager> moduleManager);
	public void setCommandManager(Optional<CommandManager> commandManager);
	public void setEventManager(Optional<EventManager> eventManager);
	
}
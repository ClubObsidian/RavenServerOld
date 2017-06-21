package com.clubobsidian.raven.server;

import java.util.Optional;

import com.clubobsidian.raven.command.CommandManager;
import com.clubobsidian.raven.command.SimpleCommandManager;
import com.clubobsidian.raven.event.EventManager;
import com.clubobsidian.raven.event.SimpleEventManager;
import com.clubobsidian.raven.module.ModuleManager;
import com.clubobsidian.raven.module.SimpleModuleManager;

public class SimpleServer implements Server {

	private Optional<ModuleManager> moduleManager = Optional.of(new SimpleModuleManager());
	private Optional<CommandManager> commandManager = Optional.of(new SimpleCommandManager());
	private Optional<EventManager> eventManager = Optional.of(new SimpleEventManager());
	
	@Override
	public Optional<ModuleManager> getModuleManager() 
	{
		return this.moduleManager;
	}
	
	@Override
	public Optional<CommandManager> getCommandManager() 
	{
		return this.commandManager;
	}
	
	@Override
	public Optional<EventManager> getEventManager() 
	{
		return this.eventManager;
	}

	@Override
	public void setModuleManager(Optional<ModuleManager> moduleManager) 
	{
		this.moduleManager = moduleManager;
	}

	@Override
	public void setCommandManager(Optional<CommandManager> commandManager) 
	{
		this.commandManager = commandManager;
	}

	@Override
	public void setEventManager(Optional<EventManager> eventManager) 
	{
		this.eventManager = eventManager;
	}
}
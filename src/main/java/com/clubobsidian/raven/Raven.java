package com.clubobsidian.raven;

import java.io.IOException;
import java.util.Optional;

import com.clubobsidian.raven.command.CommandManager;
import com.clubobsidian.raven.event.EventManager;
import com.clubobsidian.raven.module.ModuleManager;
import com.clubobsidian.raven.server.Server;
import com.clubobsidian.raven.server.SimpleServer;

public class Raven {

	private static Optional<Server> server = Optional.of(new SimpleServer());
	
	public static void main(String[] args) throws IOException
	{
		//Make instance of SimpleModuleManager to check modules for pre-init
		
		

	}
	
	public static Optional<Server> getServer()
	{
		return Raven.server;
	}
	
	public static void setServer(Optional<Server> server)
	{
		Raven.server = server;
	}
	
	public static ModuleManager getModuleManager()
	{
		return Raven.server.get().getModuleManager().orElse(null);
	}
	
	public static CommandManager getCommandManager()
	{
		return Raven.server.get().getCommandManager().orElse(null);
	}
	
	public static EventManager getEventManager()
	{
		return Raven.server.get().getEventManager().orElse(null);
	}
}
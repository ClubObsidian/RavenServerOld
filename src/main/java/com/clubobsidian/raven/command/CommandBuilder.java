package com.clubobsidian.raven.command;

import java.util.Optional;

import com.clubobsidian.raven.Raven;
import com.clubobsidian.raven.server.Server;

import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class CommandBuilder
{

	@Setter
	private CommandExecutor executor;
	@Setter 
	private String permission;
	@Setter
	private boolean forceRegister;
	@Setter 
	private String name;

	public void build()
	{
		Command command = new Command()
		{
			
			@Override
			public String getPermission()
			{
				return permission;
			}

			@Override
			public String getName() 
			{
				return name;
			}
		};
		
		Optional<Server> server = Raven.getServer();
		if(server.isPresent())
		{
			Optional<CommandManager> commandManager = server.get().getCommandManager();
			if(commandManager.isPresent())
			{
				
				if(this.forceRegister)
				{
					commandManager.get().registerCommand(command, this.executor, true);
				}
				else
				{
					commandManager.get().registerCommand(command, this.executor);
				}
			}
			
		}
	}
}
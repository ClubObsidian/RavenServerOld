package com.clubobsidian.raven.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.clubobsidian.raven.Raven;
import com.clubobsidian.raven.command.event.CommandExecuteEvent;
import com.clubobsidian.raven.event.EventManager;
import com.clubobsidian.raven.server.Server;
import com.clubobsidian.raven.user.User;

import lombok.NonNull;

public class SimpleCommandManager implements CommandManager {

	private final Map<String, CommandExecutor> commands = new HashMap<String, CommandExecutor>();
	private final Map<CommandExecutor, Command> commandMap = new HashMap<CommandExecutor, Command>();

	@Override
	public boolean registerCommand(@NonNull Command command, @NonNull CommandExecutor executor) 
	{
		if(this.commandExists(command.getName()))
			return false;

		return this.registerCommand(command, executor, false);
	}

	@Override
	public boolean registerCommand(@NonNull Command command, @NonNull CommandExecutor executor, boolean force) 
	{
		this.commands.put(command.getName(), executor);
		this.commandMap.put(executor, command);
		return true;
	}

	@Override
	public boolean commandExists(@NonNull String command) 
	{
		return this.commands.keySet().contains(command);
	}

	@Override
	public boolean dispatchCommand(@NonNull User user, @NonNull String command) 
	{
		char ch = command.charAt(0);
		if(ch == '!' || ch == '/')
		{
			command = command.substring(1);
		}

		String[] args = command.split(" ");
		if(args.length > 0)
		{
			String name = args[0];
			if(this.commandExists(name))
			{
				CommandExecutor executor = this.commands.get(name);
				if(args.length > 1)
				{
					args = Arrays.copyOfRange(args, 1, args.length);
				}
				else
				{
					args = new String[0];
				}
				Command cmd = this.commandMap.get(executor);
				CommandExecuteEvent event = new CommandExecuteEvent(name, args);
				if(!user.hasPermission(cmd.getPermission()))
				{
					event.setCancelled(true);
				}
				Optional<Server> server = Raven.getServer();
				if(server.isPresent())
				{
					Optional<EventManager> eventManager = server.get().getEventManager();
					if(eventManager.isPresent())
					{
						eventManager.get().dispatchEvent(event);
						if(!event.isCancelled())
						{
							executor.onCommand(user, cmd, args);
							return true;
						}
					}
				}

			}
			else
			{
				user.sendMessage("Unknown command " + name + " please try again");
			}
		}
		return false;
	}
}
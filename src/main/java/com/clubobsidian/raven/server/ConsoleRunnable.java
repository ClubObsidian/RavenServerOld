package com.clubobsidian.raven.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import com.clubobsidian.raven.Raven;
import com.clubobsidian.raven.command.CommandManager;
import com.clubobsidian.raven.user.User;

import lombok.SneakyThrows;

public class ConsoleRunnable implements Runnable {

	@Override
	@SneakyThrows(IOException.class)
	public void run()
	{
		String line = null;
		if(System.console() != null)
		{	
			line = System.console().readLine();
		}
		else
		{  
			//Fixes bug with some ides not support console
			//https://stackoverflow.com/questions/4203646/system-console-returns-null
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			line = reader.readLine();

			Optional<Server> server = Raven.getServer();
			if(server.isPresent())
			{
				Optional<CommandManager> commandManager = server.get().getCommandManager();
				if(commandManager.isPresent())
				{
					Optional<User> user = server.get().getUser();
					if(user.isPresent())
					{
						commandManager.get().dispatchCommand(user.get(), line);
					}
				}

			}
		}
		this.run();
	}
}
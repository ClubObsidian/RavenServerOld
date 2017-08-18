package com.clubobsidian.raven.command;

import com.clubobsidian.raven.user.User;

public interface CommandExecutor {

	public boolean onCommand(User sender, Command cmd, String[] args);
	
}

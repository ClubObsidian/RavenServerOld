package com.clubobsidian.raven.command;

import com.clubobsidian.raven.user.User;

public interface CommandManager {

	public abstract boolean registerCommand(Command command, CommandExecutor executor);
	public abstract boolean registerCommand(Command command, CommandExecutor executor, boolean force);
	public abstract boolean commandExists(String command);
	public abstract boolean dispatchCommand(User user, String command);
}

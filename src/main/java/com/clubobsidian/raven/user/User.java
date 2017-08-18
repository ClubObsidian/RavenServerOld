package com.clubobsidian.raven.user;

import java.util.List;

public interface User {

	public boolean hasPermission(String permission);
	public List<String> getPermissions();
	public void sendMessage(String message);
}

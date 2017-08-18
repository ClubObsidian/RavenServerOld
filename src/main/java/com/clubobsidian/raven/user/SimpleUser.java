package com.clubobsidian.raven.user;

import java.util.ArrayList;
import java.util.List;

import com.clubobsidian.raven.Raven;

import lombok.Getter;
import lombok.NonNull;

public class SimpleUser implements User {

	@Getter(lazy = true)
	private final List<String> permissions = permissions();
	
	@Override
	public boolean hasPermission(String permission)
	{
		return true;
	}

	private List<String> permissions()
	{
		List<String> permissions = new ArrayList<String>();
		permissions.add("*");
		return permissions;
	}
	
	@Override
	public void sendMessage(@NonNull String msg) 
	{
		Raven.getLogger().info(msg);
	}
}
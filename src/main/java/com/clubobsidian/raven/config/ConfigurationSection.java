package com.clubobsidian.raven.config;

import java.util.ArrayList;
import java.util.List;

import com.google.common.reflect.TypeToken;

import lombok.NonNull;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

public class ConfigurationSection {

	protected ConfigurationNode node;
	
	public ConfigurationSection getSection(@NonNull String path)
	{
		ConfigurationSection section = new ConfigurationSection();
		section.node = this.node.getNode(this.parsePath(path));
		return section;
	}
	
	public Object get(@NonNull String path)
	{
		return this.node.getNode(this.parsePath(path)).getValue();
	}
	
	public String getString(@NonNull String path)
	{
		return this.node.getNode(this.parsePath(path)).getString();
	}
	
	public int getInt(@NonNull String path)
	{
		return this.node.getNode(this.parsePath(path)).getInt();
	}
	
	public Long getLong(@NonNull String path)
	{
		return this.node.getNode(this.parsePath(path)).getLong();
	}
	
	public Float getFloat(@NonNull String path)
	{
		return this.node.getNode(this.parsePath(path)).getFloat();
	}
	
	public boolean getBoolean(@NonNull String path)
	{
		return this.node.getNode(this.parsePath(path)).getBoolean();
	}
	
	public double getDouble(@NonNull String path)
	{
		return this.node.getNode(this.parsePath(path)).getDouble();
	}
	
	public List<String> getStringList(@NonNull String path)
	{
		try 
		{
			return this.node.getNode(this.parsePath(path)).getList(TypeToken.of(String.class));
		}
		catch (ObjectMappingException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Integer> getIntList(@NonNull String path)
	{
		try 
		{
			return this.node.getNode(this.parsePath(path)).getList(TypeToken.of(Integer.class));
		} 
		catch (ObjectMappingException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Long> getLongList(@NonNull String path)
	{
		try 
		{
			return this.node.getNode(this.parsePath(path)).getList(TypeToken.of(Long.class));
		} 
		catch (ObjectMappingException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Float> getFloatList(@NonNull String path)
	{
		try 
		{
			return this.node.getNode(this.parsePath(path)).getList(TypeToken.of(Float.class));
		}
		catch (ObjectMappingException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Boolean> getBooleanList(@NonNull String path)
	{
		try
		{
			return this.node.getNode(this.parsePath(path)).getList(TypeToken.of(Boolean.class));
		}
		catch (ObjectMappingException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Double> getDoubleList(@NonNull String path)
	{
		try
		{
			return this.node.getNode(this.parsePath(path)).getList(TypeToken.of(Double.class));
		}
		catch (ObjectMappingException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public List<String> getKeys()
	{	
		List<String> keys = new ArrayList<String>();
		this.node.getChildrenMap().keySet().forEach(n -> keys.add((String) n));
		return keys;
	}
	
	private Object[] parsePath(@NonNull String path)
	{
		Object[] ar = new Object[1];
		if(path.contains("."))
		{
			String[] split = path.split("\\.");
			ar = new Object[split.length];
			for(int i = 0; i < split.length; i++)
			{
				ar[i] = (Object) split[i];
			}
		}
		else
		{
			ar[0] = (Object) path;
		}
		return ar;
	}
}
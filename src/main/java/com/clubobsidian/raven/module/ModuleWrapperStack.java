package com.clubobsidian.raven.module;

import java.util.Stack;

public class ModuleWrapperStack extends Stack<ModuleWrapper> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2330746198534891930L;

	public boolean insertBefore(ModuleWrapper wrapper, ModuleWrapper insert)
	{
		return this.insertBefore(wrapper.getName(), insert);
	}
	
	public boolean insertBefore(String wrapper, ModuleWrapper insert)
	{
		int index = -1;
		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i).getName().equals(wrapper))
			{
				index = i;
				this.insertElementAt(insert, index);
				break;
			}
		}
		return index != -1;
	}	
	
	public boolean insertAfter(ModuleWrapper wrapper, ModuleWrapper insert)
	{
		return this.insertAfter(wrapper.getName(), insert);
	}
	
	public boolean insertAfter(String wrapper, ModuleWrapper insert)
	{
		int index = -1;
		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i).getName().equals(wrapper))
			{
				index = i + 1;
				this.insertElementAt(insert, index);
				break;
			}
		}
		return index != -1;
	}
	
	public int getIndexOfModule(ModuleWrapper wrapper)
	{
		return this.getIndexOfModule(wrapper.getName());
	}
	
	public int getIndexOfModule(String wrapper)
	{
		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i).getName().equals(wrapper))
			{
				return i;
			}
		}
		return -1;
	}
}
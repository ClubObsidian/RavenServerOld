package com.clubobsidian.raven.event;

public enum EventPriority {

	LOWEST(0),LOW(1),NORMAL(2),HIGH(3),HIGHEST(4),MONITOR(5);
	
	private int priority;
	EventPriority(int priority)
	{
		this.priority = priority;
	}
	
	public int getPriority()
	{
		return this.priority;
	}
}
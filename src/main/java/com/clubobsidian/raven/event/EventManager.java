package com.clubobsidian.raven.event;

public interface EventManager {

	public void dispatchEvent(Event event);
	public void register(Object listener);
	public void unregister(Object listener);
	
}
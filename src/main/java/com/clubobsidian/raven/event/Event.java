package com.clubobsidian.raven.event;

import lombok.Getter;
import lombok.Setter;

public abstract class Event {

	@Getter(lazy = true)
	private final String name = findName();
	@Setter
	private boolean cancelled = false;

	private String findName()
	{
		return this.getClass().getSimpleName();
	}

	public boolean isCancelled()
	{
		return this.cancelled;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Event))
			return false;

		Event event = (Event) obj;
		return event.getName().equals(this.getName());
	}
}
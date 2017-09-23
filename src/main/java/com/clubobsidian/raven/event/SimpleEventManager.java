package com.clubobsidian.raven.event;

import lombok.NonNull;
import net.techcable.event4j.EventBus;
import net.techcable.event4j.EventExecutor;
import net.techcable.event4j.marker.MarkedEvent;

public class SimpleEventManager implements EventManager {

	private final EventBus<Object, Object> bus;
	
	public SimpleEventManager()
	{
		this.bus = EventBus.builder()
				.eventMarker(m -> m.isAnnotationPresent(EventHandler.class) ? (MarkedEvent) m.getAnnotation(EventHandler.class).priority()::ordinal : null)
				.build();
	}
	
	@Override
	public void dispatchEvent(@NonNull Event event) 
	{
		this.bus.fire(event);
	}

	@Override
	public void register(@NonNull Object listener) 
	{
		this.bus.register(listener);
	}

	@Override
	public void unregister(@NonNull Object listener) 
	{
		this.bus.unregister(listener);
	}
}
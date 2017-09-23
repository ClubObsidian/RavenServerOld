package com.clubobsidian.raven.module;

import java.util.List;

import org.apache.logging.log4j.Logger;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import lombok.Getter;
import lombok.NonNull;

public abstract class Module implements com.google.inject.Module {
	
	
	@Getter
	@Inject
	protected final String name;
	private List<Class<? extends Module>> binds;
	@Getter(lazy = true)
	private final Logger logger = logger();
	
	@Inject
	public Module(@NonNull @Named("name") String name, @NonNull List<Class<? extends Module>> binds)
	{
		this.name = name;
		this.binds = binds;
	}
	
	@Override
	public void configure(Binder binder) 
	{
		 for(Class<? extends Module> bind : this.binds)
		 {
			 binder.bind(Module.class).annotatedWith(Names.named("name")).to(bind);
		 }
	}
	
	public void onLoad()
	{
		
	}
	
	public void onEnable()
	{
		
	}
	
	public void onDisable()
	{
		
	}
	
	private Logger logger()
	{
		return org.apache.logging.log4j.LogManager.getLogger(this.getClass());
	}
}
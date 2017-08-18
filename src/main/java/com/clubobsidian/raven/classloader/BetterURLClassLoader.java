package com.clubobsidian.raven.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class BetterURLClassLoader extends URLClassLoader {
	
	public BetterURLClassLoader(URL[] urls, ClassLoader parent) 
	{
		super(urls, parent);
	}

	@Override
	public void addURL(URL url)
	{
		super.addURL(url);
	}
	
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException
	{
		return super.findClass(name);
	}
	
	public URL[] getURLS()
	{
		return super.getURLs();
	}
	
	public void defineClass(String name, byte[] bytes)
	{
		this.defineClass(name, bytes, 0, bytes.length);
	}
}
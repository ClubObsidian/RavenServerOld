package com.clubobsidian.raven.yaml;

import java.io.File;

import javax.annotation.Nonnull;

public class UnknownFileTypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5825723878989203063L;
	
	public UnknownFileTypeException(@Nonnull File file)
	{
		super("Unknown file type for configuration file " + file.getName());
	}

}

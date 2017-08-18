package com.clubobsidian.raven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.slf4j.Logger;

import com.clubobsidian.raven.server.Server;
import com.clubobsidian.raven.server.SimpleServer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Raven {

	@Getter
	private static Optional<Server> server = Optional.of(new SimpleServer());
	@Getter @Setter
	private static Path moduleFolder;
	
	public static void main(String[] args) throws IOException
	{
		if(moduleFolder == null)
		{
			moduleFolder = Files.createDirectories(Paths.get("modules"));
		}
		
		if(Raven.getServer().isPresent())
		{
			Raven.getServer().get().start(moduleFolder);
		}
		else
		{
			System.exit(0);
		}
	}

	public static Logger getLogger()
	{
		return Raven.log;
	}
}
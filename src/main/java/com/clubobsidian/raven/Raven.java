package com.clubobsidian.raven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.apache.logging.log4j.Logger;

import com.clubobsidian.raven.server.Server;
import com.clubobsidian.raven.server.SimpleServer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Raven {

	@Getter
	private static Optional<Server> server = Optional.of(new SimpleServer());
	@Getter @Setter
	private static Path moduleFolder;
	
	public static void main(String[] args) throws IOException
	{
		Raven.log.info("Starting raven....");
		if(moduleFolder == null)
		{
			moduleFolder = Paths.get("modules");
		}
		if(!Files.exists(moduleFolder))
		{
			Files.createDirectories(moduleFolder);
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
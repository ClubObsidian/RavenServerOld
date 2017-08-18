package com.clubobsidian.raven.module;

import java.nio.file.Path;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class ModuleWrapper {

	@Getter @Setter
	private String name;
	@Getter @Setter
	private Path location;
	@Getter @Setter
	private String mainClass;
	@Getter @Setter
	private String version;
	@Getter @Setter
	private List<String> authors;
	@Getter @Setter
	private List<String> loadBefore;
	@Getter @Setter
	private List<String> depend;
	@Getter @Setter
	private List<String> softDepend;
}
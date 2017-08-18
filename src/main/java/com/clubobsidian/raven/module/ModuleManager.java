package com.clubobsidian.raven.module;

import java.nio.file.Path;

public interface ModuleManager {

	public abstract void preLoadModules(Path toLoad);
	public abstract void loadModules();
	public abstract void enableModules();
	public abstract void disableModules();
}

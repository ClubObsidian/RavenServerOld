package com.clubobsidian.raven.scheduler;

import com.clubobsidian.raven.module.Module;

import co.aikar.taskchain.AsyncQueue;
import co.aikar.taskchain.GameInterface;
import co.aikar.taskchain.TaskChainExample;
import co.aikar.taskchain.TaskChainFactory;
import co.aikar.taskchain.TaskChainTasks;

public class Scheduler {

	public static TaskChainFactory create(Module module)
	{
		return null;
	}
	
	private class RavenTaskChainFactory extends TaskChainFactory {

		public RavenTaskChainFactory(GameInterface impl) 
		{
			super(impl);
		}
		
	}
	
	private class RavenInterface implements GameInterface {

		private final Module module;
		private final AsyncQueue asyncQueue;
		
		RavenInterface(Module module, AsyncQueue asyncQueue)
		{
			this.module = module;
			this.asyncQueue = asyncQueue;
		}
		
		@Override
		public boolean isMainThread() 
		{
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public AsyncQueue getAsyncQueue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void postToMain(Runnable run) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void scheduleTask(int gameUnits, Runnable run) {
		
			
		}

		@Override
		public void registerShutdownHandler(TaskChainFactory factory) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}

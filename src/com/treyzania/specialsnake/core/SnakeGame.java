package com.treyzania.specialsnake.core;

import java.util.HashMap;

public class SnakeGame {

	public final long instantiationTime;
	
	public World theWorld;
	public HashMap<String, Entity> specialEntities;
	
	public boolean isPaused;
	
	public SSPanel mainRenderer;
	
	public SnakeGame() {
		
		this.specialEntities = new HashMap<String, Entity>();
		
	}
	
	{
		this.instantiationTime = System.currentTimeMillis();
		this.isPaused = false;
	}
	
}

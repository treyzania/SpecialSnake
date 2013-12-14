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
	
	public void registerSpecialEntity(String name, Entity ent) {
		specialEntities.put(name, ent);
	}
	
	public Entity getSpecialEntity(String name) {
		return specialEntities.get(name);
	}
	
	{
		this.instantiationTime = System.currentTimeMillis();
		this.isPaused = false;
	}
	
}

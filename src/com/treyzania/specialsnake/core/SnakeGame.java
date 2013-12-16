package com.treyzania.specialsnake.core;

import java.util.HashMap;

import javax.swing.JFrame;

public class SnakeGame {

	public final long instantiationTime;
	
	public World theWorld;
	public HashMap<String, Entity> specialEntities;
	public HashMap<String, Controller> controllers;
	
	public boolean isPaused;
	
	public JFrame mainWin;
	public SSPanel mainRenderer;
	
	public SnakeGame() {
		
		this.specialEntities = new HashMap<String, Entity>();
		this.controllers = new HashMap<String, Controller>();
		
	}
	
	public void registerSpecialEntity(String name, Entity ent) {
		specialEntities.put(name, ent);
	}
	
	public void addController(String name, ControllerKeypress ctrl) {
		mainWin.addKeyListener(ctrl);
		controllers.put(name, ctrl);
	}
	
	public Controller getController(String name) {
		return controllers.get(name);
	}
	
	public Controller removeController(String name) {
		
		Controller ctrl = this.getController(name);
		controllers.remove(name);
		return ctrl;
		
	}
	
	public Entity getSpecialEntity(String name) {
		return specialEntities.get(name);
	}
	
	{
		this.instantiationTime = System.currentTimeMillis();
		this.isPaused = false;
	}
	
}

package com.treyzania.specialsnake.core;

import java.awt.event.KeyListener;

/**
 * A way to bridge the gap between the world and the UI.  Controllers an be linked and de-linked easily, without
 * causing problems with 
 * Currently only supports KeyListener, I'm too lazy to add the others.
 * 
 * @author Treyzania
 */
public abstract class Controller implements KeyListener {

	public Entity myEntity;
	
	public Controller(Entity entity) {
		
		this.myEntity = entity;
		entity.controllers.add(this);
		
	}
	
	/**
	 * Called every time the world ticks.  Used in certain cases.
	 */
	public void tick() {
		
	}
	
}

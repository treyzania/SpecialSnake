package com.treyzania.specialsnake.core;

/**
 * A way to bridge the gap between the world and the UI.  Controllers an be linked and de-linked easily, without
 * causing problems with 
 * Currently only supports KeyListener, I'm too lazy to add the others.
 * 
 * @author Treyzania
 */
public abstract class Controller {

	public Entity myEntity;
	
	public Controller(Entity entity) {
		
		this.myEntity = entity;
		
	}
	
}

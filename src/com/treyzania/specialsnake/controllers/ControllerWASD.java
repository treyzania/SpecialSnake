package com.treyzania.specialsnake.controllers;

import java.awt.event.KeyEvent;

import com.treyzania.specialsnake.core.ControllerKeypress;
import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IVelocity;

public class ControllerWASD extends ControllerKeypress {

	public float movementFactor;
	
	public char[] keys = {'W', 'A', 'S', 'D'};
	public float[] xValues = {0, -1, 0, 1};
	public float[] yValues = {-1, 0, 1, 0};
	
	public boolean[] states = new boolean[4];
	
	public ControllerWASD(Entity entity, float factor) {
		
		super(entity);
		
		this.movementFactor = factor;
		
	}

	@Override
	public void tick() {
		
		IVelocity iv = (IVelocity) this.myEntity;
		
		float xVel = iv.getXVelocity();
		float yVel = iv.getYVelocity();
		
		// Keys is four...
		for (int i = 0; i < keys.length; i++){
			
			if (states[i]) {
				
				xVel += xValues[i] * this.movementFactor;
				yVel += yValues[i] * this.movementFactor;
				
			}
			
		}
		
		iv.setXVelocity(xVel);
		iv.setYVelocity(yVel);
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		for (int i = 0; i < keys.length; i++) {
			
			if (this.keys[i] == e.getKeyChar()) {
				
				states[i] = true;
				
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		for (int i = 0; i < keys.length; i++) {
			
			if (this.keys[i] == e.getKeyChar()) {
				
				states[i] = false;
				
			}
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	/* Old Code:
	 * 
	 * 
		float xVel = iv.getXVelocity();
		float yVel = iv.getYVelocity();
		
		if (key == KeyEvent.VK_W) {
			
			yVel = this.movementFactor * -1;
			
		}
		
		if (key == KeyEvent.VK_S) {
			
			yVel = this.movementFactor * 1;
			
		}
		
		if (key == KeyEvent.VK_A) {
			
			xVel = this.movementFactor * -1;
			
		}
		
		if (key == KeyEvent.VK_D) {
			
			xVel = this.movementFactor * 1;
			
		}
	 * 
	 */
	
}

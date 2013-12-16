package com.treyzania.specialsnake.controllers;

import java.awt.event.KeyEvent;

import com.treyzania.specialsnake.core.ControllerKeypress;
import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IVelocity;

public class ControllerWASD extends ControllerKeypress {

	public float movementFactor;
	
	public ControllerWASD(Entity entity, float factor) {
		
		super(entity);
		
		this.movementFactor = factor;
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		

		int key = e.getKeyCode();
		
		IVelocity iv = (IVelocity) this.myEntity;
		
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
		
		iv.setXVelocity(xVel);
		iv.setYVelocity(yVel);
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}

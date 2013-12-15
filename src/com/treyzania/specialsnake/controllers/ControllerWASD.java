package com.treyzania.specialsnake.controllers;

import java.awt.event.KeyEvent;

import com.treyzania.specialsnake.core.ControllerKeypress;
import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IVelocity;

public class ControllerWASD extends ControllerKeypress {

	public ControllerWASD(Entity entity) {
		
		super(entity);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		

		int key = e.getKeyCode();
		
		IVelocity iv = (IVelocity) this.myEntity;
		
		float xVel = iv.getXVelocity();
		float yVel = iv.getYVelocity();
		
		if (key == KeyEvent.VK_W) {
			
			yVel = -1;
			
		}
		
		if (key == KeyEvent.VK_S) {
			
			yVel = 1;
			
		}
		
		if (key == KeyEvent.VK_A) {
			
			xVel = -1;
			
		}
		
		if (key == KeyEvent.VK_D) {
			
			xVel = 1;
			
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

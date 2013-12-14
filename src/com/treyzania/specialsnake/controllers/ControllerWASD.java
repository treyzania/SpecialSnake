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
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		String key = KeyEvent.getKeyText(e.getKeyCode()).toLowerCase();
		
		IVelocity iv = (IVelocity) this.myEntity;
		
		float xVel = iv.getXVelocity();
		float yVel = iv.getYVelocity();
		
		if (key.contains("w")) {
			
			xVel += 0;
			yVel += -1;
			
		}
		
		if (key.contains("s")) {
			
			xVel += 0;
			yVel += 1;
			
		}
		
		if (key.contains("a")) {
			
			xVel += -1;
			yVel += 0;
			
		}
		
		if (key.contains("d")) {
			
			xVel += 1;
			yVel = 0;
			
		}
		
		iv.setXVelocity(xVel);
		iv.setYVelocity(yVel);
		
	}

}

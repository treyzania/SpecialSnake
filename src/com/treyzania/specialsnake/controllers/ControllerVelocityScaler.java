package com.treyzania.specialsnake.controllers;

import java.awt.event.KeyEvent;

import com.treyzania.specialsnake.core.ControllerKeypress;
import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IVelocity;

public class ControllerVelocityScaler extends ControllerKeypress {

	public float velocityFactor;
	public int keycode;
	
	public ControllerVelocityScaler(Entity entity, float factor, int keycode) {
		
		super(entity);
		
		this.velocityFactor = factor;
		this.keycode = keycode;
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		int key = arg0.getKeyCode();
		
		if (key == this.keycode) {
			
			IVelocity iv = (IVelocity) this.myEntity;
			
			iv.setXVelocity(iv.getXVelocity() * this.velocityFactor);
			iv.setYVelocity(iv.getYVelocity() * this.velocityFactor);
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}

package com.treyzania.specialsnake.controllers;

import java.awt.event.KeyEvent;

import com.treyzania.specialsnake.core.ControllerKeypress;
import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IVelocity;

public class ControllerVelocityScaler extends ControllerKeypress {

	public float velocityFactor;
	public int keycode;
	
	public boolean state = false; 
	
	public ControllerVelocityScaler(Entity entity, float factor, int keycode) {
		
		super(entity);
		
		this.velocityFactor = factor;
		this.keycode = keycode;
		
	}
	
	
	@Override
	public void tick() {
		
		if (state) {
			
			IVelocity iv = (IVelocity) this.myEntity;
			
			iv.setXVelocity(iv.getXVelocity() * this.velocityFactor);
			iv.setYVelocity(iv.getYVelocity() * this.velocityFactor);
			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		if (arg0.getKeyCode() == this.keycode) {
			state = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		if (arg0.getKeyCode() == this.keycode) {
			state = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}

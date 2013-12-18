package com.treyzania.specialsnake.controllers;

import java.awt.event.KeyEvent;

import com.treyzania.specialsnake.core.ControllerKeypress;
import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IVelocity;
import com.treyzania.specialsnake.core.PointF;

public class ControllerReset extends ControllerKeypress {

	public int keycode;
	public PointF resetLocation;
	
	public ControllerReset(Entity entity, int keycode, PointF reset) {
		
		super(entity);
		
		this.keycode = keycode;
		this.resetLocation = reset;
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		int key = arg0.getKeyCode();
		
		if (key == this.keycode) {
			
			this.myEntity.setLocation(this.resetLocation);
			
			if (myEntity instanceof IVelocity) {
				
				IVelocity iv = (IVelocity) myEntity;
				
				iv.setXVelocity(0);
				iv.setYVelocity(0);
				
			}
			
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}

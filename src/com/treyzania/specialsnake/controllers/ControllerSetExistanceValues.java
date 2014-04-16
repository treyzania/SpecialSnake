package com.treyzania.specialsnake.controllers;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import com.treyzania.specialsnake.core.ControllerKeypress;
import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IVelocity;

public class ControllerSetExistanceValues extends ControllerKeypress {

	public int keycode;
	
	public ControllerSetExistanceValues(Entity entity, int keycode) {
		
		super(entity);
		
		this.keycode = keycode;
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		int key = arg0.getKeyCode();
		
		if (key == this.keycode) {
			
			System.out.println("MAX FLOAT: " + Float.MAX_VALUE);
			
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			
			int nx = s.nextInt();
			int ny = s.nextInt();
			float nxv = s.nextFloat();
			float nyv = s.nextFloat();
			
			this.myEntity.setLocation(new Point(nx, ny));
			IVelocity eiv = (IVelocity) this.myEntity;
			eiv.setXVelocity(nxv);
			eiv.setYVelocity(nyv);
			
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

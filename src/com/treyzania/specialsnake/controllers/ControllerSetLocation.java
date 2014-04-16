package com.treyzania.specialsnake.controllers;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import com.treyzania.specialsnake.core.ControllerKeypress;
import com.treyzania.specialsnake.core.Entity;

public class ControllerSetLocation extends ControllerKeypress {

	public int keycode;
	
	public ControllerSetLocation(Entity entity, int keycode) {
		
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
			
			this.myEntity.setLocation(new Point(nx, ny));
			
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

package com.treyzania.specialsnake.controllers;

import java.awt.event.KeyEvent;

import com.treyzania.specialsnake.SpecialSnake;
import com.treyzania.specialsnake.core.ControllerKeypress;
import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.PointF;
import com.treyzania.specialsnake.generics.EntityPlayer;

public class ControllerSnakeWASD extends ControllerKeypress {

	public float movementFactor;
	
	public char[] keys = {'w', 'a', 's', 'd'};
	public float[] xValues = {0, -1, 0, 1};
	public float[] yValues = {-1, 0, 1, 0};
	public boolean[] states = new boolean[4];
	
	private boolean moving = false;
	private int movingDir = -1;
	private int cyclesLeft = 0;
	
	private int cycleLength;
	private float unitsPerTick;
	
	public ControllerSnakeWASD(Entity entity, int cycleLength) {
		
		super(entity);
		
		this.cycleLength = cycleLength;
		this.unitsPerTick = ((float) EntityPlayer.CELL_SIZE) / ((float) cycleLength);
		
	}

	@Override
	public void tick() {
		
		boolean aKeyIsDown = false;
		for (boolean s : states) aKeyIsDown |= s; // Sexy one-liner.
		
		if (!moving) {
			
			if (aKeyIsDown) {
				
				int dir = -1;
				for (int i = 0; i < states.length; i++) {
					
					if (states[i]) {
						dir = i;
						break;
					}
					
				}
				
				moving = true;
				movingDir = dir;
				cyclesLeft = this.cycleLength;
				
			}
			
		} else {
			
			if (this.movingDir != -1) {
				
				float chgX = xValues[this.movingDir] * this.unitsPerTick;
				float chgY = yValues[this.movingDir] * this.unitsPerTick;
				
				PointF op = this.myEntity.getLocation();
				PointF p = new PointF(op.x + chgX, op.y + chgY);
				
				this.myEntity.setLocation(p);
				
				cyclesLeft--;
				
			}
			
		}
		
		if (cyclesLeft <= 0 && moving && movingDir != -1) {
			
			moving = false;
			cyclesLeft = 0;
			movingDir = -1;
			
			PointF op = this.myEntity.getLocation();
			float cellSize = (float) EntityPlayer.CELL_SIZE;
			
			float dx = (float) (Math.floor(op.x / cellSize) * cellSize);
			float dy = (float) (Math.floor(op.y / cellSize) * cellSize);
			
			PointF np = new PointF(dx + (cellSize / 2), dy + (cellSize / 2));
			
			this.myEntity.setLocation(np);
			
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		SpecialSnake.log.info("KEY HIT: " + e.getKeyChar());
		
		// General purpose...
		for (int i = 0; i < keys.length; i++) {
			
			if (this.keys[i] == e.getKeyChar()) {
				
				states[i] = true;
				
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		// General purpose...
		for (int i = 0; i < keys.length; i++) {
			
			if (this.keys[i] == e.getKeyChar()) {
				
				states[i] = false;
				
			}
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}

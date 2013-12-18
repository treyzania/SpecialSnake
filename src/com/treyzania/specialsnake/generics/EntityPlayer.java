package com.treyzania.specialsnake.generics;

import java.awt.Point;
import java.awt.Rectangle;

import com.treyzania.specialsnake.SpecialSnake;
import com.treyzania.specialsnake.core.CycleMeter;
import com.treyzania.specialsnake.core.EntUtil;
import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IModel;
import com.treyzania.specialsnake.core.ITickable;
import com.treyzania.specialsnake.core.IVelocity;
import com.treyzania.specialsnake.core.Model;
import com.treyzania.specialsnake.core.VelocityHolder;

public class EntityPlayer extends Entity implements IVelocity, IModel, ITickable, Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2400404211468721109L;
	
	public static final int CELL_SIZE = 16;
	
	private VelocityHolder velocity;
	private Model model;
	
	private Thread positionResetter;
	
	public EnumDirection direction;
	
	public Point lastPos; // I feel like I'm doing this right.
	public Point[] relativeSegLocations;
	
	private CycleMeter movementTracker;
	
	public int ticks;
	
	public EntityPlayer() {
		
		this.velocity = new VelocityHolder();
		this.model = new ModelPlayer(this);
		
		this.positionResetter = new Thread(this, this.getClass().getSimpleName() + "@" + Long.toHexString(this.hashCode()) + "-PositionResetThread");
		positionResetter.start();
		
		this.direction = EnumDirection.NORTH;
		this.movementTracker = new CycleMeter(false);
		this.relativeSegLocations = new Point[16];
		
	}
	
	@Override
	public boolean doTick() {
		
		// I think I should put this here.
		this.movementTracker.updateTime();
		this.ticks++;
		if (ticks > (5 * 10^5)) this.ticks = 0;
		
		// This is what this method is actually for.
		return true;
		
	}
	
	public int getEmptySegments() {
		return (this.relativeSegLocations.length - this.getFilledSegments());
	}
	
	public int getFilledSegments() {
		
		int out = 0;
		for (Point p : this.relativeSegLocations) {
			if (p != null) out++;
		}
		return out;
		
	}
	
	@Override
	public void tick() {
		
		Point p = this.getSegmentPos(0);
		
		if (!p.equals(this.relativeSegLocations[0])) {
			
			// Pick up Bikini Bottom, and push it somewhere else...!
			Point[] oldPoints = this.relativeSegLocations.clone();
			for (int i = 0; i < (this.relativeSegLocations.length - 1); i++) {
				this.relativeSegLocations[i + 1] = oldPoints[i];
			}
			this.relativeSegLocations[0] = p;
			
			// Spam...
			StringBuilder sb = new StringBuilder();
			sb.append("Player Segments:");
			for (Point tp : this.relativeSegLocations) {
				if (tp != null) {
					sb.append(" {" + tp.x + ", " + tp.y + "},");
				} else {
					sb.append(" null,");
				}
			}
			sb.append(".");
			SpecialSnake.log.info(sb.toString());
			
		}
		
	}
	
	public Point getSegmentPos(int segId) {
		
		// TODO Make sure this is logical, based on the cell ID provided.
		
		int cs = CELL_SIZE; // Why is this line here?
		
		int x = (int) (cs * Math.floor(this.x / cs));
		int y = (int) (cs * Math.floor(this.y / cs));
		
		Point p = new Point(x, y);
		
		if (segId > 0 && this.relativeSegLocations[segId] != null) {
			p.translate(this.relativeSegLocations[segId].x, this.relativeSegLocations[segId].y);
		}
		
		return new Point(p.x / CELL_SIZE, p.y / CELL_SIZE);
		
	}
	
	public Rectangle[] getSegments() {
		
		Rectangle[] out = new Rectangle[this.relativeSegLocations.length];
		
		for (int i = 0; i < this.relativeSegLocations.length; i++) {
			
			Point testPoint = this.relativeSegLocations[i];
			
			if (testPoint != null) {
				
				out[i] = new Rectangle(testPoint.x * CELL_SIZE, testPoint.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
				
			}
			
		}
		
		return out;
		
	}
	
	@Override
	public Model getModel() {
		return this.model;
	}
	
	@Override
	public boolean hasVelocity() {
		return true;
	}
	
	@Override
	public float getMass() {
		return 100;
	}

	@Override
	public void setXVelocity(float val) {
		this.velocity.xVel = val;
	}

	@Override
	public float getXVelocity() {
		return this.velocity.xVel;
	}

	@Override
	public void setYVelocity(float val) {
		this.velocity.yVel = val;
	}

	@Override
	public float getYVelocity() {
		return this.velocity.yVel;
	}

	@Override
	public void run() {
		
		boolean looooooop = false; // I wanted a fancy name.
		
		while (looooooop) {
			
			if (System.currentTimeMillis() % 2500 == 0) {
				
				EntUtil.placeEntityInRegion(this, new Rectangle(128, 72, 1280 - (128 * 2), 720 - (72 * 2)));
				
				this.setXVelocity(0);
				this.setYVelocity(0);
				
			}
			
		}
		
	}

}

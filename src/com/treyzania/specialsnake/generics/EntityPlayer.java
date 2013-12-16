package com.treyzania.specialsnake.generics;

import java.awt.Rectangle;
import com.treyzania.specialsnake.core.EntUtil;
import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IModel;
import com.treyzania.specialsnake.core.ITickable;
import com.treyzania.specialsnake.core.IVelocity;
import com.treyzania.specialsnake.core.Model;
import com.treyzania.specialsnake.core.PointF;
import com.treyzania.specialsnake.core.VelocityHolder;

public class EntityPlayer extends Entity implements IVelocity, IModel, ITickable, Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2400404211468721109L;
	
	private VelocityHolder velocity;
	private Model model;
	
	private Thread positionResetter;
	
	public EntityPlayer() {
		
		this.velocity = new VelocityHolder();
		this.model = new ModelPlayer(this);
		
		this.positionResetter = new Thread(this, this.getClass().getSimpleName() + "@" + Long.toHexString(this.hashCode()) + "-PositionResetThread");
		positionResetter.start();
		
	}
	
	@Override
	public boolean doTick() {
		return false;
	}

	@Override
	public void tick() {
		
		
		
	}

	@Override
	public Model getModel() {
		return this.model;
	}
	
	@Override
	public PointF getLocation() {
		return new PointF(this.x, this.y);
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

package com.treyzania.specialsnake.generics;

import com.treyzania.specialsnake.util.Entity;
import com.treyzania.specialsnake.util.IModel;
import com.treyzania.specialsnake.util.ITickable;
import com.treyzania.specialsnake.util.IVelocity;
import com.treyzania.specialsnake.util.Model;
import com.treyzania.specialsnake.util.PointF;

public class EntityPlayer extends Entity implements IVelocity, IModel, ITickable {

	public float xVel;
	public float yVel;
	public Model model;
	
	public EntityPlayer() {
		
	}
	
	@Override
	public boolean doTick() {
		return true;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
	}

	@Override
	public Model getModel() {
		return this.model;
	}

	@Override
	public boolean hasPosition() {
		return true;
	}

	@Override
	public PointF getPosition() {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getXVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setYVelocity(float val) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getYVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}

}

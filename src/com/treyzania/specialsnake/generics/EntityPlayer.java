package com.treyzania.specialsnake.generics;

import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IModel;
import com.treyzania.specialsnake.core.ITickable;
import com.treyzania.specialsnake.core.IVelocity;
import com.treyzania.specialsnake.core.Model;
import com.treyzania.specialsnake.core.PointF;
import com.treyzania.specialsnake.core.VelocityHolder;

public class EntityPlayer extends Entity implements IVelocity, IModel, ITickable {

	private VelocityHolder velocity;
	private Model model;
	
	public EntityPlayer() {
		
		this.velocity = new VelocityHolder();
		this.model = new ModelPlayer(this);
		
	}
	
	@Override
	public boolean doTick() {
		return false;
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

}

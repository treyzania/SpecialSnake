package com.treyzania.specialsnake.generics;

import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IModel;
import com.treyzania.specialsnake.core.IVelocity;
import com.treyzania.specialsnake.core.Model;
import com.treyzania.specialsnake.core.PointF;
import com.treyzania.specialsnake.core.VelocityHolder;

public class EntityBall extends Entity implements IVelocity, IModel {

	private VelocityHolder velocity;
	private Model model;
	
	public EntityBall() {
		
		this.velocity = new VelocityHolder();
		this.model = new ModelBall(this);
		
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

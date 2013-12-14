package com.treyzania.specialsnake.util;

public class Particle implements IReal, IVelocity, ITickable, IModel {

	PointF loc;
	VelocityHolder vel;
	
	@Override
	public Model getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasPosition() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PointF getPosition() {
		return this.loc;
	}

	@Override
	public boolean hasVelocity() {
		return true;
	}

	@Override
	public boolean doTick() {
		return false;
	}

	@Override
	public void tick() {	}

	@Override
	public float getMass() {
		return 0;
	}

	@Override
	public void setXVelocity(float val) {
		this.vel.xVel = val;
	}

	@Override
	public float getXVelocity() {
		return this.vel.xVel;
	}

	@Override
	public void setYVelocity(float val) {
		this.vel.yVel = val;
	}

	@Override
	public float getYVelocity() {
		return this.vel.yVel;
	}

	@Override
	public PointF getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocation(PointF point) {
		this.loc = point;
	}

}

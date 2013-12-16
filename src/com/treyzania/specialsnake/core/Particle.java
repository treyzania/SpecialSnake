package com.treyzania.specialsnake.core;

public class Particle implements IReal, IVelocity, ITickable, IModel {

	private final long creationTime;
	
	PointF loc;
	VelocityHolder vel;
	
	public Particle() {
		
		this.creationTime = System.currentTimeMillis();
		
	}
	
	@Override
	public Model getModel() {
		return null;
	}

	@Override
	public boolean hasLocation() {
		return false;
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
	public void tick() { }

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
		return this.loc;
	}

	@Override
	public void setLocation(PointF point) {
		this.loc = point;
	}

	@Override
	public long getCreationTime() {
		return this.creationTime;
	}

}

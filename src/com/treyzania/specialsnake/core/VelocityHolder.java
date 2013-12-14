package com.treyzania.specialsnake.core;

public class VelocityHolder {

	public float xVel;
	public float yVel;
	
	public VelocityHolder(float xVel, float yVel) {
		this.xVel = xVel;
		this.yVel = yVel;
	}
	
	public VelocityHolder() {
		this(0, 0);
	}

	public void zero() {
		this.xVel = 0F;
		this.yVel = 0F;
	}
	
}

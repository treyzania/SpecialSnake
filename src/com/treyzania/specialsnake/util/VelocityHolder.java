package com.treyzania.specialsnake.util;

public class VelocityHolder {

	public float xVel;
	public float yVel;
	
	public VelocityHolder(float xVel, float yVel) {
		this.xVel = xVel;
		this.yVel = yVel;
	}
	
	public void zero() {
		this.xVel = 0F;
		this.yVel = 0F;
	}
	
}

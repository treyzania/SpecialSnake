package com.treyzania.specialsnake.core;

public interface IVelocity {

	/**
	 * Kilograms, the player weight is 100 per segment.
	 * 
	 * @return
	 */
	public float getMass();
	
	public void setXVelocity(float val);
	public float getXVelocity();
	
	public void setYVelocity(float val);
	public float getYVelocity();
	
}

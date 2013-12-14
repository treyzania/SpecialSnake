package com.treyzania.specialsnake.core;


public interface IModel {

	public Model getModel();
	
	public boolean hasPosition();
	public PointF getPosition();
	
	public boolean hasVelocity();
	public float getXVelocity();
	public float getYVelocity();
	
}

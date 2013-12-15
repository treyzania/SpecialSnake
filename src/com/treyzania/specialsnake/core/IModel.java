package com.treyzania.specialsnake.core;


public interface IModel {

	public Model getModel();
	
	public boolean hasLocation();
	public PointF getLocation();
	
	public boolean hasVelocity();
	public float getXVelocity();
	public float getYVelocity();
	
}

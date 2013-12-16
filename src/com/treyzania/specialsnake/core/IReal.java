package com.treyzania.specialsnake.core;

/**
 * Allows this object to be in a world.
 * 
 * @author Treyzania
 */
public interface IReal {

	public PointF getLocation();
	public void setLocation(PointF point);
	
	public long getCreationTime();
	
}

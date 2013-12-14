package com.treyzania.specialsnake.core;

import java.awt.Point;

public abstract class Entity implements IReal {

	public float x;
	public float y;
	
	public EnumEntityType entityType;
	
	public Entity() {
		
	}
	
	public void setPosition(Point p) {
		
		this.x = p.x;
		this.y = p.y;
		
	}
	
	public PointF getLocation() {
		
		return new PointF(x, y);
		
	}
	
	public void setLocation(PointF point) {
		
		this.x = point.x;
		this.y = point.y;
		
	}
	
}

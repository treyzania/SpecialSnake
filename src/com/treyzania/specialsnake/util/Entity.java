package com.treyzania.specialsnake.util;

import java.awt.Point;

public abstract class Entity {

	public float x;
	public float y;
	
	public Entity() {
		
	}
	
	public void setPosition(Point p) {
		
		this.x = p.x;
		this.y = p.y;
		
	}
	
}

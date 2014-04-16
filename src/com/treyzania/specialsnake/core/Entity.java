package com.treyzania.specialsnake.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Entity implements IReal, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3886060011217538663L;
	
	private final long creationTime;
	
	public World myWorld = null;
	public ArrayList<Controller> controllers = null;
	
	public float x = 0F;
	public float y = 0F;
	
	public EnumEntityType entityType;
	
	public Entity() {
		
		this.creationTime = System.currentTimeMillis();
		this.controllers = new ArrayList<Controller>();
		
	}
	
	@Override
	public long getCreationTime() {
		return this.creationTime;
	}

	public final boolean hasLocation() {
		return true;
	}
	
	public PointF getLocation() {
		return new PointF(x, y);
	}
	
	public void setLocation(PointF point) {
		this.x = point.x;
		this.y = point.y;
	}
	
	public void setLocation(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	public String getEntManifest() {
		return this.getClass().getSimpleName() + "@" + Long.toHexString(this.hashCode());
	}
	
}

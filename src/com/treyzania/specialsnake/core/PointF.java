package com.treyzania.specialsnake.core;

import java.awt.geom.Point2D;

/**
 * This class is basically just to make it so you don't have to type in "Point2D.Float" and "new Point2D.Float()" everywhere.
 * 
 * @author Treyzania
 */
public class PointF extends Point2D.Float {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2221280003682965107L;

	public PointF(float x, float y) {
		super(x, y);
	}
	
	public PointF(Point2D.Float pseudopoint) {
		this(pseudopoint.x, pseudopoint.y);
	}
	
}

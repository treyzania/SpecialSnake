package com.treyzania.specialsnake.core;

import java.awt.Rectangle;
import java.util.Random;

public class EntUtil {

	public static void placeEntityInRegion(Entity e, Rectangle r, Random rand) {
		e.setLocation(new PointF(r.x + rand.nextInt(r.width), r.y + rand.nextInt(r.height)));
	}
	
	public static void placeEntityInRegion(Entity e, Rectangle r) {
		placeEntityInRegion(e, r, new Random());
	}
	
}

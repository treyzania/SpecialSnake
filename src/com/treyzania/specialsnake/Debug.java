package com.treyzania.specialsnake;

import java.util.Random;

import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IReal;
import com.treyzania.specialsnake.core.IVelocity;
import com.treyzania.specialsnake.core.PointF;
import com.treyzania.specialsnake.core.World;
import com.treyzania.specialsnake.generics.EntityBug;

public class Debug {

	public static void populateEntities(World w) {
		
		SpecialSnake.log.info("Populating arraylist with entities...");
		
		Random r = new Random();
		
		long oldTime = System.currentTimeMillis();
		
		int max = 5;
		IReal[] irs = new IReal[max];
		for (int i = 0; i < max; i++) {
			
			Entity e = new EntityBug(false);
			e.setLocation(new PointF(1280 / 2, 720 / 2));
			((IVelocity) e).setXVelocity((r.nextFloat() * 20) - 10);
			((IVelocity) e).setYVelocity((r.nextFloat() * 20) - 10);
			
			irs[i] = e;
			
		}
		
		w.registerThings(irs);
		
		long doneTime = System.currentTimeMillis();
		
		SpecialSnake.log.info("Created & added " + max + " entities in " + (doneTime - oldTime) + " milliseconds.");
		
	}
	
}

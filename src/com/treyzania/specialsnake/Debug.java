package com.treyzania.specialsnake;

import java.util.Random;

import com.treyzania.specialsnake.core.IReal;
import com.treyzania.specialsnake.core.PointF;
import com.treyzania.specialsnake.core.World;
import com.treyzania.specialsnake.generics.EntityBall;

public class Debug {

	public static void populateEntities(World w) {
		
		SpecialSnake.log.info("Populating arraylist with entities...");
		
		Random r = new Random();
		
		long oldTime = System.currentTimeMillis();
		
		int max = 10000;
		IReal[] irs = new IReal[max];
		for (int i = 0; i < max; i++) {
			
			EntityBall eb = new EntityBall();
			eb.setLocation(new PointF(640, 360));
			eb.setXVelocity((r.nextFloat() * 20) - 10);
			eb.setYVelocity((r.nextFloat() * 20) - 10);
			
			irs[i] = eb;
			
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) { }
			
		}
		
		w.registerThings(irs);
		
		long doneTime = System.currentTimeMillis();
		
		SpecialSnake.log.info("Created & added " + max + " entities in " + (doneTime - oldTime) + " milliseconds.");
		
	}
	
}

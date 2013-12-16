package com.treyzania.specialsnake;

import java.awt.event.KeyEvent;
import java.util.Random;

import com.treyzania.specialsnake.controllers.ControllerVelocityScaler;
import com.treyzania.specialsnake.controllers.ControllerWASD;
import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.EntityLandmark;
import com.treyzania.specialsnake.core.IReal;
import com.treyzania.specialsnake.core.IVelocity;
import com.treyzania.specialsnake.core.PointF;
import com.treyzania.specialsnake.core.SnakeGame;
import com.treyzania.specialsnake.core.World;
import com.treyzania.specialsnake.generics.EntityBug;
import com.treyzania.specialsnake.generics.EntityPlayer;

public class Debug {

	public static void populateEntities(World w) {
		
		SpecialSnake.log.info("Populating world with entities...");
		
		Random r = new Random();
		
		long oldTime = System.currentTimeMillis();
		
		int max = 500;
		IReal[] irs = new IReal[max];
		for (int i = 0; i < max; i++) {
			
			Entity e = new EntityBug();
			e.setLocation(new PointF(1280 / 2, 720 / 2));
			((IVelocity) e).setXVelocity((r.nextFloat() * 20) - 10);
			((IVelocity) e).setYVelocity((r.nextFloat() * 20) - 10);
			
			irs[i] = e;
			
		}
		
		w.registerThings(irs);
		
		long doneTime = System.currentTimeMillis();
		
		SpecialSnake.log.info("Created & added " + max + " entities in " + (doneTime - oldTime) + " milliseconds.");
		
		Entity lm = new EntityLandmark(1280 / 2, 720 / 2);
		w.registerThing(lm);
		GameRegistry.getGameFromWorld(w).registerSpecialEntity("center", lm);
		
	}
	
	public static void initalizeGame(SnakeGame game) {
		
		SpecialSnake.log.info("Intializing game...");
		
		EntityPlayer ep = new EntityPlayer();
		ControllerWASD ctrlWasd = new ControllerWASD(ep, 5F);
		ControllerVelocityScaler ctrlVS = new ControllerVelocityScaler(ep, 0.25F, KeyEvent.VK_SHIFT);
		
		game.addController("player_ctrl", ctrlWasd);
		game.addController("player_slowdown", ctrlVS);
		
		ep.setLocation(new PointF(1280 / 2, 720 / 2 + 25));
		
		game.theWorld.registerThing(ep);
		game.registerSpecialEntity("player", ep);
		
		SpecialSnake.log.info("Done initializing game!");
		
	}
	
}

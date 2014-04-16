package com.treyzania.specialsnake;

import java.awt.event.KeyEvent;
import java.util.Random;

import com.treyzania.specialsnake.controllers.ControllerReset;
import com.treyzania.specialsnake.controllers.ControllerSetExistanceValues;
import com.treyzania.specialsnake.controllers.ControllerSnakeWASD;
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
import com.treyzania.specialsnake.generics.EntityFood;
import com.treyzania.specialsnake.generics.EntityPlayer;

public class Debug {

	public static void populateEntities(World w) {
		
		SpecialSnake.log.info("Populating world with entities...");
		
		Random r = new Random();
		
		int width = 1280;
		int height = 720;
		
		long oldTime = System.currentTimeMillis();
		
		int max = 20;
		IReal[] irs = new IReal[max];
		for (int i = 0; i < max; i++) {
			
			Entity e = new EntityBug();
			e.setLocation(new PointF(width / 2, height / 2));
			((IVelocity) e).setXVelocity((r.nextFloat() * 20) - 10);
			((IVelocity) e).setYVelocity((r.nextFloat() * 20) - 10);
			
			irs[i] = e;
			
		}
		
		int max2 = 500;
		IReal[] irs2 = new IReal[max2];
		for (int i = 0; i < max2; i++) {
			
			Entity food = new EntityFood();
			food.setLocation(new PointF(r.nextInt(width), r.nextInt(height)));
			irs2[i] = food;
			
		}
		
		w.registerThings(irs);
		w.registerThings(irs2);
		
		long doneTime = System.currentTimeMillis();
		
		SpecialSnake.log.info("Created & added " + max + " entities in " + (doneTime - oldTime) + " milliseconds.");
		
		Entity lm = new EntityLandmark(width / 2, height / 2);
		w.registerThing(lm);
		GameRegistry.getGameFromWorld(w).registerSpecialEntity("center", lm);
		
	}
	
	public static void initalizeGame(SnakeGame game) {
		
		SpecialSnake.log.info("Intializing game...");
		
		PointF center = new PointF(1280 / 2, 720 / 2 + 25);
		
		EntityPlayer ep = new EntityPlayer();
		ControllerSnakeWASD ctrlSnakeWasd = new ControllerSnakeWASD(ep, 10);
		ControllerWASD ctrlWasd = new ControllerWASD(ep, 0.75F);
		ControllerVelocityScaler ctrlVS = new ControllerVelocityScaler(ep, 0.75F, KeyEvent.VK_F);
		ControllerReset ctrlRst = new ControllerReset(ep, KeyEvent.VK_SPACE, center);
		//ControllerSetLocation ctrlSL = new ControllerSetLocation(ep, KeyEvent.VK_E);
		ControllerSetExistanceValues ctrlSEV = new ControllerSetExistanceValues(ep, KeyEvent.VK_E);
		
		game.addController("player_ctrl-snake", ctrlSnakeWasd);
		game.addController("player_ctrl-free", ctrlWasd);
		game.addController("player_slowdown", ctrlVS);
		game.addController("player_reset", ctrlRst);
		game.addController("player_setmov", ctrlSEV);
		
		ep.setLocation(center);
		
		game.theWorld.registerThing(ep);
		game.registerSpecialEntity("player", ep);
		
		SpecialSnake.log.info("Done initializing game!");
		
	}
	
}

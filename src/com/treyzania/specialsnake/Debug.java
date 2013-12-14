package com.treyzania.specialsnake;

import java.util.ArrayList;

import com.treyzania.specialsnake.controllers.ControllerWASD;
import com.treyzania.specialsnake.core.IReal;
import com.treyzania.specialsnake.core.PointF;
import com.treyzania.specialsnake.generics.EntityBall;
import com.treyzania.specialsnake.generics.EntityPlayer;

public class Debug {

	public static void populateEntities(ArrayList<IReal> al) {
		
		SpecialSnake.log.info("Populating arraylist with entities...");
		
		EntityPlayer ep = new EntityPlayer();
		ControllerWASD ctrlWasd = new ControllerWASD(ep);
		
		//GameRegistry.getGame("main").mainRenderer.registerHandler("playerWASD", ctrlWasd);
		
		al.add(ep);
		
		for (int i = 0; i < 10; i++) {
			
			EntityBall eb = new EntityBall();
			eb.setLocation(new PointF(20, 10 * i + 100));
			eb.setXVelocity(2 * i + 1);
			
			al.add(eb);
			
		}
		
	}
	
}

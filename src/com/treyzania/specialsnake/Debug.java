package com.treyzania.specialsnake;

import java.util.ArrayList;

import com.treyzania.specialsnake.controllers.ControllerWASD;
import com.treyzania.specialsnake.core.IReal;
import com.treyzania.specialsnake.generics.EntityPlayer;

public class Debug {

	public static void populateEntities(ArrayList<IReal> al) {
		
		SpecialSnake.log.info("Populating arraylist with entities...");
		
		EntityPlayer ep = new EntityPlayer();
		ControllerWASD ctrlWasd = new ControllerWASD(ep);
		
		GameRegistry.getGame("main").mainRenderer.registerHandler("playerWASD", ctrlWasd);
		
		al.add(ep);
		
	}
	
}

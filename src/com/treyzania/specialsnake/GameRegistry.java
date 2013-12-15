package com.treyzania.specialsnake;

import java.util.HashMap;
import java.util.Set;

import com.treyzania.specialsnake.core.SnakeGame;
import com.treyzania.specialsnake.core.World;

public class GameRegistry {

	private static HashMap<String, SnakeGame> games;
	
	public static void registerGame(String name, SnakeGame game) {
		games.put(name, game);
	}
	
	public static SnakeGame getGame(String name) {
		return games.get(name);
	}
	
	public static SnakeGame getGameFromWorld(World w) {
		
		Set<String> gNames = games.keySet();
		
		SnakeGame game = null;
		
		for (String s : gNames) {
			
			SnakeGame g = games.get(s);
			
			if (g.theWorld == w) {
				
				game = g;
				break;
				
			}
			
		}
		
		return game;
		
	}
	
	static {
		
		games = new HashMap<String, SnakeGame>();
		
	}
	
}

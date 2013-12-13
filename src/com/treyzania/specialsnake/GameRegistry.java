package com.treyzania.specialsnake;

import java.util.HashMap;

public class GameRegistry {

	private static HashMap<String, SnakeGame> games;
	
	public void registerGame(String name, SnakeGame game) {
		games.put(name, game);
	}
	
	public SnakeGame getGame(String name) {
		return games.get(name);
	}
	
	static {
		
		games = new HashMap<String, SnakeGame>();
		
	}
	
}

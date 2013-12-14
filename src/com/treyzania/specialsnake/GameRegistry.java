package com.treyzania.specialsnake;

import java.util.HashMap;

import com.treyzania.specialsnake.core.SnakeGame;

public class GameRegistry {

	private static HashMap<String, SnakeGame> games;
	
	public static void registerGame(String name, SnakeGame game) {
		games.put(name, game);
	}
	
	public static SnakeGame getGame(String name) {
		return games.get(name);
	}
	
	static {
		
		games = new HashMap<String, SnakeGame>();
		
	}
	
}

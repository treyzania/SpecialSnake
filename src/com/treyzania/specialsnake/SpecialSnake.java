package com.treyzania.specialsnake;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import com.treyzania.specialsnake.core.SSPanel;
import com.treyzania.specialsnake.core.SnakeGame;
import com.treyzania.specialsnake.core.World;

public class SpecialSnake {

	public static Logger log;
	public static Handler consoleHandler;
	public static Handler fileHandler;
	
	public static SnakeGame theGame;
	public static JFrame frame;
	
	public static void main(String[] args) {
		
		// Steal a bit of code from ZaniDL...
		log = Logger.getLogger("SnakeGame");
		setupLogs();
		//logTest();
		
		frame = new JFrame();
		frame.setSize(1280, 720);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.setEnabled(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		theGame = new SnakeGame();
		GameRegistry.registerGame("main", theGame);
		theGame.theWorld = new World(50, 50);
		theGame.mainRenderer = new SSPanel(theGame.theWorld);
		theGame.mainWin = frame;
		
		frame.add(theGame.mainRenderer);
		frame.setVisible(true);
		
		Debug.initalizeGame(theGame);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) { }
		
		Debug.populateEntities(theGame.theWorld);
		
	}
	
	private static void setupLogs() {
		
		log.setUseParentHandlers(false); // Hehehehehhhh...
		
		// Make the logs folder
		File logsfolder = new File("logs");
		if (!logsfolder.exists()) {
			logsfolder.mkdirs();
		}
		
		consoleHandler = new ConsoleHandler();
		try {
			fileHandler = new FileHandler("logs/SpecialSnake-" + Long.toString(System.currentTimeMillis()) + ".log");
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		
		log.setLevel(Level.ALL);
		consoleHandler.setLevel(Level.ALL);
		fileHandler.setLevel(Level.ALL);
		
		consoleHandler.setFormatter(new LogFormatter());
		fileHandler.setFormatter(new LogFormatter());
		
		log.addHandler(consoleHandler);
		log.addHandler(fileHandler);
		
	}
	
	@SuppressWarnings("unused")
	private static void logTest() {
		
		String logTestPrefix = "{LOGTEST}";
		log.info(logTestPrefix + "Beginning log test...");
		log.severe(logTestPrefix + "Severe");
		log.warning(logTestPrefix + "Warning");
		log.info(logTestPrefix + "Info");
		log.fine(logTestPrefix + "Fine");
		log.finer(logTestPrefix + "Finer");
		log.finest(logTestPrefix + "Finest");
		log.info(logTestPrefix + "Log test finished.");
		
	}
	
}

package com.treyzania.specialsnake.core;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class SSPanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7139656492559642044L;

	public HashMap<String, Controller> controllers;
	public World world; 
	
	public CycleMeter renderHandler;
	
	public Thread repainter;
	
	public SSPanel(World world) {
		
		this.world = world;
		this.controllers = new HashMap<String, Controller>();
		
		this.renderHandler = new CycleMeter(false);
		
		this.repainter = new Thread(this, "SSPanel-Repainter");
		repainter.start();
		
	}
	
	public void registerHandler(String key, Controller controller) {
		
		controllers.put(key, controller);
		
		if (controller instanceof KeyListener) {
			this.addKeyListener((KeyListener) controller);
		}
		
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		
		// Render the models of all the things in the world with models.
		ArrayList<IReal> things = world.constituents;
		for (IReal ir : things) {
			
			if (ir instanceof IModel) {
				
				IModel im = (IModel) ir;
				im.getModel().draw(g);
				
			}
			
		}
		
		String fpsText = "FPS: " + renderHandler.getUpdatesPerSecond_Fast() + " (" + renderHandler.getLatency() + " ms)";
		g.drawString(fpsText, 20, 20);
		
		Entity e = (Entity) world.constituents.get(0);
		IVelocity iv = (IVelocity) e;
		String locText = "X: " + e.x + ", Y: " + e.y;
		String velText = "XVel : " + iv.getXVelocity() + ", YVel: " + iv.getYVelocity();
		g.drawString(locText, 20, 40);
		g.drawString(velText, 20, 55);
		
		//String wuText = "UVs: " + world.data[World.WorldCalcValues.UVs] + ", UPFVs: " + world.data[World.WorldCalcValues.UPFVs] + ", TEs: " + world.data[World.WorldCalcValues.TEs];
		//g.drawString(wuText, 20, 70);
		
		this.renderHandler.updateTime();
		
	}

	@Override
	public void run() {
		
		while (true) {
			
			this.repaint();
			
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}

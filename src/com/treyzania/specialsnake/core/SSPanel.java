package com.treyzania.specialsnake.core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.treyzania.specialsnake.GameRegistry;

public class SSPanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7139656492559642044L;
	
	public World world; 
	
	public CycleMeter renderHandler;
	
	public Thread repainter;
	public boolean repainting;
	
	public SSPanel(World world) {
		
		this.world = world;
		
		this.renderHandler = new CycleMeter(false);
		
		this.repainter = new Thread(this, "SSPanel-Repainter");
		repainter.start();
		
		this.setFocusable(true);
		
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		
		// Render lines of dooooooom!
		for (int i = 0; i < 20; i++) {
			g.setColor(Color.GRAY);
			g.drawLine(0, i * 50, 1280, i * 50);
		}
		
		// Render the models of all the things in the world with models.
		ArrayList<Model> modelsToRender = new ArrayList<Model>();
		synchronized (world.constituents) {
			// First, isolate the models themselves.
			for (IReal ir : world.constituents) {
				if (ir instanceof IModel) {
					g.setColor(Color.BLACK);
					modelsToRender.add(((IModel) ir).getModel());
				}
			}
		}
		for (Model m : modelsToRender) {
			// Then actually render the models.
			m.draw(g);
			g.setColor(Color.BLACK);
		}
		
		
		
		g.drawImage(world.updateData, 5, 80, world.updateData.getWidth(), world.updateData.getHeight(), null);
		g.drawRect(5, 80, world.updateData.getWidth(), world.updateData.getHeight());
		
		g.setColor(Color.WHITE);
		g.fillRect(5, 5, 220, 60);
		g.setColor(Color.BLACK);
		g.drawRect(5, 5, 220, 60);
		
		String fpsText = "FPS: " + renderHandler.getUpdatesPerSecond_Fast() + " (" + renderHandler.getLatency() + " ms)";
		g.drawString(fpsText, 20, 20);
		
		Entity e = GameRegistry.getGameFromWorld(this.world).getSpecialEntity("player");
		if (e != null) {
			IVelocity iv = (IVelocity) e;
			String locText = "X: " + e.x + ", Y: " + e.y;
			String velText = "XVel : " + iv.getXVelocity() + ", YVel: " + iv.getYVelocity();
			g.drawString(locText, 20, 40);
			g.drawString(velText, 20, 55);
		}
		
		try {
			Thread.sleep(0);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		
		this.renderHandler.updateTime();
		
	}

	@Override
	public void run() {
		
		while (true) {
			
			if (this.repainting) this.repaint();
			
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}

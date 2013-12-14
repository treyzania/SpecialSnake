package com.treyzania.specialsnake.core;

import java.util.ArrayList;
import com.treyzania.specialsnake.GameRegistry;

public class World {

	public final int width;
	public final int height;
	
	public ArrayList<IReal> constituents;
	
	public EntityUpdaterThread tickThread;
	
	public float envWindResistanceFactor = 0.99F;
	public float envMovementFrictionFactor = 0.99F;
	public float envMinimumNetVelocity = 0.01F;
	
	public World(int w, int h) {
		
		this.width = w;
		this.height = h;
		
		this.tickThread = new EntityUpdaterThread();
		
		this.constituents = new ArrayList<IReal>();
		
	}
	
	public float getTPS() {
		return tickThread.cm.getUpdatesPerSecond_Fast();
	}
	
	private class EntityUpdaterThread implements Runnable {
		
		public CycleMeter cm;
		private Thread thread;
		
		{
			this.cm = new CycleMeter(false);
			this.thread = new Thread();
			
			thread.start();
		}
		
		@Override
		public void run() {
			
			while (true) {
				
				boolean doUpdate = !GameRegistry.getGame("main").isPaused;
				if (doUpdate) {
					updateAllEntities();
				}
				
				cm.updateTime();
				
				// Wait until a full tick time is completed.
				boolean blocking = true;
				while (blocking) {
					if (cm.getLatency() > 50F) blocking = false;
				}
				
			}
			
		}
		
		private void updateAllEntities() {
			
			for (IReal e : constituents) {
				
				// Update object velocity, then its position.
				if (e instanceof IVelocity) {
					
					this.iv_updateVelocities(e);
					this.iv_updatePositionsFromVelocity(e);
					
				}
				
				// Do entity behavior ticking.
				if (e instanceof ITickable) {
					
					this.it_tickEntities(e);
					
				}
				
			}
			
		}
		
		private void iv_updateVelocities(IReal e) {
			
			IVelocity eiv = (IVelocity) e;
			
			float mass = eiv.getMass();
			float xVel = eiv.getXVelocity();
			float yVel = eiv.getYVelocity();
			
			float newXVel = 0; // If there's a problem with the code, the object will just stop all together.
			float newYVel = 0;
			
			// Reset if the velocity is too low, or do logic.
			if (xVel + yVel < envMinimumNetVelocity) {
				
				// Reset to save CUP time.
				newXVel = 0F;
				newYVel = 0F;
				
			} else {
				
				// Do the actual calculations.
				float mod_wind = (mass / envWindResistanceFactor) / 1000; // Let's hope this is good enough. (A.k.a. "modulation (by) wind")
				float tmod_vel = mod_wind * envMovementFrictionFactor; // A.k.a. "total modulation (of) velocity"
				
				newXVel = xVel * tmod_vel;
				newYVel = yVel * tmod_vel;
				
			}
			
			// Put the new values back into the object.
			eiv.setXVelocity(newXVel);
			eiv.setYVelocity(newYVel);
			
		}
		
		private void iv_updatePositionsFromVelocity(IReal e) {
			
			IVelocity eiv = (IVelocity) e;
			
			float x = e.getLocation().x;
			float y = e.getLocation().y;
			float xVel = eiv.getXVelocity();
			float yVel = eiv.getYVelocity();
			float nx = 0F;
			float ny = 0F;
			
			nx = x + xVel;
			ny = y + yVel;
			
			e.setLocation(new PointF(nx, ny));
			
		}
		
		private void it_tickEntities(IReal e) {
			
			ITickable eit = (ITickable) e;
			if (eit.doTick()) eit.tick();	
			
		}
		
	}
	
}

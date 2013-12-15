package com.treyzania.specialsnake.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.treyzania.specialsnake.GameRegistry;
import com.treyzania.specialsnake.entbehavior.Behavior;

public class World {

	public BufferedImage updateData;
	
	public final int width;
	public final int height;
	
	public ArrayList<IReal> constituents;
	
	public EntityUpdaterThread tickThread;
	
	public float envWindResistanceFactor = 1F;
	public float envMovementFrictionFactor = 0.98F;
	public float envMinimumNetVelocity = 0.03F;
	
	public World(int w, int h) {
		
		this.width = w;
		this.height = h;
		
		this.updateData = new BufferedImage(150, 100, BufferedImage.TYPE_INT_ARGB);
		
		this.constituents = new ArrayList<IReal>();
		
		this.tickThread = new EntityUpdaterThread();
		
	}
	
	public float getTPS() {
		return tickThread.cm.getUpdatesPerSecond_Fast();
	}
	
	public void registerThing(IReal ireal) {
		
		SSPanel ssp = GameRegistry.getGame("main").mainRenderer;
		
		ssp.repainting = false;
		
		synchronized (this.constituents) {
			
			this.constituents.add(ireal);
			
			if (ireal instanceof Entity) {
				((Entity) ireal).myWorld = this;
			}
			
		}
		
		ssp.repainting = true;
		
	}
	
	public void registerThings(IReal[] ira) {
		
		SSPanel ssp = GameRegistry.getGame("main").mainRenderer;
		
		ssp.repainting = false;
		
		synchronized (this.constituents) {
			
			for (IReal ireal : ira) {
				
				this.constituents.add(ireal);
				
				if (ireal instanceof Entity) {
					((Entity) ireal).myWorld = this;
				}
				
			}
			
		}
		
		ssp.repainting = true;
		
	}
	
	public Object[] getInstancesOf(Class<? extends Object> clazz) {
		
		SSPanel ssp = GameRegistry.getGame("main").mainRenderer;
		ArrayList<IReal> things = new ArrayList<IReal>();
		
		ssp.repainting = false;
		
		synchronized (this.constituents) {
			
			for (IReal ireal : constituents) {
				
				// If the IReal is an isntance of the "clazz".
				if (ireal.getClass().equals(clazz)) {
					things.add(ireal);
				}
				
			}
			
		}
		
		ssp.repainting = true;
		
		return (Object[]) things.toArray();
		
	}
	
	private class EntityUpdaterThread implements Runnable {
	
		public CycleMeter cm;
		private Thread thread;
		
		private int ivelocities;
		private int iticks;
		private int iticksDone;
		private int ibehaviors;
		
		public EntityUpdaterThread() {
			
			this.cm = new CycleMeter(false);
			this.thread = new Thread(this);
			
			thread.start();
			
		}
		
		@Override
		public void run() {
			
			while (true) {
				
				boolean doUpdate = !GameRegistry.getGame("main").isPaused;
				if (doUpdate) {
					updateAllEntities();
				}
				
				synchronized (updateData) {
					
					Graphics g = updateData.getGraphics();
					
					g.fillRect(0, 0, updateData.getWidth(), updateData.getHeight());
					g.setColor(Color.BLACK);
					
					String text1 = "PS/s: " + getTPS() + " (" + cm.getLatency() + " ms)"; // Physics update speed.
					String text2 = "VelUD #: " + this.ivelocities;
					String text3 = "Ticks: " + this.iticksDone + "/" + this.iticks;
					String text5 = "Behs.: " + this.ibehaviors; // No number 4, simpler.
					
					g.drawString(text1, 5, 16);
					g.drawString(text2, 5, 32);
					g.drawString(text3, 5, 48);
					g.drawString("LUDT: " + cm.lastTicking, 5, 64); // #4 here.
					g.drawString(text5, 5, 80);
					
				}
				
				this.ivelocities = 0;
				this.iticks = 0;
				this.iticksDone = 0;
				this.ibehaviors = 0;
				
				// Wait a little while.
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) { }
				
				// This is important for statistics.
				cm.updateTime();
				
			}
			
		}
		
		private void updateAllEntities() {
			
			synchronized (constituents) {
				
				for (IReal e : constituents) {
					
					// Update object velocity, then its position.
					if (e instanceof IVelocity) {
						
						this.iv_updateVelocities(e);
						this.iv_updatePositionsFromVelocity(e);
						
						this.ivelocities++;
						
					}
					
					// Do entity behavior ticking.
					if (e instanceof ITickable) {
						
						this.it_tickEntities(e);
						
						this.iticks++;
						
					}
					
					if (e instanceof IBehavior) {
						
						this.ib_callBehaviors(e);
						
						this.ibehaviors++;
						
					}
					
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
			if (Math.abs(xVel) + Math.abs(yVel) < envMinimumNetVelocity) {
				
				// Reset to save CPU time.
				newXVel = 0F;
				newYVel = 0F;
				
			} else {
				
				// Do the actual calculations.
				float mod_wind = 1 - (envWindResistanceFactor / mass); // Let's hope this is good enough. (A.k.a. "modulation (by) wind")
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
			if (eit.doTick()) {
				eit.tick();
				this.iticksDone++;
			}
			
		}
		
		private void ib_callBehaviors(IReal e) {
			
			IBehavior ib = (IBehavior) e;
			Behavior[] behaviors = ib.getBehaviors();
			
			for (Behavior b : behaviors) {
				b.doAi();
			}
			
		}
		
	}
	
}

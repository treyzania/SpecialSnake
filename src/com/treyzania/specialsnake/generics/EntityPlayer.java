package com.treyzania.specialsnake.generics;

import java.awt.Rectangle;
import com.treyzania.specialsnake.GameRegistry;
import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IModel;
import com.treyzania.specialsnake.core.ITickable;
import com.treyzania.specialsnake.core.IVelocity;
import com.treyzania.specialsnake.core.Model;
import com.treyzania.specialsnake.core.PointF;
import com.treyzania.specialsnake.core.VelocityHolder;
import com.treyzania.specialsnake.core.World;

public class EntityPlayer extends Entity implements IVelocity, IModel, ITickable, Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2400404211468721109L;
	
	private VelocityHolder velocity;
	private Model model;
	
	private Thread positionResetter;
	
	public EntityPlayer() {
		
		this.velocity = new VelocityHolder();
		this.model = new ModelPlayer(this);
		
		this.positionResetter = new Thread(this, this.getClass().getSimpleName() + "@" + Long.toHexString(this.hashCode()) + "-PositionResetThread");
		positionResetter.start();
		
	}
	
	@Override
	public boolean doTick() {
		return true;
	}

	@Override
	public void tick() {
		
		World w = GameRegistry.getGame("main").theWorld;
		Object[] objs = w.getInstancesOf(EntityBug.class);
		
		double totalXFactor = 0F;
		double totalYFactor = 0F;
		
		//System.out.println("Bug #: " + objs.length);
		
		double bugFactor = 0.5F;
		
		for (Object o : objs) {
			
			EntityBug b = (EntityBug) o;
			
			double diffX = b.getLocation().x - this.x;
			double diffY = b.getLocation().y - this.y;
			
			double xFactor = ( 1 / diffX ) * bugFactor;
			double yFactor = ( 1 / diffY ) * bugFactor;
			
			totalXFactor += xFactor;
			totalYFactor += yFactor;
			
		}
		
		float velScale = 0.4F;
		
		float adjXVel = (float) (this.getXVelocity() + ((totalXFactor * -1) / objs.length));
		float adjYVel = (float) (this.getYVelocity() + ((totalYFactor * -1) / objs.length));
		
		if (adjXVel > 10) adjXVel = 10F;
		if (adjXVel < -10) adjXVel = -10F;
		if (adjYVel > 10) adjYVel = 10F;
		if (adjYVel < -10) adjYVel = -10F;
		
		this.setXVelocity(adjXVel * 2);
		this.setYVelocity(adjYVel * 2);
		
	}

	@Override
	public Model getModel() {
		return this.model;
	}
	
	@Override
	public PointF getLocation() {
		return new PointF(this.x, this.y);
	}

	@Override
	public boolean hasVelocity() {
		return true;
	}
	
	@Override
	public float getMass() {
		return 100;
	}

	@Override
	public void setXVelocity(float val) {
		this.velocity.xVel = val;
	}

	@Override
	public float getXVelocity() {
		return this.velocity.xVel;
	}

	@Override
	public void setYVelocity(float val) {
		this.velocity.yVel = val;
	}

	@Override
	public float getYVelocity() {
		return this.velocity.yVel;
	}

	@Override
	public void run() {
		
		while (true) {
			
			Rectangle r = GameRegistry.getGame("main").mainRenderer.getBounds();
			System.out.println("X: " + r.x + ", Y: " + r.y + ", W: " + r.width + ", H: " + r.height);
			
			if (System.currentTimeMillis() % 10000 == 0 || !r.contains(this.getLocation())) {
				
				this.setLocation(new PointF(1280 / 2 + 1, 720 / 2 + 1));
				
				this.setXVelocity(0);
				this.setYVelocity(0);
				
			}
			
		}
		
	}

}

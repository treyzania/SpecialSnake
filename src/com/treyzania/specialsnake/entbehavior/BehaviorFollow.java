package com.treyzania.specialsnake.entbehavior;

import java.util.Random;

import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IVelocity;

public class BehaviorFollow extends Behavior implements IRandanomity {

	private float randanomity;
	
	public Class<? extends Entity> followedObjectType;
	public double okDistance;
	public float distDivisor;
	public float slowdownRate = 0.99F;
	
	public BehaviorFollow(Entity ent, Class<? extends Entity> followedEntType, double okDistance, float distDivisor) {
		
		super(ent);
		
		this.followedObjectType = followedEntType;
		this.okDistance = okDistance;
		this.distDivisor = distDivisor;
		
	}

	@Override
	public void setRandanomity(float rand) {
		this.randanomity = rand;
	}

	@Override
	public float getRandanomity() {
		return this.randanomity;
	}
	
	@Override
	public void doAi() {
		
		super.doAi();
		
		IVelocity eiv = (IVelocity) ent;
		
		// Retrieve the old velocity values.
		float oldXVel = eiv.getXVelocity();
		float oldYVel = eiv.getYVelocity();
			
		float adjXVel = 0F;
		float adjYVel = 0F;
		
		Object[] objs = ent.myWorld.getInstancesOf(this.followedObjectType);
		for (Object o : objs) {
			
			Entity e = (Entity) o;
			
			double dist = e.getLocation().distance(e.getLocation());
			
			if (dist > okDistance) {
				
				// Calculate differences in position.
				float diffX = e.x - ent.x;
				float diffY = e.y - ent.y;
				
				// Calculate the new ones.
				adjXVel = diffX / this.distDivisor;
				adjYVel = diffY / this.distDivisor;
				
			} else {
				
				adjXVel = oldXVel * this.slowdownRate;
				adjYVel = oldYVel * this.slowdownRate;
				
			}
			
		}
		
		if (this.randanomity != 0) {
			
			// Add a little bit of randanomity to the values, for realism.
			Random r = new Random();
			adjXVel += ((r.nextFloat() * this.randanomity) - (this.randanomity / 2)) / objs.length;
			adjYVel += ((r.nextFloat() * this.randanomity) - (this.randanomity / 2)) / objs.length;
			
		}
		
		// Finally change velocity.
		eiv.setXVelocity(adjXVel);
		eiv.setYVelocity(adjYVel);
		
	}
	
}

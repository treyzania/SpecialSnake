package com.treyzania.specialsnake.entbehavior;

import java.util.Random;

import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IVelocity;

public class BehaviorFollow extends Behavior implements IRandanomity {

	private float randanomity;
	
	public Entity followed;
	public double okDistance;
	public float distDivisor;
	public float slowdownRate = 0.99F;
	
	public BehaviorFollow(Entity ent, Entity followed, double okDistance, float distDivisor) {
		
		super(ent);
		
		this.followed = followed;
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
		
		double dist = followed.getLocation().distance(ent.getLocation());
		
		if (dist > okDistance) {
			
			// Calculate differences in position.
			float diffX = followed.x - ent.x;
			float diffY = followed.y - ent.y;
			
			// Calculate the new ones.
			adjXVel = diffX / this.distDivisor;
			adjYVel = diffY / this.distDivisor;
			
		} else {
			
			adjXVel = oldXVel * this.slowdownRate;
			adjYVel = oldYVel * this.slowdownRate;
			
		}
		
		
		
		if (this.randanomity != 0) {
			
			// Add a little bit of randanomity to the values, for realism.
			Random r = new Random();
			adjXVel += (r.nextFloat() * this.randanomity) - (this.randanomity / 2);
			adjYVel += (r.nextFloat() * this.randanomity) - (this.randanomity / 2);
			
		}
		
		// Finally change velocity.
		eiv.setXVelocity(adjXVel);
		eiv.setYVelocity(adjYVel);
		
	}
	
}

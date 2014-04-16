package com.treyzania.specialsnake.generics;

import com.treyzania.specialsnake.GameRegistry;
import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IBehavior;
import com.treyzania.specialsnake.core.IModel;
import com.treyzania.specialsnake.core.IVelocity;
import com.treyzania.specialsnake.core.Model;
import com.treyzania.specialsnake.core.VelocityHolder;
import com.treyzania.specialsnake.entbehavior.Behavior;
import com.treyzania.specialsnake.entbehavior.BehaviorFollow;

public class EntityBug extends Entity implements IVelocity, IModel, IBehavior {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2905001986085281921L;
	
	private VelocityHolder velocity;
	private Model model;
	
	public EntityBug() {
		
		this.velocity = new VelocityHolder();
		this.model = new ModelBug(this);
		
	}
	
	@Override
	public Model getModel() {
		return this.model;
	}
	
	@Override
	public boolean hasVelocity() {
		return true;
	}
	
	@Override
	public float getMass() {
		return 10F;
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
	public Behavior[] getBehaviors() {
		
		BehaviorFollow bf = new BehaviorFollow(this, GameRegistry.getGame("main").getSpecialEntity("player"), 150D, 30F);
		
		bf.setRandanomity(3F);
		
		return new Behavior[] {bf};
		
	}

	@Override
	public void addBehavior(Behavior beh) {
		// Nothing.
	}
	
}

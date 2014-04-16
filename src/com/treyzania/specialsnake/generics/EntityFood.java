package com.treyzania.specialsnake.generics;

import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IModel;
import com.treyzania.specialsnake.core.Model;

public class EntityFood extends Entity implements IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2833541907953970814L;

	public EntityFood() {
		
	}

	@Override
	public Model getModel() {
		return new ModelFood(this);
	}

	@Override
	public boolean hasVelocity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getXVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getYVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

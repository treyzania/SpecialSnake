package com.treyzania.specialsnake.core;

public interface ITickable {

	/**
	 * Check to see if the entity needs to be ticked.
	 * 
	 * @return if the entity needs to be ticked
	 */
	public boolean doTick();
	
	/**
	 * Actually perform the tick.  Only called if doTick() returns true.
	 */
	public void tick();
	
}

package com.treyzania.specialsnake.core;

import com.treyzania.specialsnake.entbehavior.Behavior;

public interface IBehavior {

	public Behavior[] getBehaviors();
	public void addBehavior(Behavior beh);
	
}

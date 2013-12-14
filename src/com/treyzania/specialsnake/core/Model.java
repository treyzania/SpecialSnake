package com.treyzania.specialsnake.core;

import java.awt.Graphics;

public class Model {

	public IModel owner;
	
	public Model(IModel im) {
		
		this.owner = im;
		
	}
	
	public void draw(Graphics g) {
		
		// In this case, do nothing.
		
	}
	
}

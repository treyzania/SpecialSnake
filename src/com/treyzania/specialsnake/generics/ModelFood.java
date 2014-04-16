package com.treyzania.specialsnake.generics;

import java.awt.Color;
import java.awt.Graphics;

import com.treyzania.specialsnake.core.Entity;
import com.treyzania.specialsnake.core.IModel;
import com.treyzania.specialsnake.core.Model;

public class ModelFood extends Model {

	public ModelFood(IModel im) {
		super(im);
	}

	@Override
	public void draw(Graphics g) {
		
		super.draw(g);
		
		Entity myEnt = (Entity) this.owner;
		int x = (int) myEnt.x;
		int y = (int) myEnt.y;
		
		g.setColor(Color.GREEN);
		g.fillOval(x - 4, y - 4, 8, 8);
		
	}
	
}

package com.treyzania.specialsnake.generics;

import java.awt.Graphics;

import com.treyzania.specialsnake.core.IModel;
import com.treyzania.specialsnake.core.Model;

public class ModelPlayer extends Model {

	public ModelPlayer(IModel im) {
		
		super(im);
		
	}

	@Override
	public void draw(Graphics g) {
		
		super.draw(g);
		
		g.drawOval((int) this.owner.getPosition().x - 2, (int) this.owner.getPosition().y - 2, 4, 4);
		
	}

}

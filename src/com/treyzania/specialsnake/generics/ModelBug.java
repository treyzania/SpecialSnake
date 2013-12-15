package com.treyzania.specialsnake.generics;

import java.awt.Graphics;

import com.treyzania.specialsnake.core.IModel;
import com.treyzania.specialsnake.core.Model;
import com.treyzania.specialsnake.core.PointF;

public class ModelBug extends Model {

	public ModelBug(IModel im) {
		super(im);
	}

	@Override
	public void draw(Graphics g) {
		
		super.draw(g);
		
		PointF p = owner.getLocation();
		
		g.drawLine((int) p.x - 2, (int) p.y - 2, (int) p.x, (int) p.y);
		g.drawLine((int) p.x + 2, (int) p.y - 2, (int) p.x, (int) p.y);
		
	}
	
}

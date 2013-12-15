package com.treyzania.specialsnake.generics;

import java.awt.Graphics;

import com.treyzania.specialsnake.core.IModel;
import com.treyzania.specialsnake.core.Model;
import com.treyzania.specialsnake.core.PointF;

public class ModelBall extends Model {

	public ModelBall(IModel im) {
		super(im);
	}

	@Override
	public void draw(Graphics g) {
		
		super.draw(g);
		
		PointF p = owner.getLocation();
		
		g.drawLine((int) p.x - 2, (int) p.y - 2, (int) p.x + 2, (int) p.y + 2);
		g.drawLine((int) p.x - 2, (int) p.y + 2, (int) p.x + 2, (int) p.y - 2);
		g.drawOval((int) p.x - 4, (int) p.y - 4, 8, 8);
		
		//String velText = "v=" + (Math.abs(owner.getXVelocity()) + Math.abs(owner.getYVelocity()));
		//g.drawString(velText, (int) p.x + 4, (int) p.y - 4);
		
	}

}

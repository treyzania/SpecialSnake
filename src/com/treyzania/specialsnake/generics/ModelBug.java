package com.treyzania.specialsnake.generics;

import java.awt.Graphics;
import java.util.Random;

import com.treyzania.specialsnake.core.IModel;
import com.treyzania.specialsnake.core.Model;
import com.treyzania.specialsnake.core.PointF;

public class ModelBug extends Model {

	int renderCount = 0;
	
	public ModelBug(IModel im) {
		super(im);
	}

	@Override
	public void draw(Graphics g) {
		
		super.draw(g);
		
		PointF p = owner.getLocation();
		
		g.drawLine((int) p.x - 4, (int) (p.y + (Math.sin(renderCount / Math.PI) * 4)), (int) p.x, (int) p.y);
		g.drawLine((int) p.x + 4, (int) (p.y + (Math.sin(renderCount / Math.PI) * 4)), (int) p.x, (int) p.y);
		
		if (new Random().nextInt(3) == 0) {
			this.renderCount++;
		}
		
	}
	
}

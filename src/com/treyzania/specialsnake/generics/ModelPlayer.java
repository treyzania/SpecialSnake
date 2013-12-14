package com.treyzania.specialsnake.generics;

import java.awt.Color;
import java.awt.Graphics;

import com.treyzania.specialsnake.core.IModel;
import com.treyzania.specialsnake.core.Model;
import com.treyzania.specialsnake.core.PointF;

public class ModelPlayer extends Model {

	public ModelPlayer(IModel im) {
		
		super(im);
		
	}

	@Override
	public void draw(Graphics g) {
		
		super.draw(g);
		
		PointF pf = this.owner.getPosition();
		
		g.setColor(Color.WHITE);
		g.fillOval((int) pf.x - 2, (int) pf.y - 2, 4, 4);
		
		g.setColor(Color.BLACK);
		g.drawOval((int) pf.x - 2, (int) pf.y - 2, 4, 4);
		g.drawString("EntityPlayer Test", (int) pf.x, (int) pf.y - 5);
		//g.drawLine((int) pf.x - 30, (int) pf.y - 10, (int) pf.x, (int) pf.y);
		
	}

}

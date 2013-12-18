package com.treyzania.specialsnake.generics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.treyzania.specialsnake.core.Entity;
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
		
		PointF pf = this.owner.getLocation();
		
		drawBody(g); // Draw the body itself.
		
		// Draw more accurate location data. (Let's call it the "head".)
		g.setColor(Color.WHITE);
		g.fillOval((int) pf.x - 2, (int) pf.y - 2, 4, 4);
		g.setColor(Color.BLACK);
		g.drawOval((int) pf.x - 2, (int) pf.y - 2, 4, 4);
		g.drawString(((Entity) owner).getEntManifest(), (int) pf.x, (int) pf.y - 5);
		
	}
	
	private void drawBody(Graphics g) {
		
		Rectangle[] rects = ((EntityPlayer) this.owner).getSegments();
		
		for (int i = 0; i < rects.length; i++) {
			
			Rectangle r = rects[i];
			
			if (r != null) {
				
				g.setColor(new Color(255, (i / rects.length) * 255, 255));
				g.fillRect(r.x, r.y, r.width, r.height);
				
				g.setColor(Color.BLACK);
				g.drawRect(r.x, r.y, r.width, r.height);
				
			}
			
		}
		
	}
	
	public static Rectangle getRectFromPoint(PointF point) {
		
		int cs = EntityPlayer.CELL_SIZE;
		
		int x = (int) (cs * Math.floor(point.x / cs));
		int y = (int) (cs * Math.floor(point.y / cs));
		
		return new Rectangle(x, y, cs, cs);
		
	}
	
}

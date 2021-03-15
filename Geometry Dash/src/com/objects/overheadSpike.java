/*
 * Spikes are harmful to the player. 
 * Don't hit them!
 * 
 * @author 
 * @version 
 */

package com.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.framework.GameObject;
import com.framework.ObjectId;

public class overheadSpike extends GameObject{

	private int width = 64;
	private int height = 64;
	
	public overheadSpike(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void update(LinkedList<GameObject> object) {
		// The Spike is not doing anything yet
	}

	public void render(Graphics graphics) {
		graphics.setColor(Color.RED);
		//graphics.drawRect((int)x, (int)y, width, height);
		graphics.fillPolygon(new int[] {(int)x, (int)x + width/2, (int)x + width}, new int[] {(int)y , (int)y + height, (int)y}, 3);
	}

	public Rectangle getBorder() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Polygon getBorderPoly() {
		Polygon polygon = new Polygon();
		polygon.addPoint((int)x, (int)y);
		polygon.addPoint((int)x + width/2, (int)y + height);
		polygon.addPoint((int)x + width, (int)y);
		return polygon;		
	}
	
	//RECTANGLE POLYGON COLLISION
	
	

}

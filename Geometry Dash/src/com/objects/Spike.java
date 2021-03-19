/*
 * Spikes are harmful to the player. 
 * Don't hit them!
 * 
 * @author Robert Kelm
 * @version 15.03.2021
 */

package com.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.framework.GameObject;
import com.framework.ObjectId;
import com.framework.Texture;
import com.window.Game;

public class Spike extends GameObject{
	
	Texture texture = Game.getInstance();
	
	private int width = 64;
	private int height = 64;
	int type; //0 -> normal; 1 -> overhead
	int design;
	
	public Spike(float x, float y, int type, int design, ObjectId id) {
		super(x, y, id);
		this.type = type;
		this.design = design;
	}

	public void update(LinkedList<GameObject> object) {
		// The Spike is not doing anything yet
	}

	public void render(Graphics graphics) {
		graphics.setColor(Color.RED);
		
		switch(type) {
		case 0: 
			graphics.drawImage(texture.spike[design], (int)x, (int)y, null);
			//graphics.fillPolygon(new int[] {(int)x, (int)x + width/2, (int)x + width}, new int[] {(int)y + height, (int)y, (int)y + height}, 3);
			break;
			
		case 1:
			graphics.drawImage(texture.spike[design + 8], (int)x, (int)y, null);
			//graphics.fillPolygon(new int[] {(int)x, (int)x + width/2, (int)x + width}, new int[] {(int)y , (int)y + height, (int)y}, 3);
			break;
		}

		
	}
	
	//Create a Polygon to use in collision detection
	public Polygon getBorderPoly() {
		Polygon polygon = new Polygon();
		
		switch (type) {
		case 0:	//Normal Spike
			polygon.addPoint((int)x, (int)y + height);
			polygon.addPoint((int)x + width/2, (int)y);
			polygon.addPoint((int)x + width, (int)y + height);
			break;
			
		case 1: //Overhead Spike
			polygon.addPoint((int)x, (int)y);
			polygon.addPoint((int)x + width/2, (int)y + height);
			polygon.addPoint((int)x + width, (int)y);
			break;		
		}
		
		return polygon;
				
	}
	
	
	//Not needed here
	public Rectangle getExtendedBorder() {return null;}
	public Rectangle getBorder() {return null;}

}

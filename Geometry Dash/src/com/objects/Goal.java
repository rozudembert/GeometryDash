/*
 * The Blocks limit the games area and
 * are an obstacle for the player
 */

package com.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.framework.GameObject;
import com.framework.ObjectId;
import com.framework.Texture;
import com.window.Game;

public class Goal extends GameObject{
	
	Texture texture = Game.getInstance();
	
	//block dimensions
	public int width = 64;
	public int height = 64;
		
	//public block (x coordinate, y coordinate, type, objectId)
	public Goal(int x, int y, ObjectId id) {
		super(x, y, id);
	}
	
	public void update(LinkedList<GameObject> object) {
		//The Block isn't doing anything yet
	}
	
	//the block appears as a white outlined rectangle
	public void render (Graphics graphics) {
		graphics.setColor(Color.YELLOW);
		graphics.fillRect((int)x, (int)y, width, height);
	}
	
	//creates a rectangle to use in collision detection
	public Rectangle getBorder() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
}

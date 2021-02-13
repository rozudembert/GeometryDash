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

public class Block extends GameObject{
	
	//block dimensions
	public int width = 64;
	public int height = 64;
	
	public Block(int x, int y, ObjectId id) {
		super(x, y, id);
	}
	
	public void update(LinkedList<GameObject> object) {
		//The Block isn't doing anything yet
	}
	
	//the block appears as a white outlined rectangle
	public void render (Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.drawRect((int)x, (int)y, width, height);
	}
	
	//creates a rectangle to use in collision detection
	public Rectangle getBorder() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
}

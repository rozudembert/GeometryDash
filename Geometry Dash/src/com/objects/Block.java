/*
 * The Block can either be good or bad for the Player.
 * The Player can slide on top of Blocks but cannot hit them on the side.
 * They are limiting the game area
 * 
 * @author Robert Kelm
 * @version 08.02.2021
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

public class Block extends GameObject{
	
	Texture texture = Game.getInstance();
	
	//what type of block is it -> used for texture
	private int type;
	
	//block dimensions
	private int width = 64;
	private int height = 64;
	
	//public block (x coordinate, y coordinate, type, objectId)
	public Block(int x, int y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}
	
	public void update(LinkedList<GameObject> object) {
		//The Block isn't doing anything yet
	}
	
	//assign the texture to the block
	public void render (Graphics graphics) {
		graphics.drawImage(texture.block[type], (int)x, (int)y, null);
		
	}
	
	//create a rectangle outlining the block to use in collision detection
	public Rectangle getBorder() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	
}

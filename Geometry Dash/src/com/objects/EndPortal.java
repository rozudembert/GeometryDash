/*
 * This is literally the end... 
 * 
 * ...of the level
 * 
 * @author Robert Kelm
 * @version 08.02.2021
 */

package com.objects;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.framework.GameObject;
import com.framework.ObjectId;
import com.framework.Texture;
import com.window.Game;

public class EndPortal extends GameObject{
	
	Texture texture = Game.getInstance();
	
	private int width = 64;
	private int height = 64;
	
	public EndPortal(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void update(LinkedList<GameObject> object) {
		
	}

	//render texture
	public void render(Graphics graphics) {
		graphics.drawImage(texture.block[3], (int)x, (int)y, null);
	}

	//create border to use in collision detection
	public Rectangle getBorder() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	//this method is not needed here
	public Polygon getBorderPoly() {return null;}

	@Override
	public Rectangle getExtendedBorder() {
		// TODO Auto-generated method stub
		return null;
	}
}

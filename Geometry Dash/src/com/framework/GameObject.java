/*
 * GameObject is an abstract class and used to manage every object in the game
 * 
 * @author Robert Kelm
 * @version 08.02.2021
 */

package com.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
	
	//X and Y coordinates
	protected float x, y;
	
	protected ObjectId id; 
	
	//Vertical and horizontal velocity of the object 
	protected float velX = 0f, velY = 0f;
	
	//boolean to check if player is jumping/falling
	protected boolean falling = true;
	protected boolean jumping = false;
	
	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;		
		this.id = id;
	}
	
	public abstract void update(LinkedList<GameObject> object);	
	public abstract void render(Graphics graphics);
	public abstract Rectangle getBorder();
	
	////////////////////////////
	
	//Getters and Setters:
	
	public void setX(float x) {
		this.x = x;
	}	
	
	public void setY(float y) {
		this.y = y;		
	}
	
	public float getX() {
		return x;		
	}
	
	public float getY() {
		return y;
	}
	
	public void setVelX(float velX) {
		this.velX = velX;
	}
	
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public float getVelX() {
		return velX;
	}
	
	public float getVelY() {
		return velY;
	}
	
	public void setID(ObjectId id) {
		this.id = id;
	}	
		
	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public ObjectId getId() {
		return id;
	}

}

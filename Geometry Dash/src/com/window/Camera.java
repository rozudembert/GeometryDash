/*
 * The Camera class is to follow the players movement on the horizontal axis
 */
package com.window;

import com.framework.GameObject;

public class Camera {

	private float x, y;
	
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	//The Camera follows the players movement
	public void update(GameObject player) {
		x = -player.getX() + Game.WIDTH/2;
	}
	
	
	//Getter and Setter:
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
}

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
	
	private float cameraDistance = 320;
	
	//The Camera follows the players movement
	public void update(GameObject player) {
		x = -player.getX() + Game.WIDTH/2 - 200;
		
		//Camera moves at the same speed like the player
		//x += -player.getVelX();
		
		//the camera should always have a distance between the players top and games top
		//Wenn Abstand zwischen PlayerY und cameraY kleiner als 320
			//Was
		if(player.getY() - y < cameraDistance) {
			//Adjust camera Y
			
		}
		
		
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

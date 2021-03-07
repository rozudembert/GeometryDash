package com.window;

import java.awt.Color;
import java.awt.Graphics;

import com.framework.GameObject;

public class HUD {
	
	//the maximum lenght of a level is 1024 blocks.
	public static float LEVEL_LENGTH = 1024 * 64;
	
	Camera camera;
	Controller controller;
	
	public HUD(Camera camera) {
		this.camera = camera;
	}
	
	private float progress = 0;
	
	public void update(GameObject player) {
		progress = (player.getX() / LEVEL_LENGTH) * 600;
		System.out.println(progress);
	}
	
	public void render(Graphics g) {
		int boxWidth = 600;
		int boxHeight = 20;
		int x_position = -(int)camera.getX() + Game.WIDTH/2 - boxWidth/2;
		int y_position = -(int)camera.getY()+ 10;
		
		
		//progress bar background
		//g.setColor(Color.GRAY);
		//g.fillRect(x_position, y_position, boxWidth, boxHeight);
		
		//progess bar
		g.setColor(Color.GREEN);
		g.fillRect(x_position, y_position, (int)progress, boxHeight);
		
		//White outline
		g.setColor(Color.WHITE);
		g.drawRect(x_position, y_position, boxWidth, boxHeight);
		
		
	}
}

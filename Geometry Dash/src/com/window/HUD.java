package com.window;

import java.awt.Color;
import java.awt.Graphics;

import com.framework.GameObject;

public class HUD {
	
	public static float LEVEL_LENGTH = 1024 * 64;
	
	Camera camera;
	Controller controller;
	
	public HUD(Camera camera) {
		this.camera = camera;
	}
	
	private float progress = 0;
	
	public void update(GameObject player) {
		progress = (player.getX() / LEVEL_LENGTH) * 100;
		System.out.println(progress);
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.GRAY);
		g.fillRect(-(int)camera.getX() + Game.WIDTH/2 - 300, -(int)camera.getY()+ 10, 600, 30);
		
		g.setColor(Color.GREEN);
		g.fillRect(-(int)camera.getX() + Game.WIDTH/2 - 300, -(int)camera.getY()+ 10, (int)progress*6, 30);
		
		g.setColor(Color.WHITE);
		g.drawRect(-(int)camera.getX() + Game.WIDTH/2 - 300, -(int)camera.getY()+ 10, 600, 30);
		
		
	}
}

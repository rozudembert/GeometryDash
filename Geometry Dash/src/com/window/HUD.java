/*
 * Heads Up Display showing how far the player got in the level
 * 
 * @author Robert Kelm
 * @version 08.03.2021
 */

package com.window;

import java.awt.Color;
import java.awt.Graphics;

import com.framework.GameObject;
import com.window.Game.STATUS;

public class HUD {
	
	Camera camera;
	Controller controller;
	
	//the maximum lenght of a level is 1024 blocks.
	public static float LEVEL_LENGTH = 1024 * 64;
	
	public HUD(Camera camera, Controller controller) {
		this.camera = camera;
		this.controller = controller;
	}
	
	private float progress = 0;
	
	//calculate progress depending on players position in relation to end of the level
	public void update(GameObject player) {
		progress = (player.getX() / controller.getEndBlock()) * 600;
		Menu.setProgress((int)progress / 6);
	}
	
	//render HUD on top of the screen
	public void render(Graphics g) {
		
		int boxWidth = 600;
		int boxHeight = 20;
		int x_position = -(int)camera.getX() + Game.WIDTH/2 - boxWidth/2;
		int y_position = -(int)camera.getY()+ 10;
		
		//progess bar
		if(progress < boxWidth / 3)
			g.setColor(Color.RED);
		else if(progress < boxWidth * 2/3)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.green);
			
		g.fillRect(x_position, y_position, (int)progress, boxHeight);
		
		
		//white outline
		g.setColor(Color.WHITE);
		g.drawRect(x_position, y_position, boxWidth, boxHeight);
		g.drawRect(x_position+1, y_position+1, boxWidth-2, boxHeight-2);
		
		//show Percentage
		if(progress < 1 * boxWidth) {
			g.drawString((int)progress/6 + "%", x_position + 570, y_position + 15);
		}
		
						
	}
}

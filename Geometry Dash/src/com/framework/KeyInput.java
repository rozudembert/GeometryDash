/*
 * KeyInput is there to detect the key's pressed and
 * to manage the players movement
 *
 */

package com.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.window.Controller;

public class KeyInput extends KeyAdapter{

	int test;
	
	//disables auto movement and enables more specific movement to the left and right
	private boolean testing = false;
	
	Controller controller;
	private boolean[ ] keyDown = new boolean[2];
	
	public KeyInput(Controller controller) {
		this.controller = controller;
		keyDown[1] = false;
		keyDown[0] = false;	
	}
	
	//When the right keys get pressed, the player shall move
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		//runs through the list with every object in the game
		for(int i = 0; i < controller.object.size(); i++) {
			GameObject tempObject = controller.object.get(i);
			
			//to be sure the moving object is a player and not a block
			if(tempObject.getId() == ObjectId.Player) {
				
				if(testing == true) {
				
					//movement to the right
					if(key == KeyEvent.VK_D) {
						tempObject.setVelX(5);
						keyDown[0] = true;
					}
				
					//movement to the left
					if(key == KeyEvent.VK_A) {
						tempObject.setVelX(-5);
						keyDown[1] = true;
					}
				}
				
				//jumping
				if((key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP || key == KeyEvent.VK_W)&& !tempObject.isJumping()) {	
					tempObject.setJumping(true);
					tempObject.setVelY(-10);				
				}
			}
		}
		
		//Press Escape to exit the Game
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		//runs through the list with every object in the game
		for(int i = 0; i < controller.object.size(); i++) {
			GameObject tempObject = controller.object.get(i);
			
			//to be sure the moving object is a player
			if(tempObject.getId() == ObjectId.Player) {
				
				if(key == KeyEvent.VK_D)
					keyDown[0] = false;
				
				if(key == KeyEvent.VK_A)
					keyDown[1] = false;
				
				//Player movement stops 
				if(!keyDown[0] && !keyDown[1]) 
					tempObject.setVelX(0);
			}
		}
	}
	
}

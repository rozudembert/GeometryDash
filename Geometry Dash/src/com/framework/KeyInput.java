/*
 * KeyInput is there to manage the keyboard input.
 * To control the players movement and
 * navigation inside the menus.
 *
 * @author Robert Kelm
 * @version 08.03.2021
 */

package com.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.window.Controller;
import com.window.Game;
import com.window.Game.STATUS;
import com.window.Menu;

public class KeyInput extends KeyAdapter{
	
	Game game;
	Controller controller;
	Menu menu;
	
	private boolean[ ] keyDown = new boolean[2];
	
	private int level = 1;
	
	public KeyInput(Game game, Controller controller, Menu menu) {
		this.game = game;
		this.controller = controller;
		this.menu = menu;
		keyDown[1] = false;
		keyDown[0] = false;	
	}
	
	//When the right keys get pressed, the player shall move
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		//runs through the list with every object in the game
		for(int i = 0; i < Controller.object.size(); i++) {
			GameObject tempObject = Controller.object.get(i);
			
			//to be sure the moving object is a player and not a block
			if(tempObject.getId() == ObjectId.Player) {
				
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
		
		//TitleScreen 
		if(Game.gameStatus == STATUS.StartMenu) {
			if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
				game.setGameStatus(STATUS.Game);
				controller.startLevel(level);
			}
		}
		
		//Menu Options
		else if(Game.gameStatus == STATUS.Menu) {
			
			//Play Button
			if(menu.getPlayButton()) {
				if(key == KeyEvent.VK_ENTER) {
					game.setGameStatus(STATUS.Game);
					controller.startLevel(level);										
				}
				else if(key == KeyEvent.VK_LEFT) {
					menu.setPlayButton(false);
					menu.setLeftLevelButton(true);
				}
				else if(key == KeyEvent.VK_RIGHT) {
					menu.setPlayButton(false);
					menu.setRightLevelButton(true);
				}
			}
			
			//Left Button to change level
			else if(menu.getLeftLevelButton()) {
				if(key == KeyEvent.VK_ENTER && level > 1 ) {
					level = level - 1;
					menu.setLevel(level);
					System.out.println("You selected level: " + level);
				}
				else if(key == KeyEvent.VK_RIGHT) {
					menu.setLeftLevelButton(false);
					menu.setPlayButton(true);
				}
			}
			
			//Right Button to change level
			else if(menu.getRightLevelButton()) {
				if(key == KeyEvent.VK_ENTER && level < 5 ) {
					level++;
					menu.setLevel(level);
					System.out.println("You selected level: " + level);
				}
				else if(key == KeyEvent.VK_LEFT) {
					menu.setRightLevelButton(false);
					menu.setPlayButton(true);
				}
			}
		}
	}
	
	//This code is in fact unecessary
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		//runs through the list with every object in the game
		for(int i = 0; i < Controller.object.size(); i++) {
			GameObject tempObject = Controller.object.get(i);
			
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

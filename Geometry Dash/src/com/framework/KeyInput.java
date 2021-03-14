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
					menu.addJumps();
					tempObject.setJumping(true);
					tempObject.setVelY(-20);	
					
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
				game.setGameStatus(STATUS.Menu);
				//controller.startLevel(level);
			}
		}
		
		//Menu Options
		else if(Game.gameStatus == STATUS.Menu) {
			
			//Play Button
			if(menu.getMainPlayButton()) {
				if(key == KeyEvent.VK_ENTER) {
					game.setGameStatus(STATUS.Game);
					controller.startLevel(level);	
					menu.setMainPlayButton(false);
				}
				else if(key == KeyEvent.VK_LEFT) {
					if(level > 1) {
						menu.setMainPlayButton(false);
						menu.setMainLeftLevelButton(true);
					}
					else {
						menu.setMainPlayButton(false);
						menu.setGear(true);
					}
				}
				else if(key == KeyEvent.VK_RIGHT) {
					if(level < 5) {
						menu.setMainPlayButton(false);
						menu.setMainRightLevelButton(true);
					}					
				}
				else if(key == KeyEvent.VK_UP) {
					menu.setMainPlayButton(false);
					menu.setGear(true);
				}
			}
			
			//Left Button to change level
			else if(menu.getMainLeftLevelButton()) {
				if(key == KeyEvent.VK_ENTER && level > 1 ) {
					level = level - 1;
					menu.setLevel(level);
					System.out.println("You selected level: " + level);
				}
				else if(key == KeyEvent.VK_RIGHT) {
					menu.setMainLeftLevelButton(false);
					menu.setMainPlayButton(true);
				}
				else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_LEFT) {
					menu.setMainLeftLevelButton(false);
					menu.setGear(true);
				}
			}
			
			//Right Button to change level
			else if(menu.getMainRightLevelButton()) {
				if(key == KeyEvent.VK_ENTER && level < 5 ) {
					level++;
					menu.setLevel(level);
					System.out.println("You selected level: " + level);
					
					if(level == 5) {
						menu.setMainRightLevelButton(false);
						menu.setMainPlayButton(true);
					}
					
				}
				else if(key == KeyEvent.VK_LEFT) {
					menu.setMainRightLevelButton(false);
					menu.setMainPlayButton(true);
				}
				else if(key == KeyEvent.VK_UP) {
					menu.setMainRightLevelButton(false);
					menu.setGear(true);
				}
			}
			
			else if(menu.getGear()) {
				if(key == KeyEvent.VK_ENTER) {
					//TODO nothing is happening yet
				}
				else if(key == KeyEvent.VK_DOWN) {
					menu.setGear(false);
					menu.setMainPlayButton(true);
				}
				else if(key == KeyEvent.VK_LEFT) {
					menu.setGear(false);
					menu.setQuit(true);
				}
				else if(key == KeyEvent.VK_RIGHT) {
					if(level > 1) {
						menu.setGear(false);
						menu.setMainLeftLevelButton(true);
					}
					else {
						menu.setGear(false);
						menu.setMainPlayButton(true);
					}
				}
			}
			
			else if(menu.getQuit()) {
				if(key == KeyEvent.VK_ENTER) {
					System.exit(1);
				}
				if(key == KeyEvent.VK_DOWN) {
					menu.setQuit(false);
					menu.setMainPlayButton(true);
				}
				if(key == KeyEvent.VK_RIGHT) {
					menu.setQuit(false);
					menu.setGear(true);
				}
			}
		}
		
		else if(Game.gameStatus == STATUS.Dead) {
			
			//
			if(menu.get_death_retry()) {
				if(key == KeyEvent.VK_ENTER) {
					game.setGameStatus(STATUS.Game);
					controller.startLevel(level);
					menu.setMainPlayButton(false);
				}
				else if(key == KeyEvent.VK_LEFT) {
					menu.set_death_retry(false);
					menu.set_death_backToMenu(true);
				}
			}
			else if(menu.get_death_backToMenu()) {
				if(key == KeyEvent.VK_ENTER) {
					game.setGameStatus(STATUS.Menu);
					menu.set_death_backToMenu(false);
					menu.setMainPlayButton(true);
				}
				else if(key == KeyEvent.VK_RIGHT) {
					menu.set_death_backToMenu(false);
					menu.set_death_retry(true);
				}
			}
		}
		
		else if(Game.gameStatus == STATUS.End) {
			
			if(menu.get_final_retry()) {
				if(key == KeyEvent.VK_ENTER) {
					game.setGameStatus(STATUS.Game);
					controller.startLevel(level);										
				}
				else if(key == KeyEvent.VK_LEFT) {
					menu.set_final_retry(false);
					menu.set_final_backToMenu(true);
				}
				else if(key == KeyEvent.VK_RIGHT) {
					menu.set_final_retry(false);
					menu.set_final_nextLevel(true);
				}
			}
			
			else if(menu.get_final_backToMenu()) {
				
				if(key == KeyEvent.VK_ENTER) {
					game.setGameStatus(STATUS.Menu);
					menu.set_final_backToMenu(false);
					menu.setMainPlayButton(true);
				}
				else if(key == KeyEvent.VK_RIGHT) {
					menu.set_final_backToMenu(false);
					menu.set_final_retry(true);
				}
			}
			
			else if(menu.get_final_nextLevel()) {
				
				if(key == KeyEvent.VK_ENTER) {
					game.setGameStatus(STATUS.Game);
					level++;
					controller.startLevel(level);
				}
				else if(key == KeyEvent.VK_LEFT) {
					menu.set_final_nextLevel(false);
					menu.set_final_retry(true);
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

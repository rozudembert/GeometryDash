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
	private boolean keySpace = false;
	private boolean keyW = false;
	private boolean keyUP = false;	
	
	private int level = 1;
	
	public KeyInput(Game game, Controller controller, Menu menu) {
		this.game = game;
		this.controller = controller;
		this.menu = menu;
		keyDown[1] = false;
		keyDown[0] = false;	
	}
	
	public void update() {
		for(int i = 0; i < Controller.object.size(); i++) {
			GameObject tempObject = Controller.object.get(i);
			
			
			//Player is Jumping			
			if(tempObject.getId() == ObjectId.Player) {
				if(keySpace || keyW || keyUP) {
					menu.addJumps();
					
					tempObject.setVelY(-19);
					tempObject.setJumping(true);
					
					keySpace = false;
					keyW = false;
					keyUP = false;
				}
			}
		}
	}
	
	//Check the keys that are pressed
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
				
		//runs through the list with every object in the game
		for(int i = 0; i < Controller.object.size(); i++) {
			GameObject tempObject = Controller.object.get(i);
			
			//to be sure we are affecting the Player
			if(tempObject.getId() == ObjectId.Player) {
				
				//jumping
				if((key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP || key == KeyEvent.VK_W)&& !tempObject.isJumping() ) {	
					keySpace = true;
					keyW = true;
					keyUP = true;
										
				}
				 //Special Player controls in God Mode
				if(Game.getGodMode()) {
					if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
						tempObject.setVelX(12);
						keyDown[0] = true;
					}
					
					if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
						tempObject.setVelX(-12);
						keyDown[1] = true;
					}
				}
				
				//Pause the Game
				if((key == KeyEvent.VK_P || key == KeyEvent.VK_ESCAPE) && !Game.paused) {
					Game.paused = true;
				} 
				//Continue the Game whenever any key is pressed
				else 
					Game.paused = false;
				
			}
		}
		
		//TitleScreen 
		if(Game.gameStatus == STATUS.StartMenu) {
			
			//Get into Main Menu
			if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
				game.setGameStatus(STATUS.Menu);
			}
		}
		
		//Menu Options
		else if(Game.gameStatus == STATUS.Menu) {
			
			//Activate and deactivate God Mode
			if(key == KeyEvent.VK_B && Game.getGodMode() == false) {
				Game.setGodMode(true);
				System.out.println("God Mode activated");
			}
			else if(key == KeyEvent.VK_B && Game.getGodMode() == true) {
				Game.setGodMode(false);
				System.out.println("God Mode deactivated");
			}
			
			//Change the Player Skin
			if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
				if(menu.getPlayer() < 8) {
					menu.setPlayer(menu.getPlayer() + 1);
					controller.setPlayerSkin(controller.getPlayerSkin() + 1);
				}
			}
			else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
				if(menu.getPlayer() > 1) {
					menu.setPlayer(menu.getPlayer() - 1);
					controller.setPlayerSkin(controller.getPlayerSkin() - 1);
				}
			}			
			
			//////////////////////////////////////////////////////////////
			//															//
			//				ACTIONS FOR EVERY BUTTON 					//
			//															//
			//		->  change buttons using WASD or Arrow Keys			//
			//															//
			//////////////////////////////////////////////////////////////
			
			
			//Play Button
			if(menu.getMainPlayButton()) {
				if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
					game.setGameStatus(STATUS.Game);
					controller.startLevel(level);	
					menu.setMainPlayButton(false);
					menu.setJumps(0);
				}
				else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					if(level > 1) {
						menu.setMainPlayButton(false);
						menu.setMainLeftLevelButton(true);
					}
				}
				else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					if(level < 5) {
						menu.setMainPlayButton(false);
						menu.setMainRightLevelButton(true);
					}	
					else {
						menu.setMainPlayButton(false);
						menu.setGear(true);
					}
				}
			}
			
			//Left Button to change level
			else if(menu.getMainLeftLevelButton()) {
				if((key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) && level > 1 ) {
					level = level - 1;
					menu.setLevel(level);
					System.out.println("You selected level: " + level);
					
					if(level == 1) {
						menu.setMainLeftLevelButton(false);
						menu.setMainPlayButton(true);
					}
				}
				else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					menu.setMainLeftLevelButton(false);
					menu.setMainPlayButton(true);
				}
			}
			
			//Right Button to change level
			else if(menu.getMainRightLevelButton()) {
				if((key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) && level < 5 ) {
					level++;
					menu.setLevel(level);
					System.out.println("You selected level: " + level);
					
					if(level == 5) {
						menu.setMainRightLevelButton(false);
						menu.setMainPlayButton(true);
					}
					
				}
				
				//Change Button
				else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					menu.setMainRightLevelButton(false);
					menu.setMainPlayButton(true);
				}
				else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					menu.setMainRightLevelButton(false);
					menu.setGear(true);
				}
			}
			
			//Option Button
			else if(menu.getGear()) {
				if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
					//TODO nothing is happening yet
				}
				else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					menu.setGear(false);
					menu.setQuit(true);
				}
				else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					if(level < 5) {
						menu.setGear(false);
						menu.setMainRightLevelButton(true);
					}
					else {
						menu.setGear(false);
						menu.setMainPlayButton(true);
					}
				}
			}
			
			//Quit the game 
			else if(menu.getQuit()) {
				if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
					System.exit(1);
				}
				if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					menu.setQuit(false);
					menu.setGear(true);
				}
			}
		}
		
		//Game-Over-Screen-Actions
		else if(Game.gameStatus == STATUS.Dead) {
			
			//Play Again Button
			if(menu.get_death_retry()) {
				if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
					game.setGameStatus(STATUS.Game);
					controller.startLevel(level);
					menu.setMainPlayButton(false);
					menu.setJumps(0);
				}
				else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					menu.set_death_retry(false);
					menu.set_death_backToMenu(true);
				}
			}
			//Back to menu Button
			else if(menu.get_death_backToMenu()) {
				if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
					game.setGameStatus(STATUS.Menu);
					menu.set_death_backToMenu(false);
					menu.setMainPlayButton(true);
				}
				else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					menu.set_death_backToMenu(false);
					menu.set_death_retry(true);
				}
			}
		}
		
		//Congratulations! You did it! - Screen
		else if(Game.gameStatus == STATUS.End) {
			
			//Play Again Button
			if(menu.get_final_retry()) {
				if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
					game.setGameStatus(STATUS.Game);
					controller.startLevel(level);	
					menu.setJumps(0);
				}
				else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					menu.set_final_retry(false);
					menu.set_final_backToMenu(true);
				}
				else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					menu.set_final_retry(false);
					menu.set_final_nextLevel(true);
				}
			}
			
			//Back To Menu Button
			else if(menu.get_final_backToMenu()) {
				
				if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
					game.setGameStatus(STATUS.Menu);
					menu.set_final_backToMenu(false);
					menu.setMainPlayButton(true);
				}
				else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					menu.set_final_backToMenu(false);
					menu.set_final_retry(true);
				}
			}
			
			//Next Level Button
			else if(menu.get_final_nextLevel()) {
				
				if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
					game.setGameStatus(STATUS.Game);
					level++;
					controller.startLevel(level);
					menu.set_final_nextLevel(false);
				}
				else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					menu.set_final_nextLevel(false);
					menu.set_final_retry(true);
				}
			}	
		}
	}
	
	//This code is only necessary in god mode
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
				
				if((key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP || key == KeyEvent.VK_W)&& !tempObject.isJumping()) {	
					keySpace = false;
					keyW = false;
					keyUP = false;
				}
			}
		}
	}
	
}

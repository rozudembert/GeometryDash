package com.window;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.window.Game.STATUS;

public class Menu extends KeyAdapter{
	
	//graphics
	private BufferedImage titleScreen = null;
	private BufferedImage buttonLeft = null, buttonRight = null, buttonLeftActive = null, buttonRightActive = null;
	private BufferedImage buttonPlay = null, buttonPlayActive = null;
	
	
	//boolean for the selected buttons
	private static boolean playButtonSelected = true;
	private static boolean leftLevelButtonSelected = false;
	private static boolean rightLevelButtonSelected = false;
	private static boolean backButtonSelected = false;
	
	private static Game game;
	private Controller controller;	
	
	private int level = 1;

	public Menu(Game game, Controller controller) {
		Menu.game = game;
		this.controller = controller;
		
		graphics();
		
	}
	
	public void graphics() {
		ImageLoader loader = new ImageLoader();
		
		try {
			titleScreen = loader.loadImage("/TitleScreen.png");
			buttonLeft = loader.loadImage("/buttons/buttonLeft.png");
			buttonLeftActive = loader.loadImage("/buttons/buttonLeftActive.png");
			buttonRight = loader.loadImage("/buttons/buttonRight.png");
			buttonRightActive = loader.loadImage("/buttons/buttonRightActive.png");
			buttonPlay = loader.loadImage("/buttons/Play.png");
			buttonPlayActive = loader.loadImage("/buttons/PlayActive.png");
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(game.gameStatus == STATUS.StartMenu) {
			if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
				game.setGameStatus(STATUS.Menu);
			}
		}		
		else if(game.gameStatus == STATUS.Menu) {
			
			//Play Button
			if(playButtonSelected) {
				//Start the Game
				if(key == KeyEvent.VK_ENTER) {
					game.setGameStatus(STATUS.Game);
					controller.startLevel(level);					
									
				}
				else if(key == KeyEvent.VK_LEFT) {
					playButtonSelected = false;
					leftLevelButtonSelected = true;
				}
				else if(key == KeyEvent.VK_RIGHT) {
					playButtonSelected = false;
					rightLevelButtonSelected = true;
				}
			}			
			
			//Left Level Button to change level
			else if(leftLevelButtonSelected) {
				if(key == KeyEvent.VK_ENTER && level > 1 ) {
					level = level - 1;
					System.out.println("You selected level: " + level);
				}
				else if(key == KeyEvent.VK_RIGHT) {
					leftLevelButtonSelected = false;
					playButtonSelected = true;
				}
			}
			
			//Right Level Button to change level
			else if(rightLevelButtonSelected) {
				if(key == KeyEvent.VK_ENTER && level < 5 ) {
					level++;
					System.out.println("You selected level: " + level);
				}
				else if(key == KeyEvent.VK_LEFT) {
					rightLevelButtonSelected = false;
					playButtonSelected = true;
				}
			}			
			
		}	
		
		//else if(//new button in different menu state) {
			
		//}
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		
		int showLevel = level;
		Font fnt = new Font("calibri", 1, 50);
        Font fnt2 = new Font("calibri", 1, 28);
        int q = 200; //boxWidth
        int j = 64; //boxHeight
        int width = Game.WIDTH;
        int height = Game.HEIGHT;
        g.setColor(Color.WHITE);
        g.setFont(fnt2);
        Color buttonYellow = new Color(255,168,0);

        if(game.gameStatus == STATUS.StartMenu) {
        	g.drawImage(titleScreen, 0, 0, null);
            	
        	g.setFont(fnt2);
        	g.setColor(buttonYellow);
        	g.drawString("Press SPACE to continue", width / 2 + 200, height / 2 + 150);
        }        
        else if(game.gameStatus==STATUS.Menu) {
        	//g.drawImage(menuTest, 0, 0, null);
        	
        	//Left Button
        	if(true) {
        		if(leftLevelButtonSelected) {
        			g.drawImage(buttonLeftActive, 340, 405, null);
        			g.setColor(buttonYellow);
        		}
            	else {
            		g.setColor(Color.white);
            		g.drawImage(buttonLeft, 340, 405, null);
            	}
            	g.drawString("Level: " + (showLevel-1) , 364, 510);
        	}
        	        	        	
        	//Right Button
        	if(true) {
        		if(rightLevelButtonSelected) {
        			g.drawImage(buttonRightActive, 830, 405, null);
        			g.setColor(buttonYellow);
        		}
            	else {
            		g.setColor(Color.white);
            		g.drawImage(buttonRight, 830, 405, null);
            	}
            	g.drawString("Level: " + (showLevel+1), 852, 510);
        	}
        	
        	//Play Button
        	if(playButtonSelected) {
        		g.drawImage(buttonPlayActive, 503, 532, null);
        		g.setColor(buttonYellow);
        	}
        	else {
        		g.setColor(Color.white);
        		g.drawImage(buttonPlay, 503, 532, null);
        	}
        	
        	g.drawString("< Level: " + (showLevel) + " >", 575, 580);
        	
        	
        }        
	}
	
}
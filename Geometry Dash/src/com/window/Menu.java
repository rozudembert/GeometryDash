package com.window;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.window.Game.STATUS;
import com.window.ImageLoader;

public class Menu extends KeyAdapter{
	
	//graphics
	private BufferedImage titleScreen = null;
	private BufferedImage buttonLeft = null, buttonRight = null, buttonLeftActive = null, buttonRightActive = null;
	
	//boolean for the selected buttons
	private static boolean playButtonSelected = true;
	private static boolean levelButtonSelected = false;
	private static boolean helpButtonSelected = false;
	private static boolean backButtonSelected = false;
	private static boolean quitButtonSelected = false;
	
	private static Game game;
	private Controller controller;	
	private int level = 1;

	public Menu(Game game, Controller controller) {
		Menu.game = game;
		this.controller = controller;
		
	}
	
	public void graphics() {
		ImageLoader loader = new ImageLoader();
		
		try {
			titleScreen = loader.loadImage("/TitleScreen.png");
			buttonLeft = loader.loadImage("/buttonLeft.png");
			buttonLeftActive = loader.loadImage("/buttonLeftActive.png");
			buttonRight = loader.loadImage("/buttonRight.png");
			buttonRightActive = loader.loadImage("/buttonRightActive.png");
		
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
			if(playButtonSelected == true) {
				//Start the Game
				if(key == KeyEvent.VK_ENTER) {
					game.setGameStatus(STATUS.Game);
					controller.startLevel(level);
					
					//ImageLoader loader = new ImageLoader();
					//game.level = loader.loadImage("/level_1.png");
					//controller.loadLevel(game.level);					
				}
				else if(key == KeyEvent.VK_LEFT) {
					playButtonSelected = false;
					helpButtonSelected = true;
				}
				else if(key == KeyEvent.VK_RIGHT) {
					playButtonSelected = false;
					levelButtonSelected = true;
				}
			}
			
			//Help Button
			else if(helpButtonSelected == true) {
				if(key == KeyEvent.VK_RIGHT) {
					helpButtonSelected = false;
					playButtonSelected = true;
				}
				else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_LEFT) {
					helpButtonSelected = false;
					quitButtonSelected = true;
				}
				//Get in the Help Menu
				else if(key == KeyEvent.VK_ENTER) {
					game.setGameStatus(STATUS.Help);
					helpButtonSelected = false;
					backButtonSelected = true;
				}
			}
			
			//Quit Button
			else if(quitButtonSelected == true) {
				if(key == KeyEvent.VK_UP || key == KeyEvent.VK_RIGHT) {
					quitButtonSelected = false;
					helpButtonSelected = true;
				}
				else if(key == KeyEvent.VK_ENTER) {
					System.exit(1);
				}
			}
			
			//Change Level
			else if(levelButtonSelected == true) {
				if(key == KeyEvent.VK_UP) {
					if(level < 5)
						level++;
						System.out.println("You selected Level " + level);
				}
				else if(key == KeyEvent.VK_DOWN) {
					if(level > 1)
						level = level - 1;
						System.out.println("You selected Level " + level);
				}
				else if(key == KeyEvent.VK_LEFT) {
					levelButtonSelected = false;
					playButtonSelected = true;
				}
				else if(key == KeyEvent.VK_ENTER) {
					game.setGameStatus(STATUS.Game);
					controller.startLevel(level);
				}
			}
		}	
		else if(game.gameStatus == STATUS.Help) {
			if(backButtonSelected == true) {
				if(key == KeyEvent.VK_ENTER) {
					game.setGameStatus(STATUS.Menu);
					backButtonSelected = false;
					helpButtonSelected = true;
				}
			}
		}
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		
		Font fnt = new Font("calibri", 1, 50);
        Font fnt2 = new Font("calibri", 1, 30);
        int q = 200; //boxWidth
        int j = 64; //boxHeight
        int width = Game.WIDTH;
        int height = Game.HEIGHT;

        if(game.gameStatus == STATUS.StartMenu) {
        	g.drawImage(titleScreen, 0, 0, null);
            	
        	g.setFont(fnt2);
        	g.setColor(Color.yellow);
        	g.drawString("Press SPACE to continue", width / 2 + 200, height / 2 + 150);
        }        
        else if(game.gameStatus==STATUS.Menu) {
        	g.setFont(fnt);
        	g.setColor(Color.white);
        	g.drawString("Menu", width / 2 - 75, 100);

        	g.setFont(fnt2);
        	
        	//Play Button Position
        	if (playButtonSelected) g.setColor(Color.orange);
        	else g.setColor(Color.white);
        	g.drawRect(width / 2 - 192 / 2, height / 2 - 50, 192, 192);
        	g.drawString("Play", width / 2 - 26, height / 2 + 60);
        	
        	
        	//Help Button Position
        	if (helpButtonSelected) g.setColor(Color.orange);
        	else g.setColor(Color.white);
	        g.drawRect(width / 2 - 500, height / 2, q, j);
	        g.drawString("Help", width / 2 - 430, height / 2 + (j / 2) + 10);
        	
        	
	        //Quit Button Position
	        if (quitButtonSelected) g.setColor(Color.orange);
	        else g.setColor(Color.white);
	        g.drawRect(0, height - j , 100, 50);
	        g.drawString("Quit", 20, height - 30);
	        
	        //Level Selector Color and Position
	        if (levelButtonSelected) g.setColor(Color.orange);
	        else g.setColor(Color.white);
	        g.drawRect(width / 2 + 300, height / 2, q, j);
	        g.drawString("Level: " + level, width / 2 + 330, height / 2 + (j / 2) + 10);
        	
        }
        else if(game.gameStatus==STATUS.Help){
        	g.setFont(fnt2);
        	
        	if(backButtonSelected) g.setColor(Color.orange);
        	else g.setColor(Color.white);
        	g.drawRect(width / 2 - q / 2, height - j * 2, q, j);
        	g.drawString("Back", width / 2 - 40, height - j*3/2 + 10);

        	g.setFont(fnt);
        	g.setColor(Color.white);
        	g.drawString("Help", width / 2 - 60, 80);        	
		}
	}
	
}
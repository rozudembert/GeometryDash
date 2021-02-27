package com.window;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.window.Game.STATUS;

public class Menu extends KeyAdapter{
	
	private static boolean playButtonSelected = true;
	private static boolean helpButtonSelected = false;
	private static boolean backButtonSelected = false;
	private static boolean quitButtonSelected = false;
	
	private static Game game;
	private Controller controller;
	
	
	public Menu(Game game, Controller controller) {
		Menu.game = game;
		this.controller = controller;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(game.gameStatus == STATUS.Menu) {
			
			//Play Buttin
			if(playButtonSelected == true) {
				if(key == KeyEvent.VK_ENTER) {
					game.setGameStatus(STATUS.Game);
					
					ImageLoader loader = new ImageLoader();
					game.level = loader.loadImage("/level_1.png");
					controller.loadLevel(game.level);					
				}
				else if(key == KeyEvent.VK_DOWN) {
					playButtonSelected = false;
					helpButtonSelected = true;
				}
			}
			
			//Help Button
			else if(helpButtonSelected == true) {
				if(key == KeyEvent.VK_UP) {
					helpButtonSelected = false;
					playButtonSelected = true;
				}
				else if(key == KeyEvent.VK_DOWN) {
					helpButtonSelected = false;
					quitButtonSelected = true;
				}
				else if(key == KeyEvent.VK_ENTER) {
					game.setGameStatus(STATUS.Help);
					helpButtonSelected = false;
					backButtonSelected = true;
				}
			}
			
			//Quit Button
			else if(quitButtonSelected == true) {
				if(key == KeyEvent.VK_UP) {
					quitButtonSelected = false;
					helpButtonSelected = true;
				}
				else if(key == KeyEvent.VK_ENTER) {
					System.exit(1);
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
		
		Font fnt = new Font("arial", 1, 50);
        Font fnt2 = new Font("arial", 1, 30);
        int q = 200;
        int j = 64;
        int width = Game.WIDTH;
        int height = Game.HEIGHT;

        if(game.gameStatus==STATUS.Menu) {
        	g.setFont(fnt);
        	g.setColor(Color.white);
        	g.drawString("Menu", width / 2 - 75, 100);

        	g.setFont(fnt2);
        	
        	if (playButtonSelected) g.setColor(Color.orange);
        	else g.setColor(Color.white);
        	g.drawRect(width / 2 - q / 2, height / 2 - (j * 3 / 2), q, j);
        	g.drawString("Play", width / 2 - 30, height / 2 - j + 10);

        	if (helpButtonSelected) g.setColor(Color.orange);
        	else g.setColor(Color.white);
	        g.drawRect(width / 2 - q / 2, height / 2, q, j);
	        g.drawString("Help", width / 2 - 30, height / 2 + (j / 2) + 10);

	        if (quitButtonSelected) g.setColor(Color.orange);
	        else g.setColor(Color.white);
	        g.drawRect(width / 2 - q / 2, height / 2 + (j * 3 / 2), q, j);
	        g.drawString("Quit", width / 2 - 30, height / 2 + j * 2 + 10);
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
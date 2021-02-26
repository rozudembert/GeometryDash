package com.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.window.Game.STATUS;

public class Menu extends KeyAdapter{
	
	private BUTTON button = BUTTON.Play;
	
	private enum BUTTON{
		Play,
		Help,
		Back,
		Quit;
	}
	
	private Game game;
	private Controller controller;
	
	public Menu(Game game, Controller controller) {
		this.game = game;
		this.controller = controller;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();		
		
		if(button == BUTTON.Play) {
			if(key == KeyEvent.VK_DOWN) button = BUTTON.Help;
			else if (key == KeyEvent.VK_ENTER) {
				game.gameStatus = STATUS.Game;
				System.out.println("Enter");
				
				//load level image from file system
				ImageLoader loader = new ImageLoader();
				game.level = loader.loadImage("/level_1.png");
				
				controller.loadLevel(game.level);
			}
		}
		else if(button == BUTTON.Help) {
			if(key == KeyEvent.VK_UP) button = BUTTON.Play;
			else if(key == KeyEvent.VK_DOWN) button = BUTTON.Quit;
			else if (key == KeyEvent.VK_ENTER) {
				game.gameStatus = STATUS.Help;
				button = BUTTON.Back;
			}
		}
		else if(button == BUTTON.Quit) {
			if(key == KeyEvent.VK_UP) button = BUTTON.Help;
			else if(key == KeyEvent.VK_ENTER) {
				System.exit(1);
			}
		}
		else if(button == BUTTON.Back) {
			if(key == KeyEvent.VK_ENTER) {
				game.gameStatus = STATUS.Menu;
				button = BUTTON.Help;
			}
		}
		
		
		
	}
	
	public void keyReleased(KeyEvent e) {
		
	}	
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		
		if(game.gameStatus == STATUS.Menu) {
			Font fnt = new Font("calibri", 1, 80);
			Font fnt2 = new Font("calibri", 1, 40);
			
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Menu", Game.WIDTH/2 - 105, 130);
			g.setFont(fnt2);
			g.drawString("Play", Game.WIDTH/2 - 50, 315);
			g.drawString("Help", Game.WIDTH/2 - 50, 465);
			g.drawString("Quit", Game.WIDTH/2 - 50, 615);
			
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2 - 200, 250, 400, 100);
			g.drawRect(Game.WIDTH/2 - 200, 400, 400, 100);
			g.drawRect(Game.WIDTH/2 - 200, 550, 400, 100);
		} else if(game.gameStatus == STATUS.Help) {
			
			Font fnt = new Font("calibri", 1, 80);
			Font fnt2 = new Font("calibri", 1, 40);
			
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("HELP", Game.WIDTH/2 - 105, 130);
			g.drawRect(Game.WIDTH/2 - 200, 550, 400, 100);
			g.setFont(fnt2);
			g.drawString("Back", Game.WIDTH/2 - 50, 615);
		}
		
		//CREATE YELLOW BOX AROUND SELECTED BOX!!
	}	
}
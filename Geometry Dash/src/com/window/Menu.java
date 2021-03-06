package com.window;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.framework.Texture;
import com.window.Game.STATUS;

public class Menu{
	
	//graphics
	private BufferedImage titleScreen = null;
	private BufferedImage buttonLeft = null, buttonRight = null, buttonLeftActive = null, buttonRightActive = null;
	private BufferedImage buttonPlay = null, buttonPlayActive = null;
	
	
	//boolean for the selected buttons
	private static boolean playButton = true;
	private static boolean leftLevelButton = false;
	private static boolean rightLevelButton = false;
	
	private static Game game;
	private Controller controller;	
	Texture texture;
	
	private int level = 1;

	public Menu(Game game, Controller controller) {
		Menu.game = game;
		this.controller = controller;
		
		graphics();
		
	}
	
	//to load graphics from the files
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
	
	public void update() {
		//HIER DRIN BOXEN ERSTELLEN
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
        
        //to render titleScreen
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
        		if(leftLevelButton) {
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
        		if(rightLevelButton) {
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
        	if(playButton) {
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
	
	public void setPlayButton(boolean playButton) {
		this.playButton = playButton;
	}
	public boolean getPlayButton() {
		return playButton;
	}
	public void setLeftLevelButton(boolean leftLevelButton) {
		this.leftLevelButton = leftLevelButton;
	}
	public boolean getLeftLevelButton() {
		return leftLevelButton;
	}
	public void setRightLevelButton(boolean rightLevelButton) {
		this.rightLevelButton = rightLevelButton;
	}
	public boolean getRightLevelButton() {
		return rightLevelButton;
	}
	
}
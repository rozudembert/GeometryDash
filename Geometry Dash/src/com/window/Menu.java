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
	private BufferedImage buttonPlay = null, buttonPlayActive = null;
	private BufferedImage menu_background = null;
	
	//boolean for the selected buttons
	private static boolean playButton = true;
	private static boolean leftLevelButton = false;
	private static boolean rightLevelButton = false;
	
	private static Game game;
	private Controller controller;	
	Texture texture;
	
	private int level = 1;

	public Menu(Game game, Controller controller, Texture texture) {
		Menu.game = game;
		this.controller = controller;
		this.texture = texture;
		
		graphics();
		
	}
	
	//to load graphics from the files
	public void graphics() {
		ImageLoader loader = new ImageLoader();
		
		try {
			titleScreen = loader.loadImage("/TitleScreen.png");
			buttonPlay = loader.loadImage("/buttons/Play.png");
			buttonPlayActive = loader.loadImage("/buttons/PlayActive.png");
			menu_background = loader.loadImage("/background/menu_background.png");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		
		
	}
	
	public void render(Graphics g) {
	
        Font fnt2 = new Font("calibri", 1, 28);
        int width = Game.WIDTH;
        int height = Game.HEIGHT;
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
        	
        	g.drawImage(menu_background, 0, 0, null);
        	
        	if(leftLevelButton && level > 1) {
        		g.drawImage(texture.button[level + 4], 340, 405, null);
        	} 
        	else {
        		g.drawImage(texture.button[level - 1], 340, 405, null);
        	}
        	
        	if(rightLevelButton && level < 5) {
        		g.drawImage(texture.button[level + 16], 830, 405, null);
        	} 
        	else if (level == 5) {
        		g.drawImage(texture.button[0], 340, 405, null);
        	} 
        	else {
        		g.drawImage(texture.button[level + 11], 830, 405, null);
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
        	
        	g.drawString("< Level: " + (level) + " >", 575, 580);
        	
        	
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
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLevel() {
		return level;
	}
}
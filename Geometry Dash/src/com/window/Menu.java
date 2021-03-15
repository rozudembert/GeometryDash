/*
 * For navigation between playing to select level, design etc.
 * 
 * @author Robert Kelm
 * @version 08.03.2021
 */

package com.window;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.framework.Texture;
import com.framework.ImageLoader;
import com.window.Game.STATUS;

public class Menu{
	
	private BufferedImage titleScreen = null;
	private BufferedImage menu_background = null;
	
	private BufferedImage buttonPlay = null, buttonPlayActive = null;
	private BufferedImage gear_button = null, gear_selected = null, x_button = null, x_button_selected = null; 
	
	private BufferedImage menuButton = null, menuButton_Selected = null;
	private BufferedImage playAgainButton = null, playAgainButton_Selected = null;
	private BufferedImage resumeButton = null, resumeButton_Selected = null;
	
	//boolean for the selected buttons
	private static boolean playButton = true;
	private static boolean main_leftButton = false;
	private static boolean main_rightButton = false;
	private static boolean quit = false;
	private static boolean gear = false;
	
	private static boolean death_retry = false;
	private static boolean death_backToMenu = false;
	
	private static boolean final_retry = false;
	private static boolean final_nextLevel = false;
	private static boolean final_backToMenu = false;
	
	
	Texture texture;
	
	private int level = 1;
	private int jumps = 0;

	public Menu(Game game, Controller controller, Texture texture) {
		this.texture = texture;
		graphics();
	}
	
	//to load graphics from the files
	public void graphics() {
		ImageLoader loader = new ImageLoader();
		
		try {
			titleScreen = loader.loadImage("/TitleScreen.png");
			menu_background = loader.loadImage("/background/menu_background.png");
			buttonPlay = loader.loadImage("/buttons/Play.png");
			buttonPlayActive = loader.loadImage("/buttons/PlayActive.png");
			
			gear_button = loader.loadImage("/buttons/gear.png");
			gear_selected = loader.loadImage("/buttons/gear_selected.png");
			x_button = loader.loadImage("/buttons/x.png");
			x_button_selected = loader.loadImage("/buttons/x_selected.png");
			
			menuButton = loader.loadImage("/buttons/Menu.png");
			menuButton_Selected = loader.loadImage("/buttons/MenuAus.png");
			playAgainButton = loader.loadImage("/buttons/PlayAgain.png");
			playAgainButton_Selected = loader.loadImage("/buttons/PlayAgainAus.png");
			resumeButton = loader.loadImage("/buttons/Resume.png");
			resumeButton_Selected = loader.loadImage("/buttons/ResumeAus.png");
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		//Wow so empty :O	
	}
	
	public void render(Graphics g) {
	
		int width = Game.WIDTH;
        int height = Game.HEIGHT;
		int q = 200;
		int j = 64;
        
        
        Font fnt2 = new Font("calibri", 1, 28);
        g.setFont(fnt2);

        Color buttonYellow = new Color(255,168,0);
        
        
        
        //Title Screen
        if(Game.gameStatus == STATUS.StartMenu) {
        	g.drawImage(titleScreen, 0, 0, null);
            	
        	g.setFont(fnt2);
        	g.setColor(buttonYellow);
        	g.drawString("Press SPACE to continue", width / 2 + 200, height / 2 + 150);
        }        
       
        //Main Menu
        else if(Game.gameStatus==STATUS.Menu) {
        	
        	//background
        	g.drawImage(menu_background, 0, 0, null);
        	
        	//Left Button -> if selected make it in colour
        	if(main_leftButton && level > 1) 
        		g.drawImage(texture.button[level + 4], 340, 405, null);
        	else 
        		g.drawImage(texture.button[level - 1], 340, 405, null);
        	
        	//Right Button -> if selected make it in colour
        	if(main_rightButton && level < 5) 
        		g.drawImage(texture.button[level + 16], 830, 405, null);
        	else if (level == 5) 
        		g.drawImage(texture.button[0], 340, 405, null);
        	else 
        		g.drawImage(texture.button[level + 11], 830, 405, null);
        	
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
        	
        	if(quit) g.drawImage(x_button_selected, 1192, 47, null);
        	else g.drawImage(x_button, 1192, 47, null);
        	
        	if(gear) g.drawImage(gear_selected, 1142, 46, null);
        	else g.drawImage(gear_button, 1142, 46, null);
        }        
        
        //Game Over Menu
        else if(Game.gameStatus == STATUS.Dead) {
        	
        	//Play Again Button
        	if(death_retry) {
        		g.drawImage(playAgainButton_Selected, 508, 501, null);
        	}
        	else {
        		g.drawImage(playAgainButton, 508, 501, null);
        	}
        	
        	if(death_backToMenu) {
        		g.drawImage(menuButton_Selected, 216, 501, null);
        	}
        	else {
        		g.drawImage(menuButton, 216, 501, null);
        	}
        }
        
        //End Menu
        else if(Game.gameStatus == STATUS.End) {
        	g.drawImage(menu_background, 0, 0, null);        	
        	g.drawString("You did it!", Game.WIDTH/2 - 100, Game.HEIGHT/2 - 200);
        	g.drawString("You jumped " + jumps + " times", Game.WIDTH/2 - 200, Game.HEIGHT/2 - 100);
        	        	
        	if(final_retry) g.drawImage(playAgainButton_Selected, 508, 501, null);
        	else g.drawImage(playAgainButton, 508, 501, null);
        	
        	//Back To Menu Button
        	if(final_backToMenu) g.drawImage(menuButton_Selected, 216, 501, null);
        	else g.drawImage(menuButton, 216, 501, null);
	        
	        //next Level Button
	        if(final_nextLevel) g.drawImage(resumeButton_Selected, 800, 501, null);
        	else g.drawImage(resumeButton, 800, 501, null);
        }
        
        
	}
	
	
	//////////////////////////////////////////////////////////
	
	//Getter and Setter:
	
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLevel() {
		return level;
	}
	public void setJumps(int jumps) {
		this.jumps = jumps;
	}
	public int getJumps() {
		return jumps;
	}
	public void addJumps() {
		jumps++;
	}
	
	
	public void setMainPlayButton(boolean playButton) {
		Menu.playButton = playButton;
	}
	public boolean getMainPlayButton() {
		return playButton;
	}
	public void setMainLeftLevelButton(boolean leftLevelButton) {
		Menu.main_leftButton = leftLevelButton;
	}
	public boolean getMainLeftLevelButton() {
		return main_leftButton;
	}
	public void setMainRightLevelButton(boolean rightLevelButton) {
		Menu.main_rightButton = rightLevelButton;
	}
	public boolean getMainRightLevelButton() {
		return main_rightButton;
	}
	
	public void set_death_retry(boolean x) {
		Menu.death_retry = x;
	}
	public boolean get_death_retry() {
		return death_retry;
	}
	public void set_death_backToMenu(boolean x) {
		Menu.death_backToMenu = x;
	}
	public boolean get_death_backToMenu() {
		return death_backToMenu;
	}
	
	public void set_final_retry(boolean x) {
		Menu.final_retry = x;
	}
	public boolean get_final_retry() {
		return final_retry;
	}
	public void set_final_backToMenu(boolean x) {
		Menu.final_backToMenu = x;
	}
	public boolean get_final_backToMenu() {
		return final_backToMenu;
	}
	public void set_final_nextLevel(boolean x) {
		Menu.final_nextLevel = x;
	}
	public boolean get_final_nextLevel() {
		return final_nextLevel;
	}
	
	public void setGear(boolean gear) {
		this.gear = gear;
	}
	public boolean getGear() {
		return gear;
	}
	public void setQuit(boolean quit) {
		this.quit = quit;
	}
	public boolean getQuit() {
		return quit;
	}
	
}
/*
 * For navigation between playing to select level, design etc.
 * 
 * @author Robert Kelm
 * @version 08.03.2021
 */

package com.window;

import java.awt.*;

import com.framework.Texture;
import com.window.Game.STATUS;

public class Menu{
		
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
	
	private static int level = 1;
	private int jumps = 0;
	
	private static int progress1 = 0;
	private static int progress2 = 0;
	private static int progress3 = 0;
	private static int progress4 = 0;
	private static int progress5 = 0; 
	
	private static int player = 1;
	

	public Menu(Game game, Controller controller, Texture texture) {
		this.texture = texture;
	}
	
	public void update() {
		//Wow so empty :O	
	}
	
	public void render(Graphics g) {
	
		int width = Game.WIDTH;
        int height = Game.HEIGHT;        
        
        Font fnt2 = new Font("calibri", 1, 28);
        g.setFont(fnt2);

        Color buttonYellow = new Color(255,168,0);
        
        
        
        //Title Screen
        if(Game.gameStatus == STATUS.StartMenu) {
        	g.drawImage(texture.titleScreen, 0, 0, null);
            	
        	g.setFont(fnt2);
        	g.setColor(buttonYellow);
        	g.drawString("Press SPACE to continue", width / 2 + 200, height / 2 + 150);
        }        
       
        //Main Menu
        else if(Game.gameStatus==STATUS.Menu) {
        	
        	//background
        	g.drawImage(texture.menu_background, 0, 0, null);
        	//g.drawImage(logo, 100, 100, null);
        	
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
        		g.drawImage(texture.buttonPlayActive, 503, 532, null);
        		g.setColor(buttonYellow);
        	}
        	else {
        		g.setColor(Color.white);
        		g.drawImage(texture.buttonPlay, 503, 532, null);
        	}
        	g.drawString("  Level: " + (level) + "  ", 575, 580);  	
        	
        	if(quit) g.drawImage(texture.x_button_selected, 1192, 47, null);
        	else g.drawImage(texture.x_button, 1192, 47, null);
        	
        	if(gear) g.drawImage(texture.gear_selected, 1142, 46, null);
        	else g.drawImage(texture.gear_button, 1142, 46, null);
        	
        	//Show amount of Stars of the level depending on progress
        	int temp = 0; 
        	
        	if(level == 1)	temp = progress1;
        	else if(level == 2)	temp = progress2;
        	else if(level == 3)	temp = progress3;
        	else if(level == 4)	temp = progress4;
        	else if(level == 5)	temp = progress5;
        	
        	if(temp >= 99)
    			g.drawImage(texture.stars3, 290, -120, null);
    		else if(temp < 99 && temp >= 60)
    			g.drawImage(texture.stars2, 290, -120, null);
    		else if(temp  < 59 && temp > 30)
    			g.drawImage(texture.stars1, 290, -120, null);
    		else
    			g.drawImage(texture.stars0, 290, -120, null);
        	
        	
            g.drawImage(texture.menuPlayer[player - 1], 598, 435, null);	
        	
        	
        }        
        
        //Game Over Menu
        else if(Game.gameStatus == STATUS.Dead) {
        	
        	g.setColor(Color.BLACK);
        	g.fillRect(0, 0, width, height);
        	
        	g.drawImage(texture.gameOver, 474, 175, null);	
        	
        	//Play Again Button
        	if(death_retry) {
        		g.drawImage(texture.playAgainButton_Selected, 656, 501, null);
        	}
        	else {
        		g.drawImage(texture.playAgainButton, 656, 501, null);
        	}
        	
        	if(death_backToMenu) {
        		g.drawImage(texture.menuButton_Selected, 327, 501, null);
        	}
        	else {
        		g.drawImage(texture.menuButton, 327, 501, null);
        	}
        }
        
        //End Menu
        else if(Game.gameStatus == STATUS.End) {
        	g.drawImage(texture.menu_background, 0, 0, null);        	
        	g.drawImage(texture.congrats, 290, -75, null);
        	g.drawImage(texture.stars3, 290, -65, null);
        	
        	g.drawString("Jumps: " + jumps, 30, 485);
        	        	
        	if(final_retry) g.drawImage(texture.playAgainButton_Selected, 491, 500, null);
        	else g.drawImage(texture.playAgainButton, 491, 500, null);
        	
        	//Back To Menu Button
        	if(final_backToMenu) g.drawImage(texture.menuButton_Selected, 162, 500, null);
        	else g.drawImage(texture.menuButton, 162, 500, null);
	        
	        //next Level Button
	        if(final_nextLevel) g.drawImage(texture.resumeButton_Selected, 822, 500, null);
        	else g.drawImage(texture.resumeButton, 822, 500, null);
        }
        
        
	}
	
	public static void setProgress(int progress) {
		
		if(level == 1 && progress1 < progress)
			progress1 = progress;
		
		else if(level == 2 && progress2 < progress)
			progress2 = progress;
		
		else if(level == 3 && progress3 < progress)
			progress3 = progress;
		
		else if(level == 4 && progress4 < progress)
			progress4 = progress;
		
		else if(level == 5 && progress5 < progress)
			progress5 = progress;
		
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
	public int getPlayer() {
		return player;
	}
	public void setPlayer(int player) {
		this.player = player;
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
		Menu.gear = gear;
	}
	public boolean getGear() {
		return gear;
	}
	public void setQuit(boolean quit) {
		Menu.quit = quit;
	}
	public boolean getQuit() {
		return quit;
	}
	
}
/*
 * load textures from files and SpriteSheets and 
 * assign them to their objects
 * 
 * @author Robert Kelm
 * @version 08.02.2021
 */

package com.framework;

import java.awt.image.BufferedImage;

import com.framework.ImageLoader;

public class Texture {
	
	BufferedImage image;
	SpriteSheet bs, ps, buttonSheet, spikeSheet; 
	
	public BufferedImage gameOver = null;
	public BufferedImage congrats = null;
	
	//Buttons
	public BufferedImage buttonPlay = null, buttonPlayActive = null;
	public BufferedImage gear_button = null, gear_selected = null, x_button = null, x_button_selected = null; 
	public BufferedImage menuButton = null, menuButton_Selected = null;
	public BufferedImage playAgainButton = null, playAgainButton_Selected = null;
	public BufferedImage resumeButton = null, resumeButton_Selected = null;
	
	public BufferedImage stars1 = null, stars2 = null, stars3 = null;
	
	public BufferedImage menu_background = null;
	public BufferedImage titleScreen = null;
	
	
	//Graphic Sheets
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage button_sheet = null;
	private BufferedImage spike_sheet = null;

	//array to store the images in
	public BufferedImage[] block = new BufferedImage[32]; //array has to have the size of the amount of blocks
	public BufferedImage[] player = new BufferedImage[8];
	public BufferedImage[] button = new BufferedImage[21];
	public BufferedImage[] spike = new BufferedImage[16];
	
	public Texture() {
		
		loadTextures();
		
		getTextures();
	}
	
	public void loadTextures() {
		
		ImageLoader loader = new ImageLoader();
		
		//load the sheets from the files
		try {
			
			//load Spritesheet images
			block_sheet = loader.loadImage("/texture/block_sheet.png");
			player_sheet = loader.loadImage("/texture/player_sheet.png");
			button_sheet = loader.loadImage("/buttons/button_sheet.png");
			spike_sheet = loader.loadImage("/texture/spike_sheet.png");
		
			titleScreen = loader.loadImage("/TitleScreen.png");
			menu_background = loader.loadImage("/background/menu_background.png");
			
			buttonPlay = loader.loadImage("/buttons/Play.png");
			buttonPlayActive = loader.loadImage("/buttons/PlayActive.png");
			
			gear_button = loader.loadImage("/buttons/gear.png");
			gear_selected = loader.loadImage("/buttons/gear_selected.png");
			x_button = loader.loadImage("/buttons/x.png");
			x_button_selected = loader.loadImage("/buttons/x_selected.png");
			
			menuButton = loader.loadImage("/buttons/Back.png");
			menuButton_Selected = loader.loadImage("/buttons/BackAus.png");
			playAgainButton = loader.loadImage("/buttons/PlayAgain.png");
			playAgainButton_Selected = loader.loadImage("/buttons/PlayAgainAus.png");
			resumeButton = loader.loadImage("/buttons/NextLevel.png");
			resumeButton_Selected = loader.loadImage("/buttons/NextLevelAus.png");
			
			gameOver = loader.loadImage("/other/GameOver!.png");
			congrats = loader.loadImage("/other/Congrats.png");		
			
			stars1 = loader.loadImage("/other/1Stars.png");
			stars2 = loader.loadImage("/other/2Stars.png");
			stars3 = loader.loadImage("/other/3Stars.png");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		buttonSheet = new SpriteSheet(button_sheet);
		spikeSheet = new SpriteSheet(spike_sheet);
	}
	
	//assign the images from the sheets to the arrays
	public void getTextures() {
		block[0] = bs.grabImage(1, 1, 64, 64);	//Green Block
		block[1] = bs.grabImage(2, 1, 64, 64);	//Red-Black-Gradient
		block[2] = bs.grabImage(3, 1, 64, 64);	//Blue Cement
		block[3] = bs.grabImage(4, 1, 64, 64);	//Magenta Cement
		block[4] = bs.grabImage(5, 1, 64, 64);	//Lime Cement
		block[5] = bs.grabImage(6, 1, 64, 64);	//Yellow Cement
		block[6] = bs.grabImage(7, 1, 64, 64);	//Water
		block[7] = bs.grabImage(8, 1, 64, 64);	//Top Water
		block[8] = bs.grabImage(1, 2, 64, 64);	//Grass
		block[9] = bs.grabImage(2, 2, 64, 64);	//Dirt
		block[10] = bs.grabImage(3, 2, 64, 64);	//Plank
		block[11] = bs.grabImage(4, 2, 64, 64);	//Plank
		block[12] = bs.grabImage(5, 2, 64, 64);	//Oak Log
		block[13] = bs.grabImage(6, 2, 64, 64);	//Birch Log
		block[14] = bs.grabImage(7, 2, 64, 64);	//Leaves
		block[15] = bs.grabImage(8, 2, 64, 64);	//leaves
		block[16] = bs.grabImage(1, 3, 64, 64);	//Cobblestone
		block[17] = bs.grabImage(2, 3, 64, 64);	//Stone
		block[18] = bs.grabImage(3, 3, 64, 64);	//Diamonds
		block[19] = bs.grabImage(4, 3, 64, 64);	//Emeralds
		block[20] = bs.grabImage(5, 3, 64, 64);	//Iron
		block[21] = bs.grabImage(6, 3, 64, 64);	//Gold
		block[22] = bs.grabImage(7, 3, 64, 64);	//Stone Brick
		block[23] = bs.grabImage(8, 3, 64, 64); //Mossy Stone Brick
		block[24] = bs.grabImage(1, 4, 64, 64); //Netherack
		block[25] = bs.grabImage(2, 4, 64, 64); //Obsidian
		block[26] = bs.grabImage(3, 4, 64, 64); //Nether Brick
		block[27] = bs.grabImage(4, 4, 64, 64);	//Lapis 
		block[28] = bs.grabImage(5, 4, 64, 64); //Gravel
		block[29] = bs.grabImage(6, 4, 64, 64);	//Magma
		block[30] = bs.grabImage(7, 4, 64, 64); //Lava
		block[31] = bs.grabImage(8, 4, 64, 64); //Lava Top
		
		//Player Skin
		player[0] = ps.grabImage(1, 1, 64, 64);
		player[1] = ps.grabImage(2, 1, 64, 64);
		player[2] = ps.grabImage(3, 1, 64, 64);
		player[3] = ps.grabImage(4, 1, 64, 64);
		player[4] = ps.grabImage(5, 1, 64, 64);
		player[5] = ps.grabImage(6, 1, 64, 64);
		player[6] = ps.grabImage(7, 1, 64, 64);
		player[7] = ps.grabImage(8, 1, 64, 64);	//Creeper
		
		//Buttons
		button[0] = null;
		button[1] = buttonSheet.grabImage(1, 1, 132, 132);
		button[2] = buttonSheet.grabImage(2, 1, 132, 132);
		button[3] = buttonSheet.grabImage(3, 1, 132, 132);
		button[4] = buttonSheet.grabImage(4, 1, 132, 132);
		button[5] = buttonSheet.grabImage(5, 1, 132, 132);
		button[6] = buttonSheet.grabImage(1, 2, 132, 132);
		button[7] = buttonSheet.grabImage(2, 2, 132, 132);
		button[8] = buttonSheet.grabImage(3, 2, 132, 132);
		button[9] = buttonSheet.grabImage(4, 2, 132, 132);
		button[10] = buttonSheet.grabImage(5, 2, 132, 132);
		button[11] = buttonSheet.grabImage(1, 3, 132, 132);
		button[12] = buttonSheet.grabImage(2, 3, 132, 132);
		button[13] = buttonSheet.grabImage(3, 3, 132, 132);
		button[14] = buttonSheet.grabImage(4, 3, 132, 132);
		button[15] = buttonSheet.grabImage(5, 3, 132, 132);
		button[16] = buttonSheet.grabImage(1, 4, 132, 132);
		button[17] = buttonSheet.grabImage(2, 4, 132, 132);
		button[18] = buttonSheet.grabImage(3, 4, 132, 132);
		button[19] = buttonSheet.grabImage(4, 4, 132, 132);
		button[20] = buttonSheet.grabImage(5, 4, 132, 132);
		
		spike[0] = spikeSheet.grabImage(1, 1, 64, 64);
		spike[1] = spikeSheet.grabImage(2, 1, 64, 64);
		spike[2] = spikeSheet.grabImage(3, 1, 64, 64);
		spike[3] = spikeSheet.grabImage(4, 1, 64, 64);
		spike[4] = spikeSheet.grabImage(1, 2, 64, 64);
		spike[5] = spikeSheet.grabImage(2, 2, 64, 64);
		spike[6] = spikeSheet.grabImage(3, 2, 64, 64);
		spike[7] = spikeSheet.grabImage(4, 2, 64, 64);
		spike[8] = spikeSheet.grabImage(1, 3, 64, 64);
		spike[9] = spikeSheet.grabImage(2, 3, 64, 64);
		spike[10] = spikeSheet.grabImage(3, 3, 64, 64);
		spike[11] = spikeSheet.grabImage(4, 3, 64, 64);
		spike[12] = spikeSheet.grabImage(1, 4, 64, 64);
		spike[13] = spikeSheet.grabImage(2, 4, 64, 64);
		spike[14] = spikeSheet.grabImage(3, 4, 64, 64);
		spike[15] = spikeSheet.grabImage(4, 4, 64, 64);
	}	
}

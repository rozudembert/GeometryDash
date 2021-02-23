package com.framework;

import java.awt.image.BufferedImage;

import com.window.ImageLoader;

public class Texture {
	
	BufferedImage image;
	
	SpriteSheet bs, ps; //Sprite for Block Sheet (bs) and Player Sheet (ps)
	
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	
	//array to store the images in
	public BufferedImage[] block = new BufferedImage[2]; //array has to have the size of different blocks
	public BufferedImage[] player = new BufferedImage[3];
	
	public Texture() {
		
		ImageLoader loader = new ImageLoader();
		
		//load the sheets from our files
		try {
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet.png");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		
		getTextures();
	}
	
	//assign the images from the sheets to the arrays
	public void getTextures() {
		block[0] = bs.grabImage(1, 1, 64, 64);
		block[1] = bs.grabImage(2, 1, 64, 64);
		player[0] = ps.grabImage(1, 1, 64, 64);
		player[1] = ps.grabImage(2, 1, 64, 64);
		player[2] = ps.grabImage(3, 1, 64, 64);
	}	
}

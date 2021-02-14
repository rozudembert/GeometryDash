package com.framework;

import java.awt.image.BufferedImage;

import com.window.ImageLoader;

public class Texture {
	
	BufferedImage image;
	
	private BufferedImage block_sheet = null;
	
	//array to store the images in
	public BufferedImage[] block = new BufferedImage[2];
	
	public Texture() {
		
		ImageLoader loader = new ImageLoader();
		try {
			block_sheet = loader.loadImage("/block_sheet.png");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		image = block_sheet;
		
		getTextures();
	}
	
	//assigns the images from the ssheets
	public void getTextures() {
		block[0] = grabImage(1, 1, 64, 64);
		block[1] = grabImage(2, 1, 64, 64);
	}
	
	//method to grab the image out of our sheets
	public BufferedImage grabImage (int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col*width) - width, (row * height) - height, width, height);
		return img;
	}
}

/*
 * Used to extract images from the SpriteSheet
 */

package com.framework;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	
	//to grab any image from the spritesheets
	public BufferedImage grabImage(int column, int row, int width, int height) {
		BufferedImage img = image.getSubimage((column*width) - width, (row * height) - height, width, height);
		return img;
	}
	
}

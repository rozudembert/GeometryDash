/*
 * Class to load images from file system
 * to use for textures
 * 
 * @author Robert Kelm
 * @version 12.02.2021
 */

package com.framework;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	private BufferedImage image;
	
	public BufferedImage loadImage(String path) {
		
		//load the image of any given path in our file system
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}

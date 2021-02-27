package com.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import com.framework.GameObject;
import com.framework.ObjectId;
import com.objects.Block;
import com.objects.Goal;
import com.objects.Player;
import com.objects.Spike;

public class Controller {

	//Creates LinkedList with every GameObject in the game
	public LinkedList <GameObject> object = new LinkedList<GameObject>();
	
	private Camera cam;
	private BufferedImage level1 = null, level2 = null, level3 = null, level4 = null, level5 = null;
	public int level = 1;
	
	
	public Controller(Camera cam) {
		this.cam = cam;
		
		//load level image from file system
		ImageLoader loader = new ImageLoader();
		level1 = loader.loadImage("/level_1.png");
		level2 = loader.loadImage("/level_2.png");
		//level3 = loader.loadImage("/level_3.png");
		//level4 = loader.loadImage("/level_4.png");
		//level5 = loader.loadImage("/level_5.png");
	}
	
	//Update List
	public void update() {
		for(int i= 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.update(object);
		}
	}
	
	//Render Lists
	public void render(Graphics graphics) {
		for(int i= 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(graphics);
		}
	}
	
	public void startLevel(int level) {
		
		clearLevel();
		
		if(level == 1) loadLevel(level1);
		else if(level == 2) loadLevel(level2);
		else if(level == 3) loadLevel(level1);
		else if(level == 4) loadLevel(level1);
		else if(level == 5) loadLevel(level1);
	}
	
	//Recieves an image as input and converts it into GameObjects
	public void loadLevel(BufferedImage image) {
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		//To go through every pixel in the image
		for(int xx = 0; xx < width; xx++) {
			for(int yy = 0; yy < height; yy++) {
				
				//get RGB value per Pixel
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				//add Object depending on color code
				if(red == 255 && green == 255 && blue == 255)
					addObject(new Block(xx*64, yy*64, 0, ObjectId.Block));
				if(red == 0 && green == 0 && blue == 255)
					addObject(new Player(xx*64, yy*64, this, 0, cam, ObjectId.Player));
				if(red == 255 && green == 255 && blue == 0)
					addObject(new Goal(xx*64, yy*64, ObjectId.Goal));
			}
		}		
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void switchLevel(int level) {
		clearLevel();
		cam.setX(0);
		
		switch(level) {
		
			case 1:
				loadLevel(level1);				
				break;		
			case 2: 
				loadLevel(level2);				
				break;
			case 3: 
				loadLevel(level3);				
				break;
			case 4: 
				loadLevel(level4);				
				break;
			case 5: 
				loadLevel(level5);				
				break;
		}
	}
	
	private void clearLevel() {
		object.clear();
	}
	
	//add Objects in the world	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
		
	//remove Objects from the world
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}

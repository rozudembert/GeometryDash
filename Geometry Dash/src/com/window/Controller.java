package com.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import com.framework.GameObject;
import com.framework.ObjectId;
import com.objects.Block;
import com.objects.Player;
import com.objects.Spike;

public class Controller {

	//Creates LinkedList with every GameObject in the game
	public static LinkedList <GameObject> object = new LinkedList<GameObject>();
	
	private Camera cam;
	private BufferedImage level1 = null, level2 = null, level3 = null, level4 = null, level5 = null;
	public int level = 1;
	
	
	public Controller(Camera cam) {
		this.cam = cam;
		
		//load level image from file system
		ImageLoader loader = new ImageLoader();
		level1 = loader.loadImage("/level/level_1.png");
		level2 = loader.loadImage("/level/level_2.png");
		level3 = loader.loadImage("/level/level_3.png");
		level4 = loader.loadImage("/level/level_4.png");
		level5 = loader.loadImage("/level/level_5.png");
	}
	
	//Update List
	public void update() {
		float playerX = 0;
		
		for(int i= 0; i < object.size(); i++) {			
			GameObject tempObject = object.get(i);
			
			tempObject.update(object);
			
			//Remove blocks if they are already passed by the player
			if(tempObject.getId() == ObjectId.Player) {
				playerX = tempObject.getX();
			}			
			if(tempObject.getId() == ObjectId.Block) {
				if(tempObject.getX() < playerX - 550) {
					removeObject(tempObject);
				}					
			}
			
			//If Block is too far away from the player dont render it
			if(tempObject.getId() == ObjectId.Block) {
				if(tempObject.getX() > playerX + 900) {
					tempObject.setVisible(false);
				}
				else tempObject.setVisible(true);				
			}
			
			
			
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
		else if(level == 3) loadLevel(level3);
		else if(level == 4) loadLevel(level4);
		else if(level == 5) loadLevel(level5);
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
				
				//add objects depending on rgb color 
				//Player
				if(red == 0 && green == 0 && blue == 255) addObject(new Player(xx*64, yy*64, this, 7, cam, ObjectId.Player));
				
				//Blocks
				if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*64, yy*64, 0, ObjectId.Block));
				//if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*64, yy*64, 1, ObjectId.Block));
				//if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*64, yy*64, 2, ObjectId.Block));
				//if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*64, yy*64, 3, ObjectId.Block));
				//if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*64, yy*64, 4, ObjectId.Block));
				//if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*64, yy*64, 5, ObjectId.Block));
				//if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*64, yy*64, 6, ObjectId.Block));
				//if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*64, yy*64, 7, ObjectId.Block));
				//if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*64, yy*64, 8, ObjectId.Block));
				if(red == 0 && green == 110 && blue == 47) addObject(new Block(xx*64, yy*64, 8, ObjectId.Block));
				if(red == 163 && green == 72 && blue == 47) addObject(new Block(xx*64, yy*64, 9, ObjectId.Block));
				//if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*64, yy*64, 10, ObjectId.Block));
				//if(red == 25 blue == 255) addObject(new Block(xx*64, yy*64, 11, ObjectId.Block));
				//if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*64, yy*64, 12, ObjectId.Block));
				if(red == 132 && green == 64 && blue == 64) addObject(new Block(xx*64, yy*64, 13, ObjectId.Block));
				//if(red == 255 && green == 5 && blue == 255) addObject(new Block(xx*64, yy*64, 14, ObjectId.Block));
				//if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*64, yy*64, 15, ObjectId.Block));
				if(red == 64 && green == 64 && blue == 64) addObject(new Block(xx*64, yy*64, 16, ObjectId.Block));
				if(red == 128 && green == 128 && blue == 128) addObject(new Block(xx*64, yy*64, 17, ObjectId.Block));
				if(red == 0 && green == 255 && blue == 255) addObject(new Block(xx*64, yy*64, 18, ObjectId.Block));
				if(red == 0 && green == 255 && blue == 0) addObject(new Block(xx*64, yy*64, 19, ObjectId.Block));
				if(red == 255 && green == 255 && blue == 0) addObject(new Block(xx*64, yy*64, 20, ObjectId.Block));
				if(red == 0 && green == 31 && blue == 0) addObject(new Block(xx*64, yy*64, 21, ObjectId.Block));
				if(red == 255 && green == 0 && blue == 0) addObject(new Block(xx*64, yy*64, 22, ObjectId.Block));
				//if(red == 0 && green == 31 && blue == 0) addObject(new Block(xx*64, yy*64, 23, ObjectId.Block));
				
				
				
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
	
	public static void clearLevel() {
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

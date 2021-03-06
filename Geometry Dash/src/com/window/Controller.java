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

	//LinkedList with every GameObject
	public static LinkedList <GameObject> object = new LinkedList<GameObject>();
	
	private Camera cam;
	private BufferedImage level1 = null, level2 = null, level3 = null, level4 = null, level5 = null;
	private BufferedImage activeLevel = null;
	
	private int level = 1;
	private int renderDistance;
	
	public Controller(Camera cam) {
		this.cam = cam;
		
		//load level images from file system
		ImageLoader loader = new ImageLoader();
		level1 = loader.loadImage("/level/level_1.png");
		level2 = loader.loadImage("/level/level_2.png");
		level3 = loader.loadImage("/level/level_3.png");
		level4 = loader.loadImage("/level/level_4.png");
		level5 = loader.loadImage("/level/level_5.png");
	}
	
	public void update() {
		int playerX = 0;
		
		for(int i= 0; i < object.size(); i++) {			
			GameObject tempObject = object.get(i);
			
			tempObject.update(object);
			
			if(tempObject.getId() == ObjectId.Player) playerX = (int)tempObject.getX();			
			
			//remove block if passed by the player
			if(tempObject.getId() == ObjectId.Block && tempObject.getX() < playerX - 550) {
				removeObject(tempObject);					
			}
		}			
		
		//add blocks shortly before the player passes them
		if(playerX > 800 + renderDistance && playerX < 863 + renderDistance) {
			
			loadLevel(activeLevel, (int)playerX);
			renderDistance = renderDistance + 64;		
		
		}
	}
	
	/*
	 * recieve image of the level as input and convert it into GameObjects
	 * only load the column of blocks 15 blocks away from the player
	 */
	public void loadLevel(BufferedImage image, int playerX) {
		
		int height = image.getHeight();
		int xx = (playerX + 1000)/64;
		
		for(int yy = 0; yy < height; yy++) {
			
			int pixel = image.getRGB(xx, yy);
			int red = (pixel >> 16) & 0xff;
			int green = (pixel >> 8) & 0xff;
			int blue = (pixel) & 0xff;
			
			loadBlocks(red, green, blue, xx, yy);
		}
	}
	
	
	
	//recieve image of the level as input and convert it into GameObjects
	//only for the first 35 blocks 
	public void loadLevel_Start(BufferedImage image) {
		
		int height = image.getHeight();
		
		//go through every pixel in the image
		for(int xx = 0; xx < 2240/64; xx++) {
			for(int yy = 0; yy < height; yy++) {
				
				//get RGB value per Pixel
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				//add Player
				if(red == 0 && green == 0 && blue == 255) addObject(new Player(xx*64, yy*64, this, 7, cam, ObjectId.Player));
				
				//add Blocks
				loadBlocks(red, green, blue, xx, yy);
			}
		}		
	}
	
	//add block depending on rgb color
	public void loadBlocks(int r, int g, int b, int xx, int yy) {

		
		if(r == 255 && g == 255 && b == 255) addObject(new Block(xx*64, yy*64, 0, ObjectId.Block));
		//else if(r == ? && g == ? && b == ?) addObject(new Block(xx*64, yy*64, 1, ObjectId.Block));
		//else if(r == ? && g == ? && b == ?) addObject(new Block(xx*64, yy*64, 2, ObjectId.Block));
		//else if(r == ? && g == ? && b == ?) addObject(new Block(xx*64, yy*64, 3, ObjectId.Block));
		//else if(r == ? && g == ? && b == ?) addObject(new Block(xx*64, yy*64, 4, ObjectId.Block));
		//else if(r == ? && g == ? && b == ?) addObject(new Block(xx*64, yy*64, 5, ObjectId.Block));
		//else if(r == ? && g == ? && b == ?) addObject(new Block(xx*64, yy*64, 6, ObjectId.Block));
		//else if(r == ? && g == ? && b == ?) addObject(new Block(xx*64, yy*64, 7, ObjectId.Block));
		else if(r == 0 && g == 110 && b == 47) addObject(new Block(xx*64, yy*64, 8, ObjectId.Block));
		else if(r == 163 && g == 72 && b == 47) addObject(new Block(xx*64, yy*64, 9, ObjectId.Block));
		//else if(r == ? && g == ? && b == ?) addObject(new Block(xx*64, yy*64, 10, ObjectId.Block));
		//else if(r == ? && g == ? && b == ?) addObject(new Block(xx*64, yy*64, 11, ObjectId.Block));
		//else if(r == ? && g == ? && b == ?) addObject(new Block(xx*64, yy*64, 12, ObjectId.Block));
		else if(r == 132 && g == 64 && b == 64) addObject(new Block(xx*64, yy*64, 13, ObjectId.Block));
		//else if(r == ? && g == ? && b == ?) addObject(new Block(xx*64, yy*64, 14, ObjectId.Block));
		//else if(r == ? && g == ? && b == ?) addObject(new Block(xx*64, yy*64, 15, ObjectId.Block));
		else if(r == 64 && g == 64 && b == 64) addObject(new Block(xx*64, yy*64, 16, ObjectId.Block));
		else if(r == 128 && g == 128 && b == 128) addObject(new Block(xx*64, yy*64, 17, ObjectId.Block));
		else if(r == 0 && g == 255 && b == 255) addObject(new Block(xx*64, yy*64, 18, ObjectId.Block));
		else if(r == 0 && g == 255 && b == 0) addObject(new Block(xx*64, yy*64, 19, ObjectId.Block));
		else if(r == 255 && g == 255 && b == 0) addObject(new Block(xx*64, yy*64, 20, ObjectId.Block));
		else if(r == 0 && g == 31 && b == 0) addObject(new Block(xx*64, yy*64, 21, ObjectId.Block));
		else if(r == 255 && g == 0 && b == 0) addObject(new Block(xx*64, yy*64, 22, ObjectId.Block));
		//else if(r == ? && g == ? && b == ?) addObject(new Block(xx*64, yy*64, 23, ObjectId.Block));
		
	}
	
	
	//select correct level-image and call loadLevel method
	public void startLevel(int level) {
		
		clearLevel();
		
		switch(level) {
		case 1: 
			activeLevel = level1;
			break;
		case 2: 
			activeLevel = level2;
			break;
		case 3: 
			activeLevel = level3;
			break;
		case 4: 
			activeLevel = level4;
			break;
		case 5: 
			activeLevel = level5;
			break;
		}
		
		loadLevel_Start(activeLevel);
		
	}
	
	//render the graphics of the objects
	public void render(Graphics graphics) {
		for(int i= 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(graphics);
		}
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	//delete every object in the world
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
	
	public void setRenderDistance(int renderDistance) {
		this.renderDistance = renderDistance;
	}
	
	public int getRenderDistance() {
		return renderDistance;
	}
}

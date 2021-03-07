package com.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import com.framework.GameObject;
import com.framework.ObjectId;
import com.framework.ImageLoader;
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
		int type = 100;
		
		if(r == 255 && g == 255 && b == 255) type = 0; //Green Block
		else if(r == 99 && g == 19 && b == 19) type = 1; //Red-Black-Gradient
		else if(r == 37 && g == 138 && b == 200) type = 2; //Blue Cement
		else if(r == 169 && g == 48 && b == 159) type = 3; //Magenta Cement
		else if(r == 95 && g == 170 && b == 25) type = 4; //Lime Cement
		else if(r == 239 && g == 174 && b == 21) type = 5; //Yellow Cement
		else if(r == 99 && g == 127 && b == 175) type = 6; //Water
		else if(r == 117 && g == 172 && b == 219) type = 7; //Top Water
		else if(r == 0 && g == 110 && b == 47) type = 8; //Grass
		else if(r == 163 && g == 72 && b == 47) type = 9; //Dirt
		else if(r == 112 && g == 89 && b == 54) type = 10; //Plank
		else if(r == 58 && g == 46 && b == 29) type = 11; //Plank
		else if(r == 132 && g == 64 && b == 64) type = 12; //Oak Log
		else if(r == 179 && g == 183 && b == 169) type = 13; //Birch Log
		else if(r == 0 && g == 31 && b == 0) type = 14; //Leaves
		else if(r == 104 && g == 135 && b == 50) type = 15; //leaves
		else if(r == 64 && g == 64 && b == 64) type = 16; //Cobblestone
		else if(r == 128 && g == 128 && b == 128) type = 17; //Stone
		else if(r == 0 && g == 255 && b == 255) type = 18; //Diamonds
		else if(r == 0 && g == 255 && b == 0) type = 19; //Emeralds
		else if(r == 205 && g == 174 && b == 128) type = 20; //Iron
		else if(r == 255 && g == 255 && b == 0) type = 21; //Gold
		else if(r == 137 && g == 137 && b == 137) type = 22; //Stone Brick
		else if(r == 69 && g == 81 && b == 57) type = 23; //Mossy Stone Brick
		else if(r == 124 && g == 32 && b == 32) type = 24; //Netherack
		else if(r == 65 && g == 56 && b == 90) type = 25; //Obsidian
		else if(r == 56 && g == 26 && b == 31) type = 26; //Nether Brick
		else if(r == 48 && g == 64 && b == 190) type = 27; //Lapis 
		else if(r == 176 && g == 158 && b == 158) type = 28; //Gravel
		else if(r == 255 && g == 156 && b == 0) type = 29; //Magma
		else if(r == 215 && g == 123 && b == 45) type = 30; //Lava
		else if(r == 224 && g == 154 && b == 56) type = 31; //Lava Top
		
		if(type != 100)
			addObject(new Block(xx*64, yy*64, type, ObjectId.Block));
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

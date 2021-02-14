package com.window;

import java.awt.Graphics;
import java.util.LinkedList;
import com.framework.GameObject;
import com.framework.ObjectId;
import com.objects.Block;
import com.objects.Spike;

public class Controller {
	
	//Creates LinkedList with every GameObject in the game
	public LinkedList <GameObject> object = new LinkedList<GameObject>();
	
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
	
	//add Objects in the world	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
		
	//remove Objects from the world
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	//to create all the blocks in the game
	public void createLevel() {
		
		//line on the right side
		for(int yy = 0; yy < Game.HEIGHT + 64; yy += 64)
			addObject(new Block(00, yy, 1, ObjectId.Block));		
		
		//low ground line
		for(int xx = 0; xx < Game.WIDTH *4 ; xx += 128)
			addObject(new Block(xx, Game.HEIGHT - 160, 0, ObjectId.Block));
		for(int xx = 64; xx < Game.WIDTH *4 ; xx += 128)
			addObject(new Block(xx, Game.HEIGHT - 160, 1, ObjectId.Block));
		
	}
}

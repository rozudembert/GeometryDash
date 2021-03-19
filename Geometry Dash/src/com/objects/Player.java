/*
 * This is the player which can be controlled with the keys
 * 
 * @author Robert Kelm
 * @version 08.02.2021
 */

package com.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.awt.geom.Area;

import com.framework.GameObject;
import com.framework.ObjectId;
import com.framework.Texture;
import com.window.Camera;
import com.window.Controller;
import com.window.Game;


public class Player extends GameObject{
	
	//width and height
	public float width = 64, height = 64;
	
	//gravity settings
	private float GRAVITY = 1.6f;
	private float SPEED = 12;
	private final float MAX_FALLINGSPEED = 15f;
	
	//make the players hitbox visible
	private boolean showHitbox = true;
	
	Texture texture = Game.getInstance();
	
	private Controller controller;
	private Camera cam;
	Game game;
	
	int skin; //what kind of the player has
	
	
	public Player(float x, float y, Controller controller, int skin, Camera cam, ObjectId id) {
		super(x, y, id);
		this.controller = controller;
		this.skin = skin;
		this.cam = cam;
	}
	
	
	public void update(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		//gravity
		if(falling || jumping) {
			velY += GRAVITY;
			
			if(velY > MAX_FALLINGSPEED) {
				velY = MAX_FALLINGSPEED;
			}
		}		
		
		move(object);
		collision(object);	
	}
	
	//player is moving evenly to the right
	public void move(LinkedList<GameObject> object) {
		
		for(int i = 0; i < Controller.object.size(); i++) {
			GameObject tempObject = Controller.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player && Game.getGodMode() == false) {
				//Adjust player speed
				tempObject.setVelX(SPEED);
			}
		}	
	}
	
	//collision method to detect collision with any given GameObject
	public void collision(LinkedList<GameObject> object) {
		for(int i = 0; i < Controller.object.size(); i++) {
			GameObject tempObject = Controller.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block) {
				
				//collision with the top -> player dies
				if(getBorderTop().intersects(tempObject.getBorder())){
					if(Game.getGodMode()) {
						y = tempObject.getY() + height/2;
						velY = 0;
					}
					else
						death();
				}
				
				//collision with the ground
				if(!getBorder().intersects(tempObject.getBorder()) || !getBorder().intersects(tempObject.getExtendedBorder())) {
					falling = true; //Jumping is only possible if Player is on the ground
				}				
				else if(getBorder().intersects(tempObject.getBorder()) || getBorder().intersects(tempObject.getExtendedBorder())){
					y = tempObject.getY() - 64;
					velY = 0;
					falling = false;
					jumping = false;
				} 
				
				
				System.out.println(falling);
				
				//right side collision
				if(getBorderRight().intersects(tempObject.getBorder())){
					if(Game.getGodMode())
						x = tempObject.getX() - width;
					else
						death();
				}
				
				//left side collision
				if(getBorderLeft().intersects(tempObject.getBorder())){
					if(Game.getGodMode())
						x = tempObject.getX() + width +2;
				} 				
			}
					
			if(tempObject.getId() == ObjectId.Spike) {
				if(tempObject.getBorderPoly().intersects(getBorderRight()) || tempObject.getBorderPoly().intersects(getBorder())) {
					if(!Game.getGodMode())
						death();
				}
				
			}
			
			//player reaches the end of the level
			if(tempObject.getId() == ObjectId.EndPortal) {
				if(getBorderRight().intersects(tempObject.getBorder())) {
					end();
					Controller.clearLevel();
					Game.endMenu();
				}
			}
		}
	}
	
	//remove player from the game reset level and camera
	public void death() {
		Game.playerDeath();
		cam.setStart(true);
		controller.setRenderDistance(0);
		controller.removeObject(this);
	}
	
	public void end() {
		Game.endMenu();
		cam.setStart(true);
		controller.setRenderDistance(0);
		controller.removeObject(this);
	}
	
	
	//render the graphics
	public void render(Graphics graphics) {
		
		graphics.drawImage(texture.player[skin], (int)x, (int)y, null);
				
		//given graphics get casted in 2d graphics to use for collision
		Graphics2D g2d = (Graphics2D) graphics; 
		
		//if enabled the players hitbox will be shown
		if(showHitbox == true) {
			graphics.setColor(Color.RED);
			g2d.draw(getBorder());
			g2d.draw(getBorderRight());
			g2d.draw(getBorderLeft());
			g2d.draw(getBorderTop());
		}
	}
	
	//the following methods create rectangles inside the player to use in collision detection
	public Rectangle getBorder() {
		return new Rectangle((int)x +5, (int)y + (int)(height/2), (int)width/2 + 20, (int)height/2);
	}	
	public Rectangle getBorderTop() {
		return new Rectangle((int)x + 5 , (int)y, (int)width/2 + 20, (int)height/2);
	}	
	public Rectangle getBorderRight() {
		return new Rectangle((int)x + (int)width - 5, (int)y + 5, (int)5, (int)height - 10);
	}
	public Rectangle getBorderLeft() {
		return new Rectangle((int)x, (int)y + 5, (int)5, (int)height - 10);
	}


	@Override
	public Polygon getBorderPoly() {
		return null;
	}


	@Override
	public Rectangle getExtendedBorder() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//RECTANGLE/POLYGON COLLISION
	
	
	
	
	
}

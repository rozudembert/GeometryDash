/*
 * This is the player which can be controlled with the keys
 */

package com.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.framework.GameObject;
import com.framework.ObjectId;
import com.framework.Texture;
import com.window.Camera;
import com.window.Controller;
import com.window.Game;

public class Player extends GameObject{
	
	//players width and height
	public float width = 64, height = 64;
	
	//Gravity Settings
	private float GRAVITY = 0.5f;
	private final float MAX_FALLINGSPEED = 10f;
	
	//Visible Player hitbox can be enabled here
	private boolean showHitbox = false;
	
	Texture texture = Game.getInstance();
	
	private Controller controller;
	private Camera cam;
	int skin; //What Skin the player is wearing 
	
	public Player(float x, float y, Controller controller, int skin, Camera cam, ObjectId id) {
		super(x, y, id);
		this.controller = controller;
		this.skin = skin;
		this.cam = cam;
	}
	
	
	public void update(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(falling || jumping) {
			velY += GRAVITY;
			
			if(velY > MAX_FALLINGSPEED) {
				velY = MAX_FALLINGSPEED;
			}
		}		
		
		move(object);
		collision(object);
	}
	
	//
	public void move(LinkedList<GameObject> object) {
		for(int i = 0; i < controller.object.size(); i++) {
			GameObject tempObject = controller.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player) {
				//Adjust player speed
				tempObject.setVelX(7);
			}
		}	
	}
	
	//collision method to detect collision with any given GameObject
	public void collision(LinkedList<GameObject> object) {
		for(int i = 0; i < controller.object.size(); i++) {
			GameObject tempObject = controller.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block) {
				
				//collision with the top -> Player dies
				if(getBorderTop().intersects(tempObject.getBorder())){
					death();
					
					//y = tempObject.getY() + height/2; //height/2 kann verändert werden damit es besser passt
					//velY = 0;
				}
				
				//collision with the ground
				if(getBorder().intersects(tempObject.getBorder())){
					y = tempObject.getY() - 64;
					velY = 0;
					falling = false;
					jumping = false;
				} else falling = true; //Jumping is only possible if Player is on the ground
				
				//Right side collision
				if(getBorderRight().intersects(tempObject.getBorder())){
					death();
					
					//x = tempObject.getX() - width;					
				}
				
				//Left side collision
				if(getBorderLeft().intersects(tempObject.getBorder())){
					death();
					
					//x = tempObject.getX() + width + 2;
				}				
			}
			
			if(tempObject.getId() == ObjectId.Goal) {
				//switch level
				if(getBorder().intersects(tempObject.getBorder())) {
					//controller.switchLevel(); //Level ändert sich wenn Kontakt mit gelbem Block
				}
				
			}
			
			if(tempObject.getId() == ObjectId.Spike) {
				//player death
			}
		}
	}
	
	public void death() {
		
		//Spieler wird entfernt
		System.out.println("Player died due to collision");
		controller.removeObject(this);
		
	}
	
	//method to render the graphics
	public void render(Graphics graphics) {
		
		graphics.drawImage(texture.player[skin], (int)x, (int)y, null);
		
		//the player appears as a blue box
		//graphics.setColor(Color.blue);
		//graphics.fillRect((int)x, (int)y, (int)width, (int)height);
		
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
	
	//The following methods create Rectangles inside the player to use in collision
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
	
}

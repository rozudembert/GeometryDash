/*
 * Basically the Main-Class of this project.
 * To keep everything running
 * 
 * @author Robert Kelm
 * @version 08.03.2021
 */

package com.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import com.framework.KeyInput;
import com.framework.ObjectId;
import com.framework.Texture;

public class Game extends Canvas implements Runnable{
	
	//window dimensions
	public static int WIDTH = 1280, HEIGHT = WIDTH / 16*9;
	
	//window title
	private static String title = "Geometry Dash :)";
	
	static Texture texture;
	Controller controller;
	Camera cam;
	static Menu menu;
	HUD hud;
	
	//the gameState in which the game is starting
	public static STATUS gameStatus = STATUS.StartMenu;
	
	//!!!order of initialisation is important!!!
	private void initialise() {
		
		texture = new Texture();
		menu = new Menu(this, controller, texture);
		cam = new Camera(0, 0);
		controller = new Controller(cam);
		hud = new HUD(cam, controller);
		
		this.addKeyListener(new KeyInput(this, controller, menu));	
	}
	
	private void update() {
		controller.update();		
		
		if(gameStatus == STATUS.Menu) {
			menu.update();
		} 
		
		else if(gameStatus == STATUS.Game) {			
			
			//update camera
			for(int i = 0; i < Controller.object.size(); i++) {
				if(Controller.object.get(i).getId() == ObjectId.Player) {
					
					cam.update(Controller.object.get(i));
					hud.update(Controller.object.get(i));
					
				}
			}		
		}		
	}
	
	//render graphics
	private void render() {
		
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if(bufferStrategy == null) {
			this.createBufferStrategy(3); //amount of buffers
			return;
		}
		
		Graphics graphics = bufferStrategy.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) graphics;
		
		//create Background
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
				
		g2d.translate(cam.getX(), cam.getY()); //Begin of camera
		
		controller.render(graphics);
		hud.render(graphics);
		
		g2d.translate(-cam.getX(), -cam.getY()); //End of camera
		
		menu.render(graphics);
		
		graphics.dispose();
		bufferStrategy.show();
	}
	
	//enum to store the different states the game can be in
	public enum STATUS{
		StartMenu,
		Menu,
		Help,
		Dead,
		End,
		Game;
	}
	
	public static Texture getInstance() {
		return texture;
	}
	
	public static void playerDeath() {
		Controller.clearLevel();
		gameStatus = STATUS.Dead;
		menu.set_death_retry(true);
	}
	
	public static void endMenu() {
		Controller.clearLevel();
		gameStatus = STATUS.End;
		menu.set_final_retry(true);
	}
	
	public void setGameStatus(STATUS status) {
		Game.gameStatus = status;
	}
	
	
	
	//WOOOW SO EMPTY :)
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////////////////////////////////
	//															//
	//															//
	// 		  THE FOLLOWING CODE IS FOR RUNNING THE GAME		//
	//															//
	//															//
	//////////////////////////////////////////////////////////////
	
	private static final long serialVersionUID = 8720090281850121093L;
	
	private boolean running = false;
	private Thread thread;	
	
	//Main method to create window and start game
	public static void main (String args[]) {
		new Window(WIDTH, HEIGHT, title, new Game());
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Game-Loop	
	public void run() {
		this.requestFocus();
		initialise();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
				while(delta >= 1) {
					update();
					updates++;
					delta--;
				}	
			if(running) 
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}				
		}
		stop();
	}
}

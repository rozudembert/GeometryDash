package com.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.framework.KeyInput;
import com.framework.ObjectId;
import com.framework.Texture;
import com.objects.Block;
import com.objects.Goal;
import com.objects.Player;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -5594972038976415455L;
	
	//Window dimensions
	public static int WIDTH = 1280, HEIGHT = WIDTH / 16*9;
	
	//Window title
	private static String title = "Geometry Dash";
	
	private boolean running = false;
	private Thread thread;	
	
	public BufferedImage level = null, level2 = null;
	
	Controller controller;
	Camera cam;
	static Texture texture;
	Menu menu;
	
	public STATUS gameStatus = STATUS.StartMenu;
	
	public static int LEVEL = 1;
	
	//
	private void initialise() {
		
		menu = new Menu(this, controller);
		
		texture = new Texture();
		
		cam = new Camera(0, 0);
		
		controller = new Controller(cam);
			
		this.addKeyListener(new KeyInput(controller));	
		this.addKeyListener(new Menu(this, controller));
	}
	
	public enum STATUS{
		StartMenu,
		Menu,
		Help,
		Dead,
		Game;
	}
	
	public void setGameStatus(STATUS status) {
		this.gameStatus = status;
	}
	
	public static Texture getInstance() {
		return texture;
	}
	
	
	/*
	 * The following methods are needed to start the game
	 */
	
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
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}				
		}
		stop();
	}
	
	//updating the GameObjects
	private void update() {
		controller.update();		
		
		if(gameStatus == STATUS.Menu) {
			menu.update();
		} 
		
		else if(gameStatus == STATUS.Game) {			
			//updating camera
			for(int i = 0; i < controller.object.size(); i++) {
				if(controller.object.get(i).getId() == ObjectId.Player) {
					cam.update(controller.object.get(i));
				}
			}
		}		
	}
	
	//to render the graphics
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
		
				
		g2d.translate(cam.getX(), cam.getY()); //Begin of camera; translates everything it "sandwiches"
				
		controller.render(graphics);
		
		g2d.translate(-cam.getX(), -cam.getY()); //End of camera
		
		if(gameStatus == STATUS.Game) {
			
		}
		else if(gameStatus == STATUS.Menu || gameStatus == STATUS.Help) {
			//menu.render(graphics);
		}
		
		menu.render(graphics);
		
		graphics.dispose();
		bufferStrategy.show();
	}
	
	
	
}

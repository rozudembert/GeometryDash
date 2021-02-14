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
import com.objects.Player;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -5594972038976415455L;
	
	//Window dimensions
	public static int WIDTH = 1280, HEIGHT = WIDTH / 16*9;
	
	//Window title
	private static String title = "Geometry Dash";
	
	private boolean running = false;
	private Thread thread;	
	
	Controller controller;
	Camera cam;
	static Texture texture;
	
	//
	private void initialise() {
		
		texture = new Texture();
		
		controller = new Controller();
		
		cam = new Camera(0, 0);
		
		controller.addObject(new Player(200, 496, controller, ObjectId.Player));
		
		controller.createLevel();
		
		this.addKeyListener(new KeyInput(controller));
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
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}				
		}
		stop();
	}
	
	//updating the GameObjects
	private void update() {
		controller.update();
		
		//updating camera
		for(int i = 0; i < controller.object.size(); i++) {
			if(controller.object.get(i).getId() == ObjectId.Player) {
				cam.update(controller.object.get(i));
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
		
		///////////////////////////////
				
		graphics.dispose();
		bufferStrategy.show();
	}
	
	
	
}

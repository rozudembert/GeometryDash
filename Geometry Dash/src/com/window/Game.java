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
	
	private BufferedImage level = null;
	
	Controller controller;
	Camera cam;
	static Texture texture;
	
	//
	private void initialise() {
		
		texture = new Texture();
		
		controller = new Controller();
		
		cam = new Camera(0, 0);
		
		//load level image from file system
		ImageLoader loader = new ImageLoader();
		level = loader.loadImage("/level_1.png");
		
		loadLevel(level);
		
//		controller.addObject(new Player(700, 496, controller, 2, ObjectId.Player));
		
//		controller.createLevel();
		
		this.addKeyListener(new KeyInput(controller));
	}
	
	//Recieves an image as input and converts it into GameObjects
	private void loadLevel(BufferedImage image) {
		
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
					controller.addObject(new Block(xx*64, yy*64, 0, ObjectId.Block));
				if(red == 0 && green == 0 && blue == 255)
					controller.addObject(new Player(xx*64, yy*64, controller, 0, ObjectId.Player));
			}
		}
		
		
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
		
		
		graphics.dispose();
		bufferStrategy.show();
	}
	
	
	
}

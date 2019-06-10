import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.ImageIcon;


public class game extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8862206732067977282L;
	
	//width
	public static final int WIDTH = 1100;
	public static final int HEIGHT= 900;
	
	//for running the game
	private Thread thread;
	private boolean run = false;
	
	//object handler for the game
	private objectHandler handler;
	
	//object handler for the menu
	private menuObjectHandler menuHandler;
	
	//Jframe
	static gameWindow frame;

	//no. of snowflakes in background
	static final int snowFlakes = 200;
	
	//key listener
	keyInputs keyListener;
	
	//mouse listener
	mouseInputs mouseListener;
	
	//game stats
	gameStats score;
	
	//random value
	Random r;
	
	//menu screen
	menuScreen menu;
	//instructions screen
	instructionScreen instructions;
	//credits screen
	creditScreen credits;
	
	//game states
	public enum states{
		MENU,
		GAME,
		INSTRUCTIONS,
		CREDITS
	}
	
	//default game state
	public states gameState = states.MENU;
	
	//constructor to create a window object
	public game() {
		frame = new gameWindow(WIDTH, HEIGHT,"Box Jumping Game", this);
		//scoring for the game
		score = new gameStats();
		//class used to handle all the objects
		handler = new objectHandler(score);
		//class used to handle objects in the menu
		menuHandler = new menuObjectHandler();
		//key listener
		keyListener = new keyInputs(handler);
		
		
		//menu
		menu = new menuScreen();
		//instruction screen
		instructions = new instructionScreen();
		//credits screen
		credits = new creditScreen();
		
		//mouse listener
		mouseListener = new mouseInputs(this, handler, menu, instructions, credits);
		
		
		
		//add a keyboard listener to listen to all key inputs
		this.addKeyListener(keyListener);
		//adds a mouse listener to listen to mouse inputs
		this.addMouseListener(mouseListener);
		
		//random value
		r = new Random();
		
		//default start for every game
		if (gameState == states.GAME) {
			startGame();
		} else {
			System.out.println("create");
			menuHandler.addObject(new menuSnow(objectType.MENUSNOW, r.nextInt(WIDTH), 100, true, menuHandler));
		}
	}
	
	//starts the game
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		run = true;
	}
	
	//ends the game
	public synchronized void stop() {
		try {
			thread.join();
			run = false;
		} catch(Exception e) {

		}
	}

	//game loop to run game
	public void run() {
		this.requestFocus();
		 long lastTime = System.nanoTime();
		 double amountOfTicks = 60.0;
		 double ns = 1000000000 / amountOfTicks;
		 double delta = 0;
		 long timer = System.currentTimeMillis();
		 int frames = 0;
		 while(run){
		  long now = System.nanoTime();
		     delta += (now - lastTime) / ns;
		     lastTime = now;
		     while(delta >= 1){
		      update();
		      delta--;
		     }
		     
		     //renders the images
		     if(run)
		      render();
		     frames++;
		     
		     //finds the number of frames per second
		     if(System.currentTimeMillis() - timer > 1000){
		      timer = System.currentTimeMillis();
		      //System.out.println("FPS: " + frames);
		      frames = 0;
		     }
		 } 
		     stop();
	}
	
	//drawing setup for every new game
	public void startGame() {
		//snow ----> testing ---> just for fun!!!
		for(int i = 0; i < snowFlakes; i++) {
			handler.addObject(new snow(objectType.SNOW, r.nextInt(WIDTH), r.nextInt(HEIGHT) / 2, false, handler));
		}
				
		//creates the ground
		handler.addObject(new ground(objectType.GROUND, 0, HEIGHT - ground.height, false));
				
		//creates the player object
		handler.addObject(new player(objectType.PLAYER, WIDTH/2 - player.width, HEIGHT- ground.height - player.height, false, handler));
				
		//creates obstacles
		handler.addObject(new obstacle(objectType.TREE, WIDTH, HEIGHT - ground.height - obstacle.height, true, handler, score, false));
	}
	
	
	//updates actions in the game
	private void update() {
		
		//updates the game when the game is played
		if (gameState == states.GAME) {
			handler.update();
			score.update();
		
		//updates actions on the menu screen
		} else if (gameState == states.MENU) {
			menuHandler.update();
			menu.update();
		//updates on the instructions screen
		} else if (gameState == states.INSTRUCTIONS) {
			menuHandler.update();
			instructions.update();
		//updates on the credits screen
		} else if (gameState == states.CREDITS) {
			menuHandler.update();
			credits.update();
		}
	}
	
	//handles the rendering/drawing of the images
	private void render() {
		
		//creates a memory of the image from a previous frame
		BufferStrategy buffer = this.getBufferStrategy();
		if (buffer == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics image = buffer.getDrawGraphics();
		Graphics2D image2D = (Graphics2D) image;
		
		
		image2D.setColor(new Color(0,0,0));
		image2D.fillRect(0, 0, WIDTH, HEIGHT);
		
		ImageIcon menuBackground = new ImageIcon ("src/pics/snowy background.png");
		image2D.drawImage(menuBackground.getImage(),0,0, WIDTH, HEIGHT,frame);
		
		//game objects rendered when the game is played
		if (gameState == states.GAME) {
			ImageIcon background = new ImageIcon ("src/pics/sky.png");
			image2D.drawImage(background.getImage(),0,0, WIDTH, HEIGHT,frame);
			score.render(image2D);
			
			//renders all of the objects
			handler.render(image2D);
			
		//renders objects in the menu screen
		} else if (gameState == states.MENU) {
			menu.render(image2D);
			menuHandler.render(image2D);
		} else if (gameState == states.INSTRUCTIONS) {
			instructions.render(image2D);
			menuHandler.render(image2D);
		} else if (gameState == states.CREDITS) {
			credits.render(image2D);
			menuHandler.render(image2D);
		}
		
		image.dispose();
		buffer.show();
	}
	
	//creates the game
	public static void main(String args[]) {
		new game();
	}
}

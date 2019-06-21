import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
	
	//game speed
	int gameSpeed;
	
	//random value
	Random r;
	
	//menu screen
	menuScreen menu;
	//instructions screen
	instructionScreen instructions;
	//credits screen
	creditScreen credits;
	//pause screen
	pauseScreen pause;
	//game over screen
	gameOver gameOver;
	//setting screen
	settingScreen settings;
	
	
	
	//fps
	int frames;
	
	
	//default game state
	public states gameState = states.MENU;
	
	//music
	playMusic music;
	
	//player
	player Player;
	
	//cursor glow
	glow Glow;
	
	//cursor snow
	menuCursorSnow cursorSnow;
	
	//constructor to create a window object
	public game() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		frame = new gameWindow(WIDTH, HEIGHT,"Box Jumping Game", this);
		//scoring for the game
		score = new gameStats(this);
		//class used to handle all the objects
		handler = new objectHandler(score, this);
		//class used to handle objects in the menu
		menuHandler = new menuObjectHandler();
		//key listener
		keyListener = new keyInputs(handler, this);
		
		
		//menu
		menu = new menuScreen(frame);
		//instruction screen
		instructions = new instructionScreen();
		//credits screen
		credits = new creditScreen();
		//pause screen
		pause = new pauseScreen();
		//game over screen
		gameOver = new gameOver(score, handler);
		//setting screen
		settings= new settingScreen(this);
		
		//music
		music = new playMusic();
		
		//player
		Player = new player(objectType.PLAYER, WIDTH/2 - player.width, HEIGHT- ground.height - player.height, false, handler,frame, System.currentTimeMillis(), keyListener, this);
		
		//mouse listener
		mouseListener = new mouseInputs(this, handler, menu, instructions, credits, gameOver, score, pause, menuHandler, music, Player, settings, frame);
		
		//cursor glow
		Glow = new glow(objectType.MOUSEGLOW, mouseListener.mouseX, mouseListener.mouseY, false, mouseListener);
		
		
		//game speed
		gameSpeed = 2;
		
		
		//add a keyboard listener to listen to all key inputs
		this.addKeyListener(keyListener);
		//adds a mouse listener to listen to mouse inputs
		this.addMouseListener(mouseListener);
		//adds mouse motion listener
		this.addMouseMotionListener(mouseListener);
		
		//random value
		r = new Random();
		
		
		//cursor snow
		cursorSnow = new menuCursorSnow(objectType.CURSORSNOW, r.nextInt(Glow.glowWidth) + Glow.locationX , Glow.locationY + Glow.glowHeight, true, menuHandler, Glow, r.nextInt(6) + 6);
		
		//default start for every game
		if (gameState == states.GAME) {
			startGame();
		} else if (gameState == states.MENU){
			menuHandler.addObject(new menuSnow(objectType.MENUSNOW, r.nextInt(WIDTH), 100, true, menuHandler, this));
			music.play();
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
		 frames = 0;
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
		handler.addObject(Player);
				
		//creates obstacles
		handler.addObject(new obstacle(objectType.TREE, WIDTH, HEIGHT - ground.height - obstacle.height, true, handler, score, false, this));
	}
	
	
	//updates actions in the game
	private void update() {
		
		//changes the action for the default cursor
		if (frame.defCursorUse) {
			frame.setDefaultCursor();
		}
		
		if (score.getLives() == 0) {
			//reset the states of the buttons
			if (!gameOver.buttonReset) {
				gameOver.buttonReset = true;
				gameOver.gmOvRetryButton = false;
				gameOver.infMenButton = false;
				
				//cursor
				for (int i = 0; i < menuHandler.objectList.size() - 1; i++) {
					//reset cursor snow
					if (menuHandler.objectList.get(i).getName() == objectType.CURSORSNOW) {
						menuHandler.objectList.remove(i);
						menuCursorSnow.snowCreated = false;
					}
					
					//reset cursor glow
					if (menuHandler.objectList.get(i).getName() == objectType.MOUSEGLOW) {
						menuHandler.objectList.remove(i);
						glow.glowCreated = false;
					}
					
					//remove all menu snow
					if (menuHandler.objectList.get(i).getName() == objectType.MENUSNOW) {
						menuHandler.objectList.remove(i);
					}
				}
			}
			gameState = states.GAMEOVER;
		}
		
		if (score.getPause()) {
			gameState = states.PAUSE;
			menuHandler.update();
		}
		
		//updates the game when the game is played
		if (gameState == states.GAME) {
			handler.update();
			score.update();
		//updates actions on the menu screen
		} else if (gameState == states.MENU) {
			menuHandler.update();
		//updates on the instructions screen
		} else if (gameState == states.INSTRUCTIONS) {
			menuHandler.update();
			instructions.update();
		//updates on the credits screen
		} else if (gameState == states.CREDITS) {
			menuHandler.update();
			credits.update();
		} else if (gameState == states.GAMEOVER) {
			menuHandler.update();
		} else if (gameState == states.SETTINGS) {
			menuHandler.update();
			settings.update();
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
			
		//renders objects in the instructions screen
		} else if (gameState == states.INSTRUCTIONS) {
			instructions.render(image2D);
			menuHandler.render(image2D);
			
		//renders objects in the credit screen
		} else if (gameState == states.CREDITS) {
			credits.render(image2D);
			menuHandler.render(image2D);
			
		//renders objects in the pause screen
		} else if (gameState == states.PAUSE) {
			ImageIcon background = new ImageIcon ("src/pics/sky.png");
			image2D.drawImage(background.getImage(),0,0, WIDTH, HEIGHT,frame);
			score.render(image2D);
			handler.render(image2D);
			pause.render(image2D);
			menuHandler.render(image2D);
			
		//renders objects in the game over screen
		} else if (gameState == states.GAMEOVER) {
			ImageIcon background = new ImageIcon ("src/pics/sky.png");
			image2D.drawImage(background.getImage(),0,0, WIDTH, HEIGHT,frame);
			score.render(image2D);
			
			//renders all of the objects
			handler.render(image2D);
			gameOver.render(image2D);
			menuHandler.render(image2D);
			
		//renders objects in the settings screen
		} else if (gameState == states.SETTINGS) {
			settings.render(image2D);
		}
		
		image.dispose();
		buffer.show();
	}
	
	//creates the game
	public static void main(String args[]) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		new game();
	}
}

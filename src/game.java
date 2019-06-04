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
	
	//Jframe
	static gameWindow frame;
	
	//constructor to create a window object
	public game() {
		frame = new gameWindow(WIDTH, HEIGHT,"Box Jumping Game", this);
		//class used to handle all the objects
		handler = new objectHandler();
		//add a keyboard listener to listen to all key inputs
		this.addKeyListener(new keyInputs(handler));
		
		//random value
		Random r = new Random();
		
		//snow ----> testing ---> just for fun!!!
		for(int i = 0; i < 200; i++) {
			handler.addObject(new snow(objectType.SNOW, r.nextInt(WIDTH), r.nextInt(HEIGHT) / 2, System.currentTimeMillis(), false));
		}
		
		//creates the ground
		handler.addObject(new ground(objectType.GROUND, 0, HEIGHT - ground.height, System.currentTimeMillis(), false));
		
		//creates the player object
		handler.addObject(new player(objectType.PLAYER, WIDTH/2 - player.width, HEIGHT- ground.height - player.height, System.currentTimeMillis(), false));
		
		//creates obstacles
		handler.addObject(new obstacle(objectType.TREE, WIDTH, HEIGHT - ground.height - obstacle.height, System.currentTimeMillis(), true));
		
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
	
	//updates actions in the game
	private void update() {
		handler.update();
	}
	
	//handles the rendering/drawing of the images
	private void render() {
		BufferStrategy buffer = this.getBufferStrategy();
		if (buffer == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics image = buffer.getDrawGraphics();
		Graphics2D image2D = (Graphics2D) image;
		ImageIcon background = new ImageIcon ("src/pics/sky.png");
		
		
		image2D.setColor(new Color(0,0,0));
		image2D.fillRect(0, 0, WIDTH, HEIGHT);
		image2D.drawImage(background.getImage(),0,0, WIDTH, HEIGHT,frame);
		
		//renders all of the objects
		handler.render(image2D);
		
		
		image.dispose();
		buffer.show();
	}
	
	//creates the game
	public static void main(String args[]) {
		new game();
	}
}

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class gameWindow extends JFrame{
	
	//checks if the game is running
	boolean isRunning = true;
	
	//frame rates per second (FPS)
	long fPS = 25;
	
	//width of window
	int windowWidth = 1100;
	
	//height of window
	int windowHeight = 900;
	
	//*********temporary start position of the moving circle******* DELETE LATER
	int x = 0;
	
	//memory of the image of the previous frame
	Image buffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
	
	
	public static void main(String[] args) {
		gameWindow game = new gameWindow();
		game.run();
		System.exit(0);
	}
	
	/*
	 * Creates the display for the game
	 */
	private void makeWindow() {
		setTitle("Boxy Jump Game");
		setSize(windowWidth,windowHeight);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	
	public void run() {
		makeWindow();
		
		while(isRunning) {
			//time in milliseconds for the beginning of a frame
			long time = System.currentTimeMillis();
			
			update();
			draw();
			
			//delay for each frame ----> track the time in milliseconds between each frame
			time = (1000/fPS) - (System.currentTimeMillis() - time);
			
			if (time > 0) {
				try {
					Thread.sleep(time);
				} catch(Exception e){}
			}
		}
		
		setVisible(false);
		
		
	}
	
	public void update() {
		x=x+2;
	}
	
	public void draw() {
		Graphics printImage = getGraphics();
		Graphics prevImage = buffer.getGraphics();
		
		//red background
		prevImage.setColor(Color.RED);
		prevImage.fillRect(0,0,windowWidth,windowHeight-300);
		
		//the ground
		prevImage.setColor(Color.orange);
		prevImage.fillRect(0, windowHeight-300, windowWidth, 300);
		
		//moving white circle
		prevImage.setColor(Color.orange);
		prevImage.fillRect(x, windowHeight-350, 50, 50);
		
		printImage.drawImage(buffer,0,0,this);
		
	}
}

import java.awt.*;
import java.awt.event.KeyEvent;
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
	int x = 450;
	
	//memory of the image of the previous frame
	Image buffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
	
	//border for the window
	Insets border = this.getInsets();
	
	
	
	//object for handling the inputs of the user
	inputController input = new inputController(this);
	
		
	
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
		
		//set the size of the border
		setSize(border.left + windowWidth + border.right, border.top + windowHeight + border.bottom);
	}
	
	
	/*
	 * -makes the window for the game
	 * -Continuously updates the game
	 */
	void run() {
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
	
	void update() {
		//moves the character/box
		//moves the character right
		if (input.keyDown(KeyEvent.VK_RIGHT)) {
			x+=3;
		}
		
		if (input.keyDown(KeyEvent.VK_LEFT)) {
			x-=3;
		}
	}
	
	
	
	/*
	 * -draws the sprites and images for the game
	 */
	void draw() {
		Graphics printImage = getGraphics();
		Graphics prevImage = buffer.getGraphics();
		
		//red background
		prevImage.setColor(Color.RED);
		prevImage.fillRect(0,0,windowWidth,windowHeight-300);
		
		//the ground
		prevImage.setColor(Color.orange);
		prevImage.fillRect(0, windowHeight-300, windowWidth, 300);
		
		//character
		prevImage.setColor(Color.orange);
		prevImage.fillRect(x, windowHeight-350, 50, 50);
		
		printImage.drawImage(buffer, border.left, border.top, this);
		
	}
}

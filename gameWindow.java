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
	static int windowWidth = 1100;
	
	//height of window
	static int windowHeight = 900;
	
	//*********temporary start position of the moving circle******* DELETE LATER
	int x = 450;
	
	//memory of the image of the previous frame
	Image buffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
	
	//border for the window
	Insets border = this.getInsets();
	
	
	
	//object for handling the inputs of the user
	inputController input = new inputController(this);
	
	
	//background
	background bg = new background(new Color(255,92,51));
	//player
	player box = new player(new Color(255,153,0), new Color(179,89,0), 2, 50, 50);
	
	
		
	
	public static void main(String[] args) {
		gameWindow game = new gameWindow();
		game.run();
		System.exit(0);
	}
	
	/*
	 * Cr
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
			box.moveRight();
		}
		
		if (input.keyDown(KeyEvent.VK_LEFT)) {
			box.moveLeft();
		}
	}
	
	
	
	/*
	 * -draws the sprites and images for the game
	 */
	void draw() {
		//used to draw images
		Graphics printImage = getGraphics();
		Graphics prevImage = buffer.getGraphics();
		//cast to graphics2D class
		Graphics2D prevImage2D = (Graphics2D)prevImage;
		
		//red background
		bg.create(prevImage2D);
		
		//the ground
		prevImage2D.setColor(new Color(204,68,0));
		prevImage2D.fillRect(0, windowHeight-300, windowWidth, 300);
		
		//character
		box.create(prevImage2D);
		
		//text
		prevImage2D.setFont(new Font("TimesRoman",Font.BOLD, 100));
		prevImage2D.setColor(Color.white);
		prevImage2D.drawString("TESTING", windowWidth/2 - 225, 300);
		
		printImage.drawImage(buffer, border.left, border.top, this);
		
	}
	
	//returns the width of the game window
	public static int windowDimenX() {
		return gameWindow.windowWidth;
	}
	
	//returns the height of the game window
	public static int windowDimentY() {
		return gameWindow.windowHeight;
	}
}

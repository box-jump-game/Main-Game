import java.awt.*;
import javax.swing.JFrame;

public class gameWindow extends JFrame{
	
	//checks if the game is running
	boolean isRunning = true;
	
	//frame rates per second (FPS)
	long fPS = 25;
	
	
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
		setSize(900,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
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
		
	}
	
	public void draw() {
		
	}
}

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class obstacle extends gameObjects{
	
	//movement speed of the obstacle
	static int moveSpeedX = 4;
	//width and height of the obstacle
	static int width = 100, height = 100;
	

	public obstacle(objectType name, int locationX, int locationY, double drawnTime, boolean add) {
		super(name, locationX, locationY, drawnTime, add);
		speedX = -1 * moveSpeedX;
		speedY = 0;
	}
	
	//updates the position of the object
	public void updateObject() {
		locationX += speedX;
		locationY += speedY;
	}

	//draws the object
	public void renderObject(Graphics2D g2d) {
		ImageIcon tree = new ImageIcon("src/pics/tree.png");
		g2d.drawImage(tree.getImage(),locationX,locationY, width, height,game.frame);
	}
}



import java.awt.Color;
import java.awt.Graphics2D;

public class player extends gameObjects{
	
	static int width = 32, height = 32;
	
	//constructor for the player
	public player(objectType name, int locationX, int locationY) {
		super(name, locationX, locationY);
		speedX = 0;
		speedY = 0;
	}
	
	//updates the speed of the player
	public void updateObject() {
		locationX += speedX;
		locationY += speedY;
		
	}
	
	//constantly redraws the player during each update
	public void renderObject(Graphics2D g2d) {
		g2d.setColor(new Color(255,26,26));
		g2d.fillRect(locationX, locationY, width, height);
	}
}
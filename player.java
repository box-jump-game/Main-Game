import java.awt.Color;
import java.awt.Graphics2D;

public class player extends gameObjects{
	//constructor
	public player(objectType name, int locationX, int locationY) {
		super(name, locationX, locationY);
		speedX = 0;
		speedY = 0;
	}

	public void updateObject() {
		locationX += speedX;
		locationY += speedY;
		
	}

	public void renderObject(Graphics2D g2d) {
		g2d.setColor(new Color(255,26,26));
		g2d.fillRect(locationX, locationY, 32, 32);
	}
}

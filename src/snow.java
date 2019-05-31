
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class snow extends gameObjects{
	
	//constructor
	public snow(objectType name, int locationX, int locationY) {
		super(name, locationX, locationY);
		Random r = new Random();
		speedX = r.nextInt(5);
		speedY = r.nextInt(4) + 1;
	}
	
	//updates the actions of the object
	public void updateObject() {
		locationX += speedX;
		locationY +=speedY;
	}
	
	public void renderObject(Graphics2D g2D) {
		g2D.setColor(new Color(255,255,255));
		g2D.fillRect(locationX, locationY, 8, 8);
	}
}


import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class snow extends gameObjects{
	
	//objectHandler
	objectHandler handler;
	
	//random generator
	Random r;
	
	//constructor
	public snow(objectType name, int locationX, int locationY, boolean add, objectHandler handler) {
		super(name, locationX, locationY, add);
		this.handler = handler;
		r = new Random();
		speedX = r.nextInt(5) - 5;
		speedY = r.nextInt(4) + 1;
	}
	
	//updates the actions of the object
	public void updateObject() {
		locationX += speedX;
		locationY +=speedY;
		
		// if the snow objects go out of the screen, create a new snow object
		if (this.getLocationX() > game.WIDTH || this.getLocationY() > game.HEIGHT
				|| this.getLocationX() < 0 || this.getLocationY() < 0) {
			handler.removeObject(this);
			handler.addObject(0, new snow(objectType.SNOW, r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT) / 2, false, handler));
		}
	}
	
	public void renderObject(Graphics2D g2D) {
		g2D.setColor(new Color(255,255,255));
		g2D.fillRect(locationX, locationY, 8, 8);
	}
}

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class player extends gameObjects{
	
	static int width = 20, height = 20;
	//speed of the player in each axis
	static int moveSpeedX = 3, moveSpeedY = 4;
	//time player stays in the air when jumping and height of the player's jump
	static int airTime= 200, jumpHeight = 175;
	
	//object handler
	objectHandler handler;
	
	//constructor for the player
	public player(objectType name, int locationX, int locationY, boolean add, objectHandler handler) {
		super(name, locationX, locationY, add);
		speedX = 0;
		speedY = 0;
		this.handler = handler;
	}
	
	//updates the speed of the player
	public void updateObject() {
		locationX += speedX;
		locationY += speedY;
		
		//set boundaries for the object
		if (locationX >= game.WIDTH - width) {
			setLocationX(game.WIDTH - width);
		}
		if  (locationX <= 0) {
			setLocationX(0);
		}
		if (locationY >= game.HEIGHT - height) {
			setLocationY(game.HEIGHT - height);
		}
		if  (locationY <= 0) {
			setLocationY(0);
		}
		
		handler.addObject(new path(objectType.PATH, locationX, locationY, false, handler, new Color(255,26,26), 0.08));
		
	}
	
	//constantly redraws the player during each update
	public void renderObject(Graphics2D g2d) {
		g2d.setColor(new Color(255,26,26));
		g2d.fillRect(locationX, locationY, width, height);
		g2d.setColor(new Color(128,0,0));
		g2d.setStroke(new BasicStroke(2));
		g2d.drawRect(locationX, locationY, width, height);
	}
}

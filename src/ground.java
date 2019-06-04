import java.awt.*;
public class ground extends gameObjects{
	
	static int height = 216;
	
	//constructor for the ground object
	public ground(objectType name, int locationX, int locationY, double drawnTime, boolean add) {
		super(name, locationX, locationY, drawnTime, add);
	}
	
	public void updateObject() {
		
	}
	
	public void renderObject(Graphics2D g2D) {
		g2D.setColor(new Color(255,255,255));
		g2D.fillRect(locationX, locationY, game.WIDTH, height);
		
	}
}

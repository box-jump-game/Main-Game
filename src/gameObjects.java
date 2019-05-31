import java.awt.Graphics2D;

public abstract class gameObjects {
	
	//where object is located
	protected int locationX, locationY;
	
	//name of the object
	protected objectType name;
	
	//speed of object
	protected int speedX, speedY;
	
	//constructor for any object
	public gameObjects(objectType name, int locationX, int locationY) {
		this.name = name;
		this.locationX = locationX;
		this.locationY = locationY;
	}
	
	//updates the movements of the object
	public abstract void updateObject();
	//renders the image of the object
	public abstract void renderObject(Graphics2D g2D);
	
	//accessors and mutators
	//return the type of the gameObject
	public objectType getName() {
		return name;
	}
	
	//return the current x-value of the object's location
	public int getLocationX() {
		return locationX;
	}
	
	//return the current y-value of the object's location
	public int getLocationY() {
		return locationY;
	}
	
	
	
	//change horizontal speed
	public  void setSpeedX(int inSpeedX) {
		this.speedX = inSpeedX;
	}
	
	//change vertical speed
	public void setSpeedY(int inSpeedY) {
		this.speedY = inSpeedY;
	}
	
	//change the current x-value of the object's location
	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}
	
	//change the current y-value of the object's location
	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}
	
	
	
}

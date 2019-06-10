import java.awt.Graphics2D;

public abstract class gameObjects {
	
	//where object is located
	protected int locationX, locationY;
	
	//name of the object
	protected objectType name;
	
	//speed of object
	protected int speedX, speedY;
	
	//boolean to tell whether all of the objects are already generated
	//false = all objects already added     true = new objects still need to be added
	protected boolean add;
	
	//constructor for any object
	public gameObjects(objectType name, int locationX, int locationY, boolean add) {
		this.name = name;
		this.locationX = locationX;
		this.locationY = locationY;
		this.add = add;
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
	
	//return the boolean for the add status
	public boolean getAdd() {
		return add;
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
	
	//change the boolean status of an object to have all of its objects added or not
	public void setAdd(boolean add) {
		this.add = add;
	}
	
}

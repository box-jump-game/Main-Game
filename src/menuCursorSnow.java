import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class menuCursorSnow extends gameObjects{
	//time when the snowflake has spawned
	private double spawnTime;
		
	//time in between for the next snow flake to spawn
	private int nextSpawnTime;
	
	//objectHandler
	menuObjectHandler menuHandler;
	
	//random generator
	Random r = new Random();
	
	//check if the snow is already created
	static boolean snowCreated = false;
	
	//glow
	glow Glow;
	
	//radius
	int radius;

	public menuCursorSnow(objectType name, int locationX, int locationY, boolean add, menuObjectHandler menuHandler, glow Glow, int radius) {
		super(name, locationX, locationY, add);
		this.menuHandler = menuHandler;
		this.Glow = Glow;
		this.radius = radius;
		
		spawnTime = System.currentTimeMillis();
		nextSpawnTime = r.nextInt(3)*100;
		
		//speed of the snow flakes
		speedX = r.nextInt(2) - 1;
		speedY = r.nextInt(5) + 1;
	}

	
	public void updateObject() {
		locationX += speedX;
		locationY +=speedY;
		
		//adds a snow flake based off the next spawn time
		if (System.currentTimeMillis() - spawnTime > nextSpawnTime && getAdd()) {
			setAdd(false);
			radius = r.nextInt(6) + 6;
			menuHandler.addObject(new menuCursorSnow(objectType.CURSORSNOW, r.nextInt(Glow.glowWidth) + Glow.locationX, Glow.locationY + Glow.glowHeight, true, menuHandler, Glow, radius));
		}
				
		//if the snow goes out of the screen remove it
		if (this.getLocationX() > game.WIDTH || this.getLocationY() > game.HEIGHT || this.getLocationX() < 0 || this.getLocationY() < 0) {
			menuHandler.removeObject(this);
		}
	}


	public void renderObject(Graphics2D g2d) {
		if (glow.cursorHover) {
			g2d.setColor(new Color(255,77,77,192));
		} else {
			g2d.setColor(new Color(51,255,51,127));
		}
		g2d.fillOval(locationX, locationY, radius, radius);	
	}

}

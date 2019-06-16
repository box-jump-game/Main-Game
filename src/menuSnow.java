import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class menuSnow extends gameObjects{
	
	//time when the snowflake has spawned
	private double spawnTime;
	
	//time in between for the next snow flake to spawn
	private int nextSpawnTime;
	
	//dimensions of the snowflake
	static int menuSnowWidth = 8, menuSnowHeight = 8;
	
	//random generator
	Random r = new Random();
	
	//objectHandler
	menuObjectHandler menuHandler;
	
	//constructor for the menu snow object
	public menuSnow(objectType name, int locationX, int locationY, boolean add, menuObjectHandler menuHandler) {
		super(name, locationX, locationY, add);
		this.menuHandler = menuHandler;
		
		spawnTime = System.currentTimeMillis();
		nextSpawnTime = r.nextInt(2)*500;
		
		//speed of the snow flakes
		speedX = r.nextInt(3) - 2;
		speedY = r.nextInt(3) + 1;
	}

	
	public void updateObject() {
		locationX += speedX;
		locationY +=speedY;
		
		//adds a snow flake based off the next spawn time
		if (System.currentTimeMillis() - spawnTime > nextSpawnTime && getAdd()) {
			setAdd(false);
			menuHandler.addObject(new menuSnow(objectType.MENUSNOW, r.nextInt(game.WIDTH), 10, true, menuHandler));
		}
		
		//if the snow goes out of the screen remove it
		if (this.getLocationX() > game.WIDTH || this.getLocationY() > game.HEIGHT || this.getLocationX() < 0 || this.getLocationY() < 0) {
			menuHandler.removeObject(this);
		}
		
		menuHandler.addObject(new path(objectType.PATH, locationX, locationY, false, menuHandler, new Color(255,255,255), 0.09));
		
	}

	@Override
	public void renderObject(Graphics2D g2d) {
		g2d.setColor(new Color(255,255,255));
		g2d.fillOval(locationX, locationY, menuSnowWidth, menuSnowHeight);
		
	}

}

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Random;



public class objectHandler {

	// linked list that stores all the objects
	LinkedList<gameObjects> objectList = new LinkedList<gameObjects>();

	// random number
	Random r = new Random();
	
	//the game
	game g;
	
	//hitbox tolerance
	int tolerance;
	
	gameStats score;
	
	boolean dead = false;

	public objectHandler(gameStats score, game g) {
		this.score = score;
		this.g = g;
		
		tolerance = 10;
	}

	// updates the action of the object
	public void update() {
		for (int i = 0; i < objectList.size(); i++) {
			objectList.get(i).updateObject();
		}
	}

	// renders all the objects
	public void render(Graphics2D g2D) {
		
		if (objectList.size() > 0) {
			for (int i = 0; i < objectList.size(); i++) {

				objectList.get(i).renderObject(g2D);
				
				//when the player is not dead or when the game is not paused
				if (score.getLives() != 0 && !score.getPause()) {
					// if the object is a tree obstacle
					if (objectList.get(i).getName().equals(objectType.TREE)) {
					
						//stops the game if the player hits an obstacle
						if (getPlayer().getLocationX() - tolerance> objectList.get(i).getLocationX() && getPlayer().getLocationX() - tolerance + player.width < objectList.get(i).getLocationX()+ obstacle.width  && getPlayer().getLocationY() + player.height - tolerance > objectList.get(i).getLocationY()) {
							System.out.println("obstacleX: " +  (objectList.get(i).getLocationX() + obstacle.width) + " playerX: " + objectList.get(201).getLocationX());
							
							//stops all objects
							for (int j = 0; j < objectList.size(); j++) {
								objectList.get(j).setSpeedX(0);
								objectList.get(j).setSpeedY(0);
							}
					
							score.setLives(0);
					
						}
					}
					
					//stops all objects when the game is paused
					if (g.gameState == states.PAUSE) {
						for (int j = 0; j < objectList.size(); j++) {
							objectList.get(j).setSpeedX(0);
							objectList.get(j).setSpeedY(0);
						}
					}
				}
			}
		}
	}

	// adds objects into the object list
	public void addObject(gameObjects object) {
		this.objectList.add(object);
	}

	public void addObject(int index, gameObjects object) {
		this.objectList.add(index, object);
	}

	// removes objects from the object list
	public void removeObject(gameObjects object) {
		this.objectList.remove(object);
	}
	
	//removes all objects from the object list
	public void removeAll() {
		//System.out.println("remove");
		for (int i = 0; i < objectList.size(); i++) {
			this.objectList.remove();
		}
	}
	
	
	//accessors
	public gameObjects getPlayer() {
		for (int i = 0; i < objectList.size(); i++) {
			if (objectList.get(i).getName().equals(objectType.PLAYER)) {
				return objectList.get(i);
			}
		}
		
		return objectList.get(201);
	}

}

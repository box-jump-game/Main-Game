import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Random;

public class objectHandler {

	// linked list that stores all the objects
	LinkedList<gameObjects> objectList = new LinkedList<gameObjects>();

	// random number
	Random r = new Random();
	
	gameStats score;

	public objectHandler(gameStats score) {
		this.score = score;
	}

	// updates the action of the object
	public void update() {
		for (int i = 0; i < objectList.size(); i++) {
			objectList.get(i).updateObject();
		}
	}

	// renders all the objects
	public void render(Graphics2D g2D) {

		for (int i = 0; i < objectList.size(); i++) {

			objectList.get(i).renderObject(g2D);
	
			// if the object is a tree obstacle
			if (objectList.get(i).getName().equals(objectType.TREE)) {
				
				//stops the game if the player hits an obstacle
				if (objectList.get(game.snowFlakes + 1).getLocationX() + player.width > objectList.get(i).getLocationX() && objectList.get(game.snowFlakes + 1).getLocationX() < objectList.get(i).getLocationX() + obstacle.width  && objectList.get(game.snowFlakes + 1).getLocationY() + player.height > objectList.get(i).getLocationY()) {
					//System.out.println("obstacleX: " +  (objectList.get(i).getLocationX() + obstacle.width) + " playerX: " + objectList.get(201).getLocationX());
					for (int j = 0; j < objectList.size(); j++) {
						objectList.get(j).setSpeedX(0);
						objectList.get(j).setSpeedY(0);
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

}

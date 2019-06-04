import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Random;

public class objectHandler {
	
	//linked list that stores all the objects
	LinkedList<gameObjects> objectList = new LinkedList<gameObjects>();
	
	//random number
	Random r = new Random();
	
	//spawn rate of the obstacles
	int obstacleSpawnTime = 2000;
	
	//updates the action of the object
	public void update() {
		for (int i = 0; i < objectList.size(); i++) {
			objectList.get(i).updateObject();
		}
	}
	
	//renders all the objects
	public void render(Graphics2D g2D) {
		
		for (int i = 0; i < objectList.size(); i++) {
			objectList.get(i).renderObject(g2D);
			
			//if the snow objects go out of the screen, create a new snow object
			if (objectList.get(i).getName().equals(objectType.SNOW)) {
				if (objectList.get(i).getLocationX() > game.WIDTH || objectList.get(i).getLocationY() > game.HEIGHT || objectList.get(i).getLocationX() < 0 || objectList.get(i).getLocationY() < 0) {
					removeObject(objectList.get(i));
					addObject(0, new snow(objectType.SNOW, r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT)/2, System.currentTimeMillis(), false));
				}
				
			}
			
			//if the object is a tree obstacle
			if (objectList.get(i).getName().equals(objectType.TREE)) {
				
				//creates a new tree depending on the spawn rate
				if (System.currentTimeMillis() - objectList.get(i).getDrawnTime() > obstacleSpawnTime && objectList.get(i).getAdd()) {
					objectList.get(i).setAdd(false);
					System.out.println("create");
					addObject(new obstacle(objectType.TREE, game.WIDTH, game.HEIGHT - ground.height - obstacle.height, System.currentTimeMillis(), true));
				}
				
				//removes the tree obstacle if its x-position crosses the left side of the JFrame
				if (objectList.get(i).getLocationX() < 0 - obstacle.width) {
					System.out.println("remove");
					removeObject(objectList.get(i));
				}
			}
		}
	}
	
	//adds objects into the object list
	public void addObject(gameObjects object) {
		this.objectList.add(object);
	}
	
	public void addObject(int index, gameObjects object) {
		this.objectList.add(index, object);
	}
	
	//removes objects from the object list
	public void removeObject(gameObjects object) {
		this.objectList.remove(object);
	}
	
}

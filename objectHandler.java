import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Random;

public class objectHandler {
	
	//linked list that stores all the objects
	LinkedList<gameObjects> objectList = new LinkedList<gameObjects>();
	
	//random number
	Random r = new Random();
	
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
					addObject(0, new snow(objectType.SNOW, r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT)/2));
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

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Random;

public class menuObjectHandler {
	// linked list that stores all the objects
	LinkedList<gameObjects> objectList = new LinkedList<gameObjects>();

	// random number
	Random r = new Random();

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

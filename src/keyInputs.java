import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class keyInputs extends KeyAdapter {
	
	private objectHandler handler;
	
	public keyInputs(objectHandler handler) {
		this.handler = handler;
	}
	
	//moves the player if key is pressed
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		//System.out.println(key);
		
		//loops through all the object in the object list to find the player object
		for (int i = 0; i < handler.objectList.size(); i++) {
			
			gameObjects temp = handler.objectList.get(i);
			

			if(temp.getName() == objectType.PLAYER) {
				//player will move up
				if (key == KeyEvent.VK_UP) {
					temp.setSpeedY(-3);
				}
				//player moves down
				if (key == KeyEvent.VK_DOWN) {
					temp.setSpeedY(3);
				}
				//player moves right
				if (key == KeyEvent.VK_RIGHT) {
					temp.setSpeedX(3);
				}
				//player moves left
				if (key == KeyEvent.VK_LEFT) {
					temp.setSpeedX(-3);
				}
				
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		//System.out.println(key);
		
		//loops through all the object in the object list to find the player object
		for (int i = 0; i < handler.objectList.size(); i++) {
			
			gameObjects temp = handler.objectList.get(i);
			
			
			//if the specified key for the player is released
			if(temp.getName() == objectType.PLAYER) {
				//player will stop moving right
				if (key == KeyEvent.VK_UP) {
					temp.setSpeedY(0);
				}
				//player will stop moving down
				if (key == KeyEvent.VK_DOWN) {
					temp.setSpeedY(0);
				}
				//player will stop moving right
				if (key == KeyEvent.VK_RIGHT) {
					temp.setSpeedX(0);
				}
				//player will stop moving left
				if (key == KeyEvent.VK_LEFT) {
					temp.setSpeedX(0);
				}
				
			}
		}
	}
}

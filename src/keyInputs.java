import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class keyInputs extends KeyAdapter {
	
	private objectHandler handler;
	
	//if the key for jumping is already pressed
	boolean pressed = false;
	
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
				//player will jump up and come back down
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_SPACE) {
					
					//if the character is on the ground, make the go up a certain height at a certain speed
					if (temp.getLocationY() == game.HEIGHT - ground.height - player.height && !pressed) {
						temp.setLocationY(game.HEIGHT - ground.height - player.height);
						while (temp.getLocationY() >= game.HEIGHT - ground.height - player.jumpHeight) {
							temp.setSpeedY(-1*player.moveSpeedY);
						}
						
						//stop the character at a certain height above the ground
						temp.setSpeedY(0);
						temp.setLocationY(game.HEIGHT - ground.height - player.jumpHeight);
						
						//time at the peak of the jump
						double prevAirTime = System.currentTimeMillis();
						while (System.currentTimeMillis() - prevAirTime < player.airTime) {
							//System.out.println(System.currentTimeMillis() + " prev air time: " + prevAirTime);
						}
						
						//make the character fall back down to the ground
						while (temp.getLocationY() <= game.HEIGHT - ground.height - player.height) {
							temp.setSpeedY(player.moveSpeedY);
						}
						
						//set the character back to the ground to avoid any discrepancy 
						temp.setSpeedY(0);
						temp.setLocationY(game.HEIGHT - ground.height - player.height);
					}
					
					//does not allow the jump key to continuously run the code while the character is jumping
					pressed = true;
				}
				//player moves right
				if (key == KeyEvent.VK_RIGHT) {
					temp.setSpeedX(player.moveSpeedX);
				}
				//player moves left
				if (key == KeyEvent.VK_LEFT) {
					temp.setSpeedX(-1 * player.moveSpeedX);
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
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_SPACE) {
					pressed = false;
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

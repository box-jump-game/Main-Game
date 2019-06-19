import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class keyInputs extends KeyAdapter {
	
	private objectHandler handler;
	
	//if the key for jumping is already pressed
	
	
	//number of keys used in game
	int keyNo = 3;
	
	//status of keys that are pressed
	private boolean[] keyPressed = new boolean[keyNo];
	
	//game
	game g;
	
	//if player is jumping
	boolean jumping = false;
	
	//if player is jumping up
	boolean jumpUp = false;
	
	//if player is jumping down
	boolean jumpDown = false;
	
	//delay between each jump
	double jumpDelay = 100;
	
	//last jump time
	double lastJumpTime = 0;
	
	public keyInputs(objectHandler handler, game g) {
		this.handler = handler;
		this.g = g;
		
		//all keys are not pressed at the beginning
		for (int i = 0; i < keyNo; i++) {
			keyPressed[i] = false;
		}
	}
	
	//moves the player if key is pressed
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		
		//loops through all the object in the object list to find the player object
		for (int i = 0; i < handler.objectList.size(); i++) {
			
			gameObjects temp = handler.objectList.get(i);
			

			if(temp.getName() == objectType.PLAYER) {
				//player will jump up and come back down
				if (key == KeyEvent.VK_UP && System.currentTimeMillis() - lastJumpTime > jumpDelay && !jumping || key == KeyEvent.VK_SPACE && System.currentTimeMillis() - lastJumpTime > jumpDelay && !jumping) {
					
					//resets the pressed status of the up button if it is already pressed
					keyPressed[0] = false;
					
					jumping = true;
					
					//delay between each jump
					player.jumpTime = System.currentTimeMillis();
						
					//time to prepare for jump
					while (g.gameState == states.GAME && System.currentTimeMillis() - player.jumpTime < player.prepJumpTime) {
						//System.out.println(System.currentTimeMillis() + "prep jump time: " + prepjumpTime);
					}
						
					//if the character is on the ground, make the go up a certain height at a certain speed
					if (temp.getLocationY() == game.HEIGHT - ground.height - player.height && !keyPressed[0]) {
						temp.setLocationY(game.HEIGHT - ground.height - player.height);
							
						while (g.gameState == states.GAME && temp.getLocationY() >= game.HEIGHT - ground.height - player.jumpHeight) {
							System.out.print("");
							jumpUp = true;
							temp.setSpeedY(-1*player.moveSpeedY);
						}
							
						//stop the character at a certain height above the ground
						temp.setSpeedY(0);
						temp.setLocationY(game.HEIGHT - ground.height - player.jumpHeight);
						jumpUp = false;
							
						//time at the peak of the jump
						double prevAirTime = System.currentTimeMillis();
							
							
						while (g.gameState == states.GAME && System.currentTimeMillis() - prevAirTime < player.airTime) {
							//System.out.println(System.currentTimeMillis() + " prev air time: " + prevAirTime);
							jumpDown = true;
							player.floatPosTime = System.currentTimeMillis();
						}
							
							
						//make the character fall back down to the ground
						while (g.gameState == states.GAME && temp.getLocationY() <= game.HEIGHT - ground.height - player.height) {
							System.out.print("");
							temp.setSpeedY(player.moveSpeedY);
						}
							
						//set the character back to the ground to avoid any discrepancy 
						temp.setSpeedY(0);
						temp.setLocationY(game.HEIGHT - ground.height - player.height);
						jumpDown = false;
							
						lastJumpTime = System.currentTimeMillis();
							
					}
						
					//does not allow the jump key to continuously run the code while the character is jumping
					jumping = false;

					keyPressed[0] = true;
					
				}
				//player moves right
				if (key == KeyEvent.VK_RIGHT && !jumping) {
					temp.setSpeedX(player.moveSpeedX);
					keyPressed[1] = true;
				}
				//player moves left
				if (key == KeyEvent.VK_LEFT) {
					temp.setSpeedX(-1 * player.moveSpeedX);
					keyPressed[2] = true;
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		//loops through all the object in the object list to find the player object
		for (int i = 0; i < handler.objectList.size(); i++) {
			
			gameObjects temp = handler.objectList.get(i);
			
			
			//if the specified key for the player is released
			if(temp.getName() == objectType.PLAYER) {
				//player will stop moving right
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_SPACE) {
					keyPressed[0] = false;
				}
				//player will stop moving right
				if (key == KeyEvent.VK_RIGHT) {
					keyPressed[1] = false;
				}
				//player will stop moving left
				if (key == KeyEvent.VK_LEFT) {
					keyPressed[2] = false;
				}
				
				//will not move if both right and left keys are pressed
				if (!keyPressed[1] && !keyPressed[2]) {
					temp.setSpeedX(0);
				}
				
			}
		}

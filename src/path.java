import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class path extends gameObjects {
	
	//transparency of the object
	float transparency = 1;
	
	//minimum transparency of the path
	double minTransparency;
	
	//keyInput handler
	private objectHandler handler;
	
	//menu object handler
	private menuObjectHandler menuHandler;
	
	//colour of the path
	private Color color;
	
	//jFrame
	gameWindow frame;
	
	//player
	player Player;
	
	//key handler
	keyInputs keyHandler;

	public path(objectType name, int locationX, int locationY, boolean add, objectHandler handler, Color color, double minTransparency, gameWindow frame, player Player, keyInputs keyHandler) {
		super(name, locationX, locationY, add);
		this.handler = handler;
		this.color = color;
		this.minTransparency = minTransparency;
		this.frame = frame;
		this.Player = Player;
		this.keyHandler = keyHandler;
	}
	
	public path(objectType name, int locationX, int locationY, boolean add, menuObjectHandler menuHandler, Color color, double minTransparency) {
		super(name, locationX, locationY, add);
		this.menuHandler = menuHandler;
		this.color = color;
		this.minTransparency = minTransparency;
	}

	public void updateObject(objectHandler handler) {
		if (transparency > minTransparency) {
			transparency -= minTransparency - 0.001;
		} else {
			handler.removeObject(this);
		}
		
	}
	
	public void updateObject() {
		
		//if the handler is used
		if (handler != null) {
			if (transparency > minTransparency) {
				transparency -= minTransparency - 0.001;
			} else {
				handler.removeObject(this);
			}
		
		// if the menu handler is used 
		} else {
			if (transparency > minTransparency) {
				transparency -= minTransparency - 0.005;
			} else {
				menuHandler.removeObject(this);
			}
		}
	}

	public void renderObject(Graphics2D g2d) {
		g2d.setComposite(makeTransparency(transparency));
		
		// sets the path for the animation
		if (handler != null) {
			g2d.setColor(color);
			ImageIcon charStanding = new ImageIcon ("src/pics/terra standing.png");
			ImageIcon charWalking = new ImageIcon("src/pics/terra walking.png");
			ImageIcon charPrepJump = new ImageIcon("src/pics/terra prepare jump.png");
			ImageIcon charJumpUp = new ImageIcon("src/pics/terra jump up.png");
			ImageIcon charJumpDown = new ImageIcon("src/pics/terra jump down.png");
			
			
			if (!keyHandler.jumping) {
				//duration for standing image
				if (!Player.walking && System.currentTimeMillis() - Player.walkingPosTime < Player.walkSpeed) {
					g2d.drawImage(charStanding.getImage(),locationX,locationY, player.width, player.height,frame);
			
					//duration for walking image
				} else if (Player.walking && System.currentTimeMillis() - Player.walkingPosTime < Player.walkSpeed) {
					g2d.drawImage(charWalking.getImage(),locationX,locationY, player.width, player.height,frame);
				
					//changes from standing to walking
				} else if (!Player.walking && System.currentTimeMillis() - Player.walkingPosTime > Player.walkSpeed) {
					g2d.drawImage(charWalking.getImage(),locationX,locationY, player.width, player.height,frame);
					Player.walking = true;
					Player.walkingPosTime = System.currentTimeMillis();
			
					//changes from walking to standing
				} else if (Player.walking && System.currentTimeMillis() - Player.walkingPosTime > Player.walkSpeed) {
					g2d.drawImage(charStanding.getImage(),locationX,locationY, player.width, player.height,frame);
					Player.walking = false;
					Player.walkingPosTime = System.currentTimeMillis();
				}
			} else {
				//preparing to jump animation
				if (System.currentTimeMillis() - player.jumpTime < player.prepJumpTime + 100) {
					g2d.drawImage(charPrepJump.getImage(),locationX,locationY, player.width, player.height,frame);
					
				//jumping up animation
				} else if (keyHandler.jumpUp) {
					g2d.drawImage(charJumpUp.getImage(),locationX,locationY, player.width, player.height,frame);
					
				//jumps down or on air animation
				} else if (keyHandler.jumpDown){
					//changes the floating positions of the characters
					//duration for float1 animation
					if (!Player.float2 && System.currentTimeMillis() - player.floatPosTime < Player.floatSpeed) {
						g2d.drawImage(charPrepJump.getImage(),locationX,locationY, player.width, player.height,frame);
					
					//duration for float2 animation
					} else if (Player.float2 && System.currentTimeMillis() - player.floatPosTime < Player.floatSpeed) {
						g2d.drawImage(charJumpDown.getImage(),locationX,locationY, player.width, player.height,frame);
						
					
					//changes from float1 to float2
					} else if (!Player.float2 && System.currentTimeMillis() - player.floatPosTime > Player.floatSpeed) {
						g2d.drawImage(charJumpDown.getImage(),locationX,locationY, player.width, player.height,frame);
						Player.float2 = true;
						player.floatPosTime = System.currentTimeMillis();
						
					//changes from float2 to float1
					} else if (Player.float2 && System.currentTimeMillis() - player.floatPosTime > Player.floatSpeed) {
						g2d.drawImage(charPrepJump.getImage(),locationX,locationY, player.width, player.height,frame);
						Player.float2 = false;
						player.floatPosTime = System.currentTimeMillis();
					}
				}
			}
			
		} else {
			g2d.setColor(color);
			g2d.fillOval(locationX, locationY, menuSnow.menuSnowWidth, menuSnow.menuSnowHeight);
		}	
		
		g2d.setComposite(makeTransparency(1));
	}
	
	private AlphaComposite makeTransparency(float transparency) {
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, transparency);
	}

}

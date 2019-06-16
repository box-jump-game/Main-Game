import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class player extends gameObjects{
	
	static int width = -50, height = 50;
	//speed of the player in each axis
	static int moveSpeedX = 3, moveSpeedY = 4;
	//time player stays in the air when jumping and height of the player's jump
	static int airTime= 250, jumpHeight = 200, prepJumpTime = 100;
	
	//object handler
	objectHandler handler;
	
	//character image
	ImageIcon charStanding = new ImageIcon ("src/pics/terra standing.png");
	ImageIcon charWalking = new ImageIcon("src/pics/terra walking.png");
	ImageIcon charPrepJump = new ImageIcon("src/pics/terra prepare jump.png");
	ImageIcon charJumpUp = new ImageIcon("src/pics/terra jump up.png");
	ImageIcon charJumpDown = new ImageIcon("src/pics/terra jump down.png");
	
	//JFrame
	gameWindow frame;
	
	//keyHandler
	keyInputs keyHandler;
	
	//walking position
	boolean walking = false;
	
	//floating positions
	boolean float2 = false;
	
	//time for each frame the character walks
	double walkingPosTime;
	
	//time for each frame the character floats when jumping
	static double floatPosTime;
	
	//walk speed
	double walkSpeed;
	
	//floating speed
	double floatSpeed;
	
	//jump time
	static double jumpTime;
	
	//game
	game Game;
	
	//constructor for the player
	public player(objectType name, int locationX, int locationY, boolean add, objectHandler handler, gameWindow frame, double walkingPosTime, keyInputs keyHandler, game Game) {
		super(name, locationX, locationY, add);
		this.Game = Game;
	
		walkSpeed = 250;
		floatSpeed = 200;
		jumpTime = 0;
		floatPosTime = 0;
		
		speedX = 0;
		speedY = 0;
		this.handler = handler;
		this.frame = frame;
		this.walkingPosTime = walkingPosTime;
		this.keyHandler = keyHandler;
	}
	
	//updates the speed of the player
	public void updateObject() {
		locationX += speedX;
		locationY += speedY;
		
		
		//set boundaries for the object
		if (locationX >= game.WIDTH - width) {
			setLocationX(game.WIDTH - width);
		}
		if  (locationX <= 0) {
			setLocationX(0);
		}
		if (locationY >= game.HEIGHT - height) {
			setLocationY(game.HEIGHT - height);
		}
		if  (locationY <= 0) {
			setLocationY(0);
		}
		
		//sets different attributes for the character depending on the game speed difficulty
		if (Game.gameSpeed == 1) {
			moveSpeedX = 2;
			moveSpeedY = 3;
			
			airTime = 100;
			jumpHeight = 200;
			
			walkSpeed = 300;
			floatSpeed = 250;
		} else if (Game.gameSpeed == 2) {
			moveSpeedX = 3;
			moveSpeedY = 4;
			
			airTime = 200;
			jumpHeight = 200;
			
			walkSpeed = 250;
			floatSpeed = 200;
			
			System.out.println("med");
		} else if (Game.gameSpeed == 3) {
			moveSpeedX = 4;
			moveSpeedY = 5;
			
			airTime = 200;
			jumpHeight = 200;
			
			walkSpeed = 200;
			floatSpeed = 150;
		}
	}
	
	//constantly redraws the player during each update
	public void renderObject(Graphics2D g2d) {
		handler.addObject(new path(objectType.PATH, locationX, locationY, false, handler, new Color(255,26,26), 0.08, frame, this, keyHandler));
		
		if (!keyHandler.jumping) {
			walking(g2d);
		} else {
			if (System.currentTimeMillis() - jumpTime < prepJumpTime + 100) {
				g2d.drawImage(charPrepJump.getImage(),locationX,locationY, width, height,frame);
			} else if (keyHandler.jumpUp) {
				g2d.drawImage(charJumpUp.getImage(),locationX,locationY, width, height,frame);
			} else if (keyHandler.jumpDown){
				floating(g2d);
			}
		}
	}
	
	//walking animation
	public void walking(Graphics2D g2d) {
		//changes the walking/standing positions of the characters
		//duration of standing image
		if (!walking && System.currentTimeMillis() - walkingPosTime < walkSpeed) {
			g2d.drawImage(charStanding.getImage(),locationX,locationY, width, height,frame);
		
		//duration of walking image
		} else if (walking && System.currentTimeMillis() - walkingPosTime < walkSpeed) {
			g2d.drawImage(charWalking.getImage(),locationX,locationY, width, height,frame);
			
		//changes from standing to walking
		} else if (!walking && System.currentTimeMillis() - walkingPosTime > walkSpeed) {
			g2d.drawImage(charWalking.getImage(),locationX,locationY, width, height,frame);
			walking = true;
			walkingPosTime = System.currentTimeMillis();
		
		//changes from walking to standing
		} else if (walking && System.currentTimeMillis() - walkingPosTime > walkSpeed) {
			g2d.drawImage(charStanding.getImage(),locationX,locationY, width, height,frame);
			walking = false;
			walkingPosTime = System.currentTimeMillis();
		}
	}
	
	//floating animation
	public void floating(Graphics2D g2d) {
		//changes the floating positions of the characters
		//duration of float1 image
		if (!float2 && System.currentTimeMillis() - floatPosTime < floatSpeed) {
			g2d.drawImage(charPrepJump.getImage(),locationX,locationY, width, height,frame);
		
		//duration of float2 image
		} else if (float2 && System.currentTimeMillis() - floatPosTime < floatSpeed) {
			g2d.drawImage(charJumpDown.getImage(),locationX,locationY, width, height,frame);
			
		//changes from float1 to float2	
		} else if (!float2 && System.currentTimeMillis() - floatPosTime > floatSpeed) {
			g2d.drawImage(charJumpDown.getImage(),locationX,locationY, width, height,frame);
			float2 = true;
			floatPosTime = System.currentTimeMillis();
		
		//changes from float2 to float1
		} else if (float2 && System.currentTimeMillis() - floatPosTime > floatSpeed) {
			g2d.drawImage(charPrepJump.getImage(),locationX,locationY, width, height,frame);
			float2 = false;
			floatPosTime = System.currentTimeMillis();
		}
	}
}

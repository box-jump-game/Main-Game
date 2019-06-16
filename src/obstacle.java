import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.ImageIcon;

public class obstacle extends gameObjects{
	
	//movement speed of the obstacle
	static int moveSpeedX = 4;
	//width and height of the obstacle
	static int width = 100, height = 100;
	
	//width and height of each type of tree
	//regular tree
	private int treeWidth = 80, treeHeight = 80; 
	//christmas tree
	private int chrTreeWidth = 40, chrTreeHeight = 70;
	
	//obstacle option
	int obstacleOpt;
	
	//minimum distance between each obstacle
	private int minObSpawnPos = 400;
	
	//position till next obstacle spawns
	static int nextObSpawnPos;
	
	//image for the obstacle
	ImageIcon tree;
	
	//object handler
	objectHandler handler;
	
	//score keeper
	gameStats score;
	
	//boolean for if the obstacle has already been passed
	boolean passed = false;
	
	//game
	game Game;
	

	public obstacle(objectType name, int locationX, int locationY, boolean add, objectHandler handler, gameStats score, boolean passed, game Game) {
		super(name, locationX, locationY, add);
		
		//changes the speed the obstacle moves depending on speed difficulty
		if (Game.gameSpeed == 1) {
			moveSpeedX = 4;
			minObSpawnPos = 500;
		} else if (Game.gameSpeed == 2) {
			moveSpeedX = 5;
			minObSpawnPos = 400;
		} else if (Game.gameSpeed == 3) {
			moveSpeedX = 6;
			minObSpawnPos = 300;
		}
		
		speedX = -1 * moveSpeedX;
		speedY = 0;
		this.handler = handler;
		this.score = score;
		this.passed = passed;
		this.Game = Game;
		
		Random r = new Random();
		obstacleOpt = r.nextInt(3) + 1;
		nextObSpawnPos= r.nextInt(6)*100 + minObSpawnPos;
	}
	
	//updates the position of the object
	public void updateObject() {
		locationX += speedX;
		locationY += speedY;
		
		// creates a new tree depending on the spawn rate
		if (this.getLocationX() < game.WIDTH - nextObSpawnPos && this.getAdd()) {
			//System.out.println(obstacle.nextObSpawnPos);
			this.setAdd(false);
			handler.addObject(new obstacle(objectType.TREE, game.WIDTH, game.HEIGHT - ground.height - obstacle.height, true,handler, score, false, Game));
		}

		// removes the tree obstacle if its x-position crosses the left side of the JFrame
		if (this.getLocationX() < 0 - obstacle.width) {
			// System.out.println("remove");
			handler.removeObject(this);
		}
		
		if (handler.getPlayer().getLocationX() > this.getLocationX() && !passed) {
			passed = true;
			score.setPoints(score.getPoints() + 1);
		}
	}

	//draws the object
	public void renderObject(Graphics2D g2d) {
		//creates a tree if the random option is 1
		if (obstacleOpt == 3) {
			setLocationY(game.HEIGHT - ground.height - treeHeight);
			width = treeWidth;
			height = treeHeight;
			tree = new ImageIcon("src/pics/tree2.png");
			//System.out.println("tree1");
			
			g2d.drawImage(tree.getImage(),locationX,locationY, width, height,game.frame);
		
		//creates a christmas tree if the random option is 2
		} else if (obstacleOpt == 2){
			setLocationY(game.HEIGHT - ground.height - chrTreeHeight);
			width = chrTreeWidth;
			height = chrTreeHeight;
			tree = new ImageIcon("src/pics/christmas tree 3.png");
			//System.out.println("tree2");
			
			g2d.drawImage(tree.getImage(),locationX,locationY, width, height,game.frame);
			
		//creates 3 christmas trees if the random option is 3
		} else {
			setLocationY(game.HEIGHT - ground.height - chrTreeHeight);
			width = 3*chrTreeWidth;
			height = chrTreeHeight;
			tree = new ImageIcon("src/pics/christmas tree 3.png");
			//System.out.println("tree3");
			
			for (int i = 0; i < 3; i++) {
				g2d.drawImage(tree.getImage(),locationX + i*chrTreeWidth,locationY, chrTreeWidth, height,game.frame);
			}
		}
	}
}

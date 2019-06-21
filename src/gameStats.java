import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class gameStats {
	
	private int points = 0;
	
	//dimensions of the pause button
	static int pauseWidth = 50, pauseHeight = 50;
	
	//coordinates of the pause button
	static int pausePosX = 25, pausePosY = 50;
	
	//dimensions of pause icon 
	static int pauseIconWidth = 10, pauseIconHeight = 25;
	
	//lives
	private int lives = 1;
	
	//pause status
	private boolean pause = false;
	
	//game
	game Game;
	
	//toggle for the pause button being hovered
	static boolean pauseHover = false;
	
	public gameStats(game Game){
		this.Game = Game;
	}
	
	public void update() {
	
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(new Color(255,255,255));
		g2d.setFont(new Font("TimesRoman",Font.BOLD, 30));
		g2d.drawString("Score: " + points, game.WIDTH - 150, 50);
		
		//draw pause button only in the game state
		if (Game.gameState == states.GAME) {
			g2d.setStroke(new BasicStroke(2));
			
			if (pauseHover) {
				g2d.setColor(new Color(0,102,34));
			} else {
				g2d.setColor(new Color(72,255,118));
			}
			
			g2d.fillRect(pausePosX, pausePosY, pauseWidth, pauseHeight);
			g2d.setColor(new Color(255,255,255));
			g2d.drawRect(pausePosX,pausePosY,pauseWidth,pauseHeight);
			
			//pause icon
			g2d.fillRect(pausePosX + pauseWidth/2 - pauseIconWidth - 3, pausePosY + pauseIconHeight/2, pauseIconWidth, pauseIconHeight);
			g2d.fillRect(pausePosX + pauseWidth/2 - pauseIconWidth + 15, pausePosY + pauseIconHeight/2, pauseIconWidth, pauseIconHeight);
		}
	}
	
	//get the points of the player
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	//get lives of the player
	public int getLives() {
		return lives;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	//pause status
	public boolean getPause() {
		return pause;
	}
	
	public void setPause(boolean pause) {
		this.pause = pause;
	}
}

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class gameStats {
	
	private int points = 0;
	
	public void update() {
	
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(new Color(255,255,255));
		g2d.setFont(new Font("TimesRoman",Font.BOLD, 30));
		g2d.drawString("Score: " + points, game.WIDTH - 150, 50);
	}
	
	//get the
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
}

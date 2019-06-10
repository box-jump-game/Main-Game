import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class instructionScreen extends informationalScreens{
	
	public void update() {
		
	}
	
	public void render(Graphics2D g2d) {
		super.render(g2d);
		//title of the instructions screen
		g2d.setColor(new Color(255,255,255));
		g2d.setFont(new Font("Arial", 1, 70));
		g2d.drawString("Instructions", game.WIDTH/2 - 195, 300);
		
		//instructions
		g2d.setFont(new Font("Arial", 4, 25));
		g2d.drawString("-Use the \'UP\' arrow key or \'SPACE BAR\' to jump over the trees", game.WIDTH/2 - 350, 400);
		g2d.drawString("-Use the \'LEFT\' and \'RIGHT\' arrow keys to move left and right", game.WIDTH/2 - 350, 450);
		g2d.drawString("Respectively", game.WIDTH/2 - 75, 475);
	}
	
}

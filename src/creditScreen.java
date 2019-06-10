import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class creditScreen extends informationalScreens{

	public void update() {
		
	}

	public void render(Graphics2D g2d) {
		super.render(g2d);
		//title
		g2d.setColor(new Color(255,255,255));
		g2d.setFont(new Font("Arial", 1, 70));
		g2d.drawString("Contributors", game.WIDTH/2 - 200, 200);
		
		//names
		g2d.setFont(new Font("Arial", 1, 35));
		g2d.drawString("Alex Au", (intContainerPosX + intContainerWidth)/3 - 50, 300);
		g2d.drawString("Joshua Noronha", 2*(intContainerPosX + intContainerWidth)/3 - 50, 300);
		g2d.drawString("Harman Sekhon", (intContainerPosX + intContainerWidth)/3 - 50, 500);
	}

}

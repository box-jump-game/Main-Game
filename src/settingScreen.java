import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class settingScreen extends informationalScreens{
	
	//if music is muted
	boolean muteMusic = false;
	
	//attributes for the mute button
	private int muteWidth = 75, muteHeight = 40;
	private int mutePosX = intContainerPosX + 50 + 200, mutePosY = 300 - muteWidth/2;
	
	//attributes for the slow option
	private int slowWidth = 75, slowHeight = 40;
	private int slowPosX = intContainerPosX + 50 + 200, slowPosY = 450 - slowWidth/2;
	
	//attributes for the medium option
	private int medWidth = 110, medHeight = 40;
	private int medPosX = slowPosX + slowWidth + 50, medPosY = slowPosY;
	
	//attributes for the hard option
	private int hardWidth = 75, hardHeight = 40;
	private int hardPosX = medPosX + medWidth + 50, hardPosY = slowPosY;
	
	//game
	game Game;
	
	public settingScreen(game Game) {
		this.Game = Game;
	}

	public void update() {
		
	}
	
	public void render(Graphics2D g2d) {
		super.render(g2d);
		
		//title
		g2d.setColor(new Color(179, 89, 0));
		g2d.setFont(new Font("Arial", 1, 70));
		g2d.drawString("Settings", game.WIDTH/2 - 150, 200);
		
		//volume
		g2d.setFont(new Font("Arial", 4, 30));
		g2d.setColor(new Color(179, 89, 0));
		g2d.drawString("Volume: ", intContainerPosX + 50, mutePosY + muteWidth/2);
		
		//speed
		g2d.setColor(new Color(179, 89, 0));
		g2d.drawString("Speed: ", intContainerPosX + 50, 450);
		
		
		g2d.setFont(new Font("Arial",1, 25));
		//mute button
		if (!muteMusic) {
			g2d.setColor(new Color(175, 165, 168));
		} else {
			g2d.setColor(new Color(53, 255, 53));
		}
		g2d.drawString("Mute", mutePosX + 10, mutePosY - 7+ muteWidth/2);
		
		//speed options
		//slow
		if (Game.gameSpeed == 1) {
			g2d.setColor(new Color(53, 255, 53));
		} else {
			g2d.setColor(new Color(175, 165, 168));
		}
		g2d.drawString("Slow", slowPosX + 10, slowPosY + slowHeight/2 + 8);
		
		//medium
		if (Game.gameSpeed == 2) {
			g2d.setColor(new Color(53, 255, 53));
		} else {
			g2d.setColor(new Color(175, 165, 168));
		}
		g2d.drawString("Medium", medPosX + 10, medPosY + medHeight/2 + 8);
		
		//hard
		if (Game.gameSpeed == 3) {
			g2d.setColor(new Color(53, 255, 53));
		} else {
			g2d.setColor(new Color(175, 165, 168));
		}
		g2d.drawString("Fast", hardPosX + 10, hardPosY + hardHeight/2 + 8);
		
	}
	
	//accessors
	//mute button
	public int getMuteWidth() {
		return muteWidth;
	}

	public int getMuteHeight() {
		return muteHeight;
	}
	
	public int getMutePosX() {
		return mutePosX;
	}
	
	public int getMutePosY() {
		return mutePosY;
	}
	
	//speed options
	public int getDiffPosY() {
		return slowPosY;
	}
	
	public int getEasyHardWidth() {
		return slowWidth;
	}
	
	//slow
	public int getSlowPosX() {
		return slowPosX;
	}
	
	public int getSlowHeight() {
		return slowHeight;
	}
	
	//medium
	public int getMedPosX() {
		return medPosX;
	}
	
	public int getMedWidth() {
		return medWidth;
	}
	
	public int getMedHeight() {
		return medHeight;
	}
	
	//fast
	public int getHardPosX() {
		return hardPosX;
	}
	
	public int getHardHeight() {
		return hardHeight;
	}
	
}

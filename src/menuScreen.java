import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;



public class menuScreen extends MouseAdapter{
	
	//dimensions of the buttons on the menu screen
	private int buttonWidth = 350, buttonHeight = 75;
	
	//space between each button
	private int buttonSpaceHeight = 25;
	
	//X-coordinates of the buttons
	private int buttonPosX = game.WIDTH/2 - buttonWidth/2;
	
	//Y-coordinates of the buttons
	private int  playPosY = 400, intPosY = playPosY + buttonSpaceHeight + buttonHeight, credPosY =  playPosY + 2*(buttonSpaceHeight + buttonHeight), exPosY =  playPosY + 3*(buttonSpaceHeight + buttonHeight);
	
	//game
	game Game;
	
	//object handler
	objectHandler handler;
	
	public void update() {
		
	}
	
	public void render(Graphics2D g2d) {
		g2d.setFont(new Font("Arial",1, 80));
		g2d.setColor(new Color(255,255,255));
		
		//Title
		g2d.drawString("Boxy Jump", game.WIDTH/2 - 210, 200);
		
		g2d.setFont(new Font("Arial",1, 40));
		
		//different buttons
		//play button
		g2d.setColor(new Color(0,230,115));
		g2d.fillRect(game.WIDTH/2 - buttonWidth/2, 400, buttonWidth, buttonHeight);
		g2d.setColor(new Color(0,153,0));
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRect(game.WIDTH/2 - buttonWidth/2, 400, buttonWidth, buttonHeight);
		g2d.drawString("Play", game.WIDTH/2 - 45, 400 + buttonHeight/2 + 10);
		
		
		//button for instructions to play game
		g2d.setColor(new Color(204,255,51));
		g2d.fillRect(buttonPosX, intPosY, buttonWidth, buttonHeight);
		g2d.setColor(new Color(115,153,0));
		g2d.drawRect(buttonPosX, intPosY, buttonWidth, buttonHeight);
		g2d.drawString("Instructions", game.WIDTH/2 - 105, 400 + buttonSpaceHeight + buttonHeight + buttonHeight/2 + 10 );
		
		//button showing contributors to game
		g2d.setColor(new Color(255,255,102));
		g2d.fillRect(buttonPosX, credPosY, buttonWidth, buttonHeight);
		g2d.setColor(new Color(128,128,0));
		g2d.drawRect(buttonPosX, credPosY, buttonWidth, buttonHeight);
		g2d.drawString("Credits", game.WIDTH/2 - 70, 400 + 2*(buttonSpaceHeight + buttonHeight) + buttonHeight/2 + 10 );
		
		//exit button
		g2d.setColor(new Color(255,102,0));
		g2d.fillRect(buttonPosX, exPosY, buttonWidth, buttonHeight);
		g2d.setColor(new Color(128,43,0));
		g2d.drawRect(buttonPosX, exPosY, buttonWidth, buttonHeight);
		g2d.drawString("Quit", game.WIDTH/2 - 45, 400 + 3*(buttonSpaceHeight + buttonHeight) + buttonHeight/2 + 10 );
	}
	
	//accessors for each private variables
	public int getButtonWidth() {
		return buttonWidth;
	}
	
	public int getButtonHeight() {
		return buttonHeight;
	}
	
	public int getButtonSpaceHeight() {
		return buttonSpaceHeight;
	}
	
	public int getButtonPosX() {
		return buttonPosX;
	}
	
	public int getPlayPosY() {
		return playPosY;
	}
	
	public int getintPosY() {
		return intPosY;
	}
	
	public int getCredPosY() {
		return credPosY;
	}
	
	public int getExPosY() {
		return exPosY;
	}

}

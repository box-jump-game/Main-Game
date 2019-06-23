import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;



public class menuScreen extends MouseAdapter{
	
	//dimensions of the buttons on the menu screen
	private int buttonWidth = 350, buttonHeight = 75;
	
	//space between each button
	private int buttonSpaceHeight = 25;
	
	//X-coordinates of the buttons
	private int buttonPosX = game.WIDTH/2 - buttonWidth/2;
	
	//Y-coordinates of the buttons
	private int  playPosY = 400, intPosY = playPosY + buttonSpaceHeight + buttonHeight, credPosY =  playPosY + 2*(buttonSpaceHeight + buttonHeight), exPosY =  playPosY + 3*(buttonSpaceHeight + buttonHeight);
	
	//attributes for the settings
	private int stWidth = 75, stHeight = 75;
	private int stPosX = 25, stPosY = game.HEIGHT - stHeight - 75;
	
	//toggles if the buttons are hovered
	boolean menPlaButton = false;
	boolean menInsButton = false;
	boolean menCredButton = false;
	boolean menExButton = false;
	boolean menSetButton = false;
	
	//game
	game Game;
	
	//JFrame
	gameWindow frame;
	
	//object handler
	objectHandler handler;
	
	//image of settings icon
	ImageIcon settingIcon = new ImageIcon("src/pics/settings icon.png");
	
	//constructor
	public menuScreen(gameWindow frame) {
		this.frame = frame;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics2D g2d) {
		g2d.setFont(new Font("Arial",1, 80));
		g2d.setColor(new Color(255,255,255));
		
		//Title
		g2d.drawString("Jumpy Christmas", game.WIDTH/2 - 320, 200);
		
		g2d.setFont(new Font("Arial",1, 40));
		
		//different buttons
		//play button
		//if the button is hovered
		if (menPlaButton) {
			g2d.setColor(new Color(0,153,0));
		} else {
			g2d.setColor(new Color(0,230,115));
		}
		
		g2d.fillRect(game.WIDTH/2 - buttonWidth/2, 400, buttonWidth, buttonHeight);
		g2d.setColor(new Color(0,153,0));
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRect(game.WIDTH/2 - buttonWidth/2, 400, buttonWidth, buttonHeight);
		
		if (menPlaButton) {
			g2d.setColor(new Color(0,230,115));
		}
		
		g2d.drawString("Play", game.WIDTH/2 - 45, 400 + buttonHeight/2 + 10);
		
		
		//button for instructions to play game
		if (menInsButton) {
			g2d.setColor(new Color(115,153,0));
		} else {
			g2d.setColor(new Color(204,255,51));
		}
		g2d.fillRect(buttonPosX, intPosY, buttonWidth, buttonHeight);
		g2d.setColor(new Color(115,153,0));
		g2d.drawRect(buttonPosX, intPosY, buttonWidth, buttonHeight);
		
		if (menInsButton) {
			g2d.setColor(new Color(204,255,51));
		}
		
		g2d.drawString("Instructions", game.WIDTH/2 - 105, 400 + buttonSpaceHeight + buttonHeight + buttonHeight/2 + 10 );
		
		//button showing contributors to game
		if (menCredButton) {
			g2d.setColor(new Color(128,128,0));
		} else {
			g2d.setColor(new Color(255,255,102));
		}
		g2d.fillRect(buttonPosX, credPosY, buttonWidth, buttonHeight);
		g2d.setColor(new Color(128,128,0));
		g2d.drawRect(buttonPosX, credPosY, buttonWidth, buttonHeight);
		
		if (menCredButton) {
			g2d.setColor(new Color(255,255,102));
		}
		
		g2d.drawString("Credits", game.WIDTH/2 - 70, 400 + 2*(buttonSpaceHeight + buttonHeight) + buttonHeight/2 + 10 );
		
		//exit button
		if (menExButton) {
			g2d.setColor(new Color(128,43,0));
		} else {
			g2d.setColor(new Color(255,102,0));
		}
		g2d.fillRect(buttonPosX, exPosY, buttonWidth, buttonHeight);
		g2d.setColor(new Color(128,43,0));
		g2d.drawRect(buttonPosX, exPosY, buttonWidth, buttonHeight);
		
		if (menExButton) {
			g2d.setColor(new Color(255,102,0));
		}
		
		g2d.drawString("Quit", game.WIDTH/2 - 45, 400 + 3*(buttonSpaceHeight + buttonHeight) + buttonHeight/2 + 10 );
		
		//settings button
		if (menSetButton) {
			g2d.setColor(new Color(0,230,0));
		} else {
			g2d.setColor(new Color(131,255,6));
		}
		g2d.setStroke(new BasicStroke(5));
		g2d.fillRect(stPosX, stPosY, stWidth, stHeight);
		g2d.drawImage(settingIcon.getImage(),stPosX,stPosY, stWidth, stHeight,frame);
		g2d.setColor(new Color(0,230,0));
		g2d.drawRect(stPosX, stPosY, stWidth, stHeight);
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
	
	//accessor for the settings button
	public int getStPosX() {
		return stPosX;
	}
	
	public int getStPosY() {
		return stPosY;
	}
	
	public int getStWidth() {
		return stWidth;
	}

	public int getStHeight() {
		return stHeight;
	}
}

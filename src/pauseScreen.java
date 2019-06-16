import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class pauseScreen extends informationalScreens{
	
	//retry button attributes
	int retryPosX, retryPosY, retryWidth, retryHeight;
	
	//continue button attributes
	int continuePosX, continuePosY, continueWidth, continueHeight;
	
	//mute button attrivutes
	int mutePosX, mutePosY, muteWidth, muteHeight;
	
	//if music is muted
	boolean muteMusic = false;
	
	public pauseScreen() {
		super();
		
		//container
		intContainerWidth = 500; 
		intContainerHeight = 300;
		intContainerPosX = game.WIDTH/2 - intContainerWidth/2;
		intContainerPosY = game.HEIGHT/2 - intContainerHeight/2;
		containerFillColor = new Color(255,204,102);
		containerColor = new Color(128,64,0);
		
		//back button
		backPosX = intContainerPosX + 25; 
		backPosY = intContainerPosY + intContainerHeight - 75;
		backWidth = 140;
		
		//retry button
		retryPosX = intContainerPosX + intContainerWidth - backWidth - 25;
		retryPosY = backPosY;
		retryWidth = backWidth;
		retryHeight = backHeight;
		
		//continue button
		continuePosX = intContainerPosX + intContainerWidth/2 - backWidth/2;
		continuePosY = backPosY;
		continueWidth = backWidth;
		continueHeight = backHeight;
		
		//mute button
		mutePosX = intContainerPosX + 25 + 115;
		mutePosY = intContainerPosY + 75 + 45;
		muteWidth = 75;
		muteHeight = 40;
		
	}

	public void update() {
		
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(new Color(102,224,255,77));
		g2d.fillRect(0, 0, game.WIDTH, game.HEIGHT);
		
		super.render(g2d);
		
		//title
		g2d.setFont(new Font("Arial",1, 60));
		g2d.drawString("Pause", game.WIDTH/2 - 90, intContainerPosY + 75);
		
		//volume
		g2d.setFont(new Font("Arial",4, 25));
		g2d.setColor(new Color(185, 100, 15));
		g2d.drawString("Volume: ", intContainerPosX + 25, intContainerPosY + 75 + 75);
		
		//mute button
		g2d.setFont(new Font("Arial",1, 25));
		
		//depends if music is muted or not
		if (!muteMusic) {
			g2d.setColor(new Color(175, 165, 168));
		} else {
			g2d.setColor(new Color(53, 255, 53));
		}
		
		g2d.drawString("Mute", intContainerPosX + 25 + 125, intContainerPosY + 75 + 75);
		g2d.setColor(new Color(255, 255, 255));
		//g2d.drawRect(mutePosX, mutePosY, muteWidth, muteHeight);
		
		//retry button
		makeButton(g2d, retryPosX, retryPosY, retryWidth, retryHeight, new Color(255,255,102), new Color(255, 166,77), new Color(102,102,0), backFont);
		g2d.drawString("Retry", intContainerPosX + intContainerWidth - backWidth/2 - 25 - 45, backPosY + backHeight - 15);
		
		//continue button
		makeButton(g2d, continuePosX, continuePosY, continueWidth, continueHeight, new Color(77, 255, 77), new Color(0,153,0), new Color(32,96,32), backFont);
		g2d.setFont(new Font("Arial",1, 27));
		g2d.drawString("Continue", continuePosX + 10, backPosY + backHeight - 15);
	}
	
	//accessors
	//retry button attributes
	public int getRetryPosX() {
		return retryPosX;
	}
	
	public int getRetryPosY() {
		return retryPosY;
	}
	
	public int getRetryWidth() {
		return retryWidth;
	}
	
	public int getRetryHeight() {
		return retryHeight;
	}
	
	//continue button attributes
	public int getContinuePosX() {
		return continuePosX;
	}
	
	public int getContinuePosY() {
		return continuePosY;
	}

	public int getContinueWidth() {
		return continueWidth;
	}
	
	public int getContinueHeight() {
		return continueHeight;
	}
	
	//mute button
	public int getMutePosX() {
		return mutePosX;
	}
	
	public int getMutePosY() {
		return mutePosY;
	}
	
	public int getMuteWidth() {
		return muteWidth;
	}
	
	public int getMuteHeight() {
		return muteHeight;
	}
}

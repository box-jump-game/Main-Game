import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class gameOver extends informationalScreens{
	
	//game stats
	gameStats stats;
	
	//objectHandler
	objectHandler handler;
	
	//retry button
	int retryPosX,retryPosY, retryWidth, retryHeight;
	
	public gameOver(gameStats stats, objectHandler handler){
		super();
		this.stats = stats;
		this.handler = handler;
		intContainerWidth = 600; 
		intContainerHeight = 500;
		intContainerPosX = game.WIDTH/2 - intContainerWidth/2;
		intContainerPosY = game.HEIGHT/2 - intContainerHeight/2;
		//containerFillColor  = new Color(204,102,0);
		
		//back button
		backPosX = intContainerPosX + 25; 
		backPosY = intContainerPosY + intContainerHeight - 75;
		
		//retry button
		retryPosX = intContainerPosX + intContainerWidth - backWidth - 25;
		retryPosY = backPosY;
		retryWidth = backWidth;
		retryHeight = backHeight;
	}

	
	public void render(Graphics2D g2d) {
		g2d.setColor(new Color(102,224,255,77));
		g2d.fillRect(0, 0, game.WIDTH, game.HEIGHT);
		
		super.render(g2d);
		
		//title
		g2d.setFont(new Font("Arial",1, 40));
		g2d.drawString("Game Over", game.WIDTH/2 - 110, intContainerPosY + 75);
				
		//score
		g2d.setFont(new Font("Arial", 4, 40));
		g2d.drawString("Your score:" + stats.getPoints(), game.WIDTH/2 - 110, intContainerPosY + 75 + 100);
		
		//retry button
		makeButton(g2d, retryPosX, retryPosY, retryWidth, retryHeight, new Color(255,255,102), new Color(255, 166,77), new Color(102,102,0), backFont);
		g2d.drawString("retry", intContainerPosX + intContainerWidth - backWidth/2 - 25 - 40, backPosY + backHeight - 15);
	}



	public void update() {
		//handler.removeAll();
	}
	
	//accesosrs
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
}

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public abstract class informationalScreens {
	//dimensions of the instructions container
	protected int intContainerWidth = 800, intContainerHeight = 700;
	
	//coordinates of the instructions container
	protected int intContainerPosX = game.WIDTH/2 - intContainerWidth/2, intContainerPosY = game.HEIGHT/2 - intContainerHeight/2;
	
	//dimensions of the back button
	protected int backWidth = 150, backHeight = 50;
	
	//coordinates of the back button
	protected int backPosX = intContainerPosX + 25, backPosY = intContainerPosY + intContainerHeight - 75;
	
	//colors of the shapes
	protected Color containerColor = new Color(128,64,0),containerFillColor = new Color(255,204,102), backColor = new Color(0,77,128), backFillColor = new Color(153,230,255), backFontColor = new Color(0,77,128);
	
	//font size of the menu button
	protected Font backFont = new Font("Arial", 1, 35);
	
	//toggle for the menu button
	protected boolean infMenButton = false;
	
	//updates actions in the screen
	public abstract void update();
	
	
	//renders all images in the screen
	public void render(Graphics2D g2d) {
		//container for the information
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(containerFillColor);
		g2d.fillRect(intContainerPosX, intContainerPosY, intContainerWidth, intContainerHeight);
		g2d.setColor(containerColor);
		g2d.drawRect(intContainerPosX, intContainerPosY, intContainerWidth, intContainerHeight);
		
		//menu button
		makeButton(g2d, backPosX, backPosY, backWidth, backHeight, backFillColor, backColor, backFontColor, backFont, infMenButton);
		g2d.drawString("Menu", backPosX + backWidth/2 - 42, backPosY + backHeight/2 + 12);
	}
	
	//makes a button
	public void makeButton(Graphics2D g2d, int posX, int posY, int width, int height, Color fillColor, Color lineColor, Color fontColor, Font font, boolean buttonHover) {
		//if the button is hovered, change fill color
		if (buttonHover) {
			g2d.setColor(lineColor);
		} else {
			g2d.setColor(fillColor);
		}
		g2d.setFont(font);
		g2d.fillRect(posX, posY, width, height);
		g2d.setColor(lineColor);
		g2d.drawRect(posX, posY, width, height);
		
		//if the button is hovered, change font color
		if (buttonHover) {
			g2d.setColor(fillColor);
		} else {
			g2d.setColor(fontColor);
		}
	}
	//accessors
	public int getBackWidth() {
		return backWidth;
	}
	
	public int getBackHeight() {
		return backHeight;
	}
	
	public int getBackPosX() {
		return backPosX;
	}
	
	public int getBackPosY() {
		return backPosY;
	}
}

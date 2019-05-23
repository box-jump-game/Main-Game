import java.awt.*;

public class player implements drawings{

	Color pColor;
	Color pOutlineColor;
	float pOutlineWidth;
	int pWidth;
	int pHeight;
	int positionX = 450;
	
	//constructor for the player
	public player(Color inPColor, Color inPOutlineColor, int inPOutlineWidth, int inPHeight, int inPWidth) {
		pColor = inPColor;
		pOutlineColor = inPOutlineColor;
		pOutlineWidth = inPOutlineWidth;
		pHeight = inPHeight;
		pWidth = inPWidth;
	}
	
	//creates the character
	/*
	 * @param drawer object used to draw the player
	 * @param windowWidth width of the game window
	 * @param windowHeight height of the game window
	 */
	public void create(Graphics2D drawer) {
		drawer.setColor(pColor);
		drawer.fillRect(positionX, windowY-350, pWidth, pHeight);
		
		//outline for the character
		drawer.setColor(pOutlineColor);
		drawer.setStroke(new BasicStroke(pOutlineWidth));
		drawer.drawRect(positionX, windowY-350, pWidth, pHeight);
	}
	
	//moves the character left
	public void moveLeft() {
		positionX-=2;
	}
	
	//moves the character right
	public void moveRight() {
		positionX+=2;
	}
}


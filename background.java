import java.awt.*;

public class background implements drawings{
	
	Color bgColor;
	
	//contructor for the background
	public background(Color inBgColor) {
		bgColor = inBgColor;
	}
	
	//create the background
	public void create(Graphics2D drawer) {
		drawer.setColor(bgColor);
		drawer.fillRect(0,0,windowX, windowY);
		
	}
}

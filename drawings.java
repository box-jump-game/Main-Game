import java.awt.Graphics2D;

public interface drawings {
	
	//width of the game window
	int windowX = gameWindow.windowWidth;
	
	//height of the game window
	int windowY = gameWindow.windowHeight;
	
	//creates the drawing
	void create(Graphics2D drawer);
}

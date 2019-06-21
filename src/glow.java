import java.awt.Color;
import java.awt.Graphics2D;

public class glow extends gameObjects{
	
	//mouse
	mouseInputs mouse;
	
	//dimensions
	int glowWidth, glowHeight;
	
	//check if the glow is already created
	static boolean glowCreated = false;
	
	//check if cursor is hovering
	static boolean cursorHover;

	public glow(objectType name, int locationX, int locationY, boolean add, mouseInputs mouse) {
		super(name, locationX, locationY, add);
		
		this.mouse = mouse;
		
		glowWidth = 50;
		glowHeight = 50;
		
		cursorHover = false;
	}

	public void updateObject() {
		locationX = mouse.mouseX - 9;
		locationY = mouse.mouseY - 10;
	}

	@Override
	public void renderObject(Graphics2D g2d) {
		if (cursorHover) {
			g2d.setColor(new Color(255,77,77,77));
		} else {
			g2d.setColor(new Color(51,255,51,77));
		}
		g2d.fillOval(locationX, locationY, glowWidth, glowHeight);
	}

}

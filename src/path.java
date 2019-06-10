import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class path extends gameObjects {
	
	//transparency of the object
	float transparency = 1;
	
	//minimum transparency of the path
	double minTransparency;
	
	//keyInput handler
	private objectHandler handler;
	
	//menu object handler
	private menuObjectHandler menuHandler;
	
	//colour of the path
	private Color color;

	public path(objectType name, int locationX, int locationY, boolean add, objectHandler handler, Color color, double minTransparency) {
		super(name, locationX, locationY, add);
		this.handler = handler;
		this.color = color;
		this.minTransparency = minTransparency;
	}
	
	public path(objectType name, int locationX, int locationY, boolean add, menuObjectHandler menuHandler, Color color, double minTransparency) {
		super(name, locationX, locationY, add);
		this.menuHandler = menuHandler;
		this.color = color;
		this.minTransparency = minTransparency;
	}

	public void updateObject(objectHandler handler) {
		if (transparency > minTransparency) {
			transparency -= minTransparency - 0.001;
		} else {
			handler.removeObject(this);
		}
		
	}
	
	public void updateObject() {
		
		//if the handler is used
		if (handler != null) {
			if (transparency > minTransparency) {
				transparency -= minTransparency - 0.001;
			} else {
				handler.removeObject(this);
			}
		
		// if the menu handler is used 
		} else {
			if (transparency > minTransparency) {
				transparency -= minTransparency - 0.001;
			} else {
				menuHandler.removeObject(this);
			}
		}
	}

	public void renderObject(Graphics2D g2d) {
		g2d.setComposite(makeTransparency(transparency));
		
		if (handler != null) {
			g2d.setColor(color);
			g2d.fillRect(locationX, locationY, player.width, player.height);
		} else {
			g2d.setColor(color);
			g2d.fillOval(locationX, locationY, menuSnow.menuSnowWidth, menuSnow.menuSnowHeight);
		}	
		
		g2d.setComposite(makeTransparency(1));
	}
	
	private AlphaComposite makeTransparency(float transparency) {
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, transparency);
	}

}

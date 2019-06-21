import java.awt.*;
import javax.swing.*;

public class gameWindow extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3431428519140837998L;
	
	//window
	JFrame windowGUI;
	
	//toolkit for the cursor image
	Toolkit toolkit;
	
	//image for the default cursor, pointing cursor and selected cursor
	Image defaultIcon, defaultIcon2, pointingIcon, selectIcon;
	
	//default cursor and pointing cursor
	Cursor defCursor, defCursor2, pointCursor, selectCursor;
	
	//time for each action of the default cursor and time to change each action in the default cursor
	double defCursorTime, defCursorChangeTime;
	
	//check if the defaultCursor image 2 is on and check if the default cursor is in used
	boolean defCursor2Check, defCursorUse;
	
	//constructor to create the JFrame
	public gameWindow(int width, int height, String title, game boxGame) {
		//window for the game
		windowGUI = new JFrame(title);
		
		//toolkit
		toolkit = Toolkit.getDefaultToolkit();
		
		//time for the default cursor actions to change
		defCursorChangeTime = 500;
		
		//status if the default cursor 2 image is displayed
		defCursor2Check = false;
		
		//status if the default cursor action is used
		defCursorUse = true;
		
		//image of the cursor
		defaultIcon = toolkit.getImage("src/pics/pointer icons/penguin default pointer.png");
		defaultIcon2 = toolkit.getImage("src/pics/pointer icons/penguin default pointer2.png");
		pointingIcon = toolkit.getImage("src/pics/pointer icons/penguin pointing.png");
		selectIcon = toolkit.getImage("src/pics/pointer icons/penguin clicked.png");
		
		//default cursor icon
		defCursor = toolkit.createCustomCursor(defaultIcon, new Point(windowGUI.getX(), windowGUI.getY()), "default penguin");
		defCursor2 = toolkit.createCustomCursor(defaultIcon2, new Point(windowGUI.getX(), windowGUI.getY()), "default penguin2");
		
		//pointing cursor icon
		pointCursor = toolkit.createCustomCursor(pointingIcon, new Point(windowGUI.getX(), windowGUI.getY()), "pointing penguin");
		
		//selected cursor icon
		selectCursor = toolkit.createCustomCursor(selectIcon, new Point(windowGUI.getX(), windowGUI.getY()), "clicking penguin");
		
		//attributes for the window
		windowGUI.setPreferredSize(new Dimension(width,height));
		windowGUI.setMaximumSize(new Dimension(width, height));
		windowGUI.setMinimumSize(new Dimension(width, height));
		
		windowGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowGUI.setResizable(false);
		windowGUI.setLocationRelativeTo(null);
		
		//windowGUI.add(startButton);
		windowGUI.setCursor(defCursor);
		defCursorTime = System.currentTimeMillis();
		
		windowGUI.add(boxGame);
		windowGUI.setVisible(true);
		boxGame.start();
	}
	
	public JFrame getWindow() {
		return windowGUI;
	}
	
	//sets the default cursor walking action
	public void setDefaultCursor() {
		if (System.currentTimeMillis() - defCursorTime < defCursorChangeTime && !defCursor2Check) {
			windowGUI.setCursor(defCursor);
			
		} else if (System.currentTimeMillis() - defCursorTime < defCursorChangeTime && defCursor2Check) {
			windowGUI.setCursor(defCursor2);
			
		} else if (System.currentTimeMillis() - defCursorTime > defCursorChangeTime && !defCursor2Check) {
			defCursor2Check = true;
			windowGUI.setCursor(defCursor2);
			defCursorTime = System.currentTimeMillis();
			
		} else if (System.currentTimeMillis() - defCursorTime > defCursorChangeTime && defCursor2Check) {
			defCursor2Check = false;
			windowGUI.setCursor(defCursor);
			defCursorTime = System.currentTimeMillis();
		}
		
	}
}

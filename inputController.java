import java.awt.Component;
import java.awt.event.*;

public class inputController implements KeyListener {
	
	//array that tells whether a key is pressed
	boolean[] keys = new boolean[256];
	
	
	/*
	 * -constructor for the inputController
	 * -creates a keyListner ---> will record the user's keyboard input
	 * @param c keyboard to get the input from
	 */
	public inputController(Component c) {
		c.addKeyListener(this);
		
		//default initializes all the keys in the keys[] array to not be pressed
		for(int i = 0; i < keys.length; i++) {
			keys[i] = false;
		}
	}
	
	
	
	/*
	 * -Checks whether a key is pressed
	 * Precondition: key code is in between 0-256 
	 * 
	 * @param keyCode the key code for the key that is pressed
	 * @return tells true or false for whether a key is pressed
	 */
	public boolean keyDown(int keyCode) {
		if (keyCode > 0 && keyCode < 256) {
			return keys[keyCode];
		}
		
		return false;
	}
	
	/*
	 * Precondition: keycode is in between 0-256
	 * 
	 * -called when a key in the component is pressed
	 *@param e the key that is being pressed in the component
	 */
	public void keyPressed(KeyEvent e) {
		int pressed = e.getKeyCode();
		
		if(pressed > 0 && pressed < 256) {
			keys[pressed] = true;
		}
	}
	
	/*
	 * Precondition: keycode is in between 0-256
	 * 
	 * -called when a key in the component is released
	 * @param e the key that is released from being pressed in the component
	 */
	public void keyReleased(KeyEvent e) {
		int released = e.getKeyCode();
		
		if(released > 0 && released < 256) {
			keys[released] = false;
		}
	}
	
	/*
	 * -remembers the keys that are typed by the component
	 * @ param e the key that has been typed in the component
	 * 
	 * *****WILL NOT BE USED
	 */
	public void keyTyped(KeyEvent e) {
		
	}
}

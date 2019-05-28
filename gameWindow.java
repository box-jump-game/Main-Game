import java.awt.*;
import javax.swing.*;

public class gameWindow extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3431428519140837998L;
	
	//constructor to create the JFrame
	public gameWindow(int width, int height, String title, game boxGame) {

		//window for the game
		JFrame windowGUI = new JFrame(title);
		//////test button////////
		JButton startButton = new JButton("Start");
		
		/////dimensions for the test button////////
		startButton.setPreferredSize(new Dimension(100,100));
		startButton.setBounds(120,150,100,100);
		startButton.setBackground(new Color(255,255,255));
		
		//attributes for the window
		windowGUI.setPreferredSize(new Dimension(width,height));
		windowGUI.setMaximumSize(new Dimension(width, height));
		windowGUI.setMinimumSize(new Dimension(width, height));
		
		windowGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowGUI.setResizable(false);
		windowGUI.setLocationRelativeTo(null);
		
		//windowGUI.add(startButton);
		windowGUI.add(boxGame);
		windowGUI.setVisible(true);
		boxGame.start();
	}
}


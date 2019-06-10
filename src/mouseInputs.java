import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mouseInputs extends MouseAdapter{
	
	//the game
	private game Game;
	
	//the object handler
	private objectHandler handler;
	
	//the menu screen
	private menuScreen menu;
	
	//instruction screen
	private instructionScreen instructions;
	
	//credit screen
	private creditScreen credits;
	
	public mouseInputs(game Game, objectHandler handler, menuScreen menu, instructionScreen instructions, creditScreen credits) {
		this.Game = Game;
		this.handler = handler;
		this.menu = menu;
		this.instructions = instructions;
		this.credits = credits;
	}
	
	
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY= e.getY();
		
		//in the menu screen
		if (Game.gameState == game.states.MENU) {
			//if the user pressed the play button
			if (mouseOver(mouseX, mouseY, menu.getButtonPosX(), menu.getPlayPosY(), menu.getButtonWidth(), menu.getButtonHeight())) {
				Game.gameState = game.states.GAME;
				Game.startGame();
			}
			
			//if the user presses the instructions button
			if (mouseOver(mouseX, mouseY, menu.getButtonPosX(), menu.getintPosY(), menu.getButtonWidth(), menu.getButtonHeight())) {
				Game.gameState = game.states.INSTRUCTIONS;
			}
			
			//if the user presses the credits button
			if (mouseOver(mouseX, mouseY, menu.getButtonPosX(), menu.getCredPosY(), menu.getButtonWidth(), menu.getButtonHeight())) {
				Game.gameState = game.states.CREDITS;
			}
			
			//if the user presses the exit button
			if (mouseOver(mouseX, mouseY, menu.getButtonPosX(), menu.getExPosY(), menu.getButtonWidth(), menu.getButtonHeight())) {
				System.exit(1);
			}
		}
		
		//in the instructions screen
		if (Game.gameState == game.states.INSTRUCTIONS) {
			//if the user pressed the menu button
			if (mouseOver(mouseX, mouseY, instructions.getBackPosX(), instructions.getBackPosY(), instructions.getBackWidth(), instructions.getBackHeight())) {
				Game.gameState = game.states.MENU;
			}
		}
		
		//in the credits screen
		if (Game.gameState == game.states.CREDITS) {
			//if the user pressed the menu button
			if (mouseOver(mouseX, mouseY, credits.getBackPosX(), credits.getBackPosY(), credits.getBackWidth(), credits.getBackHeight())) {
				Game.gameState = game.states.MENU;
			}
		}
		
	
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	//checks if the mouse is over a button
	private boolean mouseOver(int mouseX, int mouseY, int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
		if (mouseX > buttonX && mouseX < buttonX + buttonWidth && mouseY > buttonY && mouseY < buttonY + buttonHeight) {
			return true;
		} else {
			return false;
		}
	}
}

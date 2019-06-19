import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
	
	//game over screen
	private gameOver end;
	
	//pause screen
	private pauseScreen pause;
	
	//score
	private gameStats score;
	
	//menu handler
	private menuObjectHandler menuHandler;
	
	//music player
	private playMusic music;
	
	//player
	private player Player;
	
	//setting screen
	private settingScreen settings;
	
	//random generator
	private Random r;
	
	public mouseInputs(game Game, objectHandler handler, menuScreen menu, instructionScreen instructions, creditScreen credits, gameOver end, gameStats score, pauseScreen pause, menuObjectHandler menuHandler, playMusic music, player Player, settingScreen settings) {
		this.Game = Game;
		this.handler = handler;
		this.menu = menu;
		this.instructions = instructions;
		this.credits = credits;
		this.end = end;
		this.score = score;
		this.pause = pause;
		this.menuHandler = menuHandler;
		this.music  = music;
		this.Player = Player;
		this.settings = settings;
		
		r = new Random();
	}
	
	
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY= e.getY();
		
		//in the menu screen
		if (Game.gameState == states.MENU) {
			//if the user pressed the play button
			if (mouseOver(mouseX, mouseY, menu.getButtonPosX(), menu.getPlayPosY(), menu.getButtonWidth(), menu.getButtonHeight())) {
				Game.gameState = states.GAME;
				Game.startGame();
				Player.setLocationX(game.WIDTH/2 - player.width);
				score.setPoints(0);
				
				try {
					music.setfilePath("src/music/Wintersport_2019-M1X__eUKGAk.wav");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
				
				if (pause.muteMusic) {
					music.pause();
				} else {
					music.play();
				}
			}
			
			//if the user presses the instructions button
			if (mouseOver(mouseX, mouseY, menu.getButtonPosX(), menu.getintPosY(), menu.getButtonWidth(), menu.getButtonHeight())) {
				Game.gameState = states.INSTRUCTIONS;
			}
			
			//if the user presses the credits button
			if (mouseOver(mouseX, mouseY, menu.getButtonPosX(), menu.getCredPosY(), menu.getButtonWidth(), menu.getButtonHeight())) {
				Game.gameState = states.CREDITS;
			}
			
			//if the user presses the exit button
			if (mouseOver(mouseX, mouseY, menu.getButtonPosX(), menu.getExPosY(), menu.getButtonWidth(), menu.getButtonHeight())) {
				System.exit(1);
			}
			
			//if the user presses the settings button
			if (mouseOver(mouseX, mouseY, menu.getStPosX(), menu.getStPosY(), menu.getStWidth(), menu.getStHeight())) {
				Game.gameState = states.SETTINGS;
			}
		}
		
		//in the instructions screen
		if (Game.gameState == states.INSTRUCTIONS) {
			//if the user pressed the menu button
			if (mouseOver(mouseX, mouseY, instructions.getBackPosX(), instructions.getBackPosY(), instructions.getBackWidth(), instructions.getBackHeight())) {
				Game.gameState = states.MENU;
			}
		}
		
		//in the credits screen
		if (Game.gameState == states.CREDITS) {
			//if the user pressed the menu button
			if (mouseOver(mouseX, mouseY, credits.getBackPosX(), credits.getBackPosY(), credits.getBackWidth(), credits.getBackHeight())) {
				Game.gameState = states.MENU;
			}
		}
		
		//in the game screen
		if (Game.gameState == states.GAME) {
			//if the user pressed the pause button
			if (mouseOver(mouseX, mouseY, gameStats.pausePosX, gameStats.pausePosY, gameStats.pauseWidth, gameStats.pauseHeight)) {
				score.setPause(true);
				Game.gameState = states.PAUSE;
			}
		}
		
		//in the game over screen
		if (Game.gameState == states.GAMEOVER) {
			//if the user pressed the menu button
			if (mouseOver(mouseX, mouseY, end.getBackPosX(), end.getBackPosY(), end.getBackWidth(), end.getBackHeight())) {
				Game.gameState = states.MENU;
				for (int k = 0; k < 100; k++) {
					handler.removeAll();
				}
				score.setLives(1);
				menuHandler.addObject(new menuSnow(objectType.MENUSNOW, r.nextInt(game.WIDTH), 100, true, menuHandler));
				try {
					music.setfilePath("src/music/No_Copyright_Christmas_Background_Music_For_Videos_-_by_AShamaluevMusic-gm3puQtwXcg.wav");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
				
				//mute music if needed
				if (pause.muteMusic) {
					music.pause();
				} else {
					music.play();
				}
			
			}
			
			//if the user pressed the retry button, restarts the game
			if (mouseOver(mouseX, mouseY, end.getRetryPosX(), end.getRetryPosY(), end.getRetryWidth(), end.getRetryHeight())) {
				for (int k = 0; k < 100; k++) {
					handler.removeAll();
				}
				score.setPoints(0);
				Game.gameState = states.GAME;
				Game.startGame();
				score.setLives(1);
				Player.setLocationX(game.WIDTH/2 - player.width);
			}
		}
		
		//in the pause screen
		if (Game.gameState == states.PAUSE) {
			//if the user pressed the menu button
			if (mouseOver(mouseX, mouseY, pause.getBackPosX(), pause.getBackPosY(), pause.getBackWidth(), pause.getBackHeight())) {
				Game.gameState = states.MENU;
				for (int k = 0; k < 100; k++) {
					handler.removeAll();
				}
				score.setPause(false);
				menuHandler.addObject(new menuSnow(objectType.MENUSNOW, r.nextInt(game.WIDTH), 100, true, menuHandler));
				try {
					music.setfilePath("src/music/No_Copyright_Christmas_Background_Music_For_Videos_-_by_AShamaluevMusic-gm3puQtwXcg.wav");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
				
				//mute music if needed
				if (pause.muteMusic) {
					music.pause();
				} else {
					music.play();
				}
			}
			
			//if the user pressed the retry button
			if (mouseOver(mouseX, mouseY, pause.getRetryPosX(), pause.getRetryPosY(), pause.getRetryWidth(), pause.getRetryHeight())) {
				for (int k = 0; k < 100; k++) {
					handler.removeAll();
				}
				score.setPoints(0);
				Game.gameState = states.GAME;
				Game.startGame();
				score.setPause(false);
				Player.setLocationX(game.WIDTH/2 - player.width);
			}
			
			//if the user pressed the continue button
			if (mouseOver(mouseX, mouseY, pause.getContinuePosX(), pause.getContinuePosY(), pause.getContinueWidth(), pause.getContinueHeight())) {
				Game.gameState = states.GAME;
				score.setPause(false);
			}
			
			//if the user pressed the mute button
			if (mouseOver(mouseX, mouseY, pause.getMutePosX(), pause.getMutePosY(), pause.getMuteWidth(), pause.getMuteHeight())) {
				//toggles between mute and unmute of music
				if (!pause.muteMusic) {
					music.pause();
					pause.muteMusic = true;
					settings.muteMusic = true;
				} else {
					try {
						music.restart();
					} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e1) {
						e1.printStackTrace();
					}
					music.play();
					pause.muteMusic = false;
					settings.muteMusic = false;
				}
			}
			
		}
		
		//in the settings screen
		if (Game.gameState == states.SETTINGS) {
			//if the user pressed the menu button
			if (mouseOver(mouseX, mouseY, settings.getBackPosX(), settings.getBackPosY(), settings.getBackWidth(), settings.getBackHeight())) {
				Game.gameState = states.MENU;
			}
			
			//if the user pressed the mute button
			if (mouseOver(mouseX, mouseY, settings.getMutePosX(), settings.getMutePosY(), settings.getMuteWidth(), settings.getMuteHeight())) {
				//toggles between mute and unmute
				if (!pause.muteMusic) {
					music.pause();
					settings.muteMusic = true;
					pause.muteMusic = true;
				} else {
					try {
						music.restart();
					} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e1) {
						e1.printStackTrace();
					}
					music.play();
					settings.muteMusic = false;
					pause.muteMusic = false;
				}
			}
			
			//if the user pressed the slow option
			if (mouseOver(mouseX, mouseY, settings.getSlowPosX(), settings.getDiffPosY(), settings.getEasyHardWidth(), settings.getSlowHeight())) {
				Game.gameSpeed = 1;
			}
			
			//if the user pressed the medium option
			if (mouseOver(mouseX, mouseY, settings.getMedPosX(), settings.getDiffPosY(), settings.getMedWidth(), settings.getMedHeight())) {
				Game.gameSpeed = 2;
			}
			
			//if the user pressed the hard option
			if (mouseOver(mouseX, mouseY, settings.getHardPosX(), settings.getDiffPosY(), settings.getEasyHardWidth(), settings.getHardHeight())) {
				Game.gameSpeed = 3;
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

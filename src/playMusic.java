import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class playMusic {
	long currentFrame;
	Clip clip;
	
	//status of the music
	String status;
	
	AudioInputStream audioInputStream;
	static String filePath = "src/music/No_Copyright_Christmas_Background_Music_For_Videos_-_by_AShamaluevMusic-gm3puQtwXcg.wav";
	
	//constructor
	public playMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		//import the audio file
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		
		// create clip reference 
        clip = AudioSystem.getClip(); 
          
        // open audioInputStream to the clip 
        clip.open(audioInputStream); 
          
        clip.loop(Clip.LOOP_CONTINUOUSLY); 
    } 
	
	//play the audio 
    public void play()  
    { 
        //start the clip 
        clip.start(); 
          
        status = "play"; 
    } 
    
    //pause the audio
    public void pause() { 
        clip.stop(); 
        status = "paused"; 
    } 
    
    //restart the audio 
    public void restart() throws IOException, LineUnavailableException, 
                                            UnsupportedAudioFileException  
    { 
        clip.stop(); 
        clip.close(); 
        resetAudioStream(); 
        currentFrame = 0L; 
        clip.setMicrosecondPosition(0); 
        this.play(); 
    } 
    
    // Method to stop the audio 
    public void stop() throws UnsupportedAudioFileException, 
    IOException, LineUnavailableException  
    { 
        currentFrame = 0L; 
        clip.stop(); 
        clip.close(); 
    } 
    
 // Method to reset audio stream 
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
                                            LineUnavailableException  
    { 
        audioInputStream = AudioSystem.getAudioInputStream( 
        new File(filePath).getAbsoluteFile()); 
        clip.open(audioInputStream); 
        clip.loop(Clip.LOOP_CONTINUOUSLY); 
    } 
    
    //mutators
    public void setfilePath(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    	stop();
    	playMusic.filePath = filePath;
    	restart();
    }

}

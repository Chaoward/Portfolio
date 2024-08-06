package base;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
    
    public static void playAudio(String audioFilePath) {
        try {
            File audioFile = new File(audioFilePath);
            
            if (audioFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else {
                System.out.println("File not found");
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error Audio Error!!!");
        }
    }
}

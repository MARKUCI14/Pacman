package game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {
    private final AudioInputStream beginningSound, winSound;

    public Sounds(){
        try {
            beginningSound = AudioSystem.getAudioInputStream(new File("sounds/pacman_beginning.wav"));
            winSound = AudioSystem.getAudioInputStream(new File("sounds/pacman_intermission.wav"));
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void play(){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(winSound);
            clip.start();
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void playIntro(){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(beginningSound);
            clip.start();
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

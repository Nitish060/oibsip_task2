import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class settings {

    public final int HEIGHT = 600 ;
    public final int WIDTH  = 800 ;
    public long beg , end  , dt ; 
    
}

enum GameState { 
    Play , 
    Pause , 
    Credits 
}

class Rect {
    int x , y ; 
    
    Rect ( int x , int  y)
    {
        this.x = x ; 
        this.y = y ; 
    }
}

class Sound {
    Clip clip ; 
    URL soundURL[] = new URL[3] ; 
    Sound ()
    {
        soundURL[0] = getClass().getResource("/sound/back_g1.wav");
        soundURL[1] = getClass().getResource("/sound/loss_1.wav");
        soundURL[2] = getClass().getResource("/sound/win_1.wav");
    }
    public void soundFile(int sound_index)
    {
        try {
            AudioInputStream mix = AudioSystem.getAudioInputStream(soundURL[sound_index]);
            clip = AudioSystem.getClip();
            clip.open(mix);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void Play()
    {
        clip.start();
    }
    public void loop()
    {
        clip.loop(-1);
    }
    public void Stop()
    {
        clip.stop();
    }
}
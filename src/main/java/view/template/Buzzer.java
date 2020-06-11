package view.template;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Buzzer {

    private Clip timerRing;

    public Buzzer() throws Exception{
        URL url = this.getClass().getClassLoader().getResource("test.wav");
        AudioInputStream timerAudio = AudioSystem.getAudioInputStream(url);
        AudioFormat timerFormat = timerAudio.getFormat();
        DataLine.Info timerInfo = new DataLine.Info(Clip.class, timerFormat);
        this.timerRing = (Clip)AudioSystem.getLine(timerInfo);
        this.timerRing.open(timerAudio);
    }


    public void ringTimer(){
        this.timerRing.start();
    }

    public void stopRingTimer(){
        this.timerRing.stop();
    }


}

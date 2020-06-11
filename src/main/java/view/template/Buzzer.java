package view.template;

import javax.sound.sampled.*;
import java.net.URL;
import java.awt.Toolkit;

public class Buzzer extends Thread{

    //private Clip timerRing;

    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private boolean flag = false;

    /*
    public Buzzer() throws Exception{
        URL url = this.getClass().getClassLoader().getResource("ringsound.wav");
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
     */

    public synchronized void threadWait() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void threadNotify() {
        this.notify();
    }

    public void run() {
        this.flag = false;

        while(true) {
            if(!this.flag) {
                threadWait();
                this.flag = true;
            }
            toolkit.beep();
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {

            }

        }

    }

    public void beeep() {
        toolkit.beep();
    }


    public void pauseThread() {
        this.flag = false;
    }


}

package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];
    FloatControl fc;
    int volumeScale = 2;
    float volume;

    public Sound(){
        soundURL[0] = getClass().getResource("/res/sounds/bgMusic1.wav");
        soundURL[1] = getClass().getResource("/res/sounds/bgMusic2.wav");
        soundURL[2] = getClass().getResource("/res/sounds/jump_se.wav");
        soundURL[3] = getClass().getResource("/res/sounds/punch.wav");
        soundURL[4] = getClass().getResource("/res/sounds/punch.wav");
        soundURL[5] = getClass().getResource("/res/sounds/special.wav");
        soundURL[6] = getClass().getResource("/res/sounds/ariel_bgm.wav");
        soundURL[7] = getClass().getResource("/res/sounds/mMenu.wav");
        soundURL[8] = getClass().getResource("/res/sounds/cSelect.wav");
        soundURL[9] = getClass().getResource("/res/sounds/cinderella_bgm.wav");
        soundURL[10] = getClass().getResource("/res/sounds/elsa_bgm.wav");
        soundURL[11] = getClass().getResource("/res/sounds/moana_bgm.wav");
        soundURL[12] = getClass().getResource("/res/sounds/mulan_bgm.wav");
        soundURL[13] = getClass().getResource("/res/sounds/rapunzel_bgm.wav");
        soundURL[14] = getClass().getResource("/res/sounds/snowWhite_bgm.wav");
        soundURL[15] = getClass().getResource("/res/sounds/tiana_bgm.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        }catch(Exception e){

        }
    }

    public void play(){
//        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        if(clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    private int currentMusicIndex = -1;  // Track the index of the currently playing music

    public void playMusic(int i) {
        // If the new music is the same as the current one, don't stop it
        if (currentMusicIndex == i) {
            return;  // Music is already playing, so we do nothing
        }

        stopMusic();  // Stop any currently playing music first
//        this.setFile(i);  // Load the new sound file
//        this.play();  // Play the new sound
//        this.loop();  // Loop the sound if needed (optional)
//
//        // Update the current music index to the new one
//        currentMusicIndex = i;
    }


    public void stopMusic() {
        this.stop();
    }

    public void playSE(int i){
        this.setFile(i);
        this.play();
    }

    public void checkVolume(){
        switch(volumeScale){
            case 0: volume = -60f;
                break;
            case 1: volume = -15f;
                break;
            case 2: volume = -8f;
                break;
            case 3: volume = -5f;
                break;
            case 4: volume = 1f;
                break;
            case 5: volume = 4f;
                break;
        }
        fc.setValue(volume);
    }
}
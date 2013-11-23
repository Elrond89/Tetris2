/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.applet.*;
import javax.swing.*;
import java.net.*;

/**
 *
 * @author antonioruffolo
 */
public class TetrisAudioPlayer extends JApplet {
    
    private AudioClip song; // Sound player

    private URL songPath; // Sound path
    
    private boolean playing;

    public TetrisAudioPlayer(String filename) {

        try {

            //songPath = new URL(getCodeBase(),filename); // Get the Sound URL
                    
            songPath= this.getClass().getResource(filename);

            song = Applet.newAudioClip(songPath); // Load the Sound
        }

        catch(Exception e){} // Satisfy the catch

    }

    public void playSound() {
        song.loop(); // Play
        playing=true;
    }

    public void stopSound() {
        song.stop(); // Stop
        playing=false;
    }

    public void playSoundOnce() {
        song.play(); // Play only once
        playing=true;
    }

    public boolean playing(){
        return playing;
    }
    
    public static void main (String []args) {
        TetrisAudioPlayer tap= new TetrisAudioPlayer("/tetris/songs/Tetris_MusicA2.mid");
        tap.playSound();
    }//main
}

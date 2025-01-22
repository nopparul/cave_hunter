/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project3_Bird;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.sound.sampled.*;     

// Interface for keeping constant values

interface MyConstants
{
    
    //----- Resource files
    static final String PATH        = "src/main/java/Project3_Bird/resource/";
    static final String FILE_BG     = PATH + "background.jpg";
    static final String FILE_F     = PATH + "mainscreen_bg.jpg";
    static final String FILE_RED   = PATH + "red.png";
    static final String FILE_BLACK   = PATH + "black.png";
    static final String FILE_BLUE    = PATH + "blue.png";
    static final String FILE_YELLOW    = PATH + "yellow.png";
    static final String FILE_GREEN    = PATH + "green.png";  
    static final String FILE_COIN        = PATH + "coin.wav";
    static final String FILE_KNIFE       = PATH + "knife.wav";

    //first page
    static final String FILE_TITLE    = PATH + "title_editted.png";
    static final String FILE_START    = PATH + "start_button.png";
    static final String FILE_SETTING    = PATH + "setting_button.png";
    static final String FILE_SCORE    = PATH + "score_button.png";
    static final String FILE_EXIT    = PATH + "exit_button.png";
    
    static final String FILE_BULLET = PATH + "bullet2.png";
    static final String FILE_ITEM = PATH + "item_powerup.png";
    
    //----- Sound files
    static final String FILE_THEME   = PATH + "soundBackground.wav";
    static final String FILE_FIRE   = PATH + "bullet-sound.wav";
    
    //----- Sizes and locations
    static final int FRAMEWIDTH  = 1366;
    static final int FRAMEHEIGHT = 768;
    
    //------ monster file
    static final String FILE_FIREFLY   = PATH + "minion_firefly.png";
    static final String FILE_BAT   = PATH + "miniboss_bat.png";
    static final String FILE_BEE   = PATH + "bee1.png";
    
    static final int MIDDLE_X    = 200;
    //static final int MONSTER1       = 50;
}


// Auxiliary class to resize image

class MyImageIcon extends ImageIcon
{
    public MyImageIcon(String fname)  { super(fname); }
    public MyImageIcon(Image image)   { super(image); }

    public MyImageIcon resize(int width, int height)
    {
	Image oldimg = this.getImage();
	Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
}
class MySoundEffect
{
    private Clip         clip;
    private FloatControl gainControl;         

    public MySoundEffect(String filename)
    {
	try
	{
            java.io.File file = new java.io.File(filename);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);            
            gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
	}
	catch (Exception e) { e.printStackTrace(); }
    }
    public void playOnce()             { clip.setMicrosecondPosition(0); clip.start(); }
    public void playLoop()             { clip.loop(Clip.LOOP_CONTINUOUSLY); }
    public void stop()                 { clip.stop(); }
    public void setVolume(float gain)
    {
        if (gain < 0.0f)  gain = 0.0f;
        if (gain > 1.0f)  gain = 1.0f;
        float dB = (float)(Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }
}
package Project3_Bird;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.*;

public class firstfram extends JFrame {

    private JPanel contentpane;
    private JLabel text;
    // private JTextArea	title_text;
    private JButton start_button, score_button, exit_button, set_button,how;
    private String name = "";
    private MySoundEffect themeSound;
    private String PATH = MyConstants.PATH;
    private String FILE_THEME = MyConstants.FILE_THEME;
    private MyImageIcon backgroundImg,titleImg,startImg,scoreImg,exitImg,setImg;
    private JLabel drawpane;
    private String FILE_BG = MyConstants.FILE_F;
    private boolean soundrun=false;
    firstfram currentFrame;
    scorefram scframe;
    startfram stframe;
    settingfram setframe;
    howtoplayfram howfram;

    public firstfram() {
        currentFrame = this;
        setTitle("First Frame");
        setSize(1366, 768);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentpane = (JPanel) getContentPane();
        //contentpane.setLayout( new BorderLayout() );

        AddComponents();
        currentFrame.addWindowListener(new WindowListener() {
            public void windowOpened(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {

                themeSound.stop();

            }

            public void windowClosed(WindowEvent e) {
            }

            public void windowActivated(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }
        });

    }

    public void sound(boolean n){
        if(n==true){
            this.themeSound.stop();
            soundrun=false;
        }
        else{
            if(n==false && soundrun==true){
            this.themeSound.stop();
            soundrun=false;
        }
            themeSound = new MySoundEffect(FILE_THEME); 
        themeSound.playLoop(); themeSound.setVolume(0.4f);
        soundrun=true;
        }
    }

    public void AddComponents() {

        backgroundImg = new MyImageIcon(FILE_BG).resize(1366, 768);
        titleImg = new MyImageIcon(MyConstants.FILE_TITLE).resize(600, 300);
        startImg = new MyImageIcon(MyConstants.FILE_START).resize(200, 100);
        scoreImg = new MyImageIcon(MyConstants.FILE_SCORE).resize(200, 100);
        exitImg = new MyImageIcon(MyConstants.FILE_EXIT).resize(200, 100);
        setImg = new MyImageIcon(MyConstants.FILE_SETTING).resize(200, 100);
        drawpane = new JLabel();
        drawpane.setIcon(backgroundImg);
        drawpane.setLayout(null);
        int centerX = 683 ;
        int centerY = 384 ;

        themeSound = new MySoundEffect(FILE_THEME);
        soundrun=true;
        themeSound.playLoop();
        themeSound.setVolume(0.4f);

        text = new JLabel();
        text.setIcon(titleImg);
        //text.setFont(new Font("SanSerif", Font.BOLD | Font.ITALIC, 70));
        text.setBounds(centerX-300,0,600,300);
        //text.setHorizontalAlignment(JLabel.CENTER);

        start_button = new JButton(startImg);
        start_button.setFont(new Font("SanSerif", Font.BOLD | Font.ITALIC, 20));
        start_button.setBounds(centerX-200, centerY, 200, 100);
        start_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                name = (String) JOptionPane.showInputDialog(null, "What is your name?", "Enter", JOptionPane.INFORMATION_MESSAGE);
                while ("".equals(name) /*|| "".equals(t)*/) {
                    name = (String) JOptionPane.showInputDialog(null, "Please enter name?", "Enter", JOptionPane.WARNING_MESSAGE);
                    //if(name!="")t = name.trim();
                }
                if (name != null) {
                    stframe = new startfram();
                    stframe.setTextMessage(name);
                    stframe.setF(currentFrame);
                    stframe.AddComponents();
                    currentFrame.setVisible(false);
                    
                }

            }
        });

        score_button = new JButton(scoreImg);
        score_button.setFont(new Font("SanSerif", Font.BOLD | Font.ITALIC, 20));
        score_button.setBounds(centerX, centerY, 200, 100);
        score_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // sortting();
                scframe = new scorefram();
                // scframe.setArrayMessage(Allseries);

                scframe.AddComponents();
            }
        });
        set_button = new JButton(setImg);
        set_button.setFont(new Font("SanSerif", Font.BOLD | Font.ITALIC, 20));
        set_button.setBounds(centerX-200, centerY+100, 200, 100);
        set_button.addMouseListener(new MouseAdapter() {
            // @Override
            public void mouseClicked(MouseEvent e) {

                setframe = new settingfram();
                setframe.setFF(currentFrame);//currentFrame;

                setframe.AddComponents();
            }
        });

        exit_button = new JButton(exitImg);
        exit_button.setFont(new Font("SanSerif", Font.BOLD | Font.ITALIC, 20));
        exit_button.setBounds(centerX, centerY + 100, 200, 100);
        exit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(currentFrame, "confirm if you Want to Exit", "Game",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                    themeSound.stop();
                }
            }
        });
        how = new JButton("HOW TO PLAY");
        how.setFont(new Font("SanSerif", Font.BOLD | Font.ITALIC, 20));
        how.setBounds(centerX, centerY -100, 200, 100);
        how.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                howfram = new howtoplayfram();
                

                howfram.AddComponents();
            }
        });

        drawpane.add(text);
        drawpane.add(start_button);
        drawpane.add(score_button);
         drawpane.add(how);
        drawpane.add(set_button);
        drawpane.add(exit_button);
        contentpane.add(drawpane, BorderLayout.CENTER);

        validate();
    }

    public static void main(String[] args) {
        new firstfram();
    }
}

class Series implements Comparable {

    private String name;
    private int score;

    public Series(String nm, int sc) {
        name = nm;
        score = sc;
    }

    public void print() {
        System.out.printf("%-20s %5d\n", name, score);
    }

    public int compareTo(Object param) {
        Series other = (Series) param;
        if (this.score < other.score) {
            return 1;
        } else if (this.score > other.score) {
            return -1;
        } else {
            return 0;
        }
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}

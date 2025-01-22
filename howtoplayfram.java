package Project3_Bird;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class howtoplayfram extends JFrame {
    private String PATH = MyConstants.PATH;
    private String FILE_THEME = MyConstants.FILE_THEME;
    private MyImageIcon backgroundImg,titleImg,startImg,scoreImg,exitImg,setImg;
    private JLabel drawpane;
    private String FILE_BG = MyConstants.FILE_F;
     private JTextArea		textarea;
    private JPanel contentpane;
    private ButtonGroup      bgroup2;
    private JLabel text;
    private JList list;
    private Object[] items = null;
    private String message = "";
    private int mode = 0;
    private Object[] messageFromList;
    private JButton print_button;
    private String path = MyConstants.PATH;
    
    private boolean x;

    private howtoplayfram currentFrame;
    private Project3_Main play;

    public howtoplayfram() {

        setTitle("how to play");
        setSize(1366, 768);
        setLocationRelativeTo(null);
        setVisible(true);
       setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(new FlowLayout());

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
        
        String x= "\n";
        textarea  = new JTextArea("How to Play : 1.Enter name and choose birds at least "+x+
                "2.Use w and s to chang direction up-down"+x+"3.Avoid monster !!! Try to survive!!"+x+
                "4.For each second you got +1 score if mini boss come out +5,boss +10"
                +x+x+"Made by:Nopparuj Ritnatikul 6513168");
        textarea.setForeground(Color.blue);
        textarea.setEditable(false);
        textarea.setFont(new Font("SanSerif", Font.BOLD | Font.ITALIC, 20));
        textarea.setBounds(centerX-350,150,700,200);
       
        
        
       
          drawpane.add(textarea);
          
         
          
          contentpane.add(drawpane);
        

        validate();
    }

    public static void main(String[] args) {
        howtoplayfram frame = new howtoplayfram();
        frame.AddComponents();
    }
}

package Project3_Bird;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class startfram extends JFrame {
    private String PATH = MyConstants.PATH;
    private String FILE_THEME = MyConstants.FILE_THEME;
    private MyImageIcon backgroundImg,titleImg,startImg,scoreImg,exitImg,setImg;
    private JLabel drawpane;
    private String FILE_BG = MyConstants.FILE_F;
    
    private JPanel contentpane;
    private ButtonGroup      bgroup2;
    private JRadioButton EasyRadio,MeduimRadio,HardRadio;
    private JLabel text;
    private JList list;
    private Object[] items = null;
    private String message = "";
    private int mode = 0;
    private String[] bird = {"Red", "Green", "Blue ", "Black", "Yellow"};
    private Object[] messageFromList;
    private JButton print_button;
    private String path = MyConstants.PATH;
    private String[] birds = {"black.png", "blue.png", "green.png",
        "red.png", "yellow.png"};
    
    private boolean x;
    private firstfram firstf;
    private startfram currentFrame;
    private Project3_Main play;

    public startfram() {

        currentFrame = this;
        setTitle("Start Frame");
        setSize(1366, 768);
        setLocationRelativeTo(null);
        setVisible(true);
       setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
       addWindowListener(new WindowListener(){
            public void windowOpened(WindowEvent e)             { }
            public void windowClosing(WindowEvent e)            { 
                firstf.setVisible(true);
            }
            public void windowClosed( WindowEvent e )		{ }
            public void windowActivated( WindowEvent e )	{ }
            public void windowDeactivated( WindowEvent e )	{ }
            public void windowIconified( WindowEvent e )        { }
            public void windowDeiconified( WindowEvent e )      { }
        });

        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(new FlowLayout());

    }

    public void setTextMessage(String m) {
        message = m;
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
        
        String bn = "\n\n\n\n";
        text = new JLabel("Your name: " + message );
        JLabel choose = new JLabel("Choose one Birds below :" );
        text.setForeground(Color.blue);
        choose.setForeground(Color.blue);
        text.setFont(new Font("SanSerif", Font.BOLD | Font.ITALIC, 20));
        text.setBounds(centerX-200,0,400,50);
        choose.setFont(new Font("SanSerif", Font.BOLD | Font.ITALIC, 20));
        choose.setBounds(50+100,60,400,50);
        
        
        JLabel labelb1 = new JLabel(new ImageIcon(path + birds[0]));
        JLabel labelb2 = new JLabel(new ImageIcon(path + birds[1]));
        JLabel labelb3 = new JLabel(new ImageIcon(path + birds[2]));
        JLabel labelb4 = new JLabel(new ImageIcon(path + birds[3]));
        JLabel labelb5 = new JLabel(new ImageIcon(path + birds[4]));
        labelb1.setBounds(50+100,300,100,100);
        labelb2.setBounds(150+100,300,100,100);
        labelb3.setBounds(250+100,300,100,100);
        labelb4.setBounds(350+100,300,100,100);
        labelb5.setBounds(450+100,300,100,100);
        
        list = new JList(bird);
        list.setVisibleRowCount(5);//see row
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting())//on doing
                {
                    x = !e.getValueIsAdjusting();
                    messageFromList = list.getSelectedValues();
                }
            }
        });
        EasyRadio = new JRadioButton();
        MeduimRadio = new JRadioButton();
        HardRadio = new JRadioButton();
        EasyRadio.setText("Easy");
        EasyRadio.setSelected(true);
        EasyRadio.setBounds(centerX+200,centerY-200,400,50);
        EasyRadio.addItemListener( new ItemListener() {
                @Override
		public void itemStateChanged( ItemEvent e )
		{
                    JRadioButton temp = (JRadioButton)e.getItem();//get radio
                    if (temp.isSelected())//use radio method//or use getStateChang
                    {
                        
			//String state =  temp.getText() ;
                        mode =0;
			//drawpane.setCount( count );
                    }
		}
            });

        MeduimRadio.setText("Meduim");
        MeduimRadio.setBounds(centerX+200,centerY-200+50,400,50);
        MeduimRadio.addItemListener( new ItemListener() {
                @Override
		public void itemStateChanged( ItemEvent e )
		{
                    JRadioButton temp = (JRadioButton)e.getItem();//get radio
                    if (temp.isSelected())//use radio method//or use getStateChang
                    {// startframe.setFram(currentFrame);
			//String state =  temp.getText() ;
                        mode =1;
			//drawpane.setCount( count );
                    }
		}
            });

        HardRadio.setText("Hard");
        HardRadio.setBounds(centerX+200,centerY-200+100,400,50);
        HardRadio.addItemListener( new ItemListener() {
                @Override
		public void itemStateChanged( ItemEvent e )
		{
                    JRadioButton temp = (JRadioButton)e.getItem();//get radio
                    if (temp.isSelected())//use radio method//or use getStateChang
                    {// startframe.setFram(currentFrame);
			//String state =  temp.getText() ;
                        mode =2;
			//drawpane.setCount( count );
                    }
		}
            });
        bgroup2  = new ButtonGroup();
        bgroup2.add( EasyRadio );
        bgroup2.add( MeduimRadio );
        bgroup2.add( HardRadio );
        
        print_button = new JButton("Finish");
        print_button.setFont(new Font("SanSerif", Font.BOLD, 25));
        print_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (x)//on doing
                {
                    String val = list.getSelectedValue().toString();
                    play = new Project3_Main();
                    
                    System.out.println(val);
                    String[] col = val.split(" ");
                    System.out.println(col[0]);
                    play.setType(col[0]);
                    play.addBird();
                   
                   play.setMode(mode);
                    play.setName(message);
                    play.setVisible(true);
                    currentFrame.setVisible(false);
                    play.setF(firstf);
                    //currentFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(currentFrame, "Choose one bird.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        print_button.setPreferredSize(new Dimension(100, 50));
        
         JScrollPane listSP = new JScrollPane(list);
        listSP.setBounds(50+100,120,400,150);
        print_button.setBounds(centerX-200,450,400,150);
        


        //listpanel.add(new JScrollPane(list));
        list.setFont(new Font("SanSerif", Font.BOLD | Font.ITALIC, 20));
        list.setPreferredSize(new Dimension(100, 50));
       
          drawpane.add(text);
          drawpane.add(choose);
          drawpane.add(labelb1);
          drawpane.add(labelb2);
          drawpane.add(labelb3);
          drawpane.add(labelb4);
          drawpane.add(labelb5);
          drawpane.add(listSP);
          drawpane.add(print_button);
          drawpane.add(HardRadio);
          drawpane.add(EasyRadio);
          drawpane.add(MeduimRadio);
          
          contentpane.add(drawpane);
        

        validate();
    }
    public void setF(firstfram f){
         firstf= f;
     }

    public static void main(String[] args) {
        startfram frame = new startfram();
        frame.AddComponents();
    }
}

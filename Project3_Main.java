/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Project3_Bird;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.Timer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author user
 */
class Project3_Main extends JFrame implements KeyListener {

     // components
    private JPanel               contentpane;
    private JLabel               drawpane;
    private JComboBox            combo;
    private JToggleButton        []tb;
    private JButton              moveButton, stopButton, itemButton;
    //private JTextField           scoreText;
    private MyImageIcon          backgroundImg;    
    private MySoundEffect        themeSound;
    private Timer minionTimer;
    private java.util.Random random;
    private JTextField scoreText, timeText, HpText;
     private String name;
     private int score = 0;
     private ItemLabel minib;
    private long elapsedTime ;
    private int count = 0;
    private int [] Mode = {60,45,30};
    private int mode ;
    private int type = 0;
    
     String path = "src/main/java/Project3_Bird/";
     String infile = path + "file.txt";
     String outfile =path + "outfile.txt";
     
    private firstfram firstf;
    private WormLabel            wormLabel;
    private Project3_Main      currentFrame;

    private int framewidth  = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
     private static long startTime;// start time for open window
    protected boolean close = false;

 public static void main(String[] args) 
    {
        new Project3_Main();
    }    

    //--------------------------------------------------------------------------
    public Project3_Main()
    {   
        setTitle("Cave Hunter");
	setSize(framewidth, frameheight); 
        setLocationRelativeTo(null);
	setVisible(true);
	setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        currentFrame = this;

        // (1) Add WindowListener (anonymous class)
        //     - Stop everything. Show total score
        
        addWindowListener(new WindowListener(){
            public void windowOpened(WindowEvent e)             { startTime = System.currentTimeMillis();}
            public void windowClosing(WindowEvent e)            { 
                wormLabel.setMove(false);
                close = true;
                //themeSound.stop();
                JOptionPane.showMessageDialog(currentFrame,("Total score = "+ score),"Name:"+name,JOptionPane.INFORMATION_MESSAGE);
                file();
                firstf.setVisible(true);
            }
            public void windowClosed( WindowEvent e )		{ }
            public void windowActivated( WindowEvent e )	{ }
            public void windowDeactivated( WindowEvent e )	{ }
            public void windowIconified( WindowEvent e )        { }
            public void windowDeiconified( WindowEvent e )      { }
        });


        minionTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                if(close != true){
                if(count % 12 ==0) score +=5;
                else if(count % 25 ==0 ) score +=10;
                else score++;}
                String result = String.valueOf(score);
                scoreText.setText(result);
                elapsedTime = System.currentTimeMillis() - startTime;
                updateTime(elapsedTime / 1000);
                
                if(count%4==0)createMinion();
                
            }
        });
        minionTimer.start();
        
	contentpane = (JPanel)getContentPane();
	contentpane.setLayout( new BorderLayout() );        
        AddComponents();
        addKeyListener(this);
        
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
               if(!wormLabel.isMove()){
                    wormLabel.setMove(true);
                    setWormThread();
                    
                }
               wormLabel.turnUp();
                break;
            case KeyEvent.VK_S:
                if(!wormLabel.isMove()){
                    wormLabel.setMove(true);
                    setWormThread();
                }
                wormLabel.turnDown();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    public void file() {
        try {
	  Scanner scan  = new Scanner(new File(infile));
	  PrintWriter write = new PrintWriter(new File(outfile));
	  while (scan.hasNext()) 
          {							
	    String line = scan.nextLine();
	   String[] col = line.split(",");
            // Use \r\n when writing to file
            //System.out.printf("%s,%d \n", col[0],Integer.parseInt(col[1].trim()));
            write.printf("%s,%d \r\n",  col[0], Integer.parseInt(col[1].trim()));
	  }
          write.printf("%s,%d \r\n",  name, score);
          
	  scan.close();
	  write.close();
           }
           catch(Exception x) {
                System.err.println("An error occurs. End program.");
                System.err.println(x);	  
                //System.exit(-1);
             }
        try {
	  Scanner scan  = new Scanner(new File(outfile));
	  PrintWriter write = new PrintWriter(new File(infile));
	  while (scan.hasNext()) 
          {							
	    String line = scan.nextLine();
	   String[] col = line.split(",");
            // Use \r\n when writing to file
            //System.out.printf("%s,%d\n", col[0],Integer.parseInt(col[1].trim()));
            write.printf("%s,%d \r\n",  col[0], Integer.parseInt(col[1].trim()));
	  }
	  scan.close();
	  write.close();
           }
           catch(Exception x) {
                System.err.println("An error occurs. End program.");
                System.err.println(x);	  
                //System.exit(-1);
             }
    }
    public void setF(firstfram f){
         firstf = f;
     }
    
    public void addBird() {
        wormLabel = new WormLabel(currentFrame, type);
        //System.out.println(type);
        drawpane.add(wormLabel);
    }
    //--------------------------------------------------------------------------
    public void AddComponents()
    {        
	backgroundImg  = new MyImageIcon(MyConstants.FILE_BG).resize(framewidth, frameheight);
	drawpane = new JLabel();
	drawpane.setIcon(backgroundImg);
        drawpane.setLayout(null);
        drawpane.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){ //add key to all label
                switch (e.getKeyCode()){
            case KeyEvent.VK_W:
               if(!wormLabel.isMove()){
                    wormLabel.setMove(true);
                    setWormThread();
                    
                }
               wormLabel.turnUp();
                break;
            case KeyEvent.VK_S:
                if(!wormLabel.isMove()){
                    wormLabel.setMove(true);
                    setWormThread();
                }
                wormLabel.turnDown();
                break;
            /*case KeyEvent.VK_J:
                setbulletThread();
                break;*/
        }
            }
        });
/*
	themeSound = new MySoundEffect(MyConstants.FILE_THEME); 
        themeSound.playLoop(); themeSound.setVolume(0.4f);*/
        
       
        
	//scoreText = new JTextField("0", 5);		
	//scoreText.setEditable(false);
        scoreText = new JTextField("0", 5);
        scoreText.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){ //add key to all label
                switch (e.getKeyCode()){
            case KeyEvent.VK_W:
               if(!wormLabel.isMove()){
                    wormLabel.setMove(true);
                    setWormThread();
                    
                }
               wormLabel.turnUp();
                break;
            case KeyEvent.VK_S:
                if(!wormLabel.isMove()){
                    wormLabel.setMove(true);
                    setWormThread();
                }
                wormLabel.turnDown();
                break;
            /*case KeyEvent.VK_J:
                setbulletThread();
                break;*/
        }
            }
        });
        scoreText.setEditable(false);
        timeText = new JTextField("0", 5);
        timeText.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){ //add key to all label
                switch (e.getKeyCode()){
            case KeyEvent.VK_W:
               if(!wormLabel.isMove()){
                    wormLabel.setMove(true);
                    setWormThread();
                    
                }
               wormLabel.turnUp();
                break;
            case KeyEvent.VK_S:
                if(!wormLabel.isMove()){
                    wormLabel.setMove(true);
                    setWormThread();
                }
                wormLabel.turnDown();
                break;
            /*case KeyEvent.VK_J:
                setbulletThread();
                break;*/
        }
            }
        });
        timeText.setEditable(false);
        HpText = new JTextField("100", 5);
        HpText.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){ //add key to all label
                switch (e.getKeyCode()){
            case KeyEvent.VK_W:
               if(!wormLabel.isMove()){
                    wormLabel.setMove(true);
                    setWormThread();
                    
                }
               wormLabel.turnUp();
                break;
            case KeyEvent.VK_S:
                if(!wormLabel.isMove()){
                    wormLabel.setMove(true);
                    setWormThread();
                }
                wormLabel.turnDown();
                break;
            /*case KeyEvent.VK_J:
                setbulletThread();
                break;*/
        }
            }
        });
        HpText.setEditable(false);

        JPanel control = new JPanel();
        JPanel tabel = new JPanel();
        //JPanel controlgb = new JPanel();
        tabel.setBounds(0, 0, 1000, 50);
        tabel.add(new JLabel("Hp : "));
        tabel.add(HpText);
        tabel.add(new JLabel("         "));
        tabel.add(new JLabel("Time : "));
        tabel.add(timeText);
        tabel.add(new JLabel("         "));
        tabel.add(new JLabel("Score : "));
        tabel.add(scoreText);
        tabel.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){ //add key to all label
                switch (e.getKeyCode()){
            case KeyEvent.VK_W:
               if(!wormLabel.isMove()){
                    wormLabel.setMove(true);
                    setWormThread();
                    
                }
               wormLabel.turnUp();
                break;
            case KeyEvent.VK_S:
                if(!wormLabel.isMove()){
                    wormLabel.setMove(true);
                    setWormThread();
                }
                wormLabel.turnDown();
                break;
            /*case KeyEvent.VK_J:
                setbulletThread();
                break;*/
        }
            }
        });
        control.setBounds(0, 0, 1000, 50);
        contentpane.add(tabel, BorderLayout.NORTH);
        contentpane.add(drawpane, BorderLayout.CENTER);      
        validate();       
    }    
    private void createMinion() {
            if (count %12 == 0) {
           
            for (int i = 0; i <= mode+1; i++) {
            setItemThread( Mode[mode]+20, 100, 1);
            }
        }
            else if (count %20 == 0) {
           
            setItemThread( Mode[mode], 100, 2);
            
        }
            else {
                random = new Random();
                int randomNum = random.nextInt((4-1) + 1)+1;
                if(mode==1) randomNum = random.nextInt((5-1)+1)+2;
                if(mode==2) randomNum = random.nextInt(((6-1) +1))+3;

               
                for (int i = 0; i <= randomNum; i++) {
                    setItemThread(Mode[mode], 10, 0);
                }
            }
       
    }
    //--------------------------------------------------------------------------
    public void setWormThread()
    {
        Thread wormThread = new Thread() {
            public void run()
            {
                while (wormLabel.isMove())
                {
                    wormLabel.updateLocation();
                }          
            } // end run
        }; // end thread creation
        wormThread.start();
    }
    //--------------------------------------------------------------------------
     
    public void setItemThread(int sp, int hp, int ty)
    {
        //long elapsedTime = System.currentTimeMillis() - startTime;
        Thread itemThread = new Thread() {
            @Override
            public void run()
            {
               
                ItemLabel itemLabel = new ItemLabel(currentFrame,sp,hp,ty);
                itemLabel.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){ //add key to all label
                switch (e.getKeyCode()){
            case KeyEvent.VK_W:
               if(!wormLabel.isMove()){
                    wormLabel.setMove(true);
                    setWormThread();
                    
                }
               wormLabel.turnUp();
                break;
            case KeyEvent.VK_S:
                if(!wormLabel.isMove()){
                    wormLabel.setMove(true);
                    setWormThread();
                }
                wormLabel.turnDown();
                break;
        }
            }
        });
                drawpane.add(itemLabel);
                int hit=0,dis=0;
                do{
                    if(close) return;
                  hit = itemLabel.intersection(wormLabel);
                  dis = itemLabel.updateLocation();
                  
                }while(hit == 0 && dis ==0);
                updateHp(hit);
                //updateScore(hit);
                drawpane.remove(itemLabel);
                repaint();

            } // end run
        }; // end thread creation
        itemThread.start();
    }
    //--------------------------------------------------------------------------
     

    synchronized public void updateHp(int hit) {
        wormLabel.remainHp(hit);
        if(wormLabel.getAlive()==false) {
            close = true;
        }
        
        String result = String.valueOf(wormLabel.getHp());
        HpText.setText(result);
    }

    synchronized public void updateTime(long t) {
        String result = String.valueOf(t);
        timeText.setText(result);
    }

    public void setName(String nm) {
        name = nm;
    }
    public void setMode(int  x) {
        mode = x;
    }
    public void setType(String ty) {
        if (ty.equals("Red ")) {
            type = 0;
        } else if (ty.equals("Blue")) {
            type = 1;
        } else if (ty.equals("Black")) {
            type = 2;
        } else if (ty.equals("Yellow")) {
            type = 3;
        } else if (ty.equals("Green")) {
            type = 4;
        } else {
            type = 0;
        }
        System.out.println(type);

    }
    
} // end class MainApplication


////////////////////////////////////////////////////////////////////////////////
class WormLabel extends JLabel
{
    private Project3_Main  parentFrame;   
    private MyImageIcon      leftImg, redbird;      
        
    private int width    = 64;
    private int height   = 64;
    private int curX     = 90, curY = 200;   
    private int speed    = 300;
    private boolean up = true, move = false;
    private int hp = 100;
    private boolean isAlive = true;
    private final MyImageIcon RedImg, BlueImg, BlackImg, YellowImg, GreenImg;
     private int type;
    
        
    public WormLabel(Project3_Main pf,int tp)
    {
        parentFrame = pf;
            
        //leftImg  = new MyImageIcon(MyConstants.FILE_WORM_LEFT).resize(width, height);
       type = tp;
        RedImg = new MyImageIcon(MyConstants.FILE_RED).resize(width, height);
        BlueImg = new MyImageIcon(MyConstants.FILE_BLUE).resize(width, height);
        BlackImg = new MyImageIcon(MyConstants.FILE_BLACK).resize(width, height);
        YellowImg = new MyImageIcon(MyConstants.FILE_YELLOW).resize(width, height);
        GreenImg = new MyImageIcon(MyConstants.FILE_GREEN).resize(width, height);

        ChangeType(type);
        setBounds(curX, curY, width, height);
    }
    public void ChangeType(int tp) {
        type = tp;
        if (type == 0) {
            setIcon(RedImg);
        } else if (type == 1) {
            setIcon(BlueImg);
        } else if (type == 2) {
            setIcon(BlackImg);
        } else if (type == 3) {
            setIcon(YellowImg);
        } else if (type == 4) {
            setIcon(GreenImg);
        } else {
            setIcon(null);
        }
        parentFrame.repaint();
    }
        
    public void setSpeed(int s)     { speed = s; }
    public void turnUp()          { /*setIcon(leftImg);*/  up = true; }
    public void turnDown()         { /*setIcon(rightImg)*/; up = false; }
    public void setMove(boolean m)  { move = m; }
    public boolean isMove()         { return move; }
        
    public void updateLocation()
    {
        if (up)
        {   
            curY = curY - 50;
            if (curY < -50) { curY = parentFrame.getHeight()+5; } 			
        }
        else
        {
            curY = curY + 50;
            if (curY > parentFrame.getHeight()) { curY = -50; }			
        }
        setLocation(curX, curY);
        repaint();             
        try { Thread.sleep(speed); } 
        catch (InterruptedException e) { e.printStackTrace(); }
    } 
    public int getHp() {
        return hp;
    }

    public void remainHp(int damage) {
        hp = hp - damage;
        if (hp <= 0) {
            
            setAlive(false);
            setVisible(false);
        }
    }
    public int getCurX() {
        return curX;
    }

    public int getCurY() {
        return curY;
    }

    public void setAlive(boolean al) {
        isAlive = al;
        System.out.println("Dead");
        if (al == false) {
            setVisible(false);
        }
    }

    public boolean getAlive() {
        return isAlive;
    }
    
} // end class WormLabel

////////////////////////////////////////////////////////////////////////////////
class ItemLabel extends JLabel 
{
    private Project3_Main  parentFrame;   
    
    private int             type;                // 0 = good item (falling down), 1 = bad item (floating up)
    private MyImageIcon     itemImg;
    private MySoundEffect   hitSound;
    
    String[] imageFiles = {MyConstants.FILE_FIREFLY, MyConstants.FILE_BAT, MyConstants.FILE_BEE};
    private int health;        
    String [] soundFiles = { MyConstants.FILE_COIN, MyConstants.FILE_KNIFE };
    int     hitpoint  = 10;    

    private int width    ;
    private int height   ;
    private int curX, curY;
    private int speed = 50;//400
    private int monstertype;////type 0 = minion, 1 = miniboss, 2 = boss(last minite of the game)
    private boolean isAlive = true;
    private boolean left = true, move = true;
    private int score;
    private boolean top = false;//check if it reach the top 

    public ItemLabel(Project3_Main pf,int sp, int hp, int ty)
    {
        parentFrame = pf;
        speed = sp;
        health = hp;
        monstertype = ty;
        /*switch (monstertype) {
            case 0: {
                this.curY = (int) (Math.random() * 5555) % (parentFrame.getHeight() - 100);
                width = 30;
                height = 30;
                score = 10;
                itemImg = new MyImageIcon(imageFiles[1]).resize(width, height); // Use index 1 for the miniboss
                setIcon(itemImg);
                break;
            }
            
        }*/
        if(monstertype==0) 
        {
        curY = (int)(Math.random() * 5555) % (parentFrame.getHeight()-100);
        width = 40;
        height = 40;
        hitpoint =10;
        { curX = parentFrame.getWidth()-70; } 
        
        itemImg  = new MyImageIcon(imageFiles[0]).resize(width, height);
        
        setIcon(itemImg);
        }
        else if(monstertype==1){
            curY = (int)(Math.random() * 5555) % (parentFrame.getHeight()-150);
          curX = parentFrame.getWidth()-70;
               
                width = 50;
                height = 90;
                hitpoint =15;
                itemImg = new MyImageIcon(imageFiles[1]).resize(width, height); // Use index 1 for the miniboss
                setIcon(itemImg);
        }
        else if(monstertype==2){
            curY = (int)(Math.random() * 5555) % (parentFrame.getHeight()-250);
          curX = parentFrame.getWidth()-150;
                width = 150;
                height = 200;
                hitpoint =15;
                itemImg = new MyImageIcon(imageFiles[2]).resize(width, height); // Use index 1 for the miniboss
                setIcon(itemImg);
        }
        
        hitSound = new MySoundEffect(soundFiles[1]);
        setBounds(curX, curY, width, height);
    }
        
    public void playHitSound()         { hitSound.playOnce(); }
    public int  getHitPoints()         { return hitpoint; }
    public void setSpeed(int s) {
        speed = s;
    }

    public void setmonstertype(int s) {
        monstertype = s;
    }

    public int getHP() {
        return health;
    }

    public void remainHP(int s) {
        health = health - s;
        if (health < 0) {
            setAlive(false);
        }
    }

    public void setMove(boolean m) {
        move = m;
    }

    public boolean isMove() {
        return move;
    }

    public void setAlive(boolean al) {
        isAlive = al;
        if (isAlive == false) {
            setIcon(null);
        }
    }

    public boolean getAlive() {
        return isAlive;
    }

    public int getScore() {
        return score;
    }
    
    public int updateLocation(){
       
            if(this.curX-20 > - width+10 ) this.curX=curX-20;
            else{              
                return 2;  
        }
        
        setLocation(curX, curY);
        repaint();
        try {
            Thread.sleep(speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int intersection(WormLabel wormLabel){
        if(this.getBounds().intersects(wormLabel.getBounds())){
            playHitSound();
            return hitpoint;
        }
        return 0;
    }
    
} // end class ItemLabel

class QuickDialog//joptionpane
{

    public static void show(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, "Game Over",//if want to box to middle change to now dialog
                JOptionPane.INFORMATION_MESSAGE);
    }
};

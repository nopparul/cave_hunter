
package Project3_Bird;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class scorefram extends JFrame
{

    private JPanel          contentpane;
    JTextArea	text;
    private final static String newline = "\n";
     private JLabel score;
     private String PATH = MyConstants.PATH;
    private String FILE_THEME = MyConstants.FILE_THEME;
    private MyImageIcon backgroundImg,titleImg,startImg,scoreImg,exitImg,setImg;
    private JLabel drawpane;
    private String FILE_BG = MyConstants.FILE_F;
     
     //private JLabel [] tb;
   // ArrayList<Series> series = new ArrayList<Series>();
    
    public scorefram()
    {
        {
	setTitle("score Frame");
	setSize(1366, 768);
        setLocationRelativeTo(null);
	setVisible(true);
	setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

	contentpane = (JPanel)getContentPane();
	contentpane.setLayout( new FlowLayout() );
        }
    }
    // public void setArrayMessage( ArrayList<Series> ar )		{ series = ar; }
     public void AddComponents()
    {
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
        
     ArrayList<Series> Allseries = new ArrayList<Series>();
     String path = "src/main/java/Project3_Bird/";
     String infile = path + "file.txt";
     int i =0;
    
        try{
          Scanner scan = new Scanner(new File(infile));
          while(scan.hasNext()){
              
            String line = scan.nextLine();
            String[] col = line.split(",");
           Allseries.add(new Series(col[0],Integer.parseInt(col[1].trim())));
           
          
           i++;
          }
          Collections.sort(Allseries);
          
         // text = new JTextArea();
          
          /*for(int x=0;x<Allseries.size();x++){
            Series m=Allseries.get(x);
            String np = m.getName();
            int sp = m.getScore();
            //text.append(np+sp+newline);
            
        }*/
        Font f = new Font("SanSerif", Font.BOLD, 50);
	JLabel score = new JLabel("SCORE BOARD");
        score.setFont(f);
        score.setForeground(Color.white);
        //score.setIcon(titleImg);
        score.setBounds(centerX-200,0,400,50);
       
        
        
       
        DefaultTableModel model = new DefaultTableModel(); 
        JTable table = new JTable(model);
        JScrollPane tableSP = new JScrollPane(table);
        
       table.setFont( new Font("SanSerif", Font.BOLD | Font.ITALIC, 20) );
       model.addColumn("NAME"); 
       model.addColumn("SCORE"); 
       table.setRowHeight(30);
        tableSP.setBounds(centerX-425,100,800,500);
        
        for(int x=0;x<Allseries.size();x++){
            Series m=Allseries.get(x);
            String np = m.getName();
            int sp = m.getScore();
            model.addRow(new Object[]{np, sp});
        }
            drawpane.add(score);
          drawpane.add(tableSP);
          contentpane.add(drawpane);
          for(Series p : Allseries) p.print();
       }
       catch(Exception e){
                       System.err.println("An error occurs. End program.");
            System.err.println(e);
       }
         
        
        
	validate();
    }
   
}



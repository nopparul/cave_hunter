
package Project3_Bird;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;



public class settingfram extends JFrame
{
    firstfram firstfram;
    private JPanel          contentpane;
   
    private JRadioButton onRadio,offRadio,EasyRadio,MeduimRadio,HardRadio;
    private JLabel lset,laudi,lmode;
    private ButtonGroup      bgroup1;
    private startfram startframe;
    
    public settingfram()
    {
        {
	setTitle("setting Frame");
	setSize(800, 500);
        setLocationRelativeTo(null);
	setVisible(true);
	setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

	contentpane = (JPanel)getContentPane();
	contentpane.setLayout( new  GridLayout(15,15,5,1) );
        }
    }
   
     public void AddComponents()
    {
	
        
	lset = new JLabel();
        laudi = new JLabel();
        onRadio = new JRadioButton();
        offRadio = new JRadioButton();
        lmode = new JLabel();
       

        lset.setText("Setting");
        lset.setFont( new Font("SanSerif", Font.BOLD | Font.ITALIC, 20) );
        laudi.setText("====>Audio");
        laudi.setFont( new Font("SanSerif", Font.BOLD | Font.ITALIC, 15) );
        
        onRadio.setText("On");
        onRadio.addItemListener( new ItemListener() {
                @Override
		public void itemStateChanged( ItemEvent e )
		{
                    JRadioButton temp = (JRadioButton)e.getItem();//get radio
                    if (temp.isSelected())//use radio method//or use getStateChang
                    {
                        firstfram.sound(false);
			//String state =  temp.getText() ;
			//drawpane.setCount( count );
                    }
		}
            });	

        offRadio.setText("Off");
        offRadio.addItemListener( new ItemListener() {
                @Override
		public void itemStateChanged( ItemEvent e )
		{
                    JRadioButton temp = (JRadioButton)e.getItem();//get radio
                    if (temp.isSelected())//use radio method//or use getStateChang
                    {
                        firstfram.sound(true);
			//String state =  temp.getText() ;
			//drawpane.setCount( count );
                    }
		}
            });	

       
        
        
        JLabel gap1 = new JLabel(" ");
        JLabel gap2= new JLabel(" ");
        
        bgroup1  = new ButtonGroup();
        
        JPanel mpanel = new JPanel();
        bgroup1.add( onRadio );
        bgroup1.add( offRadio );
        mpanel.add(onRadio);
        mpanel.add(offRadio);
        JPanel cpanel = new JPanel();
         
       
        
        
        
       
        
        contentpane.add(lset, 0,0);
        contentpane.add(gap1, 1,1);
        contentpane.add(laudi, 2,2);
        contentpane.add(mpanel, 3,3);
         contentpane.add(gap2, 4,4);
         contentpane.add(lmode, 5,5);
         contentpane.add(cpanel, 6,6);
      

	validate();
    }
     public void setFF(firstfram f){
         firstfram = f;
     }
    public static void main(String[] args) 
    {
	settingfram setframe = new settingfram();
	setframe.AddComponents();
    }
}

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainClass extends JFrame implements MouseListener, ActionListener
{
	
	
	JButton firstButton, calculate, back, clear;
 	JLabel label; 
 	JLabel[] labelArray;
	JTextField lessonSize; 
	JTextField[] lessonsName;
	JTextField[] lessonsCredit;
	JTextField[] lessonsPoint;
	String[] names;
	String[] credits;
	String[] points;
	float[] fCredits;
	float totalPoint = 0, totalCredit = 0, result = 0;
	JPanel panel = new JPanel();
	Graphics graph = panel.getGraphics();
	int sizeOfLabel = 20;
	int errorType = 0;
	int size = 0;
	int SIZEY = 400, SIZEX = 400;
	
	public MainClass()
	{
		add(panel);
		setVisible(true);
		setSize(SIZEX,SIZEY);
		panel.setLayout(null);
	    	getContentPane().add(panel);
	    	addMouseListener(this);
	    	back = new JButton();
	    	firstScreen();
	    	setDefaultCloseOperation(3);
	}
	

	
	public static void main(String args[])
	{
	  new MainClass(); 	
	}

	@Override
	public void actionPerformed(ActionEvent act) 
	{
		
		if(act.getActionCommand() == firstButton.getText())
		{
			
			if(lessonSize.getText().equals("0") || lessonSize.getText().equals(""))
				{
					
					errorMet();
					return;
				}
			else
			{
				try{size =  Integer.parseInt(lessonSize.getText());}
				catch(Exception e){errorMet(); return;}
				if(size < 0) {errorMet(); return;}
				secondScreen();
			}
			
		}
		else if(act.getActionCommand() == back.getText())
		{
			panel.removeAll();
			totalPoint = totalCredit = result = 0;
			switch(errorType)
			{
			   case 1: 
			   		firstScreen(); 
			   		break;
			   case 2: 
			   		secondScreen(); 
			   		upload(); 
			   		break;
			   case 3: 
			   		thirdScreen(); 
			   		upload(); 
			   		break;
			   default: 
			   		firstScreen();
			}
		}
		
		else	if(act.getActionCommand() == calculate.getText())
		{
			names = new String[size];
			credits = new String[size];
			points = new String[size];
			fCredits = new float[size];
			for(int count = 0; count < size; count++)
			{
				names[count] = lessonsName[count].getText();
				credits[count] = lessonsCredit[count].getText(); 
				points[count] = lessonsPoint[count].getText();
			}
			
			for(int count = 0; count < size; count++)
			{
				try{fCredits[count] =  Float.parseFloat(credits[count]);}
				catch(Exception e){ errorMet(); return; }
				
				
			}
			
			for(int count = 0; count < size; count++)
			{
				
					if(points[count].equalsIgnoreCase("aa"))
					{ 	
						totalPoint += fCredits[count]*4; 
						totalCredit += fCredits[count];
					}
					else if(points[count].equalsIgnoreCase("ba"))
					{
						totalPoint += fCredits[count]*3.5; 
						totalCredit += fCredits[count];
					}
					else if(points[count].equalsIgnoreCase("bb"))
					{
						totalPoint += fCredits[count]*3; 
						totalCredit += fCredits[count];
					}
					else if(points[count].equalsIgnoreCase("cb"))
					{
						totalPoint += fCredits[count]*2.5; 
						totalCredit += fCredits[count];
					}
					else if(points[count].equalsIgnoreCase("cc"))
					{
						totalPoint += fCredits[count]*2; 
						totalCredit += fCredits[count];
					}
					else if(points[count].equalsIgnoreCase("dc"))
					{
						totalPoint += fCredits[count]*1.5; 
						totalCredit += fCredits[count];
					}
					else if(points[count].equalsIgnoreCase("dd"))
					{
						totalPoint += fCredits[count]*1; 
						totalCredit += fCredits[count];
					}
					else if(points[count].equalsIgnoreCase("fd"))
					{
						totalPoint += fCredits[count]*0.5; 
						totalCredit += fCredits[count];
					}
					else if(points[count].equalsIgnoreCase("ff"))
					{
						totalPoint += fCredits[count]*0; 
						totalCredit += fCredits[count];
					}
					else 
					{
						errorMet(); return;
					}
					
						
				}
				
				result = totalPoint/totalCredit;
				thirdScreen();
			}
		
		
		else	if(act.getActionCommand() == clear.getText())
		{
			totalPoint = totalCredit = result = size = 0;
		    	firstScreen();	
		}
	}
	
	public void upload()
	{
		for(int count = 0; count < size; count++)
		{
			lessonsName[count].setText(names[count]);
			lessonsCredit[count].setText(credits[count]);
			lessonsPoint[count].setText(points[count]);
		}
	}
	
	public void errorMet()
	{
		panel.removeAll();
		String errorMessage = "";
		
		switch(errorType)
		{
		 case 1: 
		 		errorMessage = "Please enter lessons size as integer !!!";
		 		break;
		 case 2: 
		 		errorMessage = "Please you check credits and points!!!";
		         	break;
		 case 3: 	break;
		 default: 
		}
		
        label.setSize(errorMessage.length()*10, sizeOfLabel);
        label.setText(errorMessage);
        label.setLocation(80, 140);
        panel.add(label);
	     
		back = new JButton();
		back.setText("BACK");
		back.setLocation(130, 250);
		back.setSize(120, 30);
		back.addActionListener(this);
		panel.add(back);
		repaint();
	}
	
	public void firstScreen()
	{
		panel.removeAll();
		firstButton = new JButton();
		lessonSize = new JTextField();
		label = new JLabel();
		errorType = 1;
	    	int pointx = 40, pointy = 170;
		
		firstButton.setText("OK");
	    	lessonSize.setBounds(pointx+130, pointy, 40, 20);
	    	label.setBounds(pointx, pointy, 120, sizeOfLabel);
	    	firstButton.setBounds(pointx+180, pointy, 80, 20);
	    	label.setText("How many lessons?");
	    	
	    	panel.add(lessonSize);
	    	panel.add(label);
	    	panel.add(firstButton);
		firstButton.addActionListener(this);
		repaint();
	}
	
	public void secondScreen()
	{
		errorType = 2;
		int pointx, pointy=20, sizex = 200, sizey=40;
		int tempx, tempy;
		pointx = pointy = 20;
		sizex = 150; sizey = 40;

		tempx = pointx; tempy = pointy;
		
		panel.removeAll();
		labelArray = new JLabel[3];
		lessonsName = new JTextField[size];
		lessonsCredit = new JTextField[size];
		lessonsPoint = new JTextField[size];
		calculate = new JButton();
		label.setBounds(tempx, tempy, 300, 30);
		tempy += 30;
		label.setText("Please use  '.'  for floating numbers like '1.5'");
		panel.add(label);
		
		for(int count = 0; count < 3; count++)
		{
			labelArray[count] = new JLabel();
			labelArray[count].setBounds(tempx, tempy, sizex, sizey);
			switch(count)
			{
			  case 0: labelArray[count].setText("Courses"); break;
			  case 1: labelArray[count].setText("Credits"); break;
			  case 2: labelArray[count].setText("Letter Points"); break;
			}
			tempx += sizex;
			sizex = 100;
			panel.add(labelArray[count]);
		}
		
		tempx = pointx; 
		tempy = pointy+sizey+20;
		
		sizey = 18;
		for(int count = 0; count < size; count++)
		{
			lessonsName[count] = new JTextField();
			lessonsCredit[count] = new JTextField();
			lessonsPoint[count] = new JTextField();
			
			lessonsName[count].setBounds(tempx, tempy, 120, sizey);
			lessonsCredit[count].setBounds(tempx+150, tempy, 60, sizey);
			lessonsPoint[count].setBounds(tempx+250, tempy, 60, sizey);
			lessonsName[count].setFocusCycleRoot(false);
			panel.add(lessonsName[count]);
			panel.add(lessonsCredit[count]);
			panel.add(lessonsPoint[count]);
			tempy += sizey +3;
			
		}
		calculate.setText("Calculate");
		calculate.setBounds(SIZEX/2-50, tempy, 100, 30);
		calculate.addActionListener(this);
		panel.add(calculate);
		tempy = tempy + 30;
		
		if(tempy > SIZEY)
		{
			setSize(SIZEX, tempy+sizey+40);
		}
		
		
		
		repaint();
		
		
	}
	
	public void thirdScreen()
	{
		panel.removeAll();
		back = new JButton();
		clear = new JButton();
		
		setSize(SIZEX, SIZEY);
		label.setBounds(SIZEX/2-50, SIZEY/2, 100, 30);
		label.setText("Result: " + result);
		panel.add(label);

		back.setText("BACK");
		back.setLocation(50, 250);
		back.setSize(120, 30);
		back.addActionListener(this);
		panel.add(back);
		
		clear.setText("NEW");
		clear.setLocation(200, 250);
		clear.setSize(120, 30);
		clear.addActionListener(this);
		panel.add(clear);

		repaint();
		
	}
	
	@Override
	public void mouseClicked(MouseEvent mouse) 
	{
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
//package GhostArrayStudent;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GhostlyArray extends JFrame implements ActionListener{
	public static final int BOARDWIDTH=500;
	public static final int BOARDHEIGHT=500;
	private JPanel panel;
	private JTextField display;	
		
	public void play(){		
		double max = 0;//used to store maximum size later

		/**~~~~STEP 1: declare and fill an array of Ghosty s ~~~~**/ 
		Ghosty[] ghosts=new Ghosty[100];
		
		for(int i=0;i<ghosts.length;i++)
			ghosts[i]=new Ghosty();
		
		
		/**~~~~ end STEP 1 ~~~~**/
		
		//infinite loop for animation	
		while(true){
			/***** write some code here ****/	
			//STEP 2: tell each Ghosty to move
			for(int i=0; i<ghosts.length;i++)
				ghosts[i].move();
			
			//STEP 3: tell ghosts that are larger than 60 to get darker
			for(int i=0; i<ghosts.length; i++)
				if(ghosts[i].getSize()>60)
					ghosts[i].darken();
			
			//STEP 4: store the largest size into the max variable
			for(int i=0; i<ghosts.length;i++)
				if(ghosts[i].getSize()>=max)
					max=ghosts[i].getSize();
			
			//STEP 5: tell red ghosts to grow by 1 pixel
			for(int i=0; i<ghosts.length;i++)
				if(ghosts[i].getHue()==Color.RED)
					ghosts[i].setSize(ghosts[i].getSize()+1);
				
			/********^^^end your code^^^*******/			
			display.setText("max size is "+max);
			pause();		
		}//end while loop
		
	}//end play function
	
	
	public void actionPerformed(ActionEvent e) {
		JButton theyPressed = (JButton)e.getSource();			
	}

	
	public GhostlyArray(){
		super("Array Playground");		
		panel = new JPanel();
		this.add(panel);	
		
		
		display = new JTextField();
		this.add(display, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setSize(BOARDWIDTH, BOARDHEIGHT+45);
		this.setVisible(true);
		play();
	}
	
	public void pause(){
		((Graphics2D)panel.getGraphics()).drawImage(Ghosty.getImg(),0,0, null);
		try{Thread.sleep(75);}catch(Exception ex){ex.printStackTrace();}
		Ghosty.getImg().getGraphics().clearRect(0, 0, BOARDWIDTH, BOARDHEIGHT);		
	}
	public static void main(String[] args) {new GhostlyArray();}
	

}

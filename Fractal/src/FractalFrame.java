/*
 * File: FractalFrame.java
 * Purpose:  To display your FractalPanel and save your fractal
 * Author: Mr. Reed
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FractalFrame extends JFrame implements ActionListener{
	JLabel numLabel;
	JTextField numField;
	JButton draw, nextLvl;
	FractalPanel panel;
	JPanel buttonPanel;
	JScrollPane sp;
	private JMenuItem save;
		
	/************************************************
	 * Constructor
	*************************************************/
	public FractalFrame(){
		this( new FractalPanel());
	}
	
	/************************************************
	 * Constructor 2
	*************************************************/
	public FractalFrame(FractalPanel p1){
		super("Fractal Frame!");
		Container c = this.getContentPane();
		makeMenu();
		makeButtonPanel();
		c.add(buttonPanel, BorderLayout.NORTH);
		panel = p1;
		sp = new JScrollPane(panel);
		sp.setPreferredSize(new Dimension(100,100));
		c.add(sp);
		

		//finishing touches
		this.setSize(500,500);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/*Preconditions: NONE
	 *Postconditions: the buttons and fields are created and added
	 *  	to buttonPanel
	 */
	private void makeButtonPanel(){
		buttonPanel = new JPanel();
		numLabel = new JLabel("Levels");
		numField = new JTextField(5);
		numField.setText("0");
		draw = new JButton("Draw");
		draw.addActionListener(this);
		
		nextLvl = new JButton("^");
		nextLvl.addActionListener(this);
		
		buttonPanel.add(numLabel);
		buttonPanel.add(numField);
		buttonPanel.add(draw);
		buttonPanel.add(nextLvl);
	}
	
	private void makeMenu()
   {
	 JMenuBar bar= new JMenuBar();
	 setJMenuBar(bar);

	 //****File Menu****
	 JMenu fileMenu=new JMenu("File");
	 fileMenu.setMnemonic('F');
	 bar.add(fileMenu);
	 

	 //Menu item for saving as jpg image format
	 save = new JMenuItem("Save File");
	 save.addActionListener(this);
	 
	 fileMenu.add(save);
	 JMenuItem exit=new JMenuItem("Exit");
	 exit.addActionListener(
	   new ActionListener(){
		 public void actionPerformed(ActionEvent e)
		 {  //exit program
			 System.exit(0);
		 }
	   }
	 );//end addActionListener
	 fileMenu.add(exit);
   }
	
	
	/**************************************************
	 Handles the button event
	 **************************************************/
	public void actionPerformed(ActionEvent e){
		int x=0;
		if(e.getSource() == save){
			String nm = JOptionPane.showInputDialog(this,"File Name?");
			if(nm.indexOf(".png")==-1)
				nm+=".png";
			panel.saveFile(new File(nm));
			JOptionPane.showMessageDialog(this, "The File "+nm+" has been saved!  Click on your project and press F5 to refresh.");
		}
		else{//must have been the up or draw button
			try{//get integer from the number field
				x = Integer.parseInt(numField.getText().trim());


				if(e.getSource() == draw){					
					panel.getStarted(x); //draw the fractal
					panel.repaint();  // make system redraw the frame
				}else{
					numField.setText((x+1)+"");
					panel.getStarted(x+1); //draw the fractal
					panel.repaint();  // make system redraw the frame
				}
			}
			catch(NumberFormatException ex){//bad number
				JOptionPane.showMessageDialog(null, "Enter an Integer!");
			}
			catch(Exception exx){
				exx.printStackTrace();
			}
		}
	}



}

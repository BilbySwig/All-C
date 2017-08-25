// File:  FractalPanel.java
//Author: Mr. Reed and YOU :)
// Purpose:  a JPanel containing a BufferedImage object and a recursive
//           drawing function for making fun fractals


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import com.sun.image.codec.jpeg.*;
import java.io.*;
import java.util.Date;

public class FractalPanel extends JPanel{
	public static final int WIDTH=1200, HEIGHT=1000;
	protected Graphics2D myGraphics;		
	protected int totalLevels;
	protected Color bgColor = Color.WHITE;
	private BufferedImage img;  // the image

	//Constructor
	//Preconditions: NONE
	//Postconditions:  The panel is created 640x480 with a blank image	
	public FractalPanel(){
		super();
		this.setSize(new Dimension(WIDTH,HEIGHT));
		this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		img = new BufferedImage(this.getWidth(), this.getHeight(),  BufferedImage.TYPE_INT_RGB);
		//Make the image blank and white
		setBackground(bgColor);
		Graphics2D ig = img.createGraphics();
		ig = img.createGraphics();
		ig.setBackground(bgColor);
		ig.clearRect(0,0,img.getWidth(),img.getHeight());	
	}
	
	
	
	//Preconditions: none
	//Postconditions:  the fractal and panel are repainted
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D ig = (Graphics2D)g;
		ig.drawImage(img,0,0,this);
	}
	
	//Preconditions: None
	//Postconditions: The fractal is drawn to the bufferedImage and on the screen
	/**~~~~~~~~~~~~~~~ OVERRIDE ME ~~~~~~~~~~~~~~**/
	public void getStarted(int numLevels){
		//the image is cleared (set to blank & white)
		//the image is cleared (set to blank & white)
		totalLevels = numLevels;
		myGraphics = img.createGraphics();
		myGraphics.setBackground(bgColor);
		myGraphics.clearRect(0,0,img.getWidth(),img.getHeight());		
		/** OVERRIDE THIS AND PLACE FIRST CALL TO RECUR HERE! **/
		
	}


	/*Preconditions:
	 * 	g is the graphics object for the bufferedImage to which the fractal will be drawn
	 *  c is the desired color for this level of the fractal
	 *  num is the remaining number of desired fractal levels
	 *Postconditions: Part of the current level is drawn, and recurvsive calls are executed
	 *      that will draw the next level of the fractal 
	 */
	/**~~~~~~~~~~~~~~~ OVERRIDE ME ~~~~~~~~~~~~~~**/
	private void recur(int levelsDrawn){
		pause(50.0/totalLevels, levelsDrawn);
		
		
		

	}
	
	
	public void pause(double time, int lvl){
		//System.out.println("TIME:"+time);
		if((int)time<=0 || Double.isInfinite(time)||Double.isNaN(time))
			return;
		Graphics2D ig = (Graphics2D)this.getGraphics();
		ig.drawImage(img,0,0,this);
		//ig.setColor(Color.WHITE);
		//ig.fillRect(70,0,20,10);
		//ig.setColor(Color.BLACK);
		System.out.println("Drawing level "+lvl);
		ig.drawString("Drawing level "+lvl, 0, 10+10*lvl);
		try{
			Thread.sleep((int)Math.round(time));
		}catch(InterruptedException ie){}
	}
	
	/*
	Preconditions: f is the file to be saved to
	Postconditions: The alteredImage is written to the specified file
	This file is called by the frame that contains the ImageObject
	*/
	public void saveFile(File f)
	{
	  myGraphics.setColor( new Color(128,128,128));
	  
	  myGraphics.drawString(new Date()+": "+System.getProperty("user.name"), 5, 15);
	  try{
		  ImageIO.write(img, "png", f);
	  }
	  catch(IOException e)
	  {
		JOptionPane.showMessageDialog(null, e.getMessage());
	  }

	}
	
}

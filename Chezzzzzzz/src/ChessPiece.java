import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JOptionPane;

public abstract class ChessPiece {	
	private Image img;
	//variable for what team/color i am
	private boolean team;
	//variable for what Square I'm sitting on 
	private Square square;
	
	//Constructor :  you will need some more parameters!
	public ChessPiece(String im,boolean t, Square s){
		team=t;
		square=s;
		loadImage(im);
		s.setChessPiece(this);
	    
		//set up your other variables


	}//end constructor
	
	public Square getSquare(){return square;}
	//accessor and mutator for moving
	public void setSquare(Square s){
		square=s;
	}
		
	//accessor of team for turns
	public boolean isBlack(){return team;}
	
	//helper function for loading up your image
	private void loadImage( String im ){
		img = Toolkit.getDefaultToolkit().getImage( getClass().getResource(im) );		
	    
		MediaTracker tracker = new MediaTracker (new Component () {});
		tracker.addImage(img, 0);
		//block while reading image
		try { tracker.waitForID (0); }
	        catch (InterruptedException e) {
	        	JOptionPane.showMessageDialog(null, "Error reading file");
	        }
	}//end loadImage

	public void draw(Graphics g){
		g.drawImage(img,0,0,90,90,null,null);
	}
	
	public abstract boolean isMoveLegal(Square dest);
}

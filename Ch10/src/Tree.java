//package ForestFireForKids;

import java.awt.*;
import javax.swing.*;

public class Tree extends JButton{
	private boolean onFire;
	private boolean doneBurnt;
	private static Image[] images;
	private static final int numImages=3;
	private int myImage;
	private int myRow, myCol; 
	
	public Tree(int r, int c){
		super();
		myRow = r;
		myCol = c;
		onFire = false;
		myImage = (int)(Math.random()*numImages);
		if(images==null)
			loadImages();
	}
	
	public int getRow(){return myRow;}
	public int getCol(){return myCol;}
	
	
	public boolean isOnFire(){ return onFire; }
	public void setFire( boolean b){ onFire = b;  this.paint(this.getGraphics());}
	
	public boolean isBurnt(){return doneBurnt;}
	public void burnout(){
		if(onFire){
			onFire=false;
			doneBurnt = true;
			this.paint(this.getGraphics());
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(images[myImage], 0, 0, this.getWidth(), this.getHeight(), null);
		if( onFire ){
			g.setColor( new Color(255,0,0,100));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		else if(doneBurnt){
			g.setColor( new Color(0,0,0,200));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
	}
	private void loadImages(){
		MediaTracker mt = new MediaTracker(new Component(){});
		images = new Image[numImages];
		for(int i=0; i<numImages; i++){
			images[i] = Toolkit.getDefaultToolkit().getImage( getClass().getResource("tree"+i+".png"));
			mt.addImage(images[i],i);
		}
		
		try{  mt.waitForAll();}
		catch(Exception ex){ex.printStackTrace();}
	}
	
}

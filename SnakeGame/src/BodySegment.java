

import java.awt.Color;
import java.awt.Graphics;

public class BodySegment {
	//this BodySegment will act like a NODE
	//  he will point to the next BodySegment in the Snake
	//  You will need a variable for this
	
	public static final int SIZE=15;
	public Color hue = new Color(0,255,255);//color of snake
	public static int nextID = 0;
	public int myID;
	private int xPos, yPos;
	private BodySegment next, prev;
	
	public BodySegment(int x, int y){
		xPos = x;
		yPos = y;
		
		myID = nextID++;
		next=null;
		prev=null;
	}
	
	//~~~~~~~~~~~~~~~~accessors and mutators~~~~~~~~~~~~~~~~~~~~~~
	public BodySegment getNext(){return next;}
	public void setNext(BodySegment thing){next=thing;}
	public BodySegment getPrev(){return prev;}
	public void setPrev(BodySegment thing){prev=thing;}
	
	public int getX(){return xPos;}
	public int getY(){return yPos;}
	
	public boolean isTouching( BodySegment sp){
		int Xdis=Math.abs(sp.getX()-this.getX());
		int Ydis=Math.abs(sp.getY()-this.getY());// if x and y coordinates are close enough to something snakes can touch, returns true
		return ((Xdis<=10) && (Ydis<=10));		
	}
	
	//each BodySegment knows how to draw himself :)
	public void draw(Graphics g){
		g.setColor(hue);
		g.fillOval(xPos, yPos, SIZE, SIZE);
		
	}
}

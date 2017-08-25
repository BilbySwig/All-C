//package phase1Kids;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
// move, teleport, randomColor
public class PacMan {
	//PRIVATE MEMBER VARIABLES	
	private int speed = 1; //keep this at 1!!! (won't get hung in wall)
	private int size=25;
	private boolean open;
	private int xPos, yPos; //CENTER of pacman	
	private int direction;  //this can ONLY be 0, 1, 2, or 3
	private Color hue;
	private int score;
	private boolean shield;
	private int shieldCount;
	
        /*******don't mess with these variables, thanks :) **********/
	public static final int UP=0, RIGHT=1, DOWN=2, LEFT=3;
	public static final int BOARDWIDTH=750, BOARDHEIGHT=650;
	public int[] mouth = {90+45/2, 45/2, 270+45/2, 180+45/2}; //degrees for edge of mouth	
	private int frameCount=0; //for mouth
	/*************************************************/

	//constructors:  normal, default, just x&y, x&y&dir&hue
	public PacMan(int x, int y, int d, boolean o, boolean sh, Color h){
		xPos = x;
		yPos = y;
		direction = d;
		open = o;
		score = 0;
		shield = sh;
		hue = h;
		speed = 1;
		size=25;
		shieldCount=0;
	}
	
	//default constructor
	public PacMan(){this(375, 325, 2, false, false, Color.YELLOW);}
	
	//takes in x and y
	public PacMan(int x, int y){this(x, y, 2, false,false, Color.YELLOW);}
	
	//takes in x, y, direction, and color
	public PacMan(int x, int y, int d, Color color){this(x, y, d, false, false, color);}

	//write move & teleport
	public void move(){
		if(direction==RIGHT)
			xPos+=speed;
		if(direction==LEFT)
			xPos-=speed;
		if(direction==UP)
			yPos-=speed;
		if(direction==DOWN)
			yPos+=speed;
		shieldCount-=1;
		if(shieldCount<=0)
			shield=false;
	}
	public void teleport(){
		xPos=(int)(Math.random()*750);
		yPos= (int)(Math.random()*650);
	}
	
	public void randomColor(){
		hue= new Color((int)(Math.random()*255+1), (int)(Math.random()*255+1), (int)(Math.random()*255+1) );
	}
	
	public void eat(Food x){
		if(x.isPowerUp()){
			shield=true;
			shieldCount=200;
		}	
		score+=1;	
	}
	
	//accessors
	public int getXPos(){return xPos;}
	public int getYPos(){return yPos;}
	public int getDirection(){return direction;}
	public boolean getShield(){return shield;}
	public Color getHue(){return hue;}
	public int getSpeed(){return speed;}
	public int getSize(){return size;}
	public int getScore(){return score;}
	//mutators
	public void setxPos(int x){xPos=x;}
	public void setyPos(int x){yPos=x;}
	public void setDirection(int x){direction=x;}
	public void setShield(boolean x){shield=x;}
	public void setHue(Color x){hue=x;}
	public void setSpeed(int x){speed=x;}
	public void setSize(int x){size=x;}
	public void setScore(int x){score=x;}
	
	//toString
	public String toString(){
		String special;
		special= "("+xPos+","+yPos+")\n Score: "+score+"\n Speed: "+speed;
		return special;
	}
	
	
	/*****Give these to kids**************/	
	
	
	/********respond to keyboard******/
	public void upPressed(){direction = UP;}
	public void downPressed(){direction = DOWN;}
	public void leftPressed(){direction = LEFT;}
	public void rightPressed(){direction = RIGHT;}
	/**********don't mess up my draw***********/
	public void draw(Graphics g){
		frameCount++;
		g.setColor(hue);
		if(open){			
			g.fillArc(xPos-size/2, yPos-size/2, size, size, mouth[direction%mouth.length], 360-45);
		}
		else
			g.fillOval(xPos-size/2, yPos-size/2, size, size);
		if(shield){
			g.setColor(new Color(255,0,255));
			g.drawOval(xPos-size, yPos-size, 2*size, 2*size);
			g.setColor(new Color(255,0,255,80));
			g.fillOval(xPos-size, yPos-size, 2*size, 2*size);
		}
		if(frameCount%5==0)//slow down the mouth
			open = !open;		
	}
	
}//end class

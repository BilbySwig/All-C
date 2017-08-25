//package SmurfInfectionStudent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Smurf {
	/**  Private member variables  **/
	//(xPos,yPos) is the CENTER of the ghost! 
	private int xPos, yPos;
	private double sz;
	private Color hue;
	private int direction;
	private int speed;
	private int timeInfected;
	private boolean infected;
	private Image myPic;
	
	private static final int BOARDWIDTH = 500, BOARDHEIGHT=500;
	private static BufferedImage img = new BufferedImage(BOARDWIDTH,BOARDHEIGHT,BufferedImage.TYPE_INT_RGB);;
	public static final int UP=0, RIGHT=1, DOWN=2, LEFT=3;	
	private Color[] possible = {Color.RED, Color.PINK, new Color(0,255,255,99), Color.ORANGE};
	private static final Image[] pics = {Toolkit.getDefaultToolkit().getImage("./src/smurf0.png"), Toolkit.getDefaultToolkit().getImage("./src/smurf1.png"), Toolkit.getDefaultToolkit().getImage("./src/smurf2.png"), Toolkit.getDefaultToolkit().getImage("./src/smurf3.png"), Toolkit.getDefaultToolkit().getImage("./src/smurfPurple.png")};
	
	private final Font fnt = new Font("Arial",Font.PLAIN,8);
	
	/** constructors!  **/
	public Smurf(int x, int y, int picN, int s, int ss){
		xPos = x;
		yPos = y;		
		myPic = pics[picN];
		direction = (int)(Math.random()*4);
		sz = s;
		speed = ss;
		timeInfected = 0;
		infected = false;
	}
	//default constructor
	public Smurf(){
		xPos = (int)(Math.random()*(500-sz)+sz/2);
		yPos = (int)(Math.random()*(500-sz)+sz/2);		
		//hue = possible[(int)(Math.random()*possible.length)];
		myPic = pics[(int)(Math.random()*pics.length-1)];
		direction = (int)(Math.random()*4);		
		sz = (int)(Math.random()*10)+50;
		speed = 1;
		timeInfected = 0;
		infected = false;
	}
	//copy constructor (make twins)
	public Smurf(Smurf other){
		this.timeInfected = other.timeInfected;		
		this.direction = other.direction;
		//this.hue = other.hue;
		this.myPic = other.myPic;
		this.speed = other.speed;
		this.sz = other.sz;
		this.xPos = other.xPos;
		this.yPos = other.yPos;
		this.infected = other.infected;
	}
	
	public boolean isTouching(Smurf other){
		int dx = this.xPos - other.xPos;
		int dy = this.yPos - other.yPos;
		double dist = Math.sqrt( dx*dx + dy*dy );
		if( dist < sz/2+other.sz/2)
			return true;
		else
			return false;
	}
	
	//accessors
	public int getDirection(){return direction;}
	public double getSize(){return sz;}
	public int getXPos(){return xPos;}
	public int getYPos(){return yPos;}	
	public Color getHue(){return hue;}
	public int getSpeed(){return speed;}
	public int getTimeInfected(){return timeInfected;}
	public boolean isInfected(){return infected;}
	
	//mutators
	public void setDirection(int d){direction = d;}
	public void setSize(double s){sz = s;}
	public void setXPos(int x){xPos = x;}
	public void setYPos(int y){yPos = y;}
	public void setHue(Color h){hue = h;}
	public void setSpeed(int s){speed = s;}	
	public void reverseDirection(){direction = (direction+2)%4;}
	public void darken(){hue = hue.darker();}
	//public void setAge(int a){timeInfected = a;}
	public void infectMe(){
		if(!infected){
			infected = true;
			myPic = pics[pics.length - 1];
			speed*=2;
		}
	}
	
	public void move(){
		if(infected)
			timeInfected++;
		int xvel=0, yvel=0;
		switch(direction){
			case UP: yvel=-1*speed; break; 
			case DOWN: yvel=1*speed; break;
			case LEFT: xvel=-1*speed; break;
			case RIGHT: xvel=1*speed; break;
		}

		xPos+=xvel;
		yPos+=yvel;
		
		//if(Math.random()<0.05)
		//	direction = (int)(Math.random()*4);
	}
	
	public static BufferedImage getImg(){return img;}
	
	public void draw(){
		img.getGraphics().drawImage(myPic,(int)(xPos-sz/2.0), (int)(yPos-.5*1.3*sz),(int)sz,(int)(1.3*sz),null);
		if(infected)
			img.getGraphics().drawString(""+timeInfected, xPos, (int)(yPos-sz/2-5));
	}
}

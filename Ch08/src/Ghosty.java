//package GhostArrayStudent;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Ghosty {
	/**  Private member variables  **/
	//(xPos,yPos) is the CENTER of the ghost! 
	private int xPos, yPos;
	private double sz;
	private Color hue;
	private int direction;
	private int speed;
	private int age;
	
	private static final int BOARDWIDTH = 500, BOARDHEIGHT=500;
	private static BufferedImage img = new BufferedImage(BOARDWIDTH,BOARDHEIGHT,BufferedImage.TYPE_INT_RGB);;
	public static final int UP=0, RIGHT=1, DOWN=2, LEFT=3;	
	private Color[] possible = {Color.RED, Color.PINK, new Color(0,255,255,99), Color.ORANGE};
	private int alpha, alphaChange;
	private final Font fnt = new Font("Arial",Font.PLAIN,10);
	
	/** constructors!  **/
	public Ghosty(int x, int y, Color h, int s, int ss){
		xPos = x;
		yPos = y;		
		hue = h;//possible[(int)(Math.random()*possible.length)];
		direction = (int)(Math.random()*4);
		alpha = (int)(Math.random()*155)+200;
		alphaChange=-5;
		sz = s;
		speed = ss;
		age = 0;
		draw();
	}
	//default constructor
	public Ghosty(){
		sz = (int)(Math.random()*55)+25;
		xPos = (int)(Math.random()*(500-sz)+sz/2);
		yPos = (int)(Math.random()*(500-sz)+sz/2);	
		//System.out.println(xPos+", "+yPos+": "+sz);
		hue = possible[(int)(Math.random()*possible.length)];
		direction = (int)(Math.random()*4);
		alpha = (int)(Math.random()*155)+200;
		alphaChange=-5;
		
		speed = 1;
		age = 0;
		draw();
	}
	//copy constructor (make twins)
	public Ghosty(Ghosty other){
		this.age = other.age;
		this.alpha = other.alpha;
		this.direction = other.direction;
		this.hue = other.hue;
		this.speed = other.speed;
		this.sz = other.sz;
		this.xPos = other.xPos;
		this.yPos = other.yPos;
		draw();
	}
	
	
	//accessors
	public int getDirection(){return direction;}
	public double getSize(){return sz;}
	public int getXPos(){return xPos;}
	public int getYPos(){return yPos;}	
	public Color getHue(){return hue;}
	public int getSpeed(){return speed;}
	public int getAge(){return age;}
	
	//mutators
	public void setDirection(int d){direction = d;}
	public void setSize(double s){sz = s;}
	public void setXPos(int x){xPos = x;}
	public void setYPos(int y){yPos = y;}
	public void setHue(Color h){hue = h;}
	public void setSpeed(int s){speed = s;}
	public void setAge(int a){age = a;}
	public void reverseDirection(){direction = (direction+2)%4;}
	public void darken(){hue = hue.darker();}
		
	public void move(){
		age++;
		int xvel=0, yvel=0;
		switch(direction){
			case UP: yvel=-1*speed; break; 
			case DOWN: yvel=1*speed; break;
			case LEFT: xvel=-1*speed; break;
			case RIGHT: xvel=1*speed; break;
		}

		xPos+=xvel;
		yPos+=yvel;
		if(xPos+sz/2>500 || xPos-sz/2<0)
			direction = (direction+2)%4;
		if(yPos+sz/2>500 || yPos-sz/2<0)
			direction = (direction+2)%4;
		//if(Math.random()<0.05)
		//	direction = (int)(Math.random()*4);
		draw();
	}
	
	public void teleport(){
		xPos = (int)(Math.random()*(500-sz)+sz/2);
		yPos = (int)(Math.random()*(500-sz)+sz/2);	
	}
	
	public static BufferedImage getImg(){return img;}
	
	public void draw(){
		alpha+=alphaChange;
		alpha = Math.max(0, Math.min(255, alpha) );//don't let it out of 0-255
		if(alpha < 200 || alpha>=255)
			alphaChange*=-1;
		Graphics g=img.getGraphics();
		g.setColor(new Color(hue.getRed(), hue.getGreen(), hue.getBlue(), alpha));
		
		//my top corner (i'm centered at xPos,yPos)
		int cnrX = (int)(xPos - sz/2);
		int cnrY = (int)(yPos - Math.floor(sz/2));
		int leftX = (int)(cnrX+1/5.0*sz);
		int rightX = (int)(cnrX+3/5.0*sz);
		int eyeY = (int)(cnrY+1/4.0*sz);
		int eyeSz = (int)(1/5.0*sz);
		//body
		g.fillArc(cnrX, cnrY, (int)sz, (int)sz, 0,180);
		
		g.fillRect(cnrX, yPos, (int)sz, (int)(sz/2+1));
		
		//triangles on bottom		
		int bottom = (int)(yPos+sz/2+1);
		int left = (int)(xPos-sz/2);
		int[] ys = {bottom, (int)(bottom+1.0/6*sz), bottom};
		int[] xs = {left, (int)Math.round(left+1.0/6*sz), (int)Math.round(left+2.0/6*sz)};
		for(int tri=0; tri<3; tri++){			
			Polygon p = new Polygon(xs,ys,3);			
			g.fillPolygon(p);
			for(int v=0; v<3; v++)
				xs[v] += 2/6.0*sz;
		}
		//eyes
		g.setColor(Color.WHITE);		
		g.fillOval(leftX, eyeY, eyeSz, eyeSz);
		g.fillOval(rightX, eyeY, eyeSz, eyeSz);
		//pupils
		g.setColor(Color.BLUE);
		g.fillOval(leftX+eyeSz/2, eyeY+eyeSz/2, eyeSz/2, eyeSz/2);
		g.fillOval(rightX+eyeSz/2, eyeY+eyeSz/2, eyeSz/2, eyeSz/2);
		g.setFont(fnt);
		g.drawString((int)sz+"", (int)(xPos-sz/4), (int)(yPos+sz/4));
		
	}
}

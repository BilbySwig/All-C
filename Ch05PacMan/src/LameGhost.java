//package phase1Kids;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;


public class LameGhost {
	//private static GameBoard board;
	//(xPos,yPos) is the CENTER of the ghost! 
	private int xPos, yPos;
	private final int sz=27;
	private Color hue;
	private int direction;
	public static final int UP=0, RIGHT=1, DOWN=2, LEFT=3;	
	private Color[] possible = {Color.RED, Color.PINK, new Color(0,255,255,99), Color.ORANGE};
	private int alpha, alphaChange;
	
	public LameGhost(int x, int y, Color h){
		xPos = x;
		yPos = y;		
		hue = h;
		direction = (int)(Math.random()*4);
		alpha = (int)(Math.random()*155)+200;
		alphaChange=-5;
	}
	public LameGhost(){
		xPos = (int)(Math.random()*(PacMan.BOARDWIDTH-sz))+sz/2;
		yPos = (int)(Math.random()*(PacMan.BOARDHEIGHT-sz))+sz/2;		
		hue = possible[(int)(Math.random()*possible.length)];
		direction = (int)(Math.random()*4);
		alpha = (int)(Math.random()*155)+200;
		alphaChange=-5;
		
	}
	
	
	//accessors
	public int getDirection(){return direction;}
	public int getSize(){return sz;}
	public int getXPos(){return xPos;}
	public int getYPos(){return yPos;}
	
	//mutators
	public void setDirection(int d){direction = d;}	
	public void setXPos(int x){xPos = x;}
	public void setYPos(int y){yPos = y;}
	
	public void move(){
		int xvel=0, yvel=0;
		switch(direction){
			case UP: yvel=-1; break; 
			case DOWN: yvel=1; break;
			case LEFT: xvel=-1; break;
			case RIGHT: xvel=1; break;
		}

		xPos+=xvel;
		yPos+=yvel;
		if(Math.random()<0.05)
			direction = (int)(Math.random()*4);
	}
	
	public void draw(Graphics g){
		alpha+=alphaChange;
		alpha = Math.max(0, Math.min(255, alpha) );//don't let it out of 0-255
		if(alpha < 200 || alpha>=255)
			alphaChange*=-1;
		g.setColor(new Color(hue.getRed(), hue.getGreen(), hue.getBlue(), alpha));
		
		//my top corner (i'm centered at xPos,yPos)
		int cnrX = xPos - sz/2;
		int cnrY = yPos - sz/2;
		int leftX = (int)(cnrX+1/5.0*sz);
		int rightX = (int)(cnrX+3/5.0*sz);
		int eyeY = (int)(cnrY+1/4.0*sz);
		int eyeSz = (int)(1/5.0*sz);
		//body
		g.fillArc(cnrX, cnrY, sz, sz, 0,180);
		g.fillRect(cnrX, yPos, sz, sz/2+1);
		
		//triangles on bottom		
		int bottom = yPos+sz/2+1;
		int left = xPos-sz/2;
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
		
		
	}
}

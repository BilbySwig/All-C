
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;


public class Ghost {
	private static GameBoard board;
	//(xPos,yPos) is the CENTER of the ghost! 
	private boolean dead;
	private int xPos, yPos;
	private final int sz=27;
	private Color hue;
	private int direction;
	public static final int UP=0, RIGHT=1, DOWN=2, LEFT=3;	
	private Color[] possible = {Color.RED, Color.PINK, new Color(0,255,255,99), Color.ORANGE};
	private static Color eyeColor= new Color(200,245,255);
	private int alpha, alphaChange;
	
	public Ghost(int x, int y, Color h){
		xPos = x;
		yPos = y;		
		hue = possible[(int)(Math.random()*possible.length)];
		direction = (int)(Math.random()*4);
		alpha = (int)(Math.random()*155)+200;
		alphaChange=-5;
		dead = false;
	}
	public Ghost(){
		xPos = (int)(Math.random()*(PacMan.BOARDWIDTH-sz))+sz/2;
		yPos = (int)(Math.random()*(PacMan.BOARDHEIGHT-sz))+sz/2;		
		hue = possible[(int)(Math.random()*possible.length)];
		direction = (int)(Math.random()*4);
		alpha = (int)(Math.random()*155)+200;
		alphaChange=-5;
		dead = false;
	}
	
	public static void setBoard(GameBoard b){
		board = b;
	}
	
	//accessors
	public int getDirection(){return direction;}
	public int getSize(){return sz;}
	public int getXPos(){return xPos;}
	public int getYPos(){return yPos;}
	public boolean isDead(){return dead;}
	
	//mutators
	public void setDirection(int d){direction = d;}	
	public void setXPos(int x){xPos = x;}
	public void setYPos(int y){yPos = y;}	
	public void die(){dead = true;}
	
	public void move(){
		int xvel=0, yvel=0;
		switch(direction){
			case UP: yvel=-1; break; 
			case DOWN: yvel=1; break;
			case LEFT: xvel=-1; break;
			case RIGHT: xvel=1; break;
		}
		if(collisionCheck()==1){
			direction = (int)(Math.random()*4);
			return;
		}
		xPos+=xvel;
		yPos+=yvel;
	}
	
	public boolean contains(int x, int y){
		if(dead) return false;
		//remember (xPos,yPos) is the CENTER of the ghost!!!
		int wleft = getXPos()-sz/2;
		int wright = getXPos()+sz/2;
		int wtop = getYPos()-sz/2;
		int wbottom = getYPos()+sz/2;
		
		if(x>=wleft && x<=wright)
			if(y>=wtop && y<=wbottom)
				return true;
		return false;
	}
	
	//same thing, but takes a person to check 
	public int collisionCheck(){
		if(dead)
			return 0;
		ArrayList<Wall> walls = board.getWalls();
		int ans=0; //assume i hit nothing		
		//my velocities : up right down left
		int[] xvels = {0, 1, 0,-1};
		int[] yvels = {-1, 0, 1,0};
		int xvel = xvels[getDirection()];
		int yvel = yvels[getDirection()];
		
		//my "corners" --> think ahead...where WILL i be if i move?
		int pleft = getXPos()-getSize()/2 + xvel;
		int pright= getXPos()+getSize()/2 + xvel;
		int ptop = getYPos()-getSize()/2 + yvel;
		int pbottom = getYPos()+getSize()/2 + yvel;

		//check each wall
		for(Wall w: walls){		
			//check my four corners to see if i'm in wall
			if(w.contains(pleft,ptop))
				return 1;
			if(w.contains(pright,ptop))
				return 1; 
			if(w.contains(pleft,pbottom))
				return 1; 
			if(w.contains(pright,pbottom))
				return 1; 

		}
		
		return ans;
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
		
		if(!dead){//skip drawing the "sheet" when dead		
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
		}
		//eyes
		g.setColor(eyeColor);//Color.WHITE);		
		g.fillOval(leftX, eyeY, eyeSz, eyeSz);
		g.fillOval(rightX, eyeY, eyeSz, eyeSz);
		//pupils
		g.setColor(Color.BLUE);
		g.fillOval(leftX+eyeSz/2, eyeY+eyeSz/2, eyeSz/2, eyeSz/2);
		g.fillOval(rightX+eyeSz/2, eyeY+eyeSz/2, eyeSz/2, eyeSz/2);
		
		
	}
}

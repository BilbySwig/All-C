//package phase2Kids;
import java.awt.*;
import javax.swing.*;

public class Wall {
	private int xPos, yPos, width, height;
	private Color hue;
	public Wall(){
		xPos = (int)(Math.random()*PacMan.BOARDWIDTH);
		yPos = (int)(Math.random()*PacMan.BOARDHEIGHT);
		if(Math.random()<0.5){//wide wall
			width = (int)(Math.random()*100)+100;
			height = 50;
		}
		else{//tall wall
			width = 50;
			height = (int)(Math.random()*100)+100;			
		}
		hue = Color.BLUE;
		
	}
	public Wall(int x, int y, int w, int h){
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		hue = Color.blue;
	}
	
	//accessors
	public int getX(){return xPos;}
	public int getY(){return yPos;}
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	
	
	public boolean contains(int x, int y){
		int wleft = getX();
		int wright = getX()+getWidth();
		int wtop = getY();
		int wbottom = getY()+getHeight();
		
		if(x>=wleft && x<=wright)
			if(y>=wtop && y<=wbottom)
				return true;
		return false;
	}
	public void draw(Graphics g){
		//g.setColor(Color.WHITE);
		//g.drawRoundRect(xPos, yPos, width, height,5,5);
		g.setColor(hue);
		g.fillRoundRect(xPos+1, yPos+1, width-1, height-1,5,5);
	}
}
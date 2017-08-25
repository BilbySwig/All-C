//package Phase1;
import java.awt.*;

import javax.swing.*;
public class MazeCell extends JPanel{
	private boolean[] borders;
	public static final int UP = 0, RIGHT = 1, DOWN=2, LEFT = 3;
	public static final int BLANK=0, VISITED=1, DEAD=2;
	
	private int wallThickness = 3;
	private int padding = 1;
	private Stroke str;
	private Color lineColor = Color.BLACK;
	private Color textColor = Color.GREEN;
	private Color defaultBG = Color.WHITE;
	private Color[] colors = {defaultBG, Color.GREEN, new Color(150,150,150)};
	
	private int status;
	private int row, col;
	
	public MazeCell(int r, int c){
		super();
		str = new BasicStroke(wallThickness);
		row =r;
		col = c;
		borders = new boolean[4];
		for(int i=0; i<4; i++)
			borders[i] = true;
		this.setBackground(defaultBG);
		status = BLANK;
	}

	public int row(){return row;}
	public int col(){return col;}
	
	public void setStatus(int x){
		status = x;
		//this.setBackground(colors[status]);
		this.paint(this.getGraphics());
	}
	public boolean isBlank(){return status==BLANK;}
	public boolean isVisited(){return status==VISITED;}
	public boolean isDead(){return status==DEAD;}
	public int getStatus(){return status;}
	
		
	public boolean isBlockedUp(){return borders[UP];}
	public boolean isBlockedDown(){return borders[DOWN];}
	public boolean isBlockedLeft(){return borders[LEFT];}
	public boolean isBlockedRight(){return borders[RIGHT];}
	public boolean isBlockedDir(int dir){return borders[dir];}

	
	public void clearWallUp(){borders[UP] = false; repaint();}
	public void clearWallDown(){borders[DOWN] = false; repaint();}
	public void clearWallRight(){borders[RIGHT] = false; repaint();}
	public void clearWallLeft(){borders[LEFT] = false; repaint();}
	public void clearWallDir(int dir){ borders[dir] = false; repaint();}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//display your status		
		Color c = new Color( colors[status].getRed(), colors[status].getGreen(), colors[status].getBlue(), 100);
		if(status==2)
			c = new Color( colors[status].getRed(), colors[status].getGreen(), colors[status].getBlue(), 255);
		
			
		
		g.setColor(c);
		g.fillRect( 0, 0, this.getWidth(), this.getHeight());
		//g.fillOval(this.getWidth()/3,this.getHeight()/3,this.getWidth()/3,this.getHeight()/3 );
				
		g.setColor(lineColor);
		((Graphics2D)g).setStroke(str);
		if(borders[UP])
			g.drawLine(0,0,this.getWidth(),0);
		if(borders[RIGHT])
			g.drawLine(this.getWidth()-padding,0,this.getWidth()-padding,this.getHeight());
		if(borders[DOWN])
			g.drawLine(0,this.getHeight()-padding,this.getWidth(),this.getHeight()-padding);
		if(borders[LEFT])
			g.drawLine(0,0,0,this.getHeight());
		
		
		//label the row & col of this cell
		g.setColor(textColor);
				
	}

	public String toString(){
		return "("+row+", "+col+")";
	}

}

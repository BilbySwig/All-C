//package phase2Kids;
import java.awt.Color;
import java.awt.Graphics;


public class Food {
	private int xPos, yPos;
	private int size=5;
	private boolean powerUp;
	private final double POWER_CHANCE=0.05;
	
	public Food(int x, int y){
		xPos = x;
		yPos = y;
		if(Math.random()<POWER_CHANCE)
			powerUp=true;
		else
			powerUp=false;
	}
	
	public void draw(Graphics g){
		if(!powerUp){
			g.setColor(Color.WHITE);
			g.fillOval(xPos-size/2, yPos-size/2, size, size);
		}
		else{
			g.setColor(Color.YELLOW);
			g.fillOval(xPos-size/2, yPos-size/2, size, size);			
		}
	}
	
	public int getXPos(){return xPos;}
	public int getYPos(){return yPos;}
	public int getSize(){return size;}
	public boolean isPowerUp(){return powerUp;}
}

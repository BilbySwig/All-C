
import java.awt.*;

public abstract class Shape implements Movable{
	private int xPos, yPos;
	private Color hue;
	
	public Shape( int x, int y, Color h){
		xPos = x; 
		yPos = y;
		hue = h;
	}
	
	public int getX(){return xPos;}
	public int getY(){return yPos;}
	public Color getHue(){return hue;}
	
	public void setX(int newX){xPos = newX;}
	public void setY(int newY){yPos = newY;}	
	public void setHue( Color newHue ){hue = newHue;}
	
	public String toString(){ return "Shape @ ("+xPos+", "+yPos+")";}
	
	public abstract double area();
	public abstract double perimeter();
	

}

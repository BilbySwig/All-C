
import java.awt.*;


public class Rectangle extends Shape{
	protected int width, height;
	
	public Rectangle(){
		super(0,0,Color.GREEN);
		width=20;
		height=30;
	}
	
	public Rectangle(int x, int y, int w, int h){
		super(x, y, Color.GREEN);
		width=w;
		height=h;
	}
	
	public int getWidth(){return width;	}
	public int getHeight(){return height;}
	public void setWidth(int w){width =w;}
	public void setHeight(int h){height =h;}
	
	public String toString(){
		return "Rectangular "+super.toString()+" with dimensions "+width+" x "+height;
	}
	public double area(){return width*height;}
	public double perimeter(){return 2*width+2*height;}
	
	public void draw(Graphics g){
		g.setColor(getHue());
		g.fillRect(getX()-width/2, getY()-height/2, width, height);
	}
	
	/* (non-Javadoc)
	 * @see Movable#move()
	 */
	public void move(){setX(getX()+5);}
	
	public void onHitWall(){setX(1);}
	
	
	
	
	
	
	
	
	
	
	
	
}	
	
	
	
	
	
	
	
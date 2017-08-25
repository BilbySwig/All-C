
import java.awt.Color;
import java.awt.Graphics;


public class Circle extends Shape{
	protected int radius;
	protected int speed=5;
	
	public Circle(int x, int y, int r){
		super(x,y,Color.RED);
		radius = r;
	
	}

	public int getRadius(){return radius;}
	public void setRadius(int r){radius =r;}
	
	public String toString(){
		return "Circular "+super.toString()+" with radius "+radius;
	}	
	public double area(){return Math.PI*radius*radius;}
	public double perimeter(){return 2*Math.PI*radius;}
	
	public void draw(Graphics g){
		g.setColor(getHue());
		g.fillOval(getX()-radius, getY()-radius, 2*radius, 2*radius);
	}
	
	public void move(){
		setX(getX()+speed);
	}
	
	public void onHitWall(){
		speed*=-1;
	}
	
	
}
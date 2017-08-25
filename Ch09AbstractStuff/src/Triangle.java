//package Complete;
import java.awt.Color;
import java.awt.Graphics;


public class Triangle extends Shape{
	protected int height;
	private static boolean horiz;
	
	public Triangle(int x, int y, int h, Color c){
		super(x,y,c);
		height = h;
		horiz = true;
	}
	
	public Triangle( int x, int y){
		super(x,y,Color.BLUE);
		height = 20;
	}
	
	public static void changeBehavior(){
		horiz = !horiz;
	}
	
	public void draw(Graphics g) {
		int[] xs={ (int)(getX()-height*Math.cos(Math.PI/6)), getX(), (int)(getX()+height*Math.cos(Math.PI/6))};
		int[] ys={getY()+height, getY(), getY()+height};
		g.setColor( getHue() );
		g.fillPolygon(xs, ys, xs.length);
		
	}

	public void move(){
		if( horiz )
			setX( getX() + 5 );
		else
			setY( getY() + 5 );
	}

	public void onHitWall() {
		if( horiz )
			setX(1);
		else
			setY(1);
	}

	public double area() {
		
		return 0;
	}



	
	public double perimeter() {
		
		return 0;
	}
	

}

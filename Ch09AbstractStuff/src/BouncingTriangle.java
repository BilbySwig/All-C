//package Complete;

import java.awt.*;

public class BouncingTriangle extends Triangle{
	private int direction;	
	
	public BouncingTriangle(int x, int y, int h, Color c){
		super(x,y, h, c);
		direction = 1;
	}
	
	public void move(){
		setX( getX() + 5*direction );
	}

	public void onHitWall() {
		direction*=-1;
		height-=3;
	}

}

import java.awt.*;
public class SpeedingCircle extends Circle{
	public SpeedingCircle(int x, int y, int r){
		super(x,y,r);
		super.setHue(Color.BLUE);
	}
	
	public void onHitWall(){
		super.onHitWall();
		if(speed<=0)
			speed-=2;
		else
			speed+=2;
	}
	
	
	
	
}

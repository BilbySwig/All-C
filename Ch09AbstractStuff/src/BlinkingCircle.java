import java.awt.*;
public class BlinkingCircle extends Circle{
	public BlinkingCircle(int x, int y, int r){
		super(x,y,r);
		super.setHue(Color.WHITE);
	}
	
	public void draw(Graphics g){
		double randy= Math.random();
		if(randy<.91)
			super.draw(g);
	}
}

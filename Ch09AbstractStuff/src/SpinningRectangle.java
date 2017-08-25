import java.awt.*;
public class SpinningRectangle extends Rectangle{
	public SpinningRectangle(){
		super();
		super.setHue(Color.MAGENTA);
	}
	
	public SpinningRectangle(int x, int y, int w, int h){
		super(x,y,w,h);
		super.setHue(Color.MAGENTA);
	}
	
	public void onHitWall(){
		super.onHitWall();
		int tmp= width;
		width=height;
		height=tmp;
	}
}

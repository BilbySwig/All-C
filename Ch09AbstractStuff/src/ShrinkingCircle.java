
public class ShrinkingCircle extends Circle {
	public ShrinkingCircle(int x, int y, int r){
		super(x,y,r);
	}

	public void onHitWall(){
		super.onHitWall();
		radius-=2;
	}
}

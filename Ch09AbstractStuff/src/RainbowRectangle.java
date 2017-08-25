import java.awt.*;
public class RainbowRectangle extends Rectangle{
	public RainbowRectangle(){
		super();
	}
	
	public RainbowRectangle(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	
	public void draw(Graphics g){
		int rand1=(int)(Math.random()*256), rand2=(int)(Math.random()*256), rand3=(int)(Math.random()*256); 
		super.setHue(new Color(rand1, rand2, rand3));
		super.draw(g);
	}
}

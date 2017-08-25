//package ForKidsPart2;
import java.awt.*;

public interface Movable {
	public int getX();
	public int getY();
	public void move();
	public void draw(Graphics g);
	public void onHitWall();
}


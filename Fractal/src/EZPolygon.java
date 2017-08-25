import java.awt.*;
import java.awt.geom.GeneralPath;
public class EZPolygon{
	
	private GeneralPath path;
	private Coordinate[] vertices;
	
	public EZPolygon(Coordinate[] pts){
		int num = pts.length;
		int[] xs = new int[num];
		int[] ys = new int[num];
		
		vertices = pts;
		
		path = new GeneralPath();
		path.moveTo(pts[0].getX(), pts[0].getY());		
		
		for(int i=0; i<num; i++){		
			path.lineTo(pts[i].getX(), pts[i].getY());
		}		
		path.closePath();
				
	}
	public void draw(Graphics2D g, Color fillC, Color lineC, int thick, boolean showLines, boolean showPts){
		g.setColor(fillC);		
		g.fill(path);
		if(showLines){
			for(int i=0; i<vertices.length; i++)
				vertices[i].lineTo(vertices[(i+1)%vertices.length], g, lineC, thick, showPts);
		}
	}
	
	public void draw( Graphics2D g, Color c){
		draw(g, c, c, 1, false, false);
	}
	
	public void draw( Graphics2D g, Color fillC, Color lineC, boolean showLines){
		draw(g, fillC, lineC, 1, true, false);
	}
}

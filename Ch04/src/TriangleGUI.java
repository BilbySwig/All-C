//package TerribleTrianglesForKids;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TriangleGUI extends JPanel implements MouseMotionListener, MouseListener{
	protected double xA, yA, xB, yB, xC, yC;
	
	private Color gridColor, bgColor, lineColor, fontColor;
	public static final int WIDTH=500, HEIGHT = 500;
	private int dx, dy, minX, maxX, minY, maxY;	
	protected JTextArea info;
	private int selected = 0;
	
	public TriangleGUI(){
		this(0,0,0,5,5,0);
	}
	
	public TriangleGUI(double x1, double y1, double x2, double y2, double x3, double y3){
		super();
		gridColor = Color.blue;
		bgColor = Color.LIGHT_GRAY;
		lineColor = Color.RED;
		fontColor = Color.BLACK;
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		minX = -10;//Math.min( Math.min(x1, Math.min(x2,x3))-2, -2);
		maxX = 10;//Math.max( Math.max(x1, Math.max(x2,x3))+2, 2);
		minY = -10;//Math.min( Math.min(y1, Math.min(y2,y3))-2, -2);
		maxY = 10;//Math.max( Math.max(y1, Math.max(y2,y3))+2, 2);
		dx = WIDTH/(maxX-minX);
		dy = HEIGHT/(maxY-minY);
		this.xA = x1;
		this.yA = y1; 
		this.xB = x2;
		this.yB = y2;
		this.xC = x3;
		this.yC = y3;
		JFrame fr = new JFrame();
		fr.add(this, BorderLayout.CENTER);
		
		info = new JTextArea();
		info.setEditable(false);
		//info.setRows(10);
		//info.setColumns(40);
		info.setPreferredSize(new Dimension(440, getHeight()));
		fr.add( new JScrollPane(info), BorderLayout.EAST);
		mrMain();
		//finish up
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setSize(WIDTH+450,HEIGHT+20);
		fr.setVisible(true);
				
	}
	public static double rounder( double num){
		if(Double.isInfinite(num)) //down round infinity!
			return num;
		return (int)Math.round(num*100)/100.0;
	}
	
	//this one rounds to the nearest QUARTER (easier for dragging on screen)
	public static double rounder2(double num){
		int intPart = (int) num;
		double decimal = 0;
		//original decimals
		double decider = Math.abs( num - intPart);
		if( decider > 0.85)
			decimal = 1.0;
		else if( decider > 0.65)
			decimal = 0.75;
		else if( decider >0.35 )
			decimal = 0.5;
		else if(decider > 0.15)
			decimal = 0.25;
		if(num<0)
			decimal *= -1;
		
		return intPart + decimal;
	}
	
	//kids can use this instead of System.out.println --> prints straight to JTextArea
	public void windowprint(String st){
		info.append(st);
	}
	public void windowprintln(String st){
		info.append(st+"\n");
	}
	public void windowprint(double st){
		info.append(st+"");
	}
	public void windowprintln(double st){
		info.append(st+"\n");
	}
	//let kids override this -- it will act like their main
	public void mrMain(){
		System.out.println("This shouldn't be printing!");
	}
	/**pretty it up**/
	public void setFont(String name, int sz, Color c){
		info.setFont(new Font(name, Font.PLAIN, sz));
		info.setForeground(c);
	}
	public void setFont(String name, int sz){
		info.setFont(new Font(name, Font.PLAIN, sz));		
	}
	public void setFontColor(Color c){
		fontColor = c;
		info.setForeground(c);
	}
	public void setLineColor(Color c){lineColor = c;}
	public void setGridColor( Color c){gridColor = c;}
	public void setBackgroundColor(Color c){bgColor = c;}
	
	public void paint(Graphics g){
		super.paint(g);
		//bg
		this.setBackground(bgColor);
		//gridLines
		g.setColor(gridColor);
		for(int x=0; x<WIDTH; x+=dx)
			g.drawLine(x,0, x,HEIGHT);
		for(int y=0; y<HEIGHT; y+=dy)
			g.drawLine(0, y, WIDTH, y);
		//axes
		g.setColor(Color.BLACK);
		((Graphics2D)g).setStroke( new BasicStroke(3));
		g.drawLine(0,maxY*dy,WIDTH,maxY*dy); //x axis
		g.drawLine(-1*minX*dx, 0, -1*minX*dx, HEIGHT); // y axis
		
		//draw the points
		g.setColor(lineColor);
		g.drawOval( xToPx(xA)-2, yToPx(yA)-2, 5, 5);
		g.drawOval( xToPx(xB)-2, yToPx(yB)-2, 5, 5);
		g.drawOval( xToPx(xC)-2, yToPx(yC)-2, 5, 5);
		
		//draw the lines
		((Graphics2D)g).setStroke( new BasicStroke(2));
		g.drawLine( xToPx(xA), yToPx(yA), xToPx(xB), yToPx(yB));
		g.drawLine( xToPx(xA), yToPx(yA), xToPx(xC), yToPx(yC));
		g.drawLine( xToPx(xC), yToPx(yC), xToPx(xB), yToPx(yB));

		//label the points
		g.setColor( fontColor );//new Color(0, 0, 0));
		g.drawString("A", xToPx(xA), yToPx(yA)-5);
		g.drawString( "("+xA+", "+yA+")", xToPx(xA)-20, yToPx(yA)-25);
		g.drawString("B", xToPx(xB), yToPx(yB)-5);
		g.drawString( "("+xB+", "+yB+")", xToPx(xB)-20, yToPx(yB)-25);
		g.drawString("C", xToPx(xC), yToPx(yC)-5);
		g.drawString( "("+xC+", "+yC+")", xToPx(xC)-20, yToPx(yC)-25);

		
		//fill it
		g.setColor( new Color(lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue(), 100) );
		int[] xs = { xToPx(xA), xToPx(xB), xToPx(xC)};
		int[] ys = { yToPx(yA), yToPx(yB), yToPx(yC)};
		g.fillPolygon(new Polygon(xs,ys,xs.length));
		
		//stuff
		g.setColor( new Color(5,5,5) );
		g.setFont( new Font("Arial", Font.BOLD, 10));
		g.drawString("\u00a9MMXIV", 8, this.getHeight()-8);
		
	}
	public int xToPx(double x){
		return (int)Math.round(-1*minX*dx+x*dx);
	}
	public int yToPx(double y){
		return (int)Math.round(maxY*dy - y*dy);
	}
	public double pxToX( int px){
		//point slope form:  ( 0, minX) to (WIDTH, maxX)
		double m = (maxX-minX)/(WIDTH-0.0);
		// y - y1 = m(x - x1)
		return rounder2(m*(px - 0)+minX);
	} 
	public double pxToY( int px ){
		// point slope form: (0, maxY) to (HEIGHT, minY)
		double m = (double)(minY-maxY)/HEIGHT;
		// y-y1 = m(x - x1)
		return rounder2( m*(px - 0) + maxY );
	}

	/********************Mouse Stuff ******************************/
	//@Override
	public void mouseDragged(MouseEvent me) {
		//if no coordinate is being dragged, just give up
		if(selected == -1)	return;
		//convert the mouse's current pixel location to and (x,y) coordinate
		double xx = pxToX( me.getX());
		double yy = pxToY( me.getY());
		//"move" the appropiate coordinate (whichever is selected)
		if(selected == 1){
			xA = xx;	yA = yy;
		}
		else if (selected == 2){
			xB = xx;	yB = yy;
		}
		else if(selected == 3){
			xC = xx;	yC = yy;
		}
		info.setText("");
		mrMain();
		repaint();
		
	}

	//@Override
	public void mouseMoved(MouseEvent arg0) {	}

	//@Override
	public void mouseClicked(MouseEvent arg0) {	}

	//@Override
	public void mouseEntered(MouseEvent arg0) {	}

	//@Override
	public void mouseExited(MouseEvent arg0) {}

	//@Override
	public void mousePressed(MouseEvent me) {
		int[] xs = { xToPx(xA), xToPx(xB), xToPx(xC)};
		int[] ys = { yToPx(yA), yToPx(yB), yToPx(yC)};
		selected = -1;
		//stalker search to see if which point they are "on" (or close to)
		for(int i=0; i<xs.length; i++)
			if( Math.abs(me.getX() - xs[i])<5 &&  Math.abs(me.getY() - ys[i])<5 ){
				selected = i+1;
				break;
			}
		//System.out.println("selected: "+selected);
	}

	//@Override
	public void mouseReleased(MouseEvent arg0) {
		selected = -1;
		
	}
	
}

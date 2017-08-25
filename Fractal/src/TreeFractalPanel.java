import java.awt.Color;
import java.awt.Graphics2D;


public class TreeFractalPanel extends FractalPanel{
	//INHERITED THIS FROM DAD: don't uncomment these!
	//protected Graphics2D myGraphics;	<<--- Your crayon
	//protected int totalLevels; <<---how many levels you need to draw
	//protected Color bgColor = Color.WHITE;
	Color[] Rainbow={Color.BLUE,Color.GREEN,Color.CYAN};
	
	
	public static void main(String[] args) {
		new FractalFrame( new TreeFractalPanel());
	}
	
	public void getStarted(int numLevels){
		//the image is cleared (set to blank & white)
		super.getStarted(numLevels);
		/********************************************************
		 * 	Place the FIRST call to recursive drawing function here!!
		/********************************************************/
		Coordinate top=new Coordinate(500,500);
		Coordinate bot=new Coordinate(500,700);
		Color thisColor=Rainbow[(int)(Math.random()*Rainbow.length)];
		top.lineTo(bot,myGraphics,thisColor,2,false);
		//call sprouting function
		recur(0,bot,top);
		
	}//end getStarted
	
	/*Preconditions:
	 * 	g is the graphics object for the bufferedImage to which the fractal will be drawn
	 * 
	 *Postconditions: Part of the current level is drawn, and recurvsive calls are executed
	 *      that will draw the next level of the fractal 
	 */
	//private void recursiveDraw(Graphics2D g, Color c, int num, int x, int y, int sz){
	private void recur(int levelsDrawn, Coordinate b, Coordinate t){
		if(levelsDrawn>=totalLevels)
			return;
		pause(30.0/totalLevels, levelsDrawn);
		
		Color thisColor=Rainbow[(int)(Math.random()*Rainbow.length)];
		
		Coordinate copy1=new Coordinate(b);
		Coordinate copy2=new Coordinate(b);
		
		copy1.rotateAndScale(t,3*Math.PI/4,.75);
		copy1.lineTo(t,myGraphics,thisColor,2,false);
		recur(levelsDrawn+1,t,copy1);
		
		copy2.rotateAndScale(t,5*Math.PI/4 , .75);
		copy2.lineTo(t,myGraphics,thisColor,2,false);
		recur(levelsDrawn+1,t,copy2);
	}//end recur function

}

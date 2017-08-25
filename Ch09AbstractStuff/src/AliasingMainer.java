//package Complete;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class AliasingMainer extends VideoGame{
	private Graphics theCrayon;
	private ArrayList<Movable> elements;
	private int frameNum = 0;
	
	public void setupElements() {
		
		elements = new ArrayList();
		//Phase 1
		for( int i=0; i<10; i++)
			elements.add( new Mario( 100 + 20*i, 100 ));
		
		
		for(int x = 0 ; x<VideoGame.BOARDWIDTH; x+=15)
			elements.add( new Triangle( x, 100, 10, Color.CYAN));
		
		for(int y=0; y<VideoGame.BOARDHEIGHT; y+=30)
			elements.add( new Triangle( 10, y));
		
		
		//Uncomment this for phase 2
		for( int i=0; i<10; i++)
			elements.add( new BouncingTriangle( 100+20*i, 100+20*i, 20, Color.ORANGE));
	}

	
	public void gameFrame() {
		theCrayon = super.getCrayon();
		frameNum++;
		
		if(frameNum%10==0)
			Triangle.changeBehavior();
		
		for( Movable m : elements){
			m.move();
			m.draw(theCrayon);
			
			if(m.getX()>500 || m.getX()<0 || m.getY()>500||m.getY()<0)
				m.onHitWall();			
			
		}
		
	}
	
	/**~~~~~~~~~~~~~ignore this stuff down here~~~~~~~~~~~~~~~~~~~~~~**/
	public AliasingMainer(){
		super();
		animate();
	}	
	public static void main(String[] args){	new AliasingMainer();}

}

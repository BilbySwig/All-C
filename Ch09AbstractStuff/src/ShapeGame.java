//package ForKidsPart2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class ShapeGame extends VideoGame{

	private Graphics theCrayon;
	private ArrayList<Movable> elements;
	
	
	public void setupElements(){
		elements = new ArrayList();
		elements.add(new Mario(100,100));
		elements.add(new Mario(200,300));
		elements.add(new Rectangle(10,150,30,30));
		elements.add(new Circle(100,400,40));
		elements.add(new ShrinkingCircle(200,200,50));
		elements.add(new SpeedingCircle(300, 300, 10));
		elements.add(new BlinkingCircle(400, 400, 20));
		elements.add(new SpinningRectangle(100,150,30,20));
		elements.add(new RainbowRectangle(150,200,40,30));
	}
	
	public void gameFrame() {
		theCrayon = super.getCrayon();

		for( Movable m : elements){
			m.move();
			m.draw( theCrayon );
			if(m.getX()>=500 || m.getX()<=0 || m.getY()>=500 || m.getY()<=0)
				m.onHitWall();
		}
		
		showArea();		
	}
	
	
	public void showArea(){
		Graphics theCrayon = super.getCrayon();
		double sum = 0;
		
		//add up all of the areas
		for( Movable m : elements){
			if(m instanceof Shape)
				sum+=((Shape)m).area();
			
			
			
		}	
		
		theCrayon.setColor(Color.WHITE);
		theCrayon.drawString("Sum of Areas: "+sum, 5,15);
	}
	
	/**~~~~~~~~~~~~~ignore this stuff down here~~~~~~~~~~~~~~~~~~~~~~**/
	public ShapeGame(){
		super();
		animate();
	}	
	public static void main(String[] args){	new ShapeGame();}
	
}




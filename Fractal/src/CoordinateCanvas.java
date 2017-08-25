import javax.swing.*;
import java.awt.*;

public class CoordinateCanvas extends JPanel{
	public static final int SIZE = 800;
	
	public CoordinateCanvas(){
		super();	
	}		
	public void paint(Graphics g){	
		super.repaint();
		drawGrid(g);
	/****** PUT YOUR CODE IN HERE :) *********/
			
	/*********Keep your code up there *********/		
	}//end paint
	
	public static void main(String[] args){
		JFrame app = new JFrame("Coordinate Canvas");
		app.setBackground(Color.WHITE);
		app.getContentPane().add(new CoordinateCanvas());
		app.setSize(SIZE, SIZE);
		app.setVisible(true);		
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	private void drawGrid(Graphics g){
		g.setColor(Color.GRAY);
		for(int x=0; x<SIZE; x+=50){
			g.drawLine(x,0,x,SIZE);
			g.drawString(""+x,x,15);
		}
		for(int y=0; y<SIZE; y+=50){
			g.drawLine(0,y,SIZE,y);
			g.drawString(""+y,4,y-5);
		}
	}
}

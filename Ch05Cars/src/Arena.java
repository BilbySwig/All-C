import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Arena extends JPanel{
	public static final int WIDTH=1000, HEIGHT=800;
	
	//declaring 5 cars
	Car fred, george, ron, bill, percy;
	
	public void animate(){
		//Write your code here!
		
		fred=new Car(50, 50, 'B', 90);
		george=new Car(200, 200, 'Y', 0);
		percy=new Car(500, 300, 'G', 180);
		
		/*for(int i=0; i<50; i++)
			fred.gasPedal();
		
		for(int j=0; j<200;j++)
			fred.breakPedal();
		
		while(george.getDirection()<360)
			george.rotate(10);
		
		for(int k=0; k<20;k++)
			george.setSize(george.getSize()+5);*/
		
		
		/*for(int i=0; i<175;i++)
			fred.gasPedal();
		for(int i=0; i<100;i++)
			fred.breakPedal();
		
		while(fred.getDirection()<180)
			fred.rotate(10);
			
		for(int i=0; i<130; i++)
			fred.gasPedal();
		for(int i=0; i<100;i++)
			fred.breakPedal();
		
		while(fred.getDirection()<270)
			fred.rotate(10);
		
		for(int i=0; i<175;i++)
			fred.gasPedal();
		for(int i=0; i<100;i++)
			fred.breakPedal();
		
		while(fred.getDirection()<360)
			fred.rotate(10);
		
		for(int i=0; i<130; i++)
			fred.gasPedal();
		for(int i=0; i<100;i++)
			fred.breakPedal();*/
		
		for(int i=0; i<=720; i++){
			for(int j=0; j<1; j++)
				george.gasPedal();
			george.rotate(1);
		}
		
		// ^^ Keep your code up there ^^		
	}//end animate function -- don't delete this }
	
	/** Don't mess with stuff below this comment **/	
	private Graphics crayon;
	private BufferedImage img;
	public Arena(){
		super();
		JFrame frame = new JFrame();
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		//finishing up
		this.setBackground(Color.BLACK);
		frame.add(this);
		frame.setSize(WIDTH,HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		crayon = img.getGraphics();
		Car.g = crayon;
		Car.world = this;
		animate();
		this.drawAndPause();
	}
	
	public void drawAndPause(){		
		crayon.setColor( Color.BLACK );
		crayon.fillRect(0, 0, WIDTH, HEIGHT);
		crayon.setColor(Color.WHITE);
		//draw VERT gridlines
		for(int x=0; x<WIDTH; x+=100){
			crayon.drawLine(x,0,x,HEIGHT);
			crayon.drawString(""+x, x, 15);
		}
		//draw HORIZ gridlines
		for(int y=0; y<HEIGHT; y+=100){
			crayon.drawLine(0,y,WIDTH,y);
			crayon.drawString(""+y, 0, y-5);
		}
		//draw the cars
		Car[] fleet = {fred, george, ron, bill, percy};
		for( Car c: fleet)
			if(c != null)
				c.draw();
		//now draw it all from the image onto the panel
		this.getGraphics().drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		try{Thread.sleep(5);}catch(Exception ex){ex.printStackTrace();}
	}
	
	
	
	public static void main(String[] args) {
		new Arena();
	}

}

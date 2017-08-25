//package ForKidsPart2;
import java.awt.*;

public class Mario implements Movable {
	
	private Image[] mario;
	private int xPos;
	private int yPos;
	private int counter;
	
	public Mario(int x, int y){
		mario = new Image[3];
		loadImages();
		counter = 0;
		xPos = x;
		yPos = y;
	}
	
	private void loadImages(){
		//load images!!
		try{
			MediaTracker mt = new MediaTracker(new Component(){});
			for(int i=0; i<mario.length; i++){
				mario[i] = Toolkit.getDefaultToolkit().getImage( getClass().getResource("mario"+i+".png"));
				mt.addImage(mario[i],i);
			}
			
			mt.waitForAll();
		}catch(Exception ex){System.out.println("ERROR");}
	}

	
	//Mr. Reed already wrote this function for you because it is complicated.  You're welcome!
	public void draw(Graphics g) {	
		counter = (counter+1)%mario.length;
		g.drawImage(mario[counter], xPos-mario[counter].getWidth(null)/2,yPos-mario[counter].getHeight(null),null);
	}

	public int getX(){return xPos;}
	public int getY(){return yPos;}
	
	public void move(){xPos+=5;}
	
	public void onHitWall(){
		xPos=(int)(Math.random()*500);
		yPos=(int)(Math.random()*500);
	}
}



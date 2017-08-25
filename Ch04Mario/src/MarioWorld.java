
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
public class MarioWorld extends JFrame{

	protected Graphics crayon;
	protected Image bg;
	protected Image[] mario;
	protected Image marioJump;
	protected int counter=0;
	protected Image thePic;
	protected int WIDTH=700, HEIGHT=600;
	protected int DISPLAYX = 120;
	protected int DISPLAYY = HEIGHT-70;	
	protected JPanel canvas;
	protected Image heart; //for mario2
	protected int PEACHX = -100;//no peach here, only for  mario2
	protected int jumpSpeed = 5;
	protected int myX = 0, myY = 0; //where is Mario?
	
	public void animate(){
		while(true)
			drawMario(false);
	}//end animate

	public void jump(){
		//JUMP
		for(int i=0; i<5; i++){
			myY-=jumpSpeed;
			drawMario(true);
			//pause();
		}
		for(int i=0; i<5; i++){
			myY+=jumpSpeed;
			drawMario(true);
			//pause();
		}
	}
	
	//constructor
	public MarioWorld(String bgImgName, int w, int h){
		//Make it show up
		super("Mario World");
		WIDTH = w;
		HEIGHT = h;
		mario = new Image[3];
		canvas = new JPanel();
		canvas.setBackground(Color.WHITE);
		this.add(canvas);
		loadImages(bgImgName);
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		thePic = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		crayon = thePic.getGraphics();//canvas.getGraphics();
		crayon.setColor(Color.RED);
		//animate(); //call this from main!		
	}
	public MarioWorld(String bgImgName){
		this(bgImgName, 700, 600);
	}

	public void drawBG(){
		//draw picture
		crayon.drawImage(bg,0,0,null);
		//draw & label grid lines
		crayon.setColor(Color.GRAY);
		crayon.setFont( new Font("ARIAL",Font.PLAIN, 12));
		for(int x=0; x<WIDTH; x+=50){
			crayon.drawLine(x, 0, x, HEIGHT);
			crayon.drawString(""+x,x,10);
		}
		for(int y=0; y<HEIGHT; y+=50){
			crayon.drawLine(0, y, WIDTH, y);
			crayon.drawString(""+y,5,y);
		}
	}
	//draw mario at (x,y) on the screen
	//public void drawMario(int x, int y){
	public void drawMario(boolean jumping){
		drawBG();
		//draw him so that the middle of his feet are his "x" and "y"
		Image toDraw = mario[counter];
		if(jumping)
			toDraw = marioJump;
		crayon.drawImage(toDraw,myX-mario[counter].getWidth(null)/2,myY-mario[counter].getHeight(null),null);
		//red ball on feet to mark position
		crayon.setColor(Color.RED);
		crayon.fillOval(myX-2,myY-2,4,4);		
		//write his position to the screen
		crayon.setColor(Color.BLUE);
		crayon.setFont( new Font("ARIAL",Font.BOLD, 24));
		crayon.drawString("("+myX+","+myY+")", DISPLAYX, DISPLAYY);//120, HEIGHT-70);
		//animate him
		counter = (counter+1)%mario.length;
		//hearts if I am near PEACH
		if( Math.abs(PEACHX-myX)<60 && counter%2==0)
			crayon.drawImage(heart,myX-+mario[0].getWidth(null)+heart.getWidth(null)/2,myY-mario[0].getHeight(null)-heart.getHeight(null),null);

		canvas.getGraphics().drawImage(thePic, 0, 0, null);

		pause();
	}
	//wait for a few nanoseconds
	public void pause(){		
		try{Thread.sleep(50);}catch(Exception ex){System.out.println("ERROR");}
	}

	private void loadImages(String bgImgNm){
		//load images!!
		try{
			MediaTracker mt = new MediaTracker(new Component(){});
			bg = Toolkit.getDefaultToolkit().getImage( getClass().getResource(bgImgNm));
			marioJump = Toolkit.getDefaultToolkit().getImage( getClass().getResource("mario3.png"));
			heart = Toolkit.getDefaultToolkit().getImage( getClass().getResource("pinkHeart.png"));
			for(int i=0; i<mario.length; i++){
				mario[i] = Toolkit.getDefaultToolkit().getImage( getClass().getResource("mario"+i+".png"));
				mt.addImage(mario[i],i);
			}
			mt.addImage(bg, mario.length);
			mt.addImage(marioJump, mario.length+1);
			mt.addImage(heart, mario.length+1);
			
			mt.waitForAll();
		}catch(Exception ex){System.out.println("ERROR");}
	}
	public static void main(String[] args) {new MarioWorld("background.png").animate();}

}

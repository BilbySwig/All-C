import java.awt.*;
/** The training manual for a Car
 *  DO NOT change this file
 *  All of your code will be written inside Arena 
 **/
 
public class Car {	
	private int direction; //bearing in degrees (0 is North, 90 is EAST, etc)
	private double speed; //velocity
	private int maxSpeed; 
	private double acceleration;
	private int xPos, yPos; //center of this tank	
	private int size; //the length of the car
	private boolean lights;
	private static Image red,green,blue,yellow;
	private Image pic;
	public static Arena world;
	public static Graphics g;

	/** ~~~~~Constructors == birthing functions ~~~~~~~~**/
	/* Preconditions: none
	 * Postconditions: Creates a RED car of size 60 at (50,50) facing north*/
	public Car(){
		this(50,50,'R',0,0.05,5,60,0,false);		
	}
	
	/*Preconditions: (x,y) is the desired starting position
	 * Postconditions: a RED car of size 60 is created at (x,y) facing north*/
	public Car(int x, int y){
		this(x,y,'R',0,0.05,5,60,0,false);
	}
	/*Preconditions: (x,y) is a coordinate, c dictates the color (EITHER 'R','B','Y',or'G' for Red, Blue, Yellow, or Green), 
	 *               and dir is a degree measure <= 360
	 * Postconditions: A new car of size 60 is created at (x,y) with the specified color facing the specified direction*/
	public Car(int x, int y, char c,  int dir){
		this(x,y,c,0,0.05,5,60,dir,false);		
	}
	
	public Car(int x, int y, char c, double s, double a, int mx, int sz, int d, boolean l){
		loadImages();
		xPos = x;
		yPos = y;
		speed = s;
		acceleration = a;
		maxSpeed = mx;
		size = sz;		
		direction = d;	
		lights = l;
		//load the image
		
		if(c=='B')
			pic = blue;
		else if(c=='G')
			pic = green;
		else if(c=='Y')
			pic = yellow;
		else
			pic = red;
		world.drawAndPause();
	}
	

	//*****************Movement Functions ********************
	public void rotate(int degrees){
		direction+=degrees;	
		world.drawAndPause();
	}
	public void gasPedal(){
		if(speed + acceleration < maxSpeed)
			speed += acceleration;
		else
			speed = maxSpeed;
		coast();

	}
	public void coast(){		
		double distance = speed;		
		
		double complement = direction-90;
		double dy = distance*Math.sin( complement*Math.PI/180);
		double dx = distance*Math.cos( complement*Math.PI/180);
		int newX=(int)Math.round(xPos+dx);
		int newY=(int)Math.round(yPos+dy);

		xPos = newX;
		yPos = newY;
		world.drawAndPause();
	}
	public void breakPedal(){
		if(speed - acceleration >0)
			speed -= acceleration;
		else
			speed =0;
		coast();
	}
	
	//***************Mutators == changing things******************
	public void setXPos(int x){	xPos = x;	world.drawAndPause();}
	public void setYPos(int y){	yPos = y;	world.drawAndPause();}
	public void setColor(char c){
		if(c=='B')
			pic = blue;
		else if(c=='G')
			pic = green;
		else if(c=='Y')
			pic = yellow;
		else
			pic = red;
		world.drawAndPause();
	}
	public void setSize(int s){size=s;	world.drawAndPause();}	
	public void setSpeed(double s){speed = s;	world.drawAndPause();}
	public void setAcceleration(double a){acceleration = a;	world.drawAndPause();}
	public void setMaxSpeed(int mx){maxSpeed = mx;	world.drawAndPause();}
	public void setDirection(int d){direction = d;	world.drawAndPause();}	
	public void setLights(boolean b){lights = b;	world.drawAndPause();}

	//****Accessors == asking about things****************
	public int getXPos(){return xPos;}
	public int getYPos(){return yPos;}
	public char getColor(){
		if(pic==red)
			return 'R';
		else if(pic == green)
			return 'G';
		else if(pic == yellow)
			return 'Y';
		else
			return 'B';
	}
	public double getSpeed(){return speed;}
	public double getAcceleration(){return acceleration;}
	public int getMaxSpeed(){return maxSpeed;}
	public int getSize(){return size;}	
	public int getDirection(){return direction;}	
	public boolean getLights(){return lights;}
	
	public String toString(){
		return "Car @ ("+xPos+", "+yPos+"): speed="+speed+", acc="+acceleration+", dir="+direction;
	}
	
	public void draw(){//Graphics g){
		int height = size;
		int width = (int)(size*0.65);
		
		((Graphics2D)g).rotate( direction *Math.PI/180, xPos, yPos);		
		g.drawImage(pic, xPos-width/2, yPos-height/2, width, height, null);
		//g.fillRect(xPos-width/2, yPos-height/2, width, height);
		
		if(lights){
			//headlights?
			g.setColor(new Color(200,200,0,180));
			//left headlight
			g.fillArc(xPos-width/4-height, yPos-height/2-height, 2*height, 2*height, 70,40);
			//right headlight
			g.fillArc(xPos+width/4-height, yPos-height/2-height, 2*height, 2*height, 70,40);
		}
		
		((Graphics2D)g).rotate( -direction *Math.PI/180, xPos, yPos);
		g.setColor(Color.WHITE);
		double speedDisplay = (int)Math.round(speed*100)/100.0;
		g.drawString(""+speedDisplay, xPos, yPos);		
	}
	
	
	private void loadImages(){
		try{
			blue = Toolkit.getDefaultToolkit().getImage( getClass().getResource("Bcar.png") );
			green = Toolkit.getDefaultToolkit().getImage( getClass().getResource("Gcar.png") );
			red = Toolkit.getDefaultToolkit().getImage( getClass().getResource("Rcar.png") );
			yellow = Toolkit.getDefaultToolkit().getImage( getClass().getResource("Ycar.png") );
			MediaTracker mt = new MediaTracker(new Component(){});
			mt.addImage(blue, 1);
			mt.addImage(green, 2);
			mt.addImage(red, 3);
			mt.addImage(yellow, 4);
			mt.waitForAll();
		}
		catch(Exception ex){ex.printStackTrace();}
	}


}

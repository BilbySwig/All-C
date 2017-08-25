import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JButton;



public class Card extends JButton{
	public static final int WIDTH =(int)(200);
	public static final int HEIGHT = (int)(148);
	private boolean faceUp, matched;
	private Image current;
	private static Image back;
	private Image front;
	private static Image[] pics;	
	private boolean bonus;
	private static int numCards;
	private static ArrayList<Integer> picsAvailable;
	
	public static int themeNum=0;
	public static String[] themeNames = {"Pokemon", "My Little Pony"};	
	private static String[] themeFile = {"pokemon", "pony"};
	public static Color[] themeColors = {Color.BLACK, new Color(255	,240, 255)};
	public static Color[] fontColors = {Color.BLUE, new Color(248, 15, 240)};
	public static String[] fontNames = {"Comic Sans MS", "Brush Script MT"};
	public static int[] fontSizes =    {23, 			   30};
	public static String currTheme = "pokemon";
	private static Font font = new Font("Comic Sans MS",Font.PLAIN, 12);
	
	//Preconditions: none
	//Postconditions:  creates a card with a randomly selected image,
	//					*ensure that there will be exactly 2 of each image*
	public Card(){
		super();		
		if(pics == null)
			loadImages();
		//remove one of the numbers: this keeps track of which ones you have already used
		int img = picsAvailable.remove( (int)(Math.random()*picsAvailable.size()));

		front = pics[img]; //(int)(Math.random()*pics.length)];
		current = back;
		faceUp = true;//false;
		matched = false;	
		bonus = false;		
		setBackground( themeColor() );
	}
	
	public static void setTheme(String thNm, int numpics){
		numCards = numpics;
		//make a list of the image numbers
		picsAvailable = new ArrayList();
		for(int i=0; i<numCards; i++){
			picsAvailable.add(i);
			picsAvailable.add(i);//add this # twice so you have a match!
		}
		
		for(int i=0; i<themeNames.length; i++)
			if( thNm.equals(themeNames[i])){
				themeNum = i;
				currTheme = themeFile[i];
				font = new Font(fontNames[themeNum],Font.PLAIN, fontSizes[themeNum]);
				break;
			}
	}
	public static Color fontColor(){return fontColors[themeNum];	}
	public static Color themeColor(){return themeColors[themeNum];}
	public static Font themeFont(){return font;}
	public static String fontName(){return fontNames[themeNum];}
	
	
	public void flip(){
		faceUp = !faceUp;
		this.paint(this.getGraphics());
	}
	
	public Image getImage(){return front;}
	public boolean isFaceUp(){return faceUp;}	
	public void setMatched(boolean b){matched = b;	this.paint(this.getGraphics());}
	
	private void loadImages(){
		MediaTracker mt = new MediaTracker(new Component(){});
		back =	Toolkit.getDefaultToolkit().getImage( getClass().getResource(currTheme+"back.png") );
		mt.addImage(back, MyLittleMemoryGame.NUMPICS);
		pics = new Image[MyLittleMemoryGame.NUMPICS];
		
		for(int i=0; i<pics.length; i++){
			pics[i] = Toolkit.getDefaultToolkit().getImage( getClass().getResource(currTheme+""+i+".png") );
			mt.addImage(pics[i], i);
		}
		try{	mt.waitForAll();	}
		catch(Exception ex){ex.printStackTrace();}
	}
	public void paint(Graphics g){
		super.paint(g);
		if(matched)	return; //no picture
		if(faceUp){
			
			//g.fillRect(0,0,getWidth(), getHeight());			
			g.drawImage( front, 0, 0 , getWidth(), getHeight(),null);
			if(bonus){
				Color highlight = fontColor().darker();
				g.setColor( new Color( highlight.getRed(), highlight.getGreen(), highlight.getBlue(), 60 ) );//new Color(255,255,0,100));
				g.fillRect(0,0,getWidth(),getHeight());
				g.setColor( fontColor().brighter());				g.setFont( new Font("Tahoma", Font.PLAIN, 12) );
				g.drawString("Match this card for a", getWidth()/2-60, 25);
				g.setFont( font );
				
				g.drawString("BONUS!!", 20, getHeight()-30);
			}
		}
		else
			g.drawImage( back, 0, 0 , this.getWidth(), this.getHeight(),null);
	}
	public boolean isBonus(){return bonus;}
	public void setBonus(boolean b){bonus = b;}
	
}

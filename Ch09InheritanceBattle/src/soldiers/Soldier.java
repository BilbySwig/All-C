package soldiers;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import board.GameBoard;
import board.Square;

import utilities.ImageObject;
import utilities.PopUpDisplay;


public abstract class Soldier extends ImageObject{
	private boolean evil; //true = evil; false = good
	private int health;	 //current health
	private int attackPower;  //how hard you hit/how much damage you do	
	private int armor;  //percent of damage that you ignore
	private int maxHealth;	
	private int maxAttackPower;
	
	private GameBoard board;
	private Square mySquare;
	private boolean selected; //is it my turn or not?
	
	private Color healthBarColor;	
	private static final Stroke str = new BasicStroke(20); //use for drawing the box when you are selected
	private PopUpDisplay display;
	private boolean dead = false;
	
	
	/**~~~~~~~~~~~~~abstract functions~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**/
	public abstract void attackAction();
	public abstract void moveAction();	
	/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**/	
	
	public Soldier(int my_health, int my_armor, int my_attackP, boolean evilOrNot){
		super(evilOrNot);
		evil = evilOrNot;
		maxHealth=my_health;
		health = my_health;
		armor = my_armor;
		attackPower = my_attackP;	
		maxAttackPower=2*attackPower;
		if(evil)
			setDirection(GameBoard.EAST);
		else
			setDirection(GameBoard.WEST);
		healthBarColor = new Color(0,255,0);
		display = new PopUpDisplay(this);		
	}
	
/**...................MOVEMENT FUNCTIONS..............................................................................**/	
	/*
	 * Preconditions: none
	 * Postconditions:  If the square in front of me is empty, I will move into it
	 * 					If it is filled by a friend, I turn
	 * 					If it is filled with an enemy I do not move or turn
	 * 					If the square in front of me does not exist, I will turn
	 */
	public boolean standardMove(){
		if(dead)
			return false;
		Square next = getBoard().getSquareInDirection( this.getMySquare(), this.getDirection());
		if(next==null){//square doesn't exist
			turn();
			return false;
		}
		else if( next.isEmpty() ){//no one is there
			this.stepInto(next );
			return true;
		}
		else if( this.isEnemy( next.getSoldier() )){//facing an enemy, don't move, but don't turn
			return false;
		}
		else{//sq is filled w/a friend
			turn();
			return false;
		}
	}
	
	public void stepInto( Square newLoc ){
		if(dead)
			return;
		if(mySquare!=null)
			mySquare.setSoldier(null);//i have vacated the square i was in
		if(newLoc!=null)
			newLoc.setSoldier(this);
	}
	
	//I will turn 45 degrees counter clockwise
	public void turn(){ setDirection( (getDirection()+45)%360); }
	public void turn( int numDegrees){
		setDirection( (getDirection()+numDegrees)%360);
	}
	

/** #################################........COMBAT FUNCTIONS .....................................########################################### **/
	
	public boolean isEnemy( Soldier other ){
		if(other == null)
			return false;
		return this.isEvil() != other.isEvil();
	}
	
	public boolean isFriend( Soldier other){
		if(other==null)
			return false;
		return this.isEvil() == other.isEvil();
	}

	/*
	 * Precondition: 0<=dir<360
	 * Postconditions:  returns the Soldier who is closest to me the specified direction
	 * 					this may be a friend OR a foe
	 * 					null is returned if there is not Soldier to be found in that direction
	 */
	public Soldier findClosestTarget(int dir){		
		int r = getMySquare().getRow();
		int c = getMySquare().getCol();
		int dr = getBoard().rowDirection( dir );
		int dc = getBoard().colDirection( dir );
		
		r+=dr;
		c+=dc;
		while( getBoard().isValid(r,c)  ){
			if(getBoard().getSoldierAt(r, c)!=null)
				return getBoard().getSoldierAt(r, c);
			r+=dr;
			c+=dc;
		}
		return null;
	}
	
	/*
	 * Preconditions: dmg>=0 represents how hard someone has hit you
	 * 				  dudeWhoHitMe isthe person how has injured me
	 * Postconditions:  myhealth is decreased by dmg after adjusting for my armor%
	 * 					Pretty floating dmg numbers are displayed
	 * 					If I am now at low health, my health bar changes to red
	 * 					If I am out of health, I am REMOVED from the game
	 */
	public void takeDamage( int dmg, Soldier dudeWhoHitMe ){
		getMySquare().highlight(false);//false=bad (purple) highlight
		
		//i will ignore armor% of this damage
		int realDamage = (int)Math.round(dmg*(1-armor/100.0));
		health -= realDamage;
		//show pretty floating damage numbers
		this.displayDmg(-1*realDamage);
		//if i am almost dead, my health bar will turn red
		if(this.getHealth() < this.getMaxHealth()*0.25)
			healthBarColor = new Color( 200, 100, 0 );
		//if i'm dead, i remove myself from the board
		if(health<=0)
			die();		
	}
	
	/*
	 * Preconditions:  h>=0 and represents the amount i was healed by
	 * Postconditions:  my health has been increased by h points (but will not exceed my maxhealth)
	 * 					pretty green healing numbers are displayed
	 */
	public void beHealed( int amountOfHealing ){
		getMySquare().highlight(true);//true= good (green) highlight
		int missing = maxHealth-health;
		//can't heal you above your maximum health
		int realHeal = Math.min(amountOfHealing, missing);
		health+=realHeal;
		//health bar may need to turn back to green
		if(this.getHealth() > this.getMaxHealth()*0.25)
			healthBarColor = Color.green;
		this.displayDmg(realHeal);//make pretty floating numbers
	}
	
	/*
	 * Preconditions: none
	 * Postconditions: this Soldier is completed removed from the game
	 */
	public void die(){
		if(dead)
			return;
		
		GameBoard tmp = getBoard();//need this to update counts after I'm dead
		mySquare.setSoldier(null);
		mySquare = null;		
		dead = true;
		board = null;		
		
		tmp.updateCounts();
	}
	

	public void displayDmg(int d){	display.setText(d); }  //shows pretty floating numbers (negative numbers in red, positive in green)
	public void displayText(String s){	display.setText(s); } //shows pretty floating text
	
	public String toString(){return "Soldier @ "+mySquare+": ("+health+", "+armor+", "+attackPower+", "+evil+")";}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~              accessors & mutators                   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public boolean isDead(){return dead;}
	
	public boolean isEvil(){return evil;}
	public void setEvil(boolean e){
		if(e!=evil)//i'm changing
			pic = super.loadImage(super.getFileName(), e);//need a new picture!
		evil = e;		
	}
	
	public int getHealth(){return health;}
	public int getArmor(){return armor;}
	
	
	public void setHealth(int h){
		health = Math.min(maxHealth, h);
		if( health <= 0 )
			this.die();
	}
	public void setArmor(int a){armor = a;}
	
	public int getMaxHealth(){return maxHealth;}
	public void setMaxHealth(int m){maxHealth=m;}
	
	public int getAttackPower(){return attackPower;}
	public void setAttackPower( int ap ){attackPower = Math.min(maxAttackPower, ap);}
	
	public int getMaxAttackPower(){return maxAttackPower;}
	public void setMaxAttackPower( int ap ){maxAttackPower = ap;}
	
	public GameBoard getBoard(){return board;}
	public void setBoard( GameBoard b){board=null;}
	
	public Square getMySquare(){ return mySquare;}	
	public void setMySquare( Square s ){ 
		mySquare = s;
		if(s!=null)
			board = s.getBoard();
		else{
			board = null;
			dead = true;
		}
	}
	
	public Color getHealthBarColor(){ return healthBarColor; }
	public void setHealthBarColor(Color c){ healthBarColor = c; }
	
			
	public boolean isSelected(){return selected;}
	public void setSelected(boolean h){ 
		if(dead)
			return;
		selected=h;
		getMySquare().needsToRepaint=true;
		if(h==true)//wait so we can see that this person is selected/taking his turn
			getBoard().pause( (int)Math.round(GameBoard.FRAME_PER_TURN*(1000.0/GameBoard.FPS)) );
		else//this is the end of my turn -- see if I killed the last enemy
			getBoard().checkWinner();
	}
	

	

	public void paint( Graphics g){	
		if(dead)
			return;
		//health bar
		double width = ((double)health/maxHealth)*(getMySquare().getWidth()-4);
		double height = getMySquare().getHeight()/20;
		g.setColor(Color.BLACK);
		g.drawRect(2, 2, (int)width, (int)height);
		g.setColor( healthBarColor );
		g.fillRect(2, 2, (int)width, (int)height);
		g.setColor(Color.DARK_GRAY);
		g.drawString(""+health, 25,10);
		
		//normal stuff		
		super.draw(g);
	
		//draw the blue box if it's my turn
		if(selected){
			g.setColor(new Color(0,0,255,150));
			((Graphics2D)g).setStroke(str);
			g.drawRect(0, 0, getMySquare().getWidth(), getMySquare().getHeight());
		}
		
		//show any text that you are supposed to show
		display.paint(g);
		
		
	}
	

	


}

package soldiers;

import java.util.ArrayList;

import board.Square;

public class Witch extends Mage{
	private ArrayList<Skeleton> spookyscary= new ArrayList<Skeleton>();
	//constructor
	public Witch(boolean evil){//when constructed, which is the same in every currently known way
		super(evil);
	}
	
	/*Preconditions: none
	 * Postconditions: 75% chance the witch doesn't move. 25% chance the witch moves the same as a Mage
	 */
	public void moveAction(){
		double chancetostay=Math.random();//random chance
		if(chancetostay>.75)
			super.moveAction();//movement just like Mage 25% of time. otherwise, this function does nothing	
	}
	
	/*Preconditions:none
	 * Postconditions: will summon a skeleton to fight for her team if the square in front of her has no Soldier in it. this decreases her health by 1 
	 * if the which has a Soldier in the square in front of her, she attacks like a Mage		
	 */
	public void attackAction(){
		Square summon=getBoard().getSquareInDirection(getMySquare(), getDirection());//square for summoning of skeleton 
		if(summon!=null && summon.isEmpty() && getHealth()>1){//only summons if theres an existent square that is empty
			Skeleton newminion=new Skeleton(isEvil());//creates new minion that is on same team
			spookyscary.add(newminion);//adds new minion to arraylist of minions
			getBoard().addSoldierToGame(newminion, getBoard().getSquareInDirection(getMySquare(), getDirection()));//addz new minion in empty square that was saved for summoning
			setHealth(getHealth()-1);//summoning takes energy, so witch loses health
			}
		else
			super.attackAction();//if the square doesnt exist, is full, or the witch is near death, she will just attack like a mage
	}
	
	/*Preconditions:none
	 * Postconditions:all of the dying witch's minions die when she does
	 */
	public void die(){
		for(Skeleton dead: spookyscary)//all minions die
			dead.die();
		super.die();//witch dies just like a mage would
	}
	
}

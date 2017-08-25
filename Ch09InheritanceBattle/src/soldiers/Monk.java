package soldiers;

import java.util.ArrayList;

import board.Square;

public class Monk extends Warrior{
	
	/**
	 * @param evil
	 */
	public Monk(boolean evil){
		super(evil);//basic constructor, nothing special except the values of the variables
		setHealth(100);
		setMaxHealth(100);
		setArmor(5);
		setAttackPower(4);//setting up stats for monks
	}
	/*Precondiions: none
	 * Postconditions: Moves forward two squares
	 */
	public void moveAction(){//same as moveAction for warrior, but twice
		super.moveAction();
		super.moveAction();
	}
	
	/*Preconditions: none 
	 * Postconditions: does damage to all enemies around the monk
	 */
	public void attackAction(){//special quick area of effect attack
		for(int i=0; i<8;i++){//loop for turning and attacking
			turn();
			super.attackAction();//turns then attacks 9 times
		}
	}
	
	/*Preconditions: dmg is the amount of damage inflicted and badguy is the Soldier dealing the damage
	 *Postconditions: a 75% chance of the monk taking damage normally and a 25% chance of the monk not taking damage and moving to a square adjacent to the attacker
	 */
	public void takeDamage(int dmg, Soldier badguy){
		double dodgechance=Math.random();//chance of dodging
		if(dodgechance<75)//if the chance is below .75 (75%), the monk takes normal damage
			super.takeDamage(dmg, badguy);
		else{//otherwise (25%) monk takes no damage and moves to a square adjacent to the attacker
			ArrayList<Square> spotstogo= badguy.getBoard().getEmptySquaresAround(badguy.getMySquare(),1);
			this.stepInto(spotstogo.get((int)(Math.random()*spotstogo.size())));
			}	
		}
	}
	













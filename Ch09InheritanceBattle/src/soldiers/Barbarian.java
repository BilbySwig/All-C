package soldiers;

import java.awt.Color;


public class Barbarian extends Warrior{
	private boolean angry;
	
	public Barbarian(boolean evil){
		super(evil);
		setArmor(getArmor()/2);
		setBlockChance(getBlockChance()/2);
		angry=false;
	}

	public void moveAction(){
		super.moveAction();
		if(angry)
			super.moveAction();
	}
	
	/**
	 * 
	 */
	public void takeDamage(int dmg, Soldier guy){
		if(getHealth()<getMaxHealth()/2){
			angry=true;
			setHealthBarColor(Color.ORANGE);
			setAttackPower(getMaxAttackPower());
			dmg/=2;
		}
		super.takeDamage(dmg, guy);
		setDirection(180+guy.getDirection());
	}
	
	public void attackAction(){
		turn(45);
		super.attackAction();
		turn(-45);
		super.attackAction();
		turn(-45);
		super.attackAction();
		turn(45);
	}
	
	
	
	
	
	
	
	
}

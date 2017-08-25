package soldiers;

public class Mage extends Soldier{
	public Mage(boolean evil){
		super(50,0,15,evil);
	}
	
	public void attackAction(){
		Soldier s=getBoard().getClosestSoldierInDir(getMySquare(), getDirection());
		if(s!=null)
			if(s.isEnemy(this))
				s.takeDamage(this.getAttackPower(), this);
	}
	
	public void moveAction(){
		super.standardMove();
	}
	
	
}

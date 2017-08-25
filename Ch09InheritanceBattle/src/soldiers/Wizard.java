package soldiers;

import java.util.ArrayList;

import board.Square;

public class Wizard extends Mage{
	public Wizard(boolean evil){
		super(evil);
	}

	public void moveAction(){
		double chance=Math.random();
		if(chance<.75)
			super.moveAction();
		else{
			ArrayList<Square> choices=getBoard().getAllEmptySquares();
			Square newsquare=(choices.get(((int)(Math.random()*choices.size()))));
			stepInto(newsquare);
		}
	}
	
	public void attackAction(){
		double chance=Math.random();
		ArrayList<Soldier> enemies=getBoard().getAllEnemies(this);
		Soldier s= enemies.get((int)(Math.random()*enemies.size()));
		if(chance<.75)
			super.attackAction();
		else{
			super.attackAction();
			Square newfortoad= s.getMySquare();
			Toad toady=new Toad(s.isEvil());
			s.die();
			toady.stepInto(newfortoad);
		}
			
	}
	


}

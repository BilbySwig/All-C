package soldiers;
import java.util.ArrayList;

import board.Square;


public class Cleric extends Soldier{

	public Cleric(boolean ev){
		super(60,10,10,ev);
	}

	
	
	public void attackAction() {
		if(isDead())
			return;
		ArrayList<Square> locs = getBoard().getAllSquaresAround( getMySquare(), 1);
		getBoard().highlightSquares(locs, true);//true means turn them green (false is purple);
		
		ArrayList<Soldier> neighbors = getBoard().getSoldiersAround( getMySquare(), 1 );
		
		for( Soldier otherDude : neighbors )
			if( this.isFriend(otherDude) ) //don't heal enemies!
				otherDude.beHealed( getAttackPower() );
		
		//extra challenge:  heal the teammate with the lowest health percentage
	
		
	}

	public void moveAction() {
		if(isDead())
			return;
		ArrayList<Soldier> peeps = getBoard().getSoldiersAround(getMySquare(),1);
		boolean wantToMove=true;
		//if there is a friend to heal near me, then I won't move
		for(Soldier s : peeps)
			if(this.isFriend(s))
				wantToMove=false;
		if(wantToMove){//no one to heal?  then move
			super.standardMove();
		}
		
	}
}

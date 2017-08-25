package soldiers;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import board.Square;


public class Archer extends Soldier{
	private boolean wantToMove=false;
		
	public Archer( boolean ev ){
		super(75, 5, 10, ev);
		wantToMove=true;
	}

		
	@Override
	public void attackAction() {
		if(isDead())
			return;
		
		
		Soldier target = getBoard().getClosestSoldierInDir(this.getMySquare(), this.getDirection());//findClosestTarget( getDirection() );
		if(target==null)//no one there
			wantToMove=true;
		else if( this.isFriend( target ) ) //friendly target
			wantToMove = true;
		else if( getMySquare().distance( target.getMySquare()) < 5 ){ //must be an enemy...make sure he is in range
			target.takeDamage( getAttackPower(), this );
			wantToMove=false;//I want to stand here and shoot forever			
		}
		else //target was too far away
			wantToMove = true;
				
	}

	@Override
	public void moveAction() {
		if(isDead())
			return;
		if(wantToMove){
			standardMove();
			standardMove();
		}	
	}
	
	public void takeDamage(int dmg, Soldier guy){
		super.takeDamage(dmg, guy);
		this.turn();
		moveAction();
	}
	
	public void turn(){
		super.turn();
		super.turn();
	}
	
	
	
}


public class Pawn extends ChessPiece{
	private int dir;// variable to keep track of direction, since pawn is only piece that can move in one direction. -1 is down, 1 is up
	private int moves;//variable tp keep track of moves so first move can be 2
	private Square firstSqr;//variable to keep track of whether pawn is still at startingposition, to keep it from counting moves when it doesnt even make one
	
	public Pawn(String im, boolean tm, Square lc) {
		super(im, tm, lc);//normal constructor, except for variable for direction and amount of moves
		firstSqr=lc;//variable to keep track of whether pawn is on its starting point
		moves=0;//variable for amount of moves, used so if its first move, pawn can go 2
		if(tm)
			dir=-1;//easy assignment of dir, simplifies birth of pawns
		else
			dir=1;
	}

	
	public boolean isMoveLegal(Square dest) {
		if(getSquare()==firstSqr)//if pawn is still on its frist square, it hasn't ACTUALLY moved
			moves=0;
		int chRow=this.getSquare().getRow()-dest.getRow();//change in row and column
		int chCol=this.getSquare().getCol()-dest.getCol();
		if(dest.getChessPiece()!=null)//chess piece where pawn is going to
			if(chRow==dir && Math.abs(chCol)==1){
				moves+=1;
				return true;//if destination has a piece, pawn can only go diagonal
			}
			else
				return false;
		
			if(moves==0){//if destination doesnt have a piece, pawn moves 1 or 2 in a straight line
				moves+=1;//if it is first move, pawn CAN move 2
				return((chRow==dir*2 && chCol==0) || (chRow==dir && chCol==0));	
			}
			else{
				moves+=1;//if not first move, pawn can only move 1	
				return (chRow==dir && chCol==0);
				}
		
	
	}//end isMoveLegal
	
}


public class Knight extends ChessPiece{
	public Knight(String im,boolean t, Square s){
		super(im,t,s);//no difference in constructor
	}
	
	public boolean isMoveLegal(Square dest){
		int chRow=this.getSquare().getRow()-dest.getRow();//change in row and column
		int chCol=this.getSquare().getCol()-dest.getCol();
		return (Math.abs(chRow)==2 && Math.abs(chCol)==1 || Math.abs(chRow)==1 && Math.abs(chCol)==2);
			//^^^^knight moves 2 rows and 1 col or 1 row and 2 cols
	}
}

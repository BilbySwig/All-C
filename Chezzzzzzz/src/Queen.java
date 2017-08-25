
public class Queen extends ChessPiece{
	public Queen(String im,boolean t, Square s){
		super(im,t,s);//no difference in constructor
	}
	
	public boolean isMoveLegal(Square dest){
		int chRow=this.getSquare().getRow()-dest.getRow();//change in row and column
		int chCol=this.getSquare().getCol()-dest.getCol();
		return ((Math.abs(chRow)==Math.abs(chCol)) || ((chRow==0 && chCol!=0) || (chRow!=0 && chCol==0)));
			//^^^^queen can move like a bishop or a rook
	}
}

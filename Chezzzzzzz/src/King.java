
public class King extends ChessPiece{
	
	public King(String im,boolean t, Square s){
		super(im,t,s);//normal constructor
	}
	
	public boolean isMoveLegal(Square dest){
		int chRow=this.getSquare().getRow()-dest.getRow();//change in row and column
		int chCol=this.getSquare().getCol()-dest.getCol();
		return ((Math.abs(chRow)<=1) &&( Math.abs(chCol)<=1));//king moves one in any direction, including diagonal
	}
	
}

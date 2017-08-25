
public class Bishop extends ChessPiece{
	public Bishop(String im,boolean t, Square s){
		super(im,t,s);//no difference in constructor
	}
	
	public boolean isMoveLegal(Square dest){
		int chRow=this.getSquare().getRow()-dest.getRow();//change in row and column
		int chCol=this.getSquare().getCol()-dest.getCol();
		return (Math.abs(chRow)==Math.abs(chCol));//bishops always move same amount of rows as columns
	}
}


public class Rook extends ChessPiece {
	
	
	public Rook(String im, boolean tm, Square lc) {
		super(im, tm, lc);
		
	}
	public boolean isMoveLegal(Square dest) {
		int chRow=this.getSquare().getRow()-dest.getRow();//changs in row and col
		int chCol=this.getSquare().getCol()-dest.getCol();
		return ((chRow==0 && chCol!=0) || (chRow!=0 && chCol==0));
				//^^^^rook moves in straight lines in any direction
	}

	
}

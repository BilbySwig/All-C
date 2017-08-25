import java.awt.*;
import javax.swing.*;

public class GameBoard extends JFrame{
	private static final int ROWS = 8, COLS = 8;
	//you'll need a 2d array
	private Square[][] squares;
	//you'll need variables to keep track of the 1st and 2nd squares that were clicked
	private Square first;
	private int clickCount=0;
	
	//variable to keep track of turns
	private boolean blackturn;
	
	//variables to keep track of kings for check
	private King wking,bking;
	
	public GameBoard(){
		super("CHESS");
		
		this.setLayout(new GridLayout(ROWS,COLS));
		//be sure to instantiate the array
		squares=new Square[8][8];

		//now you'll need to birth each element of the array AND add it to the Frame 
		for(int r=0;r<=7;r++)
			for(int c=0;c<=7;c++)
				if((r+c)%2==0){//if r+c is even, square should be white
					squares[r][c]=new Square(r,c,this,false);
					this.add(squares[r][c]);
				}
				else{//if r+c is odd, the square is black
					squares[r][c]=new Square(r,c,this,true);
					this.add(squares[r][c]);
				}
		squares[0][0].setChessPiece(new Rook("blackrook.png", true, squares[0][0]));
		squares[0][1].setChessPiece(new Knight("blackknight.png", true, squares[0][1]));
		squares[0][2].setChessPiece(new Bishop("blackbishop.png", true, squares[0][2]));
		squares[0][3].setChessPiece(new Queen("blackqueen.png", true, squares[0][3]));
		squares[0][4].setChessPiece(new King("blackking.png", true, squares[0][4]));
		bking=(King)(squares[0][4].getChessPiece());
		squares[0][5].setChessPiece(new Bishop("blackbishop.png", true, squares[0][5]));
		squares[0][6].setChessPiece(new Knight("blackknight.png", true, squares[0][6]));
		squares[0][7].setChessPiece(new Rook("blackrook.png", true, squares[0][7]));
		for(int i=0;i<squares[0].length;i++)
			squares[1][i].setChessPiece(new Pawn("blackpawn.png", true, squares[1][i]));
		//^^^^^^^black pieces population
		
		squares[7][0].setChessPiece(new Rook("whiterook.png", false, squares[7][0]));
		squares[7][1].setChessPiece(new Knight("whiteknight.png", false, squares[7][1]));
		squares[7][2].setChessPiece(new Bishop("whitebishop.png", false, squares[7][2]));
		squares[7][3].setChessPiece(new Queen("whitequeen.png", false, squares[7][3]));
		squares[7][4].setChessPiece(new King("whiteking.png", false, squares[7][4]));
		wking=(King)(squares[7][4].getChessPiece());
		squares[7][5].setChessPiece(new Bishop("whitebishop.png", false, squares[7][5]));
		squares[7][6].setChessPiece(new Knight("whiteknight.png", false, squares[7][6]));
		squares[7][7].setChessPiece(new Rook("whiterook.png", false, squares[7][7]));
		for(int i=0;i<squares[0].length;i++)
			squares[6][i].setChessPiece(new Pawn("whitepawn.png", false, squares[6][i]));
			//^^^^white pieces population
		//some finishing touches
		this.setSize(750,750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		blackturn=false;
	}
	
	//one of the squares will call this function to tell the board it was clicked
	public void clicked(Square whoGotClicked){
		if(clickCount==0){//first click
			if(whoGotClicked.getChessPiece()!=null){//doesnt do first click if piece isnt clicked on
				if(whoGotClicked.getChessPiece().isBlack()==blackturn){
					first=whoGotClicked;//so i can talk about first later
					clickCount=1;//so that the second click counts as the second click
			
					for(int r=0;r<squares.length;r++)
						for(int c=0;c<squares[0].length;c++)//highlights all squares it is legal to move to
							if(first.getChessPiece().isMoveLegal(squares[r][c])&& isBlocked(first, squares[r][c])==false)
								squares[r][c].setHighlighted(true);
					
				}
				else{
					JOptionPane.showMessageDialog(null, "That's the wrong team!!!!");//popup if they try to use wrong team
				}//end if for turns
			}//end if for checking to make sure there is a piece
		}//end if for checking to see which click it is
		
		//this is a thing to switch which piece is being used if the player decides they dont want to use the one they initially clicked on
		else if((whoGotClicked.getChessPiece()!=null) && (first.getChessPiece().isBlack()==whoGotClicked.getChessPiece().isBlack())){
			for(int r=0;r<squares.length;r++)
				for(int c=0;c<squares[0].length;c++)//De-highlights all squares 
					squares[r][c].setHighlighted(false);
			first=whoGotClicked;//changes it back to what the first click would be if it had been on the second one
			for(int r=0;r<squares.length;r++)
				for(int c=0;c<squares[0].length;c++)//highlights all squares it is legal to move to
					if(first.getChessPiece().isMoveLegal(squares[r][c])&& isBlocked(first, squares[r][c])==false)
						squares[r][c].setHighlighted(true);
		}
			
		
		//second click
		else if(first.getChessPiece().isMoveLegal(whoGotClicked)){//only move if that piece can do the move
				if(isBlocked(first, whoGotClicked)!=true){
					ChessPiece storage=whoGotClicked.getChessPiece();//thing to reverse move if it keeps them in check
					
					whoGotClicked.setChessPiece(first.getChessPiece());
					first.setChessPiece(null);//previous and new square switch chesspieces, chesspiece switches squares
					whoGotClicked.getChessPiece().setSquare(whoGotClicked);
					clickCount=0;//resets clicking, so next click will be a first click
					
					for(int r=0;r<squares.length;r++)
						for(int c=0;c<squares[0].length;c++)//De-highlights all squares 
							squares[r][c].setHighlighted(false);
					
					//undoes move if it would make the king be in check
					if((blackturn && inCheck(bking)) || (!blackturn && inCheck(wking))){
						JOptionPane.showMessageDialog(null, "You're in check. Make a move that would get you out of check.");//popup if they try to use wrong team
						first.setChessPiece(whoGotClicked.getChessPiece());
						whoGotClicked.setChessPiece(storage);//squares get their pieces back
						first.getChessPiece().setSquare(first);//chesspiece gets its square back
						blackturn=!blackturn;//change turns back so a turn isnt skipped
						
					}//end what happens if black is in check
					blackturn=!blackturn;//switch turns	
				}//end if for isblocked
		}//end else if
	}//end clicked	
	public boolean isBlocked(Square orig, Square dest){
		
		if(dest.getChessPiece()!=null && (dest.getChessPiece().isBlack()==orig.getChessPiece().isBlack()))
			return true;//its blocked if theres a friendly piece
			if(!(orig.getChessPiece() instanceof Knight)){	//if you're talking to a knight, completely bypass this function
				
				//calculations for change in row and col, makes dc and dr either 1, 0, or -1
				int cr=orig.getRow(),cc=orig.getCol();
				int dr=dest.getRow()-cr, dc=dest.getCol()-cc;
				if(dr!=0)
					dr=dr/Math.abs(dr);
				if(dc!=0)
					dc=dc/Math.abs(dc);
				
				cc+=dc; cr+=dr;//add on change in row and col so it doesnt get blocked by itself
				while(cr!=dest.getRow() || cc!=dest.getCol()){
					if(squares[cr][cc].getChessPiece()!= null){//goes through squares in between dest and orig qand tests them for being blocked
						return true;
					}
					else{
						cr+=dr; cc+=dc;
					}	
				}//end while loop
		return false;//if its not blocked, returns false
			}//end knight if statement
			else
				return false;
	}//end isBlocked
	//lame main
	
	public boolean inCheck(King king){
		boolean check=false;//assume its not in check
		
		for(int r=0; r<ROWS;r++)
			for(int c=0; c<COLS;c++)//go through all squares(easiest way to talk to all chesspieces)
				if((squares[r][c].getChessPiece()!=null) && squares[r][c].getChessPiece().isBlack()!=king.isBlack() && squares[r][c].getChessPiece().isMoveLegal(king.getSquare()) && !isBlocked(squares[r][c],king.getSquare())){
					check=true;//if a chesspiece on the other team has a legal, unblocked move in which they could kill the king, this king is in check
					break;//stops the loop to speed up things(only one move is needed to be in check)
				}			
		return check;//returns whether the specified king is in check
	}
	public static void main(String[] args) {
		new GameBoard();
	}

}

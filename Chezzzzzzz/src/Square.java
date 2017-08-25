import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Square extends JPanel implements MouseListener{
	//~~~~ Private Member Variables ~~~~
	private static GameBoard board;
	private ChessPiece piece;
	private int row, col;
	private boolean highlighted;
	//~~~~ Constructors ~~~~
	public Square(int r, int c, GameBoard gb, boolean b){
		highlighted=false;
		board = gb;
		row = r;
		col = c;
		if(b)
			this.setBackground(Color.BLACK);
		else
			this.setBackground(Color.WHITE);
		this.addMouseListener(this);
	}
	public Square(int r, int c, GameBoard gb){
		highlighted=false;
		board = gb;
		row = r;
		col = c;
		this.setBackground(Color.BLACK);		
		this.addMouseListener(this);
	}
	
	public void setHighlighted(boolean h){
		highlighted=h;
		this.repaint();
	}
	
	public ChessPiece getChessPiece(){return piece;}
	//accessor and mutator used for moving
	public void setChessPiece(ChessPiece cp){piece=cp;
		this.repaint();
	}
	
	public int getRow(){return row;}//some accessors
	public int getCol(){return col;}

	//~~~~ some functions to specify the color of this square ~~~~
	public void setColor( int c ){//1 means black, 0 means white
		if(c==1)
			this.setBackground(Color.BLACK);
		else
			this.setBackground(Color.WHITE);
	}

	public void setColor( boolean black ){//true means black, false means white
		if(black)
			this.setBackground(Color.BLACK);
		else
			this.setBackground(Color.WHITE);
	}
	public void setColor( Color c ){
		this.setBackground(c);
	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(piece!=null)
			piece.draw(g);
		
		if(highlighted){//highlighting
			g.setColor(new Color(16,35,114,100));
			g.fillRect(0, 0, this.getWidth(),this.getHeight()); 
		}
	}
	
	//~~~~ MouseListener stuff ~~~~
	public void mousePressed(MouseEvent arg0) {
		//when I am clicked, I will tell the board that I was clicked!
		board.clicked(this);		
	}

	public void mouseClicked(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}
	
	public String toString(){
		return "("+row+","+col+")";
	}

}

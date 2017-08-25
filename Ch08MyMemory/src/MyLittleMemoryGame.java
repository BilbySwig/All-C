import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.util.*;
//Reminder:  on first match --> give a bonus
//    let them see an entire row or column for a second
public class MyLittleMemoryGame extends BasicMemoryTable{
	//private Card[][] cards;
	private JButton flipTable, flipRow, flipCol, findMatch, flipRandom;
	
	private int r1, c1; //the row & col of the FIRST card they selected
	private int r2, c2; //the row & col of the SECOND card they selected
	
	public MyLittleMemoryGame() {
		
		super("My Own Memory Program");		
		r1=-1;
		c1=-1;
		r2=-1;
		c2=-1;
		
		createControls(); //make the buttons
		
		cards = new Card[ROWS][COLS]; //i have instantiated the array for you		
		/** ~~~~~~~~~~~KIDS WRITE~~~~~~~~~~~~~~~~~~~~~~~~***/		
		//1) birth each card in cards 
		for(int i=0;i<cards.length;i++)
			for(int j=0; j<cards[0].length;j++)
				cards[i][j]=new Card();
		//2) pick a card to be a bonus (generate a random row & random column) 		
		cards[(int)(Math.random()*cards.length)][(int)(Math.random()*cards[0].length)].setBonus(true);
		/** ************************************ **/
		this.addToTable();
		this.paintAll(this.getGraphics());
		repaint();
		
	}//end constructor
	
	public void flipTable(){/** This function is called when they press the flip table button! **/
		//3) Go to each window in your 2d array and tell the card there to flip over
		for(int i=0;i<cards.length;i++)
			for(int j=0; j<cards[0].length;j++)
				cards[i][j].flip();
	}
	
	/** kids will write this **/
	public void cardSelected( int row, int col){
		JOptionPane.showMessageDialog(this,"you want to flip row "+row+", col "+col+"!");
		if(r1==-1){//first flip
			//4) set r1 & c1, then flip the correct card
			r1=row;
			c1=col;
			cards[r1][c1].flip();
		}
		else{//second flip!!!  
			//5) set r2 & c2, then flip the correct card
			r2=row;
			c2=col;
			cards[r2][c2].flip();
			
			//IT's A MATCH!!!!!!!!!!!!!! (if their images are the same)
			if(cards[r1][c1].getImage().equals(cards[r2][c2].getImage())){//6) fix this.  True is just a place holder!				
				pause();
				//7) Tell the cards that they are now matched
				cards[r1][c1].setMatched(true);
				cards[r2][c2].setMatched(true);
				if(cards[r1][c2].isBonus() || cards[r2][c2].isBonus()){
					int newr= Integer.parseInt(JOptionPane.showInputDialog(this, "Which row would you like to flip?"));
					this.flipRow(newr);
				}
					
			}
			else{//NOT A MATCH:   
				pause();
				//8) turn the cards back over
				cards[r1][c1].flip();
				cards[r2][c2].flip();
			}
			//reset everyting for next try
			r1=-1;
			c1=-1;
			r2=-1;
			c2=-1;		

		}//end 2nd flip
		
	}//end cardSelected function
	
		
	public void findMatch(){
		if(r1==-1){
			JOptionPane.showMessageDialog(this,"Flip a card first");
			return;
		}
		//8) search through all the cards and find the match for current the face up card
		//      (careful, don't let the actual face up card fool you!)
		//    Now:  flip it up, pause, and flip it back over
		for(int i=0;i<cards.length;i++)
			for(int j=0; j<cards[0].length;j++)
				if(cards[r1][c1]!=cards[i][j] && cards[r1][c1].getImage().equals(cards[i][j].getImage())){
				cards[i][j].flip();
				pause();
				cards[i][j].flip();
				}
		
	}
	
	public void flipRow( int row ){
		//9) flip every card in the specified row, pause, then flip them all back over
		for(int i=0;i<cards[0].length;i++)
			cards[row][i].flip();
		pause();
		for(int i=0;i<cards[0].length;i++)
			cards[row][i].flip();
	}
	
	public void flipCol( int col ){
		//10) flip every card in the specified col, pause, then flip them all back over
		for(int i=0;i<cards.length;i++)
			cards[i][col].flip();
		pause();
		for(int i=0;i<cards.length;i++)
			cards[i][col].flip();
	}
	public void flipRandom(){
		//11) select a card at random, flip it over, pause, flip it back over
		int r=(int)(Math.random()*cards.length);
		int c=(int)(Math.random()*cards[0].length);
		cards[r][c].flip();
		pause();
		cards[r][c].flip();
	}
	
	
	
	//~~~~~~~~~~~~~~~~~~~~~~~Don't mess with the code down here ~~~~~~~~~~~~~~~~~~~~~~~~~
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==flip1)
			getInput();
		if(e.getSource()==flipTable)			
			flipTable();
		if(e.getSource()==flipRow){
			int row = Integer.parseInt(JOptionPane.showInputDialog(this,"Which row?"));
			flipRow(row);			
		}
		if(e.getSource()==flipCol){
			int col = Integer.parseInt(JOptionPane.showInputDialog(this,"Which Column?"));
			flipCol(col);
		}
		if(e.getSource()==findMatch)
			findMatch();
		if(e.getSource() == flipRandom)
			flipRandom();
	
	}
	
	public void addToTable(){
		for(int r=0; r<ROWS; r++)
			for(int c=0; c<COLS; c++)
				if(cards[r][c]!=null)
					table.add(cards[r][c]);
	}
	
	private void createControls(){
		JPanel south = new JPanel( new GridLayout(1,5) );
		flipTable =createButton("Flip Table", south);
		findMatch =createButton("Find Match", south);
		flipRow =createButton("Flip Row", south);
		flipCol =createButton("Flip Column", south);
		flipRandom = createButton("Flip Random", south);
		
		this.add(south, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {new MyLittleMemoryGame();}


	

	


}

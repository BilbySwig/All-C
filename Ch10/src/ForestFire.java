//package ForestFireForKids;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;

public class ForestFire extends JFrame{

	private Tree[][] forest;
	private final int ROWS=10, COLS=10;
	private final double PROB_OF_SPREAD=0.75;
	private final int PAUSE_TIME = 100;//milliseconds
	
	//take a look:  this is how things get up and running
	public void getStarted(){
		JOptionPane.showMessageDialog(this,"Where do you want the fire to start?");
		int r = Integer.parseInt(JOptionPane.showInputDialog(this, "ROW?"));
		int c = Integer.parseInt(JOptionPane.showInputDialog(this, "COL?"));
		spreadFire( r, c );
		finishUp();
	}
	//take a look:   this is what happens after spreadFire is done
	public void finishUp(){		
		for(int r=0; r<ROWS; r++)
			for(int c=0; c<COLS; c++)
				forest[r][c].burnout();
		
		DecimalFormat df = new DecimalFormat("#.##");
		JOptionPane.showMessageDialog(this, df.format(percentBurnt())+"% of the forest has burned!");
	}
	
	//*****STEP 1:  work on spreadFire*****
	public void spreadFire( int sourceRow, int sourceCol){
		
		// Stopping State A:  if sourceRow and/or sourceCol would cause an ArrayIndexOutOfBounds error,
		//                    then you should return!
		//   <hint:  there are 4 different things to check here!>
		//   <hint:  you ARE NOT checking to see if something is null!>
		//   <hint:  if you are seeing ArrayIndexOutOfBounds when you run it, you have messed up here>
		/** write code here **/
		if(sourceRow>9 || sourceCol>9 || sourceRow<0 || sourceCol<0)
			return;
		
		
		
		
		
		
		// Stopping State B:  if this tree is already on fire, there is nothing to do & you should return
		/**write code here **/
		if(forest[sourceRow][sourceCol].isOnFire())
			return;
		

		
		
		
		
		//The only actual work
		System.out.println(sourceRow+", "+sourceCol);
		forest[sourceRow][sourceCol].setFire( true );
		pause();	
		
		
		//RECURSIVE CALLS
		//First Thing:  use recursion to make the fire spread to the tree on your right
		/**write code here**/
		double chance=Math.random();
		if(chance<=PROB_OF_SPREAD){
		this.spreadFire(sourceRow, sourceCol+1);
		
		//NOW STOP AND RUN THE PROGRAM!!!  Make sure the fire is spreading to the right & there is no error
		//   once you know that this works, add more recursive calls that will
		//   make the fire spread to your neighbors in ALL directions!
		/**write code here**/
		
		this.spreadFire(sourceRow+1, sourceCol);
		this.spreadFire(sourceRow+1, sourceCol+1);
		this.spreadFire(sourceRow-1, sourceCol+1);
		this.spreadFire(sourceRow-1, sourceCol);
		this.spreadFire(sourceRow-1, sourceCol-1);
		this.spreadFire(sourceRow, sourceCol-1);
		this.spreadFire(sourceRow+1, sourceCol-1);
		}//end if statment
		//NOW STOP AND RUN THE PROGRAM!!
		//make sure that the fire is spreading correctly
		//make sure that there are no errors!
		/**NOW GO EDIT YOUR CODE SO THAT THERE IS A PROB_OF_SPREAD% chance that the fire spreads**/
		//run the code and play with different values of PROB_OF_SPREAD, ROWS, and COLS!
	}//end spreadFire
	
	//***** STEP 2:  write percentBurnt *****
	public double percentBurnt(){
		//calculate the percentage of the forest that isBurnt!
		//this code is NOT RECURSIVE.  You will use loops.
		/** write code here**/
		double num=0, denom=0;
		for(int i=0;i<forest.length;i++)
			for(int j=0;j<forest[0].length;j++){
				denom++;
				if(forest[i][j].isBurnt())
					num++;
			}
		return (num/denom)*100;
	}
	

	
	/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~You do not need to touch anything below this line~~~~~~~~~~~~~~~~~~~~~ **/
	public ForestFire(){
		super("Forest Fire");
		
		JPanel holder = new JPanel( new GridLayout(ROWS,COLS));
		forest = new Tree[ROWS][COLS];
		for(int r=0; r<ROWS; r++)
			for(int c=0; c<COLS; c++){
				forest[r][c] = new Tree(r,c);
				holder.add(forest[r][c]);
			}
		this.add(holder, BorderLayout.CENTER);		
		
		//finishing touches
		this.setSize(50*ROWS,50*COLS);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		getStarted();
		
	}
	public void pause(){
		try{ Thread.sleep(PAUSE_TIME);}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void main(String[] args){new ForestFire();}

	
	
}

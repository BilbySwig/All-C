//package FORKIDS;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


import javax.swing.*;
public class MazeFrame extends JFrame implements ActionListener{
	private static final int UP = 0, RIGHT = 1, DOWN=2, LEFT = 3;
	private
	static int ROWS=10, COLS=10;
	private int exitRow, begRow;
	
	
	private JPanel controls, maze;
	private JButton solve, reset;
	//*** you will need a 2DArray of MazeCells****
	private MazeCell[][] cells;
	CellStack solving=new CellStack();
	CellStack creation=new CellStack();
	
	/**Constructor**/
	public MazeFrame(){
		super("MAZE");		
		ROWS=Integer.parseInt(JOptionPane.showInputDialog(this,"How many ROWS?"));
		COLS=Integer.parseInt(JOptionPane.showInputDialog(this,"How many COLS?"));
		cells=new MazeCell[ROWS][COLS];
		setUpControlPanel();//make the buttons & put them in the north		
		instantiateCells();//give birth to all the mazeCells & get them onto the screen		
		//carveALameMaze();//this will knock down walls to create a maze
		
		exitRow=0;
		begRow=0;
		carveARandomMaze();
		
		//finishing touches
		this.setSize(ROWS*20,COLS*20);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(ROWS*40,COLS*40);
		this.setVisible(true);
	}//end constructor
	
	
	/* 1111111111111111    PHASE 1 STUFF    1111111111111111111111 */
	private void instantiateCells(){
		maze = new JPanel();
		maze.setBackground(Color.WHITE);
		maze.setLayout(new GridLayout(ROWS, COLS));		
		//construct your 2D Array & instantiate EACH MazeCell
		//  be sure to add each MazeCell to the panel
		//            * call maze.add( ?the cell ? );
		/**~~~~  WRITE CODE HERE ~~~~**/
		for(int i=0;i<ROWS;i++)
			for(int j=0;j<COLS;j++){//fills up the maze with cells
				cells[i][j]=new MazeCell(i,j);
				maze.add(cells[i][j]);
			}
		/**~~~~  *************** ~~~~**/
		//put the maze on the screen
		this.add(maze, BorderLayout.CENTER);
	}
	
	private void carveALameMaze(){//"hard code" a maze
		for(int i=0;i<5;i++){
			cells[3][i].clearWallRight();
			cells[3][i+1].clearWallLeft();
		}
		
		for(int i=3;i<6;i++){
			cells[i][3].clearWallDown();
			cells[i+1][3].clearWallUp();
		}
		
		for(int i=3;i<7;i++){
			cells[6][i].clearWallRight();
			cells[6][i+1].clearWallLeft();
		}
		
		for(int i=6;i>1;i--){
			cells[i][7].clearWallUp();
			cells[i-1][7].clearWallDown();
		}
		
		cells[1][7].clearWallRight();
		cells[1][8].clearWallLeft();
		
		cells[1][7].clearWallUp();
		cells[0][7].clearWallDown();
		for(int i=1;i<4;i++){
			cells[i][8].clearWallDown();
			cells[i+1][8].clearWallUp();
		}
		
		cells[4][8].clearWallRight();
		cells[4][9].clearWallLeft();
	}
	
	/** 2222222222222222222    PHASE 2 STUFF   22222222222222222222222222 **/
	public boolean isInBounds(int r, int c){
		return r>=0 && r<ROWS && c>=0 && c<COLS;
	}
	
	public void solveStep(){//takes the next step in solving the maze
		
		int[] drs={-1,0,1,0};
		int[] dcs={0,1,0,-1};//arrays holding possible drs and dcs
		int dr=0,dc=0,cr=solving.peek().row(),cc=solving.peek().col();
		for(int i=0;i<4;i++){
			dr=drs[i];//using direction to get to the cell i want to reference
			dc=dcs[i];
			if(isInBounds(cr+dr,cc+dc)){
				MazeCell curr=cells[cr][cc];//these are here for simplicity. didnt want to refer to 2d array every time
				MazeCell next=cells[cr+dr][cc+dc];
				if(!curr.isBlockedDir(i) && next.isBlank() && !next.isVisited() && !next.isDead()){
					next.setStatus(1);//if its ok to go to the cell, i mark that cell as having been visited and add it to my stack for backing up
					solving.push(next);
					return;
				}//end if
			}
		}//end for loop
		solving.pop().setStatus(2);
			//if i get to a dead end, i go back
		return;
		
			
	}
	
	
	/* 33333333333333333333    Phase 3 stuff    3333333333333333333333333 */
	
	public MazeCell getNeighbor(MazeCell mc, int dir){
		int[] drs={-1,0,1,0};
		int[] dcs={0,1,0,-1};
		int dr=drs[dir];
		int dc=dcs[dir];
		if((mc.row()+dr)>=0 && (mc.row()+dr)<ROWS && (mc.col()+dc)>=0 && (mc.col()+dc)<COLS)
			return cells[mc.row()+dr][mc.col()+dc];
		return null;
	}
	
	public ArrayList<MazeCell> blankNeighbors(MazeCell mc){
		ArrayList<MazeCell> answer=new ArrayList();
		for(int i=0;i<4;i++)
			if(getNeighbor(mc,i)!=null && getNeighbor(mc,i).isBlank())
				answer.add(getNeighbor(mc,i));
		return answer;
	}
	
	public int getDirectionFrom(MazeCell orig, MazeCell dest){
		int[] drs={-1,0,1,0};
		int[] dcs={0,1,0,-1};
		int dir=0;
		int dr=dest.row()-orig.row();
		int dc=dest.col()-orig.col();
		
		for(int i=0;i<4;i++)
			if(dr==drs[i] && dc==dcs[i])
				dir=i;
		return dir;		
	}
	
	public void stepCarve(){
		ArrayList<MazeCell> spots=blankNeighbors(creation.peek());
		if(!spots.isEmpty()){
			int index=(int)(Math.random()*spots.size());
			MazeCell dest=spots.get(index);
			int dirTo=getDirectionFrom(creation.peek(),dest),dirFrom=getDirectionFrom(dest,creation.peek());
			creation.peek().clearWallDir(dirTo);
			dest.clearWallDir(dirFrom);
			dest.setStatus(MazeCell.VISITED);
			creation.push(dest);
			return;
		}
		creation.pop().setStatus(MazeCell.DEAD);
		return;
		
		
	}
	
	
	public void carveARandomMaze(){
	
		for(int i=0;i<ROWS;i++){
			cells[i][0].setStatus(2);
			cells[i][COLS-1].setStatus(2);
		}
		begRow=(int)(Math.random()*ROWS);
		cells[begRow][0].setStatus(MazeCell.VISITED);
		creation.push(cells[begRow][0]);		
		solving.push(cells[begRow][0]);		
		cells[begRow][1].setStatus(MazeCell.VISITED);				
		creation.push(cells[begRow][1]);		
		cells[begRow][0].clearWallRight();
		cells[begRow][1].clearWallLeft();
		
		
		
		exitRow=(int)(Math.random()*ROWS);
		cells[exitRow][COLS-1].setStatus(MazeCell.BLANK);
		
		while(creation.peek().row()!=begRow || creation.peek().col()!=0)
			stepCarve();
		
		cells[exitRow][COLS-1].setStatus(MazeCell.BLANK);
		
		for(int i=0;i<ROWS;i++)
			for(int j=1;j<(COLS-1);j++)
				cells[i][j].setStatus(MazeCell.BLANK);
	}
	
	//4444444444444444444  PHASE 4 STUFF 4444444444444444444444444444
	private void resetMaze(){
		
	}
	
	
	//This gets called any time that you press a button
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==solve){
			while(solving.peek().row()!=exitRow || solving.peek().col()!=(COLS-1)){
				solveStep();
				pause(1);
			}
			JOptionPane.showMessageDialog(null,"Done Solving");
		}
			
		if(e.getSource()==reset){
			this.dispose();
			new MazeFrame();
		}
		
	}//end action performed
	
	
	private void setUpControlPanel(){
		controls = new JPanel();
		solve = new JButton("solve");
		solve.addActionListener(this);
		controls.add(solve);
		reset=new JButton("reset");
		reset.addActionListener(this);
		controls.add(reset);
		
		this.add(controls, BorderLayout.NORTH);
	}
	
	public static void main(String[] args){	new MazeFrame();}
	
	public void pause(int millisecs){
	     try{ 
		Thread.sleep(millisecs); 
	     }
	     catch(Exception ex){
		ex.printStackTrace();
	     }
	}
	
	public int getCols(){return COLS;}

}//end class

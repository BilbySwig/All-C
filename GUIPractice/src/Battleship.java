import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Battleship extends JFrame implements ActionListener{
	private JButton[][] shots; //each button will represent a shot that you have taken
	private JPanel shotHolder; //holds the buttons
	private JTextArea myDisplay; //will display YOUR ships (as text)
	//constructor
	public Battleship(){
		super();
		shotHolder = new JPanel(new GridLayout(5,10));
		myDisplay = new JTextArea();
		
		//declare AND instantiate 2D character array named ships
		char[][] ships=new char[5][10];
		//fill the array with '^' waves
		for(int r=0;r<=4;r++)
			for(int c=0;c<=9;c++)
				ships[r][c]='^';
		//"place" your ships
		ships[0][1]='D';
		ships[1][1]='D';
		ships[1][5]='D';
		ships[2][5]='D';
		ships[4][2]='B';
		ships[4][3]='B';
		ships[4][4]='B';
		ships[4][5]='B';
		ships[1][7]='C';
		ships[2][7]='C';
		ships[3][7]='C';
		
		//use "myDisplay.append(?)" to print the grid into the myDisplay textArea
		for(int r=0;r<=4;r++){
			for(int c=0;c<=9;c++)
				myDisplay.append(ships[r][c]+"\t");
			myDisplay.append("\n");
		}
		//instantiate "shots", a 2D array of JButtons (it has already been delcared!)
		shots=new JButton[5][10];
		//instantiate each element of "shots", add listener, and add to shotHolder
		for(int r=0;r<=4;r++)
			for(int c=0;c<=9;c++){
				shots[r][c]=new JButton("("+r+","+c+")");
				shots[r][c].addActionListener(this);
				shotHolder.add(shots[r][c]);
			}
		//finishing touches
		this.add(myDisplay,BorderLayout.SOUTH);		
		this.add(shotHolder, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 700);
		this.setVisible(true);
	}
	
	//THE MAIN
	public static void main(String[] args) {new Battleship();}
	
	//@Override
	public void actionPerformed(ActionEvent arg0) {
		//who got pressed?
		JButton pressed = ((JButton)arg0.getSource());
		//displays a yes/no/cancel box
		int hit = JOptionPane.showConfirmDialog(this, "HIT?");
		
		if(hit==0){ //they pressed yess
			System.out.println("YES");
			pressed.setBackground(Color.RED);
		}
		else if(hit==1){//they pressed no
			System.out.println("NO");
			pressed.setBackground(Color.BLUE);
		}
	}

}

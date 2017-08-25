

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/** The MAIN is in here :  run this file **/

//NO need to change anything here
public class AirPlane extends JFrame{
	
	private JPanel plane, firstClass, coach;
	private JTextField info;
	private JButton[][] seats;
	private Color[] colors = {new Color(255,255,0), Color.GREEN, new Color(0,153,51), new Color(0,0,255), new Color(102,102,255), new Color(200,200,255)};
		
	public AirPlane(){
		super("Thank you for flying");
		info = new JTextField();
		info.setEditable(false);
		this.add( info, BorderLayout.NORTH);
		
		makePlane();
						
		//finishing touches
		this.setSize(500,950);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void fillSeat(PlaneTicket pt){
		info.setText("Seating: "+pt);
		String seat = pt.getSeat();
		int row=0, col=0;
		char letter= seat.charAt( seat.length()-1);
		if(letter == 'A') col= 0;
		if(letter == 'B') col= 1;
		if(letter == 'C') col= 2;
		if(letter == 'D') col= 3;
		if(letter == 'E') col= 4;
		if(letter == 'F') col= 5;
		
		row = Integer.parseInt(seat.substring(0, seat.length()-1))-1;
		seats[row][col].setBackground( colors[pt.getPriority()] );
	}
	
	private void makePlane(){
		seats = new JButton[31][6];
		plane = new JPanel();
		firstClass = new JPanel( new GridLayout(4,5));
		String[] letter={"A","B","C","D","E","F"};
		for(int r=1; r<=4; r++)
			for(int c=0; c<4; c++){
				seats[r-1][c] = new JButton(r+letter[c]);
				//seat.addActionListener(this);
				firstClass.add(seats[r-1][c]);
				if(c==1) firstClass.add(new JPanel());//aisle
			}				
		coach = new JPanel(new GridLayout(27,7));
		for(int r=5; r<32; r++)
			for(int c=0; c<6; c++){
				seats[r-1][c] = new JButton(r+letter[c]);
				//seat.addActionListener(this);
				coach.add(seats[r-1][c]);
				if(c==2) coach.add(new JPanel()); //aisle
			}	
		plane.add(firstClass, BorderLayout.NORTH);
		plane.add(coach, BorderLayout.CENTER);
		this.add(plane, BorderLayout.CENTER);
		
		JPanel key = new JPanel(new GridLayout(2,PlaneTicket.words.length/2));
		for(int i=0; i<PlaneTicket.words.length; i++){
			JButton b = new JButton(PlaneTicket.words[i]);
			b.setBackground( colors[i]);
			key.add(b);
		}
		this.add(key, BorderLayout.SOUTH);

	}
	


}

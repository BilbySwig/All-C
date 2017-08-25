
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
//No need to change anything here
public class Terminal extends JFrame implements ActionListener{
	private static AirPlane plane;
	private JTextArea peopleWaiting;
	private JButton seatNext, newArrival;
	private AirportPQ line;
	
	public Terminal(){
		super("Airport Terminal");
		plane = new AirPlane();
		PlaneTicket.setupSeats();
		line = new AirportPQ();
		
		
		
		seatNext = new JButton("<<<Seat Next Passenger");
		seatNext.addActionListener(this);
		this.add(seatNext, BorderLayout.NORTH);
		
		peopleWaiting = new JTextArea();
		this.add( new JScrollPane(peopleWaiting), BorderLayout.CENTER);
		
		newArrival = new JButton("New passengers show up");
		newArrival.addActionListener(this);
		this.add(new JScrollPane(newArrival), BorderLayout.SOUTH);
		//finish up
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(350,550);
		this.setLocation(500,200);
		this.setVisible(true);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		peopleWaiting.setText( line.toString());
	}
	
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource()==seatNext){
			if(line.isEmpty())return;
			PlaneTicket pt = line.remove();
			plane.fillSeat( pt);
			this.repaint();
			//JOptionPane.showMessageDialog(this, "seating "+pt);
		}
		if(e.getSource()==newArrival){
			int howMany = (int)(Math.random()*10)+1;
			for(int i=0; i<howMany; i++)
				if( PlaneTicket.moreSeats())
					line.add( new PlaneTicket());
			this.repaint();
		}
	}

	public static void main(String[] args){		
		new Terminal();
	}
}

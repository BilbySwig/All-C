import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ColorPicker2 extends JFrame implements ActionListener{
	private JPanel colorPane, buttonPane;
	private String[] labels = {"Red", "Orange", "Green", "Blue", "Black", "White"};
	private Color[] colors = {Color.RED, Color.ORANGE, Color.GREEN, Color.BLUE, Color.BLACK, Color.WHITE};
	private JButton[] buttons;
	
	//constructor
	public ColorPicker2(){
		super("Colors Are Fun");
		
		//main pane
		colorPane = new JPanel();
		colorPane.setBackground(Color.WHITE);
		this.add(colorPane, BorderLayout.CENTER);
		
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new GridLayout(3,2));
		
	//~~~~~~~~~~~~~~~~buttons!!!!!!!!!~~~~~~~~~~~~~~~~~~
		//instantiate the array!
		buttons= new JButton[6];
		//now for each button, you need to:
		//1) instantiate the button (new JButton)
		//2) add the actionlistener
		//3) tell buttonPane to add the button
		for(int i=0;i<=5;i++){
			buttons[i]=new JButton(labels[i]);
			buttons[i].addActionListener(this);
			buttonPane.add(buttons[i]);
		}
						
	//~~~~~~~~~~~~~~~~finishing up~~~~~~~~~~~~~~~~~~~~~~~~~
		this.add(buttonPane, BorderLayout.SOUTH);		
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	

	public void actionPerformed(ActionEvent e) {
		JButton pressed = (JButton)(e.getSource());
		//if(pressed == red)
			//colorPane.setBackground(Color.RED);
		for(int i=0;i<=5;i++){
			if(pressed==buttons[i])
				colorPane.setBackground(colors[i]);
		}
			
	}
	public static void main(String[] args) {new ColorPicker2();}

}

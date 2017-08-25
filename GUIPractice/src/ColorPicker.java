import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ColorPicker extends JFrame implements ActionListener{
	private JPanel colorPane, buttonPane;
	private JButton red, orange, green, blue, black, white;
	
	//constructor
	public ColorPicker(){
		super("Colors Are Fun");
		
		//main pane
		colorPane = new JPanel();
		colorPane.setBackground(Color.WHITE);
		this.add(colorPane, BorderLayout.CENTER);
		
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new GridLayout(1,6));
		
		//buttons!!!!!!!!!
		red = new JButton("RED");
		red.addActionListener(this);		
		buttonPane.add(red);
		
		orange = new JButton("ORANGE");
		orange.addActionListener(this);
		buttonPane.add(orange);
		
		green = new JButton("GREEN");
		green.addActionListener(this);		
		buttonPane.add(green);
		
		blue = new JButton("BLUE");
		blue.addActionListener(this);		
		buttonPane.add(blue);
		
		black = new JButton("BLACK");
		black.addActionListener(this);		
		buttonPane.add(black);
		
		white = new JButton("WHITE");
		white.addActionListener(this);		
		buttonPane.add(white);
	
		//finishing up
		this.add(buttonPane, BorderLayout.NORTH);		
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	

	public void actionPerformed(ActionEvent e) {
		JButton pressed = (JButton)(e.getSource());
		if(pressed == red)
			colorPane.setBackground(Color.RED);
		if(pressed==orange)
			colorPane.setBackground(Color.ORANGE);
		if(pressed==green)
			colorPane.setBackground(Color.GREEN);
		if(pressed == blue)
			colorPane.setBackground(Color.BLUE);
		if(pressed == black)
			colorPane.setBackground(Color.BLACK);
		if(pressed == white)
			colorPane.setBackground(Color.WHITE);
				
	}
	public static void main(String[] args) {new ColorPicker();}

}

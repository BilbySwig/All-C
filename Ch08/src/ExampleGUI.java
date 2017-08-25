import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
public class ExampleGUI extends JFrame implements ActionListener{
	private JPanel canvas;
	private JButton blueButton, redButton, greenButton, goButton;
	private JTextField redField, greenField, blueField;
	
	public ExampleGUI(){
		super("Example GUI");
		//Get the canvas ready
		canvas = new JPanel();
		canvas.setBackground(Color.WHITE);
		this.add(canvas, BorderLayout.CENTER);
		
		//**********************colored buttons********************
		JPanel buttonPane = new JPanel(new GridLayout(1,3));
		blueButton = new JButton("BLUE");
		blueButton.addActionListener(this);
		buttonPane.add(blueButton);
		
		redButton=new JButton("RED");
		redButton.addActionListener(this);
		buttonPane.add(redButton);
		
		greenButton = new JButton("GREEN");
		greenButton.addActionListener(this);
		buttonPane.add(greenButton);
		
		this.add(buttonPane, BorderLayout.NORTH);
		
		//*****************text field ***************************
		JPanel bottomPane = new JPanel(new GridLayout(1,4));		
		redField = new JTextField("0");
		redField.setBackground(Color.RED);
		bottomPane.add(redField);
		
		greenField = new JTextField("0");
		greenField.setBackground(Color.GREEN);
		bottomPane.add(greenField);
		
		blueField = new JTextField("0");
		blueField.setBackground(Color.BLUE);
		bottomPane.add(blueField);
		
		goButton = new JButton("Make Color");
		goButton.addActionListener(this);
		bottomPane.add(goButton);		
		this.add(bottomPane, BorderLayout.SOUTH);
		
		//finish up
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		//
		if(e.getSource()==blueButton)
			canvas.setBackground(Color.BLUE);
		
		if(e.getSource()==greenButton)
			canvas.setBackground(Color.GREEN);
		
		if(e.getSource()==redButton)
			canvas.setBackground(Color.RED);
		if(e.getSource() == goButton){
			
			String tmp=redField.getText();
			//convert "word" to a #
			int red=Integer.parseInt(tmp);
			
			tmp=blueField.getText();
			//convert "word" to a #
			int blue=Integer.parseInt(tmp);
			
			tmp=greenField.getText();
			//convert "word" to a #
			int green=Integer.parseInt(tmp);
			
			canvas.setBackground( new Color(red,green,blue) );
		}
			

	}
	
	public static void main(String[] args) {new ExampleGUI();}
}

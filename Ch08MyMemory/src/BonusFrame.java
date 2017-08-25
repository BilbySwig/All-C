import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class BonusFrame extends JFrame{// implements ActionListener{
	private JComboBox rowOrCol;
	private JTextField input;
	private MyLittleMemoryGame parent;
	public JButton go;
	private static boolean done;
	private static String [] result;
	
	public BonusFrame(MyLittleMemoryGame p){
		super("BONUS!!!!!!!");
		parent = p;
		done = false;
		result = new String[2];
		
		JPanel top = new JPanel(new GridLayout(2,1));
		top.add( new JLabel("You get to see an entire Row OR entire Column!  Which would you like?"));
		rowOrCol = new JComboBox();
		rowOrCol.addItem("Row");
		rowOrCol.addItem("Column");
		top.add(rowOrCol);
		this.add(top, BorderLayout.NORTH);
		
		JPanel middle = new JPanel(new GridLayout(1,2));
		middle.add(new JLabel("Number :"));
		input = new JTextField("");
		middle.add(input);
		this.add(middle, BorderLayout.CENTER);
		
		go = new JButton("GO");
		go.addActionListener(parent);
		this.add(go, BorderLayout.SOUTH);
		
		//finish up
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(300,150);
		this.setResizable(false);
		this.setVisible(false);
	}
	/*
	public static String[] showInputDialog(){
		BonusFrame me = new BonusFrame();
		try{
			me.paint(me.getGraphics());
			while(!done)
				Thread.sleep(10);
		}
		catch(Exception ex){ex.printStackTrace();}
		return result;
	}
	*/
	/*
	public static void main(String[] args) {		
		new BonusFrame();
	}
	*/
		
	/*
	public void actionPerformed(ActionEvent e) {
		result[0]=rowOrCol.getSelectedItem().toString();
		result[1] = input.getText().trim();
		done = true;
		this.dispose();
	}
	*/

	
	

}

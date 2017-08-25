//package SmurfInfectionStudent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class SmurfVillage extends JFrame implements ActionListener{
	public static final int BOARDWIDTH=500;
	public static final int BOARDHEIGHT=500;
	private static Image bg;
	private JPanel panel;	
	private JTextField display;
	ArrayList<Smurf> village;
	private double percent=10;	
	
	public void play(){
		int numS = Integer.parseInt(JOptionPane.showInputDialog(this,"How many smurfs?"));
		int numI = Integer.parseInt(JOptionPane.showInputDialog(this,"How many of those are infected?"));
		/* instantiate ArrayList, insert SMURFS */
		ArrayList<Smurf>smurfies=new ArrayList();
		for(int i=0; i<numS;i++)
			smurfies.add(new Smurf());
		
		for(int i=0; i<numI;i++)
			smurfies.get(i).infectMe();
		/* done */
		
		while(true){
			endGame();
			/***** Draw and move ****/
			for(Smurf adjective:smurfies)
				adjective.draw();
			
			for(Smurf adjective:smurfies){
				adjective.move();
				if(adjective.getXPos()>=500 || adjective.getXPos()<=0 || adjective.getYPos()>=500 || adjective.getYPos()<=0)
					adjective.reverseDirection();//makes smurfs bounce off edges of window
			}
			
			/*************************/
			
			/* *****Infection!***** */
			for(Smurf adjective: smurfies)
				if(adjective.isInfected())
					for(Smurf adverb: smurfies)
						if(adjective.isTouching(adverb))
							adverb.infectMe();
			/* **********************/			
			
			/**~~~~~~~~~Death~~~~~~**/
			for(int i=smurfies.size()-1;i>=0;i--)
				if(smurfies.get(i).getTimeInfected()==200)
					smurfies.remove(i);
					
			/**~~~~~~~~~~~~~~~~~~~~**/			
			
			/* --------- percentage --------*/
			double currentI=0;
			for(int i=smurfies.size()-1;i>=0;i--){
				if(smurfies.get(i).isInfected())
					currentI++;
				percent=(currentI/smurfies.size())*100;
			}
			/* -------------------------*/
					
			
			display.setText(percent+"% infection rate");
			pause();
		}//end while loop
	}//end play function
	
	
	/**------------DO NOTE TYPE BELOW THIS LINE-------------------**/
	public void endGame(){
		if(percent==100){
			JOptionPane.showMessageDialog(this,"Smurfs are extinct!");
			System.exit(0);
		}
		if(percent==0){
			JOptionPane.showMessageDialog(this,"Smurfs have survived");
			System.exit(0);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton theyPressed = (JButton)e.getSource();

	}

	
	public SmurfVillage(){
		super("Smurf Village");
		try{
			bg = Toolkit.getDefaultToolkit().getImage("./src/village.png");
			MediaTracker mt=new MediaTracker(new Component(){});
			mt.addImage(bg, 1);
			mt.waitForAll();
		}catch(Exception ex){ex.printStackTrace();}
		panel = new JPanel();
		this.add(panel);	
		
		display = new JTextField();
		display.setEditable(false);
		display.setBackground( new Color(77,173,52) );
		display.setForeground(Color.RED);
		display.setFont(new Font("Arial",Font.BOLD,18));
		this.add(display, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setSize(BOARDWIDTH, BOARDHEIGHT+55);
		this.setResizable(false);
		this.setVisible(true);		
		play();
	}
	
	public void pause(){
		((Graphics2D)panel.getGraphics()).drawImage(Smurf.getImg(),0,0, null);
		try{Thread.sleep(35);}catch(Exception ex){ex.printStackTrace();}
		Smurf.getImg().getGraphics().clearRect(0, 0, BOARDWIDTH, BOARDHEIGHT);
		Smurf.getImg().getGraphics().drawImage(bg, 0, 0, null);
	}
	public static void main(String[] args) {new SmurfVillage();}
	

}

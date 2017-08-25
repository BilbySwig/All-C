//package phase1Kids;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;

public class PracticeBoard extends JFrame implements ActionListener, KeyListener{
	private final int INITX = 50, INITY = 50;
	private JPanel panel;
	private BufferedImage img;
	private JTextArea info, info2, info3;
	private ArrayList<PacMan> players;

	private PacMan player;
	private PacMan player2;
	private PacMan player3;
	private LameGhost ghosty;
	
	//constructor
	public PracticeBoard(){
	/* ~~~~~~~~~~~~Create your players in HERE~~~~~~~~~~~~~~~~*/
		player = new PacMan(50, 50, 0, false, false, Color.BLUE);
		player2= new PacMan(700, 550, 3, Color.GREEN);
		player3= new PacMan(50, 600);
		ghosty = new LameGhost();
	/* ~~~~~~~~~~~~^^^^Create your players^^^^ ~~~~~~~~~~~~~~~~*/		
		setupStuff();

	}
	
	public void runGame(){		
		while(true){
			/******make things happen*******/
			player.move();
			player2.move();
			info.setText( player.toString() );
			info2.setText( player2.toString() );
			info3.setText( player3.toString() );
			player.setSize(player.getSize()+1);
			player2.setSpeed(player2.getSpeed()+1);
			player3.teleport();
			/** ^^^ make things happen ^^^ ***/
			drawGame();	
		}//end loop
	
	}
	
	private void setupStuff(){
		players = new ArrayList<PacMan>();
		players.add(player);
		players.add(player2);
		players.add(player3);
		panel = new JPanel();
		this.add(panel);
		img = new BufferedImage(PacMan.BOARDWIDTH,PacMan.BOARDHEIGHT,BufferedImage.TYPE_INT_RGB);
		
		JPanel display = new JPanel(new GridLayout(3,3));
		info = createText(display);
		info2 = createText(display);
		info3 = createText(display);
		if(player!=null)  info.setBackground(player.getHue());
		if(player2!=null)  info2.setBackground(player2.getHue());
		if(player3!=null)  info3.setBackground(player3.getHue());
		this.add(display, BorderLayout.EAST);
		
		//finish up
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(PacMan.BOARDWIDTH+10+200, PacMan.BOARDHEIGHT+30);
		this.setVisible(true);

		panel.setFocusable(true);
		panel.requestFocusInWindow();
		panel.addKeyListener(this);
		runGame();
	}

	//a function that will draw everything
	public void drawGame(){
		//erase everything
		img.getGraphics().clearRect(0, 0, PacMan.BOARDWIDTH, PacMan.BOARDHEIGHT);

		//draw ghost
		if(ghosty!=null)
			ghosty.draw(img.getGraphics());
		//draw ALL pacman		
		//player.draw(img.getGraphics());
		for(PacMan p: players)
			if(p!=null)
				p.draw(img.getGraphics());
		
		//now draw that image to the panel
		((Graphics2D)panel.getGraphics()).drawImage(img,0,0, null);		
		pause();
	}
	
	public void pause(){
		try{Thread.sleep(5);}catch(Exception ex){ex.printStackTrace();}
	}
		
	public JTextArea createText(JPanel p){
		JTextArea t = new JTextArea("No Player");
		t.setRows(5);
		t.setColumns(10);
		t.setEditable(false);
		t.setFont(new Font("Arial",Font.BOLD, 22));
		p.add(t);
		return t;
	}
	
	public static void main(String[] args) {new PracticeBoard();}

	//@Override
	public void keyPressed(KeyEvent e) {		
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
			player.downPressed();	
		if(e.getKeyCode()==KeyEvent.VK_UP)
			player.upPressed();	
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
			player.leftPressed();	
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			player.rightPressed();
		//player 2
		if(e.getKeyCode()==KeyEvent.VK_S)
			player2.downPressed();
		if(e.getKeyCode()==KeyEvent.VK_W)
			player2.upPressed();
		if(e.getKeyCode()==KeyEvent.VK_A)
			player2.leftPressed();
		if(e.getKeyCode()==KeyEvent.VK_D)
			player2.rightPressed();
			
	}

	//@Override
	public void keyReleased(KeyEvent arg0) {}
	//@Override
	public void keyTyped(KeyEvent arg0) {}
	//@Override
	public void actionPerformed(ActionEvent e) {}
	

}

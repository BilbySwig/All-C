//package ForKidsPart2;


//package shapeGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.*;

//A gift from Mr. Reed
public abstract class VideoGame extends JFrame implements KeyListener{
	private BufferedImage img;
	private JPanel canvas;
	
	public static final int BOARDWIDTH=500, BOARDHEIGHT=500;
	//private GameBoard canvas;
	
	
	public VideoGame(){
		super("Pretty Shapes");
		
		img = new BufferedImage(BOARDWIDTH,BOARDHEIGHT,BufferedImage.TYPE_INT_RGB);
		//canvas = new GameBoard(array);
		canvas = new JPanel();
		canvas.setPreferredSize(new Dimension(BOARDWIDTH,BOARDHEIGHT));
		this.setSize(BOARDWIDTH+10, BOARDHEIGHT+30);
		this.getContentPane().add(canvas);
		
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		canvas.setFocusable(true);
		canvas.requestFocusInWindow();
		canvas.addKeyListener(this);
		//animate();
		setupElements();
	
	}
	
	public Graphics getCrayon(){
		return img.getGraphics();
	}
	
	public void animate(){
		while(true){					
			gameFrame();
			pause();
		}
	}
	
	public abstract void setupElements();
	public abstract void gameFrame();
	
	public void pause(){
		//now draw that image to the panel
		((Graphics2D)canvas.getGraphics()).drawImage(img,0,0, null);

		try{Thread.sleep(75);}catch(Exception ex){ex.printStackTrace();}
		//erase everything
		//clear it for next go round
		img.getGraphics().clearRect(0, 0, BOARDWIDTH, BOARDHEIGHT);
	}
	
	//keyListener stuff
	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}

}

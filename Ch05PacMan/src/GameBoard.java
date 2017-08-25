	//package phase2Kids;
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
//NOTE:  we need to train a PacMan to "eat"
public class GameBoard extends JFrame implements ActionListener, KeyListener{
	private final int INITX = 50, INITY = 50;
	private PacMan player;
	private ArrayList<Wall> walls;
	private ArrayList<Food> food;
	private ArrayList<Ghost> ghosts;
	private JPanel panel;
	private BufferedImage img;	
	public JButton upB, downB, rightB, leftB, growB, shrinkB,colorB, teleportB;
	private JTextArea info;
	
	public void runGame(){		
		while(true){
			Wall hitW = this.hitWall();
			if(hitW==null)
				player.move();
			
			Ghost bob= this.hitGhost();
			if(bob!=null)
				if(player.getShield())
					bob.die();
				else
					break;
			
			Food stoopid= this.hitFood();
			if(stoopid!=null)
				player.eat(stoopid);
			info.setText(player.toString());
			drawGame();	
		}
		
		drawGame();
		JOptionPane.showMessageDialog(this,"GAME OVER\nYou earned: "+player.getScore()+" points!");
		System.exit(0);
	}
	
	//they pushed a button!
	public void actionPerformed(ActionEvent e){
		JButton pressed = (JButton)e.getSource();
		if(pressed == rightB)
			player.rightPressed();
			
		if(pressed == leftB)
			player.leftPressed();
			
		if(pressed == downB)
			player.downPressed();
			
		if(pressed == upB)
			player.upPressed();	
			
		if(pressed == growB)
			player.setSize(player.getSize()+1);
		if(pressed == shrinkB)
			player.setSize(player.getSize()-1);
		if(pressed == teleportB)
			player.teleport();
		if(pressed == colorB)
			player.randomColor();
	}

/****~~~~~~~~~~DON'T TOUCH STUFF BELOW THIS LINE~~~~~~~~~~****/
	//constructor
	public GameBoard(){
		player = new PacMan(INITX, INITY);
		panel = new JPanel();
		this.add(panel);
		img = new BufferedImage(PacMan.BOARDWIDTH,PacMan.BOARDHEIGHT,BufferedImage.TYPE_INT_RGB);
		
		//the food
		food = new ArrayList();
		for(int x=0; x<PacMan.BOARDWIDTH; x+=20)
			for(int y=0; y<PacMan.BOARDHEIGHT; y+=20)
				food.add(new Food(x,y));

		//set up walls				
		walls = new ArrayList();
		for(int i=0; i<20; i++){
			walls.add(new Wall());
			//dont' trap him in a wall : ghetto fix
			while(walls.get(i).contains(INITX, INITY))
				walls.set(i, new Wall());
		}
		//need walls on the edge of screen -- can't leave
		walls.add(new Wall(0,-2,PacMan.BOARDWIDTH,5)); //top
		walls.add(new Wall(0,PacMan.BOARDHEIGHT-2,PacMan.BOARDWIDTH,5)); //bottom
		walls.add(new Wall(-2,0,5,PacMan.BOARDHEIGHT));//left
		walls.add(new Wall(PacMan.BOARDWIDTH-2,0,5,PacMan.BOARDHEIGHT));//right
		
		//the ghosts
		Ghost.setBoard(this);
		ghosts = new ArrayList();
		for(int i=0; i<10; i++){
			ghosts.add(new Ghost());
			//don't make ghosts that are stuck in walls: ghetto fix
			while(ghosts.get(i).collisionCheck()==1 ){
				ghosts.set(i, new Ghost());
			}
				
		}	
		
		
		//the buttons! 		
		JPanel sidebar = new JPanel(new BorderLayout());
		
		JPanel controls = new JPanel(new GridLayout(3,3));
		controls.add(new JPanel());
		upB = createButton("UP", controls);
		controls.add(new JPanel());
		leftB = createButton("LEFT", controls);
		controls.add(new JPanel());
		rightB = createButton("RIGHT", controls);
		controls.add(new JPanel());
		downB = createButton("DOWN", controls);
		controls.add(new JPanel());	
		
		JPanel controls2 = new JPanel(new GridLayout(4,1));
		growB = createButton("GROW",controls2);
		shrinkB = createButton("SHRINK",controls2);
		colorB = createButton("Color",controls2);
		teleportB = createButton("TELEPORT",controls2);
		
		info = new JTextArea("HEY");
		info.setRows(3);
		info.setEditable(false);
		info.setFont(new Font("Arial",Font.BOLD, 22));
		
		sidebar.add(controls, BorderLayout.NORTH);
		sidebar.add(info, BorderLayout.CENTER);
		sidebar.add(controls2, BorderLayout.SOUTH);
		sidebar.setPreferredSize(new Dimension(250,50));
		this.add(sidebar,BorderLayout.EAST);
		

		
		//finish up
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(PacMan.BOARDWIDTH+10+250, PacMan.BOARDHEIGHT+30);
		this.setVisible(true);
		
		//window.setLocation(this.getX()+this.getWidth(), this.getY());
		//window.setVisible(true);
		
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		panel.addKeyListener(this);
		runGame();
	}
	
	/******Collision detection ****************/
	//hands back 3 lists in 1:  list of xs, list of ys on top edge of pacman, list of ys on bottom edge
	public ArrayList<ArrayList<Integer>> hitPoints(){
		//my velocities : up right down left
		int[] xvels = {0, player.getSpeed(), 0,-1*player.getSpeed()};
		int[] yvels = {-1*player.getSpeed(), 0, player.getSpeed(),0};
		int xvel = xvels[player.getDirection()];
		int yvel = yvels[player.getDirection()];
		//where you will be after you move...(need to detect hit B4 i actually get into the wall)
		int px = player.getXPos()+xvel;
		int py = player.getYPos()+yvel;
		int rad = player.getSize()/2;
		//EDGE OF PacMan:    y = py+-sqrt(rad^2 - (x-px)^2 ) 
		// 		xs, y1, y2 contains points on the edge of pacman
		// 		(xs[1], y1[1]) = on top edge
		// 		(xs[1], y2[1]) = on bottom edge
		ArrayList<Integer> xs =  new ArrayList();
		ArrayList<Integer> y1 = new ArrayList();
		ArrayList<Integer> y2 = new ArrayList();
		//start on left of pacman, every 2 pixels, calculate 2 ys (top edge & bottom edge)
		for(double x=px-rad; x<=px+rad; x+=2){
			xs.add( (int)Math.round(x));			 
			y1.add( (int)(py + Math.sqrt( rad*rad - (x-px)*(x-px) ) ) );
			y2.add( (int)(py - Math.sqrt( rad*rad - (x-px)*(x-px) ) ) );
			//panel.getGraphics().setColor(Color.GREEN);
			//panel.getGraphics().fillOval((int)Math.round(x), y1.get(y1.size()-1), 3, 3);
			//panel.getGraphics().fillOval((int)Math.round(x), y2.get(y2.size()-1), 3, 3);
		}

		ArrayList<ArrayList<Integer>> answer = new ArrayList();
		answer.add(xs);
		answer.add(y1);
		answer.add(y2);
		return answer;
	}
	
	//returns the Wall that I (will) hit.  Returns null if i hit nothing
	public Wall hitWall(){
		ArrayList<ArrayList<Integer>> xyy = hitPoints();
		ArrayList<Integer> xs = xyy.get(0); //xs
		ArrayList<Integer> y1 = xyy.get(1); //ys on top edge
		ArrayList<Integer> y2 = xyy.get(2); //ys on bottom edge
		
		//check each wall: check points on the "edge" of pacman to see if they are in the wall
		for(Wall w: walls)	
			for(int pt=0; pt<xs.size(); pt++){
				if(w.contains(xs.get(pt), y1.get(pt)))
					return w;
				if(w.contains(xs.get(pt), y2.get(pt)))
					return w;
			}
		return null;

	}
	
	//which ghost i (will) hit.  null if no hit
	public Ghost hitGhost(){
		ArrayList<ArrayList<Integer>> xyy = hitPoints();
		ArrayList<Integer> xs = xyy.get(0); //xs
		ArrayList<Integer> y1 = xyy.get(1); //ys on top edge
		ArrayList<Integer> y2 = xyy.get(2); //ys on bottom edge

		//check each ghost: check points on the edge of pacman to see if they are in the ghost
		for(Ghost g: ghosts)
			for(int pt=0; pt<xs.size(); pt++){
				if(g.contains(xs.get(pt), y1.get(pt)))
					return g;
				if(g.contains(xs.get(pt), y2.get(pt)))
					return g;
			}
		return null;
	}
	
	//return the food that i hit.  null if no hit
	public Food hitFood(){
		//don't use a for-each...need to remove
		for(int i=food.size()-1; i>=0; i--){
			Food f = food.get(i);
			//easier collision detection for food
			int dx = Math.abs(player.getXPos() - f.getXPos());
			int dy = Math.abs(player.getYPos() - f.getYPos());
			//lame collision detection -- am i close?
			if(dx<=f.getSize() && dy<=f.getSize()){
				Food returnThis = f;
				food.remove(i);
				return returnThis;	
			}
			
		}
		return null;
	}
	/********End Collision Detection****************/
	
	
	//ghost needs this for collision detection
	public ArrayList<Wall> getWalls(){return walls;}
	
	//a function that will draw everything
	public void drawGame(){
		//erase everything
		img.getGraphics().clearRect(0, 0, PacMan.BOARDWIDTH, PacMan.BOARDHEIGHT);
		//draw food
		for(Food f: food)
			f.draw(img.getGraphics());
		//draw walls
		for(Wall w:walls)
			w.draw(img.getGraphics());
		//draw ghost
		for(Ghost g: ghosts){			
			g.move();
			g.draw(img.getGraphics());
		}
		
		//draw pacman
		player.draw(img.getGraphics());
		
		//draw the toString
		//img.getGraphics().drawString(player.toString(), PacMan.BOARDWIDTH, PacMan.BOARDHEIGHT);
		
		//now draw that image to the panel
		((Graphics2D)panel.getGraphics()).drawImage(img,0,0, null);
		
		pause();
	}
	
	public void pause(){
		try{Thread.sleep(5);}catch(Exception ex){ex.printStackTrace();}
	}
	
	//makes it easier in constructor
	private JButton createButton(String txt, JPanel holder){
		JButton b = new JButton(txt);
		b.addActionListener(this);
		b.setFocusable(false);
		holder.add(b);
		return b;
	}
	
	public static void main(String[] args) {new GameBoard();}

	//@Override
	public void keyPressed(KeyEvent e) {		
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
			actionPerformed(new ActionEvent(downB,0,""));	
		if(e.getKeyCode()==KeyEvent.VK_UP)
			actionPerformed(new ActionEvent(upB,0,""));
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
			actionPerformed(new ActionEvent(leftB,0,""));
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			actionPerformed(new ActionEvent(rightB,0,""));
	}

	//@Override
	public void keyReleased(KeyEvent arg0) {}
	//@Override
	public void keyTyped(KeyEvent arg0) {}
	

}

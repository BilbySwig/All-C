

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class Snake implements KeyListener{
	public static final int UP=0, RIGHT=1, DOWN=2, LEFT=3;
	private int sz=0;
	//variables you will need
	// 1) pointer to the first and/or last piece of the snake
	private BodySegment first, last;
	// 2) a variable to keep track of what direction this snake is facing
	private int dir;
	
	public Snake(){
		//go ahead and give him 1 BodySegment
		addFirst();
		
		//tell him what direction he is moving
		dir=RIGHT;
	}
	
	public BodySegment getFirst(){return first;}
	public int size(){return sz;}
	
	public void addFirst(){
		BodySegment thing;
		int dy=0, dx=0;
		if(first!=null){//determines where to addFirst, if there is already a first
			if(dir==UP)
				dy=-15;
			if(dir==RIGHT)
				dx=15;
			if(dir==DOWN)
				dy=15;
			if(dir==LEFT)
				dx=-15;
			
			thing=new BodySegment(first.getX()+dx,first.getY()+dy);
			thing.setNext(first);//adds in specified place
			first.setPrev(thing);
			first=thing;
		}
		else{//if there isnt already a first,
			thing=new BodySegment(135,135);//creates a first
			first=thing;
			last=thing;
		}
		sz+=1;//edits size to keep size function O(1)
	}
	
	public void removeLast(){
		last.getPrev().setNext(null);
		last=last.getPrev();//snake cuts off his butt so he can grow a new head and stay the same length
		sz-=1;//edits size to keep size function O(1)
	}
	
	
	public void draw(Graphics g){
		//tell EACH segment to draw (and pass it g)
		BodySegment curr=first;
		while(curr!=null){
			curr.draw(g);
			curr=curr.getNext();
		}
	}
	
	public boolean gonnaLose(){
		BodySegment curr=first.getNext();
		boolean result=false;
		while(curr!=null){
			if(first.isTouching(curr))
				result=true;
			curr=curr.getNext();	
		}//finds out whether snake is touching itself
		return (result || first.getX()>=SnakeGame.WIDTH || first.getX()<0 || first.getY()>=SnakeGame.HEIGHT || first.getY()<0);		
		//snake will lose if snake is touching itself or a wall	
	}
	
	
	@Override
	//make the snake respond to key presses
	public void keyPressed(KeyEvent e) {	
		if(e.getKeyCode() == KeyEvent.VK_UP && dir!=DOWN)
			dir=UP;
		if(e.getKeyCode()==KeyEvent.VK_DOWN && dir!=UP)//pressing keys changes snake's direction and nothing else
			dir=DOWN;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT && dir!= LEFT)
			dir=RIGHT;
		if(e.getKeyCode()==KeyEvent.VK_LEFT && dir!= RIGHT)
			dir=LEFT;
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	
}

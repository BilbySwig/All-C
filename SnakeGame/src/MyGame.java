
import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MyGame extends SnakeGame{
	//things you inherited from SnakeGame
	//protected Snake player;
	//protected BodySegment food;
	//protected double waitSeconds;
	private boolean gameOver=false;
	private long startTime;
	private int scoreVal;
	private long gameTimesec,gameTimemin,gameTimemilli;
	
	public MyGame(){
		super();
		int diff=difficultyPrompt();//difficulty setting
		setBG(new Color(70,32,102));
		setGridColor( new Color(0,255,255,50));//colors for the game board
		if(diff==3)//on "impossible" there is no grid
			setGridColor(new Color(70,32,102));
		waitSeconds=.16-(.05*diff);//game is faster with higher difficulty
		if(diff==3)
			waitSeconds+=.01;//editing so that "impossible" isn't ACTUALLY impossible
		startTime=System.currentTimeMillis();//variable to find time elapsed during game
		
		timer.setHorizontalAlignment(JTextField.CENTER);
		timer.setBackground(new Color(70,32,102));
		timer.setForeground(new Color(0,255,255));
		Font Agency=new Font("Agency FB",Font.BOLD, 25);//setup for timer and score (fonts, placement, colors)
		timer.setFont(Agency);
		score.setHorizontalAlignment(JTextField.CENTER);
		score.setBackground(new Color(70,32,102));
		score.setForeground(new Color(0,255,255));
		score.setFont(Agency);
		playGame();
	}
	
	public void gameFrame(){	
			if(gameOver)//
				return;
			player.removeLast();	
			player.addFirst();//movement. kills butt, adds new head
			
			if(player.getFirst().isTouching(food)){//if the player eats, they grow
				player.addFirst();
				placeFood();
			}
			if(player.gonnaLose()){//if the player will lose on the next frame,
				gameOver=true;     //they type in their name and their score is added to the scoreboard(along with their name and time)
				String newName=JOptionPane.showInputDialog(null, "You lose! Type your name:");
				gameTimesec=59*gameTimemin+gameTimesec+gameTimemilli/1000;
				new ScoreFrame(new ScoreRecord(newName,(double)gameTimesec,scoreVal)); 	
				
			}	
			
			//timer in milliseconds, seconds and minutes
			gameTimemilli=(System.currentTimeMillis()-startTime);
			gameTimesec=gameTimemilli/1000;//raw values
			gameTimemin=gameTimemilli/60000;
			if(gameTimesec>=60){//values based on the other values
				gameTimesec-=gameTimemin*60;
			}
			if(gameTimemilli>=1000)
				gameTimemilli-=gameTimesec*1000;
			
			timer.setText("TIME: "+gameTimemin+":"+gameTimesec+"."+gameTimemilli);//time of game is displayed
			
			scoreVal= 100*(player.size()-3);//score is displayed. score is 100 times the amount of food they have eaten
			score.setText("SCORE:"+scoreVal);
			
			drawGame();//do this at some point			
			
	}
	
	
	
	public static void main(String[] args){new MyGame();}	
}

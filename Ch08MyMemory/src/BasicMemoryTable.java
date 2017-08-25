import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class BasicMemoryTable extends JFrame implements ActionListener, KeyListener{
			protected Card[][] cards;
			
			
			protected static int ROWS=4, COLS=5;
			public static int NUMPICS = ROWS*COLS/2;
			protected JPanel table, statusBar; //statusBar shows time and # clicks
			protected JFrame inputFrame;
			//protected int r1, c1, r2, c2;
			protected JTextField row, col;
			protected JButton flip1;	
			protected int numGuesses, time;
				
			//private Font font;
			protected boolean done;
			protected int cardsLeft;
			
			public BasicMemoryTable(String title){
				
				super(title);		
				//numCardsUp = 0;
				numGuesses = 0;
				time = 0;

				decideLayout();		
				Card.setTheme( (String)JOptionPane.showInputDialog(this, "Choose a theme", "Memory", JOptionPane.PLAIN_MESSAGE, null, Card.themeNames, Card.themeNames[0]), NUMPICS );
				

				
				//set the cards
				table = new JPanel(new GridLayout(ROWS, COLS));
				//setting up cards
/*				
				cards = new Card[ROWS][COLS];
				for(int r=0; r<ROWS; r++)
					for(int c=0; c<COLS; c++){
						cards[r][c] = new Card();
						table.add(cards[r][c]);
					}
				
				//pick a card to be a bonus
				int br = (int)(Math.random()*ROWS);
				int bc = (int)(Math.random()*COLS);
				cards[br][bc].setBonus(true);
*/			
				this.add(table, BorderLayout.CENTER);
				
				//the statusBar for timer
				statusBar = new JPanel();		
				statusBar.setPreferredSize(new Dimension(this.getWidth(), 75));
				statusBar.setBackground( Card.themeColor() );
				this.add(statusBar, BorderLayout.NORTH);
				
				//finishing up
				this.setBackground(Card.themeColor());//Color.BLACK);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				int w = COLS * Card.WIDTH;
				int h = ROWS * Card.HEIGHT;
				this.setSize(w,h + 75);
				this.setResizable(false);
				this.setVisible(true);
						

				createInputFrame();
				
			
			}
			
			/** kids will write this **/
			public void cardSelected( int row, int col){}
			
			
			
			protected JButton createButton(String txt, JPanel holder){
				JButton b = new JButton(txt);
				b.addActionListener(this);
				holder.add(b);
				return b;
			}
			
			protected void decideLayout(){
				//randomize the number of rows and cols
				double decider = Math.random();
				if( decider < 1.0/3){
					ROWS = 4;
					COLS = 5;
				}
				else if(decider < 2.0/3){
					ROWS = 5;
					COLS = 4;
				}
				else{
					ROWS = 4;
					COLS = 4;
				}

				
				NUMPICS = ROWS*COLS/2;
				cardsLeft = ROWS * COLS;
			}
			
			private void createInputFrame(){
				
				
				Font smallerFont = new Font(Card.themeFont().getFontName(), Font.BOLD, 13);
				
				
				//set up the input portion
				inputFrame = new JFrame();
				inputFrame.setBackground( Card.fontColor() );
				inputFrame.getContentPane().setBackground( Card.fontColor() );
				
				JPanel inputFields = new JPanel(new GridLayout(2,5));//2,2));
				inputFields.setBackground( Card.fontColor() );
				JLabel r = new JLabel("Row");
				r.setHorizontalAlignment(SwingConstants.CENTER);
				r.setBackground( Card.fontColor() );
				r.setForeground( Card.themeColor());
				inputFields.add( new Label(""));//spacer
				inputFields.add( r );

				JLabel c = new JLabel("Col");
				c.setHorizontalAlignment(SwingConstants.CENTER);
				c.setBackground( Card.fontColor() );
				c.setForeground( Card.themeColor());
				inputFields.add( new Label(""));//spacer
				inputFields.add( c );
				JPanel spacer = new JPanel();
				spacer.setBackground(Card.themeColor());
				inputFields.add( new Label(""));//spacer
				
				//labels to make it look like a 2d array
				Font bigger =  new Font("Arial", Font.BOLD, 16);
				JLabel l1 = new JLabel("Cards[  ");
				JLabel l2 = new JLabel("] [");
				JLabel l3 = new JLabel("  ]");
				l1.setFont( bigger);
				l1.setHorizontalAlignment(SwingConstants.RIGHT);
				l2.setFont( bigger);
				l2.setHorizontalAlignment(SwingConstants.CENTER);
				l3.setFont( bigger);
				l1.setForeground( Card.themeColor() );
				l2.setForeground( Card.themeColor() );
				l3.setForeground( Card.themeColor() );
				
				row = new JTextField();
				row.setFont(new Font("Arial",Font.BOLD, 15)); //35
				row.setBackground( Card.fontColor() );//Card.themeColor() );
				row.setForeground( Card.themeColor() );//Card.fontColor() );
				row.setHorizontalAlignment(SwingConstants.CENTER);
				inputFields.add( l1);//spacer
				inputFields.add(row);
				
				col = new JTextField();
				col.setFont(new Font("Arial",Font.BOLD, 15));
				col.setBackground( Card.fontColor() );//Card.themeColor() );
				col.setForeground( Card.themeColor() );//Card.fontColor() );
				col.setHorizontalAlignment(SwingConstants.CENTER);
				inputFields.add( l2);//spacer
				inputFields.add(col);
				inputFields.add( l3);//spacer
				
				//so they can just press enter!
				row.addKeyListener(this);
				col.addKeyListener(this);

				
				//JPanel rightSide = new JPanel( new BorderLayout());
				inputFrame.add(inputFields, BorderLayout.NORTH);
				flip1 = new JButton("FLIP CARD");
				flip1.setBackground( Card.themeColor() );
				flip1.setForeground( Card.fontColor() );
				flip1.setFont( smallerFont );//Card.themeFont() );
				flip1.addActionListener(this);
				flip1.addKeyListener(this);
				
				inputFrame.add( flip1, BorderLayout.SOUTH);
				//inputFrame.add(rightSide, BorderLayout.EAST);
				//finishing up
				inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//inputFrame.setSize(200,200);
				//inputFrame.setLocation(new Point(this.getX()+this.getWidth(), this.getY()));
				inputFrame.setSize(300,76);//this.getWidth(), 160);
				//inputFrame.setLocation( new Point(this.getX(), this.getY()+this.getHeight()));
				//inputFrame.setLocation( new Point(this.getX()+this.getWidth()/2-inputFrame.getWidth()/2, this.getY()+22));
				inputFrame.setLocationRelativeTo(this);
				inputFrame.setLocation( new Point(this.getWidth()/2-inputFrame.getWidth()/2, 22));
				inputFrame.setResizable(false);
				inputFrame.setUndecorated(true);//doesn't show the title bar
				inputFrame.setVisible(true);
				inputFrame.setAlwaysOnTop(true);
				
				
			}
			


			public void actionPerformed(ActionEvent e) {
				
			}
			
			public void clearFields(){
				row.setText("");
				col.setText("");
				row.requestFocus(true);
				row.requestFocusInWindow();
			}
			
			public void getInput(){

				if(!validInput())
					return;
				//blah
				cardSelected(Integer.parseInt(row.getText()), Integer.parseInt(col.getText()));
				clearFields();

			}
			
			public boolean validInput(){
				int rr, cc;
				String r = row.getText().trim();
				String c = col.getText().trim();
				try{
					rr = Integer.parseInt(r);
					cc = Integer.parseInt(c);
				}
				catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(this,"Type a valid number");
					return false;
				}
				if( rr >=0 && rr<cards.length && cc>=0 && cc<cards[0].length)
					return true;
				else{
					JOptionPane.showMessageDialog(this, "Invalid row or column!");
					return false;
				}
			}
		
			
			public void bonusRound(){		
				//clock.pauseIt();
				//row or column?
				String[] options = {"Row", "Column"};
				int ans= JOptionPane.showOptionDialog(this, "BONUS!!\n\n  You get to peak at a Row or Column for free!", "Sneak a Peek", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				String rowcol = "row";
				if(ans == 1)
					rowcol = "column";
				
				boolean gotit=false;
				while(!gotit){//they keep making exceptions happen
					try{			
						//String[] stuff = BonusFrame.showInputDialog();
						//String tmp = stuff[1]; //the number!!
						//System.out.println(stuff[0]);


						//which one?
						String tmp = JOptionPane.showInputDialog(this, "BONUS!!!\nWhich "+ rowcol+" would you like to see?");
						
						
						for(int r=0; r<ROWS; r++)
							for(int c=0; c<COLS; c++)
								cards[r][c].paint(cards[r][c].getGraphics());

						if(ans == 0){//they wanted to see a row!	
							int r = Integer.parseInt(tmp.trim());
							for(int c = 0; c<COLS; c++)
								cards[r][c].flip();
							pause();
							pause();
							for(int c = 0; c<COLS; c++)
								cards[r][c].flip();
							//clock.playIt();
						}
						else{//they wanted to see a column
							//they wanted to see a row!	
							int c = Integer.parseInt(tmp.trim());
							for(int r = 0; r<ROWS; r++)
								cards[r][c].flip();
							pause();
							pause();
							for(int r = 0; r<ROWS; r++)
								cards[r][c].flip();
							//clock.playIt();

						}
						gotit = true;//if this doesn't throw exceptions
						return;
					}
					catch(NumberFormatException nf){
						JOptionPane.showMessageDialog(this,"Invalid number!");				
					}
					catch(ArrayIndexOutOfBoundsException ai){
						JOptionPane.showMessageDialog(this, "That "+rowcol+" was out of bounds!");				
					}
					catch(NullPointerException np){
						JOptionPane.showMessageDialog(this,"You didn't enter a number!");				
					}//end catch
				}//wend while
				//bonusRound();
			}
			
			public void pause(){
				try{Thread.sleep(800);}
				catch(Exception ex){ex.printStackTrace();}
			}

			//they can press enter rather than press the button
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
					getInput();
					
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
			public static void main(String[] args) {new BasicMemoryTable("Basic");}

		}


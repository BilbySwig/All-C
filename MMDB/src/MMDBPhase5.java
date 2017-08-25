import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class MMDBPhase5 extends JFrame implements ActionListener{
	private JPanel panel; 
	private JTable table;
	private Object[][] tableData;
	private JComboBox dropDown;
	private JTextField inputBox;
	private JButton button;
	
	private Set<String> movieTitles;
	final static String TITLEFILE = "./src/top250.txt";
	
	static MyTreeMap tagsFromTitles;
	static MyTreeMap titlesFromTags;
	final static String TAGFILE = "./src/top250Tags.txt";
	
	public MMDBPhase5(){
		super("blah");
		
		tagsFromTitles=new MyTreeMap();//maps for the  titles and tags
		titlesFromTags=new MyTreeMap();
		readTags();	//fills up tagsFromTitles and titlesFromTags	
		movieTitles=readTitles();//fills up movieTitles
		
		inputBox=new JTextField();//setting up box for searching
		
		dropDown=new JComboBox();//setting up drop down menu
		
		dropDown.addItem("Title Matches");//a little aesthetic touch, makes the drop down menu not empty and say what it contains before the first search
		
		button=new JButton("Search");//setting up button for searching
		
		dropDown.addActionListener(this);//action listeners for the button and dtrop down menu
		button.addActionListener(this);
		
		//this pushes everything but the table to the top of the gui
		JPanel stuffHolder=new JPanel(new GridLayout(3,1));
		stuffHolder.add(inputBox);
		stuffHolder.add(button);
		stuffHolder.add(dropDown);
		this.add(stuffHolder, BorderLayout.NORTH);
	
		//setting up the table for recommendations
		tableData=new Object[250][2];
		String[] columnTitles={"Percent","Title"};
		table=new JTable(tableData,columnTitles);
		
		//making the gui scrollable
		this.add( new JScrollPane(table),BorderLayout.CENTER);
		
		
		//finishing touches to set up the gui
		this.setSize(500,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	//main
	public static void main(String[] args) { new MMDBPhase5();	}
	
	//gets all the titles and returns them in a set
	public static Set<String> readTitles(){
		Set<String> titles = new HashSet<String>();
		try{
			
			FileReader reeder = new FileReader(new File(TITLEFILE));
			BufferedReader br = new BufferedReader(reeder);
			String line = br.readLine();
			int rank=1;//variable to remove the number in front of each title
			while(line!=null){
				line=line.replace(rank+") ", "");//getting rid of rank
				titles.add(line);//adding the current title to the set
				
				//last lines of loop
				rank++;//increase rank
				line = br.readLine();//go to next line
			}//done reading
			br.close();
			
			
		}
		catch(Exception ex){ex.printStackTrace();}
		
		return titles;
	}//end readTitles
	
	public static void readTags(){
		//this function will need to fill up BOTH of your MyTreeMaps!
		try{
			
			FileReader reeder = new FileReader(new File(TAGFILE));
			BufferedReader br = new BufferedReader(reeder);
			String line = br.readLine();			
			while(line!=null){
				/** ~~~~~~~~~~~~ DO SOMETHING HERE ~~~~~~~~~~~~~~~~~**/
				String[] titlesAndTags=new String[2];//short array for the two things on each line; index 0=title, index 1=tag
				titlesAndTags[0]=line.substring(0,line.indexOf(")")+1);// the title is put at index 0, including year
				titlesAndTags[1]=line.substring(line.indexOf(")")+1);//the tag is put at index 1
				titlesAndTags[1]=titlesAndTags[1].trim();//gets rid of all extra spaces
				tagsFromTitles.put(titlesAndTags[0], titlesAndTags[1]);//place the title and tag in both MyTreeMaps, alternating which is key and which is value
				titlesFromTags.put(titlesAndTags[1], titlesAndTags[0]);
				/** ~~~~~~~~~~~~ ~~~~~~~~~~~~~~~~~ ~~~~~~~~~~~~~~~~~**/
				//last line of loop
				line = br.readLine();
			}//done reading
			br.close();	
		}
		catch(Exception ex){ex.printStackTrace();}
	}//end readTags
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getSource()==button){//if the button is pressed
			String input= inputBox.getText();//get the text in the search box
			if(input.length()==0)//if input is nothing
				return;//stop doing stuff
			dropDown.removeAllItems();//clear dropDown
			MyTreeMap titleMatches=getTitleMatches(input,movieTitles);
			//fill up dropDown with the titles that have anything in common with input
			for(Comparable key: titleMatches.keyList())
				for(String title:titleMatches.get(key))
					dropDown.addItem(title);
		}//end button stuff
		
		if(arg0.getSource()==dropDown){//if the drop down menu is selected
			Comparable selected=(Comparable)dropDown.getSelectedItem();//get whatever is selected
			if(selected==null)//if theres nothing selected
				return;//stop
			CounterMap counts=getRecommendations((String)selected);//countermap to count occurrences of tags in movies 
			double maxHits=counts.get(selected);//the value that all the percents will be based on
			MyTreeMap inverse=counts.inversion();
			
			//filling up the table
			int r=0;
			for(Comparable key:inverse.keyList())
				for(String title:inverse.get(key)){
					double percent=(((Integer)key)/maxHits)*100;
					DecimalFormat df=new DecimalFormat("0.00");//decimal formatting so the percentages look good
					df.setRoundingMode(RoundingMode.CEILING);
					tableData[r][0]=df.format(percent)+"%";
					tableData[r][1]=title;
					r+=1;
				}
			this.repaint();
			}//end dropDown stuff
	}

	public static MyTreeMap getTitleMatches(String userInput, Set<String> actualTitles){
		CounterMap theCount=new CounterMap();//countermap to keep track of how many matches with the input the title has
		userInput=userInput.toLowerCase();//makes it not case sensitive
		String[] wordsOfInput=userInput.split(" ");//gets each word of the input
		for(String word: wordsOfInput)
			for(String title:actualTitles)
				if(title.toLowerCase().contains(word))//counts how many matches each title has to the input
					theCount.put(title);
		return theCount.inversion();
	}//end getTitleMatches
	
	public static CounterMap getRecommendations(String requestedMovie){
		CounterMap results=new CounterMap();//countermap to keep track of how similar each title is to the input, based on tags in common
		for(String tag:  tagsFromTitles.get(requestedMovie))
			for(String title: titlesFromTags.get(tag))//counting tags in common with the input
				results.put(title);
		return results;
	}//end getRecommendations
	
}

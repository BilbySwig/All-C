import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.TreeMap;

import javax.swing.*;
/** ~~~~~~~~~~~~~~ WELCOME:  For phase 0, just focus on readFromFile ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**/

public class WookieGUI extends JFrame implements ActionListener, WindowListener{
	private String FILENAME = "./src/StarWarsInfo2.txt";
	private JTabbedPane tabby;
	private String[] columnNames1={"Name","Homeworld","Species","Gender","Occupation"};
	private String[] columnNames2={"Planet", "Named Characters"};
	private JTable myTable1;
	private JTable myTable2;//numberings b/c each tab will have a table
	private Object[][] tableData1;
	private Object[][] tableData2;
	private JComboBox dropDown, sortBy;
	private Map<String, Set<Person>>[] lotsOfData;
	private JButton button;
	
	public WookieGUI(){
		super("Example");
		
		lotsOfData=new TreeMap[5];//array of sets of maps for categories and searching
		for(int i=0;i<5;i++)
			lotsOfData[i]=new TreeMap();//put treemaps into lotsofdata so they can be added to later
		
		
		dropDown=new JComboBox();//dropdown menu setup for specific searching
		dropDown.addActionListener(this);
		
		sortBy=new JComboBox();//dropDown menu setup for categories
		sortBy.addActionListener(this);
		
		button=new JButton("Add a Person");
		button.addActionListener(this);
		
		
		JPanel holder = new JPanel(new GridLayout(3,2));//2rows 2 cols
		holder.add( new JLabel("Showing:"));
		holder.add( new JLabel("Category:"));//titles for dropdown menus
		holder.add(dropDown);
		holder.add(sortBy);//setup for dropDown and sortBy
		holder.add(button);
		this.add(holder, BorderLayout.NORTH);
		
		//get a JTable set up
		/**IMPORTANT: the table is FOREVER linked to this array
		 * Whatever goes into this array will be shown in the table **/
		tableData1 = new Object[100][5];		
		myTable1 = new JTable(tableData1, columnNames1);
		
		tableData2=new Object[100][2];
		myTable2=new JTable(tableData2,columnNames2);
		
		//add the table onto a "tab", then add the tabbedPane onto the frame
		tabby = new JTabbedPane();
		tabby.addTab("People", new JScrollPane(myTable1));
		tabby.addTab("Statistics", new JScrollPane(myTable2));
		this.add(tabby, BorderLayout.CENTER);
		
		//items for the sortBy drop down menu
		String[] categories={"Home","Species","Gender","Job"};
		for (String str: categories)
			sortBy.addItem(str);
		
		readFromFile();	
		
		this.addWindowListener(this);
		
		this.setSize(500,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		fillTables(determineIndex((String)sortBy.getSelectedItem()));
	}
	
	private void readFromFile(){
		try{
			
			String[] charInfo;//array for the current character's info to be added
			FileReader reader = new FileReader(new File(FILENAME));
			BufferedReader buff = new BufferedReader(reader);
			String line = buff.readLine(); //that's the lame intro line; throw it away
			
			line = buff.readLine();//first real line
			while(line!=null){//while there is more to read
				//process the current line/record
				charInfo=line.split(":");//turn the line into an array of data about the character
				Person newGuy=new Person(charInfo);//make the array into a character
				addPerson(newGuy);//add the current character
				
				//get next record
				line = buff.readLine();
			}//done reading the file
			/**~~~~~For phase 0:  now just try to print out your Map!  Once you know that the map has been built correctly, then you can move on~~~**/
		//	for(String s:planets.keySet())
		//		for(Person p: planets.get(s))
		//			System.out.println(p);//testing for readiness to be used
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}//end readFromFile
	
	public static void main(String[] args) { new WookieGUI();	}

	private void writeToFile(){
		try{
			FileWriter file = new FileWriter(FILENAME);//"output.txt");
			PrintWriter out = new PrintWriter(file);
			
			out.println("NAME:Homeworld:Species:Gender:Position");
			
			//now pick ONE map
			//use a loop
			//out.println( a person ) for each person
			for(String str:lotsOfData[0].keySet())
				for(Person p:lotsOfData[0].get(str))
					out.println(p);
					
			out.close();
		}catch(FileNotFoundException x){
			System.out.println("Can't Find It");
			System.exit(0);
		}catch(IOException i){
			System.out.println("Can't read file");
			System.exit(0);
		}
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		/**~~~ Don't even think about this until you are SURE that readFromFile created the Map correctly ~~~**/
		//if( arg0.getSource() == yourJComboBox )
		//	String what they selected = (String)yourJComboBox.getSelectedItem();
		// 	now you will fill up table data with the relevant information
		clearArray(tableData1);//clears spaces
		
		
		String selectedCat=(String)sortBy.getSelectedItem();
		int index=determineIndex(selectedCat);//getting new category b/c it may have changed
		
		//if the source is the sortBy drop down menu
		if(arg0.getSource()==sortBy){			
			fillTables(index);
			}
		
				
		//if the source is dropDown
		if(arg0.getSource()==dropDown){
			if(dropDown.getSelectedItem()==null)
				return;
			clearArray(tableData1);//clear the table data for the People tab
			int row=0;
			for(Person p:lotsOfData[index].get(dropDown.getSelectedItem())){
				for(int c=0; c<5; c++)
					tableData1[row][c]=p.get(c);//put all the right stuff in the table for the People tab
				row++;
			
			}
			this.repaint();	
		}
		if(arg0.getSource()==button){
			String[] data=new String[5];
			String[] types={"name","homeworld","species","gender","occupation"};
			for(int i=0;i<5;i++)
				data[i]=JOptionPane.showInputDialog("What is the person's "+types[i]+"?");
			addPerson(new Person(data));
		}
	}
	//clears shown stuff so new stuff won't show any of old stuff
	public void clearArray(Object[][] arr){
		for(int i=0;i<arr.length;i++)
			for(int j=0;j<arr[0].length;j++)
				arr[i][j]="";		
	}
	
	public void addPerson(Person p){
		String[] arr=p.getArray();//array of the character's data
		for( int i=0;i<5;i++){
			if(!lotsOfData[i].containsKey(arr[i]))//if this map doesnt have this piece of the character as a key
				lotsOfData[i].put(arr[i],new ChainedHashSet<Person>());//PUT IT IN THERE
		lotsOfData[i].get(arr[i]).add(p);//add the person to that set
		}//end for loop
	}//end addPerson
	
	//a helper to find the index in the array based on the selected string in sortBy
	public int determineIndex(String s){
		int index=-1;//return this if nothing good happens
		String[] options={"Home","Species","Gender","Job"};//possible selections
		for(int i=0;i<4;i++)
			if(s.equals(options[i]))//finding out which one is selected
				index=i+1;
		return index;//return the correct index
	}//ende determine index

	//Fills the tables up so that there is stuff to look at when u start up wookie
	public  void fillTables(int index){
		dropDown.removeAllItems();//clear dropDown	
		for(String str:lotsOfData[index].keySet()){//put the right stuff in dropDown
			dropDown.addItem(str);
		}
		
		//Stats
		clearArray(tableData2);
			
		int row=0;
		for(String str:lotsOfData[index].keySet()){
			tableData2[row][0]=str;//make the first col a key
			tableData2[row][1]=lotsOfData[index].get(str).size();//make the second col the # of ppl in that set
			row++;
		}
	}
	
	
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		writeToFile();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

//package myTunes;

import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MyTunesGUI extends JFrame implements ActionListener, WindowListener{
	//DON'T DELETE THESE
	private JTable display;
	private Object[][] data;
	private String[] headers = {"Name","Artist","Album","Genre","Price"};
	private SongCollection tunes;
	private final String FILENAME = "./src/input.txt";
	//****************YOUR VARIABLES HERE
	private JTextField artistsearch, albumsearch;//declarations of buttons and textfields for navigating MyTunes
	private JButton artistbutton, albumbutton,backtofull, addASong, Bargains, PercentGenre;
	
	public MyTunesGUI(){
		super("MyTunes");
		
		this.addWindowListener(this); 
		tunes = new SongCollection();
		readFromFile();
		data = new Object[100][5];
		display = new JTable(data, headers);
		display.setEnabled(false);
		JScrollPane scrolly = new JScrollPane(display);
		this.add(scrolly, BorderLayout.CENTER);
		
	//*************ADD CODE HERE********************************		
		showResults( tunes.allSongs()); //just for testing -- you can delete this line later
		
		JPanel paneofstuff=new JPanel(new GridLayout(2,2));//creation of panes for navigation stuff
		JPanel importantone=new JPanel(new GridLayout(2,2));
		
		artistsearch=new JTextField("Artist");//creates textfield for artist search and adds it to panel
		paneofstuff.add(artistsearch);
		
		artistbutton=new JButton("Search");
		artistbutton.addActionListener(this);
		paneofstuff.add(artistbutton);//adds button to activate artistsearch
		
		albumsearch=new JTextField("Album");
		paneofstuff.add(albumsearch);
		
		albumbutton=new JButton("Search");
		albumbutton.addActionListener(this);
		paneofstuff.add(albumbutton);//adds button to activate albumsearch
		
		backtofull=new JButton("Back to Full Collection");
		backtofull.addActionListener(this);//button to show full collection if user wants to see it without relaunching MyTunes
		importantone.add(backtofull);
		
		addASong=new JButton("Click Here to Add a Song");
		addASong.addActionListener(this);//button to add a song
		importantone.add(addASong);
		
		Bargains=new JButton("Click to See Bargains");
		Bargains.addActionListener(this);//button to see cheapest songs
		importantone.add(Bargains);
		
		PercentGenre=new JButton("Percentage of Songs of a Genre");
		PercentGenre.addActionListener(this);//button for seeing te percent of the songs of a certain genre
		importantone.add(PercentGenre);
		
		this.add(paneofstuff, BorderLayout.SOUTH);//places panels with buttons and such in the window
		this.add(importantone,BorderLayout.NORTH);
	//****************************************************
		
		
		//finishing touches!
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setVisible(true);		
	}
	
	
	//************ADD CODE HERE***************************
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==artistbutton)//shows songs by an artist input by user
			showResults( tunes.searchByArtist(artistsearch.getText()) );
		
		if(e.getSource()==albumbutton)//activates album search bar, showing songs in an album
			showResults(tunes.searchByAlbum(albumsearch.getText()));
		
		if(e.getSource()==backtofull)//returns songs shown to full collection
			showResults(tunes.allSongs());
		
		if(e.getSource()==addASong){
			String t=JOptionPane.showInputDialog("Enter Title of Song:");
			String a1=JOptionPane.showInputDialog("Enter Name of Artist:");//all of these joptionpanes get input from user about the new song that will be addd
			String a2=JOptionPane.showInputDialog("Enter Name of Album:");
			String g=JOptionPane.showInputDialog("Enter Genre:");
			double p=Double.parseDouble(JOptionPane.showInputDialog("Enter Price:"));
			Song newSong=new Song(t,a1,a2,g,p);//makes song out of input values
			tunes.addSong(newSong);//adds song to the song collection
		}
		
		if(e.getSource()==Bargains)//when pressed shows all of the cheapest songs
			showResults(tunes.bargainBin());
		
		if(e.getSource()==PercentGenre){//lets user input a genre and then tells them the percent of the songs that are that genre
			String	g2= JOptionPane.showInputDialog("What genre do you want?");
			JOptionPane.showMessageDialog(null, tunes.genrePercentage(g2)+"% of the songs are "+g2);
		}
	}
	
	
/** *****************GIFTS FROM MR. REED ****************** **/
	private void showResults(ArrayList<Song> results){
		clear();
		int row = 0;
		for(Song s: results)
			data[row++] = s.toArray();
		display.repaint();
		
	}
	private void showResults(double d){
		clear();
		data[0][0] = ""+d;
		display.repaint();
		
	}
	private void clear(){
		for(int i=0; i<data.length; i++)
			for(int j=0; j<data[0].length; j++)
				data[i][j] = null;
	}
	private void readFromFile(){
		try{
			
			FileReader reader = new FileReader(new File(FILENAME));
			BufferedReader buff = new BufferedReader(reader);
			String line = buff.readLine(); //that's the lame intro line			
			
			while(line!=null){
				String[] info = line.split(":");
				tunes.addSong( new Song(info[0],info[1],info[2],info[3],Double.parseDouble(info[4]) ) );
				line = buff.readLine();
			}			
		}catch(FileNotFoundException x){
			System.out.println("Can't find the input file (input.txt)");
			System.exit(0);
		}catch(IOException i){
			System.out.println("Can't read file");
			System.exit(0);
		}
		
	}
	private void writeToFile(){
		try{
			FileWriter file = new FileWriter(FILENAME);//"output.txt");
			PrintWriter out = new PrintWriter(file);
			
			for(Song s: tunes.allSongs()){
				Object[] stuff = s.toArray();
				for(int i=0; i<stuff.length-1; i++)
					out.print(stuff[i]+":");  //colons to separate almost everyone
				out.println(stuff[stuff.length-1]);  //no colon after the last dude
			}			
			out.close();
		}catch(FileNotFoundException x){
			System.out.println("Can't Find It");
			System.exit(0);
		}catch(IOException i){
			System.out.println("Can't read file");
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {new MyTunesGUI(); }

	public void windowOpened(WindowEvent arg0) {}
	public void windowClosing(WindowEvent arg0) {writeToFile();System.out.println("HEY");}
	public void windowClosed(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {	}
	public void windowDeiconified(WindowEvent arg0) {	}
	public void windowActivated(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
}



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import javax.swing.JOptionPane;

public class MMDBPhase3 {
	final static String INPUTFILE = "./src/top250.txt";
	
	
	public static void main(String[] args) {
		//1) read the file
		Set<String> movieTitles=readTitles();
		
		//2) ask for user input
		String userInput=JOptionPane.showInputDialog("Enter a title:");
		//3) generate a data structure that will have their matches
		MyTreeMap results=getTitleMatches(userInput,movieTitles);
		
		
		
		//4) print the title matches
		//		*each on their own line
		//		*in descending order of relevance
		System.out.println(results);
	}
	
	public static Set<String> readTitles(){
		Set<String> titles = new HashSet<String>();
		try{
			
			FileReader reeder = new FileReader(new File(INPUTFILE));
			BufferedReader br = new BufferedReader(reeder);
			String line = br.readLine();
			int rank=1;
			while(line!=null){
				line=line.replace(rank+") ", "");
				titles.add(line);
				
				//last lines of loop
				rank++;
				line = br.readLine();
			}//done reading
			br.close();
			
			
		}
		catch(Exception ex){ex.printStackTrace();}
		
		return titles;
	}
	
	public static MyTreeMap getTitleMatches(String userInput, Set<String> actualTitles){
		CounterMap theCount=new CounterMap();
		userInput=userInput.toLowerCase();
		String[] wordsOfInput=userInput.split(" ");
		for(String word: wordsOfInput)
			for(String title:actualTitles)
				if(title.toLowerCase().contains(word))
					theCount.put(title);
		return theCount.inversion();
	}
	
	
	
}

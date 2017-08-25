//package forKids;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FilePractice {

	public static void main(String[] args) {
		//We are going to be playing with  the file "practiceFile.txt"
		try{
			/**READING FROM FILE:**/
			/*
			BufferedReader br = openFileForReading();
			String line= br.readLine();
			while(line!=null){
				System.out.println(line);
				line=br.readLine();
			}
				
			
			br.close();//when you are done
			*/
			
			//WRITING TO A FILE:		
			PrintWriter pw = openFileForWriting();
			pw.println("I like to eat pie");
			pw.println("Do you also like to eat?");
			pw.println("blah blah blah blah blah");
			
			pw.close(); //be sure to do this when you are done with the file!
			
		}catch(Exception ex){ex.printStackTrace();}

	}
	
	/** You don't need to change these functions, just call them **/
	private static BufferedReader openFileForReading(){
		FileReader reeder;
		BufferedReader br=null;		
		try{
			reeder = new FileReader(new File("./src/practiceFile.txt"));
			br = new BufferedReader(reeder);			
		}
		catch(FileNotFoundException fnf){System.out.println("File Not Found!");}
		catch(Exception ex){ex.printStackTrace();}
		return br;
	}
	
	private static PrintWriter openFileForWriting(){
		FileWriter file = null;
		try{
			File scoreFile = new File("./src/practiceFile.txt");
			file = new FileWriter(scoreFile.getAbsolutePath());

		}catch(Exception ex){
			System.out.println("ERROR!!?");
			ex.printStackTrace();
		}
		return new PrintWriter(file);
	}
	

}

//package PigLatinNEW;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PLFileDecoder {
	private static File outputFile;
	private static BufferedReader buff;
	private static PrintWriter out;
	private static int whichWay;
	
	
	public static void main(String[] args) {
		try{

			openFiles();
			
			String line = buff.readLine(); //that's the lame intro line			
			String translatedLine="";
			
			while(line!=null){
				line = removePunctuation( line );
				line = line.trim();
				System.out.println( line );
				
				if(line.trim().length()>0){//if there was something to translate
					//translate the line
					if( whichWay == 0){//currently in English; going to pigLatin
						//hyphenated words are trouble -- they should just be 2 words!
						line = line.replace("-", " ").toLowerCase();
						translatedLine = Translator.toPigLatin(line);
					}
					else//currently in pigLatin; going to English
						translatedLine = Translator.toEnglish(line);
					
					//print this line to the screen AND into the output file
					System.out.println(translatedLine);
					out.println(translatedLine);
				}
				else
					out.println();
				
				//read in the next line to be translated
				line = buff.readLine();
				
			}//done reading & translating
			
			//close the files!
			buff.close();
			out.close();
			JOptionPane.showMessageDialog(null,"Your new file has been created.  Highlight your project & Press F5 to see the file.");
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}//end main
	
	public static void openFiles(){
		try{
			File inputFile;
			FileReader reader;

			JFileChooser chooser = new JFileChooser("./src/");
			FileNameExtensionFilter fnef = new FileNameExtensionFilter("Text Files","txt");
			chooser.setFileFilter(fnef);
			int option = chooser.showOpenDialog(null);
			if(option == JFileChooser.CANCEL_OPTION)
				System.exit(0);

			String[] options = {"English to PigLatin", "PigLatin to English"};
			whichWay = JOptionPane.showOptionDialog(null, "I want to convert...", "CONVERT", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);


			inputFile = chooser.getSelectedFile();
			String fn;
			if(whichWay==0)
				fn = inputFile.getName().substring( 0, inputFile.getName().indexOf(".txt") )+"PIGLATIN.txt";
			else
				fn = inputFile.getName().substring( 0, inputFile.getName().indexOf(".txt") )+"ENGLISH.txt";
			outputFile = new File(inputFile.getParentFile(), fn);


			reader = new FileReader(inputFile);//getClass().getResource(FILENAME).getFile());//new File(FILENAME));			
			buff = new BufferedReader(reader);
			FileWriter file = new FileWriter(outputFile);//getClass().getResource(FILENAME).getFile());//FILENAME);//"output.txt");
			out = new PrintWriter(file);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static String removePunctuation(String line){
		String[] endSent = {".  ", ":  ", "!  ", "?  ", " -- ", "--"};		
		String[] toRemove = {"*", ".", "," , "!" , "?" , "\"" , "'s" , ";" , ":", "(" , ")","--", ":", "'"};
		for( String s: endSent )
			line = line.replace( s, " ");
		for(String s : toRemove)
			line = line.replace( s, "");
		line = line.replace("  ", " ");
		line = line.replace("   ", " ");
		line = line.trim();
		return line;
	}

}

//package NovelAnalyzerForKids;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;
public class NovelAnalyzer {

	public static void main(String[] args) {
		String[] files={"AliceInWonderLand.txt","ChristmasCarol.txt","Dracula.txt","GrimmFairyTales.txt"};
		String[] toRemove = {".",",","!","?","\"","'s",";",":","(",")","'", "[", "]"};
		Map<String,Integer> mappy=new HashMap();
		SortedSet<String> outputs=new TreeSet();
		int totalWords=0;
		
		String FILENAME = "AliceInWonderLand.txt";
		FILENAME = "./src/"+(String)JOptionPane.showInputDialog(null,"Which Novel?","Which Novel?",JOptionPane.QUESTION_MESSAGE, null, files, files[0]);
		
		try{
			FileReader reader = new FileReader(new File(FILENAME));
			BufferedReader buff = new BufferedReader(reader);
			String line = buff.readLine(); //that's the lame intro line
			
			line = buff.readLine();
			while(line!=null){
				line=line.replace("--", " ");
				line = line.trim();//delete spaces at the beginning & end
				for(String str:toRemove)
					line=line.replace(str,"");
				line=line.toLowerCase();
				
					String[] words=line.split(" "); 
				//count for each word
				for(String str: words){
					if( str.trim().length()>0){
						if(!mappy.containsKey(str))
							mappy.put(str,1);
						else
							mappy.put(str,mappy.get(str)+1);
						totalWords++;
					}
				}
				
				
	
					//process this particular line of the book
					
				
				//now move to the next line
				line = buff.readLine();
			}// done reading the file
			buff.close();//close the file
			//PAUSE: print map u made
			
			for(String str:mappy.keySet())
				if(str.length()>3)
					outputs.add(formatNumber(mappy.get(str), 5)+" "+str);
			
			for(String str:outputs)
				System.out.println(str);
		
			System.out.println("Writer's Vocab: "+mappy.keySet().size()+" words.");
			System.out.println("There are a total of "+totalWords+" words in this novel.");
		}//end try
		catch(Exception ex){ex.printStackTrace();}

	}//end main
	
	/*
	 * postconditions: concatenates 0s onto the front of num until 
	 *        you have reached numDigits in length
	 * ex:  formatNumber( 5, 4) -->  0005
	 * ex:  formatNumber( 125, 5) --> 00125
	 * ex:  formatNumber( 9, 2) -->  09
	 */
	public static String formatNumber( int num, int numDigits){
		int[] cutoffs = new int[numDigits];
		for(int i=1; i<cutoffs.length; i++){
			cutoffs[i] = (int)Math.pow(10, i);
		}
		int howmany=0;
		for(int i=0; i<cutoffs.length; i++){
			if( num<cutoffs[i]){
				//System.out.println(num+"<"+cutoffs[i]);
				howmany=i;
				break;
			}
		}
		if(howmany!=0)
			howmany = numDigits-howmany;//how many zeroes need to be added up front
		String ans = ""+num;
		for(int i=0; i<howmany; i++)
			ans="0"+ans;
		return ans;
		
	}

}//end class

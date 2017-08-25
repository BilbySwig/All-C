import java.io.*;
import java.util.*;
import javax.swing.*;

public class PotterMaps {

	public static void main(String[] args) {
		try{
			String fileName="./src/Potter.txt";
			FileReader reeder = new FileReader(new File(fileName));
			BufferedReader br = new BufferedReader(reeder);
			
			Map<String,String> mappy=new HashMap();
			
			//read in each line 
			String line = br.readLine();
			while(line!=null){				
				//do stuff here
				String house=line.substring(line.indexOf(":")+1);
				String name=line.substring(0,line.indexOf(":"));
				mappy.put(name, house);
						
				//get the next line
				line = br.readLine();
			}//done reading!!!!!!!!!!!!!!
			
			//Print all things in mappy
			Set<String> allofem=mappy.keySet();
			for(String str: allofem)
				System.out.println(str+" is in "+mappy.get(str)+" house");
			
			//~~~~Now ask the user which character the would like to look up~~~~
			String inputName=JOptionPane.showInputDialog("Whose house do you want to know?");
			String response=mappy.get(inputName);
			if(response!=null)
				JOptionPane.showMessageDialog(null, inputName+" belongs to "+response+".");
			else
				JOptionPane.showMessageDialog(null, inputName+" doesn't go to Hogwarts because they're a plebe.");
			/**^^^^^^  KEEP YOU CODE UP THERE ^^^^^^ **/
			//***************^^^^WTF MR REED DO YOU EVEN GRAMMAR
		}//end try
		catch(Exception ex){ex.printStackTrace();}


	}//end main

}//end class

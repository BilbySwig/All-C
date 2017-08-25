import java.io.*;
import java.util.*;
public class PotterSets {

	public static void main(String[] args) {
		try{
			String fileName="./src/Potter.txt";
			FileReader reeder = new FileReader(new File(fileName));
			BufferedReader br = new BufferedReader(reeder);
			String line = br.readLine();
			Set<String> houses=new HashSet();
			Set<String> names=new HashSet();
			Set<String> discounts=new HashSet();
			while(line!=null){
				//System.out.println(line);				
				//do stuff here
				houses.add(line.substring(line.indexOf(":")+1));
				
				String addname=line.substring(line.indexOf(" ")+1, line.indexOf(":"));
				if(!names.add(addname))
				discounts.add(addname);
				
				//get the next line
				line = br.readLine();
			}//done reading
			//print out the names and houses now
					
			System.out.println("Discounts: "+discounts);	
			System.out.println(houses);
			System.out.println(names);
		}//end try
		catch(Exception ex){ex.printStackTrace();}


	}

}


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class MMDBPhase4 {
	final static String TAGFILE = "./src/top250Tags.txt";
	static String MovieTitle = "The Dark Knight (2008)";//<--just change this to check for different movies
	
	//***********HERE YOU NEED TO Declare TWO data structures
	//  1) key = title  ;  value = set of tags for that movie
	static MyTreeMap tagsFromTitles;
	//  2) key = tag    ;  value = set of movies with those tags
	static MyTreeMap titlesFromTags;
	/** make them both static variables for now **/
	
	public static void main(String[] args) {
		//0) instantiate your data structures
		tagsFromTitles=new MyTreeMap();
		titlesFromTags=new MyTreeMap();
		//1) read the file & fill your two data structures
		readTags();
		//2) generate a data structure that will hold # of hits for diff movies
		CounterMap theCount=getRecommendations(MovieTitle);
		
		
		
		
		//3) display the results:
		//		* descending order by percent
		//      * each movie on its own line
		System.out.println("Here are your recomendations:");
		displayRecResults(theCount);
	}
	
	public static void readTags(){
		//this function will need to fill up BOTH of your MyTreeMaps!
		try{
			
			FileReader reeder = new FileReader(new File(TAGFILE));
			BufferedReader br = new BufferedReader(reeder);
			String line = br.readLine();			
			while(line!=null){
				/** ~~~~~~~~~~~~ DO SOMETHING HERE ~~~~~~~~~~~~~~~~~**/
				String[] titlesAndTags=new String[2];
				titlesAndTags[0]=line.substring(0,line.indexOf(")")+1);
				titlesAndTags[1]=line.substring(line.indexOf(")")+1);
				titlesAndTags[1]=titlesAndTags[1].trim();
				tagsFromTitles.put(titlesAndTags[0], titlesAndTags[1]);
				titlesFromTags.put(titlesAndTags[1], titlesAndTags[0]);
				/** ~~~~~~~~~~~~ ~~~~~~~~~~~~~~~~~ ~~~~~~~~~~~~~~~~~**/
				//last line of loop
				line = br.readLine();
			}//done reading
			br.close();
			
			
		}
		catch(Exception ex){ex.printStackTrace();}
		
		
	}
	
	//returns a data structure where:
	//  key=movie title  ; value = number of tags this movie shares with the requested movie
	public static CounterMap getRecommendations(String requestedMovie){
		CounterMap results=new CounterMap();
		for(String tag:  tagsFromTitles.get(requestedMovie))
			for(String title: titlesFromTags.get(tag))
				results.put(title);
		return results;
	}
	
	//prints recommendations: 1 movie per line & in descending order by percentage
	public static void displayRecResults( CounterMap hits ){
		double maxHits=hits.get(MovieTitle);
		MyTreeMap theRanks=hits.inversion();
		for(Comparable theNum:theRanks.keyList())
			for(String title: theRanks.get(theNum)){
				double percent=(((Integer)theNum)/maxHits)*100;
				DecimalFormat df=new DecimalFormat("0.00");
				df.setRoundingMode(RoundingMode.CEILING);
				System.out.println(df.format(percent)+"% : "+title);
				
			}
		}

}

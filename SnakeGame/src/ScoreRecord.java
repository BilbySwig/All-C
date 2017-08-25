//package forKids;

public class ScoreRecord implements Comparable{
	private String userName;
	private double time;
	private int score;
	
	public ScoreRecord(String u, double t, int s){
		userName = u;
		time = t;
		score =s;
	}
	
	//line is a colon-delmited string like this...
	//     username:time:score
	public ScoreRecord(String line){
		String[] stuff = line.split(":");
		userName = stuff[0];
		time = Double.parseDouble(stuff[1]);
		score = Integer.parseInt(stuff[2]);
	}
	
	public String getUser(){return userName;}
	public double getTime(){return time;}
	public double getScore(){return score;}
	
	/**********  Write a pretty toString for output *************/
	public String toString(){
		return userName+" Score:"+score+" Time:"+time+" seconds";
	}
	
	//Important:  YOu want the file to be in colon-delmitted format!
	//   BECAUSE when you are creating a ScoreRecord, the constructor
	//   assumes that the input you give it is in this format.
	public String formatForFile(){
		return userName+":"+time+":"+score;//format such that u can add as a String
	}

	@Override
	public int compareTo(Object other) {
		//you MAY change this depending on how you want to keep score
		
		//you will need to cast him as a score record
		ScoreRecord that = (ScoreRecord)other; 
		if(this.score<that.score)//compares ScoreRecords based on score
			return -500;
		if(this.score>that.score)
			return 27;
		return 0;
	}
}

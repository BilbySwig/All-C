

import java.util.*;
public class PlaneTicket{
	//Priorities
	public static final int SPECIAL=0;
	public static final int FREQUENT_FLIER=1;
	public static final int FIRSTCLASS=2;	
	public static final int ROWS_5THRU13=5;
	public static final int ROWS_14THRU22=4;
	public static final int ROWS_23THRU31=3;
	                                  //     0      				1				   2			3				4			5
	public static final String[] words = {"*Special Boarding Needs*", "*Frequent Flier*",  "First Class", "Rows 23-31","Rows 14-22", "Rows 5-13", };
	
	private static String[] fn = {"Bob","Fred","George","Sally","Percy"};
	private static String[] ln = {"Smith","Young","Reed","Weasley","Swango"};
	private static char[] letter = {'A','B','C','D','E','F'};
	private static ArrayList<String> seats;
	
	//private member variables
	private String name;
	private int priority;
	private Date timeStamp;
	private String seat; //example: 4B
	 //row 1 -4 == first class, row 5-32 == coach
	//first class only has seats ABCD, coach has ABCDEF
	public static boolean moreSeats(){return seats.size()>0;}
	
	public PlaneTicket(String nm, int p, String s){
		name = nm;
		priority = p;
		timeStamp = null;
		seat = s;
	}
	
	public PlaneTicket(){
		if(seats.size()==0)
			return;
		seat = seats.remove( (int)(seats.size()*Math.random()));
		name = fn[(int)(Math.random()*fn.length)]+" "+ln[(int)(Math.random()*ln.length)];		
		int row = Integer.parseInt(seat.substring(0, seat.length()-1));
		double prob = Math.random();
		
		if(prob < 0.10)  	priority = SPECIAL;
		else if(prob < 0.25)priority = FREQUENT_FLIER;
		else 
		
		if( row<5 )	priority = FIRSTCLASS;
		else if( row<14)	priority = ROWS_5THRU13;
		else if( row<23)	priority = ROWS_14THRU22;
		else				priority = ROWS_23THRU31;
		//System.out.println(prob+": row"+row+": "+priority+": "+words[priority]+"\n");
		timeStamp = null;
		 
	}
	public static void setupSeats(){
		if(seats==null){
			String[] letters={"A","B","C","D","E","F"};
			seats = new ArrayList();
			//first class
			for(int i=1; i<=4; i++)
				for(int c=0; c<4; c++)
					seats.add(i+letters[c]);
			//coach
			for(int i=5; i<32; i++)
				for(int c=0; c<letters.length; c++)
					seats.add(i+letters[c]);

			
		}
	}
	public void stampTicket(){
		timeStamp = new Date(System.currentTimeMillis());
	}
	public int getPriority(){
		return priority;
	}
	public String getSeat(){return seat;}
	public int getRow(){
		return Integer.parseInt(seat.substring(0, seat.length()-1))-1;
	}
	
	public String toString(){
		return name+": "+words[priority]+": seat="+seat+": entered line @ "+timeStamp.getHours()+":"+timeStamp.getMinutes()+":"+timeStamp.getSeconds();
	}	
	
	
}

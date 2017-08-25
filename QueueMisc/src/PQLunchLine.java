

import java.util.PriorityQueue;

public class PQLunchLine {

	public static void main(String[] args) {
		PriorityQueue lunchline = new PriorityQueue();
		lunchline.add( new Student("Ima", "Dork", 10, 3.5));
		lunchline.add( new Student("Ura", "Moron", 10, 2.25));
		lunchline.add( new Student("Mean", "Thug", 10, 0.1));
		lunchline.add( new Student("Zach", "Morris",10,2.15));
		lunchline.add( new Student("Eigth","Grader", 10, 4.0));
		lunchline.add(new Student("Blake","Jaeger",10,4.625));
		
		while( !lunchline.isEmpty()){
			System.out.println(lunchline.remove());
		}
	}

}

import java.util.*;
public class SlotTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Reel ReelA = new Reel(1, 5, 5, 5);
		Reel ReelB = new Reel(1, 10, 9, 10);//makes reels and then slotmachine out of reels
		Reel ReelC = new Reel(2, 10, 6, 15);
		SlotMachine Slotty = new SlotMachine(ReelA, ReelB, ReelC);
		
		Scanner arno= new Scanner(System.in);// scanner and yes, i did in fact use arno AGAIN mwahahahahahahahhahahahahaha
		
		int count0=0, count1=0, count2=0, count5=0, count8=0, count25=0, sum, plays, played=0;//ints for count of each payout, sum of payout counts, amount of times they want to play, and ho0w many times they've play
		
		System.out.println("How many times do you want to play?");
			plays = arno.nextInt();// user input
			
		while(played<=plays){//loop for playing however many times
			 int winning = Slotty.play();
			System.out.println(Slotty+ "   You won $"+winning);
			
			if(winning==25)
				count25++;
			if(winning==8)
				count8++;
			if(winning==5)
				count5++;
			if(winning==2)
				count2++;
			if(winning==1)
				count1++;
			if(winning==0)
				count0++;
			}
		
		
		sum = count25+count8+count5+ count2+count1+count0;
		System.out.println("Probabilities:");
		System.out.println("P($25)= "+count25/sum );
		System.out.println("P($8)= "+count8/sum );
		System.out.println("P($5)= "+count5/sum );
		System.out.println("P($2)= "+count2/sum );
		System.out.println("P($1)= "+count1/sum );
		System.out.println("P($0)= "+count0/sum );
		
		
		
		
	}

}

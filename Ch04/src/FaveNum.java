import java.util.*;
public class FaveNum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner arno=new Scanner (System.in);
		double FaveNum;
		
		System.out.println("What's your favorite number?");
		FaveNum=arno.nextDouble();
		
		if(FaveNum%2==0)
			System.out.println("Wow, that's an even number!");
		else
			System.out.println("Wow, thats an odd number!");
		
		if(FaveNum<10)
			System.out.println("That number has 1 digit!");
		else
			System.out.println("That number has 2 digits!");
		
		if(FaveNum<36)
			System.out.println("That's less than my favorite number.");
		
		if(FaveNum==36)
			System.out.println("That's my favorite number too!");
		
		if(FaveNum>36)
			System.out.println("That's greater than my favorite number");
	}

}

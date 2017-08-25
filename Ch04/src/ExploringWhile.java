
public class ExploringWhile {

	public static void main(String[] args) {
		int die1, die2, sum=0;

		
		int count = 0;
		while( sum!=7 ){
			die1 = (int)(Math.random()*6)+1;
			die2 = (int)(Math.random()*6)+1;
			sum = die1+die2;
			System.out.println(sum);
			count+=1;
		}
		System.out.println("In the end, num is: "+count);
		
	}

}

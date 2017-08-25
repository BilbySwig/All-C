
public class Dicey {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int result1, result2, roll1, roll2, sum;
		
		roll1=(int)(Math.random()*6)+1;
		roll2=(int)(Math.random()*6)+1;
		result1=Math.min(roll1, roll2);
		result2=Math.max(roll1, roll2);
		sum= roll1+roll2;
		
		System.out.println("Your results are "+result1+" and "+result2);
		System.out.println("The sum is: "+sum);
		
		if(sum==7)
			System.out.println("You Rolled a seven, so you win! Yay!");
		else
			System.out.println("You lose!Booooooo!");
			
		if(roll1==roll2)
			System.out.println("Although you lost, you rolled doubles!Yay!");
	}

}

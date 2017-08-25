import java.util.*;
public class Cash_Register {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Variables/Scanners declared
		Scanner arno=new Scanner(System.in);
		double total=0, input, items;
		
		//asks how many items and in turn how many times the loop repeats
		System.out.println("How many items do you have?");
		items= arno.nextDouble();
		
		//repeatedly asks for price
		for(int i=1; i<=items; i++){
			System.out.println("Price: ");
			input=arno.nextDouble();
			total+=input;
		}
		
		//tells total price
		System.out.println("Your total price before taxes is $"+total);
		System.out.println("Your total price with taxes is $"+(total*1.06));
	}

}

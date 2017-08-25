import java.text.DecimalFormat;
import java.util.*;
public class Temperature {

	public static void main(String[] args){
		/*** play with / change things in the main only ***/
		Scanner input = new Scanner(System.in);
		double cel;
		double fah;
		System.out.println("Enter a temperature in celsius:");
		cel=input.nextDouble();
		fah = convert( cel, true);
		System.out.println(cel+"C= "+fah+"F");
		
		System.out.println("Enter a temperature in Fahrenheit:");
		fah=input.nextDouble();
		cel=convert(fah, false);
		System.out.println( fah + " F = "+cel+"C" );		
		
		for(cel=0;cel<=100;cel++){
			fah=convert(cel, true);
			System.out.println(cel+"C= "+fah+"F");
		}
	}//end main
	
	
	//temp = the temperature that you want to convert
	//true = this temp you gave me is IN CELSIUS currently
	// false = this temp you game me is NOT IN CELSIUS currently
	public static double convert( double temp, boolean inCelsius ){
		double answer=0;
		if( inCelsius )
			answer = 9.0/5*temp + 32;
		else
			answer = (temp-32)*5.0/9;
		
		//round the answer to the hundredth place
		double rounded = (int)Math.round(answer*100)/100.0;
		return rounded;
	}//end converter function
	
}//end class

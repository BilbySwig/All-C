import java.util.*;
public class PizzaPartay {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//scanners and variables
		double Smprice, Smsize, Smppi, Mdprice, Mdsize, Mdppi, Lgprice, Lgsize, Lgppi;
		Scanner arno = new Scanner(System.in);
		
		//gets info on small pizza and calculates price per square inch
		System.out.println("How much does the small pizza cost?");
		Smprice= arno.nextDouble();
		System.out.println("How large is a small pizza?");
		Smsize=arno.nextDouble();
		Smppi=unitPrice(Smsize, Smprice);
		
		//gets info on medium pizza and calculates price per square inch
		System.out.println("How much does the medium pizza cost?");
		Mdprice= arno.nextDouble();
		System.out.println("How large is a medium pizza?");
		Mdsize=arno.nextDouble();
		Mdppi=unitPrice(Mdsize, Mdprice);
		
		//gets info on large pizza and calculates price per square inch
		System.out.println("How much does the large pizza cost?");
		Lgprice= arno.nextDouble();
		System.out.println("How large is a large pizza?");
		Lgsize=arno.nextDouble();
		Lgppi=unitPrice(Lgsize, Lgprice);
		
		//the next 3 ifs tell the user which pizza to buy
		if(Math.min(Smppi, (Math.min(Mdppi, Lgppi)))==Smppi)
			System.out.println("Buy the SMALL!");
		
		if(Math.min(Smppi, (Math.min(Mdppi, Lgppi)))==Mdppi)
			System.out.println("Buy the MEDIUM!");
		
		if(Math.min(Smppi, (Math.min(Mdppi, Lgppi)))==Lgppi)
			System.out.println("Buy the LARGE!");
	}//end main
	
	/*Preconditions: price2 is the price of a pizza in USA dollars
	 * 				 diameter is the positive diameter of the pizza in inches
	 *Postconditions: Calculates price per square inch of your round pizza
	 * 
	 */
	public static double unitPrice(double diameter, double price2){
		double area= Math.PI*(.5*diameter)*(.5*diameter);
		return price2/area;
	}//end unitPrice
}//end class

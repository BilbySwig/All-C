
public class Fraction {
	//private member variables
	private int numerator;
	private int denominator;
	private int wholenumber;
	
	//constructors
	/*Preconditions: Makes a custom fraction/whole number: 3 ints need to be input
	 * Postconditions: num will be numerator, denom will be denominator, and mix will be the number in front if its a mixed number
	 */
	public Fraction(int mix, int num, int denom){
		numerator=num;
		denominator=denom;
		wholenumber= mix;
	}
	
	/*Preconditions: no inputs; default constructor
	 * Postconditions: makes a fraction of one half
	 */
	public Fraction(){this(0, 1, 2);}
	
	/*Preconditions: a fraction is input that you want a copy of
	 * Postconditions: will return a copy of the given fraction
	 */
	public Fraction(Fraction blah){
		numerator= blah.numerator;
		denominator= blah.denominator;
		wholenumber= blah.wholenumber;
	}
	
	/*Preconditions: a fraction is printed by FractionGame
	 * Postconditions: prints out fraction, including whole number in front if it has one and saying it's undefined if denominator=0
	 */
	public String toString(){
		String str;
		if(denominator!=0){
			if(wholenumber!=0)//includes whole number in front if one exists
				str= wholenumber+" + "+numerator+"/"+denominator;
			else
				str= numerator+"/"+denominator;
			if(numerator==0)
				str= wholenumber+" ";//if numerator is zero, simply prints wholenumber
		}
		else
			str="Undefined Fraction";//says undefined if the denominator is zero
		return str;
	}
	
	public int getNumerator(){return numerator;}
	public int getDenominator(){return denominator;}
	public int getWholenumber(){return wholenumber;}
	
	public void setNumerator(int num){numerator=num;}
	public void setDenominator(int num){denominator=num;}
	public void setWholenumber(int num){wholenumber=num;}
	
	/*Preconditions: a fraction is input that will be added to the fraction named first
	 *Postconditions: a fraction is returned that is the sum of the two fractions in the preconditions 
	 */
	public Fraction addTo(Fraction blah){
		int newdenom, newnum, newwhole;
		newdenom= blah.denominator*this.denominator;//find ECD
		newnum= blah.denominator*this.numerator + this.denominator*blah.numerator;//multiplies numerators by ECD and adds them
		newwhole= this.wholenumber+blah.wholenumber;//adds whole numbers in front of fractions
		Fraction newblah= new Fraction(newwhole, newnum, newdenom);//makes fraction that is sum of 2 original fractions
		newblah.Simplify();//simplifies sum
		return newblah;
	}
	/*Preconditions: none
	 * Postconditions: converts a fraction to its most simple form
	 */
	public void Simplify(){
		int min= Math.min(numerator, denominator), factor=1;//finds minimum between
		//if numerator is greater than denominator, adds 1 to whole number for every time denominator fully goes into numerator
		if(numerator>=denominator){
			wholenumber+= numerator/denominator;
			numerator= numerator-denominator*(int)numerator/denominator;//takes away from numerator for the amount added to wholenumber
		}
		//finds greatest common factor
			for(int i=1; i<=min; i++)
			if(numerator%i==0)
				if(denominator%i==0)
					factor=i;
		//divides numerator and denominator by greatest common factor	
		numerator/=factor;
		denominator/=factor;
	}
	/*Preconditions:factor is a fraction that you want to multiply by another fraction
	 * Postconditions: returns product of two fractions
	 */
	public Fraction multiplyBy(Fraction factor){
		int numproduct, denomproduct;
		if(wholenumber!=0){
			numerator+=wholenumber*denominator;//converts from mixed number to improper fraction
			factor.numerator=factor.wholenumber*factor.denominator;//same as line above, but for other fraction
		}
		numproduct=numerator*factor.numerator;//multiplies numerators
		denomproduct=denominator*factor.denominator;//multiplies denominators
		Fraction product= new Fraction(0, numproduct, denomproduct);//makes new fraction based on products of numerators and denominators
		product.Simplify();//simplifies new fraction
		return product;
	}
		
	public boolean isEqualTo(Fraction idontknow){
		Simplify();
		idontknow.Simplify();
		if(numerator==idontknow.numerator)
			if(denominator==idontknow.denominator)
				if(wholenumber==idontknow.wholenumber)
					return true;
				else
					return false;
			else
				return false;
		else
			return false;
	}
	
	
	
	
	
}












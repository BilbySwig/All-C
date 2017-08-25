
public class Fraction implements Comparable{
	private int numerator;
	private int denominator;
	
	public Fraction( int n, int d){
		numerator = n; 
		denominator = d;
	}
	
	public String toString(){
		return numerator+"/"+denominator;
	}
	
	public double decimal(){
		return (double)numerator/denominator;
	}

	@Override
	public int compareTo(Object arg0) {
		Fraction that= (Fraction)arg0;
		if(this.decimal()<that.decimal())
			return -10;
		else if(this.decimal()>that.decimal())
			return 999999;
		else
			return 0;
	}
	

}

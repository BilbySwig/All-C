import java.util.*;
public class QuadForm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//declare variables and scanners
		double a, b, c, x, x2;
		Scanner arno=new Scanner(System.in);
		
		//ask questions
		System.out.println("Enter a");
		a= arno.nextDouble();
		System.out.println("Enter b");
		b=arno.nextDouble();
		System.out.println("Enter c");
		c=arno.nextDouble();
		
		//calculate using answers
		x=(-b+Math.sqrt(b*b-4*a*c))/(2*a);
		x2=(-b-Math.sqrt(b*b-4*a*c))/(2*a);
		
		//print answers
		System.out.println("The first x-intercept is "+x);
		System.out.println("The second x-intercept is "+x2);
		//calculates x intercepts based on a, b, and c
	}

}

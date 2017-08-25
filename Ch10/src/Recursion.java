
public class Recursion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(sum(999));
		recfun(14);
	}

	public static int sum(int n){
		System.out.println("n = "+n);
		//STOPPING STATE
		if(n==1)
			return 1;
		//RECURSIVE STEP
		return n+sum(n-1);
	}//end sum
	
	public static void recfun(int n){
		if(n<=0)
			return;
		else{
			System.out.print(n);
			recfun(n-3);
			//System.out.print(n);
		}
	}
	
}

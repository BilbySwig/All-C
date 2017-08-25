
public class Summer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create an array
		int[] numbahs=new int[25];
		int sum=0;
		
		//build the array
		for(int i=0; i<numbahs.length;i++){
			numbahs[i]=(int)(Math.random()*6)+1;
			System.out.println(numbahs[i]);
		}
		
		//sum #s in array
		for(int j=0;j<numbahs.length;j++)
			sum+=numbahs[j];
		System.out.println("Sum= "+sum);//print sum
		
		//print out average
		System.out.println("Average= "+(double)sum/numbahs.length);
	}

}

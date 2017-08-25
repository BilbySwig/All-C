import java.util.*;//scanner privelleges
public class Practice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner arno=new Scanner(System.in);//create scanner
		
		String[] favwords= new String[5];//create array
		
		for(int j=0; j<favwords.length;j++){
			System.out.println("Enter one of your favorite words:");
			favwords[j]=arno.nextLine();
		}
		
		for(int i=favwords.length-1;i>=0;i--)
			System.out.println(favwords[i]);//print parts of array
	}

}

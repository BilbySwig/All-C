import java.util.*; //Scanner
public class Super_Max3000000 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//BY Blake Jaeger
		
		//Scanners and Variables Declared
		Scanner arno= new Scanner(System.in);
		String As=(" "), Bs=(" "), Cs=(" "), Ds=(" "), Fs=(" "), letter=("");
		double average=0, input;
		int count=0, grades;
		
		//Asks how many inputs there will be
		System.out.println("How many grades are there?");
		grades= arno.nextInt();
		
		if(grades>=0){
			for(int i=1; i<=grades; i++){
				System.out.println("Enter one of the grades:");
				input=arno.nextDouble();
			
				if(input<0)//subtracts amount of negative grades from amount of grades so that average isn't too small because of division by a large number
					count++;
				
				if(input>0){//edits star graph, adding 1 star for each of whatever grade the # is
					if(input<64.5)
						Fs+=("*");
					if(input>=64.5)
						if(input<73.5)
							Ds+=("*");
					if(input>=73.5)
						if(input<82.5)
							Cs+=("*");
					if(input>=82.5)
						if(input<91.5)
							Bs+=("*");
					if(input>=91.5)
						if(input<=100)
							As+=("*");
				}//ends large if statement
				if(input>0)
					if(input<=100)
						average+=input;
			}//ends for loop
		
			grades-=count;//these calculate the average
			average/=grades;
		
			if(average>0){//changes average letter grade based on average # grade
				letter=("F");
				if(average>=64.5)
					letter=("D");
				if(average>=73.5)
					letter=("C");
				if(average>=82.5)
					letter=("B");
				if(average>=91.5)
					letter=("A");
			}//ends large if
		
			//the next 2 ifs round the average to a whole #
			if((average-(int)average)<.5)
				average=(int)average;
			if((average-(int)average)>=.5)
				average=(int)average+1;
		
			//tells average grade and how many of each letter grade
			System.out.println("Your average is a "+average+" "+letter);
			System.out.println("A:"+As);
			System.out.println("B:"+Bs);
			System.out.println("C:"+Cs);
			System.out.println("D:"+Ds);
			System.out.println("F:"+Fs);
		}//ends all-consuming if statement
		else
			System.out.println("Come on! You can't have a negative number of grades! Try again!");
	}

}

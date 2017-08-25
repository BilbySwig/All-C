

public class SortingLab {
		
	public static void main(String[] args) {
		Fraction[] stuff={new Fraction(4,8),new Fraction(4,5),new Fraction(5,4),new Fraction(3,7),new Fraction(5,7)};
		
		//create the array
		String[] nums = {"i", "like", "to", "play", "games", "such", "as", "dungeons", "and", "arghgghghg"};
		//sort the array
		bubble(stuff);
		//display the results
		System.out.println("DONE!");
		print(stuff);
	}
	
	//Precondition: arr is a full array
	//Postcondition: arr is now sorted in ascending order
	//		(due to the execution of Selection Sort)
	public static void selection(Comparable[] arr){
		for(int reed=0;reed<arr.length-1;reed++){	
			int minWindow=reed;
			for(int minion=reed;minion<arr.length;minion++){
				if(arr[minion].compareTo(arr[minWindow])<0)
					minWindow=minion;
			}
				//swap
		Comparable tmp=arr[reed];
		arr[reed]=arr[minWindow];
		arr[minWindow]=tmp;
		print(arr);
		}
		
	}//end selection
	
	public static void bubble(Comparable[] arr){
		for(int reset=0;reset<arr.length;reset++){
			int swaps=0;
			for(int walker=0;walker<arr.length-1;walker++){
				if( arr[walker].compareTo(arr[walker+1])>0){	
					Comparable tmp=arr[walker+1];
					arr[walker+1]=arr[walker];//swap(3 lines)
					arr[walker]=tmp;
					swaps++;
				}	
			}//walker
		if(swaps==0)
			return;
		print(arr);	
		}//reset
	}
	
	
	
	
	//Precondition: arr is a full array
	//Postcondition: each element is printed on the same line (separated by a tab)
	public static void print(Comparable[] arr){
		for(int i=0; i< arr.length; i++)
			System.out.print(arr[i]+"     ");
		System.out.println();
	}
	


}

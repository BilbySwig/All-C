import javax.swing.*;
public class SearchingLab {

	public static void main(String[] args) {
		String[] myWords = {"apple","banana","blackberry","cherry","date","fig","grape","guava","orange","peach","raspberry","strawberry","tangerine","watermelon"};
		String findThis = JOptionPane.showInputDialog(null,"What do you want to find?");
		
		int linearResult = linearSearch( myWords, findThis);
		
		//search using the linear search algorithm
		if(linearResult==-1)
			JOptionPane.showMessageDialog(null, findThis+" was not found");
		else{
			JOptionPane.showMessageDialog(null,findThis+" was found at window "+linearResult+"!");
			JOptionPane.showMessageDialog(null, "Were you right? myWords["+linearResult+"] = "+myWords[linearResult]);
		}
		
		//2) Uncomment this after you know that linear search works!
		//search using binary search algorithm
		int binaryResult = binarySearch( myWords, findThis);
		
		if(binaryResult==-1)
			JOptionPane.showMessageDialog(null, findThis+" was not found");
		else{
			JOptionPane.showMessageDialog(null,findThis+" was found at window "+binaryResult+"!");
			JOptionPane.showMessageDialog(null, "Were you right? myWords["+binaryResult+"] = "+myWords[binaryResult]);
		}
		
	}
	
	/** 1) Write the linear search function **/
	public static int linearSearch( Comparable[] array, Comparable target){
		for(int i=0; i<array.length;i++)//use a loop
			if(array[i].equals(target))//compare each item to target
				return i;//if you find a match return the window number!
		return -1;
	}
	
	/** 2) After you know that the linear search function works, complete this function **/
	public static int binarySearch( Comparable[] array, Comparable target){
		int left = 0;
		int right = array.length-1;
		int mid;
		
		while(left<=right){ // <-- fix this condition :  while left is to the left of right
			//first calculate a new midpoint
			mid = (left+right)/2; //<--- take out the "=0" and change to actual calculation
			
			//this output for testing purposes:
			System.out.println("Looked in window "+mid+" and found "+array[mid]);
			
			if(array[mid].equals(target)){//<-- fix this condition: the thing i looked at is the same as the target!
				return mid;//return the window number!
			}
			else if(array[mid].compareTo(target)<0){ //<-- fix this condition: the thing i looked at is TOO SMALL
				left=mid+1;//adjust the correct endpoint
			}
			else{ //else:  the thing I looked at must have been TOO BIG
				right=mid-1;//adjust the correct endpoint
			}
		}
		//i must not have found it
		return -1;
	}

}

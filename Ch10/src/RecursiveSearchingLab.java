import javax.swing.*;
public class RecursiveSearchingLab {

	public static void main(String[] args) {
		String[] myWords = {"apple","banana","blackberry","cherry","date","fig","grape","guava","orange","peach","raspberry","strawberry","tangerine","watermelon"};
		String findThis = JOptionPane.showInputDialog(null,"What do you want to find?");
		
		int linearResult = linearSearchRecursive( myWords, 0, findThis);
		
		//search using the linear search algorithm
		if(linearResult==-1)
			JOptionPane.showMessageDialog(null, findThis+" was not found");
		else{
			JOptionPane.showMessageDialog(null,findThis+" was found at window "+linearResult+"!");
			JOptionPane.showMessageDialog(null, "Were you right? myWords["+linearResult+"] = "+myWords[linearResult]);
		}
		
		 //2) Uncomment this after you know that linear search works!
		//search using binary search algorithm
		int binaryResult = binarySearchRecursive( myWords, 0, myWords.length-1, findThis);
		
		if(binaryResult==-1)
			JOptionPane.showMessageDialog(null, findThis+" was not found");
		else{
			JOptionPane.showMessageDialog(null,findThis+" was found at window "+binaryResult+"!");
			JOptionPane.showMessageDialog(null, "Were you right? myWords["+binaryResult+"] = "+myWords[binaryResult]);
		}
		
	}
	
	/** 1) Write the linear search function **/
	public static int linearSearchRecursive( Comparable[] array, int lookInThisWindow, Comparable target){
		//Stopping State A:  lookInThisWindow is too big --> target must not be here!
		if(lookInThisWindow>array.length-1)
			return -1;
			
		//Stopping State B:  the thing in lookInThisWindow IS target!  I found it!
		if(array[lookInThisWindow].compareTo(target)==0)
			return lookInThisWindow;
		
		//Recursive Step:  call the function again, but with a parameter change!!
		return linearSearchRecursive(array, lookInThisWindow+1, target);
	}
	
	/** 2) After you know that the linear search function works, complete this function **/
	public static int binarySearchRecursive( Comparable[] array, int left, int right, Comparable target){
		//Stopping State A:  left and right are messed up.  The target must not be in the array!
		if(left>right)
			return -1;
		//Stopping State B:  calculate the midpoint & look in that window
		//                   if you have found the target, you can return!
		int mid=(left+right)/2;
		if(array[mid].compareTo(target)==0)
			return mid;
		
		//RECURSIVE STEP(S):  call the function again on either....
		//                    the left side of the array (left of mid)
		//              ~OR~  the right side of the array (right of mid)
		if(array[mid].compareTo(target)>0)
			return binarySearchRecursive(array, left, mid-1, target);
		else
			return binarySearchRecursive(array, mid+1, right, target);
			
		
	}

}

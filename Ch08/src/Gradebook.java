//package GradebookCutouts;

public class Gradebook {

	public static void main(String[] args) {
		int As=0, min=100,max=0, Fs=0; 
		int[] grades = new int[15];
		boolean keepinitonehunna=false;
		double avg=0; 
		
		//fill up the gradebook with random numbers
		for(int i=0; i<grades.length; i++)
			grades[i] = (int)(Math.random()*100)+1;
		
		System.out.println("*************");
		System.out.println("Your grades: ");
		//print them to the screen
		for(int i=0; i<grades.length; i++)
			System.out.println(grades[i]);
		System.out.println("*************");
		
		/******write code below this line*****/ 
		
		for(int i=0; i<grades.length;i++){//walk through array
			if(grades[i]>92)//counts As
				As++;
			
			if(grades[i]<65)//counts Fs
				Fs++;
				
			if(grades[i]<min)//finds smallest grade
				min=grades[i];
			
			if(grades[i]>max)//finds highest grade for range
				max=grades[i];
			
			keepinitonehunna=grades[i]==100;//finds whether there was a 100
			
			avg+=grades[i];//counts sum of grades 
		}
		
		System.out.println("As: "+As);//prints amount of As
		System.out.println("Fs: "+Fs);//prints amount of Fs
		System.out.println("The lowest grade was: "+min);//prints lowest grade
		System.out.println("The highest grade was: "+max);//prints highest grade
		
		if(keepinitonehunna==true)
			System.out.println("There was a 100!");//tells whether they got a 100
		else
			System.out.println("No perfect grades. Sorry.");
		
		System.out.println("The average grade was "+avg/grades.length);//prints average
		System.out.println("The range is "+ (max-min));//prints range
	}//end main (don't write below this)
}//end class

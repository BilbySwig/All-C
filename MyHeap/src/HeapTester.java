//package ForKids;



public class HeapTester {

	public static void main(String[] args) {
		try{			
			
			//testing the construct function
			Integer[] nums = {63, 12, 6, 43, 9, 10, 14, 55};
			Heap h0 = new Heap(nums);
			Heap.out.println(output());
			System.out.println("h0");
			Heap.out.println("h0");
			h0.print();			
		
			Integer[] nums1 = {1,2,3,4,5,6,7,8,9,10};
			Heap h1 = new Heap(nums1);
			System.out.println("h1");
			Heap.out.println("h1");
			h1.print();
			
			
			//Once you know the construct function works, 
			//   uncomment this section to test the insert function
			Heap h2 = new Heap(10);
			h2.insert(63);
			h2.insert(12);
			h2.insert(6);
			h2.insert(43);
			h2.insert(9);
			h2.insert(10);
			
			System.out.println("h2");
			Heap.out.println("h2");
			h2.print();
			h2.insert(14);
			h2.insert(55);
			h2.insert(100);
			System.out.println("h2 after insertions");
			h2.print();
			
			
			
			//once the you know the inser function works,			  
			//    uncomment this section to test the heapSort
			Comparable[] a = h2.heapSort();
			for(Comparable c: a){
				System.out.print(c+" ");
				Heap.out.print(c+" ");
			}
			
			
			Heap.out.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
		

	}
	
	public static String output(){
		char[] a = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		long tm = System.currentTimeMillis();
		String n =System.getProperty("user.name"); 
		int x = (int)(tm%10);
		for(int i=x-1; i>0; i--)
			x*=i;
		int x2 = 0;
		for(int i=0; i<a.length; i++)
			if(n.toLowerCase().charAt(0)==a[i] || n.toLowerCase().charAt(n.length()-1)==a[i]){
				x2+=i;		
			}
		
				
		return "~~~~~~~~~~~~~~~~~~~"+n+":"+x2+" : "+tm+":"+x+"~~~~~~~~~~~~~~~~~~~~~";
	}

}

//package complete;

public class OrderedListTester {

	public static void main(String[] args) {
		
		OrderedList bob = new OrderedList();
		bob.add("donut");//special case:  adding to empty list
		System.out.println(bob);
		
		bob.add("jalapeno");//special case:  largest element
		System.out.println(bob);
		
		bob.add("fig");
		bob.add("eggplant");
		bob.add("ice");
		bob.add("easymac");
		System.out.println(bob);
		
		bob.add("apple"); //special case: smallest element
		System.out.println(bob);
		
		bob.add("nachos");//special case: largest element
		System.out.println(bob);
		
	
		
	}

}

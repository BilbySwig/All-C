
public class CLLRemoveLastTester {


	public static void main(String[] args) {
		CircularLinkedList ccl = new CircularLinkedList();
		
		ccl.addLast("A");
		ccl.addLast("B");
		ccl.addLast("C");
		ccl.addLast("D");
		ccl.addLast("E");
		ccl.addLast("F");
		ccl.addLast("G");
		
		System.out.println(ccl);
		while( !ccl.isEmpty() ){
			System.out.println("REMOVING: "+ccl.removeLast());
			System.out.println("List is now: "+ccl);
		}

	}

}

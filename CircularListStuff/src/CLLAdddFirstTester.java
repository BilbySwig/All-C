
public class CLLAdddFirstTester {

	public static void main(String[] args) {
		CircularLinkedList listy =  new CircularLinkedList();
		listy.addFirst("Carrow");
		listy.addFirst("Snape");
		listy.addFirst("Umbridge");
		listy.addFirst("Moody");
		listy.addFirst("Lupin");
		listy.addFirst("Lockhart");
		listy.addFirst("Quirrell");
		
		System.out.println(listy);
		
		/*
		 * Output should be:
		 * Quirrell Lockhart Lupin Moody Umbridge Snape Carrow
		 */
	}

}

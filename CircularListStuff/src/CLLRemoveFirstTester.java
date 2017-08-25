
public class CLLRemoveFirstTester {

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
		for(int i=0; i<7; i++){
			listy.removeFirst();
			System.out.println(listy);
		}
		
		/*
		 *OUTPUT:
Quirrell Lockhart Lupin Moody Umbridge Snape Carrow
Lockhart Lupin Moody Umbridge Snape Carrow
Lupin Moody Umbridge Snape Carrow
Moody Umbridge Snape Carrow
Umbridge Snape Carrow
Snape Carrow
Carrow
empty
		 */

	}

}

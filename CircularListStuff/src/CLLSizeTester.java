
public class CLLSizeTester {


	public static void main(String[] args) {
		CircularLinkedList listy =  new CircularLinkedList();
		listy.addFirst("Carrow");
		System.out.println(listy.size());
		listy.addFirst("Snape");
		System.out.println(listy.size());
		listy.addFirst("Umbridge");
		System.out.println(listy.size());
		listy.addFirst("Moody");
		System.out.println(listy.size());
		listy.addFirst("Lupin");
		System.out.println(listy.size());
		listy.addFirst("Lockhart");
		System.out.println(listy.size());
		listy.addFirst("Quirrell");
		System.out.println(listy.size());
		while( listy.size()>0){
			listy.removeFirst();
			System.out.println(listy.size());
		}
			
		/*
		 * OUTPUT:
		 * 1
		 * 2
		 * 3
		 * 4
		 * 5
		 * 6
		 * 7
		 * 6
		 * 5
		 * 4
		 * 3
		 * 2
		 * 1
		 * 0
		 */

	}

}


public class getTester {

	public static void main(String[] args) {
		MyLinkedList hogwarts = new MyLinkedList();		
		hogwarts.addFirst("Snape");
		hogwarts.addFirst("Umbridge");
		hogwarts.addFirst("Moody");
		hogwarts.addFirst("Lupin");
		hogwarts.addFirst("Lockhart");
		hogwarts.addFirst("Quirrell");
		System.out.println( hogwarts.get(0));
		System.out.println( hogwarts.get(2));
		System.out.println( hogwarts.get(5));
		/*
		  Output should be:
		  	Quirrell
		 	Lupin
			Snape		
		 */
		 

	}

}

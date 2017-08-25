
public class setTester {

	public static void main(String[] args) {
		MyLinkedList hogwarts = new MyLinkedList();		
		hogwarts.addFirst("Snape");
		hogwarts.addFirst("Umbridge");
		hogwarts.addFirst("Moody");
		hogwarts.addFirst("Lupin");
		hogwarts.addFirst("Lockhart");
		hogwarts.addFirst("Quirrell");
		
		hogwarts.set(3, "Crouch");
		hogwarts.set(0, "Voldemort");
		hogwarts.set(5, "Half-Blood Prince");
		System.out.println(hogwarts);
		//output should be:
		//Voldemort Lockhart Lupin Crouch Umbridge Half-Blood Prince 

	}

}

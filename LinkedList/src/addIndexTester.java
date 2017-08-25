
public class addIndexTester {

	public static void main(String[] args) {
		MyLinkedList hogwarts = new MyLinkedList();		
		hogwarts.addFirst("Snape");
		hogwarts.addFirst("Umbridge");
		hogwarts.addFirst("Moody");
		hogwarts.addFirst("Lupin");
		hogwarts.addFirst("Lockhart");
		hogwarts.addFirst("Quirrell");
		hogwarts.add(0,"Merrythought");
		hogwarts.add(2,"Voldemort");
		hogwarts.add(6,"Crouch");
		System.out.println(hogwarts);
		//output should be: 
		//Merrythought Quirrell Voldemort Lockhart Lupin Moody Crouch Umbridge Snape 

		

	}

}

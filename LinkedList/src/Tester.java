
public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyLinkedList bob=new MyLinkedList();
		
		bob.addFirst("apples");
		System.out.println(bob+" "+bob.size());
		bob.addFirst("bananas");
		System.out.println(bob+" "+bob.size());
		bob.addFirst("cucumber");
		System.out.println(bob+" "+bob.size());
		bob.addFirst("danish");
		System.out.println(bob+" "+bob.size());
		while(!bob.isEmpty())
			System.out.println(bob.removeFirst());
		
		
		
		
	}

}

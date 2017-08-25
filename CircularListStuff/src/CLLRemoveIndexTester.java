
public class CLLRemoveIndexTester {

	public static void main(String[] args) {
		CircularLinkedList list = new CircularLinkedList();
		list.addLast("A");
		list.addLast("B");
		list.addLast("C");
		list.addLast("D");
		list.addLast("E");
		list.addLast("F");
		
		while( !list.isEmpty() ){
			int rand = (int)(Math.random()*list.size());
			System.out.println("REMOVING: "+list.remove(rand));
			System.out.println("LIST NOW: "+list);
			System.out.println("~~~~~~~~~~~~~~~");
		}

	}

}

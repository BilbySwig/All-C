
public abstract class TreeTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree larry=new BinarySearchTree();
		larry.insert("M");
		larry.insert("V");
		larry.insert("E");
		larry.insert("M");
		larry.insert("J");
		larry.insert("S");
		larry.insert("U");
		larry.insert("N");
		larry.insert("P");
		larry.preOrderPrint(larry.getRoot());
		System.out.println(larry);
		
		//setup for bobby
		BinarySearchTree bobby=new BinarySearchTree();
		bobby.insert(new Integer(16));
		bobby.insert(new Integer(20));
		bobby.insert(new Integer(18));
		bobby.insert(new Integer(1));
		bobby.insert(new Integer(7));
		bobby.insert(new Integer(28));
		bobby.insert(new Integer(22));
		bobby.insert(new Integer(48));
		bobby.insert(new Integer(17));
		bobby.insert(new Integer(29));
		
		//testing find
		System.out.println(larry.find("S"));
		System.out.println(larry.find("J"));
		System.out.println(larry.find("N"));
		System.out.println(larry.find("Z"));
		
		//testing recursive finding
		System.out.println(larry.recFind("S"));
		System.out.println(larry.recFind("J"));
		System.out.println(larry.recFind("N"));
		System.out.println(larry.recFind("Z"));
	
		//testing numElements, minElement and height for Strings
		System.out.println(larry.numElements(larry.getRoot()));
		System.out.println(larry.height(larry.getRoot()));
		System.out.println(larry.minElement(larry.getRoot()));
		
		System.out.println();
		
		//testing numElements, minElement, and height for ints
		System.out.println(bobby.numElements(bobby.getRoot()));
		System.out.println(bobby.height(bobby.getRoot()));
		System.out.println(bobby.minElement(bobby.getRoot()));
		bobby.preOrderPrint(bobby.getRoot());
		System.out.println();
		bobby.postOrderPrint(bobby.getRoot());
		System.out.println();
		bobby.inOrderPrint(bobby.getRoot());
	}

}

public class ListNode {
	private Object value;
	private ListNode next;
	
	public ListNode(Object v, ListNode n){
		value = v;
		next = n;
	}
	//Accessors
	public Object getValue(){
		return value;
	}
	public ListNode getNext(){
		return next;
	}
	//Mutators
	public void setValue(Object v){value =v;}
	public void setNext(ListNode n){next = n;}
}


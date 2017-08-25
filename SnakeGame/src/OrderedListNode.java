//package complete;

public class OrderedListNode {
	private Comparable value; //will probably be a ScoreRecord
	private OrderedListNode next;
	
	//CONSTRUCTORS
	public OrderedListNode(Comparable v, OrderedListNode n){
		value = v;
		next = n;
	}
	
	public OrderedListNode(Comparable v){
		value = v;
		next = null;
	}
	
	//accessors
	public Comparable getValue(){return value;}
	public OrderedListNode getNext(){return next;}
	
	//mutators
	public void setValue(Comparable v){value = v;}
	public void setNext(OrderedListNode n){ next = n;}
	
	public String toString(){
		return value.toString();
	}
	
}

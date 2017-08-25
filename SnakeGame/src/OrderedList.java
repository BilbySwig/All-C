//package forKids;

public class OrderedList {
	
	private OrderedListNode first;
	
	public OrderedList(){
		first = null;
	}
	
	public boolean isEmpty(){return first==null;}
	
	public void addFirst( Comparable newValue){
		first = new OrderedListNode( newValue, first);
	}
	
	/*
	 * Preconditions:  The elements that are currently in the list
	 *  (if there are any) are arranged in order
	 *  Postconditions:  A new OrderedListNode is created containing newValue
	 *  	This new node is inserted into the list into the appropriate place
	 *  	so that the list is STILL in order
	 *  	(you can decide if you want ascending or descending order) 
	 */
	
	//ADDS IN DESCENDING ORDER
	public void add(Comparable newValue){
		if(isEmpty()){
			addFirst(newValue);
			return;
		}//SPECIAL CASES: adds first if the list is empty or the added value is greater than the first
		if(newValue.compareTo(first.getValue())>0){
			addFirst(newValue);
			return;
		}
		OrderedListNode curr=first;
		while(curr.getNext()!=null && newValue.compareTo(curr.getNext().getValue())<0){
			curr=curr.getNext();//adds Objects in descending order
		}
		curr.setNext(new OrderedListNode(newValue,curr.getNext()));//actual adding
	}
	
	public String toString(){
		String ans="";
		OrderedListNode curr=first;
		while(curr!=null){
			ans+=curr.getValue()+"\n";// pretty way to display the list for the scoreboard
			curr = curr.getNext();
		}
		return ans;
	}
	public String toFileString(){
		String ans="";
		OrderedListNode curr=first;//formatting to put list in a file
		while(curr!=null){
			ans+=((ScoreRecord)curr.getValue()).formatForFile()+"\r\n";
			curr = curr.getNext();
		}
		return ans;
	}
	
}

import java.util.NoSuchElementException;

public class CircularLinkedList {
	private ListNode last;
	
	public CircularLinkedList(){
		last = null;
	}
	
	public boolean isEmpty(){return last==null;}
	
	public String toString(){
		if(isEmpty())
			return "empty";
		String str="";
		ListNode curr=last.getNext();
		while(curr!=last){
			str+=curr.getValue()+ " ";
			curr=curr.getNext();
		}
		str+=last.getValue();
		return str;
	}
	
	public void addFirst(Object toAdd){
		if(isEmpty()){//special case
			last=new ListNode(toAdd,null);
			last.setNext(last);
			return;
		}
		ListNode baby=new ListNode(toAdd,last.getNext());
		last.setNext(baby);
	}
	
	public Object removeFirst(){
		if(isEmpty())
			throw new NoSuchElementException("list is empty");
		ListNode first=last.getNext();
		if(last.getNext()==last)
			last=null;
		else	
			last.setNext(first.getNext());
		return first.getValue();
	}
	
	public int size(){
		if(isEmpty())
			return 0;
		int sz=0;
		ListNode curr=last.getNext();
		while(curr!=last){
			sz++;
			curr=curr.getNext();
		}
		sz++;
		return sz;
	}
	
	public Object get(int index){
		if(size()-1<index || index<0)
			throw new NoSuchElementException("invalid index");
		ListNode curr=last.getNext();
		for(int i=0;i<index;i++)
			curr=curr.getNext();
		return curr.getValue();
	}
	
	public void set(int index, Object thing){
		if(size()-1<index || index<0)
			throw new NoSuchElementException("invalid index");
		ListNode curr=last.getNext();
		for(int i=0; i<index;i++)
			curr=curr.getNext();
		curr.setValue(thing);
	}
	
	public void addLast(Object thing){
		if(isEmpty()){
			addFirst(thing);
			return;
		}
			ListNode blah=new ListNode(thing, last.getNext());
			last.setNext(blah);
			last=blah;
	}
	
	public Object removeLast(){
		if(size()==1)
			return removeFirst();
		ListNode curr=last.getNext();
		while(curr.getNext()!=last)
			curr=curr.getNext();
		ListNode death=last;
		curr.setNext(last.getNext());
		last=curr;
		return death.getValue();
	}
	
	public void add(int index,Object o){
		if(isEmpty() || index==0){
			addFirst(o);
			return;
		}
		if(index==size()){
			addLast(o);
			return;
		}
		ListNode curr=last.getNext();
		for(int i=0;i<index-1;i++)
			curr=curr.getNext();
		curr.setNext(new ListNode(o,curr.getNext()));
	}
	
	public Object remove(int index){
		if(index==size()-1)
			return removeLast();
		if(size()==1 || index==0)
			return removeFirst();
		ListNode curr=last.getNext();
		for(int i=0; i<index-1;i++)
			curr=curr.getNext();
		ListNode death=curr.getNext();
		curr.setNext(death.getNext());
		return death.getValue();
	}
	
}

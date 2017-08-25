import java.util.*;
public class MyLinkedList{// implements List{
	private ListNode firstNode;
	
	public MyLinkedList(){
		firstNode = null;
	}
	public boolean isEmpty() {return firstNode==null;}
//more code
		

	
	public Object removeFirst(){
		if(isEmpty())
			return null;
		Object ans=firstNode.getValue();
		firstNode=firstNode.getNext();
		return ans;
	}
	public void addFirst(Object newVal){
		ListNode newGuy=new ListNode(newVal,firstNode);
		firstNode=newGuy;
	}
	
	public String toString(){
		String ans="";
		ListNode curr=firstNode;
		while(curr!=null){
			ans+=curr.getValue()+ " ";
			curr=curr.getNext();
		}
		return ans;
	}
	
	public int size(){
		int ans=0;
		ListNode curr=firstNode;
		while(curr!=null){
			ans++;
			curr=curr.getNext();
		}
		return ans;
	}
	
	public Object get(int index){
		ListNode curr=firstNode;
		for(int i=0;i<index;i++)
			curr=curr.getNext();
		return curr.getValue();
			
	}
	
	public Object set(int index, Object thing){
		ListNode curr=firstNode;
		Object oldVal;
		for(int i=0;i<index;i++)
			curr=curr.getNext();
		oldVal=curr.getValue();
		curr.setValue(thing);
		return oldVal;
	}
	
	public void add(int index,Object thing){
		if(size()<index || index<0)
			throw new NoSuchElementException("invalid index");
		if(index==0){//SPECIAL Case
			addFirst(thing);
			return;
		}
		ListNode curr=firstNode;
		for(int i=0;i<(index-1);i++)
			curr=curr.getNext();
		curr.setNext(new ListNode(thing, curr.getNext()));	
	}
	public boolean add(Object thing){
		this.add(size(), thing);
		return true;
	}
	
	public Object remove(int index){
		if(index==0)
			return removeFirst();
		
		ListNode curr=firstNode;
		for(int i=0;i<index-1;i++)
			curr=curr.getNext();
		Object thing= curr.getNext().getValue();
		curr.setNext(curr.getNext().getNext());
		return thing;
	}
}

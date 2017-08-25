
import java.util.*;

public class MyTreeMap{
	private SetNode root;
	
	public MyTreeMap(){
		root = null;
	}
	public SetNode find(Comparable key){
		return null;
	}
	
	public void put(Comparable key, String value) {
		if(isEmpty()){
			root=new SetNode(key,value);
			return;
		}
		boolean movedRight=false;
		SetNode curr=root,prev=root;
		while(curr!=null){
			if(key.compareTo(curr.getKey())<0){
				prev=curr;
				curr=curr.getLeft();
				movedRight=false;
			}else if(key.compareTo(curr.getKey())>0){
				prev=curr;
				curr=curr.getRight();
				movedRight=true;
			}else{
				curr.getSet().add(value);
				return;
			}

		}//end while
		if(movedRight)
			prev.setRight(new SetNode(key, value));
		else if(!movedRight)
			prev.setLeft(new SetNode(key, value));

	}
	
	public Set<String> getFunc(Comparable key,SetNode r){
		if(r==null)
			return null;
		if(r.getKey().equals(key))
			return r.getSet();
		if(key.compareTo(r.getKey())<0)
			return getFunc(key,r.getLeft());
		else
			return getFunc(key,r.getRight());
	}
	
	public Set<String> get(Comparable key) {
		if(isEmpty())
			return null;
		return getFunc(key,root);
	}
	
	
	
	
	public boolean containsKey(Comparable key) {
		return (find(key)!=null);		
	}	
	public boolean isEmpty() {
		return root == null;
	}
	public ArrayList<Comparable> keyList() {
		//Set<Comparable> setty = new
		ArrayList<Comparable> list = new ArrayList();
		keyListHelper(root, list);
		return list;
	}
	public void keyListHelper(SetNode r, ArrayList l){
		if(r==null)
			return;
		keyListHelper(r.getRight(),l);
		l.add(r.getKey());
		keyListHelper(r.getLeft(),l);
	}
	public int size() {
		return 0;
	}
	public String toString(){
		return toStringHelp(root);
	}
	public String toStringHelp(SetNode r){
		if(r==null)
			return "\n";//             this---------------------------is actual printing
		return toStringHelp(r.getRight())+r.getKey()+": "+r.getSet()+toStringHelp(r.getLeft());//in order
	}


}

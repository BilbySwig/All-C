
import java.util.*;
public class CounterMap {
	private NumNode root;
	
	public CounterMap(){root = null;}
	
	public void put(Comparable key){
		if(isEmpty()){
			root=new NumNode(key);
			return;
		}
		boolean movedRight=false;
		NumNode curr=root,prev=root;
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
				curr.increment();
				return;
			}
		}//end while
		if(movedRight)
			prev.setRight(new NumNode(key));
		else
			prev.setLeft(new NumNode(key));

	}//end put
	
	public int getFunc(Comparable key,NumNode r){
		if(r==null)
			return -1;
		if(r.getKey().equals(key))
			return r.getCount();
		if(key.compareTo(r.getKey())<0)
			return getFunc(key,r.getLeft());
		else
			return getFunc(key,r.getRight());
	}
	
	public Integer get(Comparable k){
		return getFunc(k, root);
	}
	
	public ArrayList<Comparable> keyList(){
		ArrayList<Comparable> list = new ArrayList();
		keyHelper(root, list);
		return list;
	}
	public void keyHelper( NumNode r, ArrayList l){
		if(r==null)
			return;
		l.add(r.getKey());
		keyHelper(r.getLeft(),l);
		keyHelper(r.getRight(),l);
	}
	
	public MyTreeMap inversion(){
		MyTreeMap mtm = new MyTreeMap();
		inversionRecursion( root, mtm);
		return mtm;
	}
	public void inversionRecursion( NumNode r, MyTreeMap m){
		if(r==null)
			return;
		m.put(r.getCount(),(String)r.getKey() );
		inversionRecursion(r.getLeft(),m);
		inversionRecursion(r.getRight(),m);
	}
	public boolean isEmpty(){
		return root==null;
	}
}

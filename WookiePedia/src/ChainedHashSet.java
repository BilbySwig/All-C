
import java.util.*;

public class ChainedHashSet<Person> implements Set<Person>{
	private LinkedList<Person>[] buckets;
	private int logSz;
	
	//constructor
	public ChainedHashSet(){
		buckets = new LinkedList[10];//list of buckets
		logSz=0;//amount of stored elements
		for(int i=0; i<buckets.length; i++){
			buckets[i] = new LinkedList();//making it so stuff can be added to the buckets
		}
	}	
	
	//getting the index for placement based on hash code
	public int hashFunction( int hc ){
		return Math.abs(3*hc+2)%buckets.length;		
	}
	
	//just a simple loadfactor function
	public double loadFactor(){
		return (double)logSz/buckets.length;
	}
	
	public boolean add(Person c){		
		if(this.contains(c))//cant add duplicates
			return false;
		int code=c.hashCode();//getting hash code
		int bucket=this.hashFunction(code);//using hash code to place the person
		buckets[bucket].addFirst(c);
		logSz+=1;
		if(this.loadFactor()>1){
			//this.uglyPrint();                     This stuff is unnecessary, so i commented it out
			//System.out.println(this.loadFactor());	
			this.resize();
			//this.uglyPrint();
			//System.out.println(this.loadFactor());	
		}
		return true;
	}
	
	public void uglyPrint(){
		for(int i=0;i<buckets.length;i++){
			System.out.print("bucket "+i+":");//printing out set for use with testing
			for(int j=0;j<buckets[i].size();j++){
				System.out.print(" *");
			}
			System.out.println();
		}
			
	
	}	

	public boolean contains(Object o) {
		Person p = (Person)o;
		int bucket=this.hashFunction(p.hashCode());
		return buckets[bucket].contains(p);
	}	
	
	public boolean isEmpty() {		
		return logSz==0;
	}
	
	public int size() {
		return logSz;
	}
	
	public void resize(){
		LinkedList<Person>[] gonnaDie=buckets;//this is old buckets
		buckets=new LinkedList[buckets.length*2];//this is new buckets with 2x the size
		for(int i=0; i<buckets.length; i++)
			buckets[i] = new LinkedList();
		logSz=0;
		for(int i=0;i<gonnaDie.length;i++)
			for(Person p: gonnaDie[i])
				this.add(p);
	}	
		
	public Iterator iterator() {
		return new ChainedIterator(buckets);		
	}	
	
/**~~~~~~~~ Other stuff from the Set interface ~~~~~~~~~~~~~~~~~~~~**/
/**         we are too lazy to write this stuff    **/
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}
	public void clear() {
		// TODO Auto-generated method stub
	}
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}
}

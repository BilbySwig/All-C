//package ForKids;

import java.io.FileWriter;
import java.io.PrintWriter;


public class Heap {
	private Comparable[] data;
	private int sz;  //logical size
	
	public Heap(int maxsz){
		data = new Comparable[maxsz];
		sz = 0;
		setup();
	}
	public Heap(Comparable[] d){
		data = d;
		sz = d.length;
		setup();
		construct();
	}
	public Heap(){
		this(10);
	}
	public boolean isEmpty(){return sz==0;}

	/* ~~~~~~~~~~~~~Things for you to write ~~~~~~~~~~~~~~~*/
	
	/* Precondition: p is the index of the parent element/"node"
           Postcondition: returns the index where p's maximum child is located
			  return -1 if p has NO children  	
	*/
	public int indexOfMaxChild( int p ){
		//hint: remember a heap is a complete binary tree
		if(numChildren(p)==0)
			return -1;
		else if(numChildren(p)==1)
			return left(p);
		else if(data[left(p)].compareTo(data[right(p)])<0)
			return right(p);
		else
			return left(p);
	}
		
	
	/* Precondition:  p is the index of a "root node" who may be in violation of Heap property
			  ASSUME: BOTH the left and right subtrees of p ARE currently Heaps (not violating heap property)
	   Postcondition: the "tree" rooted at p is now a Heap (Heap property is not violated in p's tree or subtrees)
	*/
	public void restoreHeap(int p){
		if(numChildren(p)==0)
			return;
		int index=indexOfMaxChild(p);
		if(index>=0 && data[p].compareTo(data[index])<0)
			swap(p,index);
		restoreHeap(index);
	}

	//performs the bottom-up heap construction algorithm
	private void construct(){
		for(int i=sz-1;i>=0;i--)
			restoreHeap(i);
	}

	public boolean isHeap(int p){
		boolean result= true;
		for(int i=0;i<sz;i++)
			if(data[i+1]!=null && data[i].compareTo(data[i+1])>0)
				result=false;
		return result;
	}
		

	//inserts item
	//restores the heap property (using restoreHeap)
	public void insert(Comparable newbie){
		if(sz==data.length)
			resize();
		if(data.length>sz)
			data[sz]=newbie;
		sz+=1;
		int index=sz-1;
		while(parent(index)>=0){
			restoreHeap(parent(index));
			index=parent(index);
		}
	}

	// Precondition: this data structure is currently a heap 
	// Postcondition: data is now sorted in ascending order, and data is returned (Note: this data structure is NO LONGER a heap)
	public Comparable[] heapSort(){
		if(sz==0){
			return data;
		}
		Comparable temp=data[0];
		data[0]=data[sz-1];
		data[sz-1]=temp;
		
		sz-=1;
		
		int index=sz-1;
		while(parent(index)>=0){
			restoreHeap(parent(index));
			index=parent(index);
		}
	
		heapSort();
		return data;
	}

	/******   Some functions to help you out ******/
	private int parent(int x){
		if(x==0)
			return -1;
		return (x-1)/2;
	}
	private int left(int x){return 2*x+1;}
	private int right(int x){return 2*x+2;}
	
	private void swap(int me, int you){
		Comparable tmp = data[me];
		data[me] = data[you];
		data[you] = tmp;
	}

	public void print(){
		try{		

			for(int i=0; i<sz; i++){
				System.out.print(data[i]+" ");
				out.print(data[i]+" ");
			}
			System.out.println();
			out.println();			
			int i=0;
			while(i<sz){
				System.out.print(data[i]+": ");
				out.print(data[i]+": ");
				if(2*i+1<sz){
					System.out.print(data[2*i+1]+" ");
					out.print(data[2*i+1]+" ");
				}
				if(2*i+2<sz){
					System.out.print(data[2*i+2]+" ");
					out.print(data[2*i+2]+" ");
				}
				System.out.println();
				out.println();
				
				i++;
			}
		}
		catch(Exception ex){ ex.printStackTrace(); }
	}

	//helper function
	
	private void resize(){
		Comparable[] bigger = new Comparable[2*data.length];
		for(int i=0; i<data.length; i++)
			bigger[i] = data[i];
		data = bigger;
	}
	
	

	
	public void arrayprint(){
		for(int i=0; i<data.length; i++){
			System.out.print(data[i]+" ");
			out.print(data[i]+" ");
		}
		System.out.println();
		out.println();
	}
	

	
	public String toString(){
		String ans="";
		for(int i=0; i<sz; i++)
			ans+=data[i]+" ";
		return ans;
	}
	
	/*
	 * Preconditions: p is the index of an element
	 *    [in the array representation of your heap]
	 * Postconditions: returns true if the element at
	 *    index p is a "leaf" of the heap
	 */
	public boolean leaf(int p){
		if(2*p+1 >= sz)
			return true;
		else
			return false;
	}
	public int numChildren(int p){
		int count=0;
		if(2*p+1<sz)
			count++;
		if(2*p+2 < sz)
			count++;
		return count;
	}
	
	/*~~~~~~~~~~~~Don't bother stuff down here~~~~~~~~~~~~~~*/
	public static String FILENAME = "H:/heapTesterOutput.txt";
	public static FileWriter fw;
	public static PrintWriter out;	
	public static void setup(){
		if(fw == null){
			try{
				fw = new FileWriter(Heap.FILENAME);
				out = new PrintWriter(fw);
			}catch(Exception ex){ex.printStackTrace();}
		}
	}
}

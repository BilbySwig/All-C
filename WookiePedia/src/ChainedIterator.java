

import java.util.Iterator;
import java.util.LinkedList;

public class ChainedIterator<E> implements Iterator<E>{
	private LinkedList<E>[] data;
	int currList, currIndex;
	
	//constructor
	public ChainedIterator(LinkedList[] d){
		data = d;
		currList = 0;
		currIndex = 0;
	}
	
	public boolean hasNext() {
		int total=0, currCount;
		for(int i=0; i<currList; i++)
			total+=data[i].size();
		currCount = total+currIndex+1;
		if(currList < data.length)
			for(int i=currList; i<data.length; i++)
				total+=data[i].size();
		
		return currCount<=total;
	}

	
	public E next() {
		if( !hasNext())
			return null;
		while( currList<data.length && data[currList].size()==0 )
			currList++;
		E ans = data[currList].get(currIndex);
		currIndex++;
		if(currIndex >= data[currList].size()){			
			currIndex = 0;
			currList++;	
			while( currList<data.length && data[currList].size()==0 )
				currList++;			
		}
		return ans;
			
	}

	
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}

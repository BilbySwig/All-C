

import java.util.*;
public class AirportPQ /*implements Queue*/{
	//You do not have to use an ArrayList
	// other options:  array, Linked List, 2D Array, etc.
	private ArrayList<PlaneTicket> data;//list for tickets
	
	
	
	//CONSTRUCTOR
	public AirportPQ(){
		data= new ArrayList();
	}
	
	public boolean isEmpty() {		
		return data.isEmpty();//sees if data is empty
	}

	public int size() {
		return data.size();//gets data's size
	}
	
	public boolean add(PlaneTicket p) {
		p.stampTicket();//what time did they "get in line"?
		if(data.isEmpty()){//SPECIAL CASE: if its empty, just add without index
			data.add(p);
			return true;
		}
		
		int i=0;
		while(i<data.size() && data.get(i)!=null && p.getPriority()<data.get(i).getPriority())		
			i++;//find place to add in ticket
		if(i==data.size())//if its add the end, add without index
			data.add(p);
		else
			data.add(i, p);//if its in the middle, add at index i
		return true;
	}
	
	public PlaneTicket remove() {
		return data.remove(data.size()-1);//get rid of and return ticket at highest index, which is front of line
	}
	
	public PlaneTicket peek() {
		return data.get(data.size()-1);//return ticket at end of list
	}
	public String toString(){
		//return a string containing all of the people that are waiting in line
		//does NOT need to be in priority order (but it could)
		String str="";
		for(int i=data.size()-1;i>=0;i--)//add each ticket's toString to a really big string
			str+=data.get(i).toString()+"\n";
		return str;
	}

	

}

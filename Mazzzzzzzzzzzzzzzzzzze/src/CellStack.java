import java.util.ArrayList;
import java.util.EmptyStackException;


public class CellStack {
	private ArrayList<MazeCell> cells;
	
	public CellStack(){
		cells=new ArrayList<MazeCell>();
	}
	
	public boolean isEmpty(){
		return cells.get(0)==null;//if first window has nothing in it, no other windows do either
	}
	
	public MazeCell peek(){//returns value of "top" window, which is last window in cells
		if(isEmpty())//stop the function if the list is empty
			throw new EmptyStackException();
		return cells.get(cells.size()-1);
	}
	
	public MazeCell pop(){//return value of "top" window and removes it from cells
		if(cells.get(0)==null)
			throw new EmptyStackException();
		MazeCell temp= cells.get(cells.size()-1);//storage value so u can return value after removing from cells
		cells.remove(cells.size()-1);
		return temp;	
	}
	
	public void push(MazeCell toPush){//adds a MazeCel to the top of the stack
		cells.add(toPush);
	}
	
	public int size(){//standard function that gives size of the stack
		return cells.size();
	}
}


public class MyStack {
	private Object[] data;
	private int logSz;
	
	public MyStack(){
		data=new Object[100];
		logSz=0;
	}
	
	public Object peek(){
		return data[logSz-1];
	}
	
	public Object pop(){
		if(logSz==0)
			return null;
		Object thing=data[logSz-1];
		data[logSz-1]=null;
		logSz-=1;
		return thing;
	}
	
	public void push(Object newThing){
		data[logSz]=newThing;
		logSz+=1;
	}
	
	public boolean isEmpty(){
		return data[0]==null;
	}
	
	public int size(){
		return logSz;
	}
}

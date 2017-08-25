import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FinalLinkedListTester {

	public static void main(String[] args) {
		try{
			FileWriter file = new FileWriter("H:/UploadThisFile.txt");//"output.txt");
			PrintWriter out = new PrintWriter(file);

			MyLinkedList list = new MyLinkedList();
			String[] blah = {"apple","banana","coconut","donut","eggplant","fig","grape","huevos"};
			for(int i=0; i<blah.length/2; i++)
				list.add( blah[i] );
			out.println(list);
			out.println(output());
			System.out.println(list);
			System.out.println(output());

			int index=0;
			for(int i=blah.length/2; i<blah.length; i++){
				list.add(index, blah[i]);
				index+=2;
			}		
			out.println(list);
			out.println(output());
			System.out.println(list);
			System.out.println(output());

			for(int i=0; i<list.size(); i+=2)
				list.set(i , ((String)list.get(i)).toUpperCase());
			out.println(list);	
			out.println(output());
			System.out.println(list);
			System.out.println(output());

			for(int i=list.size()-1; i>0; i-=2){
				out.println("removing "+i);
				list.remove(i);
				out.println(list);
				System.out.println(list);
			}		
			out.println(output());
			System.out.println(output());
			while(!list.isEmpty()){
				out.println("removing: "+list.removeFirst());
				out.println(list);
				System.out.println(list);
			}
			System.out.println("size: "+list.size());
			out.println("size: "+list.size());
			out.close();
		}catch(FileNotFoundException x){
			System.out.println("Invalid Files Name");
			System.exit(0);
		}catch(IOException i){
			System.out.println("Can't read file");
			System.exit(0);
		}

	}
	
	public static String output(){
		char[] a = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		long tm = System.currentTimeMillis();
		String n =System.getProperty("user.name"); 
		int x = (int)(tm%10);
		for(int i=x-1; i>0; i--)
			x*=i;
		int x2 = 0;
		for(int i=0; i<a.length; i++)
			if(n.toLowerCase().charAt(0)==a[i] || n.toLowerCase().charAt(n.length()-1)==a[i]){
				x2+=i;		
			}
		
				
		return "~~~~~~~~~~~~~~~~~~~"+n+":"+x2+" : "+tm+":"+x+"~~~~~~~~~~~~~~~~~~~~~";
	}

}

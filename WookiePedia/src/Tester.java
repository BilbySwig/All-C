
import java.io.*;


public class Tester {

	public static void main(String[] args) {
		//hashCode test drive:
		String[] data1 = {"Chewbacca", "Kashyyyk","Wookie","Male","Co-Pilot"};
		Person p1 = new Person(data1);		
		System.out.println(p1.hashCode());
		
		String[] data2 = {"Chewbacca", "Kashyyyk","Wookie","Male","Co-Pilot"};
		Person p2 = new Person(data1);		
		System.out.println(p2.hashCode());
		
		ChainedHashSet set = new ChainedHashSet();
		fillFromFile(set);
		//set.uglyPrint();
		
		//testing to see if adding doesnt add duplicates
		String[] addTest={"Ben Quadinaros","Earth","Dog","Male","Pet"};//yes i know that this man is not a dog
		//System.out.println(set.add(new Person(addTest)));		//however, its more fun that way
	
		//testing resize
		set.uglyPrint();//print set
		System.out.println(set.loadFactor());//print set's load factor
		while(set.loadFactor()>1)//resize until load factor is desirable
			set.resize();
		set.uglyPrint();//print set again	
		System.out.println(set.loadFactor());//print new load factor
	}
	
	public static void fillFromFile(ChainedHashSet fillThis){
		try{

			FileReader reader = new FileReader(new File("./src/StarWarsInfo2.txt"));
			BufferedReader buff = new BufferedReader(reader);
			String line = buff.readLine(); //that's the lame intro line

			line = buff.readLine();
			while(line!=null){								
				Person p = new Person( line.split(":") );
				
				fillThis.add( p );
				
				line = buff.readLine();
			}//done reading
			


		}catch(FileNotFoundException x){
			System.out.println("Can't Find It");
			System.exit(0);
		}catch(IOException i){
			System.out.println("Can't read file");
			System.exit(0);
		}
		catch(Exception x){
			x.printStackTrace();
		}

	}//end fillFromFile

}

import java.util.*;
public class MapExplorer {

	public static void main(String[] args) {
		Map<Integer, String> myMap = new HashMap();
		
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		System.out.println("PUTTING:");
		System.out.println( myMap.put(1, "Prime") );
		System.out.println( myMap.put(8, "Prime") );
		System.out.println(myMap.put(13, "Prime") );
		System.out.println(myMap.put(5, "Prime") );
		System.out.println(myMap.put(6, "Prime") );
		System.out.println(myMap.put(4, "Prime") );
		System.out.println(myMap.put(12, "Prime") );
		System.out.println(myMap.put(25, "Prime") );
		System.out.println(myMap.put(9, "Prime") );
		System.out.println( myMap.put(1, "Neither") );
		
		System.out.println("\nBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		System.out.println("Other Functions");
		System.out.println("size is: "+ myMap.size() );
		System.out.println("containsKey 25: "+myMap.containsKey(25));
		System.out.println("containsKey \"Prime\": "+myMap.containsKey("Prime"));
		System.out.println("containsKey 15: "+myMap.containsKey(15));
		System.out.println("keyset: " + myMap.keySet() );
		System.out.println("myMap: "+myMap);
		
		
		System.out.println("\nCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
		for( Integer num : myMap.keySet() ){
			System.out.println("working on "+num);
			for(int div=2; div<num; div++){
				if( num%div==0){
					System.out.println( "\t"+div +" is a factor of "+num);
					System.out.println( "\t"+myMap.put( num, "Composite"));
				}//end if
			}//end div loop
		}//end for-each loop
		
		
		//pretty output:
		System.out.println("\nDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDd");
		System.out.println("Pretty Output:");
		for(Integer num : myMap.keySet() ){
			System.out.println( "key: "+num+" value="+myMap.get(num));
		}
		
		System.out.println("\nEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEe");
		System.out.println("????????????");
		System.out.println( myMap.get(5));
		System.out.println( myMap.get(10));

	}

}

import java.util.*;
public class SetPractice {
	public static void main(String[] args){
	Set<String> words=new HashSet();
	System.out.println(words.add("how"));
	System.out.println(words.add("much"));
	System.out.println(words.add("wood"));
	System.out.println(words.add("would"));
	System.out.println(words.add("a"));
	System.out.println(words.add("wood"));
	System.out.println(words.add("chuck"));
	System.out.println(words.add("..."));
	
	System.out.println(words.size());
	System.out.println(words);
	System.out.println(words.contains("chuck"));
	System.out.println(words.remove("chuck"));
	System.out.println(words.remove("eggplant"));
	System.out.println(words.size());
	System.out.println(words);
	}
}

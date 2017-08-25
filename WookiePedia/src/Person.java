
public class Person implements Comparable{
	private String[] info;
	public final static int NAME=0, HOME=1, SPECIES=2, GENDER=3, JOB=4;
	
	public Person(){
		info = new String[4];
		for(int i=0; i<4;i++)
			info[i]="default";
	}
	public Person(String[] stuff){
		info = new String[5];
		for(int i=0; i<5; i++)
			info[i]=stuff[i];
	}
	public String get(int piece){return info[piece];}
	public String[] getArray(){return info;}
	public String toString(){
		String s ="";
		for(int i=0; i<info.length; i++)
			s+= info[i]+":";
		return s.substring(0,s.length()-1); //cut the last : off of the string
	}
	
	public int hashCode(){//uses the hashCode of String on the persons name
		return this.info[NAME].hashCode();
	}
	public int compareTo(Object other){//compares persons by name
		Person guy=(Person)other;
		return this.info[NAME].compareTo(guy.info[NAME]);
	}
	public boolean equals(Object other){//tells u if they are the same by whether they have the same name
		Person guy=(Person)other;
		return this.info[NAME].equals(guy.info[NAME]);
	}
}

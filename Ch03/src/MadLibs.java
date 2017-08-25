import java.util.*;//needed for Scanner
public class MadLibs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner arno=new Scanner(System.in);
		String pluralNoun1, pluralNoun2, pluralNoun3, color, adjective;
		System.out.println("Type a plural noun:");
		pluralNoun1=arno.nextLine();
		System.out.println("Type another plural noun:");
		pluralNoun2=arno.nextLine();
		System.out.println("Type one more plural noun:");
		pluralNoun3=arno.nextLine();
		System.out.println("Type a color:");
		color=arno.nextLine();
		System.out.println("Type an adjective:");
		adjective=arno.nextLine();
		System.out.println(pluralNoun1+" are "+color);
		System.out.println(pluralNoun2+" are blue ");
		System.out.println(pluralNoun3+" are "+adjective+",");
		System.out.println("and so are you!");
	}

}

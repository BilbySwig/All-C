
public class Bank {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int MrReed;
		String OtherMrReed;
		
		Account arno= new Account("Arno", 123, 9001, .25);
		Account Sebastian= new Account("Sebastian", 5000, 9002, .26);
		Account Phelps= new Account("Herr Phelps", 111);
		Account Twinzy= new Account(Sebastian);
		
		System.out.println(arno.getName()+" has $"+arno.getBalance());
		System.out.println(Sebastian.getName()+" has $"+Sebastian.getBalance());
		System.out.println(Sebastian);
		System.out.println(Phelps);
		System.out.println(Twinzy);
	}

}

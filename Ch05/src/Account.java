//training manual for all Accounts
public class Account {
	//private member variables, things account knows
	private int acctNum;
	private String name;
	private double balance;
	private double rate;

	//CONSTRUCTOR, birthing function
	//1) name of function== name of class
	//2) no return type
	public Account(String nm, int n, double b, double r){
		name=nm;
		acctNum=n;
		balance=b;
		rate = r;
	}
	//Default constructor- takes no parameters
	public Account(){
		this("none", 0, 0, 0);
	}
	
	//another constructor
	public Account(String nm, int aN){
		name=nm;
		acctNum=aN;
		balance=100;
		rate=.05;
	}
	
	//COPY CONSTRUCTOR
	public Account(Account that){
		name= that.name;
		acctNum= that.acctNum;
		balance= that.balance;
		rate= that.rate;
	}
	
	//member functions, things Account can do
	public void deposit(double amt){balance+=amt;}
	public void withdraw(double amt){balance-=amt;}
	public void addInterest(){balance+=balance*rate;}
	
	//ACCESSORS, let them ask for values
	public int getAcctNum(){return acctNum;}
	public String getName(){return name;}
	public double getBalance(){return balance;}
	public double getRate(){return rate;}
	
	//MUTATORS, let ppl change things
	public void setAcctNum(int newVal){acctNum=newVal;}
	public void setName(String newVal){name= newVal;}
	public void setBalance(double newVal){balance= newVal;}
	public void setRate(double newVal){rate= newVal;}
	
	//toString function- teaches Account how to become a string
	
	public String toString(){
		String str;
		str= name+ "("+acctNum+")";
		str+= " $"+balance+" @ "+rate;
		return str;
	}
}

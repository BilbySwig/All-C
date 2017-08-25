
public class SlotMachine {
	private Reel Reel1;
	private Reel Reel2;
	private Reel Reel3;
	
	//default SlotMachine constructor with 3 default Reels
	public SlotMachine(){
		Reel1= new Reel();
		Reel2= new Reel();
		Reel3= new Reel();
	}
	
	//constructor for custom SlotMachine with custom reels input by me instructed by Mr. Reed
	public SlotMachine(Reel blah1, Reel blah2, Reel blah3){
		Reel1= new Reel(blah1);
		Reel2= new Reel(blah2);
		Reel3= new Reel(blah3);
	}
	
	/*Preconditions: none
	 * Postconditions: payout amount is returned based on what the reels land on
	 */
	public int play(){
		Reel1.Spin();
		Reel2.Spin();//spins each reel
		Reel3.Spin();
		
		char show1=Reel1.getShowing(), show2=Reel2.getShowing(), show3=Reel3.getShowing();//shortens names of what i have to type to say whats showing

		if(show1=='P' && show2=='P' && show3=='P')
			return 25;//3 pikas= $25
		else if(show1=='W' && show2=='W' && show3=='W')
			return 5;//$5 for exactly 3 wartortles
		else if((show1=='W' && show2=='W') || (show2=='W' && show3=='W') || (show1=='W' && show3=='W'))
			return 2;//$2 for exactly 2 wartortles
		else if(show1=='W' || show2=='W' || show3=='W')
			return 1;//$1 for exactly 1 wartortle
		else if(show1=='C' && show2=='C' && show3=='C')
			return 8;//$8 for 3 charmanders
		else
			return 0;
	}
	
	//toString
	public String toString(){
		return Reel1.toString()+" "+ Reel2.toString()+ " "+ Reel3.toString();// Uses toStrings of each Reel
	}
}

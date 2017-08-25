
public class Reel {
	private int numpikachu;
	private int numwartortle;
	private int numcharmander;
	private int numslugma;
	private char showing;
	
	/*Preconditions: input 4 numbers, numpika, numwar, numchar, and numslug
	 * Postconditions: makes a reel with the specified number of each picture
	 */
	public Reel(int numpika, int numwar, int numchar, int numslug){
	numpikachu=numpika;
	numwartortle=numwar;
	numcharmander=numchar;
	numslugma=numslug;
	showing='P';
	}
	
	/*Preconditions: none
	 * Postconditions: makes a reel with one of each pokemon
	 */
	public Reel(){this(1, 1, 1, 1);}
	
	/*Preconditions: a reel is input that is to be copied
	 * Postconditions: a new reel is made that is a copy of the input
	 */
	public Reel(Reel blah){this(blah.numpikachu, blah.numwartortle, blah.numcharmander, blah.numslugma);}
	
	/*Preconditions:none
	 * Postconditions: changes pokemon showing through window
	 */
	public void Spin(){//NOTE 1= Pikachu 2=wartortle 3=charmander 4=slugma         <-----------------------codes for showing
		int spin=(int)(Math.random()*(numpikachu+numwartortle+numcharmander+numslugma-1)+1);
		 if(spin>0 && spin<=numpikachu)
			showing='P';//from zero to numpikachu shows pikachu
		 if(spin>numpikachu && spin<=(numwartortle+numpikachu))
			showing='W';//from numpikachu to numpikachu+numwartortle shows wartortle
		 if(spin>(numwartortle+numpikachu) && spin<= (numwartortle+numpikachu+numcharmander))
			showing='C';//from numpikachu+numwartortle to numpikachu+numwartortle+numcharmander shows charmander
		 if(spin>(numwartortle+numpikachu+numcharmander)&& spin<= (numwartortle+numpikachu+numcharmander+numslugma))
			showing='S'; //from numpikachu+numwartortle+numcharmander to numpikachu+numwartortle+numcharmander+numslugma shows slugma 
	}
	
	//returns which pokemon is showing
	public char getShowing(){return showing;}
	
	//transforms reel into a string when it gets told to print
	public String toString(){
		if(showing=='P')
			return "PIKACHU";
		else if(showing=='W')
			return "WARTORTLE";
		else if(showing=='C')
			return "CHARMANDER";
		else if(showing=='S')
			return "SLUGMA";
		else
			return "You shouldn't be seeing this";
	}
	
	
}

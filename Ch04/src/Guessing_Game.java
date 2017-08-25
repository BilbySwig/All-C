import java.util.*;
public class Guessing_Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner arno= new Scanner (System.in);
		int input, fav, count=0;


		fav=(int)((Math.random())*100)+1;//randomly generates the computer's favorite

		System.out.println("Guess my favorite number.");
		input=arno.nextInt();


		while(input!=fav){//makes a loop so that it repeats until they guess correct
			count++;
			System.out.println("Guess: "+input);
			if(input<fav){//if they guess too small, this tells them so

				System.out.println("That's too small, try again.");
				System.out.println("You have "+(10-count)+" guesses left!");
				input=arno.nextInt();
			}

			if(input>fav){//if they guess too big, this tells them so			
				System.out.println("That's too large, try again!");	
				System.out.println("You have "+(10-count)+" guesses left!");
				input=arno.nextInt();
			}

			if(input==fav){//if they guess correct, this congratulates them

				System.out.println("That's it! Congratulations!!!!!!!!!!!!!!!!You got it after "+count+" guesses!!!!!!");
			}

			//ends loop if user guesses too many times
			if(count==10){
				System.out.println("You guessed too many times! You LOSE!!!!!!!");
				break;
			}
		}
	}
}

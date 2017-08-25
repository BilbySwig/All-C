//package PigLatinNEW;

public class PigLatinTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//test out your isVowel function
		System.out.println("THESE SHOULD BE VOWELS");
		System.out.println( Translator.isVowel('a') );
		System.out.println( Translator.isVowel('E') );
		System.out.println( Translator.isVowel('i') );
		System.out.println( Translator.isVowel('O') );
		System.out.println( Translator.isVowel('u') );
		System.out.println( Translator.isVowel('y') );
		System.out.println("THESE SHOULD NOT BE VOWELS");
		System.out.println( Translator.isVowel('r') );
		System.out.println( Translator.isVowel('s') );
		System.out.println( Translator.isVowel('t') );
		System.out.println( Translator.isVowel('l') );
		System.out.println( Translator.isVowel('n') );
		
		
		//then test out your firstVowelIndex function
		System.out.println("TESTING firstVowelIndex: ");
		System.out.println( Translator.firstVowelIndex("word") );
		System.out.println( Translator.firstVowelIndex("school") );
		System.out.println( Translator.firstVowelIndex("apple") );
		System.out.println( Translator.firstVowelIndex("yack") );//should return 1!
		
		//Now test your parser
		System.out.println("PARSER TESTING");
		//should print this:  [luke, i, am, your, father]
		System.out.println( Translator.parser("luke i am your father") );
		//should print this:  [may, the, force, be, with, you]
		System.out.println( Translator.parser("may the force be with you") );
		//should print this:  [i, have, a, bad, feeling, about, this]
		System.out.println( Translator.parser("i have a bad feeling about this") );
		
		
		//Test your toPigLatin for different flavors of words	
		System.out.println("TRYING TRANSLATION:");
		System.out.println( Translator.toPigLatin("eagles eat apples inside igloos") );
		System.out.println( Translator.toPigLatin("cars can roll") );
		System.out.println( Translator.toPigLatin("yyyyowza yawn you yarn") );
		
	}

}

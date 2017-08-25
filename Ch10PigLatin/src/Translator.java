//package PigLatinNEW;
import java.util.*;
public class Translator {	
	

	/* Pre-Condition: phrase is an English phrase
	 * Post-Condition: The piglatinish translation is returned
	 */
	public static String toPigLatin(String phrase){ 
		phrase = phrase.trim(); //toss out extra spaces
		ArrayList<String> words=parser(phrase);//an ArrayList of the things that need to be translated
		int index=0;//variable for index of first vowel
		String firstpart, secondpart;//Strings for parts of the piglating version
		for(int i=0;i<words.size();i++){//for loop to go through words and change each String
			index=firstVowelIndex(words.get(i));//variable for index of first vowel	
			if(index>=0){
				if(words.get(i).charAt(0)=='y' || words.get(i).charAt(0)=='Y')			
					index=firstVowelIndex(words.get(i).substring(1))+1;//changes index for special case where word begins with y
				firstpart=words.get(i).substring(index);//firstpart will be the part after first vowel, including it
				secondpart=words.get(i).substring(0,index);//secondpart will be part before first vowel
				if(index>0)
					words.set(i,firstpart+"-"+secondpart+"ay");//sets word to piglatin version if word starts with a consonant
				else if(index==0)
					words.set(i,firstpart+"-YAY");//piglatin version with vowel as first letter
			}		
		}
		phrase="";//clear out phrase, which will be filled
		for(String tobepig:words)//goes through words, placing each String inside of phrase, except it's been translated
			phrase+=tobepig+" ";
		return phrase;
	}//end toPigLatin

	/******** the toEnglish function is REQUIRED for MSTC *********/
	/********  it is bonus for AP students ************************/
	/* Pre-Condition: phrase is an Pig Latin phrase
	 * Post-Condition: The English translation is returned
	 */
	public static String toEnglish(String phrase){
		phrase = phrase.trim(); //toss out extra spaces
		ArrayList<String> words=parser(phrase);
		String firstpart,secondpart;
		for(int i=0;i<words.size();i++){				
			secondpart=words.get(i).substring(0,words.get(i).indexOf("-"));
			firstpart=words.get(i).substring((words.get(i)).indexOf("-")+1,words.get(i).length()-2);
			if(words.get(i).charAt(words.get(i).indexOf("-")+1)=='Y')
				firstpart="";
				words.set(i,firstpart+secondpart);
		}
		phrase="";
		for(String word:words)
			phrase=phrase + word + " ";
			return phrase;
	}	
	
	
	public static ArrayList<String> parser( String sentence ){
		ArrayList<String> theList = new ArrayList();
		putFirstWordInList( sentence, theList ); //kick off the recursive party
		return theList;
	}

	//HELPER FUNCTIONS
	/* Preconditions: sent is a sentence
	 * Postconditions: returns an ArrayList where each
	 *  spot contains single word of the sentence*/
	public static void putFirstWordInList(String sentence, ArrayList<String> wordList){
		if(sentence.length()==0)//Stopping state for if no more words are there
			return;
		if(sentence.indexOf(" ")<0){//2 situations for adding words to wordList
			wordList.add(sentence.substring(0,sentence.length()));//1) last word in sentence; also another stopping state
			return;
		}
		else//2)any other word, adds it to wordList
			wordList.add(sentence.substring(0,sentence.indexOf(" ")));
		sentence=sentence.substring(sentence.indexOf(" ")+1);//shortens sentence so next recursion will get next word in sentence
		putFirstWordInList(sentence,wordList);//recursive step, causes next word in sentence to be added to wordList
	
	}


	/* Preconditions: word is a single word (no spaces)
	 * Postconditions: the index of the first vowel
	 * 		is returned (vowels = a,e,i,o,u,A,E,I,O,U). 
	 */
	public static int firstVowelIndex(String word){
		for(int i=0;i<word.length();i++)
			if(isVowel(word.charAt(i)))
				return i;
		return -1;
	}

	/* Preconditions: c is a character (ex:'b')
	 * Postconditions: returns true if c is a vowel returns false 
	 *                 otherwise
	 */
	/**WRITE THIS FIRST!**/
	public static boolean isVowel(char c){
		char[] vowels={'a','e','i','o','u','y','A','E','I','O','U','Y'};//list of vowels to avoid huge if statement
		for(int i=0; i<vowels.length;i++)//goes through list of all vowels
			if(c==vowels[i]){
				return true;//if c is equal to one of them, return true
			} 
		return false;//if c was found to not equal any of the vowels, return false
		}


}

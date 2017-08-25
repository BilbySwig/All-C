import java.util.ArrayList;


public class SongCollection {
	private Song[] songs;
	private int logSize;
	
	//constructor
	public SongCollection(){
		songs=new Song[1000];
		logSize=0;
	}
	
	//adds song to the collection
	public void addSong(Song added){
		songs[logSize++]=added;
	}
	
	/*Preconditions:none
	 * Postconditions: an arraylist containing all songs in the collection is returned
	 */
	public ArrayList<Song> allSongs(){
		ArrayList<Song> allofthem=new ArrayList();//birth of arraylist
		for(int i=0; i<logSize;i++)//loop to fill arraylist with songs
			allofthem.add(songs[i]);
		return allofthem;//returns arraylist
	}
	
	
	 /* Preconditions:none
	 * Postconditions: returns an ArrayList full of the songs that cost the lowest available price
	 */
	public ArrayList<Song> bargainBin(){
		ArrayList<Song> cheep=new ArrayList();
		double min= 100;
		
		for(int i=0;i<logSize;i++)//loop to find the minimum price
			if(songs[i].getPrice()<=min)
				min=songs[i].getPrice();
		
		for(int i=0;i<logSize;i++)//loop to add all songs that cost minimum price to the ArrayList
			if(songs[i].getPrice()==min)
				cheep.add(songs[i]);
		return cheep;	
	}
	
	/*Preconditions:artist is the desired artist whose songs the user wants to see
	 * 	Postconditions: returns an arraylist full of Songs with that artist 
	 */
	public ArrayList<Song> searchByArtist(String artist){
		ArrayList<Song> byaperson= new ArrayList();
		for(int i=0;i<logSize;i++)
			if(songs[i].getArtist().equals(artist))//if a Song is by the input artist, it will be added to what is returned
				byaperson.add(songs[i]);
		return byaperson;
	}
		
	/*Preconditions:album is the name of the desired album the user wants to see songs from
	 * Postconditions:inanalbum is returned, it is an ArrayList full of all available Songs from that album
	 */
	public ArrayList<Song> searchByAlbum(String album){//******NOTE: literally same as searchByArtist with different variables and a few different commands because its album
		ArrayList<Song> inanalbum= new ArrayList();
		for(int i=0;i<logSize;i++)
			if(songs[i].getAlbum().equals(album))//if a Song is by the input album, it will be added to what is returned
				inanalbum.add(songs[i]);
		return inanalbum;
	}
	
	/*Preconditions:genre is a String containing the genre from which the user wants to hear music
	 * Postconditions:returns percent of songs available in that genre by dividing the total in that genre by the logical size
	 */
	public double genrePercentage(String genre){
		double totalingenre=0;
		for(int i=0;i<logSize;i++)
			if(songs[i].getGenre().equals(genre))//loop that adds up amount of songs in specified genre
				totalingenre++;
		return (totalingenre/logSize)*100;
	}
	
	public String toString(){
		String str="";
		for(int i=0;i<logSize;i++)
			str+=songs[i].toString()+"\n \n";
		return str;
	}
}

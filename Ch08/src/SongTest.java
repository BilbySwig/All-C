
public class SongTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SongCollection arno=new SongCollection();
		Song test=new Song("blah", "blah","blah","blah", 100);
		Song test2=new Song("blah1", "blah1","blah1","blah1", 100);
		arno.addSong(test);
		arno.addSong(test2);
		System.out.println(arno);
	
	}

}


public class Song {
	private String Title, artistName, album, genre;
	private double price;
	
	//constructor
	public Song(String n,String aN,String a,String g, double p){
		Title=n;
		artistName=aN;
		album=a;
		genre=g;
		price=p;
	}
	
	//accessors
	public String getTitle(){return Title;}
	public String getArtist(){return artistName;}
	public String getAlbum(){return album;}
	public String getGenre(){return genre;}
	public double getPrice(){return price;}
	
	//mutators
	public void setTitle(String T){Title=T;}
	public void setArtist(String A){artistName=A;}
	public void setAlbum(String A){album=A;}
	public void setGenre(String G){genre=G;}
	public void setPrice(double P){price=P;}
	
	//toString
	public String toString(){
		String str=Title+": $"+price+"\n"+artistName+"\n"+album+", "+genre;
		return str;
	}
	//toArray
	public String[] toArray(){
		String[] toArray={Title, artistName, album, genre,""+price};
		return toArray;
	}
	
	
	
}

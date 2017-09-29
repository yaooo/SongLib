package compare;

public class Node {
	private String ArtistName;
	private String SongName;
	private String Album ;
	private int Year;
	public Node Next;
	
	public Node(String sn, String an){
		ArtistName=an;
		SongName=sn;
		Album="";
	}
	public Node(String sn, String an,String ab) {
		ArtistName=an;
		SongName=sn;
		Album=ab;
	}
	public Node(String sn, String an,String ab, int y) {
		ArtistName=an;
		SongName=sn;
		Album=ab;
		Year =y;
	}
	public Node(String sn, String an, int y) {
		ArtistName=an;
		SongName=sn;
		Year =y;
		Album="";
	}
	public Node() {}
	public void UpdateArtist(String a) {
		ArtistName=a;
	}
	public void UpdateSong(String a) {
		SongName=a;
	}
	public void UpdateAlbum(String a) {
		Album=a;
	}
	public void UpdateYear(int a) {
		Year=a;
	}
	public String GetArtist(){
		return ArtistName;
	}
	public String GetSong() {
		return SongName;
	}
	public String GetAlbum() {
		return Album;
	}
	public int GetYear() {
		return Year;
	}
}

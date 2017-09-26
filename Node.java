package compare;

public class Node {
	String ArtistName;
	String SongName;
	String Album ;
	int Year;
	Node Next;
	
	public Node(String an, String sn){
		ArtistName=an;
		SongName=sn;
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

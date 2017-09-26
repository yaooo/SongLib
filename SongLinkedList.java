package compare;

public class SongLinkedList {
	Node head;
	public SongLinkedList() {
		head=null;
	}
	
	public void AddNode(String an, String sn) {
			if(head==null) {
				head= new Node(an,sn);
				head.Next=null;
			}
			else {
				if(sn.compareToIgnoreCase(head.GetSong())<0) {
					Node Insert=new Node(an,sn);
					Insert.Next=head;
					head=Insert;
				}
				else{
					int inserted=0;
					Node ptr=head;
					ptr=ptr.Next;
					Node prevptr=head;
					while(ptr.Next!=null) {
						if(sn.compareToIgnoreCase(ptr.GetSong())<0) {
							Node Insert=new Node(an,sn);
							Insert.Next=ptr;
							prevptr.Next=Insert;
							inserted=1;
							break;
						}
						ptr=ptr.Next;
						prevptr=prevptr.Next;
					}
					if(sn.compareToIgnoreCase(ptr.GetSong())<0 && inserted==0) {
						Node Insert=new Node(an,sn);
						Insert.Next=ptr;
						prevptr.Next=Insert;
					}
					if(sn.compareToIgnoreCase(ptr.GetSong())>0 && inserted==0) {
						Node Insert=new Node(an,sn);
						Insert.Next=null;
						ptr.Next=Insert;
						
						
					}
					
				}
				/*else {
					Node ptr=head;
					while(ptr.Next!=null) {
						ptr=ptr.Next;
					}
					Node Insert= new Node(an,sn);
					Insert.Next=null;
					ptr.Next=Insert;
			   }*/
			}
	}
	public void PrintList() {
		Node ptr=head;
		while(ptr!=null) {
			System.out.println(ptr.GetArtist() +" "+ ptr.GetSong());
			ptr=ptr.Next;
		}
	}
	public static void main(String[] args) {
		SongLinkedList List= new SongLinkedList();
		
	}

}


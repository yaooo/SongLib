package compare;

public class SongLinkedList {
	Node head;
	public SongLinkedList() {
		head=null;
	}
	
	public void AddNode(String sn, String an) {
			//If the head is null we add the starting node
			if(head==null) {
				head= new Node(sn,an);
				head.Next=null;
			}
			else {
				//if the song inserted is alphabetically lower than our first we insert it first
				if(sn.compareToIgnoreCase(head.GetSong())<0) {
					Node Insert=new Node(sn,an);
					Insert.Next=head;
					head=Insert;
				}
				//If the first song names are equal we sort by artist names instead
				else if(sn.compareToIgnoreCase(head.GetSong())==0) {
					//If the artist name is alphabetically lower
					if(an.compareToIgnoreCase(head.GetArtist())<0) {
						Node Insert=new Node(sn,an);
						Insert.Next=head;
						head=Insert;
					}
					//if there is only one song and it is alphabetically higher we insert it at the end.
					else if(head.Next==null) {
						Node Insert=new Node(sn,an);
						head.Next=Insert;
					}
					//if it is alphabetically higher and the head is the only duplicate song we insert it after the head
					else if(an.compareToIgnoreCase(head.GetArtist())>0 && sn.compareToIgnoreCase(head.Next.GetSong())!=0) {
						Node ptr=head.Next;
						InsertBefore(head,ptr,sn,an);
					}
					//Dealing with inserting a song with many duplicate names
					else if(an.compareToIgnoreCase(head.GetArtist())>0 && sn.compareToIgnoreCase(head.Next.GetSong())==0) {
						Node ptr=head.Next;
						Node prevptr=head;
						int inserted=0;
						//if the artist name is in the middle
						while(ptr.Next!=null && sn.compareToIgnoreCase(ptr.GetSong())==0) {
							if(an.compareToIgnoreCase(ptr.GetArtist())<0) {
								InsertBefore(prevptr,ptr,sn,an);
								inserted=1;
								break;
							}
							ptr=ptr.Next;
							prevptr=prevptr.Next;
						}
						//if the artist name is at the end of the list
						if(ptr.Next==null && inserted!=1) {
							InsertAfter(ptr,sn,an);
						}
						//if the artist name is in the middle of duplicate names
						else if(ptr.Next!=null && inserted!=1) {
							InsertBefore(prevptr,ptr,sn,an);
						}
					}
				}
				else{
					int inserted=0;
					Node ptr=head;
					ptr=ptr.Next;
					Node prevptr=head;
					//if the head is the only song currently in the list we decide where to insert the new song
					if(prevptr==head && ptr.Next==null){
						if(sn.compareToIgnoreCase(ptr.GetSong())<0) {
							InsertBefore(prevptr, ptr, sn, an);
							inserted=1;
						}
						if(sn.compareToIgnoreCase(ptr.GetSong())>0) {
							InsertAfter(ptr,sn,an);
							inserted=1;
						}
					}
					else {
						//Inserting the new song in the middle of the list
						while(ptr.Next!=null) {
							if(sn.compareToIgnoreCase(ptr.GetSong())<0) {
								InsertBefore(prevptr,ptr,sn,an);
								inserted=1;
								break;
							}
							//If the song name is the same we go alphabetically by artist
							else if(sn.compareToIgnoreCase(ptr.GetSong())==0) {
								//If the song is the alphabetically lowest 
								if(an.compareToIgnoreCase(ptr.GetArtist())<0) {
									InsertBefore(prevptr,ptr,sn,an);
									inserted=1;
									break;
								}
								else if(ptr.Next==null && an.compareToIgnoreCase(ptr.GetArtist())>0) {
									InsertAfter(ptr,sn,an);
									inserted=1;
									break;
								}
								//If there is only one comparison to be done and it comes after the other duplicate song
								else if(an.compareToIgnoreCase(ptr.GetArtist())>0 && sn.compareToIgnoreCase(ptr.Next.GetSong())!=0) {
									ptr=ptr.Next;
									prevptr=prevptr.Next;
									InsertBefore(prevptr,ptr,sn,an);
									inserted=1;
									break;
								}
								else if(an.compareToIgnoreCase(ptr.GetArtist())>0 && sn.compareToIgnoreCase(ptr.Next.GetSong())==0) {
									Node ptr2=ptr.Next;
									Node prevptr2=ptr;
									//If the song is inserted in the middle
									while(ptr2.Next!=null && sn.compareToIgnoreCase(ptr2.GetSong())==0 && inserted!=1) {
										if(an.compareToIgnoreCase(ptr2.GetArtist())<0) {
											InsertBefore(prevptr2,ptr2,sn,an);
											inserted=1;
											break;
										}
										ptr2=ptr2.Next;
										prevptr2=prevptr2.Next;
									}
									//If the song is inserted last
									if(ptr2.Next==null && inserted!=1) {
										InsertAfter(ptr2,sn,an);
										inserted=1;
										break;
									}
									//If the song is inserted in the middle of the names
									else if(ptr2.Next!=null && inserted!=1) {
										InsertBefore(prevptr2,ptr2,sn,an);
										inserted=1;
										break;
									}

								}
								
							}
							ptr=ptr.Next;
							prevptr=prevptr.Next;
						}
					}
					
					//If the song is last and also a duplicate
					if(sn.compareToIgnoreCase(ptr.GetSong())==0 && inserted==0) {
						if(an.compareToIgnoreCase(ptr.GetArtist())<0) {
							InsertBefore(prevptr,ptr,sn,an);
						}
						else if(an.compareToIgnoreCase(ptr.GetArtist())>0) {
							InsertAfter(ptr,sn,an);
						}
					}
					//if the song is the second to last in the list alphabetically
					else if(sn.compareToIgnoreCase(ptr.GetSong())<0 && inserted==0) {
						InsertBefore(prevptr,ptr,sn,an);
					}
					//if the song is the last song alphabetically 
					else if(sn.compareToIgnoreCase(ptr.GetSong())>0 && inserted==0) {
						InsertAfter(ptr,sn,an);
					}
					
				}
				
			}
	}
	public void PrintList() {
		Node ptr=head;
		while(ptr!=null) {
			System.out.print(ptr.GetSong() +" "+ ptr.GetArtist());
			System.out.print(" -> ");
			ptr=ptr.Next;
		}
		System.out.println("");
	}
	public void InsertBefore(Node prev, Node curr, String sn, String an) {
		Node Insert = new Node(sn,an);
		Insert.Next=curr;
		prev.Next=Insert;
		
	}
	public void InsertAfter(Node curr, String sn, String an) {
		Node Insert=new Node(sn,an);
		curr.Next=Insert;
		Insert.Next=null;
	}
	
	public static void main(String[] args) {
		SongLinkedList List= new SongLinkedList();
	}

}


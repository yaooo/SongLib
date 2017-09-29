package compare;

import java.io.*;

public class SongLinkedList {
	public Node head;
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
					if(prevptr==head && prevptr.Next==null){
						if(sn.compareToIgnoreCase(prevptr.GetSong())<0) {
							Node Insert= new Node(sn, an);
							head.Next=Insert;
							Insert.Next= null;
							inserted=1;
						}
						if(sn.compareToIgnoreCase(prevptr.GetSong())>0) {
							Node Insert= new Node(sn,an);
							head.Next=Insert;
							Insert.Next=null;
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
					if(inserted==0 && sn.compareToIgnoreCase(ptr.GetSong())==0) {
						if(an.compareToIgnoreCase(ptr.GetArtist())<0) {
							InsertBefore(prevptr,ptr,sn,an);
						}
						else if(an.compareToIgnoreCase(ptr.GetArtist())>0) {
							InsertAfter(ptr,sn,an);
						}
					}
					//if the song is the second to last in the list alphabetically
					else if(inserted==0 && sn.compareToIgnoreCase(ptr.GetSong())<0) {
						InsertBefore(prevptr,ptr,sn,an);
					}
					//if the song is the last song alphabetically 
					else if(inserted==0 && sn.compareToIgnoreCase(ptr.GetSong())>0) {
						InsertAfter(ptr,sn,an);
					}
					
				}
				
			}
	}
	public void AddNode(String sn, String an,String ab) {
		//If the head is null we add the starting node
		if(head==null) {
			head= new Node(sn,an,ab);
			head.Next=null;
		}
		else {
			//if the song inserted is alphabetically lower than our first we insert it first
			if(sn.compareToIgnoreCase(head.GetSong())<0) {
				Node Insert=new Node(sn,an,ab);
				Insert.Next=head;
				head=Insert;
			}
			//If the first song names are equal we sort by artist names instead
			else if(sn.compareToIgnoreCase(head.GetSong())==0) {
				//If the artist name is alphabetically lower
				if(an.compareToIgnoreCase(head.GetArtist())<0) {
					Node Insert=new Node(sn,an,ab);
					Insert.Next=head;
					head=Insert;
				}
				//if there is only one song and it is alphabetically higher we insert it at the end.
				else if(head.Next==null) {
					Node Insert=new Node(sn,an,ab);
					head.Next=Insert;
				}
				//if it is alphabetically higher and the head is the only duplicate song we insert it after the head
				else if(an.compareToIgnoreCase(head.GetArtist())>0 && sn.compareToIgnoreCase(head.Next.GetSong())!=0) {
					Node ptr=head.Next;
					InsertBefore(head,ptr,sn,an,ab);
				}
				//Dealing with inserting a song with many duplicate names
				else if(an.compareToIgnoreCase(head.GetArtist())>0 && sn.compareToIgnoreCase(head.Next.GetSong())==0) {
					Node ptr=head.Next;
					Node prevptr=head;
					int inserted=0;
					//if the artist name is in the middle
					while(ptr.Next!=null && sn.compareToIgnoreCase(ptr.GetSong())==0) {
						if(an.compareToIgnoreCase(ptr.GetArtist())<0) {
							InsertBefore(prevptr,ptr,sn,an,ab);
							inserted=1;
							break;
						}
						ptr=ptr.Next;
						prevptr=prevptr.Next;
					}
					//if the artist name is at the end of the list
					if(ptr.Next==null && inserted!=1) {
						InsertAfter(ptr,sn,an,ab);
					}
					//if the artist name is in the middle of duplicate names
					else if(ptr.Next!=null && inserted!=1) {
						InsertBefore(prevptr,ptr,sn,an,ab);
					}
				}
			}
			else{
				int inserted=0;
				Node ptr=head;
				ptr=ptr.Next;
				Node prevptr=head;
				//if the head is the only song currently in the list we decide where to insert the new song
				if(prevptr==head && prevptr.Next==null){
					if(sn.compareToIgnoreCase(prevptr.GetSong())<0) {
						Node Insert=new Node(sn,an,ab);
						head.Next=Insert;
						Insert.Next=null;
						inserted=1;
					}
					if(sn.compareToIgnoreCase(prevptr.GetSong())>0) {
						Node Insert=new Node(sn,an,ab);
						head.Next=Insert;
						Insert.Next=null;
						inserted=1;
					}
				}
				else {
					//Inserting the new song in the middle of the list
					while(ptr.Next!=null) {
						if(sn.compareToIgnoreCase(ptr.GetSong())<0) {
							InsertBefore(prevptr,ptr,sn,an,ab);
							inserted=1;
							break;
						}
						//If the song name is the same we go alphabetically by artist
						else if(sn.compareToIgnoreCase(ptr.GetSong())==0) {
							//If the song is the alphabetically lowest 
							if(an.compareToIgnoreCase(ptr.GetArtist())<0) {
								InsertBefore(prevptr,ptr,sn,an,ab);
								inserted=1;
								break;
							}
							else if(ptr.Next==null && an.compareToIgnoreCase(ptr.GetArtist())>0) {
								InsertAfter(ptr,sn,an,ab);
								inserted=1;
								break;
							}
							//If there is only one comparison to be done and it comes after the other duplicate song
							else if(an.compareToIgnoreCase(ptr.GetArtist())>0 && sn.compareToIgnoreCase(ptr.Next.GetSong())!=0) {
								ptr=ptr.Next;
								prevptr=prevptr.Next;
								InsertBefore(prevptr,ptr,sn,an,ab);
								inserted=1;
								break;
							}
							else if(an.compareToIgnoreCase(ptr.GetArtist())>0 && sn.compareToIgnoreCase(ptr.Next.GetSong())==0) {
								Node ptr2=ptr.Next;
								Node prevptr2=ptr;
								//If the song is inserted in the middle
								while(ptr2.Next!=null && sn.compareToIgnoreCase(ptr2.GetSong())==0 && inserted!=1) {
									if(an.compareToIgnoreCase(ptr2.GetArtist())<0) {
										InsertBefore(prevptr2,ptr2,sn,an,ab);
										inserted=1;
										break;
									}
									ptr2=ptr2.Next;
									prevptr2=prevptr2.Next;
								}
								//If the song is inserted last
								if(ptr2.Next==null && inserted!=1) {
									InsertAfter(ptr2,sn,an,ab);
									inserted=1;
									break;
								}
								//If the song is inserted in the middle of the names
								else if(ptr2.Next!=null && inserted!=1) {
									InsertBefore(prevptr2,ptr2,sn,an,ab);
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
				if(inserted==0 && sn.compareToIgnoreCase(ptr.GetSong())==0) {
					if(an.compareToIgnoreCase(ptr.GetArtist())<0) {
						InsertBefore(prevptr,ptr,sn,an,ab);
					}
					else if(an.compareToIgnoreCase(ptr.GetArtist())>0) {
						InsertAfter(ptr,sn,an,ab);
					}
				}
				//if the song is the second to last in the list alphabetically
				else if(inserted==0 && sn.compareToIgnoreCase(ptr.GetSong())<0) {
					InsertBefore(prevptr,ptr,sn,an,ab);
				}
				//if the song is the last song alphabetically 
				else if(inserted==0 && sn.compareToIgnoreCase(ptr.GetSong())>0) {
					InsertAfter(ptr,sn,an,ab);
				}
				
			}
			
		}
	}
	public void AddNode(String sn, String an,String ab,int y) {
		//If the head is null we add the starting node
		if(head==null) {
			head= new Node(sn,an,ab,y);
			head.Next=null;
		}
		else {
			//if the song inserted is alphabetically lower than our first we insert it first
			if(sn.compareToIgnoreCase(head.GetSong())<0) {
				Node Insert=new Node(sn,an,ab,y);
				Insert.Next=head;
				head=Insert;
			}
			//If the first song names are equal we sort by artist names instead
			else if(sn.compareToIgnoreCase(head.GetSong())==0) {
				//If the artist name is alphabetically lower
				if(an.compareToIgnoreCase(head.GetArtist())<0) {
					Node Insert=new Node(sn,an,ab,y);
					Insert.Next=head;
					head=Insert;
				}
				//if there is only one song and it is alphabetically higher we insert it at the end.
				else if(head.Next==null) {
					Node Insert=new Node(sn,an,ab,y);
					head.Next=Insert;
				}
				//if it is alphabetically higher and the head is the only duplicate song we insert it after the head
				else if(an.compareToIgnoreCase(head.GetArtist())>0 && sn.compareToIgnoreCase(head.Next.GetSong())!=0) {
					Node ptr=head.Next;
					InsertBefore(head,ptr,sn,an,ab,y);
				}
				//Dealing with inserting a song with many duplicate names
				else if(an.compareToIgnoreCase(head.GetArtist())>0 && sn.compareToIgnoreCase(head.Next.GetSong())==0) {
					Node ptr=head.Next;
					Node prevptr=head;
					int inserted=0;
					//if the artist name is in the middle
					while(ptr.Next!=null && sn.compareToIgnoreCase(ptr.GetSong())==0) {
						if(an.compareToIgnoreCase(ptr.GetArtist())<0) {
							InsertBefore(prevptr,ptr,sn,an,ab,y);
							inserted=1;
							break;
						}
						ptr=ptr.Next;
						prevptr=prevptr.Next;
					}
					//if the artist name is at the end of the list
					if(ptr.Next==null && inserted!=1) {
						InsertAfter(ptr,sn,an,ab,y);
					}
					//if the artist name is in the middle of duplicate names
					else if(ptr.Next!=null && inserted!=1) {
						InsertBefore(prevptr,ptr,sn,an,ab,y);
					}
				}
			}
			else{
				int inserted=0;
				Node ptr=head;
				ptr=ptr.Next;
				Node prevptr=head;
				//if the head is the only song currently in the list we decide where to insert the new song
				if(prevptr==head && prevptr.Next==null){
					if(sn.compareToIgnoreCase(prevptr.GetSong())<0) {
						Node Insert=new Node(sn,an,ab,y);
						head.Next=Insert;
						Insert.Next=null;
						inserted=1;
					}
					if(sn.compareToIgnoreCase(prevptr.GetSong())>0) {
						Node Insert= new Node(sn,an,ab,y);
						head.Next=Insert;
						Insert.Next=null;
						inserted=1;
					}
				}
				else {
					//Inserting the new song in the middle of the list
					while(ptr.Next!=null) {
						if(sn.compareToIgnoreCase(ptr.GetSong())<0) {
							InsertBefore(prevptr,ptr,sn,an,ab,y);
							inserted=1;
							break;
						}
						//If the song name is the same we go alphabetically by artist
						else if(sn.compareToIgnoreCase(ptr.GetSong())==0) {
							//If the song is the alphabetically lowest 
							if(an.compareToIgnoreCase(ptr.GetArtist())<0) {
								InsertBefore(prevptr,ptr,sn,an,ab,y);
								inserted=1;
								break;
							}
							else if(ptr.Next==null && an.compareToIgnoreCase(ptr.GetArtist())>0) {
								InsertAfter(ptr,sn,an,ab,y);
								inserted=1;
								break;
							}
							//If there is only one comparison to be done and it comes after the other duplicate song
							else if(an.compareToIgnoreCase(ptr.GetArtist())>0 && sn.compareToIgnoreCase(ptr.Next.GetSong())!=0) {
								ptr=ptr.Next;
								prevptr=prevptr.Next;
								InsertBefore(prevptr,ptr,sn,an,ab,y);
								inserted=1;
								break;
							}
							else if(an.compareToIgnoreCase(ptr.GetArtist())>0 && sn.compareToIgnoreCase(ptr.Next.GetSong())==0) {
								Node ptr2=ptr.Next;
								Node prevptr2=ptr;
								//If the song is inserted in the middle
								while(ptr2.Next!=null && sn.compareToIgnoreCase(ptr2.GetSong())==0 && inserted!=1) {
									if(an.compareToIgnoreCase(ptr2.GetArtist())<0) {
										InsertBefore(prevptr2,ptr2,sn,an,ab,y);
										inserted=1;
										break;
									}
									ptr2=ptr2.Next;
									prevptr2=prevptr2.Next;
								}
								//If the song is inserted last
								if(ptr2.Next==null && inserted!=1) {
									InsertAfter(ptr2,sn,an,ab,y);
									inserted=1;
									break;
								}
								//If the song is inserted in the middle of the names
								else if(ptr2.Next!=null && inserted!=1) {
									InsertBefore(prevptr2,ptr2,sn,an,ab,y);
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
				if(inserted==0 &&sn.compareToIgnoreCase(ptr.GetSong())==0) {
					if(an.compareToIgnoreCase(ptr.GetArtist())<0) {
						InsertBefore(prevptr,ptr,sn,an,ab,y);
					}
					else if(an.compareToIgnoreCase(ptr.GetArtist())>0) {
						InsertAfter(ptr,sn,an,ab,y);
					}
				}
				//if the song is the second to last in the list alphabetically
				else if(inserted==0 && sn.compareToIgnoreCase(ptr.GetSong())<0 ) {
					InsertBefore(prevptr,ptr,sn,an,ab,y);
				}
				//if the song is the last song alphabetically 
				else if(inserted==0 && sn.compareToIgnoreCase(ptr.GetSong())>0) {
					InsertAfter(ptr,sn,an,ab,y);
				}
				
			}
			
		}
	}
	public void AddNode(String sn, String an,int y) {
		//If the head is null we add the starting node
		if(head==null) {
			head= new Node(sn,an,y);
			head.Next=null;
		}
		else {
			//if the song inserted is alphabetically lower than our first we insert it first
			if(sn.compareToIgnoreCase(head.GetSong())<0) {
				Node Insert=new Node(sn,an,y);
				Insert.Next=head;
				head=Insert;
			}
			//If the first song names are equal we sort by artist names instead
			else if(sn.compareToIgnoreCase(head.GetSong())==0) {
				//If the artist name is alphabetically lower
				if(an.compareToIgnoreCase(head.GetArtist())<0) {
					Node Insert=new Node(sn,an,y);
					Insert.Next=head;
					head=Insert;
				}
				//if there is only one song and it is alphabetically higher we insert it at the end.
				else if(head.Next==null) {
					Node Insert=new Node(sn,an,y);
					head.Next=Insert;
				}
				//if it is alphabetically higher and the head is the only duplicate song we insert it after the head
				else if(an.compareToIgnoreCase(head.GetArtist())>0 && sn.compareToIgnoreCase(head.Next.GetSong())!=0) {
					Node ptr=head.Next;
					InsertBefore(head,ptr,sn,an,y);
				}
				//Dealing with inserting a song with many duplicate names
				else if(an.compareToIgnoreCase(head.GetArtist())>0 && sn.compareToIgnoreCase(head.Next.GetSong())==0) {
					Node ptr=head.Next;
					Node prevptr=head;
					int inserted=0;
					//if the artist name is in the middle
					while(ptr.Next!=null && sn.compareToIgnoreCase(ptr.GetSong())==0) {
						if(an.compareToIgnoreCase(ptr.GetArtist())<0) {
							InsertBefore(prevptr,ptr,sn,an,y);
							inserted=1;
							break;
						}
						ptr=ptr.Next;
						prevptr=prevptr.Next;
					}
					//if the artist name is at the end of the list
					if(ptr.Next==null && inserted!=1) {
						InsertAfter(ptr,sn,an,y);
					}
					//if the artist name is in the middle of duplicate names
					else if(ptr.Next!=null && inserted!=1) {
						InsertBefore(prevptr,ptr,sn,an,y);
					}
				}
			}
			else{
				int inserted=0;
				Node ptr=head;
				ptr=ptr.Next;
				Node prevptr=head;
				//if the head is the only song currently in the list we decide where to insert the new song
				if(prevptr==head && prevptr.Next==null){
					if(sn.compareToIgnoreCase(prevptr.GetSong())<0) {
						Node Insert= new Node(sn,an,y);
						head.Next=Insert;
						Insert.Next=null;
						inserted=1;
					}
					if(sn.compareToIgnoreCase(prevptr.GetSong())>0) {
						Node Insert= new Node(sn,an,y);
						head.Next=Insert;
						Insert.Next=null;
						inserted=1;
					}
				}
				else {
					//Inserting the new song in the middle of the list
					while(ptr.Next!=null) {
						if(sn.compareToIgnoreCase(ptr.GetSong())<0) {
							InsertBefore(prevptr,ptr,sn,an,y);
							inserted=1;
							break;
						}
						//If the song name is the same we go alphabetically by artist
						else if(sn.compareToIgnoreCase(ptr.GetSong())==0) {
							//If the song is the alphabetically lowest 
							if(an.compareToIgnoreCase(ptr.GetArtist())<0) {
								InsertBefore(prevptr,ptr,sn,an,y);
								inserted=1;
								break;
							}
							else if(ptr.Next==null && an.compareToIgnoreCase(ptr.GetArtist())>0) {
								InsertAfter(ptr,sn,an,y);
								inserted=1;
								break;
							}
							//If there is only one comparison to be done and it comes after the other duplicate song
							else if(an.compareToIgnoreCase(ptr.GetArtist())>0 && sn.compareToIgnoreCase(ptr.Next.GetSong())!=0) {
								ptr=ptr.Next;
								prevptr=prevptr.Next;
								InsertBefore(prevptr,ptr,sn,an,y);
								inserted=1;
								break;
							}
							else if(an.compareToIgnoreCase(ptr.GetArtist())>0 && sn.compareToIgnoreCase(ptr.Next.GetSong())==0) {
								Node ptr2=ptr.Next;
								Node prevptr2=ptr;
								//If the song is inserted in the middle
								while(ptr2.Next!=null && sn.compareToIgnoreCase(ptr2.GetSong())==0 && inserted!=1) {
									if(an.compareToIgnoreCase(ptr2.GetArtist())<0) {
										InsertBefore(prevptr2,ptr2,sn,an,y);
										inserted=1;
										break;
									}
									ptr2=ptr2.Next;
									prevptr2=prevptr2.Next;
								}
								//If the song is inserted last
								if(ptr2.Next==null && inserted!=1) {
									InsertAfter(ptr2,sn,an,y);
									inserted=1;
									break;
								}
								//If the song is inserted in the middle of the names
								else if(ptr2.Next!=null && inserted!=1) {
									InsertBefore(prevptr2,ptr2,sn,an,y);
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
				if(inserted ==0 && sn.compareToIgnoreCase(ptr.GetSong())==0) {
					if(an.compareToIgnoreCase(ptr.GetArtist())<0) {
						InsertBefore(prevptr,ptr,sn,an,y);
					}
					else if(an.compareToIgnoreCase(ptr.GetArtist())>0) {
						InsertAfter(ptr,sn,an,y);
					}
				}
				//if the song is the second to last in the list alphabetically
				else if(inserted==0 && sn.compareToIgnoreCase(ptr.GetSong())<0) {
					InsertBefore(prevptr,ptr,sn,an,y);
				}
				//if the song is the last song alphabetically 
				else if(inserted==0 && sn.compareToIgnoreCase(ptr.GetSong())>0) {
					InsertAfter(ptr,sn,an,y);
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
	public void InsertBefore(Node prev, Node curr, String sn, String an,String ab) {
		Node Insert = new Node(sn,an,ab);
		Insert.Next=curr;
		prev.Next=Insert;
		
	}
	public void InsertBefore(Node prev, Node curr, String sn, String an,String ab,int y) {
		Node Insert = new Node(sn,an,ab,y);
		Insert.Next=curr;
		prev.Next=Insert;
	}
	public void InsertBefore(Node prev, Node curr, String sn, String an,int y) {
		Node Insert = new Node(sn,an,y);
		Insert.Next=curr;
		prev.Next=Insert;
	}
	public void InsertAfter(Node curr, String sn, String an) {
		Node Insert=new Node(sn,an);
		curr.Next=Insert;
		Insert.Next=null;
	}
	public void InsertAfter(Node curr, String sn, String an,String ab) {
		Node Insert=new Node(sn,an,ab);
		curr.Next=Insert;
		Insert.Next=null;
	}
	public void InsertAfter(Node curr, String sn, String an,String ab,int y) {
		Node Insert=new Node(sn,an,ab,y);
		curr.Next=Insert;
		Insert.Next=null;
	}
	public void InsertAfter(Node curr, String sn, String an,int y) {
		Node Insert=new Node(sn,an,y);
		curr.Next=Insert;
		Insert.Next=null;
	}
	public void PopulateList()throws IOException {
		File SongList= new File("SongList.txt");
		FileReader Read= new FileReader(SongList);
		BufferedReader SongReader= new BufferedReader(Read);
		int num;
		char ch;
		num=SongReader.read();
		String sn="";
		String an="";
		String ab="";
		String y="";
		int songread=0;
		int artistread=0;
		while(num!=-1) {
			ch=(char)num;
			if(ch=='-') {
				if(songread==0) {
					songread=1;
				}
				else if(songread==1 && artistread==0 ) {
					artistread=1;
				}
			}
			else if(songread==0 && ch!='\n' && ch!='\r') {
				sn+=ch;
			}
			else if(songread==1 && artistread==0 && ch!='\n' && ch!='\r'){
				an+=ch;
			}
			else if(artistread==1 && songread==1 && Character.isDigit(ch)) {
				y+=ch;
			}
			else if(artistread==1 && songread==1 && ch!='\n' && ch!='\r') {
				ab+=ch;
			}
			
			if(ch=='\n') {
				if(ab.equals("") && y.equals("") && !(sn.equals(""))){
					AddNode(sn,an);
				}
				else if(!(ab.equals("")) && y.equals("")){
					AddNode(sn,an,ab);
				}
				else if(ab.equals("") && !(y.equals(""))) {
					AddNode(sn,an,Integer.parseInt(y));
				}
				else if(!(ab.equals("")) && !(y.equals(""))){
					AddNode(sn,an,ab,Integer.parseInt(y));
				}
				an="";
				sn="";
				ab="";
				y="";
				songread=0;
				artistread=0;
			}
			num=SongReader.read();
		}
	}
	public void DeleteNode(String sn, String an) {
		Node prevptr=head;
		Node ptr=prevptr.Next;
		if(sn.equals(prevptr.GetSong()) && an.equals(prevptr.GetArtist()) && ptr!=null){
			prevptr.Next=null;
			head=ptr;
		}
		else if(sn.equals(prevptr.GetSong()) && an.equals(prevptr.GetArtist()) && ptr==null) {
				head=null;
				return;
		}
		else {
			while(ptr!=null){
				if(sn.equals(ptr.GetSong())&& an.equals(ptr.GetArtist())){
					prevptr.Next=ptr.Next;
					ptr.Next=null;
					return;
				}
				else if(ptr.Next==null && sn.equals(ptr.GetSong()) && an.equals(ptr.GetArtist())) {
					prevptr.Next=null;
					return;
				}
				ptr=ptr.Next;
				prevptr=prevptr.Next;
			}
		}
		
		
	}
	public void WriteList()throws IOException{
		File SongList= new File("SongList.txt");
		FileWriter Write=new FileWriter(SongList);
		BufferedWriter SongWriter=new BufferedWriter(Write);
		Node ptr=head;
		while(ptr!=null){
			if(ptr.GetYear()==0) {
				SongWriter.write(ptr.GetSong()+"-"+ptr.GetArtist()+"-"+ptr.GetAlbum()+"-"+"\r\n");
			}
			else {
				SongWriter.write(ptr.GetSong()+"-"+ptr.GetArtist()+"-"+ptr.GetAlbum()+"-"+ptr.GetYear()+"\r\n");
			}
			ptr=ptr.Next;
		}
		SongWriter.close();
	}
	/**
	 * Return null if not in the list, otherwise return the node where the song is
	 * stored
	 */
	
public static void main(String[] args) throws IOException {
	}

}


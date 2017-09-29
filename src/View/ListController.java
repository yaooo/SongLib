package View;

import compare.Node;
import compare.SongLinkedList;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.*;
import java.util.regex.Pattern;

import javafx.stage.*;

public class ListController {
	@FXML
	private ListView<String> listView;
	@FXML
	private Text title;
	@FXML
	private Text artist;
	@FXML
	private Text album;
	@FXML
	private Text year;
	@FXML
	private Button edit;
	@FXML
	private Button add;
	@FXML
	private Button delete;
	@FXML
	private TextField songTxt;
	@FXML
	private TextField artTxt;
	@FXML
	private TextField yerTxt;
	@FXML 
	private TextField  albTxt;

	@FXML
	private void handleAddButton(ActionEvent event) {
		if (event.getSource() == add) {

		}

	}

	@FXML
	private void listViewSelection(ActionEvent event) {

	}
	
	static SongLinkedList List= new SongLinkedList();
	private ObservableList<String> obsList= FXCollections.observableArrayList();
	int titlecounter=0;
	public void start (Stage stage)throws IOException {
		List.PopulateList();
		Node ptr=List.head;
		while(ptr!=null) {
			obsList.add(ptr.GetSong() +" - "+ptr.GetArtist());
			ptr=ptr.Next;
		}
		listView.setItems(obsList);
		listView.getSelectionModel().select(0);
		if(listView.getSelectionModel().getSelectedItem()==null) {}
		else {
			String s=listView.getSelectionModel().getSelectedItem();
			String[] parts=s.split(Pattern.quote(" - "));
			Node ptr2=LoopUp(List.head, parts[0], parts[1]);
			String yearNumber = "";
			title.setText(ptr2.GetSong());
			artist.setText(ptr2.GetArtist());
			album.setText(ptr2.GetAlbum());
			if(ptr2.GetYear() != 0)
				yearNumber += ptr2.GetYear();
			year.setText(yearNumber);
			
		}
		listView.getSelectionModel().selectedIndexProperty().addListener(
	    (obs, oldVal, newVal) -> 
	               showItemInputDialog(stage));
		add.setOnAction(arg0 -> {
			try {
				handleAdd(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		delete.setOnAction(arg0 -> {
			try {
				handleDelete(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}

	private Object showItemInputDialog(Stage stage) {
		String s= listView.getSelectionModel().getSelectedItem();
		String[] parts=s.split(Pattern.quote(" - "));
		Node ptr=LoopUp(List.head, parts[0], parts[1]);
		String yearNumber = "";
		title.setText(ptr.GetSong());
		artist.setText(ptr.GetArtist());
		album.setText(ptr.GetAlbum());
		if(ptr.GetYear() != 0)
			yearNumber += ptr.GetYear();
		year.setText(yearNumber);
		
		
		return null;
	}
	private Node LoopUp(Node head, String song, String artist) {
		Node temp = head;

		while (temp != null) {
			if (song.equals(temp.GetSong()) && artist.equals(temp.GetArtist())) {
				return temp;
			}
			temp = temp.Next;
		}
		return null;
	}
	public void add(String sn, String an)throws IOException {

		List.AddNode(sn, an);
		obsList.clear();
		Node ptr=List.head;
		while(ptr!=null) {
			obsList.add(ptr.GetSong() +" - "+ptr.GetArtist());
			ptr=ptr.Next;
		}
		listView.setItems(obsList);
		
	}
	public void add(String sn,String an,String ab)throws IOException{
		if(isNumeric(ab)) {
			List.AddNode(sn,an,Integer.parseInt(ab));
		}
		else {
			List.AddNode(sn,an,ab);
		}
		List.AddNode(sn, an, ab);
		obsList.clear();
		Node ptr=List.head;
		while(ptr!=null) {
			obsList.add(ptr.GetSong() +" - "+ptr.GetArtist());
			ptr=ptr.Next;
		}
		listView.setItems(obsList);
		}
	public void add(String sn,String an,String ab,String y) {
		List.AddNode(sn,an,ab,Integer.parseInt(y));
		obsList.clear();
		Node ptr=List.head;
		while(ptr!=null) {
			obsList.add(ptr.GetSong() +" - "+ptr.GetArtist());
			ptr=ptr.Next;
		}
		listView.setItems(obsList);
	}
	public void delete(String sn, String an)throws IOException{
		List.DeleteNode(sn, an);
		obsList.clear();
		Node ptr=List.head;
		while(ptr!=null) {
			obsList.add(ptr.GetSong() +" - "+ptr.GetArtist());
			ptr=ptr.Next;
		}
		listView.setItems(obsList);
	}
	public void edit(String sn, String newsn)throws IOException{
		Node ptr=List.head;
		while(ptr!=null) {
			if(sn.compareToIgnoreCase(ptr.GetSong())==0) {
				break;
			}
			ptr=ptr.Next;
		}
		ptr.UpdateSong(newsn);
		obsList.clear();
		ptr=List.head;
		while(ptr!=null) {
			obsList.add(ptr.GetSong() +" - "+ptr.GetArtist());
			ptr=ptr.Next;
		}
		listView.setItems(obsList);
	}
	private void handleAdd(ActionEvent event) throws IOException {
		if((yerTxt.getText().equals(null) || yerTxt.getText().equals("")) && (albTxt.getText().equals(null))||albTxt.getText().equals("")){
			add(songTxt.getText(), artTxt.getText());
		}
		else if(!((albTxt.getText().equals(null) || albTxt.getText().equals(""))) && (yerTxt.getText().equals(null)) || yerTxt.getText().equals("")){
			add(songTxt.getText(), artTxt.getText(),albTxt.getText());
		}
		else if((albTxt.getText().equals(null) || albTxt.getText().equals("")) && !((yerTxt.getText().equals(null)) || (yerTxt.getText().equals("")))){
			add(songTxt.getText(),artTxt.getText(),yerTxt.getText());
		}
		else if(!((albTxt.getText().equals(null))|| albTxt.getText().equals("")) && !((yerTxt.getText().equals(null) || yerTxt.getText().equals("")))) {
			add(songTxt.getText(),artTxt.getText(),albTxt.getText(),yerTxt.getText());
		}
		
	}
	private void handleDelete(ActionEvent event) throws IOException{
		String s= listView.getSelectionModel().getSelectedItem();
		String[] parts=s.split(Pattern.quote(" - "));
		System.out.println(parts[0]+parts[1]);
		delete(parts[0],parts[1]);
	}
	private void error() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Look, an Error Dialog");
		alert.setContentText("Ooops, there was an error!");
		alert.showAndWait();
	}
	public boolean isNumeric(String k){
		for(int i=0;i<k.length();i++) {
			if(!(Character.isDigit(k.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	 
}

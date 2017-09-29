package View;

import compare.Node;
import compare.SongLinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.*;
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
	
	SongLinkedList List= new SongLinkedList();
	private ObservableList<String> obsList= FXCollections.observableArrayList();
	public void start (Stage stage)throws IOException {
		List.PopulateList();
		Node ptr=List.head;
		while(ptr!=null) {
			obsList.add(ptr.GetSong() +" , "+ptr.GetArtist());
			ptr=ptr.Next;
		}
		listView.setItems(obsList);
		add.setOnAction(arg0 -> {
			try {
				handleAdd(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}

	public void add(String sn, String an)throws IOException {

		List.AddNode(sn, an);
		obsList.clear();
		Node ptr=List.head;
		while(ptr!=null) {
			obsList.add(ptr.GetSong() +" , "+ptr.GetArtist());
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
			obsList.add(ptr.GetSong() +" , "+ptr.GetArtist());
			ptr=ptr.Next;
		}
		listView.setItems(obsList);
		}
	public void add(String sn,String an,String ab,String y) {
		List.AddNode(sn,an,ab,Integer.parseInt(y));
		obsList.clear();
		Node ptr=List.head;
		while(ptr!=null) {
			obsList.add(ptr.GetSong() +" , "+ptr.GetArtist());
			ptr=ptr.Next;
		}
		listView.setItems(obsList);
	}
	public void delete(String sn, String an)throws IOException{
		List.DeleteNode(sn, an);
		obsList.clear();
		Node ptr=List.head;
		while(ptr!=null) {
			obsList.add(ptr.GetSong() +" , "+ptr.GetArtist());
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
			obsList.add(ptr.GetSong() +" , "+ptr.GetArtist());
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
	public boolean isNumeric(String k){
		for(int i=0;i<k.length();i++) {
			if(!(Character.isDigit(k.charAt(i)))) {
				return false;
			}
		}
		return true;
	}
	public static void readFile(String path) {
		File file = new File(path);
		StringBuilder stringBuffer = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {

			bufferedReader = new BufferedReader(new FileReader(file));

			String text;
			while ((text = bufferedReader.readLine()) != null) {
				String[] arr = new String[4];
				text += '-';
				System.out.println(text);

				for (int i = 0; i < 4; i++) {
					int index = text.indexOf("-");
					arr[i] = text.substring(0, index);
					text = text.substring(index + 1);
				}

			}

		} catch (FileNotFoundException ex) {
			System.out.println("File not found.");
			;
		} catch (IOException ex) {
			System.out.println("IO exception when reading file.");
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException ex) {
				System.out.println("IO.exception when trying to cloase file");
			}
		}
	}
	 
}

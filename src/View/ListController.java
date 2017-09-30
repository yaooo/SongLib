package View;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

import compare.Node;
import compare.SongLinkedList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
	private TextField albTxt;

	@FXML
	public void exitApplication(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	private void handleAddButton(ActionEvent event) {
		if (event.getSource() == add) {

		}

	}

	@FXML
	private void listViewSelection(ActionEvent event) {

	}

	static SongLinkedList List = new SongLinkedList();
	private ObservableList<String> obsList = FXCollections.observableArrayList();
	int titlecounter = 0;

	public void start(Stage stage) throws IOException {
		List.PopulateList();
		Node ptr = List.head;
		while (ptr != null) {
			obsList.add(ptr.GetSong() + " - " + ptr.GetArtist());
			ptr = ptr.Next;
		}
		listView.setItems(obsList);
		listView.getSelectionModel().select(0);
		if (listView.getSelectionModel().getSelectedItem() == null) {
		} else {
			String s = listView.getSelectionModel().getSelectedItem();
			String[] parts = s.split(Pattern.quote(" - "));
			Node ptr2 = LoopUp(List.head, parts[0], parts[1]);
			String yearNumber = "";
			title.setText(ptr2.GetSong());
			artist.setText(ptr2.GetArtist());
			album.setText(ptr2.GetAlbum());
			if (ptr2.GetYear() != 0)
				yearNumber += ptr2.GetYear();
			year.setText(yearNumber);

		}
		listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
			try {
				showItemInputDialog(stage);
			} catch (NullPointerException e) {
				// Do Nothing program works fine.
			}
		});

		add.setOnAction(arg0 -> {
			try {
				handleAdd(arg0);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				// Do Nothing program works fine.
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
		String s = listView.getSelectionModel().getSelectedItem();
		if (!(s.equals(null))) {
			String[] parts = s.split(Pattern.quote(" - "));
			Node ptr = LoopUp(List.head, parts[0], parts[1]);
			String yearNumber = "";
			title.setText(ptr.GetSong());
			artist.setText(ptr.GetArtist());
			album.setText(ptr.GetAlbum());
			if (ptr.GetYear() != 0)
				yearNumber += ptr.GetYear();
			year.setText(yearNumber);
		}

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

	public void add(String sn, String an) throws IOException {
		if (List.InList(sn, an)) {
			error2();
			return;
		}

		List.AddNode(sn, an);
		obsList.clear();
		Node ptr = List.head;
		while (ptr != null) {
			obsList.add(ptr.GetSong() + " - " + ptr.GetArtist());
			ptr = ptr.Next;
		}
		listView.setItems(obsList);

	}

	public void add(String sn, String an, String ab) throws IOException {
		if (List.InList(sn, an)) {
			error2();
			return;
		}
		if (isNumeric(ab)) {
			List.AddNode(sn, an, Integer.parseInt(ab));
		} else {
			List.AddNode(sn, an, ab);
		}
		obsList.clear();
		Node ptr = List.head;
		while (ptr != null) {
			obsList.add(ptr.GetSong() + " - " + ptr.GetArtist());
			ptr = ptr.Next;
		}
		listView.setItems(obsList);
	}

	public void add(String sn, String an, String ab, String y) {
		if (List.InList(sn, an)) {
			error2();
			return;
		}
		List.AddNode(sn, an, ab, Integer.parseInt(y));
		obsList.clear();
		Node ptr = List.head;
		while (ptr != null) {
			obsList.add(ptr.GetSong() + " - " + ptr.GetArtist());
			ptr = ptr.Next;
		}
		listView.setItems(obsList);
	}

	public void delete(String sn, String an) throws IOException {
		List.DeleteNode(sn, an);
		obsList.clear();
		Node ptr = List.head;
		while (ptr != null) {
			obsList.add(ptr.GetSong() + " - " + ptr.GetArtist());
			ptr = ptr.Next;
		}
		listView.setItems(obsList);
	}

	public void edit(String sn, String newsn) throws IOException {
		Node ptr = List.head;
		while (ptr != null) {
			if (sn.compareToIgnoreCase(ptr.GetSong()) == 0) {
				break;
			}
			ptr = ptr.Next;
		}
		ptr.UpdateSong(newsn);
		obsList.clear();
		ptr = List.head;
		while (ptr != null) {
			obsList.add(ptr.GetSong() + " - " + ptr.GetArtist());
			ptr = ptr.Next;
		}
		listView.setItems(obsList);
	}

	private void handleAdd(ActionEvent event) throws IOException {
		if (songTxt.getText().equals("") || artTxt.getText().equals("")) {
			error();
			return;
		}
		if ((yerTxt.getText().equals(null) || yerTxt.getText().equals(""))
				&& ((albTxt.getText().equals(null)) || albTxt.getText().equals(""))) {
			add(songTxt.getText(), artTxt.getText());
		} else if (!((albTxt.getText().equals(null) || albTxt.getText().equals(""))) && (yerTxt.getText().equals(null))
				|| yerTxt.getText().equals("")) {
			add(songTxt.getText(), artTxt.getText(), albTxt.getText());
		} else if ((albTxt.getText().equals(null) || albTxt.getText().equals(""))
				&& !((yerTxt.getText().equals(null)) || (yerTxt.getText().equals("")))) {
			add(songTxt.getText(), artTxt.getText(), yerTxt.getText());
		} else if (!((albTxt.getText().equals(null)) || albTxt.getText().equals(""))
				&& !((yerTxt.getText().equals(null) || yerTxt.getText().equals("")))) {
			add(songTxt.getText(), artTxt.getText(), albTxt.getText(), yerTxt.getText());
		}

	}

	private void handleDelete(ActionEvent event) throws IOException {
		String s = listView.getSelectionModel().getSelectedItem();
		String[] parts = s.split(Pattern.quote(" - "));
		System.out.println(parts[0] + parts[1]);
		delete(parts[0], parts[1]);
	}

	private void error() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Input error");
		alert.setContentText("Must have an song and artist name!");
		alert.showAndWait();
	}

	private void error2() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Duplicate error");
		alert.setContentText("Song is already in the list!");
		alert.showAndWait();
	}

	public boolean isNumeric(String k) {
		for (int i = 0; i < k.length(); i++) {
			if (!(Character.isDigit(k.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	public void writeFile() throws IOException {
		List.WriteList();
	}

	public Boolean handleEdit(ActionEvent event) {
		// Create the custom dialog.
		if (event.getSource() == edit) {

			Dialog<Boolean> dialog = new Dialog<>();
			dialog.setTitle("Edit Song details.");

			// Set the button types.
			ButtonType OkayButtonType = new ButtonType("Okay", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().addAll(OkayButtonType, ButtonType.CANCEL);

			// Create the labels and fields.
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(20, 150, 10, 10));

			TextField editSong = new TextField(title.getText());
			editSong.setPromptText("Song");
			TextField editArtist = new TextField(artist.getText());
			editArtist.setPromptText("Artist");
			TextField editAlbum = new TextField(album.getText());
			editAlbum.setPromptText("Album");
			TextField editYear = new TextField(year.getText());
			editYear.setPromptText("Year");

			grid.add(new Label("Title:"), 0, 0);
			grid.add(editSong, 1, 0);
			grid.add(new Label("Artist:"), 0, 1);
			grid.add(editArtist, 1, 1);
			grid.add(new Label("Album:"), 0, 2);
			grid.add(editAlbum, 1, 2);
			grid.add(new Label("Year:"), 0, 3);
			grid.add(editYear, 1, 3);

			dialog.getDialogPane().setContent(grid);
			// Request focus on the title field by default.
			Platform.runLater(() -> editSong.requestFocus());

			String title = "";
			String artist = "";
			String album = "";
			String year = "";

			title = editSong.getText();
			artist = editArtist.getText();
			album = editAlbum.getText();
			year = editYear.getText();

			dialog.setResultConverter(dialogButton -> {
				if (dialogButton == OkayButtonType) {
					// TODO: update the song information

					String validation = checkEditedInputFormat(editSong.getText(), editArtist.getText(),
							editAlbum.getText(), editYear.getText());

					if (validation != null) {
						error(validation);
					}

					return false;
				}
				return true;

			});

			Optional<Boolean> result = dialog.showAndWait();

			if (result.isPresent()) {
				Node song = new Node(editSong.getText(), editArtist.getText());
				if (editAlbum.getText() != null) {
					if (editAlbum.getText().trim().length() > 0) {
						song.UpdateAlbum(editAlbum.getText());
					}
				}
				if (editYear.getText() != null) {
					if (isNumeric(editYear.getText())) {
						if (editYear.getText().trim().length() > 0) {
							int newYear = Integer.parseInt(editYear.getText());
							song.UpdateYear(newYear);
						}
					}
				}
			}
		}

		return false;
	}

	private String checkEditedInputFormat(String title, String artist, String album, String year) {
		if (title.trim().isEmpty())
			return "Edited title is empty.";
		if (artist.trim().isEmpty())
			return "Edited artist is empty.";
		Node ptr = LoopUp(List.head, title, artist);
		if (ptr == null)
			return "Title and artist already exist in the library.";
		if (year.length() != 4 && !isNumeric(year)) {
			return "Edited year has to be a four-difit number.";
		}
		return null;
	}

	private void error(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Input error");
		alert.setContentText(message);
		alert.showAndWait();
	}
}

package View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import compare.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class ListController {
	@FXML
	private ListView<Node> listView;
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
	private void handleAddButton(ActionEvent event) {
		if (event.getSource() == add) {

		}

	}

	@FXML
	private void listViewSelection(ActionEvent event) {

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

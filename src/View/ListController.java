package View;

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

}

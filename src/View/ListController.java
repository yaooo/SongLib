package View;

import compare.Node;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class ListController {
	@FXML
	ListView<Node> listView;
	@FXML
	Text title, artist, album, year;
}

package app;

import java.io.IOException;

import View.ListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Sagar Patel
 * @author Yao Shi
 * @version 1.0
 */
public class SongLib extends Application {
	ListController master;

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/View/LibraryUI.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		master = loader.getController();
		master.start(stage);
		Scene scene = new Scene(root);

		stage.setTitle("Song Library");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void stop() throws IOException {
		master.writeFile();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

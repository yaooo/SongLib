package app;

import View.ListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/View/LibraryUI.fxml"));
		AnchorPane root = (AnchorPane)loader.load();
		ListController List=loader.getController();
		List.start(stage);
		Scene scene = new Scene(root);

		stage.setTitle("Song Library");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

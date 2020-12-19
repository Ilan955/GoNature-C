package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class parkIsFullController {

	@FXML
	private Button exitBtn;

	@FXML
	private Button newOrderBtn;

	@FXML
	void WhenClickExitBtn(ActionEvent event) {
		Stage stage = (Stage) exitBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void WhenClickMakeNewOrderBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) newOrderBtn.getScene().getWindow();
		stage.close();
		FXMLLoader loader = new FXMLLoader();
		Stage primaryStage = new Stage();
		Pane root = loader.load(getClass().getResource("/GUI/NewOrder.fxml").openStream());
		Scene scene = new Scene(root);
		primaryStage.setTitle("New Order");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
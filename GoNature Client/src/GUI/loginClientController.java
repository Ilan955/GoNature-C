package GUI;

import GUI.*;
import Client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class loginClientController {

	@FXML
	private Button BtnConnect;

	@FXML
	private TextField IpLbl;

	@FXML
	private TextField portLbl;

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("loginClient.fxml"));
		Image icon = new Image(getClass().getResourceAsStream("titleIcon.png"));
		primaryStage.getIcons().add(icon);
		Scene scene = new Scene(root);
		primaryStage.setTitle("Prototype");
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	/*
	 * !!!!!!!! If you want to check your screen !!!!!!!! change line 55 to what
	 * screen you want (what fxml file you need) ip="localhost" port= what port you
	 * put in the server
	 */
	@FXML
	void WhenClickConnectBtn(ActionEvent event) throws Exception {
		String s = IpLbl.getText();
		int port = Integer.parseInt(portLbl.getText());
		ClientUI.set(s, port);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("MonthlyReport.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Prototype");
		stage.setScene(scene);

		stage.show();

	}

	@FXML
	void WhenClickExitBtn(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
	}

}

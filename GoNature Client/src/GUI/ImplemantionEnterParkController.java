
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientUI;
import Entities.TravellerInPark;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ImplemantionEnterParkController implements Initializable {

	@FXML
	private Label DateLbl;

	@FXML
	private Label ParkLbl;

	@FXML
	private Label TimeLbl;

	@FXML
	private Label VisitNumberLbl;

	@FXML
	private Label PriceLbl;

	@FXML
	private Button payNowBtn;

	@FXML
	void WhenClickPayNowBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) payNowBtn.getScene().getWindow();
		stage.close();
		FXMLLoader loader = new FXMLLoader();
		Stage primaryStage = new Stage();
		Pane root = loader.load(getClass().getResource("/GUI/enjoyMessage.fxml").openStream());
		Scene scene = new Scene(root);
		primaryStage.setTitle("bye bye");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void setValues() {
		TravellerInPark t = ClientUI.entranceParkController.travellerinpark;

		ParkLbl.setText(t.getwantedPark());
		DateLbl.setText(t.getdateOfVisit().toString());
		TimeLbl.setText(t.gettimeInPark().withSecond(0).withNano(0).toString());
		VisitNumberLbl.setText(Integer.toString(t.getnumOfVisitors()));
		PriceLbl.setText(Float.toString(t.gettotalPrice()));
		ClientUI.entranceParkController.travellerinpark = null;

	}

	public void initialize(URL location, ResourceBundle resources) {
		setValues();

	}

}
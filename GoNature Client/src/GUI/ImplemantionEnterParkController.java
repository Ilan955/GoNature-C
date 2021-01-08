
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Client.ClientUI;
import Entities.TravellerInPark;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
	
	/**
	 * This method responislbe of showing an alert
	 * when want to close the application.
	 * @param event
	 */
	  @FXML
	    void WhenClickExitBtn(MouseEvent event) {
		  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		  alert.setTitle("Exit");
		  alert.setHeaderText("Are you sure you want to exit the application?");
		  alert.setResizable(false);
		  alert.setContentText("Select yes if you want, or not if you want to get back!");
		  ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
		  ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
		  Optional<ButtonType> result =  alert.showAndWait();
		  if(!result.isPresent())
		    alert.close();
		  else if(result.get() == ButtonType.OK) { 
			  ClientUI.LogOutUtility.logOutTraveller();
			  Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.close();
		  }   
		  else if(result.get() == ButtonType.CANCEL)
			  alert.close();
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
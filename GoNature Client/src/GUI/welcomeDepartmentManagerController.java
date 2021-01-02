package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class welcomeDepartmentManagerController implements Initializable {
	@FXML
	private Button informationBtn;
	@FXML
	private Label DepartmentManagerNameLBL;

	@FXML
	private Label parkNameLBL;

	@FXML
	private Label VisitorsInParkLBL;

	@FXML
	private Button WaitingDiscountsBTN;

	@FXML
	private Button WaitingParkChangesBTN;

	@FXML
	private Button logOutBTN;

	@FXML
	private Button produceCancelledOrdersBtn;

	@FXML
	private Button produceVisitReportBtn;

	@FXML
	void WhenPressLogOutBTN(ActionEvent event) throws IOException {
		ClientUI.employeeController.logOutEmployee(ClientUI.employeeController.getUserName());
		ClientUI.employeeController.setFirstName(null);
		ClientUI.employeeController.setLastName(null);
		ClientUI.employeeController.setType(null);
		ClientUI.employeeController.setParkName(null);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResource("WelcomeAndLoginScreen.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Welcome" + " " + ClientUI.employeeController.getType() + "!");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void WhenPressNotYetApprovedDiscountsBTN(ActionEvent event) {

	}

	@FXML
	void WhenPressNotYetApprovedParkChanges(ActionEvent event) throws IOException {
		// means its Bar's method
		StringBuffer sb = new StringBuffer();
		sb.append("getParkSettingsRequestsFromDB ");
		String s = sb.toString();
		ClientUI.userController.goToDbForDepManagerRequest(s);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("penidngRequest.fxml").openStream()); // screen name here
		Scene scene = new Scene(root);
		stage.setTitle("Welcome" + " " + ClientUI.employeeController.getType() + "!");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void WhenPressproduceCancelledOrdersBtn(ActionEvent event) {

	}

	@FXML
	void WhenPressproduceVisitReportBtn(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		String first = ClientUI.employeeController.getFirstName();
		String last = ClientUI.employeeController.getLastName();
		StringBuffer name = new StringBuffer();
		name.append(first);
		name.append(" ");
		name.append(last);
		String tName = name.toString();
		DepartmentManagerNameLBL.setText(tName);
		// String park = ClientUI.employeeController.getParkName();
		// parkNameLBL.setText(park);
		/*
		 * int currentVisitors=0,unExpected=0,current=0; try { currentVisitors =
		 * ClientUI.parkController.getCurrentVisitors(park); unExpected =
		 * ClientUI.parkController.getCurrentUnexpectedVisitors(park); current =
		 * currentVisitors + unExpected; VisitorsInParkLBL.setText("" + current); }
		 * catch (IOException e) {e.printStackTrace();}
		 */
	}

	@FXML
	void whenClickInformationBtn(ActionEvent event) {
		Alert a = new Alert(AlertType.INFORMATION,
				"Perform logout only by pressing Logout button\nIf you press the 'X' on the top right side you will remain connected\nIf so, you will need to call to IT for help\nPlease avoid it");
		a.setAlertType(AlertType.INFORMATION);
		a.show();
		return;
	}

}
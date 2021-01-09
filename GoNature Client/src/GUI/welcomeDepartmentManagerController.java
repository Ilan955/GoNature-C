package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
		ClientUI.LogOutUtility.makeTheStageDynamicForParent(stage, root);
		stage = ClientUI.LogOutUtility.getStage();
		root = ClientUI.LogOutUtility.getP();
		Scene scene = new Scene(root);
		stage.setTitle("Welcome" + " " + ClientUI.employeeController.getType() + "!");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void WhenPressNotYetApprovedDiscountsBTN(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("discountForDM.fxml").openStream()); // screen name here
		ClientUI.LogOutUtility.makeTheStageDynamic(stage, root);
		stage = ClientUI.LogOutUtility.getStage();
		root = ClientUI.LogOutUtility.getParent();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This method responislbe of showing an alert when want to close the
	 * application.
	 * 
	 * @param event
	 */
	@FXML
	void WhenClickExitBtn(MouseEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("Are you sure you want to exit the application?");
		alert.setResizable(false);
		alert.setContentText("Select Yes if you want to exit Or No if you want to stay.");
		((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
		((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
		Optional<ButtonType> result = alert.showAndWait();
		if (!result.isPresent())
			alert.close();
		else if (result.get() == ButtonType.OK) {
			ClientUI.LogOutUtility.logOutEmployee();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
		} else if (result.get() == ButtonType.CANCEL)
			alert.close();
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
		ClientUI.LogOutUtility.makeTheStageDynamic(stage, root);
		stage = ClientUI.LogOutUtility.getStage();
		root = ClientUI.LogOutUtility.getParent();
		Scene scene = new Scene(root);
		stage.setTitle("Welcome" + " " + ClientUI.employeeController.getType() + "!");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void WhenPressproduceCancelledOrdersBtn(ActionEvent event) {

	}

	@FXML
	void WhenPressproduceVisitReportBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Parent root;
		try {
			root = loader.load(getClass().getResource("MonthlyStayAndEnterReport.fxml").openStream());

			ClientUI.LogOutUtility.makeTheStageDynamicForParent(stage, root);
			stage = ClientUI.LogOutUtility.getStage();
			root = ClientUI.LogOutUtility.getP();
			Scene scene = new Scene(root);
			stage.setTitle("Welcome to GoNature!");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {

			e.printStackTrace();
		}
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

	}

}

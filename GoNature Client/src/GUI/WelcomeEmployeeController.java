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

public class WelcomeEmployeeController implements Initializable {
	@FXML
	private Button informationBtn;
	@FXML
	private Button logOutButton;
	@FXML
	private Label EmployeeNameLbl;

	@FXML
	private Label CurrentPeopleLbl;

	@FXML
	private Label HowManyEnterLbl;

	@FXML
	private Label ParkNameLbl;

	public void initialize(URL arg0, ResourceBundle arg1) {
		String first = ClientUI.employeeController.getFirstName();
		String last = ClientUI.employeeController.getLastName();
		StringBuffer name = new StringBuffer();
		name.append(first);
		name.append(" ");
		name.append(last);
		String tName = name.toString();
		EmployeeNameLbl.setText(tName);
		String park = ClientUI.employeeController.getParkName();
		ParkNameLbl.setText(park);
		int current = 0, currentVisitors = 0, unExpected = 0;
		currentVisitors = ClientUI.parkController.getCurrentVisitors(park);
		unExpected = ClientUI.parkController.getCurrentUnexpectedVisitors(park);
		current = currentVisitors + unExpected;
		CurrentPeopleLbl.setText("" + current);
		int howMany = 0;
		howMany = ClientUI.parkController.getMaxVisitors(park) - current;
		HowManyEnterLbl.setText("" + howMany);
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
	void WhenClickLogOutBtn(ActionEvent event) throws IOException {
		ClientUI.LogOutUtility.logOutEmployee();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResource("WelcomeAndLoginScreen.fxml").openStream());
		ClientUI.LogOutUtility.makeTheStageDynamicForParent(stage, root);
		stage = ClientUI.LogOutUtility.getStage();
		root= ClientUI.LogOutUtility.getP();
		Scene scene = new Scene(root);
		stage.setTitle("Welcome to GoNature!");
		stage.setScene(scene);
		stage.show();

	}


	@FXML
	void WhenClickSignUpNewMemberBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("SignUpNewMember.fxml").openStream());
		ClientUI.LogOutUtility.makeTheStageDynamic(stage, root);
		stage = ClientUI.LogOutUtility.getStage();
		root= ClientUI.LogOutUtility.getParent();
		Scene scene = new Scene(root);
		stage.setTitle("Signup new member");
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void WhenClickSRequestsOfCasualTravellerBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("requestsEnterTraveller.fxml").openStream());
		ClientUI.LogOutUtility.makeTheStageDynamic(stage, root);
		stage = ClientUI.LogOutUtility.getStage();
		root= ClientUI.LogOutUtility.getParent();
		Scene scene = new Scene(root);
		stage.setTitle("Requests enter traveller");
		stage.setScene(scene);
		stage.show();
	}

}
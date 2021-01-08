package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WelcomeParkManagerController implements Initializable {
	@FXML
	private Button informationBtn;
	@FXML
	private Label ParkMAnagerName;

	@FXML
	private Label ParkName;

	@FXML
	private TextField MaxVisitorsField;

	@FXML
	private TextField GapField;

	@FXML
	private TextField durationField;

	@FXML
	private Button duraionbtn;

	@FXML
	private Button duraionbtn1;

	@FXML
	private Label numberOFVisitorsLabel;

	@FXML
	private Button SpecialDiscountBTN;

	@FXML
	private Button overallVisitorsReportBTN;

	@FXML
	private Button CreateUsageReportBTN;

	@FXML
	private Button SpecialDiscountBTN111;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String first = ClientUI.employeeController.getFirstName();
		String last = ClientUI.employeeController.getLastName();
		StringBuffer name = new StringBuffer();
		name.append(first);
		name.append(" ");
		name.append(last);
		String tName = name.toString();
		ParkMAnagerName.setText(tName);
		String park = ClientUI.employeeController.getParkName();
		ParkName.setText(park);
		int current = 0, currentVisitors = 0, unExpected = 0;
		currentVisitors = ClientUI.parkController.getCurrentVisitors(park);
		unExpected = ClientUI.parkController.getCurrentUnexpectedVisitors(park);
		current = currentVisitors + unExpected;
		numberOFVisitorsLabel.setText("" + current);
		MaxVisitorsField.setText("" + ClientUI.parkController.getMaxVisitors(park));
		GapField.setText("" + (ClientUI.parkController.getMaxVisitors(park)
				- ClientUI.parkController.getMaxAvailableVisitors(park))); // --> need to know what the gap is
		durationField.setText("" + ClientUI.parkController.getMaxDuration(park));

		// int current = ParkController.getCurrentVisitors(park) +
		// ParkController.getCurrentUnexpectedVisitors(park);
		// CurrentPeopleLbl.setText("" + current);
		// 64-65 rows are after DB check with parkController. (I didn't use park DB yet)
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
		alert.setContentText("Select yes if you want, or not if you want to get back!");
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
	void ClickOnSetSpecialDiscount(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("SetDiscount.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Set new discount");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void ClickShowNumberVisitorsPark(ActionEvent event) {

	}

	@FXML
	void ClickCreateOverallVisitorsReport(ActionEvent event) {

	}

	@FXML
	void WhenClickCreateMonthlyIncomeReportBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("MonthlyReport.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Create monthly report");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void WhenClickCreateUsageReportBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("usageReport.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Create usage report");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void WhenClickLogOutBtn(ActionEvent event) throws IOException {
		ClientUI.employeeController.setFirstName(null);
		ClientUI.employeeController.setLastName(null);
		ClientUI.employeeController.setType(null);
		ClientUI.employeeController.setParkName(null);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResource("WelcomeAndLoginScreen.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Welcome to GoNature!");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void WhenClickSendChangesBtn(ActionEvent event) {
		if (MaxVisitorsField.getText().isEmpty() || GapField.getText().isEmpty() || durationField.getText().isEmpty()) {
			Alert a = new Alert(AlertType.NONE, "All fields are required!\nPlease fill every field correctly");
			a.setAlertType(AlertType.ERROR);
			a.show();
			return;
		}
		int gap = Integer.parseInt(GapField.getText());
		int maxVisit = Integer.parseInt(MaxVisitorsField.getText());
		float duration = Float.parseFloat(durationField.getText());
		if (gap >= maxVisit) {
			Alert a = new Alert(AlertType.NONE,
					"Gap can not be greater then maximum visitors amount!\nPlease fill fields correctly");
			a.setAlertType(AlertType.ERROR);
			a.show();
			return;
		}
		if (duration <= 0 || gap <= 0 || maxVisit <= 0) {
			Alert a = new Alert(AlertType.NONE, "Field can not be negative!");
			a.setAlertType(AlertType.ERROR);
			a.show();
			return;
		}
		if (duration >= 8) {
			Alert a = new Alert(AlertType.NONE, "Duration must be less or equal to 8.00(H)");
			a.setAlertType(AlertType.ERROR);
			a.show();
			return;
		}
		LocalTime timeNow = LocalTime.now();
		LocalDate dateNow = LocalDate.now();
		StringBuffer s = new StringBuffer();
		s.append("SendParkChangesToDepartmentManager ");
		s.append("x ");
		s.append(dateNow);
		s.append(" ");
		s.append(timeNow);
		s.append(" ");
		s.append(ClientUI.employeeController.getParkName());
		s.append(" ");// untill now --> s = [SendParkChanges..,date,time,parkName]
		s.append(MaxVisitorsField.getText() + " " + GapField.getText() + " " + durationField.getText() + " " + "0");
		ClientUI.employeeController.sendChangesToDepartmentManager(s.toString());
		if (ClientUI.requestsController.isCanSendParkSettingsChangesToDm()) {
			Alert a = new Alert(AlertType.INFORMATION, "Changes sent to D.manager approval!");
			a.setAlertType(AlertType.INFORMATION);
			a.setHeaderText("Sent to D.manager");
			a.setTitle("Sent to D.manager");
			a.show();
			ClientUI.requestsController.setCanSendParkSettingsChangesToDm(false);
			return;
		} else {
			Alert a = new Alert(AlertType.NONE, "An error has occured\nCould not send to D.manager");
			a.setAlertType(AlertType.ERROR);
			a.show();
		}
		return;
	}

	@FXML
	void WhenOverGapInPArkForHelp(MouseDragEvent event) {

	}

	@FXML
	void whenClickInformationBtn(ActionEvent event) {
		Alert a = new Alert(AlertType.INFORMATION,
				"Perform logout only by pressing Logout button\nIf you press the 'X' on the top right side you will remain connected\nIf so, you will need to call to IT for help\nPlease avoid it");
		a.setAlertType(AlertType.INFORMATION);
		a.setHeaderText("Logout instructions");
		a.show();
		return;
	}

	@FXML
	void ClickCreateOverallVisitorsReport(ActionEvent event) {

	}
	/*
	 * @Override public void start(Stage primaryStage) throws Exception {
	 * primaryStage.setOnCloseRequest(e->ClientUI.employeeController.logOutEmployee(
	 * ClientUI.employeeController.getUserName())); }
	 */

}

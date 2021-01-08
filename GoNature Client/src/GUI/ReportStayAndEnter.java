package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Client.ClientUI;
import clientLogic.inits;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReportStayAndEnter implements Initializable {

	@FXML
	private Button calculateBtn;

	@FXML
	private ComboBox<String> MonthCB;

	@FXML
	private ComboBox<String> YearCB;

	@FXML
	private Label parkLbl;

	@FXML
	private ComboBox<String> parkCB;

	// initialize Combo Box
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MonthCB.setItems(inits.setMonthCB());
		parkCB.setItems(inits.setWantedParkCB());
		YearCB.setItems(inits.setYearCB());
	}

	@FXML
	void WhenClickBackBtn(ActionEvent event) {

	}

	@FXML
	void WhenClickClearBtn(ActionEvent event) {
		ClientUI.orderController.oR.clear();
		Alert a = new Alert(AlertType.INFORMATION, "All the previous data deleted");
		a.setTitle("Cleared data!");
		a.setHeaderText("Successfuly cleared all the data");
		a.show();
	}

	@FXML
	void WhenClickInformationBtn(ActionEvent event) {
		Alert a = new Alert(AlertType.INFORMATION,
				"Please enter the wanted Park Month and Year to make the report.\nYou can't choose month that is not yet been.");
		a.setTitle("Stay and Entrance reports");
		a.setHeaderText("Information about Stay and Entrance report");
		a.show();
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
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
		} else if (result.get() == ButtonType.CANCEL)
			alert.close();
	}

	@FXML
	void whenClickCalculateBtn(ActionEvent event) throws IOException {
		String monthOfReport = (String) MonthCB.getValue();
		String yearOfReport = (String) YearCB.getValue();
		String parkOfreport = (String) parkCB.getValue();
		ClientUI.reportsController.getDataEntranceTimesAndStay(monthOfReport, yearOfReport, parkOfreport);
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("MonthlyStayAndEnterReportGraph.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("View Graph");
		stage.setScene(scene);
		stage.show();

	}

}

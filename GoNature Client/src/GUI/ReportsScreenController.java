
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReportsScreenController implements Initializable {

	@FXML
	private ComboBox<String> WantedMonthDATE;

	@FXML
	private TextField WantedYearLBL;

	@FXML
	private Button GetBTN;

	@FXML
	private TableView<String> ReportTable;

	@FXML
	private Button SetReportBTN;

	@FXML
	private Button ClickCancelBTN;

	@FXML
	private Label MonthLBL;

	@FXML
	private Label YearLBL;

	@FXML
	private Label ParkLBL;

	@FXML
	private Label NameLBL;

	// Set Month ComboBox
	private void setMonthCB() {
		ArrayList<String> pays = new ArrayList<String>();
		pays.add("Jan");
		pays.add("Fab");
		pays.add("Mar");
		pays.add("Apr");
		pays.add("May");
		pays.add("Jun");
		pays.add("Jul");
		pays.add("Aug");
		pays.add("Sep");
		pays.add("Oct");
		pays.add("Nov");
		pays.add("Dec");

		ObservableList<String> listForMonth = FXCollections.observableArrayList(pays);
		WantedMonthDATE.setItems(listForMonth);
	}

	// initialize Combo Boxes
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setMonthCB();
	}

	@FXML
	void ClickOnSendToDepartmentManager(ActionEvent event) {

	}

	@FXML
//	Click on cancel => return to welcome employee screen
	void WhenClickOnCancel(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("welcomeEmployee.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setTitle("Welcome Employee");
		stage.setScene(scene);

		stage.show();
	}

//Click on Get button => view data
	@FXML
	void WhenClickOnGetBtn(ActionEvent event) {
		MonthLBL.setText(WantedMonthDATE.getValue().toString());
		YearLBL.setText(WantedYearLBL.getText().toString());
		ParkLBL.setText(ClientUI.employeeController.getParkName());
		NameLBL.setText(ClientUI.employeeController.getFirstName() + " "
				+ ClientUI.employeeController.getLastName());
	}

}
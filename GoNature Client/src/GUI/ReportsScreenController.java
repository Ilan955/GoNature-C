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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private TableColumn<String, String> IndiLbl;

	@FXML
	private TableColumn<String, String> MemberLbl;

	@FXML
	private TableColumn<String, String> GroupsLbl;

	@FXML
	private TableColumn<String, String> TotalLbl;

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
		ArrayList<String> months = new ArrayList<String>();
		months.add("Jan");
		months.add("Fab");
		months.add("Mar");
		months.add("Apr");
		months.add("May");
		months.add("Jun");
		months.add("Jul");
		months.add("Aug");
		months.add("Sep");
		months.add("Oct");
		months.add("Nov");
		months.add("Dec");

		ObservableList<String> listForMonth = FXCollections.observableArrayList(months);

		WantedMonthDATE.setItems(listForMonth);
	}

	// initialize Combo Boxes
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setMonthCB();

		IndiLbl.setCellValueFactory(new PropertyValueFactory<>("Individuals"));
		MemberLbl.setCellValueFactory(new PropertyValueFactory<>("Members"));
		GroupsLbl.setCellValueFactory(new PropertyValueFactory<>("Groups"));
		TotalLbl.setCellValueFactory(new PropertyValueFactory<>("Total"));
	}

	@FXML
	void ClickOnSendToDepartmentManager(ActionEvent event) {
		// Send message to Park Manager with the report

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

		String month = WantedMonthDATE.getValue().toString();
		String year = WantedYearLBL.getText().toString();
		MonthLBL.setText(month);
		YearLBL.setText(year);
//		ParkLBL.setText(ClientUI.employeeController.getParkName());
//		NameLBL.setText(ClientUI.employeeController.getFirstName() + " " + ClientUI.employeeController.getLastName());
		ClientUI.reportsController.getData(month, year);
		int sumSolo = ClientUI.reportsController.getSolo();
		int sumMembers = ClientUI.reportsController.getMembers();
		int sumGroups = ClientUI.reportsController.getGroups();
		int sumTotal = sumSolo + sumMembers + sumGroups;

		ObservableList<String> counters = FXCollections.observableArrayList();
		counters.add(String.valueOf(sumSolo));
		counters.add(String.valueOf(sumMembers));
		counters.add(String.valueOf(sumGroups));
		counters.add(String.valueOf(sumTotal));
		System.out.println(counters);

		ReportTable.setItems(counters);


	}

}
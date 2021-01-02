package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Client.ClientUI;
import clientLogic.inits;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReportEntreisScreenController implements Initializable {

	@FXML
	private ComboBox<String> WantedMonthDATE;

	@FXML
	private ComboBox<String> YearCB;

	@FXML
	private Button GetBTN;

	@FXML
	private TableView<ReportData> ReportTable;
	@FXML
	private TableColumn<ReportData, String> IndiLbl;

	@FXML
	private TableColumn<ReportData, String> MemberLbl;

	@FXML
	private TableColumn<ReportData, String> GroupsLbl;

	@FXML
	private TableColumn<ReportData, String> TotalLbl;

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

	@FXML
	private PieChart visitorsPie;

	// initialize Combo Boxes
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		WantedMonthDATE.setItems(inits.setMonthCB());
		YearCB.setItems(inits.setYearCB());
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
			root = FXMLLoader.load(getClass().getResource("WelcomeParkManager.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setTitle("Welcome " + ClientUI.employeeController.getFirstName());
		stage.setScene(scene);
		stage.show();
	}

//Click on Get button => view data
	@FXML
	void WhenClickOnGetBtn(ActionEvent event) {
		String month = WantedMonthDATE.getValue().toString();
		String year = YearCB.getValue().toString();
		String park = ClientUI.employeeController.getParkName();
		MonthLBL.setText(month);
		YearLBL.setText(year);
		ParkLBL.setText(ClientUI.employeeController.getParkName());
		NameLBL.setText(ClientUI.employeeController.getFirstName() + " " + ClientUI.employeeController.getLastName());
		ClientUI.reportsController.getData(month, year, park);
		int sumSolo = ClientUI.reportsController.getSolo();
		int sumMembers = ClientUI.reportsController.getMembers();
		int sumGroups = ClientUI.reportsController.getGroups();
		int sumTotal = sumSolo + sumMembers + sumGroups;
		System.out.println("After click get: " + sumSolo + " " + sumMembers + " " + sumGroups + " " + sumTotal);

		ObservableList<ReportData> counters = FXCollections.observableArrayList();
		ObservableList<PieChart.Data> pieChart = FXCollections.observableArrayList(
				new PieChart.Data("Travellers", sumSolo), new PieChart.Data("Members", sumMembers),
				new PieChart.Data("Groups", sumGroups));
		ReportData rd = new ReportData(String.valueOf(sumSolo), String.valueOf(sumMembers), String.valueOf(sumGroups),
				String.valueOf(sumTotal));
		counters.add(rd);
		visitorsPie.setData(pieChart);
		ReportTable.setItems(counters);

	}

}
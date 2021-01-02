package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class usageReportController  implements Initializable{

    @FXML
    private ComboBox monthCB;

    @FXML
    private ComboBox yearCB;

    @FXML
    private Button PreviousBtn;

    @FXML
    private Button calculateBtn;

    @FXML
    private Button SendToDmanagerBtn;
    
    @FXML
    private TableView<Data> dateofNotfullCapacityTable;

    @FXML
    private TableColumn<Data, String> DateLbl;
    
    ObservableList<String> listForYears;
    ObservableList<String> listForMonth;
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setYearCb();
		setMonthCb();
	}
	private void setYearCb() {
		ArrayList<String> years = new ArrayList<String>();	
		LocalDate lY = LocalDate.now();
		int base=2018;
		for(int i= 2017 ;i<lY.getYear();i++) {
			base++;
		years.add(Integer.toString(base));
		}
		listForYears=FXCollections.observableArrayList(years);
		yearCB.setItems(listForYears);
	}
    private void setMonthCb() {
    	ArrayList<String> month = new ArrayList<String>();
		month.add("Jan");
		month.add("Fab");
		month.add("Mar");
		month.add("Apr");
		month.add("May");
		month.add("Jun");
		month.add("Jul");
		month.add("Aug");
		month.add("Sep");
		month.add("Oct");
		month.add("Nov");
		month.add("Dec");
    	listForMonth = FXCollections.observableArrayList(month);
    	monthCB.setItems(listForMonth);
    }
    
    @FXML
    void WhenClickCralculateBtn(ActionEvent event) {
    	String year = (String) yearCB.getValue();
    	String month = (String) monthCB.getValue();	
		DateLbl.setCellValueFactory(new PropertyValueFactory<>("Date"));
		ClientUI.reportsController.ob.clear();
		ClientUI.reportsController.getTableOfUnFullCapacityInDates(month,year,"Dan"); //ClientUI.employeeController.getParkName() 
		dateofNotfullCapacityTable.setItems(ClientUI.reportsController.ob);
    }


    @FXML
    void WhenClickPreviousBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("WelcomeParkManager.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void WhenClickedOnSendToDmanagerBtn(ActionEvent event) {

    }



}

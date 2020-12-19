package GUI;

import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;

	public class  WelcomeParkManagerController implements Initializable{

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
	    	String first = ClientUI.employeeController.employee.getFirstName();
	    	String last = ClientUI.employeeController.employee.getLastName();
	    	StringBuffer name = new StringBuffer();
	    	name.append(first);
	    	name.append(" ");
	    	name.append(last);
	    	String tName = name.toString();
	    //	System.out.println("\n" + tName);
	   // 	userNamelb.setText(tName);
	    	ParkMAnagerName.setText(tName);
	    	ParkName.setText(ClientUI.employeeController.employee.getParkName());
		}

	    @FXML
	    void ClickCreateOverallVisitorsReport(ActionEvent event) {

	    }

	    @FXML
	    void ClickOnSetSpecialDiscount(ActionEvent event) {

	    }

	    @FXML
	    void ClickShowNumberVisitorsPark(ActionEvent event) {

	    }

	    @FXML
	    void WhenClickCreateMonthlyIncomeReportBtn(ActionEvent event) {

	    }

	    @FXML
	    void WhenClickCreateUsageReportBtn(ActionEvent event) {

	    }

	    @FXML
	    void WhenClickLogOutBtn(ActionEvent event) {

	    }

	    @FXML
	    void WhenClickSendChangesBtn(ActionEvent event) {

	    }

	    @FXML
	    void WhenOverGapInPArkForHelp(MouseDragEvent event) {

	    }

	}


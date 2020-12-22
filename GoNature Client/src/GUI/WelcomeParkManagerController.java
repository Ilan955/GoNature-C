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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
	    //	int current = ParkController.getCurrentVisitors(park) + ParkController.getCurrentUnexpectedVisitors(park);
	    //	CurrentPeopleLbl.setText("" + current);
	    	// 64-65 rows are after DB check with parkController. (I didn't use park DB yet)
		}

	    @FXML
	    void ClickCreateOverallVisitorsReport(ActionEvent event) {

	    }

	    @FXML
	    void ClickOnSetSpecialDiscount(ActionEvent event) throws IOException {
	       	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
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
	    void WhenClickCreateMonthlyIncomeReportBtn(ActionEvent event) {

	    }

	    @FXML
	    void WhenClickCreateUsageReportBtn(ActionEvent event) {

	    }

	    @FXML
	    void WhenClickLogOutBtn(ActionEvent event) throws IOException {
	    	ClientUI.employeeController.setFirstName(null);
	    	ClientUI.employeeController.setLastName(null);
	    	ClientUI.employeeController.setType(null);
	    	ClientUI.employeeController.setParkName(null);
	    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root = loader.load(getClass().getResource("WelcomeAndLoginScreen.fxml").openStream());
			Scene scene = new Scene(root);
			stage.setTitle("Welcome to GoNature!");
			stage.setScene(scene);
			stage.show();
	    }

	    @FXML
	    void WhenClickSendChangesBtn(ActionEvent event) {

	    }

	    @FXML
	    void WhenOverGapInPArkForHelp(MouseDragEvent event) {

	    }

	}


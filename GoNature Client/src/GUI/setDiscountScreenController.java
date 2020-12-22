package GUI;

import java.io.IOException;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import Client.ClientUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


	public class setDiscountScreenController {
		long diff_days;
		Date from , to;

		  @FXML
		    private Button calculate_duration_btn;
		  
	    @FXML
	    private DatePicker FromDateField;

	    @FXML
	    private DatePicker ToDateField;

	    @FXML
	    private TextField DiscountPrecantageLBL;

	    @FXML
	    private Label DaysOFDiscountLBL;

	    @FXML
	    private Button submitDiscountBTN;

	    @FXML
	    private Button CancelBTN;

	    @FXML
	    void WhenClickCalculate(ActionEvent event) {
	    	//calc days between/
	    	LocalDate localFrom = FromDateField.getValue();
	    	LocalDate localTo = ToDateField.getValue();
	    	if(localFrom == null || localTo == null) { 
	    		Alert a = new Alert(AlertType.NONE,"You must enter 'From' date and 'To' date!"); 
				 a.setAlertType(AlertType.ERROR);
				 a.show();
	    		return;
	    		}
	    	from = java.sql.Date.valueOf(localFrom);
	    	to = java.sql.Date.valueOf(localTo);
	    	long diff = to.getTime() - from.getTime();
	    	diff_days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	    	//set message to label/
	    	if(diff_days > 0) {
	    		String daysBetween = String.valueOf(diff_days);
	    		DaysOFDiscountLBL.setText(daysBetween + " Days");
	    	}
	    	else
	    	{
	    		DaysOFDiscountLBL.setText("'To' date must be after 'From' date!!!");
	    	}
	    	
	    	
	    	
	    	
	    	
	    	
	    }
	    
	    @FXML
	    void WhenClickCancel(ActionEvent event) {
	    	
	    	//go back to manager main menu
	    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("WelcomeParkManager.fxml"));
				Scene scene = new Scene(root);
				stage.setTitle("Park Manger Menu");
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	    }

	    @FXML
	    void whenClickSubmitDiscount(ActionEvent event) {
	    	//call WhenClickCalculate to calculate dates and daysBetween/
	    	WhenClickCalculate(event);
	    	String PrecantageLBL = DiscountPrecantageLBL.getText();
	    	
	    	//check valid daysBetween && PrecantageLBL/
	    	if(diff_days<0 || PrecantageLBL.equals("")) { //show popUp message 
	    		Alert a = new Alert(AlertType.NONE,"All fields are required\n Or Duration time is invalid"); 
				 a.setAlertType(AlertType.ERROR);
				 a.show();
				 return;
	    	}
	    	else {
	    	float precentage = Float.valueOf(PrecantageLBL);
	    	String parkName = "?";//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	    	
	    	ClientUI.discountController.setManagerDiscount(from, to, precentage, parkName);
	    	}
	    	//change screen if success
	    	
	    	if(ClientUI.discountController.setManagerDiscount_flag)
	    	{
	    		Alert a = new Alert(AlertType.NONE,"Discount was sent to D.M"); 
				 a.setAlertType(AlertType.CONFIRMATION);
				 a.show();
	    		WhenClickCancel(event); // go back to ParkManger.fxml screen
	    		
	    	}
	    	else
	    	{
	    		Alert a = new Alert(AlertType.NONE,"Sql error!!!"); 
				 a.setAlertType(AlertType.ERROR);
				 a.show();
	    	}
	    }

	}
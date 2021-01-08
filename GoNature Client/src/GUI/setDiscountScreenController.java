package GUI;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import Client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class setDiscountScreenController {
	long diff_days;
	Date from, to;

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
		// calc days between/
		LocalDate localFrom = FromDateField.getValue();
		LocalDate localTo = ToDateField.getValue();
		if (localFrom == null || localTo == null) {
			Alert a = new Alert(AlertType.NONE, "You must enter 'From' date and 'To' date!");
			a.setAlertType(AlertType.ERROR);
			a.show();
			return;
		}
		from = java.sql.Date.valueOf(localFrom);
		to = java.sql.Date.valueOf(localTo);
		long diff = to.getTime() - from.getTime();
		diff_days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		// set message to label/
		if (diff_days > 0) {
			String daysBetween = String.valueOf(diff_days);
			DaysOFDiscountLBL.setText(daysBetween + " Days");
		} else {
			DaysOFDiscountLBL.setText("'To' date must be after 'From' date!!!");
		}

	}
	
	/**
	 * This method responislbe of showing an alert
	 * when want to close the application.
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
		  Optional<ButtonType> result =  alert.showAndWait();
		  if(!result.isPresent())
		    alert.close();
		  else if(result.get() == ButtonType.OK) { 
			  ClientUI.LogOutUtility.logOutEmployee();
			  Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.close();
		  }   
		  else if(result.get() == ButtonType.CANCEL)
			  alert.close();
	    }

	@FXML
	void whenClickSubmitDiscount(ActionEvent event) {
		// call WhenClickCalculate to calculate dates and daysBetween/
		WhenClickCalculate(event);
		String PrecantageLBL = DiscountPrecantageLBL.getText();

		// check valid daysBetween && PrecantageLBL/
		if (diff_days < 0 || PrecantageLBL.equals("")) { // show popUp message
			Alert a = new Alert(AlertType.NONE, "All fields are required\n Or Duration time is invalid");
			a.setAlertType(AlertType.ERROR);
			a.show();
			return;
		} else {
			float precentage = Float.valueOf(PrecantageLBL);
			if (precentage <= 0 || precentage >= 1) {
				Alert a = new Alert(AlertType.NONE, "Discount precentage must be between 0.1 - 0.9");
				a.setAlertType(AlertType.ERROR);
				a.show();
				return;
			}
			String parkName = ClientUI.employeeController.getParkName();

			ClientUI.discountController.setManagerDiscount(from, to, precentage, parkName);
		}

		/* change screen if success */
		if (ClientUI.discountController.setManagerDiscount_flag) {
			Alert a = new Alert(AlertType.NONE, "Discount was sent to D.M");
			a.setAlertType(AlertType.CONFIRMATION);
			a.show();
			WhenClickCancel(event); // go back to ParkManger.fxml screen

		} else {
			Alert a = new Alert(AlertType.NONE, "Sql error!!!");
			a.setAlertType(AlertType.ERROR);
			a.show();
		}
	}

	@FXML
	void WhenClickCancel(ActionEvent event) {

		// go back to manager main menu
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

}
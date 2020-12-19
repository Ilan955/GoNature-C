
package GUI;

import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class WelcomeEmployeeController implements Initializable {


	    @FXML
	    private Label EmployeeNameLbl;

	    @FXML
	    private Label CurrentPeopleLbl;

	    @FXML
	    private Label HowManyEnterLbl;

	    @FXML
	    private Label ParkNameLbl;
	    
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
	    	EmployeeNameLbl.setText(tName);
	    	ParkNameLbl.setText(ClientUI.employeeController.employee.getParkName());
		}
	    @FXML
	    void WhenClickLogOutBtn(ActionEvent event) {

	    }

	    @FXML
	    void WhenClickParkCapacityBtn(ActionEvent event) {

	    }

	    @FXML
	    void WhenClickSignUpNewMemberBtn(ActionEvent event) {

	    }

	}


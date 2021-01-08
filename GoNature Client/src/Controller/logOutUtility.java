package Controller;

import java.io.IOException;

import Client.ClientUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public  class logOutUtility {

	public void logOutEmployee() {
		ClientUI.employeeController.logOutEmployee(ClientUI.employeeController.getUserName());
		ClientUI.employeeController.setFirstName(null);
		ClientUI.employeeController.setLastName(null);
		ClientUI.employeeController.setType(null);
		ClientUI.employeeController.setParkName(null);
		
	}
	
	
	public void logOutTraveller() {
		ClientUI.userController.setAlreadyLoggedIn(false);
	
		try {
			ClientUI.userController.identify("deleteFromDbWhenlogOutTraveller " + ClientUI.userController.traveller.getId()+ " " + ClientUI.userController.traveller.getMemberID());
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		ClientUI.userController.traveller = null;
	}
}

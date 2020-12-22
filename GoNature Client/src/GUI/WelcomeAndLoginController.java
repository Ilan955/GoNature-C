package GUI;
import java.io.IOException;

import Client.ClientUI;
import Entities.Person;
import Entities.departmentEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class WelcomeAndLoginController {

	    @FXML
	    private TextField UserNameLBL;

	    @FXML
	    private TextField PasswordLBL;

	    @FXML
	    private Button EmployeeloginBTN;

	    @FXML
	    private TextField IdLBL;

	    @FXML
	    private Button TravellerLoginBtn;
	   
	    	 
	    @FXML
	    void WhenPressTravellerLoginBtn(ActionEvent event) throws Exception {
	    	String id = IdLBL.getText();
	    	if (!(this.checkID(id)))
	    	{
	    		 Alert a = new Alert(AlertType.NONE,"You must enter a valid id!\nId must consist the following:\nA. 9 digits\nB. numbers only"); 
				 a.setAlertType(AlertType.ERROR);
				 a.show();
	    		return;
	    	}
	    	// need to check if id consists chars or its length is less or greater than 9
	    	// if the answer to one of the questions above is true it means id is not validated
			StringBuffer str = new StringBuffer();
			str.append("getTravellerDetails ");
			str.append(id);
			String s = str.toString(); // Got the necessary info for traveller
			ClientUI.userController.identify(s);
			//while (!(ClientUI.userController.getChangeScreen()))	{};
	    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/GUI/WelcomeTraveller.fxml").openStream());
			Scene scene = new Scene(root);
			stage.setTitle("Welcome Traveller!");
			stage.setScene(scene);
			stage.show();
	    }
	    @FXML
	    void WhenPressEmployeeLogInBtn (ActionEvent event) throws IOException
	    {
	    	String userName = UserNameLBL.getText();
	    	String password = PasswordLBL.getText();
	    	if (password.isEmpty())
	    	{
				Alert a = new Alert(AlertType.NONE,"Password field must not be empty!!"); 
				a.setAlertType(AlertType.ERROR);
				a.show();
				return;
	    	}
			StringBuffer str = new StringBuffer();
			str.append("getEmployeeDetails");
			str.append(" ");
			str.append(userName);
			str.append(" ");
			str.append(password);
			String s = str.toString();  
			ClientUI.employeeController.identify(s);
			// Here I need to check if userName exists on DB 
			// If it is --> check password entered equals to password in DB
			// If match --> can enter. If one of them fails reload scene and throw an alert
			if (!(ClientUI.employeeController.isValidEmployee()))
			{
				Alert a = new Alert(AlertType.NONE,"User name is not valid!\nYou must enter a valid user name"); 
				a.setAlertType(AlertType.ERROR);
				a.show();
				return;
			}
			else if (!(ClientUI.employeeController.isMatchingPasswords()))
			{
				Alert a = new Alert(AlertType.NONE,"Incorrect password!\nYour user name exists, password does not\nPlease try again"); 
				a.setAlertType(AlertType.ERROR);
				a.show();
				return;
			}
		//	departmentEmployee t_emp = (departmentEmployee)ClientUI.employeeController.getEmployee();
			String whichScreen = ClientUI.employeeController.getType();
			switch (whichScreen) {
			case "DepartmentEmployee":
				changeScreen(event,"welcomeEmployee.fxml");
				break;
			case "ParkManager":
				changeScreen(event,"WelcomeParkManager.fxml");
				break;
			case "DepartmentManager":
				changeScreen(event,"welcomeDepartmentManager.fxml");
				break;
			default:
				System.out.print("Don't know what to do (2)");
			}
	    }
	    /*
		public void start(Stage primaryStage) throws Exception {
			
			Parent root = FXMLLoader.load(getClass().getResource("WelcomeAndLoginScreen.fxml"));
			Image icon = new Image(getClass().getResourceAsStream("titleIcon.png"));
			primaryStage.getIcons().add(icon);
			Scene scene = new Scene(root);
			primaryStage.setTitle("Welcome To GoNature!");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		*/
		public boolean checkID(String id)
		{
			if (id.length() != 9)
				return false;
			char ch;
			for (int i=0;i<9;i++)
			{
				ch = id.charAt(i);
				if (!(Character.isDigit(ch)))
					return false;
			}
			return true;
		}
		public void changeScreen(ActionEvent event, String screen) throws IOException
		{
	    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource(screen).openStream());
			Scene scene = new Scene(root);
			stage.setTitle("Welcome" + " " + ClientUI.employeeController.getType() + "!");
			stage.setScene(scene);
			stage.show();
		}
	

}

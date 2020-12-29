package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientUI;
import Controller.ParkController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WelcomeEmployeeController implements Initializable {
	@FXML
	private Button logOutButton;
    @FXML
    private Label EmployeeNameLbl;

    @FXML
    private Label CurrentPeopleLbl;

    @FXML
    private Label HowManyEnterLbl;

    @FXML
    private Label ParkNameLbl;
    
	public void initialize(URL arg0, ResourceBundle arg1) {	
    	String first = ClientUI.employeeController.getFirstName();
    	String last = ClientUI.employeeController.getLastName();
    	StringBuffer name = new StringBuffer();
    	name.append(first);
    	name.append(" ");
    	name.append(last);
    	String tName = name.toString();
    	EmployeeNameLbl.setText(tName);
    	String park = ClientUI.employeeController.getParkName();
    	ParkNameLbl.setText(park);
    	int current=0;
     //*This section of code will initialize label of current visitors in park and display it in employee screen.
    // *It does not work yet because Liad is M _ _ i _ k. [If you answer correctly to what Liad is, you will get a surprise].
    /*
	try {
		current = ClientUI.parkController.getCurrentVisitors(park) + ClientUI.parkController.getCurrentUnexpectedVisitors(park);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     	CurrentPeopleLbl.setText("" + current);
     	try {
			current-=ClientUI.parkController.getMaxVisitors(park);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
    CurrentPeopleLbl.setText(""+current);

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
    void WhenClickParkCapacityBtn(ActionEvent event) { //don't know if its even needed.. It could appear automatic when screen is shown.
    	
    }

    @FXML
    void WhenClickSignUpNewMemberBtn(ActionEvent event) throws IOException {
       	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("SignUpNewMember.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Signup new member");
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void WhenClickSRequestsOfCasualTravellerBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("requestsEnterTraveller.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Requests enter traveller");
		stage.setScene(scene);
		stage.show();
	}

}
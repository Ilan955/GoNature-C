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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WelcomeTravellerController implements Initializable {

    @FXML
    private Label userNamelb;

    @FXML
    private Label TypeLBL;
    
    @FXML
    private Button btnExistingorders;

    @FXML
    private Button btnNewOrder;

    @FXML
    private Button btnWithoutOrder;

    @FXML
    private Button LogOutBtn;
    @FXML
    private Label beforeTypeLBL;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
    	String first = "";//ClientUI.userController.traveller.getFirstName();<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    	String last = ClientUI.userController.traveller.getLastName();
    	StringBuffer name = new StringBuffer();
    	name.append(first);
    	name.append(" ");
    	name.append(last);
    	String tName = name.toString();
//    	System.out.println("\n" + tName);
    	if (first.equals("Traveller")) {
    		userNamelb.setText("Traveller");
    		beforeTypeLBL.setText("");
    		TypeLBL.setText("");
    	}
    	else {
    	userNamelb.setText(tName);
    	TypeLBL.setText(ClientUI.userController.traveller.getType());}
	}
    

 /*
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("WelcomeTraveller.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Welcome traveler");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
*/
    
    
    @FXML
    void WhenPressEnterWithoutOrderBtn(ActionEvent event) throws IOException {
    	Stage stage = (Stage) btnWithoutOrder.getScene().getWindow();
		stage.close();
		FXMLLoader loader = new FXMLLoader();
		Stage primaryStage = new Stage();
		Pane root = loader.load(getClass().getResource("/GUI/EnterParkNow.fxml").openStream());
		Scene scene = new Scene(root);
		primaryStage.setTitle("Enter Park Now");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void WhenPressEsitingOrdersBtn(ActionEvent event) throws IOException {
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("ExistingOrders.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Existing orders");
		stage.setScene(scene);
		stage.show();
    }

    @FXML

    void WhenPressLogOutBtn(ActionEvent event) throws IOException {
    	ClientUI.userController.traveller = null;
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResource("WelcomeAndLoginScreen.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Welcome to GoNature!");
		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void WhenPressMakeNewOrderBtn(ActionEvent event) throws IOException {
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("NewOrder.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("New order");
		stage.setScene(scene);
		stage.show();
    }


}



package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	private Button LogOutBtn;
	@FXML
	private Label beforeTypeLBL;
	@FXML
	private Button btnWithoutOrder;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//String typ = ClientUI.userController.traveller.getType();
		//if (typ.equals("") || typ.equals("preOrderTraveller") || typ.equals("Traveller"))
			//btnExistingorders.setVisible(false);
		//String first = ClientUI.userController.traveller.getFirstName();
		//String last = ClientUI.userController.traveller.getLastName();
		String first = "Mozes";
		String last = "Erh";
		StringBuffer name = new StringBuffer();
		name.append(first);
		name.append(" ");
		name.append(last);
		String tName = name.toString();
		String typ="";
		if (first.equals("Traveller")) {
			userNamelb.setText("Traveller");
			if (typ.equals("")) {
				beforeTypeLBL.setText("");
				TypeLBL.setText("");
			} else {
				userNamelb.setText(tName);
				//TypeLBL.setText(ClientUI.userController.traveller.getType());
				TypeLBL.setText("Check");
			}
		} else {
			userNamelb.setText(tName);
			//TypeLBL.setText(ClientUI.userController.traveller.getType());
			TypeLBL.setText("Check");
		}
		
		/*
		 * will check if the traveller have orders for tomorrow.
		 * if he is having them, will pop up a alert message saying he need to approve the orders, in the "ExisitingOrders" section.
		 */
		LocalDate tomorrow = LocalDate.now();
		tomorrow=tomorrow.plusDays(1);
		if(ClientUI.orderController.havingAlert(tomorrow,Integer.toString(4))) {
			ClientUI.orderController.need_alert=false;
			Alert a = new Alert(AlertType.INFORMATION,"Good news! \nTomorrow you having a trip with us!\nplease make sure to approve your order in the 'Show exisitng orders' section!\nThank you and have fun with us!"); 
			 a.setTitle("UpcomingVisits");
			 a.setHeaderText("You have Upcoming visits"); 
			 a.show();
			 
		}
		
		
		
	}

	@FXML
	void WhenPressEnterWithoutOrderBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnWithoutOrder.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/GUI/EnterParkNow.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Enter Park Now");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void WhenPressEsitingOrdersBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResource("WelcomeAndLoginScreen.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Welcome to GoNature!");
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void WhenPressMakeNewOrderBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("NewOrder.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("New order");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void WhenPressExitParkBtn(ActionEvent event) throws IOException {
		String typeOfVisitor = ClientUI.userController.traveller.getType();
		int numofvisitors = 3;
		ClientUI.entranceParkController.travellerinpark.getnumOfVisitors(); // check
		String park = EnterParkNowController.wantedpark;
		String id = ClientUI.userController.traveller.getId();
		numofvisitors = numofvisitors * (-1);
		if (typeOfVisitor == "Traveller") {
			ClientUI.entranceParkController.setNumOfVisitorEntringPark(park, numofvisitors);
			ClientUI.entranceParkController.updateExitTimeForcasualTraveller(park, id);
		} else {
			ClientUI.entranceParkController.setCurrentVisitros(park, numofvisitors);
			ClientUI.entranceParkController.updateExitTimeForTravellerWithOrder(park, id);
		}

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("ByeBye.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Exit");
		stage.setScene(scene);
		stage.show();

	}

}

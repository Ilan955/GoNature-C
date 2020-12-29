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
	private Button LogOutBtn;
	@FXML
	private Label beforeTypeLBL;
	@FXML
	private Button btnWithoutOrder;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String typ = ClientUI.userController.traveller.getType();
		if (typ.equals("") || typ.equals("preOrderTraveller") || typ.equals("Traveller"))
			btnExistingorders.setVisible(false);
		String first = ClientUI.userController.traveller.getFirstName();
		String last = ClientUI.userController.traveller.getLastName();
		StringBuffer name = new StringBuffer();
		name.append(first);
		name.append(" ");
		name.append(last);
		String tName = name.toString();

		if (first.equals("Traveller")) {
			userNamelb.setText("Traveller");
			if (typ.equals("")) {
				beforeTypeLBL.setText("");
				TypeLBL.setText("");
			} else {
				userNamelb.setText(tName);
				TypeLBL.setText(ClientUI.userController.traveller.getType());
			}
		} else {
			userNamelb.setText(tName);
			TypeLBL.setText(ClientUI.userController.traveller.getType());
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

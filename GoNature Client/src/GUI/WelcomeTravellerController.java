package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author Liad Yadin
 *
 */
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
	@FXML
	private Button exitParkBtn;

	String ID = WelcomeAndLoginController.id;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (!(ClientUI.entranceParkController.IfgetTravellerInParkExistInDB(ID))
				&& !((ClientUI.entranceParkController.IfgetOrderInParkExistInDB(ID))))
			exitParkBtn.setVisible(false);

		String typ = ClientUI.userController.traveller.getType();
		if (typ.equals("") || typ.equals("preOrderTraveller"))
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
			// TypeLBL.setText("Check");
		}

		/*
		 * will check if the traveler have orders for tomorrow. if he is having them,
		 * will pop up a alert message saying he need to approve the orders, in the
		 * "ExisitingOrders" section.
		 */
		LocalDate tomorrow = LocalDate.now();
		tomorrow = tomorrow.plusDays(1);
		if (ClientUI.orderController.havingAlert(tomorrow, Integer.toString(4))) {
			ClientUI.orderController.need_alert = false;
			Alert a = new Alert(AlertType.INFORMATION,
					"Good news! \nTomorrow you having a trip with us!\nplease make sure to approve your order in the 'Show exisitng orders' section!\nThank you and have fun with us!");
			a.setTitle("UpcomingVisits");
			a.setHeaderText("You have Upcoming visits");
			a.show();

		}

	}

	/**
	 * This method responislbe of showing an alert when want to close the
	 * application.
	 * 
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
		Optional<ButtonType> result = alert.showAndWait();
		if (!result.isPresent())
			alert.close();
		else if (result.get() == ButtonType.OK) {
			ClientUI.LogOutUtility.logOutTraveller();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
		} else if (result.get() == ButtonType.CANCEL)
			alert.close();
	}

	/**
	 * This method will available to everyone. if the traveler will click this
	 * button he will move to 'EnterParkNow' screen if he will choose this option -
	 * he will be Casual taveller type in park
	 * 
	 * @param event- when click enter without order
	 * @throws IOException
	 */
	@FXML
	void WhenPressEnterWithoutOrderBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnWithoutOrder.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("EnterParkNow.fxml").openStream());
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
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ClientUI.LogOutUtility.logOutTraveller();
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

	/**
	 * This method will be available only who stay in park this moment and need to
	 * exit from park here he can click on Exit and the details on DB will update:
	 * ExitTime of this traveler and the data of the amount current visitors in the
	 * park when the traveler exit from the park - he log out from the system and
	 * the window of 'Bye Bye' is display him
	 * 
	 * @param event - when click exit button
	 * @throws IOException
	 */
	@FXML
	void WhenPressExitParkBtn(ActionEvent event) throws IOException {
		int numofvisitors;
		String park;
		if (ClientUI.entranceParkController.IfgetTravellerInParkExistInDB(ID)) {
			ClientUI.entranceParkController.getTravellerInParkDetails(ID);
			numofvisitors = ClientUI.entranceParkController.travellerinpark.getnumOfVisitors();
			park = ClientUI.entranceParkController.travellerinpark.getwantedPark();
			numofvisitors = numofvisitors * (-1);
			ClientUI.entranceParkController.setNumOfVisitorEntringPark(park, numofvisitors);
			ClientUI.entranceParkController.updateExitTimeForcasualTraveller(park, ID);
		} else { // order
			ClientUI.entranceParkController.getOrderDetailsForExitPark(ID);
			numofvisitors = ClientUI.orderController.order.getNumberOfVisitors();
			park = ClientUI.orderController.order.getWantedPark();
			numofvisitors = numofvisitors * (-1);
			ClientUI.entranceParkController.setCurrentVisitros(park, numofvisitors);
			ClientUI.entranceParkController.updateExitTimeForTravellerWithOrder(park, ID);
		}
		// Log out
		ClientUI.userController.setAlreadyLoggedIn(false);
		ClientUI.userController.identify("deleteFromDbWhenlogOutTraveller " + ClientUI.userController.traveller.getId()
				+ " " + ClientUI.userController.traveller.getMemberID());
		ClientUI.userController.traveller = null;

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("ByeBye.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Exit");
		stage.setScene(scene);
		stage.show();

	}

}

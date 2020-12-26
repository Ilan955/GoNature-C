package GUI;

import GUI.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SignUpScreenController implements Initializable {

	@FXML
	private TextField IdLbl;

	@FXML
	private TextField FirstNameLbl;

	@FXML
	private TextField LastNameLbl;

	@FXML
	private TextField PhoneNumberLbl;

	@FXML
	private TextField EmailLbl;

	@FXML
	private TextField NumberMembersLbl;

	@FXML
	private ComboBox PaymentCB;

	@FXML
	private ComboBox TypeMemberCB;

	ObservableList<String> listForTypes;
	ObservableList<String> listForPaymont;
	public String id, firstName, lastName, phoneNum, email, paymentMethod, memberType;
	int numOfVisitors;

	// Set the Type member ComboBox
	private void setTypeMemberCB() {
		ArrayList<String> types = new ArrayList<String>();
		types.add("Traveller");
		types.add("Group Guide");

		listForTypes = FXCollections.observableArrayList(types);
		TypeMemberCB.setItems(listForTypes);
	}

	// Set payment ComboBox
	private void setPaymentCB() {
		ArrayList<String> pays = new ArrayList<String>();
		pays.add("Cash");
		pays.add("Credit Card");

		listForPaymont = FXCollections.observableArrayList(pays);
		PaymentCB.setItems(listForPaymont);
	}

	// initialize Combo Boxes
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setPaymentCB();
		setTypeMemberCB();

	}

	// public void start(Stage primaryStage) throws Exception {
	// Parent root =
	// FXMLLoader.load(getClass().getResource("/GUI/SignUpNewMember.fxml"));
	// Scene scene = new Scene(root);
	// primaryStage.setTitle("Register New Member");
	// primaryStage.setScene(scene);
	// primaryStage.show();
	//
	// }

	@FXML
	void WhenClickBackBtn(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("welcomeEmployee.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setTitle("Welcome Employee");
		stage.setScene(scene);

		stage.show();
	}

	@FXML
	void WhenClickSubmitBtn(ActionEvent event) {
		boolean regFlag = true;
		id = IdLbl.getText().toString();
		firstName = FirstNameLbl.getText().toString();
		lastName = LastNameLbl.getText().toString();
		phoneNum = PhoneNumberLbl.getText().toString();
		email = EmailLbl.getText().toString();
		numOfVisitors = Integer.valueOf(NumberMembersLbl.getText().toString());
		paymentMethod = (String) PaymentCB.getValue();
		memberType = (String) TypeMemberCB.getValue();
		if (numOfVisitors > 1 && numOfVisitors < 15 && memberType == "Traveller") {
			memberType = "Family Member";
		} else {
			if (memberType == "Traveller") {
				Alert a = new Alert(AlertType.ERROR, "Member type incompatible");
				a.show();
				regFlag = false;
			}
		}
		// here we will send the data we got from the page
		ClientUI.signUpController.checkExist(id);
		if (ClientUI.signUpController.checker && regFlag == true) {
			ClientUI.signUpController.init(id, firstName, lastName, phoneNum, email, paymentMethod, memberType,
					numOfVisitors);
			System.out.println("New member added: " + id);
			GoToSuccess(event);
		} else {
			if (regFlag == true) {
				Alert b = new Alert(AlertType.WARNING, "Member with id " + id + " already exists!");
				b.show();
			}
		}

	}

	void GoToSuccess(ActionEvent event) {
		System.out.println("Enter SucceSS");
		ClientUI.signUpController.id = this.id;
		ClientUI.signUpController.memberType = this.memberType;
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("SuccessfulySignUp.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setTitle("Success Sign Up");
		stage.setScene(scene);

		stage.show();
	}

}

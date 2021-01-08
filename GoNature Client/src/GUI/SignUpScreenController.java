/** Description of SignUpScreenController 
* @author Omri Cohen
* @version final Jan 2, 2021.
*/

package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientUI;
import clientLogic.inits;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This is a controller for sign up window
 * 
 * @implNote implements Initializable - initialize all predefined data
 */
public class SignUpScreenController implements Initializable {

	@FXML
	private TextField IdLbl;
	/** text box for persons ID */

	@FXML
	private TextField FirstNameLbl;
	/** text box for persons first name */

	@FXML
	private TextField LastNameLbl;
	/** text box for persons last name */

	@FXML
	private TextField PhoneNumberLbl;
	/** text box for persons phone number */

	@FXML
	private TextField EmailLbl;
	/** text box for persons email address */

	@FXML
	private TextField NumberMembersLbl;
	/** text box for number of visitors on the membership */

	@FXML
	private ComboBox<String> PaymentCB;
	/** PaymentCB Combo box for choosing payment method */

	@FXML
	private ComboBox<String> TypeMemberCB;
	/** TypeMemberCB Combo box for choosing membership type */

	public String id, firstName, lastName, phoneNum, email, paymentMethod, memberType;
	int numOfVisitors;

	/**
	 * Description of initialize When this window is opened, this is the first
	 * function to be activated. initializing all the data needed for this window.
	 * Payment combo box and Membership types combo box
	 * 
	 * @return void - no returns.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TypeMemberCB.setItems(inits.setTypeMemberCB());
		PaymentCB.setItems(inits.setPaymentCB());
	}

	/**
	 * Description of WhenClickBackBtn Upon clicking back the employee shall be sent
	 * back to employees main screen
	 * 
	 * @return void - no returns.
	 */
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

	/**
	 * Description of WhenClickSubmitBtn Upon clicking Submit the id shall be
	 * checked to see if already exists in DB. if yes - return error if not - a new
	 * member shall be created with all the information provided from the form. upon
	 * success a new window shall pop up with confirmation message including new
	 * member ID and his membership type.
	 * 
	 * @return void - no returns.
	 */
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
		if (numOfVisitors > 1 && memberType == "Traveller") {
			memberType = "Family Member";
		}
		if (memberType == "Group Guide" && numOfVisitors > 15) {
			Alert a = new Alert(AlertType.ERROR, "Maximum 15 Persons per group!");
			a.show();
			regFlag = false;
		}

		// here we will send the data we got from the form
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

	/**
	 * Description of GoToSuccess After successful registration, this function shall
	 * open confirmation window with the new member ID and his membership type.
	 * 
	 * @return void - no returns.
	 */
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

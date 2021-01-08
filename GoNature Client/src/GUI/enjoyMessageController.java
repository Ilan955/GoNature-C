package GUI;

import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This GUI will show 'enjoy message' for casual traveler in the entrance park
 * and presnting him how many time he has in the park
 * 
 * @author Liad Yadin
 *
 */
public class enjoyMessageController implements Initializable {
	/** for presenting the time the traveler has in park */
	@FXML
	private Label Lbltime;
	/** when the traveler click close button */
	@FXML
	private Button closeBtn;

	/**
	 * close window
	 * 
	 * @param event- when click on close button
	 */
	@FXML
	void whenClickOnCloseBtn(ActionEvent event) {
		Stage stage = (Stage) closeBtn.getScene().getWindow();
		stage.close();
	}

	/**
	 * this method will show the time that the traveler could be in the park
	 */
	public void setTime() {
		String st = String.valueOf(ClientUI.parkController.park.getMaxDurationVisit());
		Lbltime.setText(st);
	}
/**
 * for present the time
 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTime();
	}

}

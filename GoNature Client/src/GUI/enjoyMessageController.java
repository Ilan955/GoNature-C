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

public class enjoyMessageController implements Initializable {

	@FXML
	private Label Lbltime;

	@FXML
	private Button closeBtn;

	@FXML
	void whenClickOnCloseBtn(ActionEvent event) {
		Stage stage = (Stage) closeBtn.getScene().getWindow();
		stage.close();
	}

	public void setTime() {
		String st = String.valueOf(ClientUI.parkController.park.getMaxDurationVisit());
		Lbltime.setText(st);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTime();
	}

}

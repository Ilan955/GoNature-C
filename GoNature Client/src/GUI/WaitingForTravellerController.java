package GUI;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import Client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WaitingForTravellerController {
	private boolean approval = false;
	private int statusApproval = -1;
	@FXML
	private Button checkBtn;

	@FXML
	void whenclickedOnCheck(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		statusApproval = ClientUI.requestsController.checkIfApproveRequest("11", "EnterPark");
		if (statusApproval == -1) {
			Parent root = FXMLLoader.load(getClass().getResource("waiting.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("Waiting for enter");
			stage.setScene(scene);
			stage.show();
		}
		else if (statusApproval == 1) {
			ClientUI.entranceParkController.enterWithoutOrder(LocalTime.now(), LocalDate.now(),
					EnterParkNowController.wantedpark, EnterParkNowController.numOfVisitors, (float) 50);

			Parent root = FXMLLoader.load(getClass().getResource("ImplementaionEnterPark.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("Confirm Enter park");
			stage.setScene(scene);
			stage.show();
		} else {
			Parent root = FXMLLoader.load(getClass().getResource("parkIsFull.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("Unapproved");
			stage.setScene(scene);
			stage.show();
		}
	}

}

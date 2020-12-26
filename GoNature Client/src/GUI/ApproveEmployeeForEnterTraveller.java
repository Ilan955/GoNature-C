package GUI;

import java.io.IOException;

import Client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ApproveEmployeeForEnterTraveller {

    @FXML
    private Button notApproveBtn;

    @FXML
    private Button approveBtn;
    
    @FXML
    void whenClickedApproveBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) approveBtn.getScene().getWindow();
		stage.close();
		ClientUI.requestsController.changeStatusForCasualTraveller(1, ClientUI.requestsController.d.getID(), ClientUI.requestsController.d.getPark());
		FXMLLoader loader = new FXMLLoader();
		Stage primaryStage = new Stage();
		Pane root = loader.load(getClass().getResource("requestsEnterTraveller.fxml").openStream());
		Scene scene = new Scene(root);
		primaryStage.setTitle("Requests enter traveller");
		primaryStage.setScene(scene);
		primaryStage.show();

    }

    @FXML
    void whenClickedDontApprove(ActionEvent event) throws IOException {
		Stage stage = (Stage) notApproveBtn.getScene().getWindow();
		stage.close();
		ClientUI.requestsController.changeStatusForCasualTraveller(0, ClientUI.requestsController.d.getID(), ClientUI.requestsController.d.getPark());
		FXMLLoader loader = new FXMLLoader();
		Stage primaryStage = new Stage();
		Pane root = loader.load(getClass().getResource("requestsEnterTraveller.fxml").openStream());
		Scene scene = new Scene(root);
		primaryStage.setTitle("Requests enter traveller");
		primaryStage.setScene(scene);
		primaryStage.show();

    }

}

package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class parkIsFullEmployeeController {

    @FXML
    private Button backBtn;

    @FXML
    private Button closeBtn;

    @FXML
    void whenClickedonBackBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) backBtn.getScene().getWindow();
		stage.close();
		FXMLLoader loader = new FXMLLoader();
		Stage primaryStage = new Stage();
		Pane root = loader.load(getClass().getResource("requestsEnterTraveller.fxml").openStream());
		Scene scene = new Scene(root);
		primaryStage.setTitle("Requests enter traveller");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void whenClickedonClosebtn(ActionEvent event) {
		Stage stage = (Stage) closeBtn.getScene().getWindow();
		stage.close();
    }

}

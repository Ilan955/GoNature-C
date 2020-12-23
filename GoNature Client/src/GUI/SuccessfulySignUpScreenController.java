package GUI;

import Client.ClientUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SuccessfulySignUpScreenController implements Initializable {

    @FXML
    private Label SubscriberNumberLbl;

    @FXML
    private Label SignedUpType;

    public void initialize(URL arg0, ResourceBundle arg1) {
        SubscriberNumberLbl.setText(ClientUI.signUpController.getID());
        SignedUpType.setText(ClientUI.signUpController.getType());
    }

    @FXML
    void WhenClickMainMenuBtn(ActionEvent event) {

    }

}

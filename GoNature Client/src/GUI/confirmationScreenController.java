package GUI;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientUI;
import Entities.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class confirmationScreenController implements Initializable {

  @FXML
  private Label IdOvTravelerLb;

  @FXML
  private Label WantedParkLbl;


  @FXML
  private Label DateLbl;

  @FXML
  private Label TimeLbl;

  @FXML
  private Label EmailLbl;

  @FXML
  private Label PhoneLbl;

  @FXML
  private Label NumVisitLlbl;


	@FXML
	private Label PriceLbl;


  /*
   * Setting the values of the confirmation based on what was set in the order and what set for the traveler who made the order
   */
  public void setValues() {
    Order o = ClientUI.orderController.order;

    WantedParkLbl.setText(o.getWantedPark());
    DateLbl.setText(o.getDateOfVisit().toString());
    TimeLbl.setText(o.getTimeInPark().toString());
    EmailLbl.setText(ClientUI.orderController.getEmail());
    PhoneLbl.setText(ClientUI.orderController.getPhone());
    NumVisitLlbl.setText(Integer.toString(o.getNumberOfVisitors()));
    PriceLbl.setText(Float.toString(o.getTotalPrice()));
  }

  /*
   *Showing the information for the user
   *the information includes all the data of the order
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    setValues();
  }

  /*
   * when the user will click on done, the system will:
   * 		will insert the order into DB
   * 		will delete the current order from the "o" value
   */

  @FXML
  void WhenClickDoneBtn(ActionEvent event) throws IOException {
    ClientUI.orderController.confirmOrder();
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Parent root = FXMLLoader.load(
      getClass().getResource("WelcomeTraveller.fxml")
    );
    Scene scene = new Scene(root);
    stage.setTitle("Prototyp");
    stage.setScene(scene);
    stage.show();
  }

  /*
   * if the user click back, he will need to fill the form again
   * with the details from here
   */
  @FXML
  void whenClickBackBtn(ActionEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Parent root = FXMLLoader.load(getClass().getResource("NewOrder.fxml"));
    Scene scene = new Scene(root);
    stage.setTitle("Prototyp");
    stage.setScene(scene);

    stage.show();
  }
}

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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class SureCancellScreen implements Initializable {

	
	    @FXML
	    private Label DateLbl;

	    @FXML
	    private Label ParkLbl;

	    @FXML
	    private Label TimeLbl;
	    
	    //if the traveler don't want to cancel the order, he will return to the previous page (the UnapprovedOrder sceen)
	    @FXML
	    void WhenClickDontWantToCancelBtn(ActionEvent event) throws IOException {
	    	 Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	    	 FXMLLoader loader = new FXMLLoader();
	    		Pane root = loader.load(getClass().getResource("/GUI/WelcomeTraveller.fxml").openStream());
	    		Scene scene = new Scene(root);
	    		stage.setTitle("Cancel order");
	    		stage.setScene(scene);
	    		stage.show();
	    }

	    //if the traveler want to cancel the order he will return to the main traveller page
	    @FXML
	    void WhenClickWantToCancel(ActionEvent event) throws IOException {
	    	ClientUI.orderController.cancelOrder(DateLbl.getText(),ParkLbl.getText(),TimeLbl.getText());
	    	
	    	 Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	    	 FXMLLoader loader = new FXMLLoader();
	    		Pane root = loader.load(getClass().getResource("/GUI/WelcomeTraveller.fxml").openStream());
	    		Scene scene = new Scene(root);
	    		stage.setTitle("Cancel order");
	    		stage.setScene(scene);
	    		stage.show();
	    	 
	    }
	    
	    public void setVals() {
	    	Order o = ClientUI.orderController.order;
	    	DateLbl.setText(o.getDateOfVisit().toString());
	    	ParkLbl.setText(o.getWantedPark());
	    	TimeLbl.setText(o.getTimeInPark().toString());
	    }
	    //initialize the values for the park, date and time the user entered.
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			setVals();
			
		}
		

	

	
}

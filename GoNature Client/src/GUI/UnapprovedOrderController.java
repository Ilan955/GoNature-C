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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class UnapprovedOrderController implements Initializable{

	    @FXML
	    private Button enterWaitingList;

	    @FXML
	    private Button ShowAlternativeDates;

	    @FXML
	    private Button cancelOrder;

	    @FXML
	    private Label ParkNameLbl;

	    @FXML
	    private Label DateVisitLbl;

	    @FXML
	    private Label NumberVisitorsLbl;

	    @FXML
	    private Label IdNumberLbl;

	    @FXML
	    void WhenClickCancellBtn(ActionEvent event) throws IOException {
	    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	    	ClientUI.orderController.wantToCancel(stage);
	    }

	    /*
	     * when click enter waiting list : 
	     * 	1. need to enter the order into the Order table in the Db
	     *  2. need to change the status of the order to waiting from confirmed
	     *  3. need to put the waiting request into the table of the waiting list with the current timeStamp.
	     */
	    @FXML
	    void WhenClickEnterWaitingListBtn(ActionEvent event) {
	    	Order tmp = ClientUI.orderController.order;
	    	ClientUI.orderController.confirmOrder();
	    	ClientUI.orderController.ChangeToWaitOrder(tmp);
	    }

	    @FXML
	    void WhenClickShowBtn(ActionEvent event) throws IOException  {
	    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	    	FXMLLoader loader = new FXMLLoader();
	    	Pane root = loader.load(getClass().getResource("AlternativeDates.fxml").openStream());
	    	Scene scene = new Scene(root);
	    	stage.setTitle("Cancel order");
	    	stage.setScene(scene);
	    	stage.show();
	    	
	    }
	    //!!!!!!! ID of user need to get from userController!!!!!!
	    public void setValues() {
	    	Order o =ClientUI.orderController.order;
	    	IdNumberLbl.setText("31198");
	    	ParkNameLbl.setText(o.getWantedPark());
	    	DateVisitLbl.setText(o.getDateOfVisit().toString());
	    	String num= Integer.toString(o.getNumberOfVisitors()); 
	    	NumberVisitorsLbl.setText(num);
	    	
	    }
	    /*
	     * This method will initialize the values of the order wanted to be complete
	     */
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			setValues();
		}

	

}

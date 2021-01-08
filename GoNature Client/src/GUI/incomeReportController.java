package GUI;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
public class incomeReportController implements Initializable {

	    @FXML
	    private Button cancelOrder;
	    @FXML
	    private Label Travelers_income_Lbl;
	    @FXML
	    private Label Amount_of_travelers_LBL;
	    
	    @FXML
	    private Label Amount_of_Member_Lbl;
	    @FXML
	    private Label Member_income_LBL;
	    
	    @FXML
	    private Label Amount_of_Families_LBL;
	    @FXML
	    private Label Families_income_LBL;
	    
	    @FXML
	    private Label Amount_of_Groups_LBL;
	    @FXML
	    private Label Groups_income_LBL;
	   
	    
	   

	    @Override
		public void initialize(URL location, ResourceBundle resources) {
			ClientUI.reportsController.makeMonthlyIncomeReport(LocalDate.now());
			Amount_of_travelers_LBL.setText(String.valueOf(ClientUI.reportsController.Traveler_cnt));
			Travelers_income_Lbl.setText(String.valueOf(ClientUI.reportsController.Traveler_income));
			
			Amount_of_Member_Lbl.setText(String.valueOf(ClientUI.reportsController.Member_cnt));
			Member_income_LBL.setText(String.valueOf(ClientUI.reportsController.Member_income));
			
			Amount_of_Families_LBL.setText(String.valueOf(ClientUI.reportsController.Family_cnt));
			Families_income_LBL.setText(String.valueOf(ClientUI.reportsController.Family_income));
			
			Amount_of_Groups_LBL.setText(String.valueOf(ClientUI.reportsController.Group_cnt));
			Groups_income_LBL.setText(String.valueOf(ClientUI.reportsController.Group_income));
			
			
			
			
		}
	    
	    @FXML
	    void WhenClickCancellBtn(ActionEvent event) {
	    	/*go back to manager main menu*/
	    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("WelcomeParkManager.fxml"));
				Scene scene = new Scene(root);
				stage.setTitle("Park Manger Menu");
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

		

	

	
}

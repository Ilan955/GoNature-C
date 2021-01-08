/** Description of SignUpScreenController 
* @author Omri Cohen
* 
* @version final Jan 2, 2021.
*/

package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientUI;
import clientLogic.inits;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This is a controller for report of entrance hours and stay duration graphs.
 * 
 * @implNote implements Initializable - initialize all predefined data
 */
public class ReportStayAndEnterGraphController implements Initializable {

	@FXML
	/** button to show graph */
	private Button calculateBtn;

	@FXML
	/** button to clear graphs data */
	private Button ClearBTN;

	@FXML
	/** button to get back */
	private Button backBTN;

	@FXML
	/** Combo box for choosing wanted month */
	private ComboBox<String> MonthCB;

	@FXML
	/** Combo box for choosing wanted year */
	private ComboBox<String> YearCB;

	@FXML
	/** button to read windows information */
	private Button InfoBTN;

	@FXML
	/** display label "park" */
	private Label parkLbl;

	@FXML
	/** Combo box for choosing wanted park */
	private ComboBox<String> parkCB;

	@Override
	/**
	 * Description of initialize() this function initializes the combo boxes for the
	 * window..
	 * 
	 * @param MonthCB combo box for wanted month.
	 * @param parkCB  combo box for wanted park.
	 * @param YearCB  combo box for wanted year.
	 * @return void.
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		MonthCB.setItems(inits.setMonthCB());
		parkCB.setItems(inits.setWantedParkCB());
		YearCB.setItems(inits.setYearCB());
	}

	@FXML
	/**
	 * Description of WhenClickBackBtn Upon clicking back the department manager
	 * shall be sent back to department managers main screen.
	 * 
	 * @return void - no returns.
	 */
	void WhenClickBackBtn(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("WelcomeDeparmentEmployee.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setTitle("Success Sign Up");
		stage.setScene(scene);

		stage.show();
	}

	@FXML
	/**
	 * Description of WhenClickClearBtn Upon clicking the button all data shall be
	 * cleared in all arrays and all graphs.
	 * 
	 * @return void - no returns.
	 */
	void WhenClickClearBtn(ActionEvent event) {
		ClientUI.reportsController.visitors.clear();
		ClientUI.reportsController.members.clear();
		ClientUI.reportsController.groups.clear();
		Alert a = new Alert(AlertType.INFORMATION, "All the previous data deleted");
		a.setTitle("Cleared data!");
		a.setHeaderText("Successfuly cleared all the data");
		a.show();
	}

	@FXML
	/**
	 * Description of WhenClickInformationBtn Upon clicking the button an alert with
	 * explanation for the screen.
	 * 
	 * @return void - no returns.
	 */
	void WhenClickInformationBtn(ActionEvent event) {
		Alert a = new Alert(AlertType.INFORMATION,
				"Please enter the wanted Park Month and Year to make the report.\nYou can't choose month that is not yet been.");
		a.setTitle("Stay and Entrance reports");
		a.setHeaderText("Information about Stay and Entrance report");
		a.show();
	}

	@FXML
	/**
	 * Description of whenClickCalculateBtn Upon clicking the button a window will
	 * pop up with wanted graphs.
	 * 
	 * @throws IOException -From inner methods (getDataEntranceTimesAndStay)
	 * 
	 * @return void - no returns.
	 */
	void whenClickCalculateBtn(ActionEvent event) throws IOException {
		String monthOfReport = (String) MonthCB.getValue();
		String yearOfReport = (String) YearCB.getValue();
		String parkOfreport = (String) parkCB.getValue();
		ClientUI.reportsController.getDataEntranceTimesAndStay(monthOfReport, yearOfReport, parkOfreport);
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("MonthlyStayAndEnterReportGraph.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("View Graph");
		stage.setScene(scene);
		stage.show();

	}

}
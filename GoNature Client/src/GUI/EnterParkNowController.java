
package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.jws.soap.SOAPBinding.Use;

import Client.ClientUI;
import Entities.Park;
import Entities.TravellerInPark;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EnterParkNowController implements Initializable {

	@FXML
	private Button btnNext;

	@FXML
	private Button btnPrevious;

	@FXML
	private Label IDlbl;

	@FXML
	private Label PriceLbl;

	@FXML
	private ComboBox WantedParkCB;

	@FXML
	private Label DateLbl;

	@FXML
	private Label TimeLbl;

	@FXML
	private TextField NumOfVisiotrstxt;

	@FXML
	private Button btnCalculatePrice;
	ObservableList<String> listForParks;
	public static String wantedpark;
	public static int numOfVisitors;
	private LocalDate myDate;
	private LocalTime myTime;

	public void setIdOfMakingOrder() {
		// IDlbl.setText(ClientUI.userController.traveller.getId());
		IDlbl.setText("11");
	}

	private void setWantedParkCm() {
		ArrayList<String> parks = new ArrayList<String>();
		parks.add("Hermon");
		parks.add("Dan");
		parks.add("Matsada");

		listForParks = FXCollections.observableArrayList(parks);
		WantedParkCB.setItems(listForParks);
	}

	private void SetTimePark() {
		myDate = LocalDate.now();
		myTime = LocalTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
		String formattedTime = myTime.format(myFormatObj);
		this.DateLbl.setText(myDate.toString());
		this.TimeLbl.setText(formattedTime.toString());

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setWantedParkCm();
		SetTimePark();
		setIdOfMakingOrder();
	}

	@FXML
	void WhenClickCalculatePriceBtn(ActionEvent event) throws IOException {

	}

	@FXML
	void WhenClickNextBtn(ActionEvent event) throws IOException {
		String wantedpark = (String) WantedParkCB.getValue();
		int numOfVisitors=Integer.parseInt(NumOfVisiotrstxt.getText());

		ClientUI.requestsController.insertRequestToDB("11", myDate, myTime, wantedpark,numOfVisitors, "EnterPark", -1);

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("waiting.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Waiting for enter");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void WhenClickPreviusBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnPrevious.getScene().getWindow();
		stage.close();
		FXMLLoader loader = new FXMLLoader();
		Stage primaryStage = new Stage();
		Pane root = loader.load(getClass().getResource("/GUI/WelcomeTraveller.fxml").openStream());
		Scene scene = new Scene(root);
		primaryStage.setTitle("Welcome Traveller");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
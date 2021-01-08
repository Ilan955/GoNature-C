
package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
	private ComboBox<String> WantedParkCB;

	@FXML
	private Label DateLbl;

	@FXML
	private Label TimeLbl;

    @FXML
    private ComboBox<String> NumOfVisitorsCB;

	@FXML
	private Button btnCalculatePrice;
	ObservableList<String> listForParks;
	ObservableList<String> listForNumOfVisitors;
	public static String wantedpark;
	public static int numOfVisitors;
	private LocalDate myDate;
	private LocalTime myTime;

	public void setIdOfMakingOrder() {
		IDlbl.setText(ClientUI.userController.traveller.getId());
	}

	private void setWantedParkCm() {
		ArrayList<String> parks = new ArrayList<String>();
		parks.add("Hermon");
		parks.add("Dan");
		parks.add("Matsada");

		listForParks = FXCollections.observableArrayList(parks);
		WantedParkCB.setItems(listForParks);
	}
	
	private void setNumOfVisitorsCm() {
		ArrayList<String> numbersOfVisitors = new ArrayList<String>();
		for(int i=1;i<=15;i++)
			numbersOfVisitors.add(String.valueOf(i));

		listForNumOfVisitors = FXCollections.observableArrayList(numbersOfVisitors);
		NumOfVisitorsCB.setItems(listForNumOfVisitors);
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
		setNumOfVisitorsCm();
	}

	@FXML
	void WhenClickCalculatePriceBtn(ActionEvent event) throws IOException {
	}

	@FXML
	void WhenClickNextBtn(ActionEvent event) throws IOException {
		wantedpark = (String) WantedParkCB.getValue();
		String numString=(String) NumOfVisitorsCB.getValue();
		numOfVisitors=Integer.parseInt(numString);

		ClientUI.requestsController.insertRequestToDB(ClientUI.userController.traveller.getId(), myDate, myTime,
				wantedpark, numOfVisitors, "EnterPark", -1);

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
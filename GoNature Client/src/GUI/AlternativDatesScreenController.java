package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Client.ClientUI;
import Entities.Order;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AlternativDatesScreenController implements Initializable {

	@FXML
	private DatePicker StartDateLbl;

	@FXML
	private DatePicker EndDateLbl;

	@FXML
	private ComboBox FromLbl;

	@FXML
	private Label UserIdLbl;

	@FXML
	private TableView<Data> AlternativeTable;

	@FXML
	private TableColumn<Data, String> Date;

	@FXML
	private TableColumn<Data, String> Park;

	@FXML
	private TableColumn<Data, String> Time;

	@FXML
	private TableColumn<Data, String> numOfVisit;

	@FXML
	private TableColumn<Data, String> Price;

	@FXML
	private TableColumn<Data, String> choseOrder;

	@FXML
	private ComboBox TimeOfVisitCB;

	ObservableList<String> listForTimes;

	@FXML
	void WhenClickBack(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
		Park.setCellValueFactory(new PropertyValueFactory<>("Park"));
		Time.setCellValueFactory(new PropertyValueFactory<>("Time"));
		numOfVisit.setCellValueFactory(new PropertyValueFactory<>("NumOfVisit"));
		Price.setCellValueFactory(new PropertyValueFactory<>("Price"));

		try {
			ClientUI.orderController.getAlternativeDates(ClientUI.orderController.order.getTimeInPark());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("HOW MANY");
		SetTimeParkCm();
		AlternativeTable.setItems(ClientUI.orderController.aD);
		addButtonToTable();
	}

	private void addButtonToTable() {
		TableColumn<Data, Void> colBtn = new TableColumn("Choose Order");

		Callback<TableColumn<Data, Void>, TableCell<Data, Void>> cellFactory = new Callback<TableColumn<Data, Void>, TableCell<Data, Void>>() {
			@Override
			public TableCell<Data, Void> call(final TableColumn<Data, Void> param) {
				final TableCell<Data, Void> cell = new TableCell<Data, Void>() {

					private final Button btn = new Button("Choose");

					{
						btn.setOnAction((ActionEvent event) -> {

							Data data = getTableView().getItems().get(getIndex());
							int orderNum = Integer.parseInt(data.getID());
							System.out.println("The time is:" + data.getTime());
							LocalTime lt = LocalTime.parse(data.getTime());
							LocalDate ld = LocalDate.parse(data.getDate());
							ClientUI.orderController.isInDb = false;
							ClientUI.orderController.order = new Order(orderNum, lt, ld, data.getPark(),
									Integer.parseInt(data.getNumOfVisit()), Float.parseFloat(data.getPrice()));
							Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
							FXMLLoader loader = new FXMLLoader();
							Pane root;
							try {
								root = loader.load(getClass().getResource("/GUI/Confirmation.fxml").openStream());
								Scene scene = new Scene(root);
								stage.setTitle("Unapproved Order");
								stage.setScene(scene);
								stage.show();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};

		colBtn.setCellFactory(cellFactory);

		AlternativeTable.getColumns().add(colBtn);

	}

	private void SetTimeParkCm() {
		String half = ":30";
		String whole = ":00";
		String t;
		int flag = 0;
		ArrayList<String> Times = new ArrayList<String>();

		for (int i = 8; i < 20; i++) {
			if (flag == 0) {
				t = Integer.toString(i) + whole;
				Times.add(t);
				flag++;
				i--;
			} else {
				t = Integer.toString(i) + half;
				Times.add(t);
				flag--;
			}
		}
		listForTimes = FXCollections.observableArrayList(Times);
		TimeOfVisitCB.setItems(listForTimes);
	}

	// if don't want to enter the waiting list, clicking back and redirect to the
	// Unapproved order
	@FXML
	void WhenClickBackBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/GUI/CencellOrder.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Cancel order");
		stage.setScene(scene);
		stage.show();
	}

	/*
	 * 1. get the date and time from the input 2. move the data to a method that in
	 * the order controller 3. send the data to the db and get back all the dates
	 * and times in range entered. 4. initialize counter that will reset every
	 * "maxDurationVisit" 5. counter will count the amount of visitors in park in
	 * every "maxDurationVisit" 6. when reaching new "maxDurationVisit" check if
	 * adding the current order possible 7. if so, create new Data object with the
	 * time and date possible 8. Save it into an ArrayList 9. Initialize this screen
	 * again, and feed the Table with the array.
	 */

	@FXML
	void whenClickSubmitBtn(ActionEvent event) {
		String temp = (String) TimeOfVisitCB.getValue();

		String[] res = temp.split(":");
		LocalTime time = LocalTime.of(Integer.parseInt(res[0]), Integer.parseInt(res[1]));
		try {
			ClientUI.orderController.getAlternativeDates(time);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AlternativeTable.getColumns().removeAll(ClientUI.orderController.aD);
		AlternativeTable.refresh();

		AlternativeTable.setItems(ClientUI.orderController.aD);

	}

}

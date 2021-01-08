/**
 * Screen showing the following:
 * 	1. Creating a table with information
 *  about the order the traveller has
 *  2. Creating buttons to enable the traveller
 *  confirm/cancel orders.
 *  Check for logical errors when clicking on buttons.
 *  3. update the park when entering the park. 
 *  
 *  @author Ilan Alexandrov
 *  @author Liad Yadin
 *  @version 2.0 Build December, 2020
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ExsitingOrdersScreenController implements Initializable {
	/** ExistingOrderTable - Table for the Existing order */
	@FXML
	private TableView<Data> ExistingOrderTable;
	/** IDLbl - TableColumn for the Existing order */
	@FXML
	private TableColumn<Data, String> IDLbl;
	/** DateTbl - TableColumn for the Existing order */
	@FXML
	private TableColumn<Data, String> DateTbl;
	/** ParkLbl - TableColumn for the Existing order */
	@FXML
	private TableColumn<Data, String> ParkLbl;
	/** TimeLbl - TableColumn for the Existing order */
	@FXML
	private TableColumn<Data, String> TimeLbl;
	/** NumOfVisitLbl - TableColumn for the Existing order */
	@FXML
	private TableColumn<Data, String> NumOfVisitLbl;
	/** StatusLbl - TableColumn for the Existing order */
	@FXML
	private TableColumn<Data, String> StatusLbl;
	/** CommentsLbl - TableColumn for the Existing order */
	@FXML
	private TableColumn<Data, String> CommentsLbl;
	/** PriceLbl - TableColumn for the Existing order */
	@FXML
	private TableColumn<Data, String> PriceLbl;

	/**
	 * Description of WhenClickBackBtn(ActionEvent event) in ExsistingOrders Screen
	 * This method is responsible to get the user back to the Welcome traveller
	 * screen
	 * 
	 * @param event
	 */
	@FXML
	void WhenClickBackBtn(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("WelcomeTraveller.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("Prototyp");
			stage.setScene(scene);

			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int index = 0;

	/**
	 * Description of initialize(URL arg0, ResourceBundle arg1) in ExsistingOrders
	 * Screen This method responsible to initialize all of the columns in the table
	 * 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DateTbl.setCellValueFactory(new PropertyValueFactory<>("Date"));
		IDLbl.setCellValueFactory(new PropertyValueFactory<>("ID"));
		ParkLbl.setCellValueFactory(new PropertyValueFactory<>("Park"));
		TimeLbl.setCellValueFactory(new PropertyValueFactory<>("Time"));
		NumOfVisitLbl.setCellValueFactory(new PropertyValueFactory<>("NumOfVisit"));
		PriceLbl.setCellValueFactory(new PropertyValueFactory<>("Price"));
		StatusLbl.setCellValueFactory(new PropertyValueFactory<>("Status"));
		CommentsLbl.setCellValueFactory(new PropertyValueFactory<>("Comments"));

		ClientUI.orderController.getExsistingOrders();
		System.out.println(ClientUI.orderController.ob.size());
		ExistingOrderTable.setItems(ClientUI.orderController.ob);
		addButtonToTable();

	}

	/**
	 * This method responislbe of showing an alert when want to close the
	 * application.
	 * 
	 * @param event
	 */
	@FXML
	void WhenClickExitBtn(MouseEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("Are you sure you want to exit the application?");
		alert.setResizable(false);
		alert.setContentText("Select yes if you want, or not if you want to get back!");
		((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
		((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
		Optional<ButtonType> result = alert.showAndWait();
		if (!result.isPresent())
			alert.close();
		else if (result.get() == ButtonType.OK) {
			ClientUI.LogOutUtility.logOutTraveller();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
		} else if (result.get() == ButtonType.CANCEL)
			alert.close();
	}

	/**
	 * Description of addButtonToTable() in ExsistingOrders Screen Creating buttons
	 * for every cell there will be adding an option to cancel the order if wanted
	 * if pressed cancel in the correct cell: Create an order in the OrderController
	 * Screen Routing the user to sure if cancell order screen
	 * 
	 * @return void
	 */

	private void addButtonToTable() {
		TableColumn<Data, Void> colBtn = new TableColumn("Cancel");
		TableColumn<Data, Void> colBtnEnter = new TableColumn("Entrence");
		TableColumn<Data, Void> colBtnConfirm = new TableColumn("Confirmation");

		Callback<TableColumn<Data, Void>, TableCell<Data, Void>> cellFactory = new Callback<TableColumn<Data, Void>, TableCell<Data, Void>>() {
			@Override
			public TableCell<Data, Void> call(final TableColumn<Data, Void> param) {
				final TableCell<Data, Void> cell = new TableCell<Data, Void>() {

					private final Button btn = new Button("Cancel");

					{

						btn.setOnAction((ActionEvent event) -> {
							Data data = getTableView().getItems().get(getIndex());

							int orderNum = Integer.parseInt(data.getID());
							System.out.println(data.getTime());
							LocalTime lt = LocalTime.parse(data.getTime());
							LocalDate ld = LocalDate.parse(data.getDate());
							ClientUI.orderController.isInDb = true;
							ClientUI.orderController.order = new Order(orderNum, lt, ld, data.getPark(),
									Integer.parseInt(data.getNumOfVisit()), Float.parseFloat(data.getPrice()));
							Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
							FXMLLoader loader = new FXMLLoader();
							Pane root;
							try {
								root = loader.load(getClass().getResource("/GUI/SureIfCancel.fxml").openStream());
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

		Callback<TableColumn<Data, Void>, TableCell<Data, Void>> cellFactory1 = new Callback<TableColumn<Data, Void>, TableCell<Data, Void>>() {
			@Override
			public TableCell<Data, Void> call(final TableColumn<Data, Void> param) {
				final TableCell<Data, Void> cell = new TableCell<Data, Void>() {

					private Button btn = new Button("Enter");

					{
						btn.setOnAction((ActionEvent event) -> {
							Data data = getTableView().getItems().get(getIndex());

							int orderNum = Integer.parseInt(data.getID());
							System.out.println(data.getTime());
							LocalTime lt = LocalTime.parse(data.getTime());
							LocalDate ld = LocalDate.parse(data.getDate());
							LocalTime timeNow = LocalTime.now();
							LocalDate dateNow = LocalDate.now();
							int timeH = timeNow.getHour();
							int timeM = timeNow.getMinute();
							int timelH = lt.getHour();
							int limitTime = timelH + 4;
							int timelM = lt.getMinute();
							if (ld.compareTo(dateNow) == 0 && timeH < limitTime && timelM == timeM) {
								System.out.println("GOOD");
								int numofvisit = ClientUI.userController.traveller.getNumberOfVisitors();
								String park = ClientUI.orderController.order.getWantedPark();
								String id = ClientUI.userController.traveller.getId();
								int maxUpdateCurrentVisitors;
								maxUpdateCurrentVisitors = ClientUI.parkController.getCurrentVisitors(park)
										+ ClientUI.parkController.getCurrentUnexpectedVisitors(park) + numofvisit;

								ClientUI.entranceParkController.updateEnterTimeForTravellerWithOrder(park, id);
								ClientUI.entranceParkController.setCurrentVisitros(park, numofvisit);

								if (maxUpdateCurrentVisitors == ClientUI.parkController.getMaxVisitors(park)) {
									ClientUI.parkController.updateStatusForCapacityParkToFull(park);
								} else {
									if (!ClientUI.parkController.IfgetDateExistInDB(park)) {
										ClientUI.parkController.enterDateofFullCapcityPark(park, LocalDate.now(), 0,
												ClientUI.parkController.getMaxVisitors(park), maxUpdateCurrentVisitors);

									} else {
										if (ClientUI.parkController
												.getMaxcurrentVisitorsPerDay(park) < maxUpdateCurrentVisitors)
											ClientUI.parkController.changeMaxcurrentAmountOfVisitorsForCapacityPark(
													park, maxUpdateCurrentVisitors);
									}

								}
							} else {
								Alert a = new Alert(AlertType.NONE, "Now it's not the time for your order!");
								a.setAlertType(AlertType.ERROR);
								a.show();
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

		Callback<TableColumn<Data, Void>, TableCell<Data, Void>> cellFactory3 = new Callback<TableColumn<Data, Void>, TableCell<Data, Void>>() {
			@Override
			public TableCell<Data, Void> call(final TableColumn<Data, Void> param) {
				final TableCell<Data, Void> cell = new TableCell<Data, Void>() {

					private final Button btn = new Button("Confirm");

					{

						btn.setOnAction((ActionEvent event) -> {

							Data data = getTableView().getItems().get(getIndex());

							if (data.getStatus().equals("confirmed")) {
								Alert b = new Alert(AlertType.NONE, "Sorry, but your order has been already confirmed");
								b.setAlertType(AlertType.ERROR);
								b.show();
							} else {
								LocalTime timeNow = LocalTime.now();
								LocalDate dateNow = LocalDate.now();
								LocalDate dateOfTomorrow = dateNow.plusDays(1);
								LocalTime timeOfVisit = LocalTime.parse(data.getTime());
								LocalDate dateOfVisit = LocalDate.parse(data.getDate());
								dateOfTomorrow = dateNow.plusDays(1);
								LocalTime timeOfVisitMinus2 = timeOfVisit.minusHours(2);

								if (dateOfTomorrow.compareTo(dateOfVisit) == 0) {
									if (timeNow.compareTo(timeOfVisitMinus2) > 0
											&& timeNow.compareTo(timeOfVisit) < 0) {
										Alert a = new Alert(AlertType.NONE, "Your order has been confirmed!");
										a.setAlertType(AlertType.INFORMATION);
										a.show();
										ClientUI.orderController.confirmAlert(data.getID());
										Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
										FXMLLoader loader = new FXMLLoader();
										Pane root;
										try {
											root = loader.load(
													getClass().getResource("/GUI/ExistingOrders.fxml").openStream());
											Scene scene = new Scene(root);
											stage.setTitle("Unapproved Order");
											stage.setScene(scene);
											stage.show();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									} else {
										Alert a = new Alert(AlertType.NONE,
												"Sorry, now its not the time to confirm your order.\nMessage will be send a day before your visit.");
										a.setAlertType(AlertType.ERROR);
										a.show();
									}
								} else {
									Alert a = new Alert(AlertType.NONE,
											"Sorry, now its not the time to confirm your order.\nMessage will be send a day before your visit.");
									a.setAlertType(AlertType.ERROR);
									a.show();
								}
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
		colBtnEnter.setCellFactory(cellFactory1);
		colBtnConfirm.setCellFactory(cellFactory3);
		ExistingOrderTable.getColumns().addAll(colBtn, colBtnEnter, colBtnConfirm);
	}
}

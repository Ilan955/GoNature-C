package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.imageio.event.IIOReadWarningListener;

import Client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RequestsEnterTravellerController implements Initializable {

	@FXML
	private TableView<Data> requestsTravellerTable;

	@FXML
	private TableColumn<Data, String> IDlbl;

	@FXML
	private TableColumn<Data, String> timeLbl;

	@FXML
	private TableColumn<Data, String> numOfVisitLbl;

	@FXML
	private Label DateLbl;

	private boolean isFull = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.requestsController.ob.clear();
		IDlbl.setCellValueFactory(new PropertyValueFactory<>("ID"));
		timeLbl.setCellValueFactory(new PropertyValueFactory<>("Time"));
		numOfVisitLbl.setCellValueFactory(new PropertyValueFactory<>("NumOfVisit"));
		LocalDate myDate = LocalDate.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
		this.DateLbl.setText(myDate.toString());
		ClientUI.requestsController.getRequestsTravellerOfEnterPark("Dan"); // change
		requestsTravellerTable.setItems(ClientUI.requestsController.ob);
		addButtonToTable();

	}

	private void addButtonToTable() {
		TableColumn<Data, Void> colBtn = new TableColumn("Action");
		LocalDate myDate = LocalDate.now();
		Callback<TableColumn<Data, Void>, TableCell<Data, Void>> cellFactory = new Callback<TableColumn<Data, Void>, TableCell<Data, Void>>() {
			@Override
			public TableCell<Data, Void> call(final TableColumn<Data, Void> param) {
				final TableCell<Data, Void> cell = new TableCell<Data, Void>() {

					private final Button btn = new Button("Check availability");

					{

						btn.setOnAction((ActionEvent event) -> {
							Data data = getTableView().getItems().get(getIndex());
							try {
								String ID = data.getID();
								String park = data.getPark();
								int numOfvisit = Integer.parseInt(data.getNumOfVisit());
								if (!ClientUI.parkController.parkIsFull(park, numOfvisit)) {
									if (!ClientUI.parkController.IfgetDateExistInDB())
										ClientUI.parkController.enterDateofFullCapcityPark(park, myDate, 0);
									Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
									FXMLLoader loader = new FXMLLoader();
									Pane root;
									root = loader.load(
											getClass().getResource("ApprovingRequestTraveller.fxml").openStream());
									Scene scene = new Scene(root);
									stage.setTitle("Approve request");
									stage.setScene(scene);
									stage.show();
								} else {
									ClientUI.requestsController.changeStatusForCasualTraveller(0, ID, park);

									if (ClientUI.parkController.getCurrentVisitors(park)
											+ ClientUI.parkController.getCurrentUnexpectedVisitors(
													park) == ClientUI.parkController.getMaxVisitors(park)) {
										ClientUI.parkController.updateStatusForCapacityParkToFull(park);
									}
									Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
									FXMLLoader loader = new FXMLLoader();
									Pane root;
									root = loader.load(getClass().getResource("parkIsFullEmployee.fxml").openStream());
									Scene scene = new Scene(root);
									stage.setTitle("park is full");
									stage.setScene(scene);
									stage.show();
								}

							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						);
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
		requestsTravellerTable.getColumns().add(colBtn);

	}
	

    @FXML
    void WhenClickedOnPreviousBtn(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("WelcomeEmployee.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("New order");
		stage.setScene(scene);
		stage.show();
    }
}

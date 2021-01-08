package GUI;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class discountForApproveDM implements Initializable {

	@FXML
	private TableView<parkPendingRData> RequestTable;

	@FXML
	private TableColumn<parkPendingRData, String> IdLbl;

	@FXML
	private TableColumn<parkPendingRData, String> RequestDateLbl;

	@FXML
	private TableColumn<parkPendingRData, String> RequestTimeLbl;

	@FXML
	private TableColumn<parkPendingRData, String> ParkNameLbl;

	@FXML
	private TableColumn<parkPendingRData, String> StartDateLbl;

	@FXML
	private TableColumn<parkPendingRData, String> LastDateLbl;

	@FXML
	private TableColumn<parkPendingRData, String> PrecentageLbl;

	@FXML
	void whenClickBackBtn(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("D");

		ParkNameLbl.setCellValueFactory(new PropertyValueFactory<>("ParkName"));
		IdLbl.setCellValueFactory(new PropertyValueFactory<>("RequestID"));
		RequestDateLbl.setCellValueFactory(new PropertyValueFactory<>("RequestDate"));
		RequestTimeLbl.setCellValueFactory(new PropertyValueFactory<>("RequestTime"));
		StartDateLbl.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
		LastDateLbl.setCellValueFactory(new PropertyValueFactory<>("LastDate"));
		PrecentageLbl.setCellValueFactory(new PropertyValueFactory<>("Precentage"));

		// start from here
		ObservableList<parkPendingRData> x = FXCollections.observableArrayList();

		parkPendingRData r = new parkPendingRData("5", "parkName", "20.20.20", "15:00", "S4.10", "End date", "5%",
				"no");
		x.add(r);
		RequestTable.setItems(x);
		addButtonToTableToApprove();
		addButtonToTableToDisApprove();

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
			ClientUI.LogOutUtility.logOutEmployee();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
		} else if (result.get() == ButtonType.CANCEL)
			alert.close();
	}

	private void addButtonToTableToApprove() {
		TableColumn<parkPendingRData, Void> colBtn = new TableColumn("Approve");

		Callback<TableColumn<parkPendingRData, Void>, TableCell<parkPendingRData, Void>> cellFactory = new Callback<TableColumn<parkPendingRData, Void>, TableCell<parkPendingRData, Void>>() {
			@Override
			public TableCell<parkPendingRData, Void> call(final TableColumn<parkPendingRData, Void> param) {
				final TableCell<parkPendingRData, Void> cell = new TableCell<parkPendingRData, Void>() {

					private final Button btn = new Button("Approve");

					{
						btn.setOnAction((ActionEvent event) -> {

							// HERE SET ACTION FOR APPROVE

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

		RequestTable.getColumns().add(colBtn);

	}

	private void addButtonToTableToDisApprove() {
		TableColumn<parkPendingRData, Void> colBtn = new TableColumn("Disapprove");

		Callback<TableColumn<parkPendingRData, Void>, TableCell<parkPendingRData, Void>> cellFactory = new Callback<TableColumn<parkPendingRData, Void>, TableCell<parkPendingRData, Void>>() {
			@Override
			public TableCell<parkPendingRData, Void> call(final TableColumn<parkPendingRData, Void> param) {
				final TableCell<parkPendingRData, Void> cell = new TableCell<parkPendingRData, Void>() {

					private final Button btn = new Button("Disapprove");

					{
						/*
						 * When pressing disapprove first we need to update the DB with status 1 After
						 * this I need to go to DB and pull the current requests And then finish.
						 * 
						 */
						btn.setOnAction((ActionEvent event) -> {

							// HERE SET ACTION FOR DISAPPROVE

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

		RequestTable.getColumns().add(colBtn);

	}

}

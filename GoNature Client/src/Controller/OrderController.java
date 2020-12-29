package Controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import Client.ClientUI;
import Entities.Order;
import Entities.Person;
import GUI.CancelReportData;
import GUI.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OrderController {
	public Person t;
//!!!! WHAT NEED TO BE !!!!
//Traveller t = ClientUI.userController.traveller
	public Order order;
	public boolean valid = false;
	ArrayList<String> mess = new ArrayList<String>();
	private String currentEmail;
	private String currentPhone;
	public boolean n_order = false;
	public float currentPrice;
	public boolean isInDb = false;
	private String ReportMonth;
	private String ReportYear;
	private ArrayList<String> alternativeDates = new ArrayList<String>();
	public ObservableList<Data> ob = FXCollections.observableArrayList();
	public ObservableList<Data> aD = FXCollections.observableArrayList();
	public ArrayList<CancelReportData> oR = new ArrayList<CancelReportData>();
	public boolean isConfirm = true;

	/*
	 * This method will check with the db if there is a place in the park for this
	 * time and date got if so, will create new order, and save it later in the DB.
	 * will check first if there is an order for the current time
	 * 
	 */
	public void canMakeOrder(LocalTime time, LocalDate dateOfVisit, String wantedPark, String type, int numOfVisitors) {
		if (n_order)
			order = new Order(5, time, dateOfVisit, wantedPark, numOfVisitors, (float) 50);
		LocalTime openingTime = LocalTime.of(8, 0);
		LocalTime closingTime = LocalTime.of(23, 30);
		LocalTime turn = LocalTime.of(11, 00);
		/*
		 * check what are the boundary's this will numbers will help us to know how many
		 * park in this times.
		 */
		LocalTime from = null;
		LocalTime to = null;
		LocalTime tmp;
		tmp = time.minusMinutes(30);
		if (tmp.isBefore(turn))
			from = openingTime;
		else {
			for (int i = 3; i >= 0; i--) {
				tmp = time.minusHours(i);
				if (!(tmp.isBefore(openingTime))) {
					from = tmp;
					break;
				}
			}
		}
		tmp = time.minusHours(3);
		tmp = tmp.minusMinutes(30);
		if (!(tmp.isBefore(openingTime)))
			from = tmp;

		for (int i = 3; i >= 0; i--) {
			tmp = time.plusHours(i);
			if (tmp.isBefore(closingTime)) {
				to = tmp;
				break;
			}
		}
		tmp = time.plusHours(3);
		tmp = tmp.plusMinutes(30);
		if (tmp.isBefore(closingTime))
			to = tmp;

		System.out.println("The interval for " + time.toString());

		/*
		 * Send the date, time number of visitors and wanted park to the db in the db
		 * will return the amount of people in the park for the boudry time set above in
		 * new method in the OrderController will check if : Add new order for the
		 * current orders be possible if the amount of visitors in total will be greater
		 * then how many visitors can enter will show the unproved order screen
		 */
		String fromB = from.toString();
		String toB = to.toString();
		System.out.println(fromB);
		StringBuffer sb = new StringBuffer();
		sb.append("canMakeOrder");
		sb.append(" ");
		sb.append(from.toString());
		sb.append(":00");
		sb.append(" ");
		sb.append(to.toString());
		sb.append(":00");
		sb.append(" ");
		sb.append(wantedPark);
		sb.append(" ");
		sb.append(dateOfVisit.toString());
		ClientUI.chat.accept(sb.toString());
	}

	/*
	 * check if the phone and numbers are valid will check if the phone containing
	 * 10 digits. will check if all the string of the phone is not a char but a
	 * number if will find a char there will enter to the cache.
	 */
	public boolean checkValidValues(String phone, String email) {
		if (phone.length() == 10) {
			String[] tmp = email.split("@");
			try {
				int areDigits = Integer.parseInt(phone);
			} catch (Exception e) {
				return false;
			}

			if (tmp.length == 2)
				return true;
		}
		return false;
	}

	public boolean getValid() {
		return valid;
	}

	public void setEmailAndPhone(String email, String phone) {
		setEmail(email);
		setPhone(phone);
	}

//This method will be the connector from the data came from the server to this controller
	public void gotMessage(String[] msg) throws IOException {
		String cases = msg[0];
		switch (cases) {
		case "canMakeOrder":
			checkIfCanMakeOrder(msg);
			break;
		case "getExsistingOrders":
			fillExsistingOrders(msg);
			break;
		case "getDataForReport":
			fillReportTableData(msg);
			break;

		}

	}

//This method will create a table row for the report.
	private void fillReportTableData(String[] msg) {
		System.out.println(msg[1] + "This is the cancel Amount");

		System.out.println(msg[2] + "this is confirmed");
		Number numOfCancel;
		Number numOfConfirm;
		CancelReportData crd = null;
		try {
			numOfCancel = NumberFormat.getInstance().parse(msg[1]);
			numOfConfirm = NumberFormat.getInstance().parse(msg[2]);
			crd = new CancelReportData(ReportYear, ReportMonth, numOfCancel, numOfConfirm);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		oR.add(crd);

	}

// array[0]=methodName, array[1]=data
	private void fillExsistingOrders(String[] ordersArray) {
		int counter = 1;
		int check = 1;
		Data d;
		if (!(ordersArray[1].equals("Done"))) {
			while (!(ordersArray[counter].equals("Done"))) {
				d = new Data(ordersArray[counter], ordersArray[counter + 1], ordersArray[counter + 2],
						ordersArray[counter + 3], ordersArray[counter + 4], ordersArray[counter + 5]);
				ob.add(d);
				counter += 6;
				check++;

			}
			System.out.println(check);

		}

	}

	/*
	 * This method get from the server all the numbers how many can enter how many
	 * visitors overall in the gap if adding will make the maxVisit bigger, move to
	 * unapproved order
	 */

	public void checkIfCanMakeOrder(String[] msg) throws IOException {

		int currentVisitorsAtBoundry = Integer.parseInt(msg[1]);

		int availableVisitors = Integer.parseInt(msg[2]);
		if (currentVisitorsAtBoundry + order.getNumberOfVisitors() > availableVisitors)
			valid = false;
		else
			valid = true;
	}

	public void setPhone(String phone) {
		this.currentPhone = phone;
	}

	public void setEmail(String email) {
		this.currentEmail = email;
	}

	public String getPhone() {
		return currentPhone;
	}

	public String getEmail() {
		return currentEmail;
	}

	public void wantToCancel(Stage stage) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/GUI/SureIfCancel.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Cancel order");
		stage.setScene(scene);
		stage.show();

	}

	public void getAlternativeDates(LocalTime timeForVisit) throws IOException {
		alternativeDates.clear();
		Data d;
		aD.removeAll(aD);
		n_order = false;
		LocalDate date = order.getDateOfVisit();
		LocalTime time = timeForVisit;
		String park = order.getWantedPark();
		int numOfVisit = order.getNumberOfVisitors();
		for (int i = 1; i <= 7; i++) {
			LocalDate toDate = date.plusDays(i);
			this.canMakeOrder(time, toDate, park, "f", numOfVisit);
			if (valid)
				alternativeDates.add(toDate.toString());

			for (String var : alternativeDates) {
				d = new Data("4", var, order.getWantedPark(), time.toString(),
						Integer.toString(order.getNumberOfVisitors()), Float.toString(order.getTotalPrice()));

				aD.add(d);

			}

		}

	}

//This method will be responsible for saving the order into the db
	public void confirmOrder() {

		StringBuffer sb = new StringBuffer();
		sb.append("confirmOrder");
		sb.append(" ");
		sb.append(order.getTimeInPark().toString());
		sb.append(":00");
		sb.append(" ");
		sb.append(order.getDateOfVisit().toString());
		sb.append(" ");
		sb.append(order.getWantedPark());
		sb.append(" ");
		sb.append(Float.toString(order.getTotalPrice()));
		sb.append(" ");

		// !!!!!!! NEED TO BE !!!!!!
		// sb.append(t.getId());

		sb.append("6");
		sb.append(" ");
		// !!!!!!! NEED TO BE !!!!!!
		// sb.append(t.getTYpe());
		sb.append("F");
		sb.append(" ");
		sb.append(Integer.toString(order.getNumberOfVisitors()));
		sb.append(" ");
		sb.append("Confirmed");
		order = null;
		ClientUI.chat.accept(sb.toString());

	}

	public void cancelOrder(String dateOfVisit, String wantedPark, String timeOfVisit) {
		// String id = traveller.getId();
		if (isInDb) {
			String id = "4";
			StringBuffer sb = new StringBuffer();
			sb.append("cancelOrder");
			sb.append(" ");
			sb.append(timeOfVisit);
			sb.append(":00");
			sb.append(" ");
			sb.append(dateOfVisit);
			sb.append(" ");
			sb.append(wantedPark);
			sb.append(" ");
			sb.append(id);
			ClientUI.chat.accept(sb.toString());
		} else
			order = null;
		isInDb = false;

	}

	/*
	 * This method will fill the observable value with all the exsisting orders of
	 * the current person
	 */
	public void getExsistingOrders() {
		StringBuffer sb = new StringBuffer();
		sb.append("getExsistingOrders");
		sb.append(" ");
		sb.append("4");
		ClientUI.chat.accept(sb.toString());

	}

	/*
	 * Method that will change the status of the order of the traveller from
	 * confirmed to entered
	 */
	public void setEnterOrder(String Id, String wantedPark, String dateOfVisit, String TimeInPark) {

		StringBuffer sb = new StringBuffer();
		sb.append("setEnterOrder");
		sb.append(" ");
		sb.append(TimeInPark);
		sb.append(" ");
		sb.append(dateOfVisit);
		sb.append(" ");
		sb.append(wantedPark);
		sb.append(" ");
		sb.append(Id);
		sb.append(" ");

		ClientUI.chat.accept(sb.toString());

	}

	public void ChangeToWaitOrder(Order getOr) {
		StringBuffer sb = new StringBuffer();
		sb.append("ChangeToWaitOrder");
		sb.append(" ");
		sb.append(getOr.getTimeInPark().toString());
		sb.append(" ");
		sb.append(getOr.getDateOfVisit().toString());
		sb.append(" ");
		sb.append(getOr.getWantedPark());
		sb.append(" ");
		sb.append(t.getType());
		sb.append(" ");
		ClientUI.chat.accept(sb.toString());

	}

//private String ReportMonth;
//private String ReportYear;
	public void getDataForReport(LocalDate fromDate, LocalDate toDate) {
		ReportMonth = Integer.toString(fromDate.getMonthValue());
		ReportYear = Integer.toString(fromDate.getYear());
		StringBuffer sb = new StringBuffer();
		sb.append("getDataForReport");
		sb.append(" ");
		sb.append(fromDate.toString());
		sb.append(" ");
		sb.append(toDate.toString());
		ClientUI.chat.accept(sb.toString());
	}

}

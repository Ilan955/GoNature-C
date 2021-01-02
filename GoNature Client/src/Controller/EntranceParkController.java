
package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import Client.ClientUI;
import Entities.*;

public class EntranceParkController {
	public TravellerInPark travellerinpark;
	public boolean travellerExistsInDB=false;
	public boolean	OrderExistsInDB=false;
	
	public void enterWithoutOrder(LocalTime timeOfVisit, LocalDate dateOfVisit, String wantedPark, int numOfVisitors,
			float price) {
		travellerinpark = new TravellerInPark(timeOfVisit, dateOfVisit, wantedPark, numOfVisitors, price);

		StringBuffer sb = new StringBuffer();
		sb.append("enterWithoutOrder");
		sb.append(" ");
		// !!!!!!! NEED TO BE !!!!!!
		// sb.append(t.getId());
		sb.append("6");
		sb.append(" ");
		sb.append(Integer.toString(travellerinpark.getnumOfVisitors()));
		sb.append(" ");
		sb.append(travellerinpark.getdateOfVisit().toString());
		sb.append(" ");
		sb.append(travellerinpark.gettimeInPark().withNano(0).toString());
		//sb.append(":00");
		sb.append(" ");
		sb.append(Float.toString(travellerinpark.gettotalPrice()));
		sb.append(" ");
		sb.append(travellerinpark.getwantedPark());
		sb.append(" ");
		ClientUI.chat.accept(sb.toString());

	}

	public void setNumOfVisitorEntringPark(String wantedpark, int numOfVisitors) throws IOException {
		int unexpectedVisitorsToUpdate = ClientUI.parkController.getCurrentUnexpectedVisitors(wantedpark) + numOfVisitors;
		StringBuffer sb = new StringBuffer();
		sb.append("setNumOfVisitorEntringPark");
		sb.append(" ");
		sb.append(wantedpark);
		sb.append(" ");
		sb.append(unexpectedVisitorsToUpdate);
		String res = sb.toString();
		System.out.println(res);
		ClientUI.chat.accept(res);
	}
	
	public void checkIfTravellerExistsInPark(String id) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("checkIfTravellerExistsInPark");
		sb.append(" ");
		sb.append(id);
		String res = sb.toString();
		ClientUI.chat.accept(res);
	}
	
	public void setCurrentVisitros(String wantedpark, int numOfVisitors) throws IOException {
		int currentVisitorsToUpdate = ClientUI.parkController.getCurrentVisitors(wantedpark) + numOfVisitors;
		StringBuffer sb = new StringBuffer();
		sb.append("setCurrentVisitros");
		sb.append(" ");
		sb.append(wantedpark);
		sb.append(" ");
		sb.append(currentVisitorsToUpdate);
		String res = sb.toString();
		System.out.println(res);
		ClientUI.chat.accept(res);
	}
	
	public void updateExitTimeForTravellerWithOrder(String wantedpark, String id) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("updateExitTimeForTravellerWithOrder");
		sb.append(" ");
		sb.append(wantedpark);
		sb.append(" ");
		sb.append(id);
		String res = sb.toString();
		System.out.println(res);
		ClientUI.chat.accept(res);
	}
	
	public void updateExitTimeForcasualTraveller(String wantedpark, String id) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("updateExitTimeForcasualTraveller");
		sb.append(" ");
		sb.append(wantedpark);
		sb.append(" ");
		sb.append(id);
		String res = sb.toString();
		System.out.println(res);
		ClientUI.chat.accept(res);
	}
	
	public boolean IfgetTravellerInParkExistInDB(String id) throws IOException {
		checkIfTravellerExistsInPark(id);
		return travellerExistsInDB;
	}
	public void getTravellerInParkDetails(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append("getTravellerInParkDetails");
		sb.append(" ");
		sb.append(id);
		String res = sb.toString();
		ClientUI.chat.accept(res);
	}
	
	
	public void checkIfOrderExistsInParkAndConfirmed(String id) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("checkIfOrderExistsInParkAndConfirmed");
		sb.append(" ");
		sb.append(id);
		String res = sb.toString();
		ClientUI.chat.accept(res);
	}
	public boolean IfgetOrderInParkExistInDB(String id) throws IOException {
		checkIfOrderExistsInParkAndConfirmed(id);
		return OrderExistsInDB;
	}
	
	public void getOrderDetailsForExitPark(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append("getOrderDetailsForExitPark");
		sb.append(" ");
		sb.append(id);
		String res = sb.toString();
		ClientUI.chat.accept(res);
	}
	
	public void gotMesage(String[] msg) {
		String cases = msg[0];
		switch (cases) {
		case "checkIfTravellerExistsInPark":
			if ((msg[1].toString()).equals("true"))
				travellerExistsInDB = true;
			else
				travellerExistsInDB = false;
			break;
			
		case "getTravellerInParkDetails":
			travellerinpark=null;
			travellerinpark=new TravellerInPark(Integer.parseInt(msg[1]),msg[2]);
		
		case "checkIfOrderExistsInParkAndConfirmed":
			if ((msg[1].toString()).equals("true"))
				OrderExistsInDB = true;
			else
				OrderExistsInDB = false;
			break;
		case "getOrderDetailsForExitPark":
			ClientUI.orderController.order=null;
			ClientUI.orderController.order=new Order(Integer.parseInt(msg[1]),msg[2]);
			
		}
	}

}
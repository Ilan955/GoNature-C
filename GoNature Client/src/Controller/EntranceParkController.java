
package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import Client.ClientUI;
import Entities.TravellerInPark;

public class EntranceParkController {
	public TravellerInPark travellerinpark;

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

	/*
	 * this method handle the GoClient data
	 */
	//public void gotMesage(String[] msg) {
	//	String cases = msg[0];
	//	switch (cases) {
	//	}

	//}

}
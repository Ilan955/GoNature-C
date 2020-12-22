package Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import Client.ClientUI;

import Entities.Order;

public class DiscountController {
	//ManagerDiscounts mDiscount;
	public boolean setManagerDiscount_flag;
	public boolean checkDiscount_flag;// update by ValidDiscount > gotMessage > checkDiscount
	private float discountPrecentage;

	//use this method to create a new manager discount in DB (called by setDiscountScreenController.whenClickSubmitDiscount())/
	public void setManagerDiscount(Date startDate, Date lastDate, float precentage, String parkName) {
		// mDiscount = new ManagerDiscounts(startDate, lastDate, precentage, parkName);
		/* record discount to db */
		StringBuffer sb = new StringBuffer();
		sb.append("setManagerDiscount");// method name
		sb.append(" ");
		sb.append(startDate.toString());
		sb.append(" ");
		sb.append(lastDate.toString());
		sb.append(" ");
		sb.append(precentage);
		sb.append(" ");
		sb.append(parkName);

		ClientUI.chat.accept(sb.toString());

	}

	public void ValidDiscount(Date dateOfVisit, String parkName) {
		/* check in db for Discounts */
		StringBuffer sb = new StringBuffer();
		sb.append("ValidDiscount");// methode name
		sb.append(" ");
		sb.append(parkName);
		sb.append(" ");
		sb.append(dateOfVisit.toString());

		ClientUI.chat.accept(sb.toString());

	}
	
	//use this method to calc finalPrice with managerDiscount/
	//!!!!does not include regular discount!!!!!!
	public float calculateFinalPrice(Order order) {
		//check for valid parkManager discount/
		//ValidDiscount(order.getDateOfVisit(),order.getWantedPark());
		if(checkDiscount_flag) // there is a valid discount for this park and timeOfVisit
		{
			return order.getTotalPrice() * discountPrecentage; // full price after parkManager discount
		}
		return order.getTotalPrice();//return full price without parkManager discount
	}

	public void checkDiscount(String fromDate, String toDate, String status, String dateOfVisit, String discount) {
		/* update checkDiscount_flag && discountPrecentage */
		if (status.equals("T")) {
			Date From = Date.valueOf(fromDate);
			Date To = Date.valueOf(toDate);
			Date Visit = Date.valueOf(dateOfVisit);

			if (From.before(Visit) && To.after(Visit)) {
				checkDiscount_flag = true;
				discountPrecentage = Float.valueOf(discount);
				return;
			}
		}
		checkDiscount_flag = false;

	}

	public void set_ManagerDiscount_Flag(String str) {
		if (str.equals("false"))
			setManagerDiscount_flag = false;
		else
			setManagerDiscount_flag = true;
	}

	// This method will be the connector from the data came from the server to this
	// controller
	public void gotMessage(String[] msg) throws IOException {
		String cases = msg[0];
		switch (cases) {
		case "setManagerDiscount":
			set_ManagerDiscount_Flag(msg[1]);
			break;
		case "ValidDiscount":
			// [methodeName[0] , parkName[1], fromDate[2] , toDate[3] , precentage[4] ,
			// status[5] , dateOfVisit[6]]
			// e.x [ValidDiscount, tal, 2018-12-12, 2020-01-01, 0.5, F , 2019-12-31]
			checkDiscount(msg[2], msg[3], msg[5], msg[6] , msg[4]);
			break;

		}

	}

}
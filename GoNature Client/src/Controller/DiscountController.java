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

	public void ValidDiscount(LocalDate dateOfVisit, String parkName) {
		/* check in db for a valid Discount */
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
		/*check for valid parkManager discount*/
		ValidDiscount(order.getDateOfVisit(),order.getWantedPark());
		if(checkDiscount_flag)//there is a valid manager discount for this order
			return (order.getTotalPrice() * (1-discountPrecentage)); // return price after manager discount
		else
			return order.getTotalPrice();//return original price without manager discount
	}

	/* update checkDiscount_flag && discountPrecentage */
	public void checkDiscount(String precentage_str) {
		discountPrecentage = Float.valueOf(precentage_str);
		if(discountPrecentage == -1) //invalid
			checkDiscount_flag = false;
		else 
			checkDiscount_flag = true;
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
			checkDiscount(msg[1]);
			break;

		}

	}

}
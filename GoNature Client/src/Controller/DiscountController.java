package Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import Client.ClientUI;
import Entities.Order;

public class DiscountController {
	// ManagerDiscounts mDiscount;
	public boolean setManagerDiscount_flag;
	public boolean checkDiscount_flag;// update by ValidDiscount > gotMessage > checkDiscount
	private float discountPrecentage;

	private float finalPriceWithoutDM;

	// use this method to create a new manager discount in DB (called by
	// setDiscountScreenController.whenClickSubmitDiscount())/
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

	// use this method to calc finalPrice with managerDiscount/
	// !!!!does not include regular discount!!!!!!
	public float calculateFinalPrice(Order order) {
		ValidDiscount(order.getDateOfVisit(), order.getWantedPark());
		/* check for valid parkManager discount */
		if (checkDiscount_flag)// there is a valid manager discount for this order
			return (order.getTotalPrice() * (1 - discountPrecentage)); // return price after manager discount
		else
			return order.getTotalPrice();// return original price without manager discount
	}

	public float getFinalPriceWithoutDM() {
		return finalPriceWithoutDM;
	}

	public void setFinalPrice(float fp) {
		this.finalPriceWithoutDM = fp;

	}

	/* update checkDiscount_flag && discountPrecentage */
	public void checkDiscount(String precentage_str) {
		discountPrecentage = Float.valueOf(precentage_str);
		if (discountPrecentage == -1) // invalid
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

		case "getTotalPrice":
			updateTotalPrice(msg);
			break;
		}

	}
	/*
	 * msg[1] = numOfVisitors msg[2]= ifMember msg[3] = price,
	 * msg[4]=valueDiscount,msg[5]=MemberDisc
	 * 
	 * 1. get the data from the db to a local string 2. check how many visitors are
	 * 3. check the precentage of discount he can get based of his type 4. check if
	 * he a member or not, if yes, add extra discount precantage
	 */

	private void updateTotalPrice(String[] msg) {

		// d[0]=depPrice , d[1] = valueDiscount, d[2] = MemberDiscount
		float totalDiscount = 0;

		if (!(msg[4].equals("-")))
			totalDiscount += Float.parseFloat(msg[4]) / 100;
		if (msg[2].equals("True"))
			if (!(msg[5].equals("-")))
				totalDiscount += Float.parseFloat(msg[5]) / 100;

		float finalPrice = Float.parseFloat(msg[3]) * totalDiscount * Integer.parseInt(msg[1]);
		finalPriceWithoutDM = finalPrice;

	}

	/*
	 * Method that will calculate the total price for the travveler based on the
	 * stats that are in the Db This method need the type of person, how many
	 * visitors, and if he ordering a futre order or came into the park and ordering
	 * there
	 */
	public void getTotalPrice(String typeOfMember, int numberOfVisitors, String orderKind, String isMember) {

		StringBuffer sb = new StringBuffer();
		sb.append("getTotalPrice");
		sb.append(" ");
		switch (typeOfMember) {

		case "PreOrderedTraveller":
			if (orderKind.equals("FutreOrder"))
				sb.append("OrderdIOF");
			else
				sb.append("PreOrderdIOF");
			break;

		case "FamilyMember":
			if (orderKind.equals("FutreOrder"))
				sb.append("OrderdIOF");
			else
				sb.append("PreOrderdIOF");
			break;

		case "GroupGuide":
			if (orderKind.equals("FutreOrder"))
				sb.append("OrderdG");

			else
				sb.append("PreOrderdG");
			break;
		}
		sb.append(" ");
		sb.append(numberOfVisitors);
		sb.append(" ");
		sb.append(isMember);
		sb.append(" ");

		ClientUI.chat.accept(sb.toString());

	}

}
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
	private float finalPriceWithoutDM;
	
	
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
	
	public float getFinalPriceWithoutDM() {
		return finalPriceWithoutDM;
	}

	public void setFinalPrice(float fp) {
		this.finalPriceWithoutDM=fp;
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
			
		case "getTotalPrice":
			updateTotalPrice(msg);

		}

	}
	/*
	 * msg[1] = numOfVisitors msg[2]= ifMember msg[3] = price, msg[4]=valueDiscount,msg[5]=MemberDisc
	 * 
	 * 	1. get the data from the db to a local string
	 * 	2. check how many visitors are 
	 * 	3. check the precentage of discount he can get based of his type
	 * 	4. check if he a member or not, if yes, add extra discount precantage
	 */
	
	private void updateTotalPrice(String[] msg) {
		
		
		//d[0]=depPrice , d[1] = valueDiscount, d[2] = MemberDiscount
		float totalDiscount=0;
		
			if(!(msg[4].equals("-")))
					totalDiscount+= Float.parseFloat(msg[4])/100;
			if(msg[2].equals("True"))
				if(!(msg[5].equals("-")))
				totalDiscount+=Float.parseFloat(msg[5])/100;
			
		float finalPrice = Float.parseFloat(msg[3])*totalDiscount*Integer.parseInt(msg[1]);		
		finalPriceWithoutDM = finalPrice;
	
	}

	/*
	 * Method that will calculate the total price for the travveler based on the stats that are in the Db
	 * This method need the type of person, how many visitors, and if he ordering a futre order or came into the park and ordering there
	 */
	public void getTotalPrice(String typeOfMember,int numberOfVisitors,String orderKind,String isMember) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("getTotalPrice");
		sb.append(" ");
		switch (typeOfMember) {
		
		case "PreOrderedTraveller":
			if(orderKind.equals("FutreOrder"))
				sb.append("OrderdIOF");	
			else
				sb.append("PreOrderdIOF");
			break;
			
			
		case "FamilyMember":
			if(orderKind.equals("FutreOrder"))
					sb.append("OrderdIOF");	
				else
					sb.append("PreOrderdIOF");
			break;
			
		case "GroupGuide":
			if(orderKind.equals("FutreOrder"))
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
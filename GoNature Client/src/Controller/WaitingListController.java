package Controller;

import java.io.IOException;
import java.sql.Date;

import Client.ClientUI;
import Entities.Order;

public class WaitingListController {

	public boolean enterWaitingList_Flag;

	public void sendMessageFirstWaitingList(Order cancelledOrder) {
		StringBuffer sb = new StringBuffer();
		sb.append("sendMessageFirstWaitingList");// method name
		sb.append(" ");
		sb.append(cancelledOrder.getOrderNum());

		ClientUI.chat.accept(sb.toString());
	}

	// d
	public void enterWaitingList(Order orderToWaitingList) {
		StringBuffer sb = new StringBuffer();
		sb.append("enterWaitingList");// method name
		sb.append(" ");
		sb.append(orderToWaitingList.getWantedPark());// parkName
		sb.append(" ");
		sb.append(orderToWaitingList.getDateOfVisit());
		sb.append(" ");
		sb.append(orderToWaitingList.getTimeInPark());
		sb.append(" ");
		sb.append(orderToWaitingList.getNumberOfVisitors());
		sb.append(" ");
		sb.append(orderToWaitingList.getOrderNum());

		ClientUI.chat.accept(sb.toString());
	}

	// remove a single waiting order from waitinglist and update other waiters
	// placeInLine
	public void removeFromWaitingList(Order orderToRemove) {
		/// should remove orderToRemove from all ordersToWaitFor/
		StringBuffer sb = new StringBuffer();
		sb.append("enterWaitingList");// method name
		sb.append(" ");
		sb.append(orderToRemove.getOrderNum());

		ClientUI.chat.accept(sb.toString());
	}

	/*
	 * removeAllwaiters: remove All waiting orders from waitinglist for this
	 * orderToRemove_AllWaiters use this because original order executed
	 * successfully!
	 */
	public void removeAllwaiters(Order orderToRemove_AllWaiters) {
		StringBuffer sb = new StringBuffer();
		sb.append("removeAllwaiters");// method name
		sb.append(" ");
		sb.append(orderToRemove_AllWaiters.getOrderNum());

		ClientUI.chat.accept(sb.toString());

	}

	public void gotMessage(String[] msg) throws IOException {
		String cases = msg[0];
		switch (cases) {
		case "enterWaitingList":
			set_enterWaitingList_Flag(msg[1]);
			break;

		case "sendMessageFirstWaitingList":
			sendMessage(msg);
			break;

		case "removeFromWaitingList":
			set_removeFlag(msg[1]);
			break;

		case "removeAllWaiters":
			break;
		}

	}

	private void set_removeFlag(String str) {
		if (str.equals("false"))
			enterWaitingList_Flag = false;
		else
			enterWaitingList_Flag = true;
	}

	private void sendMessage(String[] msg) {
		// msg =
		// [sendMessageFirstWaitingList[0],id[1],type[2],numOfVisitors[3],orderNum[4],TimeInPark[5],DateOfVisit[6],wantedPark[7],TotalPrice[8],status[9]]

		if (msg[1].equals("null"))// there is no waiting order!
			return;

		// else send email and sms to first in line , and give him 1 hour to confirm (if
		// passed , should do the same for next in line)/

	}

	private void set_enterWaitingList_Flag(String str) {
		if (str.equals("false"))
			enterWaitingList_Flag = false;
		else
			enterWaitingList_Flag = true;
	}
}
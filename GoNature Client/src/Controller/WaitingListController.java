package Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import Client.ClientUI;
import Entities.Order;

public class WaitingListController {

	public boolean enterWaitingList_Flag;
	
	void enterWaitingList(int orderNumber) {
		StringBuffer sb = new StringBuffer();
		sb.append("enterWaitingList");// method name
		sb.append(" ");

		sb.append(orderNumber);
		ClientUI.chat.accept(sb.toString());
	}

	public void gotMessage(String[] msg) throws IOException {
		String cases = msg[0];
		switch (cases) {
		case "enterWaitingList":
			set_enterWaitingList_Flag(msg[1]);
			break;
		}
	}

	private void set_enterWaitingList_Flag(String str) {
		if (str.equals("false"))
			enterWaitingList_Flag = false;
		else
			enterWaitingList_Flag = true;
	}
}
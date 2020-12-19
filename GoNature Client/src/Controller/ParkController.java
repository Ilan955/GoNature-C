package Controller;

import java.io.IOException;

import Client.ClientUI;
import Entities.*;

public class ParkController {
	
	public void DetailsPark(String parkName) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("DetailsPark");
		sb.append(" ");
		sb.append(parkName);
		String res = sb.toString();
		System.out.println(res);
		ClientUI.chat.accept(res);
	}
	
	public static int getCurrentVisitors(String parkName) {
		return Park.currentVisitors;
	}
	
	public static int getCurrentUnexpectedVisitors(String parkName) {
		return Park.AmountOfUnExpectedTravellers;
	}
	
	public static int getMaxAvailableVisitors(String parkName) {
		return Park.maxAvailableVisitors;
	}
	
	public static int getMaxVisitors(String parkName) {
		return Park.maxVisitors;
	}
	
	
	public boolean parkIsFull(String parkName,int numOfVisitors) {
		int unExpectedVisitors=getMaxVisitors(parkName)-getMaxAvailableVisitors(parkName);
		if(numOfVisitors+getCurrentUnexpectedVisitors(parkName)>unExpectedVisitors)
			return false;
		return true;
	}

	public void gotMessage(String[] msg)  {
		String cases = msg[0];
		switch (cases) {
		case "DetailsPark":
			Park.currentVisitors=Integer.parseInt(msg[1]);
			Park.AmountOfUnExpectedTravellers=Integer.parseInt(msg[2]);
			Park.maxAvailableVisitors=Integer.parseInt(msg[3]);;
			Park.maxVisitors=Integer.parseInt(msg[4]);
			break;

		}

	}
}
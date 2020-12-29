package Controller;

import java.io.IOException;

import Client.ClientUI;
import Entities.Park;

public class ParkController {
	public Park park;
	private String parkname;
//	public int maxVisitors,maxAvailableVisitoirs,currentVisitors,maxDuration,unExpected;
	
	public void DetailsPark(String parkName) throws IOException {
		this.parkname=parkName;
		StringBuffer sb = new StringBuffer();
		sb.append("DetailsPark");
		sb.append(" ");
		sb.append(parkName);
		String res = sb.toString();
		ClientUI.chat.accept(res);
	}
	
	public int getCurrentVisitors(String parkName) throws IOException {
		ClientUI.parkController.DetailsPark(parkName);
		return park.getCurrentVisitors();
	}
	
	public int getCurrentUnexpectedVisitors(String parkName) throws IOException {
		ClientUI.parkController.DetailsPark(parkName);
		return park.getAmountOfUnExpectedTravellers();
	}
	
	public int getMaxAvailableVisitors(String parkName) throws IOException {
		ClientUI.parkController.DetailsPark(parkName);
		return park.getMaxAvailableVisitors();
	}
	
	public int getMaxVisitors(String parkName) throws IOException {
		ClientUI.parkController.DetailsPark(parkName);
		return park.getMaxVisitors();
	}
	public float getMaxDuration(String parkName) throws IOException {
		ClientUI.parkController.DetailsPark(parkName);
		return park.getMaxDurationVisit();
	}
	
	public boolean parkIsFull(String parkName,int numOfVisitors) throws IOException {
		int unExpectedVisitors=getMaxVisitors(parkName)-getMaxAvailableVisitors(parkName);
		if(numOfVisitors+getCurrentUnexpectedVisitors(parkName)>=unExpectedVisitors)
			return true;
		return false;
	}

	public void gotMessage(String[] msg)  {
		String cases = msg[0];
		switch (cases) {
		case "DetailsPark":
			park=null;
			park=new Park(parkname,Integer.parseInt(msg[1]),Integer.parseInt(msg[2]),Integer.parseInt(msg[3]),Integer.parseInt(msg[4]),0);			
			break;

		}

	}
}
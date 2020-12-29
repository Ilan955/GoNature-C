package Controller;

import Client.ClientUI;
//import Entities.departmentEmployee;
import Entities.*;
import GUI.Data;
import GUI.parkPendingRData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeController {
	//!!!! WHAT NEED TO BE !!!!
	//departmentEmployee emp = ClientUI.userController.departmentEmployee
	String firstName,lastName,type,parkName;
	private boolean isValidUserName;
	private boolean isPasswordsMatch;
	public ObservableList<parkPendingRData> parkSettingChangeRequests = FXCollections.observableArrayList();
	//private departmentEmployee employee;
	

	public boolean isValidEmployee() {
		return isValidUserName;
	}
	public boolean isMatchingPasswords() {
		return isPasswordsMatch;
	}
	public void identify(String s) {
		ClientUI.chat.accept(s);
	}
	public void sendChangesToDepartmentManager(String s) {
		ClientUI.chat.accept(s);
	}
	public void gotMessage(String action, String[] info)
	{
		switch (action) {
		case "IdentifyEmployee": //Employee exist in our DB 
				isValidUserName = true;
				isPasswordsMatch = true;
				firstName=info[0];
				lastName=info[1];
				type=info[3];
				parkName=info[7];
			 	break;
		case "IdentifyNotExistingEmployee": //UserName does not exist on our DB
			isValidUserName = false;
			break;
		case "IdentifyPasswordDoesNotMatch": //Means that passwords do not match
			isPasswordsMatch = false;
			isValidUserName=true;
			break;
		case "displayParkSettingsRequestsToDepartmentManager":
			fillParkSettingRequests(info);
			break;
		default:
			System.out.print("Don't know what to do");
		}
	}
		public String getFirstName() {
			return this.firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName=firstName;
		}
		public String getLastName() {
			return this.lastName;
		}
		public void setLastName(String lastName) {
			this.lastName=lastName;
		}
		public String getType() {
			return this.type;
		}
		public void setType(String type) {
			this.type=type;
		}
		public String getParkName() {
			return this.parkName;
		}
		public void setParkName (String parkName) {
			this.parkName=parkName;
		}
		private void fillParkSettingRequests(String[] ordersArray) {
			int counter=0;
			parkSettingChangeRequests.removeAll(parkSettingChangeRequests);
			parkPendingRData pprd;
			if(!(ordersArray[1].equals("Done"))) {
				while(!(ordersArray[counter].equals("Done"))) {
					 pprd= new parkPendingRData(ordersArray[counter],ordersArray[counter+1],ordersArray[counter+2],ordersArray[counter+3],ordersArray[counter+4],ordersArray[counter+5],ordersArray[counter+6]);
					 parkSettingChangeRequests.add(pprd);
					counter+=7;	
				}
			}
			
		}
		public void goAndUpdateRequestStatusInDB(String s) {
			ClientUI.chat.accept(s);
		}
		public void goAndChangeParkSettingsInDB(String s) {
			ClientUI.chat.accept(s);
		}
}

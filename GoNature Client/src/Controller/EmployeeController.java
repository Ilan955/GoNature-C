package Controller;

import Client.ClientUI;
//import Entities.departmentEmployee;
import Entities.*;

public class EmployeeController {
	//!!!! WHAT NEED TO BE !!!!
	//departmentEmployee emp = ClientUI.userController.departmentEmployee
	String firstName,lastName,type,parkName;
	private boolean isValidUserName;
	private boolean isPasswordsMatch;
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
				//departmentEmployee t_emp = new departmentEmployee (info[0],info[1],info[2],info[3],info[4],info[5],info[6],info[7],info[8],info[9]);
				//System.out.println("Dep" + t_emp.getFirstName() + " " + t_emp.getLastName());
				//ClientUI.employeeController.employee = t_emp;
			 	// ClientUI.employeeController.setEmployee(new departmentEmployee (info[0],info[1],info[2],info[3],info[4],info[5],info[6],info[7],info[8],info[9]));
			 	//System.out.print("Hello from Employee: " + getEmployee().getUserName() + " " + getEmployee().getPassword());
			 	break;
		case "IdentifyNotExistingEmployee": //UserName does not exist on our DB
			isValidUserName = false;
			break;
		case "IdentifyPasswordDoesNotMatch": //Means that passwords do not match
			isPasswordsMatch = false;
			isValidUserName=true;
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

}

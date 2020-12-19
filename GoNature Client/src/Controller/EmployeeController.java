package Controller;

import Client.ClientUI;

import Entities.departmentEmployee;

public class EmployeeController {
	//!!!! WHAT NEED TO BE !!!!
	//departmentEmployee emp = ClientUI.userController.departmentEmployee
	public departmentEmployee employee = null;
	private boolean isValidUserName;
	private boolean isPasswordsMatch;
	
	/*
	public void getEmployeeDetails(String st)
	{
		StringBuffer str = new StringBuffer();
		str.append("getEmployeeDetails ");
		str.append(st);
		String s = str.toString();
		ClientUI.chat.accept(s);
		// now emp is updated with this specific employee fields from DB
		
		
	}
	*/
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
		case "IdentifyEmployee": //Employee exist in our DB and its a department employee
			 isValidUserName = true;
			 isPasswordsMatch = true;
			 ClientUI.employeeController.employee = new departmentEmployee (info[0],info[1],info[2],info[3],info[4],info[5],info[6],info[7],info[8],info[9]);
			 System.out.print("Hello from Employee: " + employee.getUserName() + " " + employee.getPassword());
			 break;
	//	case "": //Employee exists and its a Park manager
		//	ClientUI.employeeController.employee = new departmentEmployee();
			//break;
/*
		case ""
		
		break;
		
		case ""
		break;
*/
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
}

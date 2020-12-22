package Controller;

import java.io.IOException;

import Client.ClientUI;
import Entities.Person;

import Entities.departmentEmployee;




public class UserController {
	public Person traveller = null;
	private boolean changeScreen = false;
	public void identify(String str) throws IOException
	{
			 ClientUI.chat.accept(str);
	}
	public void setChangeScreen(boolean toChange)
	{
		this.changeScreen = toChange;
	}
	public boolean getChangeScreen()
	{
		return this.changeScreen;
	}
	public void gotMessage(String action, String[] info)
	{
		switch (action) {
		case "IdentifyTraveller": //Traveller exist in our DB
			 ClientUI.userController.traveller = new Person(info[0],info[1],info[2],info[3],info[4]);
			 ClientUI.userController.traveller.setId(info[5]);
			 ClientUI.userController.traveller.setCreditCardNumber(info[6]);
			 ClientUI.userController.traveller.setNumberOfVisitors(Integer.parseInt(info[7]));
			 ClientUI.userController.traveller.setMemberID(info[8]);
			 break;
		case "IdentifyNotExistingTraveller": //Traveller does not exist on our DB, making a default one
			ClientUI.userController.traveller = new Person("Traveller","",null,"",null);
			break;
		default:
			System.out.print("Don't know what to do");
		}
		
	}

}

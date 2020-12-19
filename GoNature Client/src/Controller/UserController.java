package Controller;

import java.io.IOException;

import Client.ClientUI;
import Entities.Person;
import Entities.Traveller;
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
			 break;
		case "IdentifyNotExistingTraveller": //Traveller does not exist on our DB, making a default one
			ClientUI.userController.traveller = new Person("Traveller",null,null,"Pre order traveller",null);
			break;
		//case "": //Employee exists in DB
			
	//	case "": //Employee does not exist --> need to reload scene and ask for valid a username or matching password 
		default:
			System.out.print("Don't know what to do");
		}
		
	}

}

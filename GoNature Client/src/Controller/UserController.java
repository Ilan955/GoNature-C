package Controller;

import java.io.IOException;

import Client.ClientUI;
import Entities.Person;

public class UserController {
	public Person traveller = null;
	private boolean changeScreen = false;
	private boolean alreadyLoggedIn = false;

	public void identify(String str) throws IOException {
		ClientUI.chat.accept(str);
	}

	public void goToDbForDepManagerRequest(String str) {
		ClientUI.chat.accept(str);
	}

	public void setChangeScreen(boolean toChange) {
		this.changeScreen = toChange;
	}

	public boolean getChangeScreen() {
		return this.changeScreen;
	}

	public void gotMessage(String action, String[] info) {
		switch (action) {
		case "IdentifyTraveller": // Traveller exist in our DB
			this.alreadyLoggedIn = false;
			ClientUI.userController.traveller = new Person(info[1], info[2], info[4], info[7], info[3]);
			ClientUI.userController.traveller.setId(info[0]);
			ClientUI.userController.traveller.setCreditCardNumber(info[6]);
			ClientUI.userController.traveller.setNumberOfVisitors(Integer.parseInt(info[5]));
			ClientUI.userController.traveller.setMemberID(info[8]);
			ClientUI.orderController.setPerson();
			// for (int i=0;i<info.length;i++)
			// System.out.println(info[i]);
			break;
		case "IdentifyNotExistingTraveller": // Traveller does not exist on our DB, making a default one
			this.alreadyLoggedIn = false;
			ClientUI.userController.traveller = new Person("Traveller", "", null, "", null);
			ClientUI.userController.traveller.setId(info[0]);
			ClientUI.orderController.setPerson();
			break;
		case "AlreadyLoggedIn":
			this.alreadyLoggedIn = true;
			break;
		case "UpdateFieldofLoggedInTraveller":
			this.alreadyLoggedIn = false;
			break;
		default:
			System.out.print("Don't know what to do");
		}

	}

	public boolean isAlreadyLoggedIn() {
		return alreadyLoggedIn;
	}

	public void setAlreadyLoggedIn(boolean alreadyLoggedIn) {
		this.alreadyLoggedIn = alreadyLoggedIn;
	}

}

package Controller;

import java.io.IOException;

import Client.ClientUI;

public class SignUpController {
	public String memberID, id, firstName, lastName, phoneNum, email, paymentMethod, memberType;
	int numOfVisitors;
	public Boolean checker;

	public void checkExist(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append("isMemberExists");
		sb.append(" ");
		sb.append(id);
		String res = sb.toString();
		ClientUI.chat.accept(res);
	}

	public void init(String id, String firstName, String lastName, String phoneNum, String email, String paymentMethod,
			String memberType, int numOfVisitors) {
		StringBuffer sb = new StringBuffer();
		sb.append("addMember");
		sb.append(" ");
		sb.append(id);
		sb.append(" ");
		sb.append(firstName);
		sb.append(" ");
		sb.append(lastName);
		sb.append(" ");
		sb.append(phoneNum);
		sb.append(" ");
		sb.append(email);
		sb.append(" ");
		sb.append(paymentMethod);
		sb.append(" ");
		sb.append(memberType);
		sb.append(" ");
		sb.append(numOfVisitors);
		String res = sb.toString();
		ClientUI.chat.accept(res);
	}

	public void gotMessage(String[] msg) throws IOException {
		String cases = msg[0];
		switch (cases) {
		case "isMemberExists":
			if (msg[1] == "false") {
				checker = false;
			} else
				checker = true;
			;
			break;
		case "addMember":
			if (msg[1] == "false") {
				System.out.println("Member was not added");
			} else {
				this.memberID = msg[1];
			}
			break;
		default:
			break;
		}

	}

	public String getID() {
		return this.memberID;
	}

	public String getType() {
		return this.memberType;
	}
}
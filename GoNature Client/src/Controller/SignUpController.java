package Controller;

import Client.ClientUI;

public class SignUpController {
	String id, firstName, lastName, phoneNum, email, paymentMethod, memberType;
	int numOfVisitors;
	public Boolean checker;

	public Boolean checkExist(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append("exists");
		sb.append(" ");
		sb.append(id);
		String res = sb.toString();
		ClientUI.chat.accept(res);
		return !checker;
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
}

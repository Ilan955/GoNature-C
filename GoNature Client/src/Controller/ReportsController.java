package Controller;

import java.io.IOException;

import Client.ClientUI;

public class ReportsController {
	public int sumSolo = 0, sumMembers = 0, sumGroups = 0;
	// sumSolo=individuals that entered the park
	// sumMembers=members and family members that entered the park
	// sumGroups=groups that entered to the park

	public void gotMessage(String[] msg) throws IOException {
		String cases = msg[0];
		switch (cases) {
		case "getData":
			this.sumSolo = Integer.parseInt(msg[1]);
			this.sumMembers = Integer.parseInt(msg[2]);
			this.sumGroups = Integer.parseInt(msg[3]);
			break;
		default:
			break;
		}

	}

	public void getData(String month, String year) {
		StringBuffer sb = new StringBuffer();
		sb.append("getData");
		sb.append(" ");

		switch (month) {

		case "Jan":
			sb.append("01");
			break;
		case "Fab":
			sb.append("02");
			break;
		case "Mar":
			sb.append("03");
			break;
		case "Apr":
			sb.append("04");
			break;
		case "May":
			sb.append("05");
			break;
		case "Jun":
			sb.append("06");
			break;
		case "Jul":
			sb.append("07");
			break;
		case "Aug":
			sb.append("08");
			break;
		case "Sep":
			sb.append("09");
			break;
		case "Oct":
			sb.append("10");
			break;
		case "Nov":
			sb.append("11");
			break;
		case "Dec":
			sb.append("12");
			break;
		default:
			System.out.println("Error validation month");
			break;
		}

		sb.append(" ");
		sb.append(year);
		ClientUI.chat.accept(sb.toString());
	}

	public int getSolo() {
		return this.sumSolo;
	}

	public int getMembers() {
		return this.sumMembers;

	}

	public int getGroups() {
		return this.sumGroups;

	}

}
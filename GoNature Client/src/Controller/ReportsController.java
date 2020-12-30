package Controller;

import Client.ClientUI;
import GUI.Data;
import clientLogic.*;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReportsController {

	public Data d;
	public ObservableList<Data> ob = FXCollections.observableArrayList();
	public ArrayList<Reports> visitors;
	public ArrayList<Reports> members;
	public ArrayList<Reports> groups;
	public String month, park, year;
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
		case "getTableOfUnFullCapacityInDates":
			filldateofNotfullCapacityTable(msg);
		case "getDataEntranceTimesAndStay":
			dealWithgetDataEntranceTimesAndStay(msg);
		default:
			break;
		}
	}

	private String getMonth(String month) {
		switch (month) {
		case "Jan":
			return ("01");
		case "Fab":
			return ("02");
		case "Mar":
			return ("03");
		case "Apr":
			return ("04");
		case "May":
			return ("05");
		case "Jun":
			return ("06");
		case "Jul":
			return ("07");
		case "Aug":
			return ("08");
		case "Sep":
			return ("09");
		case "Oct":
			return ("10");
		case "Nov":
			return ("11");
		case "Dec":
			return ("12");
		default:
			System.out.println("Error validation month");
			return "Error validation month";
		}
	}

	public void getData(String month, String year, String park) {
		StringBuffer sb = new StringBuffer();
		sb.append("getData");
		sb.append(" ");
		sb.append(getMonth(month));
		sb.append(" ");
		sb.append(year);
		sb.append(" ");
		sb.append(park);
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

	public void getTableOfUnFullCapacityInDates(String month, String year, String wantedpark) {
		StringBuffer sb = new StringBuffer();
		sb.append("getTableOfUnFullCapacityInDates");
		sb.append(" ");
		sb.append(getMonth(month));
		sb.append(" ");
		sb.append(year);
		sb.append(" ");
		sb.append(wantedpark);
		ClientUI.chat.accept(sb.toString());
	}

	private void filldateofNotfullCapacityTable(String[] msg) {
		int cnt = 1;
		if (!(msg[1].equals("Done"))) {
			while (!(msg[cnt].equals("Done"))) {
				d = new Data(msg[cnt]);
				ob.add(d);
				cnt++;
			}
		}
	}

	public void getDataEntranceTimesAndStay(String month, String year, String wantedpark) {
		StringBuffer sb = new StringBuffer();
		this.month = month;
		this.year = year;
		this.park = wantedpark;
		sb.append("getDataEntranceTimesAndStay");
		sb.append(" ");
		sb.append(getMonth(month));
		sb.append(" ");
		sb.append(year);
		sb.append(" ");
		sb.append(wantedpark);
		ClientUI.chat.accept(sb.toString());
	}

	private void dealWithgetDataEntranceTimesAndStay(String[] msg) {
		/*
		 * msg[0] is numOfVisitors, msg[1] is type, msg[2] is entrance time, msg[3] is
		 * exit time.
		 */
		int i = 0;
		ArrayList<Reports> visitors = new ArrayList<Reports>();
		ArrayList<Reports> members = new ArrayList<Reports>();
		ArrayList<Reports> groups = new ArrayList<Reports>();

		while (!(msg[i].equals("Done"))) {
			String type = msg[i + 1];
			switch (type) {
			case "traveller":
				visitors.add(new Reports(msg[i], msg[i + 1], msg[i + 2], msg[i + 3]));
				break;
			case "member":
			case "familyMember":
				members.add(new Reports(msg[i], msg[i + 1], msg[i + 2], msg[i + 3]));
				break;
			case "groupGuide":
				groups.add(new Reports(msg[i], msg[i + 1], msg[i + 2], msg[i + 3]));
				break;
			default:
				System.out.println("Somthing is wrong with type");
			}
			i += 4;
		}

	}
}

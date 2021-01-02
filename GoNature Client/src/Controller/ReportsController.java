package Controller;

import java.io.IOException;
import java.sql.Date;

import Client.ClientUI;
import GUI.Data;
import clientLogic.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReportsController {

	public Data d;
	public ObservableList<Data> ob = FXCollections.observableArrayList();
	public ArrayList<Reports> visitors = new ArrayList<Reports>();
	public ArrayList<Reports> members = new ArrayList<Reports>();
	public ArrayList<Reports> groups = new ArrayList<Reports>();
	public String month, park, year;

	public int sumSolo = 0, sumMembers = 0, sumGroups = 0;

	// sumSolo=individuals that entered the park
	// sumMembers=members and family members that entered the park
	// sumGroups=groups that entered to the park
	Float Traveler_cnt, Traveler_income, Member_cnt, Member_income, Family_cnt, Family_income, Group_cnt, Group_income;

	public void makeMonthlyIncomeReport(Date Month) {
		StringBuffer sb = new StringBuffer();
		sb.append("makeMonthlyIncomeReport");// method name
		sb.append(" ");
		sb.append(Month.toString());
		ClientUI.chat.accept(sb.toString());
	}

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
			break;
		case "getDataEntranceTimesAndStay":
			dealWithgetDataEntranceTimesAndStay(msg);
			break;
		case "makeMonthlyIncomeReport":
			createMonthlyIncomeReport(msg[1], msg[2], msg[3], msg[4], msg[5], msg[6], msg[7], msg[8]);
			break;
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

	private void createMonthlyIncomeReport(String Traveler_cnt, String Traveler_income, String Member_cnt,
			String Member_income, String Family_cnt, String Family_income, String Group_cnt, String Group_income) {
		/* set values */
		this.Traveler_cnt = Float.valueOf(Traveler_cnt);
		this.Traveler_income = Float.valueOf(Traveler_income);

		this.Member_cnt = Float.valueOf(Member_cnt);
		this.Member_income = Float.valueOf(Member_income);

		this.Family_cnt = Float.valueOf(Family_cnt);
		this.Family_income = Float.valueOf(Family_income);

		this.Group_cnt = Float.valueOf(Group_cnt);
		this.Group_income = Float.valueOf(Group_income);
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
		int i = 1;
		while (!(msg[i].equals("Done"))) {

			String type = msg[i + 1];
			switch (type) {
			case "traveller":
				visitors.add(new Reports(msg[i], msg[i + 1], msg[i + 2], msg[i + 3], msg[i + 4]));
				break;
			case "Member":
			case "familyMember":
				members.add(new Reports(msg[i], msg[i + 1], msg[i + 2], msg[i + 3], msg[i + 4]));
				break;
			case "groupGuide":
				groups.add(new Reports(msg[i], msg[i + 1], msg[i + 2], msg[i + 3], msg[i + 4]));
				break;
			default:
				System.out.println("Somthing is wrong with type");
				System.out.println("I: " + i + " msg[1]: " + msg[i] + "  msg[2]: " + msg[i + 1] + "  msg[3]: "
						+ msg[i + 2] + "  msg[4]: " + msg[i + 3] + " msg[5]: " + msg[i + 4]);
			}
			i += 5;
		}
	}
}

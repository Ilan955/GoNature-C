package Controller;

import java.io.IOException;
import java.sql.Date;

import Client.ClientUI;

public class ReportsController {
	Float Traveler_cnt, Traveler_income ,Member_cnt, Member_income,Family_cnt,Family_income,Group_cnt,Group_income;

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
		case "makeMonthlyIncomeReport":
			createMonthlyIncomeReport(msg[1],msg[2],msg[3],msg[4],msg[5],msg[6],msg[7],msg[8]);
		default:
			break;
		}

	}

	private void createMonthlyIncomeReport(String Traveler_cnt,String Traveler_income ,String Member_cnt, String Member_income, String Family_cnt, String Family_income, String Group_cnt, String Group_income) {
		/*set values*/
		this.Traveler_cnt = Float.valueOf(Traveler_cnt);
		this.Traveler_income = Float.valueOf(Traveler_income);
		
		this.Member_cnt = Float.valueOf(Member_cnt);
		this.Member_income = Float.valueOf(Member_income);
		
		this.Family_cnt = Float.valueOf(Family_cnt);
		this.Family_income = Float.valueOf(Family_income);
		
		this.Group_cnt = Float.valueOf(Group_cnt);
		this.Group_income = Float.valueOf(Group_income);
	}

}
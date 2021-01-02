package Controller;

import java.time.LocalDate;
import java.time.LocalTime;

import Client.ClientUI;
import GUI.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RequestsController {
	private int statusOfRequest = -1;
	public ObservableList<Data> ob = FXCollections.observableArrayList();
	public Data d;
	private boolean canSendParkSettingsChangesToDm;
	private String park;

	public void insertRequestToDB(String id, LocalDate date, LocalTime time, String park, int numOfVisitors,
			String type, int status) {
		StringBuffer sb = new StringBuffer();
		sb.append("insertRequestToDB");
		sb.append(" ");
		sb.append(id);
		sb.append(" ");
		sb.append(date);
		sb.append(" ");
		sb.append(time.withNano(0));
		sb.append(" ");
		sb.append(park);
		sb.append(" ");
		sb.append(numOfVisitors);
		sb.append(" ");
		sb.append(type);
		sb.append(" ");
		sb.append(status);
		String res = sb.toString();
		ClientUI.chat.accept(res);
	}

	public int checkIfApproveRequest(String id, String type) {
		StringBuffer sb = new StringBuffer();
		sb.append("checkIfApproveRequest");
		sb.append(" ");
		sb.append(id);
		sb.append(" ");
		sb.append(type);
		String res = sb.toString();
		ClientUI.chat.accept(res);
		return statusOfRequest; // ?
	}

	public void getRequestsTravellerOfEnterPark(String wantedpark) {
		this.park = wantedpark;
		StringBuffer sb = new StringBuffer();
		sb.append("getRequestsTravellerOfEnterPark");
		sb.append(" ");
		sb.append(wantedpark);
		ClientUI.chat.accept(sb.toString());

	}

	public void changeStatusForCasualTraveller(int status, String id, String park) {
		StringBuffer sb = new StringBuffer();
		sb.append("changeStatusForCasualTraveller");
		sb.append(" ");
		sb.append(status);
		sb.append(" ");
		sb.append(id);
		sb.append(" ");
		sb.append(park);
		String res = sb.toString();
		ClientUI.chat.accept(res);
	}

	public void gotMessage(String[] msg) {
		String cases = msg[0];
		switch (cases) {
		case "checkIfApproveRequest":
			statusOfRequest = Integer.parseInt(msg[1]);
			break;
		case "getRequestsTravellerOfEnterPark":
			fillRequestsTraveller(msg);
			break;
		case "parkSettingsChangesSent":
			if (msg[1].equals("true")) {
				ClientUI.requestsController.setCanSendParkSettingsChangesToDm(true);
			} else if (msg[1].equals("false"))
				ClientUI.requestsController.setCanSendParkSettingsChangesToDm(false);
		default:
			System.out.print("Nothing to do");
		}
	}

	private void fillRequestsTraveller(String[] msg) {
		int cnt = 1;
		if (!(msg[1].equals("Done"))) {
			while (!(msg[cnt].equals("Done"))) {
				d = new Data(msg[cnt], msg[cnt + 1], msg[cnt + 2]);
				d.setPark(park);
				ob.add(d);
				cnt += 3;
			}
		}
	}

	public boolean isCanSendParkSettingsChangesToDm() {
		return canSendParkSettingsChangesToDm;
	}

	public void setCanSendParkSettingsChangesToDm(boolean canSendParkSettingsChangesToDm) {
		this.canSendParkSettingsChangesToDm = canSendParkSettingsChangesToDm;
	}
}

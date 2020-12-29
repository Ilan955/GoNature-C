package GUI;

import javafx.beans.property.SimpleStringProperty;

public class parkPendingRData {
	private SimpleStringProperty ParkName;
	private SimpleStringProperty RequestDate;
	private SimpleStringProperty RequestTime;
	private SimpleStringProperty MaxVisitors;
	private SimpleStringProperty Gap;
	private SimpleStringProperty MaxDuration;
	private SimpleStringProperty RequestID;
	
	
	public parkPendingRData(String ID,String ParkName,String RequestDate,String RequestTime, String MaxVisitors,String Gap,String MaxDuration) {
		this.ParkName= new SimpleStringProperty(ParkName);
		this.RequestID= new SimpleStringProperty(ID);
		this.RequestDate=new SimpleStringProperty(RequestDate);
		this.RequestTime=new SimpleStringProperty(RequestTime);
		this.MaxVisitors=new SimpleStringProperty(MaxVisitors);
		this.Gap=new SimpleStringProperty(Gap);
		this.MaxDuration=new SimpleStringProperty(MaxDuration);
	}
	public String getParkName() {
		return ParkName.get();
	}
	public void setParkName(String parkName) {
		ParkName = new SimpleStringProperty(parkName);
	}
	
	
	public String getRequestID() {
		return RequestID.get();
	}
	
	
	public void setRequestID(String requestID) {
		RequestID = new SimpleStringProperty(requestID);
	}
	
	
	
	
	public String getRequestDate() {
		return RequestDate.get();
	}
	public void setRequestDate(String requestDate) {
		RequestDate = new SimpleStringProperty(requestDate);
	}
	public String getRequestTime() {
		return RequestTime.get();
	}
	public void setRequestTime(String requestTime) {
		RequestTime = new SimpleStringProperty(requestTime);
	}
	public String getMaxVisitors() {
		return MaxVisitors.get();
	}
	public void setMaxVisitors(String maxVisitors) {
		MaxVisitors = new SimpleStringProperty(maxVisitors);
	}
	public String getGap() {
		return Gap.get();
	}
	public void setGap(String gap) {
		Gap = new SimpleStringProperty(gap);
	}
	public String getMaxDuration() {
		return MaxDuration.get();
	}
	public void setMaxDuration(String maxDuration) {
		MaxDuration = new SimpleStringProperty(maxDuration);
	}
}

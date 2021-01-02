package GUI;

import javafx.beans.property.SimpleStringProperty;

public class CancelReportData {
	public String getYear() {
		return Year;
	}

	public String getMonth() {
		return Month;
	}

	public Number getCancelledOrders() {
		return CancelledOrders;
	}

	public Number getUncompleteOrders() {
		return UncompleteOrders;
	}

	private String Year;
	private String Month;
	private Number CancelledOrders;
	private Number UncompleteOrders;

	public CancelReportData(String year, String Month, Number CancelledOrders, Number UncompleteOrders) {
		this.Year = year;
		this.Month = Month;
		this.CancelledOrders = CancelledOrders;
		this.UncompleteOrders = UncompleteOrders;
	}

}

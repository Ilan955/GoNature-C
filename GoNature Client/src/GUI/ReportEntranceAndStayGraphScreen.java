package GUI;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientUI;
import clientLogic.Reports;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ReportEntranceAndStayGraphScreen implements Initializable {

	@FXML
	private BarChart<String, Number> chartBarEntrance;

	@FXML
	private CategoryAxis DateX;

	@FXML
	private NumberAxis VisitY;
	@Override

	public void initialize(URL arg0, ResourceBundle arg1) {
		XYChart.Series<String, Number> series1;
		XYChart.Series<String, Number> series2;
		XYChart.Series<String, Number> series3;
		series1 = new XYChart.Series<String, Number>();
		series2 = new XYChart.Series<String, Number>();
		series3 = new XYChart.Series<String, Number>();
		series1.setName("Num Of Visitors");
		series2.setName("Entrance Times");
		series3.setName("Stay Duration");

		for (Reports r : ClientUI.reportsController.visitors) {
			series1.getData().add(new XYChart.Data<>(
					ClientUI.reportsController.month + "/" + ClientUI.reportsController.year, r.getNumOfVisit()));
			series2.getData()
					.add(new XYChart.Data<>(ClientUI.reportsController.month + "/" + ClientUI.reportsController.year,
							Integer.valueOf(r.getEntranceTime())));
			series3.getData().add(new XYChart.Data<>(
					ClientUI.reportsController.month + "/" + ClientUI.reportsController.year, r.getStay()));
		}

		for (CancelReportData k : ClientUI.orderController.oR) {
			series1.getData().add(new XYChart.Data<>(k.getMonth() + "/" + k.getYear(), k.getCancelledOrders()));
			series2.getData().add(new XYChart.Data<>(k.getMonth() + "/" + k.getYear(), k.getUncompleteOrders()));
		}
		chartBarEntrance.getData().addAll(series1, series2,series3);
	}

	 @FXML
	    void whenClickExit(ActionEvent event) {
		 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		 stage.close();
	    }


}

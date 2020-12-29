package GUI;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class cancelGraphScreen implements Initializable{
	

	    @FXML
	    private BarChart<String, Number> chartBar;

	    @FXML
	    private CategoryAxis DateX;

	    @FXML
	    private NumberAxis VisitY;

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			 XYChart.Series<String,Number> series1;
			 XYChart.Series<String,Number> series2;
		     series1=new XYChart.Series<String,Number>();
			 series2=new XYChart.Series<String,Number>();
			 series1.setName("Canceled");
			series2.setName("Not entered");
			for(CancelReportData k : ClientUI.orderController.oR) {
				series1.getData().add(new XYChart.Data<>(k.getMonth()+"/"+k.getYear(), k.getCancelledOrders()));
				series2.getData().add(new XYChart.Data<>(k.getMonth()+"/"+k.getYear(), k.getUncompleteOrders()));	
			}
			chartBar.getData().addAll(series1,series2);
		}
		 @FXML
		    void whenClickExit(ActionEvent event) {
			 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			 stage.close();
		    }

	

}

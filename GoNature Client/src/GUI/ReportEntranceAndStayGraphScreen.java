package GUI;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import Client.ClientUI;
import clientLogic.Reports;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ReportEntranceAndStayGraphScreen implements Initializable {

	@FXML
	private BarChart<String, Number> chartBarNumbers;

	@FXML
	private CategoryAxis DateX;

	@FXML
	private NumberAxis VisitY;
	@FXML
	private ScatterChart<String, Float> chartBarEntrance;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private PieChart pieChartTraveler;
	@FXML
	private PieChart pieChartMember;
	@FXML
	private PieChart pieChartGroups;

	int cnt1, cnt2, cnt3, cnt4, cnt5, cnt6, cnt7, cnt8;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		yAxis=new NumberAxis(7.0,20.,0.1);
		XYChart.Series<String, Number> visitTravel;
		XYChart.Series<String, Number> visitMember;
		XYChart.Series<String, Number> visitGroup;

		XYChart.Series<String, Float> enterTravel;
		XYChart.Series<String, Float> enterMember;
		XYChart.Series<String, Float> enterGroup;

		visitTravel = new XYChart.Series<String, Number>();
		visitMember = new XYChart.Series<String, Number>();
		visitGroup = new XYChart.Series<String, Number>();

		enterTravel = new XYChart.Series<String, Float>();// when did they enter the park
		enterMember = new XYChart.Series<String, Float>();// when did they enter the park
		enterGroup = new XYChart.Series<String, Float>();// when did they enter the park

		visitTravel.setName("Travelers");
		enterTravel.setName("Travelers");
		// Initialize Traveler graphs
		for (Reports r : ClientUI.reportsController.visitors) {
			visitTravel.getData().add(new XYChart.Data<>(r.getDate().toString(), r.getNumOfVisit()));

			enterTravel.getData().add(new XYChart.Data<>(r.getDate().toString(), r.getEntranceTime()));
		}
		visitMember.setName("Members");
		enterMember.setName("Members");
		// Initialize Member graphs
		for (Reports r : ClientUI.reportsController.members) {
			visitMember.getData().add(new XYChart.Data<>(r.getDate().toString(), r.getNumOfVisit()));

			enterMember.getData().add(new XYChart.Data<>(r.getDate().toString(), r.getEntranceTime()));
		}
		visitGroup.setName("Groups");
		enterGroup.setName("Groups");
		// Initialize Group graphs
		for (Reports r : ClientUI.reportsController.groups) {
			visitGroup.getData().add(new XYChart.Data<>(r.getDate().toString(), r.getNumOfVisit()));
			enterGroup.getData().add(new XYChart.Data<>(r.getDate().toString(), r.getEntranceTime()));
		}

		chartBarNumbers.getData().addAll(visitTravel, visitMember, visitGroup);
		chartBarEntrance.getData().addAll(enterTravel, enterMember, enterGroup);

		// Initialize pie chart
		countinit();
	}

	private void countinit() {
		resetcnts();
		pieChartTraveler.setClockwise(true);
		pieChartMember.setClockwise(true);
		pieChartGroups.setClockwise(true);

		for (Reports r : ClientUI.reportsController.visitors) {
			if (r.getStay() > 0) {
				if (r.getStay() > 30) {
					if (r.getStay() > 60) {
						if (r.getStay() > 90) {
							if (r.getStay() > 120) {
								if (r.getStay() > 150) {
									if (r.getStay() > 180) {
										if (r.getStay() > 210) {
											cnt8++;
										}
										cnt7++;
									}
									cnt6++;
								}
								cnt5++;
							}
							cnt4++;
						}
						cnt3++;
					}
					cnt2++;
				}
				cnt1++;
			}

		}
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("0-0.5h", cnt1), new PieChart.Data("0.5-1h", cnt2), new PieChart.Data("1-1.5h", cnt3),
				new PieChart.Data("1.5-2h", cnt4), new PieChart.Data("2-2.5h", cnt5), new PieChart.Data("1.5-3h", cnt6),
				new PieChart.Data("3-3.5h", cnt7), new PieChart.Data("3.5-4h", cnt8));
		pieChartTraveler.getData().addAll(pieChartData);

		resetcnts();

		for (Reports r : ClientUI.reportsController.members) {
			if (r.getStay() > 0) {
				if (r.getStay() > 30) {
					if (r.getStay() > 60) {
						if (r.getStay() > 90) {
							if (r.getStay() > 120) {
								if (r.getStay() > 150) {
									if (r.getStay() > 180) {
										if (r.getStay() > 210) {
											cnt8++;
										}
										cnt7++;
									}
									cnt6++;
								}
								cnt5++;
							}
							cnt4++;
						}
						cnt3++;
					}
					cnt2++;
				}
				cnt1++;
			}

		}
		ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
				new PieChart.Data("0-0.5h", cnt1), new PieChart.Data("0.5-1h", cnt2), new PieChart.Data("1-1.5h", cnt3),
				new PieChart.Data("1.5-2h", cnt4), new PieChart.Data("2-2.5h", cnt5), new PieChart.Data("1.5-3h", cnt6),
				new PieChart.Data("3-3.5h", cnt7), new PieChart.Data("3.5-4h", cnt8));
		pieChartMember.getData().addAll(pieChartData2);
		resetcnts();
		for (Reports r : ClientUI.reportsController.groups) {
			if (r.getStay() > 0) {
				if (r.getStay() > 30) {
					if (r.getStay() > 60) {
						if (r.getStay() > 90) {
							if (r.getStay() > 120) {
								if (r.getStay() > 150) {
									if (r.getStay() > 180) {
										if (r.getStay() > 210) {
											cnt8++;
										}
										cnt7++;
									}
									cnt6++;
								}
								cnt5++;
							}
							cnt4++;
						}
						cnt3++;
					}
					cnt2++;
				}
				cnt1++;
			}

		}
		ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
				new PieChart.Data("0-0.5h", cnt1), new PieChart.Data("0.5-1h", cnt2), new PieChart.Data("1-1.5h", cnt3),
				new PieChart.Data("1.5-2h", cnt4), new PieChart.Data("2-2.5h", cnt5), new PieChart.Data("1.5-3h", cnt6),
				new PieChart.Data("3-3.5h", cnt7), new PieChart.Data("3.5-4h", cnt8));
		pieChartGroups.getData().addAll(pieChartData3);

	}

	private void resetcnts() {
		cnt1 = 0;
		cnt2 = 0;
		cnt3 = 0;
		cnt4 = 0;
		cnt5 = 0;
		cnt6 = 0;
		cnt7 = 0;
		cnt8 = 0;
	}

	@FXML
	void whenClickExit(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

}

package controllers;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import Enums.MessageType;
import entity.DBmessage;
import entity.recentreport;
import entity.requestSuper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The Class ITManagerReportsController.
 */
public class ITManagerReportsController implements Initializable {

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;
	/** The rows. */
	private ObservableList<recentreport> rows;

	/** The arry ac su co. */
	private ArrayList<Integer> arryAcSuCo;

	/** The arry delays info. */
	private ArrayList<Integer> arryDelaysInfo;

	/**
	 * Instantiates a new IT manager reports controller.
	 */
	public ITManagerReportsController() {

		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	/** The home BTN. */
	@FXML
	private Button homeBTN;

	/** The show request BTN. */
	@FXML
	private Button showRequestBTN;

	/** The generate report BTN 1. */
	@FXML
	private Button generateReportBTN1;

	/** The employees mang BTN. */
	@FXML
	private Button employeesMangBTN;

	/** The Message BTN. */
	@FXML
	private Button MessageBTN;

	/** The personal info BTN. */
	@FXML
	private Button personalInfoBTN;

	/** The help BTN. */
	@FXML
	private Button helpBTN;

	/** The logout BTN. */
	@FXML
	private Button logoutBTN;

	/** The choose type of report. */
	@FXML
	private ChoiceBox<String> chooseTypeOfReport;

	/** The date from. */
	@FXML
	private DatePicker dateFrom;

	/** The Date to. */
	@FXML
	private DatePicker DateTo;

	/** The generate report. */
	@FXML
	private Button generateReport;

	/** The activity. */
	@FXML
	private Pane activity;

	/** The Active su clo. */
	@FXML
	private PieChart ActiveSuClo;

	/** The medin T. */
	@FXML
	private TextField medinT;

	/** The devesion T. */
	@FXML
	private TextField devesionT;

	/** The frequency. */
	@FXML
	private TextField frequency;

	/** The deniedreq. */
	@FXML
	private TextField deniedreq;

	/** The allreq. */
	@FXML
	private TextField allreq;

	/** The medin delay. */
	@FXML
	private TextField medinDelay;

	/** The choosestatus. */
	@FXML
	private ChoiceBox<?> choosestatus;

	/** The performence. */
	@FXML
	private Pane performence;

	/** The days. */
	@FXML
	private TextField days;

	/** The delays in execution. */
	@FXML
	private Pane delaysInExecution;

	/** The delayspie. */
	@FXML
	private PieChart delayspie;

	/** The number of delays. */
	@FXML
	private TextField numberOfDelays;

	/** The devesion delay. */
	@FXML
	private TextField devesionDelay;

	/** The time of delays. */
	@FXML
	private TextField timeOfDelays;

	/** The Frequencygraph. */
	@FXML
	private StackedBarChart<Integer, Integer> Frequencygraph;

	/** The table. */
	@FXML
	private TableView<tablefield> table;

	/** The month. */
	@FXML
	private TableColumn<tablefield, String> month;

	/** The day. */
	@FXML
	private TableColumn<tablefield, Integer> day;

	/** The num. */
	@FXML
	private TableColumn<tablefield, Integer> num;

	/** The medgraph. */
	@FXML
	private BarChart<String, Integer> medgraph;

	/** The devgraph. */
	@FXML
	private BarChart<?, ?> devgraph;

	/** The freqgraph. */
	@FXML
	private BarChart<?, ?> freqgraph;

	/** The freqtable. */
	@FXML
	private TableView<tablefield> freqtable;

	/** The month 1. */
	@FXML
	private TableColumn<tablefield, String> month1;

	/** The day 1. */
	@FXML
	private TableColumn<tablefield, Integer> day1;

	/** The num 1. */
	@FXML
	private TableColumn<tablefield, Integer> num1;

	@FXML
	private BarChart<?, ?> devgraph2;
	@FXML
	private TableView<recentreport> Recentreports;

	@FXML
	private TableColumn<?, ?> fromtable;

	@FXML
	private TableColumn<?, ?> totable;

	@FXML
	private TableColumn<?, ?> typetable;

    @FXML
    private Pane chooseDate;

	/**
	 * Generate report click.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void generateReportClick(MouseEvent event) throws IOException {

		freqgraph.getData().clear();
		devgraph.getData().clear();
		medgraph.getData().clear();
		
		delaysInExecution.setVisible(false);
		performence.setVisible(false);
		activity.setVisible(false);
		Recentreports.setVisible(false);
		if (chooseTypeOfReport.getValue().equals("Activity")) {
			
			makeActiveSuClo(null,null);
			activity.setVisible(true);
		}
		if (chooseTypeOfReport.getValue().equals("Performence")) {
			makePerformenct(null,null);
			performence.setVisible(true);
		}
		if (chooseTypeOfReport.getValue().equals("Delays in execution")) {
			makeDelays(null,null);
			delaysInExecution.setVisible(true);
		} else {

		}
	}

	/**
	 * Make delays.
	 */
	private void makeDelays(String s,String e) {
		
		DBmessage dbm;
		String start, end;
		if(e==null&&s==null)
		{
			start = this.dateFrom.getValue().toString();
			end = this.DateTo.getValue().toString();
		}
		else
		{
			start = s;
			end =e;
			
		}
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(start);
		arry.add(end);
		dbm = new DBmessage(MessageType.makeDelays, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException we) {
		}
	}

	/**
	 * Make performenct.
	 */
	private void makePerformenct(String s,String e) {

		DBmessage dbm;
		String start, end;
		if(e==null&&s==null)
		{
			start = this.dateFrom.getValue().toString();
			end = this.DateTo.getValue().toString();
		}
		else
		{
			start = s;
			end =e;
			
		}
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(start);
		arry.add(end);
		dbm = new DBmessage(MessageType.makePerformenct, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException we) {
		}
	}

	/**
	 * Make active su clo.
	 */
	public void makeActiveSuClo(String s,String e) {
		DBmessage dbm;
		String start, end;
		if(e==null&&s==null)
		{
			start = this.dateFrom.getValue().toString();
			end = this.DateTo.getValue().toString();
		}
		else
		{
			start = s;
			end =e;
			
		}

		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(start);
		arry.add(end);
		dbm = new DBmessage(MessageType.makeActiveSuClo, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException we) {
		}

	}

	/**
	 * Sets the active su clo.
	 *
	 * @param send the new active su clo
	 */
	@SuppressWarnings("unchecked")
	public void setActiveSuClo(ArrayList<Object> send) {

		int devesion, medfail, medsuc, medsusp;
		float avrgf = 0, avrgs = 0, avrgsus = 0, devf = 0, devs = 0, devsus = 0;
		ArrayList<Object> failur = (ArrayList<Object>) send.get(0);
		ArrayList<Object> suc = (ArrayList<Object>) send.get(1);
		ArrayList<Object> susp = (ArrayList<Object>) send.get(2);
		int allreq = (int) send.get(3);
		this.allreq.setText("" + allreq);
		ArrayList<String> arry = new ArrayList<String>();
		int[] failure = new int[12];
		int[] success = new int[12];
		int[] susppend = new int[12];
		int denied = 0;
		int numOfDays = 0;

		for (int i = 0; i < 12; i++) {
			failure[i] = (int) failur.get(i);
			denied += failure[i];
			success[i] = (int) suc.get(i);

			susppend[i] = (int) susp.get(i);

		}
		this.deniedreq.setText("" + denied);
		medfail = foundmed(failure);
		medsuc = foundmed(success);
		medsusp = foundmed(susppend);
		XYChart.Series medf = new XYChart.Series<>();
		medf.getData().add(new XYChart.Data<>("failure", medfail));
		XYChart.Series meds = new XYChart.Series();
		meds.getData().add(new XYChart.Data<>("success", medsuc));
		XYChart.Series medsus = new XYChart.Series();

		medsus.getData().add(new XYChart.Data<>("susppend", medsusp));
		this.medgraph.getData().add(meds);
		this.medgraph.getData().add(medf);
		this.medgraph.getData().add(medsus);
		XYChart.Series freqs = new XYChart.Series();
		XYChart.Series freqf = new XYChart.Series<>();
		XYChart.Series freqsus = new XYChart.Series();
		/*
		 * int[] n= {1,4,3,2,5,8,3,7,2,7,1,6}; int[] d= {2,7,1,6,1,4,3,2,5,8,3,7};
		 * 
		 * int[] s= {0,0,0,0,5,8,7,1,6,3,7,2};
		 */
		freqf.setName("failure");
		freqs.setName("success");
		freqsus.setName("susppend");
		for (int i = 0; i < 12; i++) {
			freqf.getData().add(new XYChart.Data<>("" + (i + 1), failure[i]));

			freqs.getData().add(new XYChart.Data<>("" + (i + 1), success[i]));

			freqsus.getData().add(new XYChart.Data<>("" + (i + 1), susppend[i]));
		}
		this.freqgraph.getData().add(freqsus);
		this.freqgraph.getData().add(freqs);
		this.freqgraph.getData().add(freqf);

		for (int i = 0; i < 12; i++) {
			avrgf += failure[i];
			avrgs += success[i];
			avrgsus += susppend[i];
		}
		avrgf = avrgf / 12;
		avrgs = avrgs / 12;
		avrgsus = avrgsus / 12;

		for (int j = 0; j < 12; j++) {
			devf += (float) Math.pow(failure[j] - avrgf, 2);
			devs += (float) Math.pow(success[j] - avrgs, 2);
			devsus += (float) Math.pow(susppend[j] - avrgsus, 2);
		}
		devf = (float) Math.sqrt(devf / 12);
		devs = (float) Math.sqrt(devs / 12);
		devsus = (float) Math.sqrt(devsus / 12);

		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("status request");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Standard deviation:");
		// this.freqgraph= new BarChart<String, Number>(xAxis, yAxis);
		XYChart.Series avrf = new XYChart.Series<>();
		avrf.getData().add(new XYChart.Data<>("failure", 5));
		XYChart.Series avrs = new XYChart.Series();
		avrs.getData().add(new XYChart.Data<>("success", 5));
		XYChart.Series avrsus = new XYChart.Series();

		avrsus.getData().add(new XYChart.Data<>("susppend", 7));
		this.devgraph.getData().add(avrf);
		this.devgraph.getData().add(avrs);
		this.devgraph.getData().add(avrsus);

	}

	/**
	 * Go employees mang.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goEmployeesMang(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerEmployeesManagment");
		MainAllControllers.changeWin();
	}

	/**
	 * Go help page.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goHelpPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerHelp");
		MainAllControllers.changeWin();

	}

	/**
	 * Go home page.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goHomePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerHome");
		MainAllControllers.changeWin();
	}

	/**
	 * Go personal info.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goPersonalInfo(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerPersonalInfo");
		MainAllControllers.changeWin();
	}

	/**
	 * Go show req.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goShowReq(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerShowRequests");
		MainAllControllers.changeWin();
	}

	/**
	 * Go generate report.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void gogenerateReport(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerReports");
		MainAllControllers.changeWin();
	}

	/**
	 * go Logout page.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void logoutPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
		MainAllControllers.logOutUser();
	}

	/**
	 * go Message page.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void messagePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerMessages");
		MainAllControllers.changeWin();
	}

	/**
	 * Initialize.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chooseTypeOfReport.getItems().add("Activity");
		chooseTypeOfReport.getItems().add("Performence");
		chooseTypeOfReport.getItems().add("Delays in execution");
		month.setCellValueFactory(new PropertyValueFactory<>("month"));
		day.setCellValueFactory(new PropertyValueFactory<>("day"));
		num.setCellValueFactory(new PropertyValueFactory<>("num"));
		fromtable.setCellValueFactory(new PropertyValueFactory<>("From"));
		totable.setCellValueFactory(new PropertyValueFactory<>("To"));
		typetable.setCellValueFactory(new PropertyValueFactory<>("type"));
		viewrecentreport();
	}

	/**
	 * Sets the make performenct.
	 *
	 * @param send the new make performenct
	 */
	public void setmakePerformenct(ArrayList<Object> send) {
		this.days.setText("" + send.get(0));
		Map<Integer, Object> mapDev = (Map<Integer, Object>) send.get(1);
		int j = 0;
		int[] sumDev = new int[4];
		for (int i = 0; i < 4; i++)
			sumDev[i] = 0;

		for (Map.Entry<Integer, Object> entry : mapDev.entrySet()) {
			int[] nums = (int[]) entry.getValue();
			for (int i = 0; i < 4; i++)
				sumDev[i] += nums[i];
		}

		XYChart.Series stage1 = new XYChart.Series<>();
		stage1.setName("stage 1");
		stage1.getData().add(new XYChart.Data<>("1", sumDev[0]));
		XYChart.Series stage2 = new XYChart.Series();
		stage2.getData().add(new XYChart.Data<>("2", sumDev[1]));
		XYChart.Series stage3 = new XYChart.Series();
		stage3.getData().add(new XYChart.Data<>("3", sumDev[2]));
		XYChart.Series stage4 = new XYChart.Series();
		stage4.getData().add(new XYChart.Data<>("4", sumDev[3]));
		stage2.setName("stage 2");
		stage3.setName("stage 3");
		stage4.setName("stage 4");
		this.devgraph2.getData().add(stage1);
		this.devgraph2.getData().add(stage2);
		this.devgraph2.getData().add(stage3);
		this.devgraph2.getData().add(stage4);

	}

	/**
	 * Sets the make delays.
	 *
	 * @param send the new make delays
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setmakeDelays(ArrayList<Object> send) {
		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		int[] daysReq = new int[12];
		int[] freq = new int[12];
		int numOfDelay = 0;
		int numOfDays = 0;
		int i = 0;

		for (i = 0; i < 12; i++) {
			daysReq[i] = (int) send.get(i);
			freq[i] = (int) send.get(i + 12);
		}
		for (i = 0; i < 12; i++) {
			numOfDays += daysReq[i];
			numOfDelay += freq[i];
		}
		this.numberOfDelays.setText("" + numOfDelay);
		this.timeOfDelays.setText("" + numOfDays);

		int medin = foundmed(daysReq);
		this.medinDelay.setText("" + medin);
		float avrg = 0;
		for (int j = 0; j < 12; j++) {
			avrg += (float) daysReq[j];
		}

		avrg = (float) avrg / 12;
		float sum = 0;
		for (int j = 0; j < 12; j++)
			sum += (float) Math.pow(daysReq[j] - avrg, 2);
		String devesion = String.format("%.2f", Math.sqrt(sum / 12));
		this.devesionDelay.setText(devesion);
		ObservableList<tablefield> rows = FXCollections.observableArrayList();
		XYChart.Series dataSeries1 = new XYChart.Series();

		for (int j = 0; j < 12; j++) {
			rows.add(new tablefield(months[j], daysReq[j], freq[j]));
			dataSeries1.getData().add(new XYChart.Data("" + daysReq[j], freq[j]));
		}

		table.setItems(rows);
		dataSeries1.setName("Delays request");

		this.Frequencygraph.getData().add(dataSeries1);
	}

	/**
	 * Foundmed.
	 *
	 * @param arr the arr
	 * @return the int
	 */
	private int foundmed(int[] arr) {

		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1])
					swap(arr, j, j + 1);
			}
		}
		return arr[(int) arr.length / 2];

	}

	/**
	 * Swap.
	 *
	 * @param arr the arr
	 * @param i   the i
	 * @param j   the j
	 */
	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	  @FXML
	    void showRequestInfo(MouseEvent event) 
	    {
		  Recentreports.setOnMouseClicked((MouseEvent ev ) -> 
		  {
	            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2)
	            {
	                String stage=Recentreports.getItems().get(Recentreports.getSelectionModel().getSelectedIndex()).getStage();
	                String start=Recentreports.getItems().get(Recentreports.getSelectionModel().getSelectedIndex()).getFrom();
	                String end=Recentreports.getItems().get(Recentreports.getSelectionModel().getSelectedIndex()).getTo();
					freqgraph.getData().clear();
					devgraph.getData().clear();
					medgraph.getData().clear();
					chooseDate.setVisible(false);
					delaysInExecution.setVisible(false);
					performence.setVisible(false);
					activity.setVisible(false);
					if (stage.equals("Activity")) {
						makeActiveSuClo(start,end);
						activity.setVisible(true);
					}
					if (stage.equals("Performence")) {
						makePerformenct(start,end);
						performence.setVisible(true);
					}
					if (stage.equals("Delays in execution")) {
						makeDelays(start,end);
						delaysInExecution.setVisible(true);
					} else {

					}                
	            }
		  });
		  
	    }

		public void viewrecentreport() {
		DBmessage dbm;
		dbm = new DBmessage(MessageType.viewrecentreport, null);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

		public void setTextInTable(ArrayList<Object> list) {
			System.out.println("im here");
			rows = FXCollections.observableArrayList();
			for (Object r : list)
				rows.add((recentreport) r);
			Recentreports.setItems(rows);
			
		}

}

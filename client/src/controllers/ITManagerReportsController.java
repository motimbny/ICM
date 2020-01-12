package controllers;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import Enums.MessageType;
import entity.DBmessage;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ITManagerReportsController implements Initializable {
	private MainAllControllers MainAllControllers;
	private ArrayList<Integer> arryAcSuCo;
	private ArrayList<Integer> arryDelaysInfo;

	public ITManagerReportsController() {

		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	@FXML
	private Button homeBTN;

	@FXML
	private Button showRequestBTN;

	@FXML
	private Button generateReportBTN1;

	@FXML
	private Button employeesMangBTN;

	@FXML
	private Button MessageBTN;

	@FXML
	private Button personalInfoBTN;

	@FXML
	private Button helpBTN;

	@FXML
	private Button logoutBTN;

	@FXML
	private ChoiceBox<String> chooseTypeOfReport;

	@FXML
	private DatePicker dateFrom;

	@FXML
	private DatePicker DateTo;

	@FXML
	private Button generateReport;

	@FXML
	private Pane activity;

	@FXML
	private PieChart ActiveSuClo;

	@FXML
	private TextField medinT;

	@FXML
	private TextField devesionT;

	@FXML
	private TextField frequency;

	@FXML
	private TextField deniedreq;

	@FXML
	private TextField allreq;
	@FXML
	private TextField medinDelay;

	@FXML
	private ChoiceBox<?> choosestatus;

	@FXML
	private Pane performence;

	@FXML
	private TextField days;

	@FXML
	private Pane delaysInExecution;

	@FXML
	private PieChart delayspie;

	@FXML
	private TextField numberOfDelays;
	@FXML
	private TextField devesionDelay;

	@FXML
	private TextField timeOfDelays;
	@FXML
	private StackedBarChart<Integer, Integer> Frequencygraph;
	@FXML
	private TableView<tablefield> table;

	@FXML
	private TableColumn<tablefield, String> month;

	@FXML
	private TableColumn<tablefield, Integer> day;

	@FXML
	private TableColumn<tablefield, Integer> num;
	@FXML
	private StackedBarChart<String, Integer> medgraph;

	@FXML
	private StackedBarChart<?, ?> devgraph;

	@FXML
	private StackedBarChart<?, ?> freqgraph;

	@FXML
	private TableView<tablefield> freqtable;
	@FXML
	private TableColumn<tablefield, String> month1;

	@FXML
	private TableColumn<tablefield, Integer> day1;

	@FXML
	private TableColumn<tablefield, Integer> num1;

	@FXML
	void generateReportClick(MouseEvent event) throws IOException {

		freqgraph.getData().clear();
		devgraph.getData().clear();
		medgraph.getData().clear();
		delaysInExecution.setVisible(false);
		performence.setVisible(false);
		activity.setVisible(false);
		if (chooseTypeOfReport.getValue().equals("Activity")) {
			makeActiveSuClo();
			activity.setVisible(true);
		}
		if (chooseTypeOfReport.getValue().equals("Performence")) {
			makePerformenct();
			performence.setVisible(true);
		}
		if (chooseTypeOfReport.getValue().equals("Delays in execution")) {
			makeDelays();
			delaysInExecution.setVisible(true);
		} else {

		}
	}

	private void makeDelays() {
		DBmessage dbm;
		String start, end;
		start = this.dateFrom.getValue().toString();
		end = this.DateTo.getValue().toString();
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(start);
		arry.add(end);
		dbm = new DBmessage(MessageType.makeDelays, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	private void makePerformenct() {

		DBmessage dbm;
		String start, end;
		start = this.dateFrom.getValue().toString();
		end = this.DateTo.getValue().toString();
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(start);
		arry.add(end);
		dbm = new DBmessage(MessageType.makePerformenct, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	public void makeActiveSuClo() {
		DBmessage dbm;
		String start, end;
		start = this.dateFrom.getValue().toString();
		end = this.DateTo.getValue().toString();

		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(start);
		arry.add(end);
		dbm = new DBmessage(MessageType.makeActiveSuClo, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}

	}

	@SuppressWarnings("unchecked")
	public void setActiveSuClo(ArrayList<Object> send) {

		int devesion ,medfail, medsuc, medsusp;
		float avrgf = 0,avrgs = 0,avrgsus = 0, devf = 0,devs = 0,devsus = 0 ;
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
		XYChart.Series<String, Integer> medf = new XYChart.Series<>();
		medf.getData().add(new XYChart.Data<>("failure" , medfail));
		XYChart.Series meds = new XYChart.Series();
		meds.getData().add(new XYChart.Data<>("success" , medsuc));
		XYChart.Series medsus = new XYChart.Series();
		
		medsus.getData().add(new XYChart.Data<>("susppend" ,medsusp));
		this.medgraph.getData().add(meds);
		this.medgraph.getData().add(medf);
		this.medgraph.getData().add(medsus);
		XYChart.Series freqs = new XYChart.Series();
		XYChart.Series freqf = new XYChart.Series<>();
		XYChart.Series freqsus = new XYChart.Series();
	/*	int[] n= {1,4,3,2,5,8,3,7,2,7,1,6};
		int[] d= {2,7,1,6,1,4,3,2,5,8,3,7};

		int[] s= {0,0,0,0,5,8,7,1,6,3,7,2};*/

		for(int i=0;i<12;i++)
		{
			freqf.getData().add(new XYChart.Data<>(""+i , failure[i]));
			
			freqs.getData().add(new XYChart.Data<>(""+i , success[i]));
			
			
			freqsus.getData().add(new XYChart.Data<>(""+i , susppend[i]));
		}
		
		this.freqgraph.getData().add(freqsus);
		this.freqgraph.getData().add(freqs);
		this.freqgraph.getData().add(freqf);
		
		for(int i=0;i<12;i++)
		{
			avrgf+=failure[i];
			avrgs+=success[i];
			avrgsus+=susppend[i];
		}
		avrgf=avrgf/12;
		avrgs=avrgs/12;
		avrgsus=avrgsus/12;
		
		
		for (int j = 0; j < 12; j++)
		{
			devf += (float) Math.pow(failure[j] - avrgf, 2);
			devs += (float) Math.pow(success[j] - avrgs, 2);
			devsus += (float) Math.pow(susppend[j] - avrgsus, 2);
		}
		devf=(float)Math.sqrt(devf / 12);
		devs=(float)Math.sqrt(devs / 12);
		devsus=(float)Math.sqrt(devsus / 12);
	
		
		XYChart.Series avrf = new XYChart.Series<>();
		avrf.getData().add(new XYChart.Data<>("failure" , 5));
		XYChart.Series avrs = new XYChart.Series();
		avrs.getData().add(new XYChart.Data<>("success" , 5));
		XYChart.Series avrsus = new XYChart.Series();
		
		avrsus.getData().add(new XYChart.Data<>("susppend" , 7));
		this.devgraph.getData().add(avrf);
		this.devgraph.getData().add(avrs);
		this.devgraph.getData().add(avrsus);
		
		/*
		 * XYChart.Series fre = new XYChart.Series();
		 * 
		 * 
		 * this.medinDelay.setText("" + medin); float avrg = 0; for (int j = 0; j < 12;
		 * j++) { avrg += (float) daysReq[j]; }
		 * 
		 * avrg = (float) avrg / 12; float sum = 0; for (int j = 0; j < 12; j++) sum +=
		 * (float) Math.pow(daysReq[j] - avrg, 2); String devesion=String.format("%.2f",
		 * Math.sqrt(sum / 12)); this.devesionDelay.setText(devesion);
		 * ObservableList<tablefield> rows = FXCollections.observableArrayList();
		 * XYChart.Series dataSeries1 = new XYChart.Series();
		 * 
		 * 
		 * for(int j=0;j<12;j++) { rows.add(new tablefield(months[j],
		 * daysReq[j],freq[j])); dataSeries1.getData().add(new
		 * XYChart.Data(""+daysReq[j], freq[j])); }
		 * 
		 * table.setItems(rows); dataSeries1.setName("Delays request");
		 * 
		 * this.Frequencygraph.getData().add(dataSeries1);
		 */

	}

	@FXML
	void goEmployeesMang(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerEmployeesManagment");
		MainAllControllers.changeWin();
	}

	@FXML
	void goHelpPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerHelp");
		MainAllControllers.changeWin();

	}

	@FXML
	void goHomePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerHome");
		MainAllControllers.changeWin();
	}

	@FXML
	void goPersonalInfo(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerPersonalInfo");
		MainAllControllers.changeWin();
	}

	@FXML
	void goShowReq(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerShowRequests");
		MainAllControllers.changeWin();
	}

	@FXML
	void gogenerateReport(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerReports");
		MainAllControllers.changeWin();
	}

	@FXML
	void logoutPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
		MainAllControllers.logOutUser();
	}

	@FXML
	void messagePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerMessages");
		MainAllControllers.changeWin();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chooseTypeOfReport.getItems().add("Activity");
		chooseTypeOfReport.getItems().add("Performence");
		chooseTypeOfReport.getItems().add("Delays in execution");
		month.setCellValueFactory(new PropertyValueFactory<>("month"));
		day.setCellValueFactory(new PropertyValueFactory<>("day"));
		num.setCellValueFactory(new PropertyValueFactory<>("num"));

	}

	public void setmakePerformenct(ArrayList<Object> send) {
		this.days.setText("" + send.get(0));
	}

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

	private int foundmed(int[] arr) {

		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1])
					swap(arr, j, j + 1);
			}
		}
		return arr[(int) arr.length / 2];

	}

	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}

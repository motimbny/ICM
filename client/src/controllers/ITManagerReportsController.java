package controllers;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ITManagerReportsController implements Initializable
{
	private MainAllControllers MainAllControllers;
	private ArrayList<Integer> arryAcSuCo;
	private ArrayList<Integer> arryDelaysInfo;


	public ITManagerReportsController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
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
	    private ChoiceBox<String> choosestatus;
	    
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
	    private Pane performence;

	    @FXML
	    private TextField days;

	    @FXML
	    private Pane delaysInExecution;

	    @FXML
	    private PieChart delayspie;

	    @FXML
	    private TextField medinDelay;

	    @FXML
	    private TextField devesionDelay;

	    @FXML
	    private TextField frequencyDelay;

	    @FXML
	    private TextField numberOfDelays;

	    @FXML
	    private TextField timeOfDelays;
	    @FXML
	    private TextField frequency;
	    @FXML
	    private TextField deniedreq;
	    @FXML
	    private TextField allreq;
	    

   

    @FXML
    void generateReportClick(MouseEvent event) throws IOException 
    {
    	delaysInExecution.setVisible(false);
		performence.setVisible(false);
    	activity.setVisible(false);
    	if(chooseTypeOfReport.getValue().equals("Activity"))
    	{
    		makeActiveSuClo();
    		activity.setVisible(true);
    	}
    	if(chooseTypeOfReport.getValue().equals("Performence"))
    	{
    		makePerformenct();
    		performence.setVisible(true);
    	}
    	if(chooseTypeOfReport.getValue().equals("Delays in execution"))
        {
    		makeDelays();
    		delaysInExecution.setVisible(true);
    	}
    	else {
    		
    	}
    }
    private void makeDelays() {
		
    	ArrayList<String> arryInfo=new ArrayList<String>();
    	arryInfo.add("Moodle");
    	arryInfo.add("Info system");
    	arryInfo.add("Libary");
    	arryInfo.add("Computers");
    	for(int i:arryDelaysInfo)
    	{
    	    PieChart.Data slice1 = new PieChart.Data(arryInfo.get(i),i+10);
    	    ActiveSuClo.getData().add(slice1);
    	}
    	delayspie.setVisible(true);
	}
	private void makePerformenct() {
    	
		DBmessage dbm;
		String start,end;
		start=this.dateFrom.getValue().toString();
		end=this.DateTo.getValue().toString();
    	ArrayList<Object> arry=new ArrayList<Object>();
    	arry.add(start);
    	arry.add(end);
		dbm = new DBmessage(MessageType.makePerformenct, arry); 
		try {
			MainAllControllers.sendToAbsServer(dbm); 
		} catch (IOException e) {
		}
	}
	public void makeActiveSuClo()
    {
		DBmessage dbm;
		String start,end;
		start=this.dateFrom.getValue().toString();
		end=this.DateTo.getValue().toString();
		
    	ArrayList<Object> arry=new ArrayList<Object>();
    	arry.add(start);
    	arry.add(end);
		dbm = new DBmessage(MessageType.makeActiveSuClo, arry); 
		try {
			MainAllControllers.sendToAbsServer(dbm); 
		} catch (IOException e) {
		}
    	
    	
    }
	public void setActiveSuClo(ArrayList<Object> send)
	{
    	int devesion,avrg=0;

		int failur=(int)send.get(0);
		int suc=(int)send.get(1);
		int susp=(int)send.get(2);
		int allreq=(int)send.get(3);
	 	ArrayList<String> arry=new ArrayList<String>();
    	arry.add("failur");
    	arry.add("Active");
    	arry.add("susspened");
    	if(failur>suc&&failur<susp)
    		this.medinT.setText(""+failur);
    	else if(suc>failur&&suc<susp)
    		this.medinT.setText(""+suc);
    	else if(susp>failur&&susp<suc)
    		this.medinT.setText(""+susp);
    	if(failur<suc&&failur>susp)
    		this.medinT.setText(""+failur);
    	else if(suc<failur&&suc>susp)
    		this.medinT.setText(""+suc);
    	else if(susp<failur&&susp>suc)
    		this.medinT.setText(""+susp);
    	else
    	{
        	avrg=(susp+suc+failur)/3;

    		this.medinT.setText(""+avrg);
    	}
    	avrg=(susp+suc+failur)/3;
    	devesion=(int) Math.sqrt((Math.pow(failur-avrg, 2)+Math.pow(suc-avrg, 2)+Math.pow(susp-avrg, 2))/3);
    	avrg=(susp+suc+failur)/3;
    	this.devesionT.setText(""+devesion);
    	this.frequency.setText(""+avrg);
    	this.deniedreq.setText(""+failur);
    	this.allreq.setText(""+allreq);
    	this.choosestatus.getItems().add("failur");
    	this.choosestatus.getItems().add("succes");
    	this.choosestatus.getItems().add("susppend");
   	    PieChart.Data slice1 = new PieChart.Data("failur",failur);
   	    PieChart.Data slice2 = new PieChart.Data("succes",suc);
   	    PieChart.Data slice3 = new PieChart.Data("susppend",susp);
   	    ActiveSuClo.getData().add(slice1);
   	    ActiveSuClo.getData().add(slice2);
   	    ActiveSuClo.getData().add(slice3);
	
	delayspie.setVisible(true);
    	
	}
 
  
	@FXML
    void goEmployeesMang(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerEmployeesManagment");
    	MainAllControllers.changeWin();
	}

    @FXML
    void goHelpPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerHelp");
    	MainAllControllers.changeWin();
	
    }

    @FXML
    void goHomePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerHome");
    	MainAllControllers.changeWin();
	}

    @FXML
    void goPersonalInfo(MouseEvent event)throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerPersonalInfo");
    	MainAllControllers.changeWin();
	}

    @FXML
    void goShowReq(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerShowRequests");
    	MainAllControllers.changeWin();
	}

    @FXML
    void gogenerateReport(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerReports");
    	MainAllControllers.changeWin();
	}

    @FXML
    void logoutPage(MouseEvent event)  throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.logOutUser();
	}

    @FXML
    void messagePage(MouseEvent event)throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerMessages");
    	MainAllControllers.changeWin();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		chooseTypeOfReport.getItems().add("Activity");
		chooseTypeOfReport.getItems().add("Performence");
		chooseTypeOfReport.getItems().add("Delays in execution");


		
	}
	public void setmakePerformenct(ArrayList<Object> send) {
		this.days.setText(""+send.get(0));
	}


}

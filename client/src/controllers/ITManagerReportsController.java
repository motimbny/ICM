package controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    void generateReportClick(MouseEvent event) throws IOException 
    {
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
    	performence.setVisible(true);
		
	}
	public void makeActiveSuClo()
    {
    	ArrayList<String> arry=new ArrayList<String>();
    	arry.add("Active");
    	arry.add("susspened");
    	arry.add("Closed");
    	for(int i:arryAcSuCo)
    	{
    	    PieChart.Data slice1 = new PieChart.Data(arry.get(i),i+10);
    	    ActiveSuClo.getData().add(slice1);
    	}
    	ActiveSuClo.setVisible(true);
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


}

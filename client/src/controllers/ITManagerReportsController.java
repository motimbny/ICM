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
import javafx.scene.input.MouseEvent;

public class ITManagerReportsController implements Initializable
{
	private MainAllControllers MainAllControllers;
	private ArrayList<Integer> arryAcSuCo;
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
    private PieChart ActiveSuClo;

    @FXML
    void generateReportClick(MouseEvent event) 
    {
    	if(chooseTypeOfReport.getValue().equals("Activity"))
    	{
    		makeActiveSuClo();
    	}
    	if(chooseTypeOfReport.getValue().equals("Performence"))
    	{
    		
    	}
    	else
        {
    		
    	}
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

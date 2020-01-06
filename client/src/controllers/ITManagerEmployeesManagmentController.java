package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.ITemployee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ITManagerEmployeesManagmentController implements Initializable 
{
	private MainAllControllers MainAllControllers;
	private ObservableList<ITemployee> rows; 
	public ITManagerEmployeesManagmentController()
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
    private TableView<ITemployee> EmployeesTable;

    @FXML
    private TableColumn<ITemployee, Integer> employeeId;

    @FXML
    private TableColumn<ITemployee, String> employeeName;

    @FXML
    private TableColumn<ITemployee, String> employeeLastName;

    @FXML
    private TableColumn<ITemployee, String> employeeMail;

    @FXML
    private TableColumn<ITemployee, Integer> numOfProjects;
    
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
    void logoutPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
	}

    @FXML
    void goPersonalInfo(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerPersonalInfo");
    	MainAllControllers.changeWin();
	}

    @FXML
    void goShowReq(MouseEvent event)  throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerShowRequests");
    	MainAllControllers.changeWin();
	}
    public void requestServer()
    {
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.ShowEmployeeList, null);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
    public void setTextInTable(ArrayList<Object> list)
	{
		rows= FXCollections.observableArrayList();
    	for(Object r:list)
    		rows.add((ITemployee)r);		
    	EmployeesTable.setItems(rows);		
	}
    @FXML
    void gogenerateReport(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerReports");
    	MainAllControllers.changeWin();
	}

    @FXML
    void messagePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerMessages");
    	MainAllControllers.changeWin();
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
		employeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
		employeeLastName.setCellValueFactory(new PropertyValueFactory<>("employeeLastName"));
		employeeMail.setCellValueFactory(new PropertyValueFactory<>("employeeMail"));
		numOfProjects.setCellValueFactory(new PropertyValueFactory<>("numOfProjects"));
		requestServer();
		
	}

}

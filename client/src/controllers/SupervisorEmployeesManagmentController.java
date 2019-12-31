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

public class SupervisorEmployeesManagmentController implements Initializable 
{
	private MainAllControllers MainAllControllers;
	private ObservableList<ITemployee> rows; 
	public SupervisorEmployeesManagmentController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}
    @FXML
    private Button homeBTN;

    @FXML
    private Button showreBTN;

    @FXML
    private Button EmployeesManagmentBTN;

    @FXML
    private Button personBTN;

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
    
    public void requestServer()
    {
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.ShowEmployeeList, null);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
    
	
    @FXML
    void goEmployeesMangPage(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("SupervisorEmployeesManagment");
    	MainAllControllers.changeWin();
    }

    @FXML
    void goHelpPage(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("SupervisorHelp");
    	MainAllControllers.changeWin();
    }

    @FXML
    void goHomePage(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("SupervisorHome");
    	MainAllControllers.changeWin();
    }

    @FXML
    void goLogoutPage(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
    }

    @FXML
    void goPersonalPage(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("SupervisorPersonalInfo");
    	MainAllControllers.changeWin();
    }


    @FXML
    void goShowReqPage(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("SupervisorShowRequests");
    	MainAllControllers.changeWin();
    }

	public void setTextInTable(ArrayList<Object> list)
	{
		rows= FXCollections.observableArrayList();
    	for(Object r:list)
    		rows.add((ITemployee)r);		
    	EmployeesTable.setItems(rows);		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
		employeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
		employeeLastName.setCellValueFactory(new PropertyValueFactory<>("employeeLastName"));
		employeeMail.setCellValueFactory(new PropertyValueFactory<>("employeeMail"));
		numOfProjects.setCellValueFactory(new PropertyValueFactory<>("numOfProjects"));
		requestServer();
		
	}

}

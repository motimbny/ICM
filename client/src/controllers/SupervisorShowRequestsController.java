package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.RequestUser;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SupervisorShowRequestsController implements Initializable
{
	private ObservableList<RequestUser> rows;
	private MainAllControllers MainAllControllers;
	public SupervisorShowRequestsController()
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
	private TableView<?> requestTable;

	@FXML
	private TableColumn<?, ?> RequestID;

	@FXML
	private TableColumn<?, ?> RequestStatus;

	@FXML
	private TableColumn<?, ?> RequestProcessStage;

	@FXML
	private Button SuprvisorUpdateRequestBTN;

	@FXML
	private Button SuprvisorCloseRequestBTN;

	@FXML
	private Button SuprvisorSuspendRequestBTN;

	@FXML
	private Button SuprvisorExtensionRequestBTN;

    @FXML
    private Button search;

    @FXML
    private TextField requestIdTo;
	
	@FXML
	void UpdateRequest(MouseEvent event) 
	{

	}

	@FXML
	void closeRequest(MouseEvent event) 
	{

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

	@FXML
	void suspendRequest(MouseEvent event) 
	{

	}

	@FXML
	void viewExtensionReport(MouseEvent event) 
	{

	}
	

    @FXML
    void showRequestDetails(MouseEvent event) 
    {

    }
    
    @FXML
    void searchRequest(MouseEvent event) 
    {
    	ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.user.getName());
		arry.add(Integer.parseInt(requestIdTo.getText()));
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.SearchReqSupervisor, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void clearTable()
	{
		requestTable.getItems().clear();
	}
	
	 public void setTextInTable(ArrayList<Object> list)
	 {
		 
	 }

}

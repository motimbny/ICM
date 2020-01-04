package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.requestSuper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ITManagerShowRequestsController implements Initializable
{	
	private ObservableList<requestSuper> rows;
	private MainAllControllers MainAllControllers;
	public ITManagerShowRequestsController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}

    @FXML
    private Button homeBTN;

    @FXML
    private Button showRequestBTN1;

    @FXML
    private Button generateReportBTN1;

    @FXML
    private Button employeesMangBTN;

    @FXML
    private Button logoutBTN1;

    @FXML
    private Button personalInfoBTN;

    @FXML
    private Button messageBTN;
    
    @FXML
    private Button helpBTN;

    @FXML
    private Button logoutBTN;

    @FXML
    private TableView<requestSuper> requestTable;

    @FXML
    private TableColumn<requestSuper, Integer> RequestID;

    @FXML
    private TableColumn<requestSuper, Integer> RequestStatus;

    @FXML
    private TableColumn<requestSuper, Integer> RequestProcessStage;

    @FXML
    private Button RenewRequestRequestBTN;

    @FXML
    private Button search;

    @FXML
    private TextField requestIdTo;

    @FXML
    void goEmployeesMang(MouseEvent event)throws IOException 
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
    void goHomePage(MouseEvent event)throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerHome");
    	MainAllControllers.changeWin();
	}

    @FXML
    void goPersonalInfo(MouseEvent event) throws IOException 
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
    void messagePage(MouseEvent event) 
    {

    }

    @FXML
    void gogenerateReport(MouseEvent event) 
    {

    }

    @FXML
    void logoutPage(MouseEvent event)throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
	}

    @FXML
    void renewRequest(MouseEvent event)
    {
        
    }

    @FXML
    void searchRequest(MouseEvent event) 
    {
    		if (requestIdTo.getText().equals("")) 
    		{
    			try {
    				MainAllControllers.setWindowVar("ITManagerShowRequests");
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			MainAllControllers.changeWin();
    		} else 
    		{
    			ArrayList<Object> arry = new ArrayList<Object>();
    			arry.add(Integer.parseInt(requestIdTo.getText()));
    			DBmessage dbm;
    			dbm = new DBmessage(MessageType.SearchReqManager, arry); 
    			try {
    				MainAllControllers.sendToAbsServer(dbm);
    			} catch (IOException e) {
    			}
    		}
    }

    @FXML
    void showRequestDetails(MouseEvent event)
    {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		RequestID.setCellValueFactory(new PropertyValueFactory<>("id"));
		RequestStatus.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
		RequestProcessStage.setCellValueFactory(new PropertyValueFactory<>("currentStage"));
		requestServer();
		
	}
	public void requestServer()
    {
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.MangerRequestShow, null);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
	public void clearTable()
	{
		requestTable.getItems().clear();
	}
	 public void setTextTable(ArrayList<Object> list)
	 {
		 rows= FXCollections.observableArrayList();
	    	for(Object r:list)
	    		rows.add((requestSuper)r);		
	    	requestTable.setItems(rows);	
	 }
}


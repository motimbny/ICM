package controllers;

import java.io.IOException;
import java.util.ArrayList;

import Enums.MessageType;
import entity.DBmessage;
import entity.requestSuper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ITManagerShowRequestsController {
	
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
    private TableView<?> requestTable;

    @FXML
    private TableColumn<?, ?> RequestID;

    @FXML
    private TableColumn<?, ?> RequestStatus;

    @FXML
    private TableColumn<?, ?> RequestProcessStage;

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
    void messagePage(MouseEvent event) {

    }

    @FXML
    void gogenerateReport(MouseEvent event) {

    }

    @FXML
    void logoutPage(MouseEvent event)throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
	}

    @FXML
    void renewRequest(MouseEvent event) {

    }

    @FXML
    void searchRequest(MouseEvent event) {
  /*  	{
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
    			arry.add(MainAllControllers.user.getName());
    			arry.add(Integer.parseInt(requestIdTo.getText()));
    			DBmessage dbm;
    			dbm = new DBmessage(MessageType.SearchReqITManager, arry); 
    			try {
    				MainAllControllers.sendToAbsServer(dbm);
    			} catch (IOException e) {
    			}
    		}
    	}
*/
    }

    @FXML
    void showRequestDetails(MouseEvent event) {

    }

	/*public void setTextInTable(ArrayList<Object> list) {	
			 rows= FXCollections.observableArrayList();
		    	for(Object r:list)
		    		rows.add((requestSuper)r);		
		    	requestTable.setItems(rows);	
		 }
		*/
	}


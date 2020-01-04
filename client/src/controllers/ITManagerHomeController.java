package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ITManagerHomeController implements Initializable 
{
	private MainAllControllers MainAllControllers;
	public ITManagerHomeController()
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
    private Button logoutBTN1;

    @FXML
    private Button personalInfoBTN;

    @FXML
    private Button MessageBTN;

    @FXML
    private Button helpBTN;

    @FXML
    private Button logoutBTN;

    @FXML
    private Label helloITmanager;

    @FXML
    private Label ExistingReq;

    @FXML
    void goEmployeesMang(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerEmployeesManagment");
    	MainAllControllers.changeWin();
	}
    @FXML
    void goHelpPage(MouseEvent event) 	throws IOException 
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
    void gogenerateReport(MouseEvent event) {

    }
    
    @FXML
    void messagePage(MouseEvent event) {

    }

    @FXML
    void logoutPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
	}
    void setRequestNumber(int num)
    {
    	ExistingReq.setText("Existing Requests : "+num);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		helloITmanager.setText("Hello "+MainAllControllers.user.getName());
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.user.getName());
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.MhomeRequestNum, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}

}

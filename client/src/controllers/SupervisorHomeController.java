package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class SupervisorHomeController implements Initializable 
{
	private MainAllControllers MainAllControllers;
	public SupervisorHomeController()
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
	private Label helloSupervisor;

	@FXML
	private Label ExistingRequests;

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

    void setRequestNumber(int num)
    {
    	ExistingRequests.setText("Existing Requests : "+Integer.toString(num));
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		helloSupervisor.setText("   Hello "+MainAllControllers.user.getName());
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.user.getName());
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.supervisorHomeRequestNum, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}

}

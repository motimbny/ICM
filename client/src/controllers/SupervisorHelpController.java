package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class SupervisorHelpController 
{

	private MainAllControllers MainAllControllers;
	public SupervisorHelpController() 
	{
		MainAllControllers = controllers.MainAllControllers.getInstance();
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
    void goEmployeesMangPage(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("SupervisorEmployeesManagment");
    	MainAllControllers.changeWin();
    }

    @FXML
    void helpBTNE(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("SupervisorHelp");
    	MainAllControllers.changeWin();
    }

    @FXML
    void homeBack(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("SupervisorHome");
    	MainAllControllers.changeWin();
    }

    @FXML
    void logoutBTNE(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
    }

    @FXML
    void personBTNE(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("SupervisorPersonalInfo");
    	MainAllControllers.changeWin();
    }

    @FXML
    void showreBTNE(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("SupervisorShowRequests");
    	MainAllControllers.changeWin();
    }

}

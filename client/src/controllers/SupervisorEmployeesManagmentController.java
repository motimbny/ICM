package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class SupervisorEmployeesManagmentController 
{
	private MainAllControllers MainAllControllers;
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
    private TableView<?> EmployeesTable;
    
    @FXML
    private TableColumn<?, ?> EmployeeID;

    @FXML
    private TableColumn<?, ?> FirstName;

    @FXML
    private TableColumn<?, ?> LastName;

    @FXML
    private TableColumn<?, ?> Email;

    @FXML
    private TableColumn<?, ?> NumberOfProjects;

    @FXML
    private Button setEmployeePermissionBTN;

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
    void goSetEmployeePermission(MouseEvent event) {

    }

    @FXML
    void goShowReqPage(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("SupervisorShowRequests");
    	MainAllControllers.changeWin();
    }

}

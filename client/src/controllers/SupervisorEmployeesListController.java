package controllers;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class SupervisorEmployeesListController 
{
	private MainAllControllers MainAllControllers;
	public SupervisorEmployeesListController()
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
    private TableView<?> employeesTable;

    @FXML
    private TableColumn<?, ?> EmployeeID;

    @FXML
    private TableColumn<?, ?> EmployeeName;

    @FXML
    private TableColumn<?, ?> NumberOfProjects;

    @FXML
    private Button chooseRequestBTN;

    @FXML
    void chooseEmployee(MouseEvent event) {

    }

    @FXML
    void goEmployeesMangPage(MouseEvent event) {

    }

    @FXML
    void goHelpPage(MouseEvent event) {

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
    void goPersonalPage(MouseEvent event) {

    }

    @FXML
    void goShowReqPage(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("SupervisorShowRequests");
    	MainAllControllers.changeWin();
    }
}


package controllers;
import entity.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UserShowRequestsController {

	public UserShowRequestsController()
	{
		MainAllControllers=MainAllControllers.getInstance();
	}
	
	private MainAllControllers MainAllControllers;
	
    @FXML
    private Button homeBTN;

    @FXML
    private Button addreBTN;

    @FXML
    private Button showreBTN;

    @FXML
    private Button personBTN;

    @FXML
    private Button helpBTN;

    @FXML
    private Button logoutBTN;

    @FXML
    private TableView<Request> requestsTable = new TableView<Request>();
    
    @FXML
    private TableColumn<Request, String> RequestID;

    @FXML
    private TableColumn<Request, String> RequestStatus;

    @FXML
    private TableColumn<Request, String> RequestStage;

    @FXML
    private TableColumn<Request, Integer> TimeLeft;
    
    @FXML
    void goAddReqPage(ActionEvent event) 
    {
    	MainAllControllers.setWindowVar("UserAddRequest");
    	MainAllControllers.changeWin();
    }

    @FXML
    void goHelpPage(ActionEvent event) {

    }

    @FXML
    void goHomePage(ActionEvent event) 
    {
    	MainAllControllers.setWindowVar("userHome");
    	MainAllControllers.changeWin();
    }

    @FXML
    void goLogoutPage(ActionEvent event) 
    {
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
    }

    @FXML
    void goPersonalPage(ActionEvent event) {

    }

    @FXML
    void goShowReqPage(ActionEvent event) 
    {
    	MainAllControllers.setWindowVar("UserShowRequests");
    	MainAllControllers.changeWin();
    }
    
    public void setTextInTable()
    {
    	
    	
    }

}

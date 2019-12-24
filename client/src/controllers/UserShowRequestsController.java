package controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import Enums.StageName;
import entity.DBmessage;
import entity.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class UserShowRequestsController implements Initializable  {
	
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
    private TableColumn<Request, StageName> RequestStage;

    @FXML
    private TableColumn<Request, Integer> TimeLeft;
    
    @FXML
    void goAddReqPage(ActionEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("UserAddRequest");
    	MainAllControllers.changeWin();
    }

    @FXML
    void goHelpPage(ActionEvent event) {

    }

    @FXML
    void goHomePage(ActionEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("userHome");
    	MainAllControllers.changeWin();
    }

    @FXML
    void goLogoutPage(ActionEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
    }

    @FXML
    void goPersonalPage(ActionEvent event) {

    }

    @FXML
    void goShowReqPage(ActionEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("UserShowRequests");
    	MainAllControllers.changeWin();
    }
    
    public void setTextInTable()
    {
    	requestsTable.getItems();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		MainAllControllers=MainAllControllers.getInstance();
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.user);
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.ShowReqUser, arry);   
    	try {
			MainAllControllers.mcABS.sendToServer(dbm);
		} catch (IOException e) {}
		
	}

}

package controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import Enums.StageName;
import entity.DBmessage;
import entity.Request;
import entity.RequestUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class UserShowRequestsController implements Initializable  {
	
	private ObservableList<RequestUser> rows; 
	private MainAllControllers MainAllControllers;
	public UserShowRequestsController()
	{
    	MainAllControllers=MainAllControllers.getInstance();
	}
	
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
    private TableView<RequestUser> requestsTable;
    
    @FXML
    private TableColumn<RequestUser, Integer> id;

    @FXML
    private TableColumn<RequestUser, String> currentStatus;

    @FXML
    private TableColumn<RequestUser, StageName> currentStage;

    @FXML
    private TableColumn<RequestUser, Integer> timeLeft;
    @FXML
    private Button search;

    @FXML
    private TextField requestIdTo;
    @FXML
    void searchRequest(MouseEvent event)
    {
    	ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.user.getName());
		arry.add(Integer.parseInt(requestIdTo.getText()));
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.SearchReqUser, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
    @FXML
    void goAddReqPage(ActionEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("UserAddRequest");
    	MainAllControllers.changeWin();
    }

    @FXML
    void goHelpPage(ActionEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("UserHelp");
    	MainAllControllers.changeWin();
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
    void goPersonalPage(ActionEvent event) throws IOException
    {
    	MainAllControllers.setWindowVar("UserPersonalInfo");
    	MainAllControllers.changeWin();
    }

    @FXML
    void goShowReqPage(ActionEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("UserShowRequests");
    	MainAllControllers.changeWin();
    }
    
    @FXML
    void showReqT(MouseEvent event)  {
    	requestsTable.setOnMouseClicked((MouseEvent ev ) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                try {
					MainAllControllers.setWindowVar("UserRequestDetails");
					MainAllControllers.changeWin();
				} catch (IOException e) {
					e.printStackTrace();
				}                
            }
        });
    }
    
    public void setTextInTable(ArrayList<Object> list)
    {
    	
    	rows= FXCollections.observableArrayList();
    	for(Object r:list)
    		rows.add((RequestUser)r);		
    	requestsTable.setItems(rows);
    }

    public void requestServer()
    {
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.user.getName());
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.ShowReqUser, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{

		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		currentStatus.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
		currentStage.setCellValueFactory(new PropertyValueFactory<>("currentStage"));
		timeLeft.setCellValueFactory(new PropertyValueFactory<>("timeLeft"));
		requestServer();
	}
	public void clearTable()
	{
		requestsTable.getItems().clear();
	}

}

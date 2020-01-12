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

/**
 * User show requests screen Controller .
 *
 * @author SHIRA
 */

public class UserShowRequestsController implements Initializable  {
	
	/** The rows. */
	private ObservableList<RequestUser> rows; 
	
	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;
	
	/**
	 * Instantiates a new user show requests controller.
	 */
	public UserShowRequestsController()
	{
    	MainAllControllers=MainAllControllers.getInstance();
	}
	
    /** The home BTN. */
    @FXML
    private Button homeBTN;

    /** The addre BTN. */
    @FXML
    private Button addreBTN;

    /** The showre BTN. */
    @FXML
    private Button showreBTN;

    /** The person BTN. */
    @FXML
    private Button personBTN;

    /** The help BTN. */
    @FXML
    private Button helpBTN;

    /** The logout BTN. */
    @FXML
    private Button logoutBTN;

    /** The requests table. */
    @FXML
    private TableView<RequestUser> requestsTable;
    
    /** The id. */
    @FXML
    private TableColumn<RequestUser, Integer> id;

    /** The current status. */
    @FXML
    private TableColumn<RequestUser, String> currentStatus;

    /** The current stage. */
    @FXML
    private TableColumn<RequestUser, StageName> currentStage;

    /** The time left. */
    @FXML
    private TableColumn<RequestUser, Integer> timeLeft;
    
    /** The search. */
    @FXML
    private Button search;

    /** The request id to. */
    @FXML
    private TextField requestIdTo;
    
    /**
     * Search request.
     *
     * @param event the event
     */
    @FXML
    void searchRequest(MouseEvent event)
    {
    	if(requestIdTo.getText().equals(""))
    	{
    		try {
				MainAllControllers.setWindowVar("UserShowRequests");
			} catch (IOException e) {
				e.printStackTrace();
			}
        	MainAllControllers.changeWin();
    	}
    	else
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
    }
    
    /**
     * Mouse click event, if "Add request" button clicked, open the screen of "Add new request".
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goAddReqPage(ActionEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("UserAddRequest");
    	MainAllControllers.changeWin();
    }
    
    /**
     * Mouse click event, if "help" button clicked, open the screen of "help".
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goHelpPage(ActionEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("UserHelp");
    	MainAllControllers.changeWin();
    }
    
    /**
     * Mouse click event, if "Home" button clicked, open the screen of "Home".
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goHomePage(ActionEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("userHome");
    	MainAllControllers.changeWin();
    }
    
    /**
     * Mouse click event, if "logOut" button clicked, open the screen of "LogOut" and clean the fields.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goLogoutPage(ActionEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.logOutUser();
    }
    
    /**
     * Mouse click event, if "Personal info" button clicked, open the screen of "Personal information".
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goPersonalPage(ActionEvent event) throws IOException
    {
    	MainAllControllers.setWindowVar("UserPersonalInfo");
    	MainAllControllers.changeWin();
    }
    
    /**
     * Mouse click event, if "Show requests" button clicked, open the screen of "Show requests".
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goShowReqPage(ActionEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("UserShowRequests");
    	MainAllControllers.changeWin();
    }
    
    /**
     * Show req T.
     *
     * @param event the event
     */
    @FXML
    void showReqT(MouseEvent event)  {
    	requestsTable.setOnMouseClicked((MouseEvent ev ) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                try {
                	MainAllControllers.request=requestsTable.getItems().get(requestsTable.getSelectionModel().getSelectedIndex()).getId();
					MainAllControllers.setWindowVar("UserRequestDetails");
					MainAllControllers.changeWin();
				} catch (IOException e) {
					e.printStackTrace();
				}                
            }
        });
    }
    
    /**
     * Sets the text in table.
     *
     * @param list the new text in table
     */
    public void setTextInTable(ArrayList<Object> list)
    {
    	
    	rows= FXCollections.observableArrayList();
    	for(Object r:list)
    		rows.add((RequestUser)r);		
    	requestsTable.setItems(rows);
    }

    /**
     * Request server.
     */
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
    
    /**
     * Initializes GUI components before this window open.
     *
     * @param location the location
     * @param resources the resources
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{

		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		currentStatus.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
		currentStage.setCellValueFactory(new PropertyValueFactory<>("currentStage"));
		timeLeft.setCellValueFactory(new PropertyValueFactory<>("timeLeft"));
		requestServer();
	}
	
	/**
	 * Clear table.
	 */
	public void clearTable()
	{
		requestsTable.getItems().clear();
	}
	
	/**
	 * Gets the requests table.
	 *
	 * @return the requests table
	 */
	public TableView<RequestUser> getRequestsTable() {
		return requestsTable;
	}

	
	

}

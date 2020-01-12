package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.extensionrequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Supervisor extension request screen controller.
 *
 * @author SHIRA
 */
public class SupervisorExtensionRequestController implements Initializable
{
	
	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;
	
	/**
	 * Instantiates a new supervisor extension request controller.
	 */
	public SupervisorExtensionRequestController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}
    
    /** The home BTN. */
    @FXML
    private Button homeBTN;

    /** The showre BTN. */
    @FXML
    private Button showreBTN;

    /** The person BTN. */
    @FXML
    private Button personBTN;

    /** The Message BTN. */
    @FXML
    private Button MessageBTN;
    
    /** The help BTN. */
    @FXML
    private Button helpBTN;

    /** The logout BTN. */
    @FXML
    private Button logoutBTN;
    
    /** The back. */
    @FXML
    private Button BACK;
    
    /** The approve. */
    @FXML
    private Button APPROVE;

    /** The deny. */
    @FXML
    private Button DENY;

    /** The request ID. */
    @FXML
    private TextField requestID;

    /** The request STG. */
    @FXML
    private TextField requestSTG;

    /** The request IT. */
    @FXML
    private TextField requestIT;

    /** The request E xp. */
    @FXML
    private TextArea requestEXp;
    
    /** The answer. */
    @FXML
    private Label answer;
    
    /** The request time. */
    @FXML
    private TextField requestTime;
    
    /**
     * Approve EX.
     *
     * @param event the event
     */
    @FXML
    void approveEX(MouseEvent event) 
    {
    	DBmessage dbm;
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		arry.add("approve");
    	dbm=new DBmessage(MessageType.superviserExtensionRequestAnswer, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
    
    /**
     * Deny EXT.
     *
     * @param event the event
     */
    @FXML
    void denyEXT(MouseEvent event) 
    {
    	DBmessage dbm;
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		arry.add("denied");
    	dbm=new DBmessage(MessageType.superviserExtensionRequestAnswer, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
    
    /**
     * Back to show.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void backToShow(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("SupervisorShowRequests");
    	MainAllControllers.changeWin();
    }
    
    /**
     * Mouse click event, if "help" button clicked, open the screen of "help".
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
	void goHelpPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorHelp");
    	MainAllControllers.changeWin();
	}
    
    /**
     * Mouse click event, if "Home" button clicked, open the screen of "Home".
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
	@FXML
	void goHomePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorHome");
    	MainAllControllers.changeWin();
	}
	 
 	/**
 	 * Mouse click event, if "logOut" button clicked, open the screen of "LogOut" and clean the fields.
 	 *
 	 * @param event the event
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
	@FXML
	void goLogoutPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.logOutUser();
	}
	 
 	/**
 	 * Mouse click event, if "Messages" button clicked, open the screen of "Messages".
 	 *
 	 * @param event the event
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
	 @FXML
	 void messagePage(MouseEvent event) throws IOException 
		{
	    	MainAllControllers.setWindowVar("SupervisorMessages");
	    	MainAllControllers.changeWin();
		}
	 
 	/**
 	 * Mouse click event, if "Personal info" button clicked, open the screen of "Personal information".
 	 *
 	 * @param event the event
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
	@FXML
	void goPersonalPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorPersonalInfo");
    	MainAllControllers.changeWin();
	}
	 
 	/**
 	 * Mouse click event, if "Show requests" button clicked, open the screen of "Show requests".
 	 *
 	 * @param event the event
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
	@FXML
	void goShowReqPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorShowRequests");
    	MainAllControllers.changeWin();
	}
	
	/**
	 * Sets the to fields.
	 *
	 * @param ev the new to fields
	 */
	public void setToFields(extensionrequest ev)
	{
		this.requestID.setText(Integer.toString(ev.getId()));
		this.requestSTG.setText(ev.getStage());
		this.requestIT.setText(ev.getItHandler());
		this.requestEXp.setText(ev.getReason());
		this.requestTime.setText(Integer.toString(ev.getTime()));
	}
	  
  	/**
  	 * Initializes GUI components before this window open.
  	 *
  	 * @param arg0 the arg 0
  	 * @param arg1 the arg 1
  	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		DBmessage dbm;
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.request);
    	dbm=new DBmessage(MessageType.superviserExtensionRequest, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}
	
	/**
	 * Show answer.
	 */
	public void showAnswer()
	{
		answer.setVisible(true);	
	}

}

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

/**
 *  The Class ITManagerHomeController.
 *  This This window is in IT Manager GUI and this is
 *  the first window after successful login.
 *  
 *  @author SHIRA
 */
public class ITManagerHomeController implements Initializable 
{
	
	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;
	
	/**
	 * Instantiates a new IT manager home controller.
	 */
	public ITManagerHomeController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}

    /** The home BTN. */
    @FXML
    private Button homeBTN;

    /** The show request BTN. */
    @FXML
    private Button showRequestBTN;

    /** The generate report BTN 1. */
    @FXML
    private Button generateReportBTN1;

    /** The employees mang BTN. */
    @FXML
    private Button employeesMangBTN;

    /** The logout BTN 1. */
    @FXML
    private Button logoutBTN1;

    /** The personal info BTN. */
    @FXML
    private Button personalInfoBTN;

    /** The Message BTN. */
    @FXML
    private Button MessageBTN;

    /** The help BTN. */
    @FXML
    private Button helpBTN;

    /** The logout BTN. */
    @FXML
    private Button logoutBTN;

    /** The hello I tmanager. */
    @FXML
    private Label helloITmanager;

    /** The Existing req. */
    @FXML
    private Label ExistingReq;
    
    /** The new message. */
    @FXML
    private Label newMessage;
    
    /**
	 * Mouse click event, if "Management" button clicked, open the screen of
	 * "Employees management".
	 *
	 * @param event The Management BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    @FXML
    void goEmployeesMang(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerEmployeesManagment");
    	MainAllControllers.changeWin();
	}
    
    /**
	 * Mouse click event, if "help" button clicked, open the screen of "help".
	 *
	 * @param event The help BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    @FXML
    void goHelpPage(MouseEvent event) 	throws IOException 
    	{
        	MainAllControllers.setWindowVar("ITManagerHelp");
        	MainAllControllers.changeWin();
    	}

    /**
	 * Mouse click event, if "Home" button clicked, open the screen of "Home".
	 *
	 * @param event The Home BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    @FXML
    void goHomePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerHome");
    	MainAllControllers.changeWin();
	}

    /**
	 * Mouse click event, if "Personal info" button clicked, open the screen of
	 * "Personal information".
	 *
	 * @param event The Personal info BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    @FXML
    void goPersonalInfo(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerPersonalInfo");
    	MainAllControllers.changeWin();
	}

    /**
	 * Mouse click event, if "Show requests" button clicked, open the screen of
	 * "Show requests".
	 *
	 * @param event The Show request BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    @FXML
    void goShowReq(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerShowRequests");
    	MainAllControllers.changeWin();
	}


    /**
	 * Mouse click event, if "Reports" button clicked, open the screen of "Reports".
	 *
	 * @param event The Reports BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    @FXML
    void gogenerateReport(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerReports");
    	MainAllControllers.changeWin();
	}
    
    /**
	 * Mouse click event, if "Messages" button clicked, open the screen of
	 * "Messages".
	 *
	 * @param event The Messages BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    @FXML
    void messagePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerMessages");
    	MainAllControllers.changeWin();
	}


    /**
	 * Mouse click event, if "logOut" button clicked, open the screen of "LogOut"
	 * and clean the fields.
	 *
	 * @param event The Logout BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    @FXML
    void logoutPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.logOutUser();
	}
    
    /**
     * Set requests number in the screen .
	 * 
	 * @param num The number of total requests
     */
    void setRequestNumber(int num)
    {
    	ExistingReq.setText("Existing Requests : "+num);
    }
    
    /**
     * Sets the number of messages in the screen.
	 *
	 * @param num the new messages number
     */
    void setMessagesNumber(int num)
    {
    	newMessage.setText("You have: "+num+" unread messages");
    }
    
	/**
	 * Initializes GUI components before this window open. Get the user name from
	 * the DB and show it in this screen Also get the number of request of this user
	 * from DB
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
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

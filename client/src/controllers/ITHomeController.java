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
 * IT home screen Controller .
 *
 * @author SHIRA
 */
public class ITHomeController implements Initializable
{	
	
	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;
    
    /**
     * Instantiates a new IT home controller.
     */
    public ITHomeController()
    {
    	MainAllControllers=controllers.MainAllControllers.getInstance();
    }
    
	/** The home BTN. */
	@FXML
	private Button homeBTN;

	/** The show request BTN. */
	@FXML
	private Button showRequestBTN;

	/** The personal info BTN. */
	@FXML
	private Button personalInfoBTN;

	/** The help BTN. */
	@FXML
	private Button helpBTN;

	/** The logout BTN. */
	@FXML
	private Button logoutBTN;

	/** The hello IT. */
	@FXML
	private Label helloIT;

	/** The Existing req. */
	@FXML
	private Label ExistingReq;

	 /**
 	 * Mouse click event, if "help" button clicked, open the screen of "help".
 	 *
 	 * @param event the event
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */

	@FXML
	void goHelpPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITHelp");
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
    	MainAllControllers.setWindowVar("ITHome");
    	MainAllControllers.changeWin();
	}
	 
 	/**
 	 * Mouse click event, if "Personal info" button clicked, open the screen of "Personal information".
 	 *
 	 * @param event the event
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
	@FXML
	void goPersonalInfo(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITPersonalInfo");
    	MainAllControllers.changeWin();
	}
	 
 	/**
 	 * Mouse click event, if "Show requests" button clicked, open the screen of "Show requests".
 	 *
 	 * @param event the event
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
	@FXML
	void goShowReq(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITshowRequests");
    	MainAllControllers.changeWin();
	}
	 
 	/**
 	 * Mouse click event, if "logOut" button clicked, open the screen of "LogOut" and clean the fields.
 	 *
 	 * @param event the event
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
	 * This function set number of request in the screen.
	 *
	 * @param num the new request number
	 */
    void setRequestNumber(int num)
    {
    	ExistingReq.setText("Existing requests: "+Integer.toString(num));
    }
	
	/**
	 * Initialize.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		helloIT.setText("Hello "+MainAllControllers.user.getName());
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.user.getName());
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.IThomeRequestNum, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}

}

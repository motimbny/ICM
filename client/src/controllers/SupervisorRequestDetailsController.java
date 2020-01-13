package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.Request;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Supervisor request details screen controller.
 *
 * @author SHIRA
 */
public class SupervisorRequestDetailsController implements Initializable
{

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;
	
	/**
	 * Instantiates a new supervisor request details controller.
	 */
	public SupervisorRequestDetailsController()
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

    /** The help BTN. */
    @FXML
    private Button helpBTN;

    /** The Message BTN. */
    @FXML
	private Button MessageBTN;
    
    /** The logout BTN. */
    @FXML
    private Button logoutBTN;

    /** The req number. */
    @FXML
    private Label reqNumber;

    /** The Applicant name field. */
    @FXML
    private TextField ApplicantNameField;

    /** The Information system field. */
    @FXML
    private TextField InformationSystemField;

    /** The request status field. */
    @FXML
    private TextField requestStatusField;

    /** The Description existing situation field. */
    @FXML
    private TextArea DescriptionExistingSituationField;

    /** The Description of request field. */
    @FXML
    private TextArea DescriptionOfRequestField;

    /** The Request stage field. */
    @FXML
    private TextField RequestStageField;

    /** The Back to show. */
    @FXML
    private Button BackToShow;

   
    /**
     * Back to S.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void BackToS(MouseEvent event) throws IOException 
	{
    	try {
    		if(MainAllControllers.nowWin.equals("ITRequestSuperviser"))
			     MainAllControllers.setWindowVar("SupervisorUpdateRequest");
    		else if(MainAllControllers.nowWin.equals("SupervisorShowRequests"))
        			MainAllControllers.setWindowVar("SupervisorShowRequests");
    		else
    			MainAllControllers.setWindowVar("SupervisorTimeRequest");
	    	MainAllControllers.changeWin();
		}
		catch (Exception e) {
			
		}
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
    void goHomePage(MouseEvent event)  throws IOException 
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
     * Mouse click event, if "Personal info" button clicked, open the screen of "Personal information".
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goPersonalPage(MouseEvent event)throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorPersonalInfo");
    	MainAllControllers.changeWin();
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
     * Sets the text in fields.
     *
     * @param listR the new text in fields
     */
    void setTextInFields(ArrayList<Object> listR)
    {
    	Request req=(Request)listR.get(0);	  	
    	ApplicantNameField.setText(req.getUserSubFullName());
    	InformationSystemField.setText(req.getInfoSystem());
    	requestStatusField.setText(req.getCurrentStatus());
    	RequestStageField.setText(req.getCurrentStage().toString());
    	DescriptionExistingSituationField.setText(req.getDesExtSit());
    	DescriptionOfRequestField.setText(req.getWantedChange());   	
    }
	
	/**
	 * Initialize.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		ArrayList<Object> arry=new ArrayList<Object>();
		int s=MainAllControllers.request;
		arry.add(s);//request id
		reqNumber.setText("Request number: "+s);
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.showRequestDetailsSuperviser, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
		
	}

}

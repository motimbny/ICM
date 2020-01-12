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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


/**
 * The Class ITExtensionRequestController.
 */
public class ITExtensionRequestController implements Initializable 
{
	
	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;
    
    /**
     * Instantiates a new IT extension request controller.
     */
    public ITExtensionRequestController()
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

    /** The Request ID. */
    @FXML
    private TextField RequestID;

    /** The Req stage. */
    @FXML
    private TextField ReqStage;

    /** The it handler. */
    @FXML
    private TextField itHandler;

    /** The Reason. */
    @FXML
    private TextArea Reason;

    /** The submit extension request BTN. */
    @FXML
    private Button submitExtensionRequestBTN;

    /** The Back to show. */
    @FXML
    private Button BackToShow;

    /** The time to add. */
    @FXML
    private TextField timeToAdd;
    
    /** The request was submitted. */
    @FXML
    private Label requestWasSubmitted;

    /** The fill all fields. */
    @FXML
    private Label fillAllFields;

    /**
     * Back to S.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void BackToS(MouseEvent event) throws IOException 
    {
		MainAllControllers.setWindowVar("ITHandleRequest");
		MainAllControllers.changeWin();
    }

	/**
	 * Go help page.
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
	 * Go home page.
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
	 * Go personal info.
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
	 * Go show req.
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
	 * Logout page.
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
     * Submit extension request.
     *
     * @param event the event
     */
    @FXML
    void submitExtensionRequest(MouseEvent event) 
    {
    	
    	if(Reason.getText().equals("")||timeToAdd.getText().equals(""))
    	{
    		fillAllFields.setVisible(true);
    	}
    	else
    	{
    		fillAllFields.setVisible(false);
    		ArrayList<Object> arry=new ArrayList<Object>(); 
	        arry.add(Integer.parseInt(RequestID.getText()));
	        arry.add(ReqStage.getText());
	        arry.add(itHandler.getText()); 
	        arry.add(Integer.parseInt(timeToAdd.getText()));
    		arry.add(Reason.getText());
    		DBmessage dbm;
        	dbm=new DBmessage(MessageType.AddExtensionRequest, arry);   
        	try {
        	
        		MainAllControllers.sendToAbsServer(dbm);
    		} catch (IOException e) {}
    	}

    }
    
    /**
     * Sets the on sucsess.
     *
     * @param num the new on sucsess
     */
    public void setOnSucsess(int num)
    {	
    	if(num==1)
    	requestWasSubmitted.setVisible(true);
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
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.user.getName());
		arry.add(MainAllControllers.request); //request id
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.ITgetReqStage, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}
	
	/**
	 * Sets the text in fields.
	 *
	 * @param list the new text in fields
	 */
	void setTextInFields(ArrayList<Object> list)
	{
		RequestID.setText(""+MainAllControllers.request);
		itHandler.setText(MainAllControllers.user.getName());
		ReqStage.setText((String)list.get(0));
	}

}

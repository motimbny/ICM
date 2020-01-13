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
 * The Class ITMeaningAssessmentEvaluationReportController.
 */
public class ITMeaningAssessmentEvaluationReportController implements Initializable
{
	
	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;
    
    /**
     * Instantiates a new IT meaning assessment evaluation report controller.
     */
    public ITMeaningAssessmentEvaluationReportController()
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

    /** The Location. */
    @FXML
    private TextField Location;

    /** The description of change required. */
    @FXML
    private TextArea descriptionOfChangeRequired;

    /** The result of change. */
    @FXML
    private TextArea resultOfChange;

    /** The time estimated. */
    @FXML
    private TextField timeEstimated;

    /** The constraints and risks. */
    @FXML
    private TextArea constraintsAndRisks;

    /** The request ID. */
    @FXML
    private TextField requestID;

    /** The submit evaluation report BTN. */
    @FXML
    private Button submitEvaluationReportBTN;
    
    /** The request was submitted. */
    @FXML
    private Label requestWasSubmitted;

    /** The fill all fields. */
    @FXML
    private Label fillAllFields;
    
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
     * Submit evaluation report.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void submitEvaluationReport(MouseEvent event) throws IOException 
    {
    	if(timeEstimated.getText().equals("")||constraintsAndRisks.getText().equals("")||resultOfChange.getText().equals("")||descriptionOfChangeRequired.getText().equals(""))
    	{
    		fillAllFields.setVisible(true);
    	}
    	if(timeEstimated.getText().matches("[0-100]"))
    	{
    		fillAllFields.setVisible(true);
    	}
    	else
    	{
    		fillAllFields.setVisible(false);
    		ArrayList<Object> arry=new ArrayList<Object>();
    		arry.add(Integer.parseInt(requestID.getText()));
    		arry.add(Location.getText());
    		arry.add(timeEstimated.getText());
    		arry.add(descriptionOfChangeRequired.getText());
    		arry.add(resultOfChange.getText());
    		arry.add(constraintsAndRisks.getText());
    		DBmessage dbm;
			dbm = new DBmessage(MessageType.ITSubmitEvaluationReport, arry); 
			try {
				MainAllControllers.sendToAbsServer(dbm);
			} catch (IOException e) {
			}
    	}
    	goShowReq(event);
    }

    /**
     * Sets the on sucsess.
     */
    public void setOnSucsess()
    {	
    	requestWasSubmitted.setVisible(true);
    }
    
    public void showReqAgain() throws IOException
    {
    	MainAllControllers.setWindowVar("ITshowRequests");
		MainAllControllers.changeWin();
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
		int s=MainAllControllers.request;
		arry.add(s); //request id
		requestID.setText(""+s);
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.ITgetLocation, arry);   
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
    	Location.setText((String)list.get(0));
    }

}

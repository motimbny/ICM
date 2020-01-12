package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.UIManager;

import Enums.MessageType;
import Enums.StageName;
import entity.DBmessage;
import entity.Request;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


/**
 * User request details screen Controller .
 *
 * @author SHIRA
 */
public class UserRequestDetailsController implements Initializable  
{
	
	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;
	
	/**
	 * Instantiates a new user request details controller.
	 */
	public UserRequestDetailsController()
	{
    	MainAllControllers=MainAllControllers.getInstance();
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
    
    /** The addre BTN. */
    @FXML
    private Button addreBTN;

    /** The logout BTN. */
    @FXML
    private Button logoutBTN;

    /** The request id field. */
    @FXML
    private Text requestIdField;

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
    
    /** The progress B. */
    @FXML
    private ProgressBar progressB;
    
    /**
     * Back to S.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void BackToS(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserShowRequests");
		MainAllControllers.changeWin();
	}
    
    /**
     * Mouse click event, if "Add request" button clicked, open the screen of "Add new request".
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */   
    @FXML
    void addreBTNE(MouseEvent event)throws IOException {
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
    void goHelpPage(MouseEvent event) throws IOException {
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
    void goHomePage(MouseEvent event) throws IOException {

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
    void goLogoutPage(MouseEvent event) throws IOException {
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
    void goPersonalPage(MouseEvent event) throws IOException {
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
    void goShowReqPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserShowRequests");
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
    	setProgressBar(req);
    }
    
    /**
     * Sets the progress bar.
     *
     * @param req the new progress bar
     */
    public void setProgressBar(Request req)
    {
    	 if(req.getCurrentStage().equals(StageName.supervisorApprovel)||req.getCurrentStage().equals(StageName.waitingEvaluationTime)
    			 ||req.getCurrentStage().equals(StageName.waitingSupervisorApproveEvaluationTime)||req.getCurrentStage().equals(StageName.meaningAssessment))
    	 {
        	 progressB.setStyle("-fx-accent:green");
         	progressB.setProgress(0.25);
    	 }
    	 else if(req.getCurrentStage().equals(StageName.waitingExecutionTime)||req.getCurrentStage().equals(StageName.waitingSupervisorApproveExecutionTime)
    			 ||req.getCurrentStage().equals(StageName.waitingSupervisorApproveExecutionTime)||req.getCurrentStage().equals(StageName.examinationAndDecision))
    	 {
        	 progressB.setStyle("-fx-accent:green");
         	progressB.setProgress(0.5);
    	 }
    	 else if(req.getCurrentStage().equals(StageName.execution)||req.getCurrentStage().equals(StageName.testing))
    	 {
        	 progressB.setStyle("-fx-accent:green");
         	progressB.setProgress(0.75);
    	 }
    	 else if(req.getCurrentStage().equals(StageName.closing)||req.getCurrentStage().equals(StageName.Closed))
    	 {
        	 progressB.setStyle("-fx-accent:red");
         	progressB.setProgress(1);
    	 }
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
		
		
		ArrayList<Object> arry=new ArrayList<Object>();
		int s=MainAllControllers.request;
		arry.add(s);//request id
		requestIdField.setText("request number: "+s);
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.showRequestDetailsUser, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}
    
    

}

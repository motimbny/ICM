package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
 * The Class ITTestFailurReportController.
 */
public class ITTestFailurReportController implements Initializable 
{

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;
    
    /**
     * Instantiates a new IT test failur report controller.
     */
    public ITTestFailurReportController()
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

    /** The Req ID. */
    @FXML
    private TextField ReqID;

    /** The submit failur report BTN. */
    @FXML
    private Button submitFailurReportBTN;

    /** The Report summry. */
    @FXML
    private TextArea ReportSummry;

    /** The fill all fields. */
    @FXML
    private Label fillAllFields;

    /** The report was submitted. */
    @FXML
    private Label reportWasSubmitted;

    /** The Back to show. */
    @FXML
    private Button BackToShow;

    /** The date. */
    @FXML
    private TextField date;
    
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
     * Submit failur report.
     *
     * @param event the event
     */
    @FXML
    void submitFailurReport(MouseEvent event) 
    {
    	if(ReportSummry.getText().equals(""))
    	{
    		fillAllFields.setVisible(true);
    	}
    	else
    	{
    		fillAllFields.setVisible(false);
        	ArrayList<Object> arr=new ArrayList<Object>();
        	arr.add(ReqID.getText());
        	arr.add(date.getText());
        	arr.add(ReportSummry.getText());
        	DBmessage dbm=new DBmessage(MessageType.ITFailurReport, arr);
        	try {
				MainAllControllers.sendToAbsServer(dbm);
			} catch (IOException e) {}
    	}
    }
    
    /**
     * Sets the on sucsess.
     */
    public void setOnSucsess()
    {
    	reportWasSubmitted.setVisible(true);
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
		arry.add(s);//request id
		ReqID.setText(""+s);
		LocalDate myObj = LocalDate.now();
		date.setText("" + myObj);
	}
}

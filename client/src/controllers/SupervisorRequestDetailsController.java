package controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.Request;
import entity.ServerFile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Supervisor request details screen controller. This window is in Supervisor
 * GUI and display the request details
 *
 * @author SHIRA
 */
public class SupervisorRequestDetailsController implements Initializable {

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/**
	 * Instantiates a new supervisor request details controller.
	 */
	public SupervisorRequestDetailsController() {
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	/** The home BTN. */
	@FXML
	private Button homeBTN;

	/** The show request BTN. */
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

	/** The request number. */
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
	 @FXML
	 private Button showAttach;
	 @FXML
	 void showAttachfile(MouseEvent event)
	 {
		   System.out.println("im here op");
		   ArrayList<Object> arry=new ArrayList<Object>();
			arry.add(MainAllControllers.request);
			arry.add(MainAllControllers.request);
			DBmessage dbm;
	    	dbm=new DBmessage(MessageType.showattachfile, arry);   
	    	try {
	    		MainAllControllers.sendToAbsServer(dbm);
			} catch (IOException e) {}
	 }

	 
	/**
	 * Mouse click event, if "Back" button clicked, open the screen of "Show
	 * requests".
	 *
	 * @param event The Back BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void BackToS(MouseEvent event) throws IOException {
		try {
			if (MainAllControllers.nowWin.equals("ITRequestSuperviser"))
				MainAllControllers.setWindowVar("SupervisorUpdateRequest");
			else if (MainAllControllers.nowWin.equals("SupervisorShowRequests"))
				MainAllControllers.setWindowVar("SupervisorShowRequests");
			else
				MainAllControllers.setWindowVar("SupervisorTimeRequest");
			MainAllControllers.changeWin();
		} catch (Exception e) {

		}
	}

	/**
	 * Mouse click event, if "help" button clicked, open the screen of "help".
	 *
	 * @param event The Help BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goHelpPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorHelp");
		MainAllControllers.changeWin();
	}

	/**
	 * Mouse click event, if "Home" button clicked, open the screen of "Home".
	 *
	 * @param event The Home BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goHomePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorHome");
		MainAllControllers.changeWin();
	}

	/**
	 * Mouse click event, if "logOut" button clicked, open the screen of "LogOut"
	 * and clean the fields.
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
	 * Mouse click event, if "Personal info" button clicked, open the screen of
	 * "Personal information".
	 *
	 * @param event The Personal info BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goPersonalPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorPersonalInfo");
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
	void messagePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorMessages");
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
	void goShowReqPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorShowRequests");
		MainAllControllers.changeWin();
	}

	/**
	 * This method get the request details from DB and sets the text in fields of
	 * this screen.
	 *
	 * @param listR the new text in fields
	 */
	void setTextInFields(ArrayList<Object> listR) {
		Request req = (Request) listR.get(0);
		ApplicantNameField.setText(req.getUserSubFullName());
		InformationSystemField.setText(req.getInfoSystem());
		requestStatusField.setText(req.getCurrentStatus());
		RequestStageField.setText(req.getCurrentStage().toString());
		DescriptionExistingSituationField.setText(req.getDesExtSit());
		DescriptionOfRequestField.setText(req.getWantedChange());
		System.out.println(req.getAddDocuments());
		if(req.getAddDocuments()==1)
			showAttach.setVisible(true);
			
	}

	/**
	 * Initializes GUI components before this window open. Get the relevant
	 * information from DB and set the request details on fields
	 *
	 * @param location  the arg0
	 * @param resources the arg1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Object> arry = new ArrayList<Object>();
		int s = MainAllControllers.request;
		arry.add(s);// request id
		reqNumber.setText("Request number: " + s);
		DBmessage dbm;
		dbm = new DBmessage(MessageType.showRequestDetailsSuperviser, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}

	}


	public void openRequest(ArrayList<Object> send)
	{
		  ServerFile sf;
		  sf=(ServerFile) send.get(0);
		  int fileSize =sf.getSize(); 
		  System.out.println("Message received: " + sf);
		  System.out.println("length "+ fileSize); 
		  String LocalfilePath="clientfile/";
		  try{
			      File newFile = new File (LocalfilePath+sf.getFileName());    		      
			      byte [] mybytearray  = sf.getMybytearray();		  
			      FileOutputStream fos = new FileOutputStream(newFile);
				  BufferedOutputStream bos = new BufferedOutputStream(fos);
				  bos.write(mybytearray,0,sf.getSize());
			      bos.flush();
			      fos.flush();
			    }
			catch (Exception e) {
				System.out.println("Error send ((Files)msg) to Server");
			}
		
	}

}

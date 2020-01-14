package controllers;

import java.awt.Desktop;
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
 * The Class ITRequestDetailsController.This window is in IT
 * GUI and display the request details
 */
public class ITRequestDetailsController implements Initializable 
{
	
	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;
    
    /**
     * Instantiates a new IT request details controller.
     */
    public ITRequestDetailsController()
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

    /** The User name. */
    @FXML
    private Label UserName;

    /** The email. */
    @FXML
    private Label email;

    /** The position. */
    @FXML
    private Label position;

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
    @FXML
    private Button showAttach;
    /**
	 * Mouse click event, if "Back" button clicked, open the screen of "Show
	 * requests".
	 *
	 * @param event The Back BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    @FXML
    void BackToS(MouseEvent event) throws IOException 
    {
		MainAllControllers.setWindowVar("ITHandleRequest");
		MainAllControllers.changeWin();
    }

    /**
	 * Mouse click event, if "help" button clicked, open the screen of "help".
	 *
	 * @param event The help BTN
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
	 * @param event The Home BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goHomePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITHome");
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
    	MainAllControllers.setWindowVar("ITPersonalInfo");
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
    	MainAllControllers.setWindowVar("ITshowRequests");
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
     * This method get the request details from DB and sets the text in fields of
	 * this screen.
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
    	if(req.getAddDocuments()==1)
			showAttach.setVisible(true);
    }
	
	/**
	 * Initializes GUI components before this window open. Get the relevant
	 * information from DB and set the request details on fields
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
		reqNumber.setText("request number: "+s);
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.showRequestDetailsIT, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}
	 @FXML
	    void showAttachfile(MouseEvent event)
	    {
		    ArrayList<Object> arry=new ArrayList<Object>();
			arry.add(MainAllControllers.request);
			arry.add(MainAllControllers.request);
			DBmessage dbm;
	    	dbm=new DBmessage(MessageType.showattachfileIT, arry);   
	    	try {
	    		MainAllControllers.sendToAbsServer(dbm);
			} catch (IOException e) {}
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
			fileopen(LocalfilePath+sf.getFileName());
		}
	 private void fileopen(String path)
		{
			File file = new File(path);
			Desktop desktop = Desktop.getDesktop();
	        if(file.exists())
				try {
					desktop.open(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

}

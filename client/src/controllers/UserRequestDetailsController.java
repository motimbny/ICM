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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
/**
 * User request details screen Controller 
 * @author SHIRA
 */
public class UserRequestDetailsController implements Initializable  
{
	private MainAllControllers MainAllControllers;
	public UserRequestDetailsController()
	{
    	MainAllControllers=MainAllControllers.getInstance();
	}
	/**
     * buttons and labels of User request details screen
     */
    @FXML
    private Button homeBTN;

    @FXML
    private Button showreBTN;

    @FXML
    private Button personBTN;

    @FXML
    private Button helpBTN;
    
    @FXML
    private Button addreBTN;

    @FXML
    private Button logoutBTN;

    @FXML
    private Text requestIdField;

    @FXML
    private TextField ApplicantNameField;

    @FXML
    private TextField InformationSystemField;

    @FXML
    private TextField requestStatusField;

    @FXML
    private TextArea DescriptionExistingSituationField;

    @FXML
    private TextArea DescriptionOfRequestField;

    @FXML
    private TextField RequestStageField;

    @FXML
    private Button BackToShow;

    @FXML
    void BackToS(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserShowRequests");
		MainAllControllers.changeWin();
	}
    /**
	 * Mouse click event, if "Add request" button clicked, open the screen of "Add new request"
	 * @param event
	 * @throws IOException
	 */   
    @FXML
    void addreBTNE(MouseEvent event)throws IOException {
		MainAllControllers.setWindowVar("UserAddRequest");
		MainAllControllers.changeWin();

	}
    /**
     * Mouse click event, if "help" button clicked, open the screen of "help"
     * @param event
     * @throws IOException
     */
    @FXML
    void goHelpPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserHelp");
		MainAllControllers.changeWin();
	}
    /**
     * Mouse click event, if "Home" button clicked, open the screen of "Home"
     * @param event
     * @throws IOException
     */
    @FXML
    void goHomePage(MouseEvent event) throws IOException {

		MainAllControllers.setWindowVar("userHome");
		MainAllControllers.changeWin();
	}
    /**
     * Mouse click event, if "logOut" button clicked, open the screen of "LogOut" and clean the fields
     * @param event
     * @throws IOException
     */
    @FXML
    void goLogoutPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
    	MainAllControllers.logOutUser();
	}
    /**
     * Mouse click event, if "Personal info" button clicked, open the screen of "Personal information"
     * @param event
     * @throws IOException
     */
    @FXML
    void goPersonalPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserPersonalInfo");
		MainAllControllers.changeWin();
	}
    /**
     * Mouse click event, if "Show requests" button clicked, open the screen of "Show requests"
     * @param event
     * @throws IOException
     */
    @FXML
    void goShowReqPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserShowRequests");
		MainAllControllers.changeWin();
	}
    
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

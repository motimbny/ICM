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
 * IT home screen Controller 
 * @author SHIRA
 */
public class ITHomeController implements Initializable
{	
	private MainAllControllers MainAllControllers;
    public ITHomeController()
    {
    	MainAllControllers=controllers.MainAllControllers.getInstance();
    }
    
	@FXML
	private Button homeBTN;

	@FXML
	private Button showRequestBTN;

	@FXML
	private Button personalInfoBTN;

	@FXML
	private Button helpBTN;

	@FXML
	private Button logoutBTN;

	@FXML
	private Label helloIT;

	@FXML
	private Label ExistingReq;

	 /**
     * Mouse click event, if "help" button clicked, open the screen of "help"
     * @param event
     * @throws IOException
     */

	@FXML
	void goHelpPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITHelp");
    	MainAllControllers.changeWin();
	}
	 /**
     * Mouse click event, if "Home" button clicked, open the screen of "Home"
     * @param event
     * @throws IOException
     */
	@FXML
	void goHomePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITHome");
    	MainAllControllers.changeWin();
	}
	 /**
     * Mouse click event, if "Personal info" button clicked, open the screen of "Personal information"
     * @param event
     * @throws IOException
     */
	@FXML
	void goPersonalInfo(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITPersonalInfo");
    	MainAllControllers.changeWin();
	}
	 /**
     * Mouse click event, if "Show requests" button clicked, open the screen of "Show requests"
     * @param event
     * @throws IOException
     */
	@FXML
	void goShowReq(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITshowRequests");
    	MainAllControllers.changeWin();
	}
	 /**
     * Mouse click event, if "logOut" button clicked, open the screen of "LogOut" and clean the fields
     * @param event
     * @throws IOException
     */
	@FXML
	void logoutPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.logOutUser();
	}
	/**
	 * This function set number of request in the screen
	 * @param num
	 */
    void setRequestNumber(int num)
    {
    	ExistingReq.setText("Existing requests: "+Integer.toString(num));
    }
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

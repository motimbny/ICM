package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
/**
 * User home screen Controller 
 * @author SHIRA
 */
public class ITHelpController {

	private MainAllControllers MainAllControllers;
    public ITHelpController()
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
}

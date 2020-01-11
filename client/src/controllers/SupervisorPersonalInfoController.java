package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
/**
 * Supervisor personal information screen controller
 * @author SHIRA
 *
 */
public class SupervisorPersonalInfoController implements Initializable 
{
	private MainAllControllers MainAllControllers;
	public SupervisorPersonalInfoController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}
	
	@FXML
	private Button homeBTN;

	@FXML
	private Button showreBTN;

	@FXML
	private Button personBTN;

	@FXML
	private Button helpBTN;

	@FXML
	private Button logoutBTN;

	@FXML
	private Button MessageBTN;
	
	@FXML
	private Label UserName;

	@FXML
	private Label email;

	@FXML
	private Label position;
	
	 /**
     * Mouse click event, if "Home" button clicked, open the screen of "Home"
     * @param event
     * @throws IOException
     */
	@FXML
	void backHome(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorHome");
    	MainAllControllers.changeWin();
	}
	/**
     * Mouse click event, if "help" button clicked, open the screen of "help"
     * @param event
     * @throws IOException
     */
	@FXML
	void helpBTNE(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorHelp");
    	MainAllControllers.changeWin();
	}
	 /**
     * Mouse click event, if "logOut" button clicked, open the screen of "LogOut" and clean the fields
     * @param event
     * @throws IOException
     */
	@FXML
	void logoutBTNE(MouseEvent event) throws IOException 
	{
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
	void personBTNE(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorPersonalInfo");
    	MainAllControllers.changeWin();
	}
	 /**
     * Mouse click event, if "Show requests" button clicked, open the screen of "Show requests"
     * @param event
     * @throws IOException
     */
	@FXML
	void showreBTNE(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorShowRequests");
    	MainAllControllers.changeWin();
	}
	 /**
     * Mouse click event, if "Messages" button clicked, open the screen of "Messages"
     * @param event
     * @throws IOException
     */
    @FXML
    void messagePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorMessages");
    	MainAllControllers.changeWin();
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		UserName.setText(MainAllControllers.user.getName());
		email.setText(MainAllControllers.user.getName() + "@braude.ac.il");
		position.setText(MainAllControllers.user.getstrPosition());	
	}
	
}

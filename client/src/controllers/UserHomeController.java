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
 * User home screen Controller 
 * @author SHIRA
 */
public class UserHomeController implements Initializable
{
    
    /**
     * Instantiates a new user home controller.
     */
    public UserHomeController()
    {
    	MainAllControllers=controllers.MainAllControllers.getInstance();
    }
    
    @FXML
    private Button homeBTN;

    @FXML
    private Button addreBTN;

    @FXML
    private Button showreBTN;

    @FXML
    private Button personBTN;

    @FXML
    private Button helpBTN;

    @FXML
    private Button logoutBTN;
    
    @FXML
    private Label helloUser;
    
    @FXML
    private Label reqExNum;
    
      
    private MainAllControllers MainAllControllers;
/**
 * Mouse click event, if "Add request" button clicked, open the screen of "Add new request"
 * @param event
 * @throws IOException
 */
    @FXML
    void addreBTNE(MouseEvent event) throws IOException
    {
    	MainAllControllers.setWindowVar("UserAddRequest");
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
    	MainAllControllers.setWindowVar("UserHelp");
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
    	MainAllControllers.setWindowVar("UserPersonalInfo");
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
    	MainAllControllers.setWindowVar("UserShowRequests");
    	MainAllControllers.changeWin();
    	
    }
    /**
     * Set requests number in the screen 
     * @param num
     */
    void setRequestNumber(int num)
    {
    	reqExNum.setText(Integer.toString(num));
    }
    
    /**
     * Initializes GUI components before this window open.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		helloUser.setText("Hello "+MainAllControllers.user.getName());
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.user.getName());
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.homeRequestNum, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}
	  

}

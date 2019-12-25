package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;



public class UserHomeController implements Initializable
{
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

    @FXML
    void addreBTNE(MouseEvent event) throws IOException
    {
    	MainAllControllers.setWindowVar("UserAddRequest");
    	MainAllControllers.changeWin();
          
    }

    @FXML
    void helpBTNE(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("UserHelp");
    	MainAllControllers.changeWin();
    }

    @FXML
    void logoutBTNE(MouseEvent event) throws IOException
    {
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
    }

    @FXML
    void personBTNE(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("UserPersonalInfo");
    	MainAllControllers.changeWin();
    	
    }

    @FXML
    void showreBTNE(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("UserShowRequests");
    	MainAllControllers.changeWin();
    	
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		helloUser.setText("Hello "+MainAllControllers.user.getName());
	}
	
	/*public void initialize(URL location, ResourceBundle resources)
	{
		reqExNum.setText();
		for number of requests
	}*/
	
 
    

}

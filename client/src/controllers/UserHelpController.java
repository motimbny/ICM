package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class UserHelpController 
{
	 private MainAllControllers MainAllControllers;
	 public UserHelpController()
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
    void addreBTNE(MouseEvent event) throws IOException
    {
    	MainAllControllers.setWindowVar("UserAddRequest");
    	MainAllControllers.changeWin();
          
    }

    @FXML
    void homeBack(MouseEvent event) throws IOException
    {
    	MainAllControllers.setWindowVar("userHome");
    	MainAllControllers.changeWin();
          
    }



    @FXML
    void helpBTNE(MouseEvent event) throws IOException 
    {
    	
    }

    @FXML
    void logoutBTNE(MouseEvent event) throws IOException
    {
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
    }

    @FXML
    void personBTNE(MouseEvent event)throws IOException 
    {
    	MainAllControllers.setWindowVar("UserPersonalInfo");
    	MainAllControllers.changeWin();
    	
    }

    @FXML
    void showreBTNE(MouseEvent event) {

    }

}

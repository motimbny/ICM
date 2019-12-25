package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class UserPersonalInfoController implements Initializable{
	 public UserPersonalInfoController()
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
    private Label UserName;

    @FXML
    private Label email;

    @FXML
    private Label position;

    @FXML
    void addreBTNE(MouseEvent event) throws IOException
    {
    	MainAllControllers.setWindowVar("UserAddRequest");
    	MainAllControllers.changeWin();
          
    }

    @FXML
    void helpBTNE(MouseEvent event)throws IOException 
    {
    	MainAllControllers.setWindowVar("UserHelp");
    	MainAllControllers.changeWin();
    }
    
    @FXML
    void backHome(MouseEvent event) throws IOException {

    	MainAllControllers.setWindowVar("userHome");
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

    private MainAllControllers MainAllControllers;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		UserName.setText("User name: "+MainAllControllers.user.getName());
		email.setText("email: "+MainAllControllers.user.getName()+"@braude.ac.il");
		position.setText("Position: "+MainAllControllers.user.getstrPosition());
		
	}

}

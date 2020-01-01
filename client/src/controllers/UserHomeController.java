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
    void setRequestNumber(int num)
    {
    	reqExNum.setText(Integer.toString(num));
    }
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

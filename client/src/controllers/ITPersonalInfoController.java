package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ITPersonalInfoController implements Initializable 
{
	private MainAllControllers MainAllControllers;
    public ITPersonalInfoController()
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
    private Label UserName;

    @FXML
    private Label email;

    @FXML
    private Label position;

	@FXML
	void goHelpPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITHelp");
    	MainAllControllers.changeWin();
	}

	@FXML
	void goHomePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITHome");
    	MainAllControllers.changeWin();
	}

	@FXML
	void goPersonalInfo(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITPersonalInfo");
    	MainAllControllers.changeWin();
	}

	@FXML
	void goShowReq(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITshowRequests");
    	MainAllControllers.changeWin();
	}

	@FXML
	void logoutPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.logOutUser();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		UserName.setText("User name: " + MainAllControllers.user.getName());
		email.setText("email: " + MainAllControllers.user.getName() + "@braude.ac.il");
		position.setText("Position: " + MainAllControllers.user.getstrPosition());
		
	}
}

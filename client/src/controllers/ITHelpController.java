package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

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
	void goShowReq(MouseEvent event) {

	}

	@FXML
	void logoutPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
	}
}

package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CCRequestMoreInfoController 
{
	private MainAllControllers MainAllControllers;
	public CCRequestMoreInfoController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}
	
	@FXML
	private Button home;

	@FXML
	private Button showRequests;

	@FXML
	private Button personalInfo;

	@FXML
	private Button help;

	@FXML
	private Button logOut;

	@FXML
	private TextArea requiredInformation;

	@FXML
	private Button sendCCRequireMoreInfo;

	@FXML
	private TextField requestID;

	@FXML
	private TextField date;

	@FXML
	void goHelpPage(MouseEvent event) {

	}

	@FXML
	void goHomePage(MouseEvent event) {

	}

	@FXML
	void goLogoutPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
	}

	@FXML
	void goPersonalPage(MouseEvent event) {

	}

	@FXML
	void goShowReqPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("CCShowRequests");
    	MainAllControllers.changeWin();
	}

	@FXML
	void submitRequireMoreInfo(MouseEvent event) {

	}
}

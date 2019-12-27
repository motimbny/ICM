package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class PerformanceLeaderHomeController implements Initializable
{
	private MainAllControllers MainAllControllers;
	public PerformanceLeaderHomeController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}

	@FXML
	private Button homeBTN;

	@FXML
	private Button showreBTN;

	@FXML
	private Button teamMemberBTN;

	@FXML
	private Button personBTN;

	@FXML
	private Button helpBTN;

	@FXML
	private Button logoutBTN;

	@FXML
	private Label helloPerformanceLeader;

	@FXML
	private Label reqExNum;

	@FXML
	void goHelpPage(MouseEvent event) {

	}

	@FXML
	void goHomePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("PerformanceLeaderHome");
    	MainAllControllers.changeWin();
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
    	MainAllControllers.setWindowVar("PerformanceLeaderShowRequests");
    	MainAllControllers.changeWin();
	}

	@FXML
	void goTeamMemberPage(MouseEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		helloPerformanceLeader.setText("Hello "+MainAllControllers.user.getName());	
	}

}

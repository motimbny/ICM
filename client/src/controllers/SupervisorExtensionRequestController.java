package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.extensionrequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SupervisorExtensionRequestController implements Initializable
{
	private MainAllControllers MainAllControllers;
	public SupervisorExtensionRequestController()
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
    private Button BACK;
    @FXML
    private Button APPROVE;

    @FXML
    private Button DENY;

    @FXML
    private TextField requestID;

    @FXML
    private TextField requestSTG;

    @FXML
    private TextField requestIT;

    @FXML
    private TextArea requestEXp;
    @FXML
    private Label answer;
    @FXML
    private TextField requestTime;
    @FXML
    void approveEX(MouseEvent event) 
    {
    	DBmessage dbm;
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		arry.add("approve");
    	dbm=new DBmessage(MessageType.superviserExtensionRequestAnswer, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
    @FXML
    void denyEXT(MouseEvent event) 
    {
    	DBmessage dbm;
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		arry.add("denied");
    	dbm=new DBmessage(MessageType.superviserExtensionRequestAnswer, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
    @FXML
    void backToShow(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("SupervisorShowRequests");
    	MainAllControllers.changeWin();
    }
    @FXML
	void goHelpPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorHelp");
    	MainAllControllers.changeWin();
	}

	@FXML
	void goHomePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorHome");
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
	void goPersonalPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorPersonalInfo");
    	MainAllControllers.changeWin();
	}

	@FXML
	void goShowReqPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorShowRequests");
    	MainAllControllers.changeWin();
	}
	public void setToFields(extensionrequest ev)
	{
		this.requestID.setText(Integer.toString(ev.getId()));
		this.requestSTG.setText(ev.getStage());
		this.requestIT.setText(ev.getItHandler());
		this.requestEXp.setText(ev.getReason());
		this.requestTime.setText(Integer.toString(ev.getTime()));
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		DBmessage dbm;
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.request);
    	dbm=new DBmessage(MessageType.superviserExtensionRequest, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}
	public void showAnswer()
	{
		answer.setVisible(true);	
	}

}

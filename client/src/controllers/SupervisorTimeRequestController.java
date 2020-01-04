package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.updateRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SupervisorTimeRequestController implements Initializable
{
	private MainAllControllers MainAllControllers;
	public SupervisorTimeRequestController()
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
    private Button requestInfoBTN;

    @FXML
    private Button saveUpdateReq;

    @FXML
    private TextField requestID;

    @FXML
    private TextField evluation;

    @FXML
    private TextField execution;

    @FXML
    private ComboBox<Integer> examination;

    @FXML
    private ComboBox<Integer> test;

    @FXML
    private Button BackToShow;

    @FXML
    void BackToS(MouseEvent event) throws IOException 
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

    @FXML
    void requestInfoWindow(MouseEvent event) throws IOException
    {
    	MainAllControllers.setWindowVar("SupervisorRequestDetalies");
    	MainAllControllers.changeWin();
    }
    public void setToFields(updateRequest ev)
	{
		this.requestID.setText(Integer.toString(ev.getId()));
		this.evluation.setText(Integer.toString(ev.getEvaluation()));
		this.examination.getSelectionModel().select(ev.getEvaluation()-1);;
		this.test.getSelectionModel().select((ev.getTest()-1));
		this.execution.setText(Integer.toString(ev.getExecution()));
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		examination.getItems().add(1);
		examination.getItems().add(2);
		examination.getItems().add(3);
		examination.getItems().add(4);
		examination.getItems().add(5);
		examination.getItems().add(6);
		examination.getItems().add(7);
		test.getItems().add(1);
		test.getItems().add(2);
		test.getItems().add(3);
		test.getItems().add(4);
		test.getItems().add(5);
		test.getItems().add(6);
		test.getItems().add(7);
		DBmessage dbm;
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.request);
    	dbm=new DBmessage(MessageType.SupervisorTimeRequest, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
		
	}

}

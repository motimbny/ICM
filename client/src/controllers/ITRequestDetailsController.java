package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.Request;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ITRequestDetailsController implements Initializable 
{
	private MainAllControllers MainAllControllers;
    public ITRequestDetailsController()
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
    private Label reqNumber;

    @FXML
    private TextField ApplicantNameField;

    @FXML
    private TextField InformationSystemField;

    @FXML
    private TextField requestStatusField;

    @FXML
    private TextArea DescriptionExistingSituationField;

    @FXML
    private TextArea DescriptionOfRequestField;

    @FXML
    private TextField RequestStageField;

    @FXML
    private Button BackToShow;

    @FXML
    void BackToS(MouseEvent event) throws IOException 
    {
		MainAllControllers.setWindowVar("ITHandleRequest");
		MainAllControllers.changeWin();
    }

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
	
    void setTextInFields(ArrayList<Object> listR)
    {
    	Request req=(Request)listR.get(0);		
    	ApplicantNameField.setText(req.getUserSubFullName());
    	InformationSystemField.setText(req.getInfoSystem());
    	requestStatusField.setText(req.getCurrentStatus());
    	RequestStageField.setText(req.getCurrentStage().toString());
    	DescriptionExistingSituationField.setText(req.getDesExtSit());
    	DescriptionOfRequestField.setText(req.getWantedChange());   	
    }
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{	
		ArrayList<Object> arry=new ArrayList<Object>();
		int s=MainAllControllers.request;
		arry.add(s);//request id
		reqNumber.setText("request number: "+s);
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.showRequestDetailsIT, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}

}

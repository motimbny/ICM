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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ITManagerRequestDetailsController implements Initializable  {
	private MainAllControllers MainAllControllers;
	public ITManagerRequestDetailsController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}
    @FXML
    private Button homeBTN;

    @FXML
    private Button showRequestBTN;

    @FXML
    private Button generateReportBTN1;

    @FXML
    private Button employeesMangBTN;

    @FXML
    private Button MessageBTN;

    @FXML
    private Button personalInfoBTN;

    @FXML
    private Button helpBTN;

    @FXML
    private Button logoutBTN;

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
    private Text requestIdField;

    @FXML
    void BackToS(MouseEvent event) throws IOException {
    	MainAllControllers.setWindowVar("ITManagerShowRequests");
    	MainAllControllers.changeWin();
	}


    @FXML
    void goEmployeesMang(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerEmployeesManagment");
    	MainAllControllers.changeWin();
	}

    @FXML
    void goHelpPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerHelp");
    	MainAllControllers.changeWin();
	}

    @FXML
    void goHomePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerHome");
    	MainAllControllers.changeWin();
	}


    @FXML
    void goPersonalInfo(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerPersonalInfo");
    	MainAllControllers.changeWin();
	}
    @FXML
    void goShowReq(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerShowRequests");
    	MainAllControllers.changeWin();
	}

    @FXML
    void gogenerateReport(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerReports");
    	MainAllControllers.changeWin();
	}

    @FXML
    void logoutPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.logOutUser();
	}

    @FXML
    void messagePage(MouseEvent event)throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerMessages");
    	MainAllControllers.changeWin();
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Object> arry=new ArrayList<Object>();
		int s=MainAllControllers.request;
		arry.add(s);//request id
		requestIdField.setText("request number: "+s);
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.showRequestDetailsITManager, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
		
	}

}

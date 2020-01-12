package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.Evluationreport;
import entity.Request;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ITCCCEvaluationReportController implements Initializable 
{
	private MainAllControllers MainAllControllers;
    public ITCCCEvaluationReportController()
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
    private TextField Location;

    @FXML
    private TextArea descriptionOfChangeRequired;

    @FXML
    private TextArea resultOfChange;

    @FXML
    private TextField timeEstimated;

    @FXML
    private TextArea constraintsAndRisks;

    @FXML
    private Button requireMoreInfoBTN;

    @FXML
    private Button DenyCCEvaluationReport;

    @FXML
    private Button ApproveCCEvaluationReport;

    @FXML
    private TextField requestID;
    
    @FXML
    private Button backBTN;

    @FXML
    void back(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("ITHandleRequest");
		MainAllControllers.changeWin();
    }

    @FXML
    void approveEvaluationReport(MouseEvent event) {
    	ArrayList<Object> arry=new ArrayList<Object>();
    	arry.add(MainAllControllers.request);//request id
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.approveEvaluationReport, arry);   
    	try {
			MainAllControllers.sendToAbsServer(dbm);
			Platform.runLater(new Runnable()
			{
			@Override
			public void run() 
			{
			try {
			MainAllControllers.setWindowVar("ITshowRequests");
			} catch (IOException e) {
				e.printStackTrace();
			}
			MainAllControllers.changeWin();
			}});
		} catch (IOException e) {
		}
    }

    @FXML
    void denyEvaluationReport(MouseEvent event) {
    	ArrayList<Object> arry=new ArrayList<Object>();
    	arry.add(MainAllControllers.request);//request id
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.denyEvaluationReport, arry);   
    	try {
			MainAllControllers.sendToAbsServer(dbm);
			Platform.runLater(new Runnable()
			{
			@Override
			public void run() 
			{
			try {
			MainAllControllers.setWindowVar("ITshowRequests");
			} catch (IOException e) {
				e.printStackTrace();
			}
			MainAllControllers.changeWin();
			}});
		} catch (IOException e) {
		}
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

    @FXML
    void requireMoreInfo(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("ITCCCRequestMoreInfo");
    	MainAllControllers.changeWin();
    }

    void setTextInFields(Evluationreport ev)
    {
    	requestID.setText(""+ev.getRequestID());
    	Location.setText(ev.getLocation());
    	timeEstimated.setText(""+ev.getTimeEstimated());
    	descriptionOfChangeRequired.setText(ev.getDescriptionOfChangeRequired());
    	resultOfChange.setText(ev.getResultOfChange());
    	constraintsAndRisks.setText(ev.getConstraintsAndRisks());
    	
    }
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{	
		ArrayList<Object> arry=new ArrayList<Object>();
		int s=MainAllControllers.request;
		arry.add(s);//request id
		String job=MainAllControllers.itHandlejob;
		requestID.setText(""+s);
		if(job.equals("CEOControlCommitte"))
		{
			requireMoreInfoBTN.setVisible(true);
			DenyCCEvaluationReport.setVisible(true);
			ApproveCCEvaluationReport.setVisible(true);
		}
		else
			backBTN.setVisible(true);
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.ITshowEvaluationReport, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}

}

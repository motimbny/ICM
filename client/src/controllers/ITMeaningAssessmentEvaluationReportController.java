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

public class ITMeaningAssessmentEvaluationReportController implements Initializable
{
	private MainAllControllers MainAllControllers;
    public ITMeaningAssessmentEvaluationReportController()
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
    private TextField requestID;

    @FXML
    private Button submitEvaluationReportBTN;
    
    @FXML
    private Label requestWasSubmitted;

    @FXML
    private Label fillAllFields;
    
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
    	MainAllControllers.user=null;
	}

    @FXML
    void submitEvaluationReport(MouseEvent event) throws IOException 
    {
    	if(timeEstimated.getText().equals("")||constraintsAndRisks.getText().equals("")||resultOfChange.getText().equals("")||descriptionOfChangeRequired.getText().equals(""))
    	{
    		fillAllFields.setVisible(true);
    	}
    	else
    	{
    		fillAllFields.setVisible(false);
    		ArrayList<Object> arry=new ArrayList<Object>();
    		arry.add(Integer.parseInt(requestID.getText()));
    		arry.add(Location.getText());
    		arry.add(timeEstimated.getText());
    		arry.add(descriptionOfChangeRequired.getText());
    		arry.add(resultOfChange.getText());
    		arry.add(constraintsAndRisks.getText());
    		DBmessage dbm;
			dbm = new DBmessage(MessageType.ITSubmitEvaluationReport, arry); 
			try {
				MainAllControllers.sendToAbsServer(dbm);
			} catch (IOException e) {
			}
    	}
    	goShowReq(event);
    }

    public void setOnSucsess()
    {	
    	requestWasSubmitted.setVisible(true);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{	
		ArrayList<Object> arry=new ArrayList<Object>();
		int s=MainAllControllers.request;
		arry.add(s); //request id
		requestID.setText(""+s);
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.ITgetLocation, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}
	
    void setTextInFields(ArrayList<Object> list)
    {
    	Location.setText((String)list.get(0));
    }

}

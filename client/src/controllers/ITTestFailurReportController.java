package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ITTestFailurReportController implements Initializable 
{

	private MainAllControllers MainAllControllers;
    public ITTestFailurReportController()
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
    private TextField ReqID;

    @FXML
    private Button submitFailurReportBTN;

    @FXML
    private TextArea ReportSummry;

    @FXML
    private Label fillAllFields;

    @FXML
    private Label reportWasSubmitted;

    @FXML
    private Button BackToShow;

    @FXML
    private TextField date;
    
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

    @FXML
    void submitFailurReport(MouseEvent event) 
    {
    	if(ReportSummry.getText().equals(""))
    	{
    		fillAllFields.setVisible(true);
    	}
    	else
    	{
    		fillAllFields.setVisible(false);
        	ArrayList<Object> arr=new ArrayList<Object>();
        	arr.add(ReqID.getText());
        	arr.add(date.getText());
        	arr.add(ReportSummry.getText());
        	DBmessage dbm=new DBmessage(MessageType.ITFailurReport, arr);
        	try {
				MainAllControllers.sendToAbsServer(dbm);
			} catch (IOException e) {}
    	}
    }
    
    public void setOnSucsess()
    {
    	reportWasSubmitted.setVisible(true);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{	
		ArrayList<Object> arry=new ArrayList<Object>();
		int s=MainAllControllers.request;
		arry.add(s);//request id
		ReqID.setText(""+s);
		LocalDate myObj = LocalDate.now();
		date.setText("" + myObj);
	}
}

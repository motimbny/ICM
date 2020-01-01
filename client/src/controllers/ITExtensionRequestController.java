package controllers;

import java.io.IOException;
import java.net.URL;
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

public class ITExtensionRequestController implements Initializable 
{
	private MainAllControllers MainAllControllers;
    public ITExtensionRequestController()
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
    private TextField RequestID;

    @FXML
    private TextField ReqStage;

    @FXML
    private TextField itHandler;

    @FXML
    private TextArea Reason;

    @FXML
    private Button submitExtensionRequestBTN;

    @FXML
    private Button BackToShow;

    @FXML
    private TextField timeToAdd;
    
    @FXML
    private Label requestWasSubmitted;

    @FXML
    private Label fillAllFields;

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
    void submitExtensionRequest(MouseEvent event) 
    {
    	if(Reason.getText().equals("")||timeToAdd.getText().equals(""))
    	{
    		fillAllFields.setVisible(true);
    	}
    	else
    	{
    		ArrayList<Object> arry=new ArrayList<Object>();
	        arry.add(RequestID.getText());
	        arry.add(ReqStage.getText());
	        arry.add(itHandler.getText()); 
	        arry.add(timeToAdd.getText());
    		arry.add(Reason.getText());
	       
	    	DBmessage dbm=new DBmessage(MessageType.AddExtensionRequest, arry);
	    	try
	    	{
				MainAllControllers.sendToAbsServer(dbm);
			} 
	    	catch (IOException e) 
	    	{
				e.printStackTrace();
			} 
    		requestWasSubmitted.setVisible(true);
    	}
    	

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.user.getName());
		arry.add(MainAllControllers.request); //request id
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.ITgetReqStage, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
		
	}
	
	void setTextInFields(ArrayList<Object> list)
	{
		System.out.println((String)list.get(0));
		RequestID.setText(""+MainAllControllers.request);
		itHandler.setText(MainAllControllers.user.getName());
		ReqStage.setText((String)list.get(0));
	}

}

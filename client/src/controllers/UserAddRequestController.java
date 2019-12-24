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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;


public class UserAddRequestController implements Initializable
{
	private MainAllControllers MainAllControllers;
	public UserAddRequestController()
	{
    	MainAllControllers=MainAllControllers.getInstance();
	}
	
    @FXML
    private Button homeBTN;
    @FXML
    private Button addreBTN;
    @FXML
    private Button showreBTN;
    @FXML
    private Button personBTN;
    @FXML
    private Button helpBTN;
    @FXML
    private Button logoutBTN;
    @FXML
    private Button submitRequestUserAddReq;
    @FXML
    private TextArea desExtSit;
    @FXML
    private ChoiceBox<String> chooseInfoCom;
    @FXML
    private TextArea desReqCha;
    @FXML
    private TextArea expChaBen;
    @FXML
    private TextArea MorInfoAdd;
    @FXML
    private Button addAttachmentsUserAddReq;
    @FXML
    private Label badAddRequest;
    @FXML
    private Label goodAddRe;
    @FXML
    void submitRequest(MouseEvent event) 
    {
    	if(desExtSit.getText().equals("")||desReqCha.getText().equals("")||expChaBen.getText().equals(""))
    	{
    		badAddRequest.setVisible(true);
    	}
    	else
    	{
    		badAddRequest.setVisible(false);
	    	ArrayList<Object> arry=new ArrayList();
	        arry.add(chooseInfoCom.getValue());
	        arry.add(desReqCha.getText());
	        arry.add(desExtSit.getText());
	        arry.add(expChaBen.getText());
	        arry.add(MorInfoAdd.getText());
	        arry.add(MainAllControllers.user.getName());
	        arry.add(MainAllControllers.user.getPosition());
	    	DBmessage dbm=new DBmessage(MessageType.AddRequest, arry);
	    	try
	    	{
				MainAllControllers.mcABS.sendToServer(dbm);
			} 
	    	catch (IOException e) 
	    	{
				e.printStackTrace();
			}  
    	}
    }
    @FXML
    void setfields(MouseEvent event)
    {

    }
    public void setOnSucsess()
    {
		goodAddRe.setVisible(true);
    }
    @FXML
    void backToHome(MouseEvent event) 
    {
        	MainAllControllers.setWindowVar("userHome");
        	MainAllControllers.changeWin();
    }
    @FXML
    void backToRequest(MouseEvent event)
    {
    	//mainallcontrollers.setWindowVar("userrequests");
    	//mainallcontrollers.setWindow();
    }
    @FXML
    void backTohelp(MouseEvent event)
    {
    	//mainallcontrollers.setWindowVar("help");
    	//mainallcontrollers.setWindow();
    }
    @FXML
    void backToinfo(MouseEvent event)
    {
    	//mainallcontrollers.setWindowVar("userinfo");
    	//mainallcontrollers.setWindow();
    }
    @FXML
    void backTorAddequest(MouseEvent event) 
    {
    	//mainallcontrollers.setWindowVar("useraddrequest");
    	//mainallcontrollers.setWindow();
    }
    @FXML
    void logut(MouseEvent event)
    {
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		chooseInfoCom.getItems().add("Moodle");
    	chooseInfoCom.getItems().add("Info System");
    	chooseInfoCom.getItems().add("Libary");
    	chooseInfoCom.getItems().add("Computers");
		
	}
}

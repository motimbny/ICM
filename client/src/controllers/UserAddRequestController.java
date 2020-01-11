package controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import Enums.Position;
import entity.DBmessage;
import entity.ServerFile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * User  add request screen Controller 
 * @author SHIRA
 */

public class UserAddRequestController implements Initializable
{
	private ServerFile fileOfUser=null;
	private MainAllControllers MainAllControllers;
	private boolean flag=false;
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
    private Label fileName;
    
    
    @FXML
    void submitRequest(MouseEvent event) 
    {
    	if(desExtSit.getText().equals("")||desReqCha.getText().equals("")||expChaBen.getText().equals("")||chooseInfoCom.getSelectionModel().isEmpty())
    	{
    		badAddRequest.setVisible(true);
    	}
    	else
    	{
    		badAddRequest.setVisible(false);
	    	ArrayList<Object> arry=new ArrayList<Object>();
	        arry.add(chooseInfoCom.getValue());
	        arry.add(desReqCha.getText());
	        arry.add(desExtSit.getText());
	        arry.add(expChaBen.getText());
	        arry.add(MorInfoAdd.getText());
	        arry.add(MainAllControllers.user.getName());
	        arry.add(MainAllControllers.user.getstrPosition());
	        if(flag==true)
	           arry.add(fileOfUser);
	    	DBmessage dbm=new DBmessage(MessageType.AddRequest, arry);
	    	try
	     	{
				MainAllControllers.sendToAbsServer(dbm);
			} 
	    	catch (IOException e) 
	    	{
				e.printStackTrace();
			}  
    	}
    }
    public void setOnSucsess()
    {
		goodAddRe.setVisible(true);
    }
    @FXML
    private void attachFile(MouseEvent event)
    {
    	FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        fileName.setText("+"+file.getName());
        fileName.setVisible(true);
        fileOfUser= new ServerFile(MainAllControllers.user.getName()+"-"+file.getName());
  	    String LocalfilePath=file.getPath();

        try{ 	
  	    	 File newFile=new File(LocalfilePath);
  		      byte [] mybytearray  = new byte [(int)newFile.length()];
  		      FileInputStream fis = new FileInputStream(newFile);
  		      BufferedInputStream bis = new BufferedInputStream(fis);			     
  		      fileOfUser.initArray(mybytearray.length);
  		      fileOfUser.setSize(mybytearray.length); 
  		      bis.read(fileOfUser.getMybytearray(),0,mybytearray.length); 	
  		      flag=true;
  		    }
  		catch (Exception e) {
  			System.out.println("Error send File to Server");
  		}
    }
    /**
     * Mouse click event, if "Home" button clicked, open the screen of "Home"
     * @param event
     * @throws IOException
     */
    @FXML
    void backToHome(MouseEvent event) throws IOException 
    {
        	MainAllControllers.setWindowVar("userHome");
        	MainAllControllers.changeWin();
    }
    /**
	 * Mouse click event, if "Add request" button clicked, open the screen of "Add new request"
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void backToRequest(MouseEvent event) throws IOException
    {
    	MainAllControllers.setWindowVar("UserAddRequest");
    	MainAllControllers.setWindow();
    }
    /**
     * Mouse click event, if "help" button clicked, open the screen of "help"
     * @param event
     * @throws IOException
     */
    @FXML
    void backTohelp(MouseEvent event) throws IOException
    {
    	MainAllControllers.setWindowVar("UserHelp");
    	MainAllControllers.setWindow();
    }
    /**
     * Mouse click event, if "Personal info" button clicked, open the screen of "Personal information"
     * @param event
     * @throws IOException
     */
    @FXML
    void backToinfo(MouseEvent event) throws IOException
    {
    	MainAllControllers.setWindowVar("UserPersonalInfo");
    	MainAllControllers.setWindow();
    }
    /**
     * Mouse click event, if "Show requests" button clicked, open the screen of "Show requests"
     * @param event
     * @throws IOException
     */
    @FXML
    void backTorAddequest(MouseEvent event) throws IOException 
    {
    	MainAllControllers.setWindowVar("UserShowRequests");
    	MainAllControllers.setWindow();
    }
    /**
     * Mouse click event, if "logOut" button clicked, open the screen of "LogOut" and clean the fields
     * @param event
     * @throws IOException
     */
    @FXML
    void logut(MouseEvent event) throws IOException
    {
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.logOutUser();
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

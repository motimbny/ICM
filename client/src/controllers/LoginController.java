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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


/**
 * Login screen controller.
 * The first window GUI after enter to system.
 *
 * @author SHIRA
 */
public class LoginController implements Initializable
{
	
	/**
	 * Instantiates a new login controller.
	 */
	public LoginController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}
	
	/** The userflag. */
	private boolean userflag=false;
    
    /** The login main screen. */
    @FXML
    private Button loginMainScreen;
    
    /** The help. */
    @FXML
    private ImageView help;
    
    /** The click forgt pass. */
    @FXML
    private Button clickForgtPass;
    
    /** The User name field. */
    @FXML
    private TextField UserNameField;
    
    /** The pass F. */
    @FXML
    private PasswordField passF;
    
    /** The baduserpass. */
    @FXML
    private Label baduserpass;
    
    /** The wrong lbl. */
    @FXML
    private Label wrongLbl;
    
    /** The connect pane. */
    @FXML
    private Pane connectPane;
    
    /** The port TO. */
    @FXML
    private TextField portTO;
    
    /** The host TO. */
    @FXML
    private TextField hostTO;
    
    /** The logedallready. */
    @FXML
    private Label logedallready;
    
    /** The sign pane. */
    @FXML
    private Pane signPane;
    
    /** The Main all controllers. */
    private MainAllControllers MainAllControllers;
    
    /**
     * This method create a DBmsg to send to server with the fields that entered
     *
     * @param event Login BTN
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void Login(MouseEvent event) throws IOException 
    {
		wrongLbl.setVisible(false);
    	ArrayList<Object> arry=new ArrayList();
    	arry.add(UserNameField.getText());
    	arry.add(passF.getText());
    	DBmessage dbm;
    	dbm=new DBmessage(MessageType.Login, arry);
    	MainAllControllers.sendToAbsServer(dbm);
    }
    
    /**
     * This method connect To host port.
     *
     * @param event The connect BTN
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void connectTOhoPo(MouseEvent event) throws IOException 
    {
		MainAllControllers.port=Integer.parseInt(portTO.getText());
		MainAllControllers.host=hostTO.getText();
		MainAllControllers.setMainAbs(); 
		connectPane.setVisible(false);
		signPane.setVisible(true);
    }
    
    /**
     * Forgotpass.
     *
     * @param event the event
     */
    @FXML
    void forgotpass(MouseEvent event)
    {
         
    }
    
    /**
     * This method sets the label visible .
     */
    public void setVisibleLbl()
    {
		wrongLbl.setVisible(true);
    }
	
	/**
	 * Initializes GUI components before this window open.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		if(MainAllControllers.getisConnect())
		{
			connectPane.setVisible(false);
			signPane.setVisible(true);
		}
	}
	
	/**
	 * This method sets the label visible .
	 */
	public void setVisibleLblCon() 
	{
		logedallready.setVisible(true);
	}
}

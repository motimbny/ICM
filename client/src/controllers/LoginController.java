package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
 * Login screen 
 * @author SHIRA
 *
 */
public class LoginController implements Initializable
{
	public LoginController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}
	private boolean userflag=false;
    @FXML
    private Button loginMainScreen;
    @FXML
    private ImageView help;
    @FXML
    private Button clickForgtPass;
    @FXML
    private TextField UserNameField;
    @FXML
    private PasswordField passF;
    @FXML
    private Label baduserpass;
    @FXML
    private Label wrongLbl;
    @FXML
    private Pane connectPane;
    @FXML
    private TextField portTO;
    @FXML
    private TextField hostTO;
    @FXML
    private Label logedallready;
    @FXML
    private Pane signPane;
    
    private MainAllControllers MainAllControllers;
    
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
    @FXML
    void connectTOhoPo(MouseEvent event) throws IOException 
    {
		MainAllControllers.port=Integer.parseInt(portTO.getText());
		MainAllControllers.host=hostTO.getText();
		MainAllControllers.setMainAbs(); 
		connectPane.setVisible(false);
		signPane.setVisible(true);
    }
    @FXML
    void forgotpass(MouseEvent event)
    {
         
    }
    public void setVisibleLbl()
    {
		wrongLbl.setVisible(true);
    }
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		if(MainAllControllers.getisConnect())
		{
			connectPane.setVisible(false);
			signPane.setVisible(true);
		}
	}
	public void setVisibleLblCon() 
	{
		logedallready.setVisible(true);
	}
}

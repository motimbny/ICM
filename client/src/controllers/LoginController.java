package controllers;

import java.io.IOException;
import java.util.ArrayList;

import Enums.MessageType;
import entity.DBmessage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class LoginController
{
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
    private MainAllControllers MainAllControllers;
    @FXML
    void Login(MouseEvent event) throws IOException 
    {
		wrongLbl.setVisible(false);
		MainAllControllers=controllers.MainAllControllers.getInstance();
		MainAllControllers.setMainAbs();
    	ArrayList<Object> arry=new ArrayList();
    	arry.add(UserNameField.getText());
    	arry.add(passF.getText());
    	DBmessage dbm;
    	dbm=new DBmessage(MessageType.Login, arry);
    	MainAllControllers.sendToAbsServer(dbm);
    }
    @FXML
    void forgotpass(MouseEvent event)
    {
         
    }
    public void setVisibleLbl()
    {
		wrongLbl.setVisible(true);
    }
}

package controllers;

import java.util.ArrayList;

import Enums.MessageType;
import entity.DBmessage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class LoginController
{
	private boolean userflag=false;
	public void loginController()
	{
		//wrongLbl.setVisible(false);
		//loginMainScreen.setDisable(true);
		/*UserNameField.textProperty().addListener(new ChangeListener<String>()
		{
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) 
		    {
               if(!oldValue.equals(newValue))
            	   userflag=true;   
		    }
		});
		PasswordField.textProperty().addListener(new ChangeListener<String>()
		{
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) 
		    {
               if(!oldValue.equals(newValue)&& userflag==true)
           		 loginMainScreen.setDisable(false);
		    }
		});*/
	}
    @FXML
    private Button loginMainScreen;
    @FXML
    private ImageView help;
    @FXML
    private Button clickForgtPass;
    @FXML
    private TextField UserNameField;
    @FXML
    private TextField PasswordField;
    @FXML
    private Label baduserpass;
    @FXML
    private Label wrongLbl;
    @FXML
    void Login(MouseEvent event) 
    {
    	ArrayList<Object> arry=new ArrayList();
    	arry.add(UserNameField.getText());
    	arry.add(PasswordField.getText());
    	DBmessage dbm=new DBmessage(MessageType.Login, arry);
    	//mainallcontrollers.mcABS.sendtoserver(dbm);        //this line will send DBmessage to server
    }
    @FXML
    void forgotpass(MouseEvent event)
    {
         
    }
    public void setVisibleLbl(boolean condition)
    {
		wrongLbl.setVisible(condition);
    }
}

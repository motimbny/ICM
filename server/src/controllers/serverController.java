package controllers;

import boundries.mainServerABS;
import entity.ConnectToDB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class serverController 
{
    @FXML
    private Button connect;
    @FXML
    private Button disconnect;
    @FXML
    private TextArea messagebox;
    @FXML
    private TextField dbuser;
    @FXML
    private TextField dbscheme;
    @FXML
    private TextField port;
    @FXML
    private PasswordField dbpassword;
    private ConnectToDB connecti;
    private mainServerABS mainServerABS;
    @FXML
    void connect(MouseEvent event)
    {
    	 messagebox.clear();
    	if(dbpassword.getText().equals("")||dbuser.getText().equals("")|| dbscheme.getText().equals("")||port.getText().equals(""))
    	{
    		messagebox.setText("ERROR!!! Please provide all fields before connecting");
    	}
    	else
    	{
    	  mainServerABS=new mainServerABS(Integer.parseInt(port.getText()),this); 
		  connecti=new ConnectToDB(dbpassword.getText(),dbuser.getText(), dbscheme.getText());
		  mainServerABS.connectToDb(connecti.Connect());
 	      mainServerABS.startServer();
    	}
    }
    @FXML
    void disconnect(MouseEvent event)
    {
    	mainServerABS.stopServer();
    }
    public void showOnScreen(String str)
    {
    	messagebox.setText(str);
    }

}

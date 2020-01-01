package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import boundries.mainServer;
import boundries.mainServerABS;
import entity.ConnectToDB;
import entity.DBSmessage;
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
    	mainServer.NUM_OF_REQUEST=getNumOfRequest();
    }
    private int getNumOfRequest()
    {
    	Statement stmt;
		DBSmessage dbs;
		ResultSet rs = null;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connecti.Connect().createStatement();
			rs = stmt.executeQuery("SELECT COUNT(*) FROM request");
			rs.next();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		try
		{
			return rs.getInt(1);
		} 
		catch (SQLException e)
		{
		   System.out.println("Cant count number of request");
		}
		return 0;
	}
	@FXML
    void disconnect(MouseEvent event)
    {
    	mainServerABS.stopServer();
    }
    public  void showOnScreen(String str)
    {
    	messagebox.setText(str);
    }

}

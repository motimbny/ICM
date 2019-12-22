package controllers;

import boundries.mainServerABS;
import entity.ConnectToDB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private TextField port;
    private ConnectToDB connecti;
    private mainServerABS mainServerABS;
    @FXML
    void connect(MouseEvent event)
    {
    	mainServerABS=new mainServerABS(Integer.parseInt(port.getText()),this); 
		connecti=new ConnectToDB("Aa123456","root", "project");
		mainServerABS.connectToDb(connecti.Connect());
 	    mainServerABS.startServer();
    }
    @FXML
    void disconnect(MouseEvent event)
    {
    	mainServerABS.stopListening();
    	
    }
    public void showOnScreen(String str)
    {
    	messagebox.setText(str);
    }

}

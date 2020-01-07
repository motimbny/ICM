package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.Messages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class SupervisorMessagesController  //implements Initializable
{

	private MainAllControllers MainAllControllers;
	String sentby;
	String subject;
	private ObservableList<Messages> rows;
	public SupervisorMessagesController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}
    @FXML
    private Button homeBTN;

    @FXML
    private Button showreBTN;

    @FXML
    private Button MessageBTN;

    @FXML
    private Button personBTN;

    @FXML
    private Button helpBTN;

    @FXML
    private Button logoutBTN;

    @FXML
    private TableView<Messages> MessageTable;

    @FXML
    private TableColumn<Messages, String> SentBy;

    @FXML
    private TableColumn<Messages, String> messSubject;

    @FXML
    private TableColumn<Messages, String> MessageContent;

    @FXML
    private TableColumn<Messages, String> Date;

    @FXML
    void UpdateR(MouseEvent event) {
    	MessageTable.setOnMouseClicked((MouseEvent ev ) -> 
    	{
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
            	sentby=MessageTable.getItems().get(MessageTable.getSelectionModel().getSelectedIndex()).getSentBy();
            	subject=MessageTable.getItems().get(MessageTable.getSelectionModel().getSelectedIndex()).getSubject();                
            DBmessage dbm;
    		String user;
    		user=MainAllControllers.user.getName();
    		ArrayList<Object> arry=new ArrayList<Object>();
            arry.add(user);
            arry.add(SentBy);
            arry.add(messSubject);
        	dbm=new DBmessage(MessageType.MangerUpdateMessages, arry);   
        	try {
        		MainAllControllers.sendToAbsServer(dbm);
    		} catch (IOException e) {}
            }
        });
    }

    @FXML
    void goHelpPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorHelp");
    	MainAllControllers.changeWin();
	}

    @FXML
    void goHomePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorHome");
    	MainAllControllers.changeWin();
	}

    @FXML
    void goLogoutPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
	}

    @FXML
    void goPersonalPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorPersonalInfo");
    	MainAllControllers.changeWin();
	}


    @FXML
    void goShowReqPage(MouseEvent event)throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorShowRequests");
    	MainAllControllers.changeWin();
	}

    @FXML
    void messagePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorMessages");
    	MainAllControllers.changeWin();
	}

	/*@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
   		SentBy.setCellValueFactory(new PropertyValueFactory<>("sentBy"));
		messSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
		MessageContent.setCellValueFactory(new PropertyValueFactory<>("messageCon"));
		Date.setCellValueFactory(new PropertyValueFactory<>("date"));
		MessageTable.setRowFactory(tv -> new TableRow<Messages>() 
		{
			@Override
            public void updateItem(Messages item, boolean empty) 
			{
                super.updateItem(item, empty);
                if (item == null) 
                {
                    setStyle("");
                } 
                else if (item.getRead()==0)
                {
                	setStyle("-fx-font-weight: bold;");
                } 
                else if (item.getRead()==1)
                {
                	setStyle("");
                } 
            }
		});
		requestServer();	
	}
	public void requestServer()
    {
		DBmessage dbm;
		String user;
		user=MainAllControllers.user.getName();
		ArrayList<Object> arry=new ArrayList<Object>();
        arry.add(user);
    	dbm=new DBmessage(MessageType.MangerShowMessages, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
	 public void setTextTable(ArrayList<Object> list)
	 {
		 Messages toad;
		 rows= FXCollections.observableArrayList();
	    	for(Object r:list)
	    	{
	    		    rows.add((Messages)r);
	    	}
	    	MessageTable.setItems(rows);
	 }

	*/	
	}



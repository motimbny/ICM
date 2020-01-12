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

/**
 * Supervisor messages screen controller.
 *
 * @author SHIRA
 */
public class SupervisorMessagesController  implements Initializable
{
	
	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;
	
	/** The sentby. */
	String sentby;
	
	/** The subject. */
	String subject;
	
	/** The rows. */
	private ObservableList<Messages> rows;
	
	/**
	 * Instantiates a new supervisor messages controller.
	 */
	public SupervisorMessagesController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}
    
    /** The home BTN. */
    @FXML
    private Button homeBTN;

    /** The showre BTN. */
    @FXML
    private Button showreBTN;

    /** The Message BTN. */
    @FXML
    private Button MessageBTN;

    /** The person BTN. */
    @FXML
    private Button personBTN;

    /** The help BTN. */
    @FXML
    private Button helpBTN;

    /** The logout BTN. */
    @FXML
    private Button logoutBTN;

    /** The Message table. */
    @FXML
    private TableView<Messages> MessageTable;

    /** The Sent by. */
    @FXML
    private TableColumn<Messages, String> SentBy;

    /** The mess subject. */
    @FXML
    private TableColumn<Messages, String> messSubject;

    /** The Message content. */
    @FXML
    private TableColumn<Messages, String> MessageContent;

    /** The Date. */
    @FXML
    private TableColumn<Messages, String> Date;

    /**
     * Update R.
     *
     * @param event the event
     */
    @FXML
    void UpdateR(MouseEvent event) {
		MessageTable.setOnMouseClicked((MouseEvent ev ) -> 
    	{
            if (ev.getButton().equals(MouseButton.PRIMARY) && ev.getClickCount() == 2)
            {
                sentby=MessageTable.getItems().get(MessageTable.getSelectionModel().getSelectedIndex()).getSentBy();
				subject=MessageTable.getItems().get(MessageTable.getSelectionModel().getSelectedIndex()).getSubject();                
	            DBmessage dbm;
	    		String user;
	    		user=MainAllControllers.user.getName();
	    		ArrayList<Object> arry=new ArrayList<Object>();
	            arry.add(user);
	            arry.add(sentby);
	            arry.add(subject);
	        	dbm=new DBmessage(MessageType.SuperviserUpdateMessages, arry);   
	        	try
	        	{
	        		MainAllControllers.sendToAbsServer(dbm);
	    		} 
	        	catch (IOException e) {}
	        }
        });
    }
    
    /**
     * Mouse click event, if "help" button clicked, open the screen of "help".
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goHelpPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorHelp");
    	MainAllControllers.changeWin();
	}
    
    /**
     * Mouse click event, if "Home" button clicked, open the screen of "Home".
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goHomePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorHome");
    	MainAllControllers.changeWin();
	}
    
    /**
     * Mouse click event, if "logOut" button clicked, open the screen of "LogOut" and clean the fields.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goLogoutPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.logOutUser();
	}
    
    /**
     * Mouse click event, if "Personal info" button clicked, open the screen of "Personal information".
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goPersonalPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorPersonalInfo");
    	MainAllControllers.changeWin();
	}

    /**
     * Mouse click event, if "Show requests" button clicked, open the screen of "Show requests".
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goShowReqPage(MouseEvent event)throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorShowRequests");
    	MainAllControllers.changeWin();
	}
    
    /**
     * Mouse click event, if "Messages" button clicked, open the screen of "Messages".
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void messagePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorMessages");
    	MainAllControllers.changeWin();
	}

	/**
	 * Initialize.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
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
	
	/**
	 * Request server.
	 */
	public void requestServer()
    {
		DBmessage dbm;
		String user;
		user=MainAllControllers.user.getName();
		ArrayList<Object> arry=new ArrayList<Object>();
        arry.add(user);
    	dbm=new DBmessage(MessageType.SuperviserShowMessages, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
	 
 	/**
 	 * Sets the text table.
 	 *
 	 * @param list the new text table
 	 */
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
}



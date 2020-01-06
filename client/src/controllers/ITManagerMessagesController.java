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
import javafx.scene.input.MouseEvent;

public class ITManagerMessagesController implements Initializable
{
	private ObservableList<Messages> rows;
	private MainAllControllers MainAllControllers;
	public ITManagerMessagesController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}
    @FXML
    private Button homeBTN;

    @FXML
    private Button showRequestBTN;

    @FXML
    private Button generateReportBTN1;

    @FXML
    private Button employeesMangBTN;

    @FXML
    private Button MessageBTN;

    @FXML
    private Button personalInfoBTN;

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
    void goEmployeesMang(MouseEvent event)  throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerEmployeesManagment");
    	MainAllControllers.changeWin();
	}

    @FXML
    void goHelpPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerHelp");
    	MainAllControllers.changeWin();
	}

    @FXML
    void goHomePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerHome");
    	MainAllControllers.changeWin();
	}

    @FXML
    void goPersonalInfo(MouseEvent event)  throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerPersonalInfo");
    	MainAllControllers.changeWin();
	}

    @FXML
    void goShowReq(MouseEvent event)  throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerShowRequests");
    	MainAllControllers.changeWin();
	}

    @FXML
    void gogenerateReport(MouseEvent event) {

    }

    @FXML
    void logoutPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
	}

    @FXML
    void messagePage(MouseEvent event)throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerMessages");
    	MainAllControllers.changeWin();
	}

    @FXML
    void showMessage(MouseEvent event) {

    }
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

}

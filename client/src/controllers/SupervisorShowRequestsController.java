package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.requestSuper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SupervisorShowRequestsController implements Initializable
{
	private ObservableList<requestSuper> rows;
	private MainAllControllers MainAllControllers;
	public SupervisorShowRequestsController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}
	
	@FXML
	private Button homeBTN;

	@FXML
	private Button showreBTN;

	@FXML
	private Button personBTN;

	@FXML
	private Button helpBTN;

	@FXML
	private Button logoutBTN;

	@FXML
	private TableView<requestSuper> requestTable;

	@FXML
	private TableColumn<requestSuper, Integer> RequestID;

	@FXML
	private TableColumn<requestSuper, String> RequestStatus;

	@FXML
	private TableColumn<requestSuper, String> RequestProcessStage;

	@FXML
	private Button SuprvisorUpdateRequestBTN;

	@FXML
	private Button SuprvisorCloseRequestBTN;

	@FXML
	private Button SuprvisorSuspendRequestBTN;

	@FXML
	private Button SuprvisorExtensionRequestBTN;

    @FXML
    private Button search;

    @FXML
    private TextField requestIdTo;
	
	@FXML
	void UpdateRequest(MouseEvent event) 
	{

	}
	@FXML
	void closeRequest(MouseEvent event) 
	{
		Stage popupwindow=new Stage();   
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Alert pop up");      
		Label label1= new Label("Are you sure you want to close request?");    
		Button button1= new Button("Yes");  
		Button button2= new Button("No"); 
		button1.setOnAction(e -> popupwindow.close());
		button2.setOnAction(e -> popupwindow.close());
		button1.setStyle("-fx-border-color:green");
		button2.setStyle("-fx-border-color:red");
		VBox layout= new VBox(10);     
		layout.getStylesheets().add("CSS/it.css");
		layout.getChildren().addAll(label1, button1,button2);     
		layout.setAlignment(Pos.CENTER);     
		Scene scene1= new Scene(layout, 250, 200);     
		popupwindow.setScene(scene1);     
		popupwindow.showAndWait();
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
	void goShowReqPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorShowRequests");
    	MainAllControllers.changeWin();
	}

	@FXML
	void suspendRequest(MouseEvent event) 
	{

	}

	@FXML
	void viewExtensionReport(MouseEvent event) 
	{

	}
	

    @FXML
    void showRequestDetails(MouseEvent event) 
    {

    }
    
    @FXML
    void searchRequest(MouseEvent event) 
    {
    	if(requestIdTo.getText().equals(""))
    	{
    		try 
    		{
				MainAllControllers.setWindowVar("SupervisorShowRequests");
			} catch (IOException e)
    		{
				e.printStackTrace();
			}
        	MainAllControllers.changeWin();
    	}
    	else
    	{
	    	ArrayList<Object> arry=new ArrayList<Object>();
			arry.add(Integer.parseInt(requestIdTo.getText()));
			DBmessage dbm;
	    	dbm=new DBmessage(MessageType.SearchReqSupervisor, arry);   
	    	try {
	    		MainAllControllers.sendToAbsServer(dbm);
			} catch (IOException e) {}
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		RequestID.setCellValueFactory(new PropertyValueFactory<>("id"));
		RequestStatus.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
		RequestProcessStage.setCellValueFactory(new PropertyValueFactory<>("currentStage"));
		requestServer();
		
	}
	public void requestServer()
    {
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.superviserRequestShow, null);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
	public void clearTable()
	{
		requestTable.getItems().clear();
	}
	
	 public void setTextInTable(ArrayList<Object> list)
	 {
		 rows= FXCollections.observableArrayList();
	    	for(Object r:list)
	    		rows.add((requestSuper)r);		
	    	requestTable.setItems(rows);	
	 }

}

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
import javafx.event.ActionEvent;
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
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ITManagerShowRequestsController implements Initializable
{	
	private ObservableList<requestSuper> rows;
	private MainAllControllers MainAllControllers;
	public ITManagerShowRequestsController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}

    @FXML
    private Button homeBTN;

    @FXML
    private Button showRequestBTN1;

    @FXML
    private Button generateReportBTN1;

    @FXML
    private Button employeesMangBTN;

    @FXML
    private Button logoutBTN1;

    @FXML
    private Button personalInfoBTN;

    @FXML
    private Button messageBTN;
    
    @FXML
    private Button helpBTN;

    @FXML
    private Button logoutBTN;

    @FXML
    private TableView<requestSuper> requestTable;

    @FXML
    private TableColumn<requestSuper, Integer> RequestID;

    @FXML
    private TableColumn<requestSuper, Integer> RequestStatus;

    @FXML
    private TableColumn<requestSuper, Integer> RequestProcessStage;

    @FXML
    private Button RenewRequestRequestBTN;

    @FXML
    private Button search;

    @FXML
    private TextField requestIdTo;
    @FXML
    private Label selectR;

    @FXML
    private Label okR;
    @FXML
    void goEmployeesMang(MouseEvent event)throws IOException 
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
    void goHomePage(MouseEvent event)throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerHome");
    	MainAllControllers.changeWin();
	}

    @FXML
    void goPersonalInfo(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerPersonalInfo");
    	MainAllControllers.changeWin();
	}

    @FXML
    void goShowReq(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerShowRequests");
    	MainAllControllers.changeWin();
	}
    @FXML
    void messagePage(MouseEvent event)	throws IOException 
    	{
        	MainAllControllers.setWindowVar("ITManagerMessages");
        	MainAllControllers.changeWin();
    	}

   
    @FXML
    void gogenerateReport(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerReports");
    	MainAllControllers.changeWin();
	}
    @FXML
    void logoutPage(MouseEvent event)throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
	}

    @FXML
    void renewRequest(MouseEvent event)
    {
    	Stage popupwindow=new Stage();   
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Alert pop up");      
		Label label1= new Label("Are you sure you want to renew the request?"); 
		label1.setFont(new Font("Arial", 14));
		Button button1= new Button("Yes");  
		Button button2= new Button("No"); 
		button1.addEventHandler(ActionEvent.ACTION, (e)->popupwindow.close());
    	button1.addEventHandler(ActionEvent.ACTION, (e)->{
    		if(requestTable.getSelectionModel().getSelectedIndex()==-1)
    			selectR.setVisible(true);
    		else if(requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getCurrentStatus().contentEquals("Suspend"))
    		{
    			
    			this.renewRequest(requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getId());
    		}
    		else
    			selectR.setVisible(true);
    	    });
		button2.setOnAction(e -> popupwindow.close());
		button1.setStyle("-fx-border-color:green");
		button2.setStyle("-fx-border-color:red");
		VBox layout= new VBox(10);     
		layout.getStylesheets().add("CSS/it.css");
		layout.getChildren().addAll(label1, button1,button2);     
		layout.setAlignment(Pos.CENTER);     
		Scene scene1= new Scene(layout, 350, 250);     
		popupwindow.setScene(scene1);     
		popupwindow.showAndWait();	
    	
    }
    private void renewRequest(int id) 
    {
    	ArrayList<Object> arry=new ArrayList<Object>();
		int idadd=id;
		DBmessage dbm;
		arry.add(idadd);
    	dbm=new DBmessage(MessageType.renewRequest, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}
    @FXML
    void visable(MouseEvent event)
    {
    	selectR.setVisible(false);
    }
	@FXML
    void searchRequest(MouseEvent event) 
    {
    		if (requestIdTo.getText().equals("")) 
    		{
    			try {
    				MainAllControllers.setWindowVar("ITManagerShowRequests");
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			MainAllControllers.changeWin();
    		} else 
    		{
    			ArrayList<Object> arry = new ArrayList<Object>();
    			arry.add(Integer.parseInt(requestIdTo.getText()));
    			DBmessage dbm;
    			dbm = new DBmessage(MessageType.SearchReqManager, arry); 
    			try {
    				MainAllControllers.sendToAbsServer(dbm);
    			} catch (IOException e) {
    			}
    		}
    }

    @FXML
    void showRequestDetails(MouseEvent event)
    {

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
    	dbm=new DBmessage(MessageType.MangerRequestShow, null);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
	public void clearTable()
	{
		requestTable.getItems().clear();
	}
	 public void setTextTable(ArrayList<Object> list)
	 {
		 rows= FXCollections.observableArrayList();
	    	for(Object r:list)
	    		rows.add((requestSuper)r);		
	    	requestTable.setItems(rows);	
	 }
}


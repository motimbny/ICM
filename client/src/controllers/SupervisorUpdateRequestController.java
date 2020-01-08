package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.extensionrequest;
import entity.updateRequest;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SupervisorUpdateRequestController implements Initializable
{
	private MainAllControllers MainAllControllers;
	private ArrayList<Object> list;
	public SupervisorUpdateRequestController()
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
    private Button MessageBTN;
    @FXML
    private Button logoutBTN;
    @FXML
    private Button requestInfoBTN;
    @FXML
    private Button saveUpdateReq;
    @FXML
    private TextField requestID;
    @FXML
    private Button changeApprieserBTN;
    @FXML
    private TextField apprieserName;
    @FXML
    private Button changeExecuterBTN;
    @FXML
    private TextField executerName;
  

    @FXML
    private Button approveApprieserBTN;
    @FXML
    private TextField evluation;
    @FXML
    private TextField execution;
    @FXML
    private ComboBox<Integer> examination;
    @FXML
    private ComboBox<Integer> test;
    @FXML
    private Button BackToShow;
	    @FXML
	    void BackToS(MouseEvent event) throws IOException 
		{
	    	MainAllControllers.setWindowVar("SupervisorShowRequests");
	    	MainAllControllers.changeWin();
		}
	    @FXML
	    void requestInfoWindow(MouseEvent event) throws IOException
	    {
	    	MainAllControllers.setWindowVar("SupervisorRequestDetalies");
	    	MainAllControllers.changeWin();
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
    	MainAllControllers.logOutUser();
 	}

 	@FXML
 	void goPersonalPage(MouseEvent event) throws IOException 
 	{
     	MainAllControllers.setWindowVar("SupervisorPersonalInfo");
     	MainAllControllers.changeWin();
 	}
 	@FXML
    void messagePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorMessages");
    	MainAllControllers.changeWin();
	}
    @FXML
	void goShowReqPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("SupervisorShowRequests");
    	MainAllControllers.changeWin();
	}

    @FXML
    void changeApprieser(MouseEvent event) {
    	listOfIt();
    	showListOfIT("itAppraiser");
    }

  
    
    @FXML
    private void changeExecuter(MouseEvent event) {
    	listOfIt();
    	showListOfIT("itPerformanceLeader");
    	
    }
    private void showListOfIT(String NameOfPositionChange)
    {
    	Stage popupwindow=new Stage();   
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("List of IT");      
		Label label1= new Label("Please choose "+NameOfPositionChange+":"); 
		label1.setFont(new Font("Arial", 16));
		TableView<String> EmployeesTable=new TableView<>();
		TableColumn<String, String> employeeName = new TableColumn<>();
		EmployeesTable.getColumns().addAll(employeeName);
		Collection<String> rows = new ArrayList<>();
		employeeName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		while(list==null) {}
	    for(Object r:list)
	    	rows.add(r.toString());
        ObservableList<String> details = FXCollections.observableArrayList(rows);
	    EmployeesTable.setItems(details);
	    EmployeesTable.setOnMouseClicked((MouseEvent ev ) ->
	    	{
	    		switch (NameOfPositionChange) {
				case "itAppraiser":
				{
					apprieserName.setText(EmployeesTable.getSelectionModel().getSelectedItem());
				}
				break;
			
				case "itPerformanceLeader":
				{
					executerName.setText(EmployeesTable.getSelectionModel().getSelectedItem());
				}
				break;

				default:
					break;
				}
	            //this.changeExecuter(EmployeesTable.getSelectionModel().getSelectedItem(),NameOfPositionChange);
	            popupwindow.close(); 
	        });
		VBox layout= new VBox(10);     
		layout.getStylesheets().add("CSS/it.css");
		layout.getChildren().addAll(label1,EmployeesTable);
		layout.setAlignment(Pos.CENTER); 
		Scene scene1= new Scene(layout, 350, 250);     
		popupwindow.setScene(scene1);     
		popupwindow.showAndWait();
    }
    private void changeExecuter() 
    {
    	int id=(Integer.parseInt(requestID.getText()));
    	ArrayList<Object> arry=new ArrayList<Object>();
		DBmessage dbm;
		arry.add(id);
		arry.add(executerName.getText());
		arry.add(apprieserName.getText());
    	dbm=new DBmessage(MessageType.changeExecuter, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}
	public void setToFields(updateRequest ev)
	{
		this.requestID.setText(Integer.toString(ev.getId()));
		this.apprieserName.setText(ev.getApprieser());
		this.executerName.setText(ev.getExecuter());
		this.evluation.setText(Integer.toString(ev.getEvaluation()));
		this.examination.getSelectionModel().select(ev.getEvaluation()-1);;
		this.test.getSelectionModel().select((ev.getTest()-1));
		this.execution.setText(Integer.toString(ev.getExecution()));
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		DBmessage dbm;
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.request);
    	dbm=new DBmessage(MessageType.SupervisorUpdateRequest, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}
	public void listOfIt()
	{
		DBmessage dbm;
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.request);
    	dbm=new DBmessage(MessageType.SupervisorListIt, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}
	public void setListOfIT(ArrayList<Object> arrayList)
	{
		this.list=arrayList;
	}
	

    @FXML
    void SaveChanges(MouseEvent event) throws IOException {
    	changeExecuter();
    	goShowReqPage(event);
    
    	

    }
}

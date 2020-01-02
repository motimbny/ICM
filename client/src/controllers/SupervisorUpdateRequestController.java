package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.evluationReport;
import entity.updateRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SupervisorUpdateRequestController implements Initializable{

	private MainAllControllers MainAllControllers;
	private ArrayList<Object> list;
	public SupervisorUpdateRequestController()
	{
		System.out.println("3");
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
    private Button changeTesterBTN;

    @FXML
    private TextField testerName;

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
    void changeApprieser(MouseEvent event) {
    	showListOfIT("itAppraiser");
    }

    @FXML
    void changeTester(MouseEvent event) {
    	showListOfIT("itTester");

    }
    
    @FXML
    private void changeExecuter(MouseEvent event) {
    	showListOfIT("itPerformanceLeader");
    	
    }
    private void showListOfIT(String NameOfPositionChange)
    {
    	Stage popupwindow=new Stage();   
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("List of IT");      
		Label label1= new Label("Please choose your choice:"); 
		label1.setFont(new Font("Arial", 14));
		Button[] button= new Button[list.size()]; 
		int i=0;
		for(Object name:list)
		{
			int num=i+1;
			button[i]=new Button(num+". "+name.toString());
			button[i].addEventHandler(ActionEvent.ACTION, (e)->popupwindow.close());
			button[i].addEventHandler(ActionEvent.ACTION, (e)->{
    			
    			this.changeExecuter((String)name,NameOfPositionChange);
    	
    	    });
			i++;
		}

		VBox layout= new VBox(10);     
		layout.getStylesheets().add("CSS/it.css");
		layout.getChildren().add(label1);
		for(Button button1:button)
			layout.getChildren().add(button1);
		layout.setAlignment(Pos.CENTER);     
		Scene scene1= new Scene(layout, 350, 250);     
		popupwindow.setScene(scene1);     
		popupwindow.showAndWait();
    }
    private void changeExecuter(String name,String Position) {
    	String change=executerName.getText();
    	int id=(Integer.parseInt(requestID.getText()));

    	ArrayList<Object> arry=new ArrayList<Object>();
		DBmessage dbm;
		arry.add(id);
		arry.add(name);
		arry.add(Position);

    	dbm=new DBmessage(MessageType.changeExecuter, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
		
	}

    


	public void setToFields(updateRequest ev)
	{
		System.out.println("im here again "+ev.getId());
		this.requestID.setText(Integer.toString(ev.getId()));
		this.apprieserName.setText(ev.getApprieser());
		this.executerName.setText(ev.getExecuter());
		this.testerName.setText(ev.getTester());
		this.evluation.setText(Integer.toString(ev.getEvaluation()));
		this.examination.getSelectionModel().select(ev.getEvaluation()-1);;
		this.test.getSelectionModel().select((ev.getTest()-1));
		this.execution.setText(Integer.toString(ev.getExecution()));
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		examination.getItems().add(1);
		examination.getItems().add(2);
		examination.getItems().add(3);
		examination.getItems().add(4);
		examination.getItems().add(5);
		examination.getItems().add(6);
		examination.getItems().add(7);
		test.getItems().add(1);
		test.getItems().add(2);
		test.getItems().add(3);
		test.getItems().add(4);
		test.getItems().add(5);
		test.getItems().add(6);
		test.getItems().add(7);
		DBmessage dbm;
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(MainAllControllers.request);
    	dbm=new DBmessage(MessageType.SupervisorUpdateRequest, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
	}

	public void setListOfIT(ArrayList<Object> arrayList) {
		this.list=arrayList;
	}
}

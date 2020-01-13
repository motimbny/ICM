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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The Class ITManagerShowRequestsController.
 */
public class ITManagerShowRequestsController implements Initializable
{	
	
	/** The rows. */
	private ObservableList<requestSuper> rows;
	
	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;
	
	/**
	 * Instantiates a new IT manager show requests controller.
	 */
	public ITManagerShowRequestsController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}

    /** The home BTN. */
    @FXML
    private Button homeBTN;

    /** The show request BTN 1. */
    @FXML
    private Button showRequestBTN1;

    /** The generate report BTN 1. */
    @FXML
    private Button generateReportBTN1;

    /** The employees mang BTN. */
    @FXML
    private Button employeesMangBTN;

    /** The logout BTN 1. */
    @FXML
    private Button logoutBTN1;

    /** The personal info BTN. */
    @FXML
    private Button personalInfoBTN;

    /** The message BTN. */
    @FXML
    private Button messageBTN;
    
    /** The help BTN. */
    @FXML
    private Button helpBTN;

    /** The logout BTN. */
    @FXML
    private Button logoutBTN;

    /** The request table. */
    @FXML
    private TableView<requestSuper> requestTable;

    /** The Request ID. */
    @FXML
    private TableColumn<requestSuper, Integer> RequestID;

    /** The Request status. */
    @FXML
    private TableColumn<requestSuper, Integer> RequestStatus;

    /** The Request process stage. */
    @FXML
    private TableColumn<requestSuper, Integer> RequestProcessStage;

    /** The Renew request request BTN. */
    @FXML
    private Button RenewRequestRequestBTN;

    /** The search. */
    @FXML
    private Button search;

    /** The request id to. */
    @FXML
    private TextField requestIdTo;
    
    /** The select R. */
    @FXML
    private Label selectR;

    /** The ok R. */
    @FXML
    private Label okR;
    
    /**
     * Go employees management.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goEmployeesMang(MouseEvent event)throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerEmployeesManagment");
    	MainAllControllers.changeWin();
	}

    /**
     * Go help page.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goHelpPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerHelp");
    	MainAllControllers.changeWin();
	
    }

    /**
     * Go home page.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goHomePage(MouseEvent event)throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerHome");
    	MainAllControllers.changeWin();
	}

    /**
     * Go personal info.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goPersonalInfo(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerPersonalInfo");
    	MainAllControllers.changeWin();
	}

    /**
     * Go show request.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goShowReq(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerShowRequests");
    	MainAllControllers.changeWin();
	}
    
    /**
     * go Message page.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void messagePage(MouseEvent event)	throws IOException 
    	{
        	MainAllControllers.setWindowVar("ITManagerMessages");
        	MainAllControllers.changeWin();
    	}

   
    /**
     * Go generate report.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void gogenerateReport(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerReports");
    	MainAllControllers.changeWin();
	}
    
    /**
     * go Logout page.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void logoutPage(MouseEvent event)throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.logOutUser();
	}

    /**
     * Renew request.
     *
     * @param event the event
     */
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
    
    /**
     * Renew request.
     *
     * @param id the id
     */
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
    
    /**
     * Visable.
     *
     * @param event the event
     */
    @FXML
    void visable(MouseEvent event)
    {
    	selectR.setVisible(false);
    }
	
	/**
	 * Search request.
	 *
	 * @param event the event
	 */
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

    /**
     * Show request details.
     *
     * @param event the event
     */
    @FXML
    void showRequestDetails(MouseEvent event)
    {
    	requestTable.setOnMouseClicked((MouseEvent ev ) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                try {
                	MainAllControllers.request=requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getId();
					MainAllControllers.setWindowVar("ITManagerRequestDetails");
					MainAllControllers.changeWin();
				} catch (IOException e) {
					e.printStackTrace();
				}                
            }
        });
    }

	/**
	 * Initialize.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		RequestID.setCellValueFactory(new PropertyValueFactory<>("id"));
		RequestStatus.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
		RequestProcessStage.setCellValueFactory(new PropertyValueFactory<>("currentStage"));
		requestServer();
		
	}
	
	/**
	 * Request server.
	 */
	public void requestServer()
    {
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.MangerRequestShow, null);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
	
	/**
	 * Clear table.
	 */
	public void clearTable()
	{
		requestTable.getItems().clear();
	}
	 
 	/**
 	 * Sets the text table.
 	 *
 	 * @param list the new text table
 	 */
 	public void setTextTable(ArrayList<Object> list)
	 {
		 rows= FXCollections.observableArrayList();
	    	for(Object r:list)
	    		rows.add((requestSuper)r);		
	    	requestTable.setItems(rows);	
	 }
}


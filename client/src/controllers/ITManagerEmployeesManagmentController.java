package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.ITemployee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


/**
 * IT manager employees management screen Controller .
 *
 * @author SHIRA
 */
public class ITManagerEmployeesManagmentController implements Initializable 
{
	
	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;
	
	/** The rows. */
	private ObservableList<ITemployee> rows; 
	
	/**
	 * Instantiates a new IT manager employees managment controller.
	 */
	public ITManagerEmployeesManagmentController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}
	
	/** The home BTN. */
	@FXML
    private Button homeBTN;

    /** The show request BTN. */
    @FXML
    private Button showRequestBTN;

    /** The generate report BTN 1. */
    @FXML
    private Button generateReportBTN1;

    /** The employees mang BTN. */
    @FXML
    private Button employeesMangBTN;

    /** The Message BTN. */
    @FXML
    private Button MessageBTN;

    /** The personal info BTN. */
    @FXML
    private Button personalInfoBTN;

    /** The help BTN. */
    @FXML
    private Button helpBTN;

    /** The logout BTN. */
    @FXML
    private Button logoutBTN;
    
    /** The change pos. */
    @FXML
    private Button changePos;
    
    /** The combo. */
    @FXML
    private ChoiceBox<String> combo;

    /** The Employees table. */
    @FXML
    private TableView<ITemployee> EmployeesTable;

    /** The employee id. */
    @FXML
    private TableColumn<ITemployee, Integer> employeeId;

    /** The employee name. */
    @FXML
    private TableColumn<ITemployee, String> employeeName;

    /** The employee last name. */
    @FXML
    private TableColumn<ITemployee, String> employeeLastName;

    /** The employee mail. */
    @FXML
    private TableColumn<ITemployee, String> employeeMail;

    /** The employee pos. */
    @FXML
    private TableColumn<ITemployee, String> employeePos;
    
    /** The submit chnage. */
    @FXML
    private Label submitChnage;
    
    /**
     * Go employees mang.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goEmployeesMang(MouseEvent event) throws IOException 
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
    void goHomePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerHome");
    	MainAllControllers.changeWin();
	}

    /**
     * Logout page.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void logoutPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.logOutUser();
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
     * Go show req.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void goShowReq(MouseEvent event)  throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerShowRequests");
    	MainAllControllers.changeWin();
	}
    
    /**
     * Request server.
     */
    public void requestServer()
    {
		DBmessage dbm;
    	dbm=new DBmessage(MessageType.ShowEmployeeList, null);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
    
    /**
     * Sets the text in table.
     *
     * @param list the new text in table
     */
    public void setTextInTable(ArrayList<Object> list)
	{
		rows= FXCollections.observableArrayList();
    	for(Object r:list)
    		rows.add((ITemployee)r);		
    	EmployeesTable.setItems(rows);		
	}
    
    /**
     * Gogenerate report.
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
     * Message page.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void messagePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITManagerMessages");
    	MainAllControllers.changeWin();
	}
    
    /**
     * Changep.
     *
     * @param event the event
     */
    @FXML
    void changep(MouseEvent event) 
    {
    	ArrayList<Object> arry=new ArrayList<Object>();
		DBmessage dbm;
		String nameIT=EmployeesTable.getItems().get(EmployeesTable.getSelectionModel().getSelectedIndex()).getEmployeeName();
		String pos=combo.getValue();
		arry.add(nameIT);
		arry.add(pos);
    	dbm=new DBmessage(MessageType.SwitchPositions, arry);   
    	try {
    		MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {}
    }
    
     /**
      * Sets the visable.
      */
     public void setVisable()
     {
    	 submitChnage.setVisible(true);
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
		combo.getItems().add("superviser");
		combo.getItems().add("CEO");
		combo.getItems().add("CC");
		employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
		employeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
		employeeLastName.setCellValueFactory(new PropertyValueFactory<>("employeeLastName"));
		employeeMail.setCellValueFactory(new PropertyValueFactory<>("employeeMail"));
		employeePos.setCellValueFactory(new PropertyValueFactory<>("employeePos"));
		requestServer();
		
	}

}

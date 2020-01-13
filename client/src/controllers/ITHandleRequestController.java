package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import Enums.MessageType;
import Enums.Position;
import entity.DBmessage;
import entity.Request;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * The Class ITHandleRequestController.
 */
public class ITHandleRequestController implements Initializable {
	
	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;
	
	/** The list. */
	private ArrayList<Object> list;

	/**
	 * Instantiates a new IT handle request controller.
	 */
	public ITHandleRequestController() {
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	/** The home BTN. */
	@FXML
	private Button homeBTN;

	/** The show request BTN. */
	@FXML
	private Button showRequestBTN;

	/** The personal info BTN. */
	@FXML
	private Button personalInfoBTN;

	/** The help BTN. */
	@FXML
	private Button helpBTN;

	/** The logout BTN. */
	@FXML
	private Button logoutBTN;

	/** The request id field. */
	@FXML
	private Text requestIdField;

	/** The Show request details BTN. */
	@FXML
	private Button ShowRequestDetailsBTN;

	/** The Req for time extension BTN. */
	@FXML
	private Button ReqForTimeExtensionBTN;

	/** The Test stage. */
	@FXML
	private Pane TestStage;

	/** The Test approval BTN. */
	@FXML
	private Button TestApprovalBTN;

	/** The Report failure BTN. */
	@FXML
	private Button ReportFailureBTN;

	/** The Evaluation stage. */
	@FXML
	private Pane EvaluationStage;

	/** The Submit time estimate BTN. */
	@FXML
	private Button SubmitTimeEstimateBTN;

	/** The Create evaluation report BTN. */
	@FXML
	private Button CreateEvaluationReportBTN;

	/** The time estimated evaluation. */
	@FXML
	private TextField timeEstimatedEvaluation;

	/** The Performance stage. */
	@FXML
	private Pane PerformanceStage;

	/** The change completed BTN. */
	@FXML
	private Button changeCompletedBTN;

	/** The Submit time estimate performance BTN. */
	@FXML
	private Button SubmitTimeEstimatePerformanceBTN;

	/** The time estimated performance. */
	@FXML
	private TextField timeEstimatedPerformance;

	/** The examination and decision stage. */
	@FXML
	private Pane examinationAndDecisionStage;

	/** The View evaluation report BTN. */
	@FXML
	private Button ViewEvaluationReportBTN;

	/** The Appoint tester BTN. */
	@FXML
	private Button AppointTesterBTN;

	/** The successful. */
	@FXML
	private Label successful;

	/** The l 1. */
	@FXML
	private Label l1;

	/** The l 2. */
	@FXML
	private Label l2;
	
    /** The l 3. */
    @FXML
    private Label l3;

    /** The l 4. */
    @FXML
    private Label l4;
    
    /** The positon req. */
    @FXML
    private Text positonReq;
	
	/**
	 * Appoint tester.
	 *
	 * @param event the event
	 */
	@FXML
	void AppointTester(MouseEvent event) {
		listOfIt();
		showListOfIT("itTester");
	}

	/**
	 * Show list of IT.
	 *
	 * @param NameOfPositionChange the name of position change
	 */
	private void showListOfIT(String NameOfPositionChange) {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("List of IT");
		Label label1 = new Label("Please choose " + NameOfPositionChange + ":");
		label1.setFont(new Font("Arial", 16));
		TableView<String> EmployeesTable = new TableView<>();
		TableColumn<String, String> employeeName = new TableColumn<>();
		EmployeesTable.getColumns().addAll(employeeName);
		Collection<String> rows = new ArrayList<>();
		employeeName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		while (list == null) {}
		for (Object r : list)
			rows.add(r.toString());
		ObservableList<String> details = FXCollections.observableArrayList(rows);
		EmployeesTable.setItems(details);
		EmployeesTable.setOnMouseClicked((MouseEvent ev) -> {
			DBmessage dbm;
			ArrayList<Object> arry = new ArrayList<Object>();
			arry.add(MainAllControllers.request);
			arry.add(EmployeesTable.getSelectionModel().getSelectedItem());
			dbm = new DBmessage(MessageType.ITSaveTester, arry);
			try {
				MainAllControllers.sendToAbsServer(dbm);
			} catch (IOException e) {
			}
			popupwindow.close();
		});
		VBox layout = new VBox(10);
		layout.getStylesheets().add("CSS/it.css");
		layout.getChildren().addAll(label1, EmployeesTable);
		layout.setAlignment(Pos.CENTER);
		Scene scene1 = new Scene(layout, 350, 250);
		popupwindow.setScene(scene1);
		popupwindow.showAndWait();
	}

	/**
	 * List of it.
	 */
	public void listOfIt() {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		dbm = new DBmessage(MessageType.ITShowEmployeeList, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * Sets the list of IT.
	 *
	 * @param arrayList the new list of IT
	 */
	public void setListOfIT(ArrayList<Object> arrayList) {
		this.list = arrayList;
	}

	/**
	 * Creates the evaluation report.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void CreateEvaluationReport(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITMeaningAssessmentEvaluationReport");
		MainAllControllers.changeWin();
	}

	/**
	 * Report failure.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void ReportFailure(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITTestFailurReport");
		MainAllControllers.changeWin();
	}

	/**
	 * Req for time extension.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void ReqForTimeExtension(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITExtensionRequest");
		MainAllControllers.changeWin();
	}

	/**
	 * Show request details.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void ShowRequestDetails(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITRequestDetails");
		MainAllControllers.changeWin();
	}

	/**
	 * Submit time estimate evaluation.
	 *
	 * @param event the event
	 */
	@FXML
	void SubmitTimeEstimateEvaluation(MouseEvent event) {
		String time = timeEstimatedEvaluation.getText();
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		arry.add(Integer.parseInt(timeEstimatedEvaluation.getText()));
		DBmessage dbm;
		dbm = new DBmessage(MessageType.ITaddTimeEstimated, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * Submit time estimate performance.
	 *
	 * @param event the event
	 */
	@FXML
	void SubmitTimeEstimatePerformance(MouseEvent event) {
		String time = timeEstimatedPerformance.getText();
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		arry.add(Integer.parseInt(timeEstimatedPerformance.getText()));
		DBmessage dbm;
		dbm = new DBmessage(MessageType.addTimeEstimatedPerformance, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * Test approval.
	 *
	 * @param event the event
	 */
	@FXML
	void TestApproval(MouseEvent event) {
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		DBmessage dbm;
		dbm = new DBmessage(MessageType.ITTestApproval, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
			Platform.runLater(new Runnable()
			{
			@Override
			public void run() 
			{
			try {
				MainAllControllers.setWindowVar("ITshowRequests");
			} catch (IOException e) {
				e.printStackTrace();
			}
			MainAllControllers.changeWin();
			}});
		} catch (IOException e) {
		}
	}

	/**
	 * View evaluation report.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void ViewEvaluationReport(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITCCCEvaluationReport");
		MainAllControllers.changeWin();
	}

	/**
	 * Change completed.
	 *
	 * @param event the event
	 */
	@FXML
	void changeCompleted(MouseEvent event) {
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		DBmessage dbm;
		dbm = new DBmessage(MessageType.ITchangeCompleted, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}
	
	public void showRequests() throws IOException
	{
		MainAllControllers.setWindowVar("ITshowRequests");
		MainAllControllers.changeWin();
	}

	/**
	 * Go help page.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goHelpPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITHelp");
		MainAllControllers.changeWin();
	}

	/**
	 * Go home page.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goHomePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITHome");
		MainAllControllers.changeWin();
	}

	/**
	 * Go personal info.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goPersonalInfo(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITPersonalInfo");
		MainAllControllers.changeWin();
	}

	/**
	 * Go show req.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goShowReq(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITshowRequests");
		MainAllControllers.changeWin();
	}

	/**
	 * Logout page.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void logoutPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
		MainAllControllers.logOutUser();
	}

	/**
	 * Initialize.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		int s = MainAllControllers.request;
		requestIdField.setText("Request ID: " + s);
		positonReq.setText("Position: "+MainAllControllers.itHandlejob);
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.user.getName()); // it name
		arry.add(s); // request id
		DBmessage dbm;
		dbm = new DBmessage(MessageType.ITjobInReq, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * Sets the pane.
	 *
	 * @param list the new pane
	 */
	public void setPane(ArrayList<Object> list) {
		String job = (String) list.get(0);
		MainAllControllers.itHandlejob = job;
		switch (job) {
		case "Appraiser":
		{	EvaluationStage.setVisible(true);
			if (list.get(1).equals("waitingEvaluationTime")) {
				CreateEvaluationReportBTN.setVisible(false);
				SubmitTimeEstimateBTN.setVisible(true);
				timeEstimatedEvaluation.setVisible(true);
				l1.setVisible(true);
				l2.setVisible(true);
			}
			else if (list.get(1).equals("meaningAssessment")) {
				CreateEvaluationReportBTN.setVisible(true);
				SubmitTimeEstimateBTN.setVisible(false);
				timeEstimatedEvaluation.setVisible(false);
				l1.setVisible(false);
				l2.setVisible(false);
			} else {
				ReqForTimeExtensionBTN.setVisible(false);
				CreateEvaluationReportBTN.setVisible(false);
				SubmitTimeEstimateBTN.setVisible(false);
				timeEstimatedEvaluation.setVisible(false);
				l1.setVisible(false);
				l2.setVisible(false);
			}
		}
			break;
		case "ControlCommitte":
		{	examinationAndDecisionStage.setVisible(true);
			if (list.get(1).equals("examinationAndDecision"))
				ViewEvaluationReportBTN.setVisible(true);
			else {
				ViewEvaluationReportBTN.setVisible(false);
				ReqForTimeExtensionBTN.setVisible(false);
			}
		}
			break;
		case "CEOControlCommitte":
		{	examinationAndDecisionStage.setVisible(true);
			if (list.get(1).equals("examinationAndDecision")) {
				ViewEvaluationReportBTN.setVisible(true);
				AppointTesterBTN.setVisible(true);
			} else {
				AppointTesterBTN.setVisible(false);
				ViewEvaluationReportBTN.setVisible(false);
				ReqForTimeExtensionBTN.setVisible(false);
			}
		}
			break;
		case "PerformanceLeader":
		{
			PerformanceStage.setVisible(true);
			if (list.get(1).equals("waitingExecutionTime"))
			{
				changeCompletedBTN.setVisible(false);
				timeEstimatedPerformance.setVisible(true);
				SubmitTimeEstimatePerformanceBTN.setVisible(true);
				l3.setVisible(true);
				l4.setVisible(true);
			}
			else if (list.get(1).equals("execution"))
			{
				changeCompletedBTN.setVisible(true);
				timeEstimatedPerformance.setVisible(false);
				SubmitTimeEstimatePerformanceBTN.setVisible(false);
				l3.setVisible(false);
				l4.setVisible(false);
			}
			else
			{
				ReqForTimeExtensionBTN.setVisible(false);
				changeCompletedBTN.setVisible(false);
				timeEstimatedPerformance.setVisible(false);
				SubmitTimeEstimatePerformanceBTN.setVisible(false);
				l3.setVisible(false);
				l4.setVisible(false);
			}
			
		}
			break;
		case "Tester":
			TestStage.setVisible(true);
			if (list.get(1).equals("testing"))
			{
				ReportFailureBTN.setVisible(true);
				TestApprovalBTN.setVisible(true);
			}
			else
			{
				ReportFailureBTN.setVisible(false);
				TestApprovalBTN.setVisible(false);
				ReqForTimeExtensionBTN.setVisible(false);
			}
			break;
			
		}
		this.checkDaysLeft();
	}
	
	public void checkDaysLeft()
	{
		int s = MainAllControllers.request;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(s); // request id
		DBmessage dbm;
		dbm = new DBmessage(MessageType.ITrequestDaysLeft, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}
	
	public void visableExtensionBTN(boolean con)
	{
		ReqForTimeExtensionBTN.setVisible(con);
	}

	/**
	 * Sets the on success.
	 */
	public void setOnSucsess() {
		successful.setVisible(true);
	}

}

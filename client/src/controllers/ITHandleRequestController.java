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

public class ITHandleRequestController implements Initializable {
	private MainAllControllers MainAllControllers;
	private ArrayList<Object> list;

	public ITHandleRequestController() {
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	@FXML
	private Button homeBTN;

	@FXML
	private Button showRequestBTN;

	@FXML
	private Button personalInfoBTN;

	@FXML
	private Button helpBTN;

	@FXML
	private Button logoutBTN;

	@FXML
	private Text requestIdField;

	@FXML
	private Button ShowRequestDetailsBTN;

	@FXML
	private Button ReqForTimeExtensionBTN;

	@FXML
	private Pane TestStage;

	@FXML
	private Button TestApprovalBTN;

	@FXML
	private Button ReportFailureBTN;

	@FXML
	private Pane EvaluationStage;

	@FXML
	private Button SubmitTimeEstimateBTN;

	@FXML
	private Button CreateEvaluationReportBTN;

	@FXML
	private TextField timeEstimatedEvaluation;

	@FXML
	private Pane PerformanceStage;

	@FXML
	private Button changeCompletedBTN;

	@FXML
	private Button SubmitTimeEstimatePerformanceBTN;

	@FXML
	private TextField timeEstimatedPerformance;

	@FXML
	private Pane examinationAndDecisionStage;

	@FXML
	private Button ViewEvaluationReportBTN;

	@FXML
	private Button AppointTesterBTN;

	@FXML
	private Label successful;

	@FXML
	private Label l1;

	@FXML
	private Label l2;
	
    @FXML
    private Label l3;

    @FXML
    private Label l4;
    @FXML
    private Text positonReq;
	@FXML
	void AppointTester(MouseEvent event) {
		listOfIt();
		showListOfIT("itTester");
	}

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

	public void setListOfIT(ArrayList<Object> arrayList) {
		this.list = arrayList;
	}

	@FXML
	void CreateEvaluationReport(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITMeaningAssessmentEvaluationReport");
		MainAllControllers.changeWin();
	}

	@FXML
	void ReportFailure(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITTestFailurReport");
		MainAllControllers.changeWin();
	}

	@FXML
	void ReqForTimeExtension(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITExtensionRequest");
		MainAllControllers.changeWin();
	}

	@FXML
	void ShowRequestDetails(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITRequestDetails");
		MainAllControllers.changeWin();
	}

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

	@FXML
	void TestApproval(MouseEvent event) {
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		DBmessage dbm;
		dbm = new DBmessage(MessageType.ITTestApproval, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
			MainAllControllers.setWindowVar("ITshowRequests");
			MainAllControllers.changeWin();
		} catch (IOException e) {
		}
	}

	@FXML
	void ViewEvaluationReport(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITCCCEvaluationReport");
		MainAllControllers.changeWin();
	}

	@FXML
	void changeCompleted(MouseEvent event) {
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		DBmessage dbm;
		dbm = new DBmessage(MessageType.ITchangeCompleted, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
			MainAllControllers.setWindowVar("ITshowRequests");
			MainAllControllers.changeWin();
		} catch (IOException e) {
		}
	}

	@FXML
	void goHelpPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITHelp");
		MainAllControllers.changeWin();
	}

	@FXML
	void goHomePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITHome");
		MainAllControllers.changeWin();
	}

	@FXML
	void goPersonalInfo(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITPersonalInfo");
		MainAllControllers.changeWin();
	}

	@FXML
	void goShowReq(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITshowRequests");
		MainAllControllers.changeWin();
	}

	@FXML
	void logoutPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
		MainAllControllers.logOutUser();
	}

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
	}

	public void setOnSucsess() {
		successful.setVisible(true);
	}

}

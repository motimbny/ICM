package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.updateRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SupervisorTimeRequestController implements Initializable {
	private MainAllControllers MainAllControllers;

	public SupervisorTimeRequestController() {
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	@FXML
	private Button homeBTN;

	@FXML
	private Button showreBTN;

	@FXML
	private Button MessageBTN;

	@FXML
	private Button personBTN;

	@FXML
	private Button helpBTN;

	@FXML
	private Button logoutBTN;

	@FXML
	private Button requestInfoBTN;

	@FXML
	private TextField requestID;

	@FXML
	private Button dentEvluationBTN;

	@FXML
	private TextField evluation;

	@FXML
	private Button approveEvluationBTN;

	@FXML
	private Button denyExwcutionBTN;

	@FXML
	private TextField execution;

	@FXML
	private Button approveExwcutionBTN;

	@FXML
	private Button BackToShow;

	@FXML
	private Label saved;

	@FXML
	void approveEvluation(MouseEvent event) {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		dbm = new DBmessage(MessageType.SupervisorApproveEvluationTime, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	@FXML
	void approveExwcution(MouseEvent event) {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		dbm = new DBmessage(MessageType.SupervisorApproveExecutionTime, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	@FXML
	void denyExwcution(MouseEvent event) {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		dbm = new DBmessage(MessageType.SupervisorDenyExecutionTime, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	@FXML
	void dentEvluation(MouseEvent event) {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		dbm = new DBmessage(MessageType.SupervisorDenyEvluationTime, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	@FXML
	void BackToS(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorShowRequests");
		MainAllControllers.changeWin();
	}

	@FXML
	void goHelpPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorHelp");
		MainAllControllers.changeWin();
	}

	@FXML
	void goHomePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorHome");
		MainAllControllers.changeWin();
	}

	@FXML
	void goLogoutPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
		MainAllControllers.logOutUser();
	}

	@FXML
	void goPersonalPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorPersonalInfo");
		MainAllControllers.changeWin();
	}

	@FXML
	void goShowReqPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorShowRequests");
		MainAllControllers.changeWin();
	}

	@FXML
	void messagePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorMessages");
		MainAllControllers.changeWin();
	}

	@FXML
	void requestInfoWindow(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorRequestDetalies");
		MainAllControllers.changeWin();
	}

	public void setToFields(ArrayList<Object> list) {
		this.requestID.setText("" + list.get(0));
		this.evluation.setText("" + list.get(1));
		this.execution.setText("" + list.get(2));
		if ((!(list.get(1).equals(0))) && (list.get(3).equals("waitingSupervisorApproveEvaluationTime"))) {
			approveEvluationBTN.setVisible(true);
			dentEvluationBTN.setVisible(true);
		}
		if ((!(list.get(2).equals(0))) && (list.get(3).equals("waitingSupervisorApproveExecutionTime"))) {
			approveExwcutionBTN.setVisible(true);
			denyExwcutionBTN.setVisible(true);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		this.requestID.setText("" + MainAllControllers.request);
		dbm = new DBmessage(MessageType.SupervisorTimeRequest, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}

	}

	public void setOnSucsess() {
		saved.setVisible(true);
	}

}

package controllers;

import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.util.ArrayList;

import Enums.MessageType;
import entity.DBmessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ITCCCRequestMoreInfoController implements Initializable {
	private MainAllControllers MainAllControllers;

	public ITCCCRequestMoreInfoController() {
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
	private TextArea requiredInformation;

	@FXML
	private Button sendCCRequireMoreInfo;

	@FXML
	private TextField requestID;

	@FXML
	private TextField date;

	@FXML
	private Label requestWasSubmitted;

	@FXML
	private Label fillAllFields;

	@FXML
	private Button BackToShow;

	@FXML
	void BackToS(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITHandleRequest");
		MainAllControllers.changeWin();
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
		MainAllControllers.user = null;
	}

	@FXML
	void submitRequireMoreInfo(MouseEvent event) {
		if (requiredInformation.getText().equals("")) {
			fillAllFields.setVisible(true);
		} else {
			fillAllFields.setVisible(false);
			ArrayList<Object> arr = new ArrayList<Object>();
			arr.add(Integer.parseInt(requestID.getText()));
			arr.add(date.getText());
			arr.add(requiredInformation.getText());
			DBmessage dbm;
			dbm = new DBmessage(MessageType.ITsubmitRequireMoreInfo, arr);
			try {
				MainAllControllers.sendToAbsServer(dbm);
			} catch (IOException e) {
			}
		}
	}

	public void setOnSucsess() {
		requestWasSubmitted.setVisible(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Object> arry = new ArrayList<Object>();
		int s = MainAllControllers.request;
		arry.add(s); // request id
		requestID.setText("" + s);
		LocalDate myObj = LocalDate.now();
		date.setText("" + myObj);
	}

}

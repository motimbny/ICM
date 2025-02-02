package controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.ServerFile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * User add request screen Controller . This window is in User GUI and allows
 * the user to add a new request.
 *
 * @author SHIRA
 */

public class UserAddRequestController implements Initializable {

	/** The file of user. */
	private ServerFile fileOfUser = null;

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/** The flag. */
	private boolean flag = false;

	/**
	 * Instantiates a new user add request controller.
	 */
	public UserAddRequestController() {
		MainAllControllers = MainAllControllers.getInstance();
	}

	/** The home BTN. */
	@FXML
	private Button homeBTN;

	/** The add request BTN. */
	@FXML
	private Button addreBTN;

	/** The show request BTN. */
	@FXML
	private Button showreBTN;

	/** The person BTN. */
	@FXML
	private Button personBTN;

	/** The help BTN. */
	@FXML
	private Button helpBTN;

	/** The logout BTN. */
	@FXML
	private Button logoutBTN;

	/** The submit request user add request. */
	@FXML
	private Button submitRequestUserAddReq;

	/** The describe existing situation. */
	@FXML
	private TextArea desExtSit;

	/** The choose information system comboBox. */
	@FXML
	private ChoiceBox<String> chooseInfoCom;

	/** The describe request change. */
	@FXML
	private TextArea desReqCha;

	/** The explain change benefits. */
	@FXML
	private TextArea expChaBen;

	/** The More info add. */
	@FXML
	private TextArea MorInfoAdd;

	/** The add attachments BTN. */
	@FXML
	private Button addAttachmentsUserAddReq;

	/** The bad add request. */
	@FXML
	private Label badAddRequest;

	/** The good add request. */
	@FXML
	private Label goodAddRe;

	/** The file name. */
	@FXML
	private Label fileName;

	/**
	 * If all fields of the request filled correctly save the data in server set a
	 * success or fail label
	 * 
	 * @param event The submit BTN
	 */
	@FXML
	void submitRequest(MouseEvent event) {
		if (desExtSit.getText().equals("") || desReqCha.getText().equals("") || expChaBen.getText().equals("")
				|| chooseInfoCom.getSelectionModel().isEmpty()) {
			badAddRequest.setVisible(true);
		} else {
			badAddRequest.setVisible(false);
			ArrayList<Object> arry = new ArrayList<Object>();
			arry.add(chooseInfoCom.getValue());
			arry.add(desReqCha.getText());
			arry.add(desExtSit.getText());
			arry.add(expChaBen.getText());
			arry.add(MorInfoAdd.getText());
			arry.add(MainAllControllers.user.getName());
			arry.add(MainAllControllers.user.getstrPosition());
			if (flag == true)
				arry.add(fileOfUser);
			DBmessage dbm = new DBmessage(MessageType.AddRequest, arry);
			try {
				MainAllControllers.sendToAbsServer(dbm);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method called when the submit of the request succeeded.
	 */
	public void setOnSucsess() {
		goodAddRe.setVisible(true);
	}

	/**
	 * If the user press on add attachments BTN, window of choose the file will open
	 * and after the file choose' it will save in server
	 *
	 * @param event Add attachments BTN
	 */
	@FXML
	private void attachFile(MouseEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open File");
		File file = chooser.showOpenDialog(new Stage());
		fileName.setText("+" + file.getName());
		fileName.setVisible(true);
		fileOfUser = new ServerFile(MainAllControllers.user.getName());
		String LocalfilePath = file.getPath();

		try {
			File newFile = new File(LocalfilePath);
			byte[] mybytearray = new byte[(int) newFile.length()];
			FileInputStream fis = new FileInputStream(newFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			fileOfUser.initArray(mybytearray.length);
			fileOfUser.setSize(mybytearray.length);
			bis.read(fileOfUser.getMybytearray(), 0, mybytearray.length);
			flag = true;
		} catch (Exception e) {
			System.out.println("Error send File to Server");
		}
	}

	/**
	 * Mouse click event, if "Home" button clicked, open the screen of "Home".
	 *
	 * @param event The Home BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("userHome");
		MainAllControllers.changeWin();
	}

	/**
	 * Mouse click event, if "Add request" button clicked, open the screen of "Add
	 * new request".
	 *
	 * @param event The Add request BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void backToRequest(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserAddRequest");
		MainAllControllers.setWindow();
	}

	/**
	 * Mouse click event, if "help" button clicked, open the screen of "help".
	 *
	 * @param event The Help BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void backTohelp(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserHelp");
		MainAllControllers.setWindow();
	}

	/**
	 * Mouse click event, if "Personal info" button clicked, open the screen of
	 * "Personal information".
	 *
	 * @param event The Personal info BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void backToinfo(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserPersonalInfo");
		MainAllControllers.setWindow();
	}

	/**
	 * Mouse click event, if "Show requests" button clicked, open the screen of
	 * "Show requests".
	 *
	 * @param event The Show requests
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void backTorAddequest(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserShowRequests");
		MainAllControllers.setWindow();
	}

	/**
	 * Mouse click event, if "logOut" button clicked, open the screen of "LogOut"
	 * and clean the fields.
	 *
	 * @param event The Logout BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void logut(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
		MainAllControllers.logOutUser();
	}

	/**
	 * Initializes GUI components before this window open.
	 *
	 * @param location  the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		chooseInfoCom.getItems().add("Moodle");
		chooseInfoCom.getItems().add("Information System");
		chooseInfoCom.getItems().add("Libary");
		chooseInfoCom.getItems().add("Class Rooms With Computers");
		chooseInfoCom.getItems().add("College Website");
		chooseInfoCom.getItems().add("Computer Farm");
		chooseInfoCom.getItems().add("Labs");
	}
}

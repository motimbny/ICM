package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class UserRequestDetailsController {
	private MainAllControllers MainAllControllers;
	public UserRequestDetailsController()
	{
    	MainAllControllers=MainAllControllers.getInstance();
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
    private Button addreBTN;

    @FXML
    private Button logoutBTN;

    @FXML
    private Label reqNumber;

    @FXML
    private TextField ApplicantNameField;

    @FXML
    private TextField InformationSystemField;

    @FXML
    private TextField requestStatusField;

    @FXML
    private TextArea DescriptionExistingSituationField;

    @FXML
    private TextArea DescriptionOfRequestField;

    @FXML
    private TextField RequestStageField;

    @FXML
    private Button BackToShow;

    @FXML
    void BackToS(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserShowRequests");
		MainAllControllers.changeWin();
	}

   
    @FXML
    void addreBTNE(MouseEvent event)throws IOException {
		MainAllControllers.setWindowVar("UserAddRequest");
		MainAllControllers.changeWin();

	}
   
    @FXML
    void goHelpPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserHelp");
		MainAllControllers.changeWin();
	}

    @FXML
    void goHomePage(MouseEvent event) throws IOException {

		MainAllControllers.setWindowVar("userHome");
		MainAllControllers.changeWin();
	}

    @FXML
    void goLogoutPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
		MainAllControllers.user = null;
	}
    @FXML
    void goPersonalPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserPersonalInfo");
		MainAllControllers.changeWin();
	}

    @FXML
    void goShowReqPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserShowRequests");
		MainAllControllers.changeWin();
	}
    
    

}

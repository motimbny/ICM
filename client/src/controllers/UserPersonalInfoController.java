package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * User personal information screen Controller .
 *
 * @author SHIRA
 */
public class UserPersonalInfoController implements Initializable 
{
	
	/**
	 * Instantiates a new user personal info controller.
	 */
	public UserPersonalInfoController() 
	{
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}
	
	/** The home BTN. */
	@FXML
	private Button homeBTN;

	/** The addre BTN. */
	@FXML
	private Button addreBTN;

	/** The showre BTN. */
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

	/** The User name. */
	@FXML
	private Label UserName;

	/** The email. */
	@FXML
	private Label email;

	/** The position. */
	@FXML
	private Label position;
	 
 	/** The student IMG. */
 	@FXML
	    private ImageView studentIMG;

	    /** The lecturer IMG. */
    	@FXML
	    private ImageView lecturerIMG;

	    /** The emp IMG. */
    	@FXML
	    private ImageView empIMG;


	/**
	 * Mouse click event, if "Add request" button clicked, open the screen of "Add new request".
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void addreBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserAddRequest");
		MainAllControllers.changeWin();

	}
	 
 	/**
 	 * Mouse click event, if "help" button clicked, open the screen of "help".
 	 *
 	 * @param event the event
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */

	@FXML
	void helpBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserHelp");
		MainAllControllers.changeWin();
	}
    
    /**
     * Mouse click event, if "Home" button clicked, open the screen of "Home".
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
	@FXML
	void backHome(MouseEvent event) throws IOException {

		MainAllControllers.setWindowVar("userHome");
		MainAllControllers.changeWin();
	}
	
	/**
	 * Mouse click event, if "logOut" button clicked, open the screen of "LogOut" and clean the fields.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void logoutBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
    	MainAllControllers.logOutUser();
	}
	 
 	/**
 	 * Mouse click event, if "Personal info" button clicked, open the screen of "Personal information".
 	 *
 	 * @param event the event
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
	@FXML
	void personBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserPersonalInfo");
		MainAllControllers.changeWin();
	}
	
	/**
	 * Mouse click event, if "Show requests" button clicked, open the screen of "Show requests".
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void showreBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserShowRequests");
		MainAllControllers.changeWin();
	}

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

/**
 * initialize function - set the relevant user information on the screen: user name, email and position.
 *
 * @param location the location
 * @param resources the resources
 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		UserName.setText(MainAllControllers.user.getName());
		email.setText(MainAllControllers.user.getName() + "@braude.ac.il");
		position.setText(MainAllControllers.user.getstrPosition());
        switch (MainAllControllers.user.getstrPosition())
        {
	        case "lecturer":
	        {
	        	lecturerIMG.setVisible(true);

	        }
	        break;
	        case "student":
	        {
	        	studentIMG.setVisible(true);
	        }
	        break;
	        case "CollegeEmployee":
	        {
	        	empIMG.setVisible(true);
	        }
	        break;
        }
	}

}

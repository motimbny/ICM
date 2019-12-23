package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class UserHomeController 
{
    public UserHomeController()
    {
    	MainAllControllers=MainAllControllers.getInstance();
    }
    @FXML
    private Button homeBTN;

    @FXML
    private Button addreBTN;

    @FXML
    private Button showreBTN;

    @FXML
    private Button personBTN;

    @FXML
    private Button helpBTN;

    @FXML
    private Button logoutBTN;
    private MainAllControllers MainAllControllers;

    @FXML
    void addreBTNE(MouseEvent event)
    {
          
    }

    @FXML
    void helpBTNE(MouseEvent event) 
    {

    }

    @FXML
    void logoutBTNE(MouseEvent event)
    {
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
    }

    @FXML
    void personBTNE(MouseEvent event) 
    {

    }

    @FXML
    void showreBTNE(MouseEvent event) 
    {
    	
    }

}

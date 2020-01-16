package boundries;

import java.io.IOException;

import controllers.serverController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The Class mainServer.
 */
public class mainServer extends Application
{
	
	/** The num of request. */
	public static int NUM_OF_REQUEST=0;
     
     /** The controller. */
     serverController controller;
     
     /** The pane. */
     Pane pane;
     
     /** The s 1. */
     Scene s1;
	 
 	/** The window. */
 	Stage window;
	 
 	/**
 	 * This method load the server GUI and display it to the screen.
 	 *
 	 * @param primaryStage the primary stage
 	 */
 	@Override
	 public void start(Stage primaryStage) 
	 {
		    window=primaryStage;
	        try 
	        {
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("/Fxml/server.fxml"));
	            pane =  loader.load();
	            controller = loader.getController();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	            return;
	        }
	        s1= new Scene(pane);
	        window.setTitle("ICM-Server");
	        window.setScene(s1);
	        window.show(); 
	  }  
	 
 	/**
 	 * The main method.
 	 *
 	 * @param args the arguments
 	 */
 	public static void main(String[]args)
	   {
		   launch(args);
	   }

}

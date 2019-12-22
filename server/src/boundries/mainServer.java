package boundries;

import java.io.IOException;

import controllers.serverController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class mainServer extends Application
{
     serverController controller;
     Pane pane;
     Scene s1;
	 Stage window;
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
	 public static void main(String[]args)
	   {
		   launch(args);
	   }

}

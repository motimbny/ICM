package controllers;

import java.io.IOException;

import boundries.mainClientABS;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import entity.WindowToShow;
public class MainAllControllers 
{
	mainClientABS mcABS;               //singleton of one 
	Stage window;
	Scene s;
    Pane pane;
	private WindowToShow WindowToShow;
    public MainAllControllers(Stage window)
    {
    	this.window=window;
    	WindowToShow=new WindowToShow();
    	WindowToShow.setWindowToShow("login");
    }
    public void setWindowVar(String str)
    {
    	this.WindowToShow.setWindowToShow(str);
    }
	public void setWindow() 
	{
		loadScene();
		setScence(s);
	}
	public void loadScene()
	{
	        try 
	        {
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource(WindowToShow.getPathfxml()));
	            pane =  loader.load();
	            WindowToShow.controller = loader.getController();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	            return;
	        }
	        s= new Scene(pane);	
	}
	public void setScence(Scene s)
	{
		 window.setTitle(WindowToShow.getWindowName());
	     window.setScene(s);
	     window.show(); 
	}
}

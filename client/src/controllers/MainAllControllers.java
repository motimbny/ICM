package controllers;

import java.io.IOException;
import java.util.ArrayList;

import boundries.mainClientABS;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import entity.RequestUser;
import entity.User;
import entity.WindowToShow;
public class MainAllControllers 
{
	public mainClientABS mcABS;       //singleton of one 
	public Stage window;
	public User user;
	Scene s;
	Pane pane;
    private static MainAllControllers mac=new MainAllControllers();
	public WindowToShow WindowToShow;
	private MainAllControllers() 
	{
		this.mac=mac;
	}
	public void setMainAbs() throws IOException
	{
		mcABS=new mainClientABS("",5555);
	}
	public static MainAllControllers getInstance()
	{
		return mac;
	}
    public void initMainAllControllers(Stage window) throws IOException
    {
    	this.window=window;
    	WindowToShow=new WindowToShow();
    	WindowToShow.setWindowToShow("login");
    	setWindow();  	
    }
    protected static void changeWindow(MainAllControllers MainAllControllers) 
    {
    	Platform.runLater(new Runnable() {

			@Override
			public void run() 
			{
				MainAllControllers.setWindow();
			}
		});
    }
    public void setWindowVar(String str) throws IOException
    {
    	this.WindowToShow.setWindowToShow(str);
    }
    public void changeWin()
    {
    	changeWindow(this);
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
	public void sendToAbsServer(Object msg) throws IOException
	{
		mcABS.openConToServer();
		mcABS.sendToServer(msg);
	}
	public void badUser()
	{
		((LoginController) WindowToShow.controller).setVisibleLbl();
	}
	public void goodRequeSend()
	{
		((UserAddRequestController) WindowToShow.controller).setOnSucsess();		
	}
	public void showUserReq(ArrayList<Object> list)
	{
		((UserShowRequestsController) WindowToShow.controller).setTextInTable(list);
	}
	
	public void setUser(User user)
	{
		this.user=user;
	}
}

package boundries;

import controllers.MainAllControllers;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainToRun extends Application
{
	public static int NUM_OF_REQUEST=0;
	@Override
	public void start(Stage stage) throws Exception 
	{
		MainAllControllers mc =MainAllControllers.getInstance();
		mc.initMainAllControllers(stage);
	}
	public static void main(String[] args)
	{
		launch(args);
	}
}

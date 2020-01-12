package boundries;

import controllers.MainAllControllers;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Class MainToRun.
 */
public class MainToRun extends Application
{
	
	/**
	 * Start.
	 *
	 * @param stage the stage
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage stage) throws Exception 
	{
		MainAllControllers mc =MainAllControllers.getInstance();
		mc.initMainAllControllers(stage);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
}

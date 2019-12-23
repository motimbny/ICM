package boundries;

import controllers.MainAllControllers;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainToRun extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		MainAllControllers mc = new MainAllControllers(stage);
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * @author Gianluca Parilli
 * @version 1.0 
 * @Course : ITEC 3860, Fall, 2017 Written: October 12, 2017
 *  
 */
public class LostTreasureMain extends Application{
	 static Stage guiStage = new Stage();
	 static GUI gui = new GUI();

	public void start(Stage primaryStage) throws InterruptedException {	
		guiStage = primaryStage ;

		Stage newStage = new Stage();
		Login login = new Login();

		//starts the login gui 
		login.start(newStage);
		
		
	}
	public static void main(String[] args) throws Exception  {
		launch(args);
		
	}
	

}

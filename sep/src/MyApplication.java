import javafx.application.Application;
import javafx.stage.Stage;
import model.Manager;
import view.ViewHandler;
import model.Model;

/**
 * A class representing the application that starts the ViewHandler
 */
public class MyApplication extends Application
{
  private Model model;

  /**
   * The start method that sets the first stage, the model, and starts the ViewHandler with them.
   * @param primaryStage primary stage
   * @throws Exception Due to the Manager throws an exception is required to start
   */
  public void start(Stage primaryStage) throws Exception
  {
    model = new Manager();
    ViewHandler view = new ViewHandler(model);
    view.start(primaryStage);
  }
}

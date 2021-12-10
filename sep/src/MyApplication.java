import javafx.application.Application;
import javafx.stage.Stage;
import model.Manager;
import view.ViewHandler;
import model.Model;

public class MyApplication extends Application
{
  private Model model;
  public void start(Stage primaryStage) throws Exception
  {
    model = new Manager();
    ViewHandler view = new ViewHandler(model);
    view.start(primaryStage);
  }
}

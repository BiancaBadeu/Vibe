package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;

import java.util.Optional;

/**
 * A class representing the controller of an FXML file RemoveSession
 */
public class RemoveSessionController
{
  @FXML  TableView tableView;
  @FXML  ChoiceBox choiceBox;
  private Region root;
  private Model model;
  private view.ViewHandler viewHandler;

  static Session session;

  /**
   * @return courses stores the current listOfCourses as an array
   *
   * A String[] method that obtains the current listOfCourses and stores it in an array.
   */
  public String[] getCourses()
  {
    String[] courses = new String[200];
    for(int i=0;i<model.getAllCoursesAsArrayList().size();i++)
    {
      courses[i] = model.getAllCoursesAsArrayList().get(i).getCourseID();
    }
    return courses;
  }

  //ObservableList<String> displayChoiceBox = FXCollections.observableArrayList(getCourses());

  /**
   * An empty 0 argument constructor
   */
  public RemoveSessionController() {}

  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   * Initializer method for the class. The parameters are initialized and table columns for the TableView are
   * created. Content is added through a loop where the details of unbooked sessions are placed on the columns
   *
   */
  public void init(ViewHandler viewHandler, Model model, Region root){
    this.viewHandler= viewHandler;
    this.model= model;
    this.root= root;


    TableColumn numbers = new TableColumn("Number");
    numbers.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn course = new TableColumn("Course");
    course.setCellValueFactory(new PropertyValueFactory<>("course"));
    TableColumn numbersOfLessons = new TableColumn("No. of lessons");
    numbersOfLessons.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsInSession"));
    TableColumn numbersOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numbersOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));
    TableColumn getNumbersOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumbersOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));

    tableView.getColumns().setAll(numbers, course, numbersOfLessons, numbersOfLessonsForCourse, getNumbersOfLessonsRemaining);
    try
    {
      for (int i = 0; i < model.getUnbookedSessions().size(); i++)
      {
        tableView.getItems().add(model.getUnbookedSessions().get(i));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    //choiceBox.setItems(displayChoiceBox);
    //choiceBox.setValue(model.getAllCoursesAsArrayList().get(0));
  }

  /**
   * @throws Exception import java.io.PrintWriter requires an exception to be thrown in order for operations to be performed
   *
   * An FXML method called when a button Remove is pressed. Confirmation method is called and if positive continues
   * {@link #confirmation()}
   * The session hovered when the button Remove is pressed is removed from the listOfSessions and the files are updated
   */
  @FXML private void pressToRemove() throws Exception
  {
    int index = tableView.getSelectionModel().getFocusedIndex();
    if(index > -1)
    {
      session= model.getUnbookedSessions().get(index);
      if(confirmation())
      {
        model.removeSession(session);
        model.writeFiles();
      }
    }
    reset();
  }

  /**
   * An FXML method called when a button Cancel is pressed. The current window is closed and a new one opens.
   * @see ManageSessionsController
   */
  @FXML private void pressToCancel()
  {
    viewHandler.openView("ManageSessions");
  }

  /**
   * @return check if the Alert is still present and the ok button has been pressed
   *
   * A boolean method that creates an Alert. As mentioned before returns the above condition
   */
  private boolean confirmation()
  {
    Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Removing session {" + session.getNumber() + ": "+ session.getCourse() + "}");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent())&&(result.get()==ButtonType.OK);
  }

  /**
   * Reset method for the class that clears the table columns from their previous elements and updates them
   * with the new ones through a loop
   */
  public void reset()
  {
    tableView.getItems().clear();
    TableColumn numbers = new TableColumn("Number");
    numbers.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn course = new TableColumn("Course");
    course.setCellValueFactory(new PropertyValueFactory<>("course"));
    TableColumn numbersOfLessons = new TableColumn("No. of lessons");
    numbersOfLessons.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsInSession"));
    TableColumn numbersOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numbersOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));
    TableColumn getNumbersOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumbersOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));

    tableView.getColumns().setAll(numbers, course, numbersOfLessons, numbersOfLessonsForCourse, getNumbersOfLessonsRemaining);
    try
    {
      for (int i = 0; i < model.getUnbookedSessions().size(); i++)
      {
        tableView.getItems().add(model.getUnbookedSessions().get(i));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * @return root
   * Returns the current root
   */
  public Region getRoot()
  {
    return root;
  }

}

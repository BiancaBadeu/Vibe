package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;

/**
 * A class representing a controller for an FXML SelectSession
 */
public class SelectSessionController
{
  @FXML TableView SessionTable;

  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  static Session session;

  /**
   * Empty 0 argument constructor
   */
  public SelectSessionController(){}

  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * An initializer method for the class. The parameters are initialized and columns for a TableView are created.
   * Through a loop the session details are obtained from the listOfSessions and placed in the columns
   */
  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;


    TableColumn numbers = new TableColumn("Number");
    numbers.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn numbersOfLessons = new TableColumn("No. of lessons");
    numbersOfLessons.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsInSession"));
    TableColumn numbersOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numbersOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));
    TableColumn getNumbersOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumbersOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));


    SessionTable.getColumns().setAll(numbers, numbersOfLessons, numbersOfLessonsForCourse, getNumbersOfLessonsRemaining);
    try
    {
      for (int i = 0; i < model.getUnbookedSessions().size(); i++)
      {
        if(model.getUnbookedSessions().get(i).getCourse().getCourseID().equals(SelectCourseController.course.getCourseID()))
          SessionTable.getItems().add(model.getUnbookedSessions().get(i));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * An FXML method called when a button Continue is pressed. The hovered session is taken. The current window is closed
   * and a new one opens
   * @see BookARoomController
   */
  @FXML public void sessionContinuePressed()
  {
    int index = SessionTable.getSelectionModel().getFocusedIndex();
    if(index > -1)
    {
        for (int i = 0; i < model.getUnbookedSessions().size() && index>=0; i++)
        {
          if(model.getUnbookedSessions().get(i).getCourse().getCourseID().equals(SelectCourseController.course.getCourseID()))
            if(index!=0)
              index--;
            else
              session = model.getUnbookedSessions().get(i);
        }
      viewHandler.openView("BookARoom");
    }
  }

  /**
   * An FXMl method called when a button Go Back is pressed. The current window is closed a new one opens
   * @see SelectCourseController
   */
  @FXML public void sessionGoBackPressed()
  {
    viewHandler.openView("SelectCourse");
  }

  /**
   * Reset method for the class. The contents of the table columns are reset and updated with the current ones from the
   * list of sessions
   */
  public void reset() {

    SessionTable.getItems().clear();
    for (int i = 0; i < model.getUnbookedSessions().size(); i++)
    {
      if(model.getUnbookedSessions().get(i).getCourse().equals(SelectCourseController.course))
        SessionTable.getItems().add(model.getUnbookedSessions().get(i));
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

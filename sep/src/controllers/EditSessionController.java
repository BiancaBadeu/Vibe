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
 * A class representing the controller of the FXML file EditSession
 */
public class EditSessionController
{

  @FXML  TableView tableView;
  @FXML  ChoiceBox<Course> choiceBox;
  @FXML  TextField newLessons;
  @FXML  Label errorLabel;
  private Region root;
  private model.Model model;
  private view.ViewHandler viewHandler;

  static Session session;

  ObservableList<Course> displayChoiceBox = (ObservableList<Course>) FXCollections.observableArrayList(
      new CourseList().getAllCoursesAsArrayList());

  /**
   * Empty 0 argument constructor
   */
  public EditSessionController() {}

  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * Initializer method for the GUI window, the parameters mentioned are
   * initialized and the table columns for the TableView are created and added to the Table
   * Finally, the details of unbooked sessions are added through a loop to the columns
   */
  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    TableColumn numbers = new TableColumn("Number");
    numbers.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn course = new TableColumn("Course");
    course.setCellValueFactory(new PropertyValueFactory<>("course"));
    TableColumn numbersOfLessons = new TableColumn("No. of lessons");
    numbersOfLessons.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsInSession"));
    TableColumn getNumbersOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumbersOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));
    TableColumn numbersOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numbersOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));


    tableView.getColumns().setAll(numbers, course, numbersOfLessons,getNumbersOfLessonsRemaining, numbersOfLessonsForCourse);
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
    //choiceBox.setValue(null);
  }

  /**
   * A method that when used clears the previous items added to the columns are removed
   * and updated through the loop of getting the unbooked session details.
   *{@link #init(ViewHandler, Model, Region)}
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
    TableColumn getNumbersOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumbersOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));
    TableColumn numbersOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numbersOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));


    tableView.getColumns().setAll(numbers, course, numbersOfLessons,getNumbersOfLessonsRemaining, numbersOfLessonsForCourse);
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
   * @throws Exception import java.io.PrintWriter requires an exception to be thrown in order to perform operations
   *
   * An FXML method that when a button named Edit is pressed the changes made by the user input are
   * updated in the listOfSessions within the model. Session files are also written with the updated list.
   */
  @FXML private void pressToEdit() throws Exception
  {
    int index = tableView.getSelectionModel().getFocusedIndex();
    if(index > -1)
    {
      session= model.getUnbookedSessions().get(index);
    }
    try{
      model.validateEditSession(newLessons.getText());
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText(e.getMessage());
    }
    if(errorLabel.getText().equals(""))
    {
      if(confirmation())
      {
        int lessonNumbers = Integer.parseInt(newLessons.getText());
        session.setNumberOfLessonsInSession(lessonNumbers);
        model.writeFiles();
      }
    }
    reset();
  }

  /**
   * An FXML method called when a button called Cancel is pressed the current window is closed, and a new one is open
   * @see ManageSessionsController
   */
  @FXML private void pressToCancel()
  {
    viewHandler.openView("ManageSessions");
  }

  /**
   * @return the check if the alert answer in the alert message is ok and the alert is no longer showing up.
   *
   * Boolean method to confirm the edit of a session. An alert is shown, if the alert is accepted, the return is true
   * and used in the method pressToEdit().
   * {@link #pressToEdit()}
   */
  private boolean confirmation()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(
        "Edit number of lesson for session" + session.getNumber() + ", of the course " + session.getCourse() + "?");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

  /**
   * @return root
   *
   * Current root is returned by the getter method
   */
  public Region getRoot()
  {
    return root;
  }

}